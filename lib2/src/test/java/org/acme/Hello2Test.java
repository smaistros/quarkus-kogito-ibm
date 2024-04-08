package org.acme;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.List;
import org.acme.Lib2Unit;
import org.drools.ruleunits.api.RuleUnitInstance;
import org.drools.ruleunits.api.RuleUnitProvider;
import org.junit.jupiter.api.Test;
import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
public class Hello2Test {

    @Test
    public void testHello1Endpoint() {

        Lib2Unit lib1Unit = new Lib2Unit();
        RuleUnitInstance<Lib2Unit> instance = RuleUnitProvider.Factory.get().createRuleUnitInstance(lib1Unit);
        try {
            lib1Unit.getStrings().add("hello");                    

            List<String> queryResult = instance.executeQuery("hello2").toList("$s");
            String strings = "";
            for ( String s : queryResult ){
                strings = strings + s;
            }
            assertEquals("hello hello world from lib2 hello world fom from lib2 decision table", strings);
        } finally {
            instance.close();
        }

    }
}