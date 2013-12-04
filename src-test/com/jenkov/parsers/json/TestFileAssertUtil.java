package com.jenkov.parsers.json;

import com.jenkov.parsers.core.IndexBuffer;
import junit.framework.Assert;

/**
 */
public class TestFileAssertUtil {


    public static char[] mediumFile() {

        String file =
                "{ \"key\"   : \"value\",\n" +
                        "  \"key2\"  : 12345,\n" +
                        "  \"key3\"  : false,\n" +
                        "\n" +
                        "  \"stringArray\" : [ \"one\", \"two\", \"three\", \"four\", \"five\", \"six\", \"seven\", \"eight\", \"nine\", \"ten\"],\n" +
                        "  \"numberArray\" : [ 12345, 12345, 12345, 12345, 12345, 12345, 12345, 12345, 12345, 12345],\n" +
                        "  \"booleanArray\" : [ true, false, true, false, true, false, true, false, true, false],\n" +
                        "\n" +
                        "  \"sub\"  : {\n" +
                        "             \"key\"  : \"value\",\n" +
                        "             \"key2\" : 12345,\n" +
                        "             \"key3\" : false,\n" +
                        "             \"stringArray\" : [ \"one\", \"two\", \"three\", \"four\", \"five\", \"six\", \"seven\", \"eight\", \"nine\", \"ten\"],\n" +
                        "             \"numberArray\" : [ 12345, 12345, 12345, 12345, 12345, 12345, 12345, 12345, 12345, 12345],\n" +
                        "             \"booleanArray\" : [ true, false, true, false, true, false, true, false, true, false]\n" +
                        "           }\n" +
                        "}";

        return file.toCharArray();
    }

    public static void assertsMediumFile(IndexBuffer elementBuffer) {
        Assert.assertEquals(ElementTypes.JSON_OBJECT_START, elementBuffer.type[0]);
        Assert.assertEquals(ElementTypes.JSON_PROPERTY_NAME           , elementBuffer.type[ 1]);
        Assert.assertEquals(ElementTypes.JSON_PROPERTY_VALUE_STRING   , elementBuffer.type[ 2]);

        Assert.assertEquals(ElementTypes.JSON_PROPERTY_NAME           , elementBuffer.type[ 3]);
        Assert.assertEquals(ElementTypes.JSON_PROPERTY_VALUE_NUMBER   , elementBuffer.type[ 4]);

        Assert.assertEquals(ElementTypes.JSON_PROPERTY_NAME           , elementBuffer.type[ 5]);
        Assert.assertEquals(ElementTypes.JSON_PROPERTY_VALUE_BOOLEAN  , elementBuffer.type[ 6]);

        Assert.assertEquals(ElementTypes.JSON_PROPERTY_NAME           , elementBuffer.type[ 7]);
        Assert.assertEquals(ElementTypes.JSON_ARRAY_START             , elementBuffer.type[ 8]);
        Assert.assertEquals(ElementTypes.JSON_ARRAY_VALUE_STRING      , elementBuffer.type[ 9]);
        Assert.assertEquals(ElementTypes.JSON_ARRAY_VALUE_STRING      , elementBuffer.type[ 10]);
        Assert.assertEquals(ElementTypes.JSON_ARRAY_VALUE_STRING      , elementBuffer.type[ 11]);
        Assert.assertEquals(ElementTypes.JSON_ARRAY_VALUE_STRING      , elementBuffer.type[ 12]);
        Assert.assertEquals(ElementTypes.JSON_ARRAY_VALUE_STRING      , elementBuffer.type[ 13]);
        Assert.assertEquals(ElementTypes.JSON_ARRAY_VALUE_STRING      , elementBuffer.type[ 14]);
        Assert.assertEquals(ElementTypes.JSON_ARRAY_VALUE_STRING      , elementBuffer.type[ 15]);
        Assert.assertEquals(ElementTypes.JSON_ARRAY_VALUE_STRING      , elementBuffer.type[ 16]);
        Assert.assertEquals(ElementTypes.JSON_ARRAY_VALUE_STRING      , elementBuffer.type[ 17]);
        Assert.assertEquals(ElementTypes.JSON_ARRAY_VALUE_STRING      , elementBuffer.type[ 18]);
        Assert.assertEquals(ElementTypes.JSON_ARRAY_END               , elementBuffer.type[ 19]);

        Assert.assertEquals(ElementTypes.JSON_PROPERTY_NAME           , elementBuffer.type[ 20]);
        Assert.assertEquals(ElementTypes.JSON_ARRAY_START             , elementBuffer.type[ 21]);
        Assert.assertEquals(ElementTypes.JSON_ARRAY_VALUE_NUMBER      , elementBuffer.type[ 22]);
        Assert.assertEquals(ElementTypes.JSON_ARRAY_VALUE_NUMBER      , elementBuffer.type[ 23]);
        Assert.assertEquals(ElementTypes.JSON_ARRAY_VALUE_NUMBER      , elementBuffer.type[ 24]);
        Assert.assertEquals(ElementTypes.JSON_ARRAY_VALUE_NUMBER      , elementBuffer.type[ 25]);
        Assert.assertEquals(ElementTypes.JSON_ARRAY_VALUE_NUMBER      , elementBuffer.type[ 26]);
        Assert.assertEquals(ElementTypes.JSON_ARRAY_VALUE_NUMBER      , elementBuffer.type[ 27]);
        Assert.assertEquals(ElementTypes.JSON_ARRAY_VALUE_NUMBER      , elementBuffer.type[ 28]);
        Assert.assertEquals(ElementTypes.JSON_ARRAY_VALUE_NUMBER      , elementBuffer.type[ 29]);
        Assert.assertEquals(ElementTypes.JSON_ARRAY_VALUE_NUMBER      , elementBuffer.type[ 30]);
        Assert.assertEquals(ElementTypes.JSON_ARRAY_VALUE_NUMBER      , elementBuffer.type[ 31]);
        Assert.assertEquals(ElementTypes.JSON_ARRAY_END               , elementBuffer.type[ 32]);

        Assert.assertEquals(ElementTypes.JSON_PROPERTY_NAME           , elementBuffer.type[ 33]);
        Assert.assertEquals(ElementTypes.JSON_ARRAY_START             , elementBuffer.type[ 34]);
        Assert.assertEquals(ElementTypes.JSON_ARRAY_VALUE_BOOLEAN     , elementBuffer.type[ 35]);
        Assert.assertEquals(ElementTypes.JSON_ARRAY_VALUE_BOOLEAN     , elementBuffer.type[ 36]);
        Assert.assertEquals(ElementTypes.JSON_ARRAY_VALUE_BOOLEAN     , elementBuffer.type[ 37]);
        Assert.assertEquals(ElementTypes.JSON_ARRAY_VALUE_BOOLEAN     , elementBuffer.type[ 38]);
        Assert.assertEquals(ElementTypes.JSON_ARRAY_VALUE_BOOLEAN     , elementBuffer.type[ 39]);
        Assert.assertEquals(ElementTypes.JSON_ARRAY_VALUE_BOOLEAN     , elementBuffer.type[ 40]);
        Assert.assertEquals(ElementTypes.JSON_ARRAY_VALUE_BOOLEAN     , elementBuffer.type[ 41]);
        Assert.assertEquals(ElementTypes.JSON_ARRAY_VALUE_BOOLEAN     , elementBuffer.type[ 42]);
        Assert.assertEquals(ElementTypes.JSON_ARRAY_VALUE_BOOLEAN     , elementBuffer.type[ 43]);
        Assert.assertEquals(ElementTypes.JSON_ARRAY_VALUE_BOOLEAN     , elementBuffer.type[ 44]);
        Assert.assertEquals(ElementTypes.JSON_ARRAY_END               , elementBuffer.type[ 45]);

        Assert.assertEquals(ElementTypes.JSON_PROPERTY_NAME           , elementBuffer.type[ 46]);
        Assert.assertEquals(ElementTypes.JSON_OBJECT_START            , elementBuffer.type[ 47]);

        Assert.assertEquals(ElementTypes.JSON_PROPERTY_NAME           , elementBuffer.type[ 48]);
        Assert.assertEquals(ElementTypes.JSON_PROPERTY_VALUE_STRING   , elementBuffer.type[ 49]);

        Assert.assertEquals(ElementTypes.JSON_PROPERTY_NAME           , elementBuffer.type[ 50]);
        Assert.assertEquals(ElementTypes.JSON_PROPERTY_VALUE_NUMBER   , elementBuffer.type[ 51]);

        Assert.assertEquals(ElementTypes.JSON_PROPERTY_NAME           , elementBuffer.type[ 52]);
        Assert.assertEquals(ElementTypes.JSON_PROPERTY_VALUE_BOOLEAN  , elementBuffer.type[ 53]);

        Assert.assertEquals(ElementTypes.JSON_PROPERTY_NAME           , elementBuffer.type[ 54]);
        Assert.assertEquals(ElementTypes.JSON_ARRAY_START             , elementBuffer.type[ 55]);
        Assert.assertEquals(ElementTypes.JSON_ARRAY_VALUE_STRING      , elementBuffer.type[ 56]);
        Assert.assertEquals(ElementTypes.JSON_ARRAY_VALUE_STRING      , elementBuffer.type[ 57]);
        Assert.assertEquals(ElementTypes.JSON_ARRAY_VALUE_STRING      , elementBuffer.type[ 58]);
        Assert.assertEquals(ElementTypes.JSON_ARRAY_VALUE_STRING      , elementBuffer.type[ 59]);
        Assert.assertEquals(ElementTypes.JSON_ARRAY_VALUE_STRING      , elementBuffer.type[ 60]);
        Assert.assertEquals(ElementTypes.JSON_ARRAY_VALUE_STRING      , elementBuffer.type[ 61]);
        Assert.assertEquals(ElementTypes.JSON_ARRAY_VALUE_STRING      , elementBuffer.type[ 62]);
        Assert.assertEquals(ElementTypes.JSON_ARRAY_VALUE_STRING      , elementBuffer.type[ 63]);
        Assert.assertEquals(ElementTypes.JSON_ARRAY_VALUE_STRING      , elementBuffer.type[ 64]);
        Assert.assertEquals(ElementTypes.JSON_ARRAY_VALUE_STRING      , elementBuffer.type[ 65]);
        Assert.assertEquals(ElementTypes.JSON_ARRAY_END               , elementBuffer.type[ 66]);

        Assert.assertEquals(ElementTypes.JSON_PROPERTY_NAME           , elementBuffer.type[ 67]);
        Assert.assertEquals(ElementTypes.JSON_ARRAY_START             , elementBuffer.type[ 68]);
        Assert.assertEquals(ElementTypes.JSON_ARRAY_VALUE_NUMBER      , elementBuffer.type[ 69]);
        Assert.assertEquals(ElementTypes.JSON_ARRAY_VALUE_NUMBER      , elementBuffer.type[ 70]);
        Assert.assertEquals(ElementTypes.JSON_ARRAY_VALUE_NUMBER      , elementBuffer.type[ 71]);
        Assert.assertEquals(ElementTypes.JSON_ARRAY_VALUE_NUMBER      , elementBuffer.type[ 72]);
        Assert.assertEquals(ElementTypes.JSON_ARRAY_VALUE_NUMBER      , elementBuffer.type[ 73]);
        Assert.assertEquals(ElementTypes.JSON_ARRAY_VALUE_NUMBER      , elementBuffer.type[ 74]);
        Assert.assertEquals(ElementTypes.JSON_ARRAY_VALUE_NUMBER      , elementBuffer.type[ 75]);
        Assert.assertEquals(ElementTypes.JSON_ARRAY_VALUE_NUMBER      , elementBuffer.type[ 76]);
        Assert.assertEquals(ElementTypes.JSON_ARRAY_VALUE_NUMBER      , elementBuffer.type[ 77]);
        Assert.assertEquals(ElementTypes.JSON_ARRAY_VALUE_NUMBER      , elementBuffer.type[ 78]);
        Assert.assertEquals(ElementTypes.JSON_ARRAY_END               , elementBuffer.type[ 79]);

        Assert.assertEquals(ElementTypes.JSON_PROPERTY_NAME           , elementBuffer.type[ 80]);
        Assert.assertEquals(ElementTypes.JSON_ARRAY_START             , elementBuffer.type[ 81]);
        Assert.assertEquals(ElementTypes.JSON_ARRAY_VALUE_BOOLEAN     , elementBuffer.type[ 82]);
        Assert.assertEquals(ElementTypes.JSON_ARRAY_VALUE_BOOLEAN     , elementBuffer.type[ 83]);
        Assert.assertEquals(ElementTypes.JSON_ARRAY_VALUE_BOOLEAN     , elementBuffer.type[ 84]);
        Assert.assertEquals(ElementTypes.JSON_ARRAY_VALUE_BOOLEAN     , elementBuffer.type[ 85]);
        Assert.assertEquals(ElementTypes.JSON_ARRAY_VALUE_BOOLEAN     , elementBuffer.type[ 86]);
        Assert.assertEquals(ElementTypes.JSON_ARRAY_VALUE_BOOLEAN     , elementBuffer.type[ 87]);
        Assert.assertEquals(ElementTypes.JSON_ARRAY_VALUE_BOOLEAN     , elementBuffer.type[ 88]);
        Assert.assertEquals(ElementTypes.JSON_ARRAY_VALUE_BOOLEAN     , elementBuffer.type[ 89]);
        Assert.assertEquals(ElementTypes.JSON_ARRAY_VALUE_BOOLEAN     , elementBuffer.type[ 90]);
        Assert.assertEquals(ElementTypes.JSON_ARRAY_VALUE_BOOLEAN     , elementBuffer.type[ 91]);
        Assert.assertEquals(ElementTypes.JSON_ARRAY_END               , elementBuffer.type[ 92]);

        Assert.assertEquals(ElementTypes.JSON_OBJECT_END              , elementBuffer.type[ 93]);

        Assert.assertEquals(ElementTypes.JSON_OBJECT_END              , elementBuffer.type[ 94]);
    }

}
