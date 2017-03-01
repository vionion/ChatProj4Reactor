package com.tsybulko.chat.web.util;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by vtsybulko on 25/02/17.
 */
public class SwearnesChecker {

    private Set<String> swearWords = new HashSet<>();

    public long getMessageSwearnes(String message) {
        return Arrays.stream(message.split(" "))
                .filter(word -> swearWords.contains(word))
                .count();
    }

    public String filter(String message) {
        return Arrays.stream(message.split(" "))
                .filter(word -> !swearWords.contains(word))
                .collect(Collectors.joining(" "));
    }

    public Set<String> getSwearWords() {
        return swearWords;
    }

    public void setSwearWords(Set<String> swearWords) {
        this.swearWords = swearWords;
    }
}
