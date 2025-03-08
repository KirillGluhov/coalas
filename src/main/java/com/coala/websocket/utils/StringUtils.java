package com.coala.websocket.utils;

public final class StringUtils {

    public static String getStringAfterFirstOccurenceOfSubstring(String text, String substring)
    {
        int index = text.indexOf(substring);
        return (index != -1) ? text.substring(index + substring.length()) : "";
    }

    public static String getStringBeforeFirstOccurenceOfSubstring(String text, String substring)
    {
        int index = text.indexOf(substring);
        return (index != -1) ? text.substring(0, index) : "";
    }
}
