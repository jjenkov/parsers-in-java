package com.jenkov.parsers.json;

import com.jenkov.parsers.core.DataCharBuffer;
import com.jenkov.parsers.core.IndexBuffer;

/**
 * This class helps users navigate the parsed output from the JsonParser and JsonParser2
 */
public class JsonNavigator {

    private DataCharBuffer buffer     = null;
    private IndexBuffer elementBuffer = null;
    private int         elementIndex  = 0;

    public JsonNavigator(DataCharBuffer buffer, IndexBuffer elementBuffer) {
        this.buffer = buffer;
        this.elementBuffer = elementBuffer;
    }

    /* IndexBuffer (elementBuffer) navigation support methods */

    public boolean hasNext() {
        return this.elementIndex < this.elementBuffer.count -1;
    }

    public void next(){
        this.elementIndex++;
    }

    public void previous(){
        this.elementIndex--;
    }


    /* Parser element location methods */
    public int position() {
        return this.elementBuffer.position[this.elementIndex];
    }

    public int length() {
        return this.elementBuffer.length[this.elementIndex];
    }

    public byte type() {
        return this.elementBuffer.type[this.elementIndex];
    }

    /**
       Counts the number of elements in an array. Only works for arrays of primitive
       elements, like strings, numbers, booleans, null etc. Arrays containing objects
       or nested arrays will not be properly counted using this method.
     */
    public int countPrimitiveArrayElements() {
        int tempIndex = this.elementIndex + 1; //skip to first element array.
        while(this.elementBuffer.type[tempIndex] != ElementTypes.JSON_ARRAY_END) {
            tempIndex++;
        }
        return tempIndex - this.elementIndex;
    }


    /* Data extraction support methods */

    /**
     * Compares an unencoded string to the current token value.
     * The two strings (target + token) must be without escape codes (e.g. without " \" \t \r \n " etc.)
     * for this method to work. This method is especially helpful (faster) when comparing a field name to a target
     * string, as you don't have to first extract the field name into a String object in order to compare
     * it to the target String.
     *
     * @param target The string to compare the current string token against.
     * @return
     */
    public boolean isEqualUnencoded(String target) {
        if(target.length() != this.elementBuffer.length[this.elementIndex]) return false;

        for(int i=0, n=target.length(); i<n; i++){
            if(target.charAt(i) != this.buffer.data[this.elementBuffer.position[this.elementIndex] + i]) return false;
        }

        return true;
    }


    public String asString() {

        byte stringType = this.elementBuffer.type[this.elementIndex];
        switch(stringType) {
            case ElementTypes.JSON_PROPERTY_NAME              : { return new String(this.buffer.data,  this.elementBuffer.position[this.elementIndex], this.elementBuffer.length[this.elementIndex]); }
            case ElementTypes.JSON_PROPERTY_VALUE_STRING      : { return new String(this.buffer.data,  this.elementBuffer.position[this.elementIndex], this.elementBuffer.length[this.elementIndex]); }
            case ElementTypes.JSON_ARRAY_VALUE_STRING         : { return new String(this.buffer.data,  this.elementBuffer.position[this.elementIndex], this.elementBuffer.length[this.elementIndex]); }
            case ElementTypes.JSON_PROPERTY_VALUE_STRING_ENC  : { return asStringDecoded();  }
            case ElementTypes.JSON_ARRAY_VALUE_STRING_ENC     : { return asStringDecoded();  }
        }

        return null; //or trow exception - element is not a string.
    }

    private String asStringDecoded() {
        int length = this.elementBuffer.length[this.elementIndex];
        StringBuilder builder = new StringBuilder(length);   //reserve enough space to contain the whole string - to avoid StringBuilder expansion.
        int tempPos = this.elementBuffer.position[this.elementIndex];
        for(int i=0; i < length; i++){
            char nextChar = this.buffer.data[tempPos];
            switch(nextChar) {
                case '\\' : {
                    switch(this.buffer.data[tempPos+1]) {
                        case  '"' : builder.append('"') ; tempPos++; i++; break;
                        case  't' : builder.append('\t'); tempPos++; i++; break;
                        case  'n' : builder.append('\n'); tempPos++; i++; break;
                        case  'r' : builder.append('\r'); tempPos++; i++; break;
                    }
                    break;}
                default   : { builder.append(nextChar); }
            }
            tempPos++;
        }
        return builder.toString();
    }

    public boolean  asBoolean() {
        return 't' == this.buffer.data[this.elementBuffer.position[this.elementIndex]];
    }

    public int    asInt() {
        int value   = 0;
        int tempPos = this.elementBuffer.position[this.elementIndex];
        for(int i=0, n=this.elementBuffer.length[this.elementIndex]; i < n; i++){
            value *= 10;
            value += (this.buffer.data[tempPos] - 48);
            tempPos++;
        }
        return value;
    }

    public double asDouble() {
        int tempPos = this.elementBuffer.position[this.elementIndex];
        int i      = 0;
        int length = this.elementBuffer.length[this.elementIndex];

        double value = 0;
        int    decimalIndex = 0;

        //parse integer part
        for(; i < length; i++){
            value *= 10;
            value += (this.buffer.data[tempPos] - 48);
            tempPos++;
            if(this.buffer.data[tempPos] == '.') {
                tempPos++;
                i++;
                decimalIndex = i;
                break;
            }
        }
        //parse double part
        for(i++; i < length; i++){
            value *= 10;
            value += (this.buffer.data[tempPos] - 48);
            tempPos++;
        }

        int fractionLength = length - decimalIndex - 1; // -1 for the .
        double divisor = Math.pow(10, fractionLength);
        return value / divisor;
    }




}
