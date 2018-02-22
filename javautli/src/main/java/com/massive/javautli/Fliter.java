package com.massive.javautli;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Fliter {
    public String ClearText(String inComeText) {
        String pattern = "[A-Za-z0-9]";
        Pattern p = Pattern.compile( pattern );
        Matcher m = p.matcher( inComeText );
        StringBuilder sb = new StringBuilder();
        while( m.find() )
        {
            sb.append( m.group() );
        }
        String result = sb.toString();
        return result;
    }
}
