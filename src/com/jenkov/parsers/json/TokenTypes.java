package com.jenkov.parsers.json;

/**
 */
public class TokenTypes {

    public static final byte JSON_CURLY_BRACKET_LEFT   = 1;   // {
    public static final byte JSON_CURLY_BRACKET_RIGHT  = 2;   // }
    public static final byte JSON_SQUARE_BRACKET_LEFT  = 3;   // [
    public static final byte JSON_SQUARE_BRACKET_RIGHT = 4;   // ]
    public static final byte JSON_STRING_TOKEN         = 5;   //
    public static final byte JSON_STRING_ENC_TOKEN     = 6;   //
    public static final byte JSON_NUMBER_TOKEN         = 7;   //
    public static final byte JSON_BOOLEAN_TOKEN        = 8;   //
    public static final byte JSON_NULL_TOKEN           = 9;   // the , between properties
    public static final byte JSON_COLON                = 10;  // :
    public static final byte JSON_COMMA                = 11; // the , between properties


}
