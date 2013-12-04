package com.jenkov.parsers.json;

import com.jenkov.parsers.core.DataCharBuffer;
import com.jenkov.parsers.core.IndexBuffer;
import junit.framework.Assert;
import org.junit.Test;

/**
 */
public class JsonParser2Test {

    @Test
    public void test() {
        DataCharBuffer dataBuffer = new DataCharBuffer();
        dataBuffer.data = "{  \"key\" : \"value\", \"key2\" : \"value2\" }".toCharArray();
        dataBuffer.length = dataBuffer.data.length;

        IndexBuffer elementBuffer = new IndexBuffer(dataBuffer.data.length, true);

        JsonParser2 parser = new JsonParser2();

        parser.parse(dataBuffer, elementBuffer);

        Assert.assertEquals(ElementTypes.JSON_OBJECT_START  , elementBuffer.type[0]);
        Assert.assertEquals(ElementTypes.JSON_PROPERTY_NAME , elementBuffer.type[1]);
        Assert.assertEquals(ElementTypes.JSON_PROPERTY_VALUE_STRING, elementBuffer.type[2]);
        Assert.assertEquals(ElementTypes.JSON_PROPERTY_NAME , elementBuffer.type[3]);
        Assert.assertEquals(ElementTypes.JSON_PROPERTY_VALUE_STRING, elementBuffer.type[4]);
        Assert.assertEquals(ElementTypes.JSON_OBJECT_END    , elementBuffer.type[5]);
    }

    @Test
    public void testArrays() {
        System.out.println("JsonParser2 Test");

        DataCharBuffer dataBuffer = new DataCharBuffer();
        dataBuffer.data = "{  \"key\" : \"value\", \"key2\" : [ \"value2\", \"value3\" ], \"key3\": \"value4\" }".toCharArray();
        dataBuffer.length = dataBuffer.data.length;

        IndexBuffer elementBuffer = new IndexBuffer(dataBuffer.data.length, true);

        JsonParser2 parser = new JsonParser2();

        parser.parse(dataBuffer, elementBuffer);


        Assert.assertEquals(ElementTypes.JSON_OBJECT_START  , elementBuffer.type[0]);
        Assert.assertEquals(ElementTypes.JSON_PROPERTY_NAME , elementBuffer.type[1]);
        Assert.assertEquals(ElementTypes.JSON_PROPERTY_VALUE_STRING, elementBuffer.type[2]);
        Assert.assertEquals(ElementTypes.JSON_PROPERTY_NAME , elementBuffer.type[3]);
        Assert.assertEquals(ElementTypes.JSON_ARRAY_START   , elementBuffer.type[4]);
        Assert.assertEquals(ElementTypes.JSON_ARRAY_VALUE_STRING , elementBuffer.type[5]);
        Assert.assertEquals(ElementTypes.JSON_ARRAY_VALUE_STRING, elementBuffer.type[6]);
        Assert.assertEquals(ElementTypes.JSON_ARRAY_END     , elementBuffer.type[7]);
        Assert.assertEquals(ElementTypes.JSON_PROPERTY_NAME , elementBuffer.type[8]);
        Assert.assertEquals(ElementTypes.JSON_PROPERTY_VALUE_STRING, elementBuffer.type[9]);

        Assert.assertEquals(ElementTypes.JSON_OBJECT_END    , elementBuffer.type[10]);
    }

    @Test
    public void testArraysWithObjectsWithArraysWithObjects() {
        DataCharBuffer dataBuffer = new DataCharBuffer();
        dataBuffer.data = "{  \"key\" : \"value\", \"key2\" : [ \"value2\", { \"key21\" : \"value22\", \"key22\" : [ \"value221\", \"value222\"]} ], \"key3\": \"value4\" }".toCharArray();
        dataBuffer.length = dataBuffer.data.length;

        IndexBuffer elementBuffer = new IndexBuffer(dataBuffer.data.length, true);

        JsonParser2 parser = new JsonParser2();

        parser.parse(dataBuffer, elementBuffer);


        Assert.assertEquals(ElementTypes.JSON_OBJECT_START   , elementBuffer.type[ 0]);
        Assert.assertEquals(ElementTypes.JSON_PROPERTY_NAME  , elementBuffer.type[ 1]);
        Assert.assertEquals(ElementTypes.JSON_PROPERTY_VALUE_STRING , elementBuffer.type[ 2]);
        Assert.assertEquals(ElementTypes.JSON_PROPERTY_NAME  , elementBuffer.type[ 3]);
        Assert.assertEquals(ElementTypes.JSON_ARRAY_START    , elementBuffer.type[ 4]);
        Assert.assertEquals(ElementTypes.JSON_ARRAY_VALUE_STRING, elementBuffer.type[ 5]);
        Assert.assertEquals(ElementTypes.JSON_OBJECT_START   , elementBuffer.type[ 6]);
        Assert.assertEquals(ElementTypes.JSON_PROPERTY_NAME  , elementBuffer.type[ 7]);
        Assert.assertEquals(ElementTypes.JSON_PROPERTY_VALUE_STRING , elementBuffer.type[ 8]);
        Assert.assertEquals(ElementTypes.JSON_PROPERTY_NAME  , elementBuffer.type[ 9]);
        Assert.assertEquals(ElementTypes.JSON_ARRAY_START    , elementBuffer.type[10]);
        Assert.assertEquals(ElementTypes.JSON_ARRAY_VALUE_STRING, elementBuffer.type[11]);
        Assert.assertEquals(ElementTypes.JSON_ARRAY_VALUE_STRING, elementBuffer.type[12]);
        Assert.assertEquals(ElementTypes.JSON_ARRAY_END      , elementBuffer.type[13]);
        Assert.assertEquals(ElementTypes.JSON_OBJECT_END     , elementBuffer.type[14]);
        Assert.assertEquals(ElementTypes.JSON_ARRAY_END      , elementBuffer.type[15]);
        Assert.assertEquals(ElementTypes.JSON_PROPERTY_NAME  , elementBuffer.type[16]);
        Assert.assertEquals(ElementTypes.JSON_PROPERTY_VALUE_STRING , elementBuffer.type[17]);

        Assert.assertEquals(ElementTypes.JSON_OBJECT_END     , elementBuffer.type[18]);

    }


    @Test
    public void testNumbers () {
        DataCharBuffer dataBuffer = new DataCharBuffer();
        dataBuffer.data = "{  \"key\" : 123.345, \"key2\" : [ \"value2\", 0.987 ] }".toCharArray();
        dataBuffer.length = dataBuffer.data.length;

        IndexBuffer elementBuffer = new IndexBuffer(dataBuffer.data.length, true);

        JsonParser2 parser = new JsonParser2();

        parser.parse(dataBuffer, elementBuffer);


        Assert.assertEquals(ElementTypes.JSON_OBJECT_START            , elementBuffer.type[ 0]);
        Assert.assertEquals(0, elementBuffer.position[0]);

        Assert.assertEquals(ElementTypes.JSON_PROPERTY_NAME           , elementBuffer.type[ 1]);
        Assert.assertEquals(4, elementBuffer.position[1]);

        Assert.assertEquals(ElementTypes.JSON_PROPERTY_VALUE_NUMBER   , elementBuffer.type[ 2]);
        Assert.assertEquals(11, elementBuffer.position[2]);

        Assert.assertEquals(ElementTypes.JSON_PROPERTY_NAME           , elementBuffer.type[ 3]);
        Assert.assertEquals(21, elementBuffer.position[3]);

        Assert.assertEquals(ElementTypes.JSON_ARRAY_START             , elementBuffer.type[ 4]);
        Assert.assertEquals(29, elementBuffer.position[4]);

        Assert.assertEquals(ElementTypes.JSON_ARRAY_VALUE_STRING      , elementBuffer.type[ 5]);
        Assert.assertEquals(32, elementBuffer.position[5]);

        Assert.assertEquals(ElementTypes.JSON_ARRAY_VALUE_NUMBER      , elementBuffer.type[ 6]);
        Assert.assertEquals(41, elementBuffer.position[6]);

        Assert.assertEquals(ElementTypes.JSON_ARRAY_END               , elementBuffer.type[ 7]);
        Assert.assertEquals(47, elementBuffer.position[7]);

        Assert.assertEquals(ElementTypes.JSON_OBJECT_END              , elementBuffer.type[ 8]);
        Assert.assertEquals(49, elementBuffer.position[8]);

    }


    @Test
    public void testStringsEncoded() {
        DataCharBuffer dataBuffer = new DataCharBuffer();
        dataBuffer.data = "{  \"key\" : \" \\\" \\t \\n \\r \" }".toCharArray();
        dataBuffer.length = dataBuffer.data.length;

        IndexBuffer elementBuffer = new IndexBuffer(dataBuffer.data.length, true);

        JsonParser2 parser = new JsonParser2();

        parser.parse(dataBuffer, elementBuffer);

        Assert.assertEquals(ElementTypes.JSON_OBJECT_START              , elementBuffer.type[0]);
        Assert.assertEquals(ElementTypes.JSON_PROPERTY_NAME             , elementBuffer.type[1]);
        Assert.assertEquals(ElementTypes.JSON_PROPERTY_VALUE_STRING_ENC , elementBuffer.type[2]);
        Assert.assertEquals(ElementTypes.JSON_OBJECT_END                , elementBuffer.type[3]);

    }

    @Test
    public void testBooleans() {
        DataCharBuffer dataBuffer = new DataCharBuffer();
        dataBuffer.data = "{  \"key\" : true, \"key2\" : [ \"value2\", 0.987, false, true ], \"key3\" : false }".toCharArray();
        dataBuffer.length = dataBuffer.data.length;

        IndexBuffer elementBuffer = new IndexBuffer(dataBuffer.data.length, true);

        JsonParser2 parser = new JsonParser2();

        parser.parse(dataBuffer, elementBuffer);

        Assert.assertEquals(ElementTypes.JSON_OBJECT_START            , elementBuffer.type[ 0]);
        Assert.assertEquals(ElementTypes.JSON_PROPERTY_NAME           , elementBuffer.type[ 1]);
        Assert.assertEquals(ElementTypes.JSON_PROPERTY_VALUE_BOOLEAN  , elementBuffer.type[ 2]);
        Assert.assertEquals(ElementTypes.JSON_PROPERTY_NAME           , elementBuffer.type[ 3]);
        Assert.assertEquals(ElementTypes.JSON_ARRAY_START             , elementBuffer.type[ 4]);
        Assert.assertEquals(ElementTypes.JSON_ARRAY_VALUE_STRING      , elementBuffer.type[ 5]);
        Assert.assertEquals(ElementTypes.JSON_ARRAY_VALUE_NUMBER      , elementBuffer.type[ 6]);
        Assert.assertEquals(ElementTypes.JSON_ARRAY_VALUE_BOOLEAN     , elementBuffer.type[ 7]);
        Assert.assertEquals(ElementTypes.JSON_ARRAY_VALUE_BOOLEAN     , elementBuffer.type[ 8]);
        Assert.assertEquals(ElementTypes.JSON_ARRAY_END               , elementBuffer.type[ 9]);
        Assert.assertEquals(ElementTypes.JSON_PROPERTY_NAME           , elementBuffer.type[10]);
        Assert.assertEquals(ElementTypes.JSON_PROPERTY_VALUE_BOOLEAN  , elementBuffer.type[11]);
        Assert.assertEquals(ElementTypes.JSON_OBJECT_END              , elementBuffer.type[12]);

    }

    @Test
    public void testNull() {
        DataCharBuffer dataBuffer = new DataCharBuffer();
        dataBuffer.data = "{  \"key\" : null, \"key2\" : [ \"value2\", 0.987, false, true, null ], \"key3\" : false }".toCharArray();
        dataBuffer.length = dataBuffer.data.length;

        IndexBuffer elementBuffer = new IndexBuffer(dataBuffer.data.length, true);

        JsonParser2 parser = new JsonParser2();

        parser.parse(dataBuffer, elementBuffer);

        Assert.assertEquals(ElementTypes.JSON_OBJECT_START            , elementBuffer.type[ 0]);
        Assert.assertEquals(ElementTypes.JSON_PROPERTY_NAME           , elementBuffer.type[ 1]);
        Assert.assertEquals(ElementTypes.JSON_PROPERTY_VALUE_NULL     , elementBuffer.type[ 2]);
        Assert.assertEquals(ElementTypes.JSON_PROPERTY_NAME           , elementBuffer.type[ 3]);
        Assert.assertEquals(ElementTypes.JSON_ARRAY_START             , elementBuffer.type[ 4]);
        Assert.assertEquals(ElementTypes.JSON_ARRAY_VALUE_STRING      , elementBuffer.type[ 5]);
        Assert.assertEquals(ElementTypes.JSON_ARRAY_VALUE_NUMBER      , elementBuffer.type[ 6]);
        Assert.assertEquals(ElementTypes.JSON_ARRAY_VALUE_BOOLEAN     , elementBuffer.type[ 7]);
        Assert.assertEquals(ElementTypes.JSON_ARRAY_VALUE_BOOLEAN     , elementBuffer.type[ 8]);
        Assert.assertEquals(ElementTypes.JSON_ARRAY_VALUE_NULL        , elementBuffer.type[ 9]);
        Assert.assertEquals(ElementTypes.JSON_ARRAY_END               , elementBuffer.type[10]);
        Assert.assertEquals(ElementTypes.JSON_PROPERTY_NAME           , elementBuffer.type[11]);
        Assert.assertEquals(ElementTypes.JSON_PROPERTY_VALUE_BOOLEAN  , elementBuffer.type[12]);
        Assert.assertEquals(ElementTypes.JSON_OBJECT_END              , elementBuffer.type[13]);

    }


    @Test
    public void testMediumFile() {
        DataCharBuffer dataBuffer = new DataCharBuffer();
        dataBuffer.data = TestFileAssertUtil.mediumFile();
        dataBuffer.length = dataBuffer.data.length;

        IndexBuffer elementBuffer = new IndexBuffer(dataBuffer.data.length, true);

        JsonParser2 parser = new JsonParser2();

        parser.parse(dataBuffer, elementBuffer);

        TestFileAssertUtil.assertsMediumFile(elementBuffer);
    }


}
