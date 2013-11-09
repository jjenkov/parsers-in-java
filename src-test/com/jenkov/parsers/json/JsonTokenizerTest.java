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
        dataBuffer.data = "{  key : \"value\" }".toCharArray();
        dataBuffer.length = dataBuffer.data.length;

        JsonTokenizer tokenizer = new JsonTokenizer(dataBuffer, new IndexBuffer(dataBuffer.data.length, true));

        /* { */
        Assert.assertTrue(tokenizer.hasMoreTokens());
        tokenizer.parseToken();

        Assert.assertEquals(0, tokenizer.tokenPosition());
        Assert.assertEquals(1, tokenizer.tokenLength());

        /* key */
        Assert.assertTrue(tokenizer.hasMoreTokens());
        tokenizer.nextToken();
        tokenizer.parseToken();
        Assert.assertEquals(3, tokenizer.tokenPosition());
        Assert.assertEquals(3, tokenizer.tokenLength());

        /* : */
        Assert.assertTrue(tokenizer.hasMoreTokens());
        tokenizer.nextToken();
        tokenizer.parseToken();
        Assert.assertEquals(7, tokenizer.tokenPosition());
        Assert.assertEquals(1, tokenizer.tokenLength());

        /* "value" */
        Assert.assertTrue(tokenizer.hasMoreTokens());
        tokenizer.nextToken();
        tokenizer.parseToken();
        Assert.assertEquals(9, tokenizer.tokenPosition());
        Assert.assertEquals(7, tokenizer.tokenLength());

        /* } */
        Assert.assertTrue(tokenizer.hasMoreTokens());
        tokenizer.nextToken();
        tokenizer.parseToken();
        Assert.assertEquals(17, tokenizer.tokenPosition());
        Assert.assertEquals(1, tokenizer.tokenLength());

        Assert.assertFalse(tokenizer.hasMoreTokens());

    }
}
