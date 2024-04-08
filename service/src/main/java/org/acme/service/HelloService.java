package org.acme.service;

import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import org.acme.Lib1Unit;
import org.acme.Lib2Unit;
import org.drools.ruleunits.api.RuleUnitInstance;
import org.drools.ruleunits.api.RuleUnitProvider;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;

@Path("/hello")
public class HelloService{

    @GET    
    public JSONArray hello() {
        String returnString = "Hello from RESTEasy Reactive \n";

        JSONArray ja = new JSONArray();

        ja.add(getInstanceLib1());
        ja.add(getInstanceLib2());

        return ja;
    }

    private JSONObject getInstanceLib1() {
        JSONObject jo = new JSONObject();     

        Lib1Unit lib1Unit = new Lib1Unit();
        RuleUnitInstance<Lib1Unit> instance = RuleUnitProvider.Factory.get().createRuleUnitInstance(lib1Unit);
        try {
            lib1Unit.getStrings().add("hello");                    

            List<String> queryResult = instance.executeQuery("hello1").toList("$s");
            String strings = "";
            for ( String s : queryResult ){
                strings = strings + s;
            }           
                            
            jo.put("lib1 returned:",strings);
            

        } finally {
            instance.close();
        }

        return jo;
    }


    private JSONObject getInstanceLib2() {
        JSONObject jo = new JSONObject();                

        Lib2Unit lib2Unit = new Lib2Unit();
        RuleUnitInstance<Lib2Unit> instance = RuleUnitProvider.Factory.get().createRuleUnitInstance(lib2Unit);
        try {
            lib2Unit.getStrings().add("hello");                    

            List<String> queryResult = instance.executeQuery("hello2").toList("$s");
            String strings = "";
            for ( String s : queryResult ){
                strings = strings + s;
            }          
            jo.put("lib2 returned:",strings);
        } finally {
            instance.close();
        }

        return jo;
    }

}
