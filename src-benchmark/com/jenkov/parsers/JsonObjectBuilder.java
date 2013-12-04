package com.jenkov.parsers;

import com.jenkov.parsers.core.DataCharBuffer;
import com.jenkov.parsers.core.IndexBuffer;
import com.jenkov.parsers.json.ElementTypes;
import com.jenkov.parsers.json.JsonNavigator;

/**
 */
public class JsonObjectBuilder {

    public static JsonObject parseJsonObject(DataCharBuffer buffer, IndexBuffer elementBuffer) {
        JsonNavigator jsonNavigator = new JsonNavigator(buffer, elementBuffer);
        return parseJsonObject(jsonNavigator);
    }

    public static JsonObject parseJsonObject(JsonNavigator jsonNavigator) {

        JsonObject jsonObject = new JsonObject();

        jsonNavigator.next(); //skip over object begin

        while(jsonNavigator.type() != ElementTypes.JSON_OBJECT_END){
            //String fieldName = jsonNavigator.asString(); //field name
            jsonNavigator.next();  //move to field value

            if(jsonNavigator.isEqualUnencoded("key")) {
                jsonObject.key = jsonNavigator.asString();
                jsonNavigator.next();
            } else if(jsonNavigator.isEqualUnencoded("key2")){
                jsonObject.key2 = jsonNavigator.asInt();
                jsonNavigator.next();
            } else if(jsonNavigator.isEqualUnencoded("key3")) {
                jsonObject.key3 = jsonNavigator.asBoolean();
                jsonNavigator.next();
            } else if(jsonNavigator.isEqualUnencoded("stringArray")) {
                jsonNavigator.next();  //skip over array start
                String[] strings = new String[jsonNavigator.countPrimitiveArrayElements()];
                for(int i=0, n=strings.length; i<n; i++) {
                    strings[i] = jsonNavigator.asString();
                    jsonNavigator.next();
                }
                jsonObject.stringArray = strings;
                jsonNavigator.next();  //skip over array end
            } else if(jsonNavigator.isEqualUnencoded("numberArray")) {
                jsonNavigator.next();  //skip over array start
                int[] ints = new int[jsonNavigator.countPrimitiveArrayElements()];
                for(int i=0, n=ints.length; i<n; i++){
                    ints[i] = jsonNavigator.asInt();
                    jsonNavigator.next();
                }
                jsonObject.numberArray = ints;
                jsonNavigator.next();   //skip over array end
            } else if(jsonNavigator.isEqualUnencoded("booleanArray")) {
                jsonNavigator.next();   //skip over array start
                boolean[] booleans = new boolean[jsonNavigator.countPrimitiveArrayElements()];

                for(int i=0, n= booleans.length; i<n; i++){
                    booleans[i] = jsonNavigator.asBoolean();
                    jsonNavigator.next();
                }
                jsonObject.booleanArray = booleans;
                jsonNavigator.next();  //skip over array end
            } else if(jsonNavigator.isEqualUnencoded("sub")){
                jsonObject.sub = parseJsonObject(jsonNavigator);
            }
        }

        jsonNavigator.next(); //skip over object end

        return jsonObject;
    }

}