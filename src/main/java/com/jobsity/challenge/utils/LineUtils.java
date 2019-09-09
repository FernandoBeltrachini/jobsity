package com.jobsity.challenge.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Class that holds utilities to manipulate a line.
 */
public class LineUtils {

    private static final String[] EMPTY_STRING_ARRAY = {};

    /**
     * Method used to split a string.
     */
    public static String[] split(String toSplit, String delimitor) {
        return Objects.nonNull(toSplit) && Objects.nonNull(delimitor) ? toSplit.split(delimitor) : EMPTY_STRING_ARRAY;
    }

    /**
     * Method used to give format to a line.
     */
    public static String parseLine(List<String> words, String delimitor) {
        if (Objects.nonNull(words) && Objects.nonNull(delimitor)) {
            StringBuilder stringBuilder = new StringBuilder();
            words.forEach(word -> stringBuilder.append(word).append(delimitor));
            return stringBuilder.toString();
        }
        return null;
    }

    /**
     * Method used to print a list with keyword.
     */
    public static void printList(String keyword, List<String> values, String delimiter) {
        List<String> line = new ArrayList<>();
        line.add(keyword);
        line.addAll(values);
        System.out.println(LineUtils.parseLine(line, delimiter).trim());

    }
}
