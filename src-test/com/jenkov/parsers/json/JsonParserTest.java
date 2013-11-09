package com.jenkov.parsers.json;

import com.jenkov.parsers.core.DataCharBuffer;
import com.jenkov.parsers.core.IndexBuffer;
import junit.framework.Assert;
import org.junit.Test;

/**
 */
public class JsonParserTest {

    @Test
    public void test() {

        DataCharBuffer dataBuffer = new DataCharBuffer();
        dataBuffer.data = "{  key : \"value\", key2 : \"value2\" }".toCharArray();
        dataBuffer.length = dataBuffer.data.length;

        IndexBuffer elementBuffer = new IndexBuffer(dataBuffer.data.length, true);

        JsonParser parser = new JsonParser();

        parser.parse(dataBuffer, elementBuffer);


        Assert.assertEquals(ElementTypes.JSON_OBJECT_START  , elementBuffer.type[0]);
        Assert.assertEquals(ElementTypes.JSON_PROPERTY_NAME , elementBuffer.type[1]);
        Assert.assertEquals(ElementTypes.JSON_PROPERTY_VALUE, elementBuffer.type[2]);
        Assert.assertEquals(ElementTypes.JSON_PROPERTY_NAME , elementBuffer.type[3]);
        Assert.assertEquals(ElementTypes.JSON_PROPERTY_VALUE, elementBuffer.type[4]);
        Assert.assertEquals(ElementTypes.JSON_OBJECT_END    , elementBuffer.type[5]);
    }

    @Test
    public void testArrays() {
        DataCharBuffer dataBuffer = new DataCharBuffer();
        dataBuffer.data = "{  key : \"value\", key2 : [ \"value2\", \"value3\" ], key3: \"value4\" }".toCharArray();
        dataBuffer.length = dataBuffer.data.length;

        IndexBuffer elementBuffer = new IndexBuffer(dataBuffer.data.length, true);

        JsonParser parser = new JsonParser();

        parser.parse(dataBuffer, elementBuffer);


        Assert.assertEquals(ElementTypes.JSON_OBJECT_START  , elementBuffer.type[0]);
        Assert.assertEquals(ElementTypes.JSON_PROPERTY_NAME , elementBuffer.type[1]);
        Assert.assertEquals(ElementTypes.JSON_PROPERTY_VALUE, elementBuffer.type[2]);
        Assert.assertEquals(ElementTypes.JSON_PROPERTY_NAME , elementBuffer.type[3]);
        Assert.assertEquals(ElementTypes.JSON_ARRAY_START   , elementBuffer.type[4]);
        Assert.assertEquals(ElementTypes.JSON_ARRAY_VALUE   , elementBuffer.type[5]);
        Assert.assertEquals(ElementTypes.JSON_ARRAY_VALUE   , elementBuffer.type[6]);
        Assert.assertEquals(ElementTypes.JSON_ARRAY_END     , elementBuffer.type[7]);
        Assert.assertEquals(ElementTypes.JSON_PROPERTY_NAME , elementBuffer.type[8]);
        Assert.assertEquals(ElementTypes.JSON_PROPERTY_VALUE, elementBuffer.type[9]);

        Assert.assertEquals(ElementTypes.JSON_OBJECT_END    , elementBuffer.type[10]);
    }

    @Test
    public void testArraysWithObjectsWithArraysWithObjects() {
        DataCharBuffer dataBuffer = new DataCharBuffer();
        dataBuffer.data = "{  key : \"value\", key2 : [ \"value2\", { key21 : \"value22\", key22 : [ \"value221\", \"value222\"]} ], key3: \"value4\" }".toCharArray();
        dataBuffer.length = dataBuffer.data.length;

        IndexBuffer elementBuffer = new IndexBuffer(dataBuffer.data.length, true);

        JsonParser parser = new JsonParser();

        parser.parse(dataBuffer, elementBuffer);


        Assert.assertEquals(ElementTypes.JSON_OBJECT_START   , elementBuffer.type[ 0]);
        Assert.assertEquals(ElementTypes.JSON_PROPERTY_NAME  , elementBuffer.type[ 1]);
        Assert.assertEquals(ElementTypes.JSON_PROPERTY_VALUE , elementBuffer.type[ 2]);
        Assert.assertEquals(ElementTypes.JSON_PROPERTY_NAME  , elementBuffer.type[ 3]);
        Assert.assertEquals(ElementTypes.JSON_ARRAY_START    , elementBuffer.type[ 4]);
        Assert.assertEquals(ElementTypes.JSON_ARRAY_VALUE    , elementBuffer.type[ 5]);
        Assert.assertEquals(ElementTypes.JSON_OBJECT_START   , elementBuffer.type[ 6]);
        Assert.assertEquals(ElementTypes.JSON_PROPERTY_NAME  , elementBuffer.type[ 7]);
        Assert.assertEquals(ElementTypes.JSON_PROPERTY_VALUE , elementBuffer.type[ 8]);
        Assert.assertEquals(ElementTypes.JSON_PROPERTY_NAME  , elementBuffer.type[ 9]);
        Assert.assertEquals(ElementTypes.JSON_ARRAY_START    , elementBuffer.type[10]);
        Assert.assertEquals(ElementTypes.JSON_ARRAY_VALUE    , elementBuffer.type[11]);
        Assert.assertEquals(ElementTypes.JSON_ARRAY_VALUE    , elementBuffer.type[12]);
        Assert.assertEquals(ElementTypes.JSON_ARRAY_END      , elementBuffer.type[13]);
        Assert.assertEquals(ElementTypes.JSON_OBJECT_END     , elementBuffer.type[14]);
        Assert.assertEquals(ElementTypes.JSON_ARRAY_END      , elementBuffer.type[15]);
        Assert.assertEquals(ElementTypes.JSON_PROPERTY_NAME  , elementBuffer.type[16]);
        Assert.assertEquals(ElementTypes.JSON_PROPERTY_VALUE , elementBuffer.type[17]);

        Assert.assertEquals(ElementTypes.JSON_OBJECT_END     , elementBuffer.type[18]);

    }
}
