package com.jenkov.parsers.json;

import com.jenkov.parsers.core.DataCharBuffer;
import com.jenkov.parsers.core.IndexBuffer;
import org.junit.Test;

import static junit.framework.Assert.*;

/**
 */
public class JsonNavigatorTest {

    @Test
    public void testWithParser() {
        DataCharBuffer dataBuffer = new DataCharBuffer();
        dataBuffer.data = "{  \"key\" : \"value\", \"key2\" : 12345, \"key3\" : true, \"key4\" : false, \"key5\" : 12345.6789, \"key6\" : \" \\\" \\t \\n \\r \" }".toCharArray();
        dataBuffer.length = dataBuffer.data.length;

        IndexBuffer tokenBuffer   = new IndexBuffer(dataBuffer.data.length, true);
        IndexBuffer elementBuffer = new IndexBuffer(dataBuffer.data.length, true);

        JsonParser parser = new JsonParser(tokenBuffer, elementBuffer);

        parser.parse(dataBuffer);
        assertEquals(14, elementBuffer.count);


        assertsOnNavigator(dataBuffer, elementBuffer);

    }

    public void testWithParser2() {
        DataCharBuffer dataBuffer = new DataCharBuffer();
        dataBuffer.data = "{  \"key\" : \"value\", \"key2\" : 12345, \"key3\" : true, \"key4\" : false, \"key5\" : 12345.6789, \"key6\" : \" \\\" \\t \\n \\r \" }".toCharArray();
        dataBuffer.length = dataBuffer.data.length;

        IndexBuffer elementBuffer = new IndexBuffer(dataBuffer.data.length, true);

        JsonParser2 parser = new JsonParser2();

        parser.parse(dataBuffer, elementBuffer);
        assertEquals(14, elementBuffer.count);


        assertsOnNavigator(dataBuffer, elementBuffer);

    }

    private void assertsOnNavigator(DataCharBuffer dataBuffer, IndexBuffer elementBuffer) {
        JsonNavigator navigator = new JsonNavigator(dataBuffer, elementBuffer);

        assertEquals(ElementTypes.JSON_OBJECT_START, navigator.type());
        assertTrue(navigator.hasNext());

        navigator.next();
        assertEquals(ElementTypes.JSON_PROPERTY_NAME, navigator.type());
        assertEquals(3, navigator.length());
        assertEquals("key", navigator.asString());
        assertTrue(navigator.isEqualUnencoded("key"));
        assertFalse(navigator.isEqualUnencoded("kea"));
        assertFalse(navigator.isEqualUnencoded("key2"));
        assertTrue(navigator.hasNext());

        navigator.next();
        assertEquals(ElementTypes.JSON_PROPERTY_VALUE_STRING, navigator.type());
        assertEquals("value", navigator.asString());
        assertTrue(navigator.hasNext());

        navigator.next();
        assertEquals(ElementTypes.JSON_PROPERTY_NAME, navigator.type());
        assertEquals("key2", navigator.asString());
        assertTrue(navigator.hasNext());

        navigator.next();
        assertEquals(ElementTypes.JSON_PROPERTY_VALUE_NUMBER, navigator.type());
        assertEquals(12345, navigator.asInt());
        assertTrue(navigator.hasNext());

        navigator.next();
        assertEquals(ElementTypes.JSON_PROPERTY_NAME, navigator.type());
        assertEquals("key3", navigator.asString());
        assertTrue(navigator.hasNext());

        navigator.next();
        assertEquals(ElementTypes.JSON_PROPERTY_VALUE_BOOLEAN, navigator.type());
        assertEquals(true, navigator.asBoolean());
        assertTrue(navigator.hasNext());

        navigator.next();
        assertEquals(ElementTypes.JSON_PROPERTY_NAME, navigator.type());
        assertEquals("key4", navigator.asString());
        assertTrue(navigator.hasNext());

        navigator.next();
        assertEquals(ElementTypes.JSON_PROPERTY_VALUE_BOOLEAN, navigator.type());
        assertEquals(false, navigator.asBoolean());
        assertTrue(navigator.hasNext());

        navigator.next();
        assertEquals(ElementTypes.JSON_PROPERTY_NAME, navigator.type());
        assertEquals("key5", navigator.asString());
        assertTrue(navigator.hasNext());

        navigator.next();
        assertEquals(ElementTypes.JSON_PROPERTY_VALUE_NUMBER, navigator.type());
        assertEquals((double) 12345.6789, navigator.asDouble());
        assertTrue(navigator.hasNext());

        navigator.next();
        assertEquals(ElementTypes.JSON_PROPERTY_NAME, navigator.type());
        assertEquals("key6", navigator.asString());
        assertTrue(navigator.hasNext());

        navigator.next();
        assertEquals(ElementTypes.JSON_PROPERTY_VALUE_STRING_ENC, navigator.type());
        assertEquals(" \" \t \n \r ", navigator.asString());
        assertTrue(navigator.hasNext());

        navigator.next();
        assertEquals(ElementTypes.JSON_OBJECT_END, navigator.type());
        assertFalse(navigator.hasNext());
    }




}
