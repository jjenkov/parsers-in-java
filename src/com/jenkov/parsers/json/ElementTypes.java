package com.jenkov.parsers.json;

/**
 */
public class ElementTypes {

    /*
      Is it possible to create a reusable list of token types in the core package? One that
      does not use data specific names like JSON_OBJECT_START, but perhaps uses names like
      CURLY_BRACE_LEFT ?
     */

    public static final byte JSON_OBJECT_START   = 1;   // {
    public static final byte JSON_OBJECT_END     = 2;   // }
    public static final byte JSON_ARRAY_START    = 3;   // [
    public static final byte JSON_ARRAY_VALUE    = 4;   // [
    public static final byte JSON_ARRAY_END      = 5;   // ]
    public static final byte JSON_PROPERTY_NAME  = 6;   //
    public static final byte JSON_PROPERTY_VALUE = 7;   //

}
