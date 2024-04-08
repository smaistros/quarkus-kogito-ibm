/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.acme;

import org.drools.core.event.DefaultAgendaEventListener;
import org.jboss.logging.Logger;
import org.kie.api.event.rule.AfterMatchFiredEvent;
import java.util.Iterator;
import javax.inject.Singleton;

@Singleton
public class MyAgendaEventListener extends DefaultAgendaEventListener {
    private static final Logger LOG = Logger.getLogger(MyAgendaEventListener.class);

    @Override
    public void afterMatchFired(AfterMatchFiredEvent event) {
        String ruleName = event.getMatch().getRule().getName();        

        Iterator<? extends Object> iter = event.getMatch().getObjects().iterator();
        while (iter.hasNext()) {
            Object matchedobj = iter.next();
            LOG.info("Rule fired:"+ruleName + " LHS:"+formatLHSObject(matchedobj));
        }        
    }

	/**
	 * Method formatting an object matching the rules conditions
	 */
	protected String formatLHSObject(Object obj) {
		return "  -> "+obj.toString();
	}    
}
