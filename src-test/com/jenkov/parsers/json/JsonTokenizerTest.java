package com.jenkov.parsers.json;

import com.jenkov.parsers.core.DataCharBuffer;
import com.jenkov.parsers.core.IndexBuffer;
import junit.framework.Assert;
import org.junit.Test;

/**
 */
public class JsonTokenizerTest {

    @Test
    public void test () {
        DataCharBuffer dataBuffer = new DataCharBuffer();
        dataBuffer.data = "{  \"key\" : \"value\" }".toCharArray();
        dataBuffer.length = dataBuffer.data.length;

        JsonTokenizer tokenizer = new JsonTokenizer(dataBuffer, new IndexBuffer(dataBuffer.data.length, true));

        /* { */
        Assert.assertTrue(tokenizer.hasMoreTokens());
        tokenizer.parseToken();
        Assert.assertEquals(0, tokenizer.tokenPosition());
        Assert.assertEquals(TokenTypes.JSON_CURLY_BRACKET_LEFT, tokenizer.tokenType());

        /* key */
        Assert.assertTrue(tokenizer.hasMoreTokens());
        tokenizer.nextToken();
        tokenizer.parseToken();
        Assert.assertEquals(4, tokenizer.tokenPosition());
        Assert.assertEquals(3, tokenizer.tokenLength());

        /* : */
        Assert.assertTrue(tokenizer.hasMoreTokens());
        tokenizer.nextToken();
        tokenizer.parseToken();
        Assert.assertEquals(9, tokenizer.tokenPosition());
        Assert.assertEquals(TokenTypes.JSON_COLON, tokenizer.tokenType());

        /* "value" */
        Assert.assertTrue(tokenizer.hasMoreTokens());
        tokenizer.nextToken();
        tokenizer.parseToken();
        Assert.assertEquals(12, tokenizer.tokenPosition());
        Assert.assertEquals(5, tokenizer.tokenLength());

        /* } */
        Assert.assertTrue(tokenizer.hasMoreTokens());
        tokenizer.nextToken();
        tokenizer.parseToken();
        Assert.assertEquals(19, tokenizer.tokenPosition());
        Assert.assertEquals(TokenTypes.JSON_CURLY_BRACKET_RIGHT, tokenizer.tokenType());

        Assert.assertFalse(tokenizer.hasMoreTokens());

    }



    @Test
    public void testNumbers() {
        DataCharBuffer dataBuffer = new DataCharBuffer();
        dataBuffer.data = "{  \"key\" : 0.123, \"key2\" : 1234567890.0123456789 }".toCharArray();
        dataBuffer.length = dataBuffer.data.length;

        JsonTokenizer tokenizer = new JsonTokenizer(dataBuffer, new IndexBuffer(dataBuffer.data.length, true));

        Assert.assertTrue(tokenizer.hasMoreTokens());
        tokenizer.parseToken();

        Assert.assertEquals(0, tokenizer.tokenPosition());
        Assert.assertEquals(TokenTypes.JSON_CURLY_BRACKET_LEFT, tokenizer.tokenType());

        tokenizer.nextToken();
        tokenizer.parseToken();
        Assert.assertEquals(4, tokenizer.tokenPosition());
        Assert.assertEquals(3, tokenizer.tokenLength());
        Assert.assertEquals(TokenTypes.JSON_STRING_TOKEN, tokenizer.tokenType());

        tokenizer.nextToken();
        tokenizer.parseToken();
        Assert.assertEquals(9, tokenizer.tokenPosition());
        Assert.assertEquals(TokenTypes.JSON_COLON, tokenizer.tokenType());

        tokenizer.nextToken();
        tokenizer.parseToken();
        Assert.assertEquals(11, tokenizer.tokenPosition());
        Assert.assertEquals(5, tokenizer.tokenLength());
        Assert.assertEquals(TokenTypes.JSON_NUMBER_TOKEN, tokenizer.tokenType());

        tokenizer.nextToken();
        tokenizer.parseToken();
        Assert.assertEquals(16, tokenizer.tokenPosition());
        Assert.assertEquals(TokenTypes.JSON_COMMA, tokenizer.tokenType());

        tokenizer.nextToken();
        tokenizer.parseToken();
        Assert.assertEquals(19, tokenizer.tokenPosition());
        Assert.assertEquals(4, tokenizer.tokenLength());
        Assert.assertEquals(TokenTypes.JSON_STRING_TOKEN, tokenizer.tokenType());

        tokenizer.nextToken();
        tokenizer.parseToken();
        Assert.assertEquals(25, tokenizer.tokenPosition());
        Assert.assertEquals(TokenTypes.JSON_COLON, tokenizer.tokenType());

        tokenizer.nextToken();
        tokenizer.parseToken();
        Assert.assertEquals(27, tokenizer.tokenPosition());
        Assert.assertEquals(21, tokenizer.tokenLength());
        Assert.assertEquals(TokenTypes.JSON_NUMBER_TOKEN, tokenizer.tokenType());

        tokenizer.nextToken();
        tokenizer.parseToken();
        Assert.assertEquals(49, tokenizer.tokenPosition());
        Assert.assertEquals(TokenTypes.JSON_CURLY_BRACKET_RIGHT, tokenizer.tokenType());
    }


}
