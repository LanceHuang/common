package com.lance.common.tool.util;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * "What the fuck?" => "What the *?"
 * But, it don't support English. Yes, It only works for Chinese, :) Because English use space to split word.
 *
 * @author Lance
 * @since 2018-8-22 12:00:53
 */
public class SensitiveWordFilter {

    /**
     * Sensitive word dictionary, "dict/sensitiveWord.txt,dict/fuck.txt"
     */
    private String locations;

    private DfaNode rootNode;

    //TODO White list

    public SensitiveWordFilter(String locations) {
        this.locations = locations;
        init();
    }

    private void init() {
        rootNode = new DfaNode();
        for (String location : locations.split(",")) {
            init(location);
        }
    }

    private void init(String location) {
        if (isEmpty(location)) {
            return;
        }

        Set<String> sensitiveWords = readFileAsSet(location);
        init(sensitiveWords);
    }

    private Set<String> readFileAsSet(String location) {
        Set<String> dataSet = new HashSet<>();
        BufferedReader bufr = null;
        try {
            bufr = new BufferedReader(new InputStreamReader(
                    this.getClass().getClassLoader().getResourceAsStream(location)));
            String line = null;
            while ((line = bufr.readLine()) != null) {
                dataSet.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != bufr) {
                try {
                    bufr.close();
                } catch (IOException e) {
                    //Do nothing
                }
            }
        }
        return dataSet;
    }

    private void init(Set<String> sensitiveWords) {
        if (null == sensitiveWords || sensitiveWords.isEmpty()) {
            return;
        }

        for (String sensitiveWord : sensitiveWords) {
            DfaNode currNode = rootNode;
            for (int i = 0; i < sensitiveWord.length(); i++) {
                char c = sensitiveWord.charAt(i);

                if (currNode.hasChild(c)) {
                    currNode = currNode.findChild(c);
                } else {
                    currNode = currNode.addChild(c);
                }

                //A complete word
                if (i == sensitiveWord.length() - 1) {
                    currNode.setEnd(true);
                }
            }
        }
    }

    /**
     * Filter sensitive word
     *
     * @param message A message
     * @return New message without sensitive word
     */
    public String filter(String message) {
        if (isEmpty(message)) {
            return message;
        }

        char[] messageCharacters = message.toCharArray();
        for (int i = 0; i < message.length(); ) {
            int endIndex = endOfSensitiveWord(message, i);
            if (endIndex == -1) {
                i++;
                continue;
            }

            for (int j = i; j < endIndex; j++) {
                messageCharacters[j] = '*';
            }
            i = endIndex;
        }

        return new String(messageCharacters);
    }

    /**
     * End index of sensitive word. Example are as followed.
     * Sensitive words: fuck, shit
     *
     * <ul>
     * <li>endOfSensitiveWord("What the fuck!!!", 0) == 13</li>
     * <li>endOfSensitiveWord("What the fuk!!!", 0) == -1</li>
     * <li>endOfSensitiveWord("What the fuuk!!!", 10) == -1</li>
     * </ul>
     *
     * @param message   a message
     * @param fromIndex the index to start the search from
     * @return end index of sensitive word, not included; {@code -1} didn't find the sensitive word
     */
    public int endOfSensitiveWord(String message, int fromIndex) {
        if (isEmpty(message) || fromIndex < 0 || fromIndex >= message.length()) {
            return -1;
        }

        DfaNode currNode = rootNode;
        for (int i = fromIndex; i < message.length(); i++) {
            char c = message.charAt(i);
            if (!currNode.hasChild(c)) {
                return -1;
            }

            currNode = currNode.findChild(c);
            if (currNode.isEnd()) {
                return i + 1;
            }
        }

        return -1;
    }


    /**
     * Return {@code} if the {@code message} contains sensitive words.
     *
     * @param message A message
     * @return {@code true}, the {@code message} contains sensitive words
     */
    public boolean containSensitiveWord(String message) {
        if (isEmpty(message)) {
            return false;
        }

        DfaNode currNode = rootNode;
        for (int i = 0; i < message.length(); i++) {
            char c = message.charAt(i);
            if (!currNode.hasChild(c)) {
                currNode = rootNode;
                continue;
            }

            currNode = currNode.findChild(c);
            if (currNode.isEnd()) {
                return true;
            }
        }

        return false;
    }

    private boolean isEmpty(String str) {
        return null == str || str.isEmpty();
    }

    static class DfaNode {
        private char c;
        private boolean end;
        private Map<Character, DfaNode> child;

        public DfaNode() {
        }

        public DfaNode(char c) {
            this.c = c;
        }

        public char getC() {
            return c;
        }

        public boolean isEnd() {
            return end;
        }

        public void setEnd(boolean end) {
            this.end = end;
        }

        public boolean hasChild(char c) {
            c = Character.toLowerCase(c);

            return null != child && child.containsKey(c);
        }

        public DfaNode findChild(char c) {
            c = Character.toLowerCase(c);

            return (null != child && child.containsKey(c)) ? child.get(c) : null;
        }

        public DfaNode addChild(char c) {
            c = Character.toLowerCase(c);

            if (null == child) {
                child = new HashMap<>();
            }

            DfaNode newChild = new DfaNode(c);
            child.put(c, newChild);
            return newChild;
        }

    }
}
