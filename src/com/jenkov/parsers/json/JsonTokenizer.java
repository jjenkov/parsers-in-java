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

        this.tokenBuffer.position[this.tokenIndex] = this.dataPosition;
        char nextChar = this.dataBuffer.data[this.dataPosition];

        switch(nextChar) {
            case '{'   :  { this.tokenLength = 1; this.tokenBuffer.type[this.tokenIndex] = TokenTypes.JSON_CURLY_BRACKET_LEFT; } break;
            case '}'   :  { this.tokenLength = 1; this.tokenBuffer.type[this.tokenIndex] = TokenTypes.JSON_CURLY_BRACKET_RIGHT; } break;
            case '['   :  { this.tokenLength = 1; this.tokenBuffer.type[this.tokenIndex] = TokenTypes.JSON_SQUARE_BRACKET_LEFT ; } break;
            case ']'   :  { this.tokenLength = 1; this.tokenBuffer.type[this.tokenIndex] = TokenTypes.JSON_SQUARE_BRACKET_RIGHT; } break;
            case ','   :  { this.tokenLength = 1; this.tokenBuffer.type[this.tokenIndex] = TokenTypes.JSON_COMMA; } break;
            case ':'   :  { this.tokenLength = 1; this.tokenBuffer.type[this.tokenIndex] = TokenTypes.JSON_COLON; } break;

            case '"'   :  { parseStringToken(); this.tokenBuffer.type[this.tokenIndex] = TokenTypes.JSON_STRING_TOKEN; } break;
            case '0'   :  ;
            case '1'   :  ;
            case '2'   :  ;
            case '3'   :  ;
            case '4'   :  ;
            case '5'   :  ;
            case '6'   :  ;
            case '7'   :  ;
            case '8'   :  ;
            case '9'   :  { parseNumberToken(); this.tokenBuffer.type[this.tokenIndex] = TokenTypes.JSON_NUMBER_TOKEN; } break;

            case 'f'   :  { if(parseFalse()) { this.tokenBuffer.type[this.tokenIndex] = TokenTypes.JSON_BOOLEAN_TOKEN;} } break;
            case 't'   :  { if(parseTrue())  { this.tokenBuffer.type[this.tokenIndex] = TokenTypes.JSON_BOOLEAN_TOKEN;} } break;

            default    :  { parseStringToken(); this.tokenBuffer.type[this.tokenIndex] = TokenTypes.JSON_STRING_TOKEN; }
        }

        this.tokenBuffer.length[this.tokenIndex] = this.tokenLength;
    }

    private boolean parseTrue() {
        if(
            this.dataBuffer.data[this.dataPosition + 1] == 'r' &&
            this.dataBuffer.data[this.dataPosition + 2] == 'u' &&
            this.dataBuffer.data[this.dataPosition + 3] == 'e' )
        {
            this.tokenLength = 4;
            return true;
        }
        return false;
    }

    private boolean parseFalse() {
        if(
            this.dataBuffer.data[this.dataPosition + 1] == 'a' &&
            this.dataBuffer.data[this.dataPosition + 2] == 'l' &&
            this.dataBuffer.data[this.dataPosition + 3] == 's' &&
            this.dataBuffer.data[this.dataPosition + 4] == 'e'
                )  {
            this.tokenLength = 5;
            return true;
        }
        return false;
    }

    private void parseNumberToken() {
        this.tokenLength = 1;

        boolean isEndOfNumberFound = false;
        while(!isEndOfNumberFound) {
            switch(this.dataBuffer.data[this.dataPosition + this.tokenLength]){
                case '0'   :  ;
                case '1'   :  ;
                case '2'   :  ;
                case '3'   :  ;
                case '4'   :  ;
                case '5'   :  ;
                case '6'   :  ;
                case '7'   :  ;
                case '8'   :  ;
                case '9'   :  ;
                case '.'   :  { this.tokenLength++; } break;

                default    :  { isEndOfNumberFound = true; }
            }
        }
    }

    private void parseStringToken() {
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
