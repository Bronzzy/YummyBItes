package com.dhbinh.restaurantservice.utils;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class Utils {

    public static String capitalizeFirstWord(String input) {
        return StringUtils.capitalize(input);
    }

    public static String capitalizeFirstWordAndAfterWhitespace(String input) {
        StringBuilder sb = new StringBuilder();
        String[] words = input.toLowerCase().split("\\s+");

        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            if (!word.isEmpty()) {
                if (i > 0 && isExcludedWord(word)) {
                    sb.append(word);
                } else {
                    sb.append(Character.toUpperCase(word.charAt(0)));
                    sb.append(word.substring(1));
                }
                sb.append(" ");
            }
        }

        return sb.toString().trim();
    }

    private static boolean isExcludedWord(String word) {
        String[] excludedWords = {"and", "of", "the", "a", "an", "to", "is", "are", "in", "for", "on", "with", "at", "by", "from"};
        for (String excludedWord : excludedWords) {
            if (word.equals(excludedWord)) {
                return true;
            }
        }
        return false;
    }
}
