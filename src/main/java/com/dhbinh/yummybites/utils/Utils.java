package com.dhbinh.yummybites.utils;

import org.springframework.stereotype.Component;

@Component
public class Utils {

    public String capitalizeFirstWord(String input) {
        if (input == null || input.isEmpty()) {
            return input;
        }

        String firstLetter = input.substring(0, 1).toUpperCase();
        String remainingLetters = input.substring(1);

        return firstLetter + remainingLetters;
    }
    public String capitalizeFirstWordAndAfterWhitespace(String input) {
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

    private boolean isExcludedWord(String word) {
        String[] excludedWords = {"and", "of", "the", "a", "an", "to", "is", "are", "in", "for", "on", "with", "at", "by", "from"};
        for (String excludedWord : excludedWords) {
            if (word.equals(excludedWord)) {
                return true;
            }
        }
        return false;
    }
}
