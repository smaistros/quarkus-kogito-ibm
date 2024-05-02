package org.acme.service;

import java.util.List;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import org.acme.Lib1Unit;
import org.acme.Lib2Unit;
import org.drools.ruleunits.api.RuleUnit;
import org.drools.ruleunits.api.RuleUnitInstance;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;

@Path("/hello")
public class HelloService{

    @Inject
    RuleUnit<Lib1Unit> ruleUnit1;

    @Inject
    RuleUnit<Lib2Unit> ruleUnit2;

    @GET
    public JSONArray hello() {
        String returnString = "Hello from RESTEasy Reactive \n";

        JSONArray ja = new JSONArray();
        // DO A MAINFRAIME CALL
        ja.add(getInstanceLib1()); // Rule service
        // DO A SECOND MAINFRAIME CALL
        ja.add(getInstanceLib2()); // Rule service
        // DO A THIRD MAINFRAIME CALL
        return ja;
    }

    private JSONObject getInstanceLib1() {
        JSONObject jo = new JSONObject();     

        Lib1Unit lib1Unit = new Lib1Unit();
        RuleUnitInstance<Lib1Unit> instance = ruleUnit1.createInstance(lib1Unit);
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
        RuleUnitInstance<Lib2Unit> instance = ruleUnit2.createInstance(lib2Unit);

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
