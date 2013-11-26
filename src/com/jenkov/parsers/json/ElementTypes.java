package com.jenkov.parsers.json;

/**
 */
public class ElementTypes {

    public static final byte JSON_OBJECT_START   = 1;   // {
    public static final byte JSON_OBJECT_END     = 2;   // }
    public static final byte JSON_ARRAY_START    = 3;   // [
    public static final byte JSON_ARRAY_VALUE_STRING = 4;   // [
    public static final byte JSON_ARRAY_VALUE_NUMBER = 5;   // [
    public static final byte JSON_ARRAY_VALUE_BOOLEAN = 6;   // [
    public static final byte JSON_ARRAY_END      = 7;   // ]
    public static final byte JSON_PROPERTY_NAME  = 8;   //
    public static final byte JSON_PROPERTY_VALUE_STRING = 9;     //
    public static final byte JSON_PROPERTY_VALUE_NUMBER = 10;    //
    public static final byte JSON_PROPERTY_VALUE_BOOLEAN = 11;   //

}
