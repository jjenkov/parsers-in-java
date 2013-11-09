package com.jenkov.parsers.json;

import com.jenkov.parsers.core.DataCharBuffer;
import com.jenkov.parsers.core.IndexBuffer;


/**
 */
public class JsonTokenizer {

    private DataCharBuffer dataBuffer  = null;
    private IndexBuffer    tokenBuffer = null;

    private int tokenIndex   = 0;
    private int dataPosition = 0;
    private int tokenLength  = 0;

    public JsonTokenizer() {
    }

    public JsonTokenizer(DataCharBuffer dataBuffer, IndexBuffer tokenBuffer) {
        this.dataBuffer  = dataBuffer;
        this.tokenBuffer = tokenBuffer;
    }

    public void reinit(DataCharBuffer dataBuffer, IndexBuffer tokenBuffer) {
        this.dataBuffer  = dataBuffer;
        this.tokenBuffer = tokenBuffer;
        this.tokenIndex  = 0;
        this.dataPosition= 0;
        this.tokenLength = 0;
    }

    public boolean hasMoreTokens() {
        return (this.dataPosition + this.tokenLength) < this.dataBuffer.length ;
    }


    public void parseToken() {
        skipWhiteSpace();
        this.tokenLength = 0;

        char nextChar = this.dataBuffer.data[this.dataPosition];
        this.tokenBuffer.position[this.tokenIndex] = this.dataPosition;

        switch(nextChar) {
            case '{'   :  { this.tokenLength = 1; this.tokenBuffer.type[this.tokenIndex] = TokenTypes.JSON_OBJECT_START; } break;
            case '}'   :  { this.tokenLength = 1; this.tokenBuffer.type[this.tokenIndex] = TokenTypes.JSON_OBJECT_END  ; } break;
            case '['   :  { this.tokenLength = 1; this.tokenBuffer.type[this.tokenIndex] = TokenTypes.JSON_ARRAY_START ; } break;
            case ']'   :  { this.tokenLength = 1; this.tokenBuffer.type[this.tokenIndex] = TokenTypes.JSON_ARRAY_END   ; } break;
            case ','   :  { this.tokenLength = 1; this.tokenBuffer.type[this.tokenIndex] = TokenTypes.JSON_PROPERTY_SEPARATOR; } break;
            case ':'   :  { this.tokenLength = 1; this.tokenBuffer.type[this.tokenIndex] = TokenTypes.JSON_PROPERTY_NAME_VALUE_SEPARATOR; } break;

            case '"'   :  { parsePropertyValue(); this.tokenBuffer.type[this.tokenIndex] = TokenTypes.JSON_STRING_TOKEN; } break;

            default    : { parsePropertyName();   this.tokenBuffer.type[this.tokenIndex] = TokenTypes.JSON_STRING_TOKEN; }
        }

        this.tokenBuffer.length[this.tokenIndex] = this.tokenLength;
    }

    private void parsePropertyValue() {
        this.tokenLength = 0;

        boolean isEndOfValueFound = false;
        while(!isEndOfValueFound){
            switch(this.dataBuffer.data[this.dataPosition + this.tokenLength + 1]) {
                case  '"' : { isEndOfValueFound = this.dataBuffer.data[this.dataPosition + this.tokenLength] != '\\'; } break;

                default   : { this.tokenLength++ ; }
            }

        }
        this.tokenLength += 2;  //include both start " and end " in the token.

    }

    private void parsePropertyName() {
        boolean isPropertyNameChar = true;
        this.tokenLength = 0;
        while(isPropertyNameChar) {
            switch(this.dataBuffer.data[this.dataPosition + this.tokenLength]) {
                case '{'    :  ;  /* falling through is okay for all these cases - they are treated equally */
                case '}'    :  ;
                case '['    :  ;
                case ']'    :  ;
                case ','    :  ;
                case ':'    :  ;
                case ' '    :  ;
                case '\r'   :  ;
                case '\n'   :  ;
                case '\t'   :  ;
                case '"'    :  { isPropertyNameChar = false; } break;

                default     :  { this.tokenLength++; }  /* it's a property name char - inc.token length */
            }
        }
    }

    private void skipWhiteSpace() {
        boolean isWhiteSpace = true;
        while(isWhiteSpace) {
            switch(this.dataBuffer.data[this.dataPosition]) {
                case ' '    :  ;  /* falling through - all white space characters are treated the same*/
                case '\r'   :  ;
                case '\n'   :  ;
                case '\t'   :  { this.dataPosition++; } break;

                default     :  { isWhiteSpace = false; }  /* any non white space char will break the while loop */
            }
        }

    }

    public void nextToken() {
        this.dataPosition += this.tokenBuffer.length[this.tokenIndex]; //move data position to end of current token.
        this.tokenIndex++;  //point to next token index array cell.
    }

    public int tokenPosition() {
        return this.tokenBuffer.position[this.tokenIndex];
    }

    public int tokenLength() {
        return this.tokenBuffer.length[this.tokenIndex];
    }

    public byte tokenType() {
        return this.tokenBuffer.type[this.tokenIndex];
    }

}
