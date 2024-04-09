package org.acme;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.List;
import org.acme.Lib1Unit;
import org.drools.ruleunits.api.RuleUnitInstance;
import org.drools.ruleunits.api.RuleUnitProvider;
import org.junit.jupiter.api.Test;
import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
public class Hello1Test {

    @Test
    public void testHello1Endpoint() {

        Lib1Unit lib1Unit = new Lib1Unit();
        RuleUnitInstance<Lib1Unit> instance = RuleUnitProvider.Factory.get().createRuleUnitInstance(lib1Unit);
        try {
            lib1Unit.getStrings().add("hello");                    

            List<String> queryResult = instance.executeQuery("hello1").toList("$s");
            String strings = "";
            for ( String s : queryResult ){
                strings = strings + s;
            }
            assertEquals("hello hello world from lib1 hello world from lib1 decision table", strings);
        } finally {
            instance.close();
        }

    }
}