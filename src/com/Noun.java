package com;

import java.util.HashMap;
import java.util.Map;

public class Noun {
    private char gender;
    private String singular;
    private String plural;

    private boolean ends_in_vowel(String str) {
        char[] vowels = {'a', 'e', 'i', 'o', 'u'};
        boolean ends_in_vowel = false;
        for (char vowel : vowels)  {
            if (str.charAt(str.length()-1) == vowel) {
                ends_in_vowel = true;
                break;
            }
        }
        return ends_in_vowel;
        }

    Noun(String sing, char gen) {
        singular = sing;
        gender = gen;

        if (ends_in_vowel(singular)) {
            plural = singular + 's';
        } else if (singular.charAt(singular.length()-1) == 'l') {
            Map<Character, Character> replace_vowel = Map.of('a', 'a', 'e', 'é', 'i', 'i', 'o', 'ó', 'u', 'u');
            int char_index = singular.length()-2;
            plural = singular.substring(0, char_index);
            if (ends_in_vowel(singular.substring(char_index, char_index + 1))) {
                plural += replace_vowel.get(singular.charAt(char_index));
            } else {
                plural += singular.charAt(char_index);
            }

            plural += "is";
        } else if (singular.substring(singular.length()-2, singular.length()).equals("em") ||
                   singular.substring(singular.length()-2, singular.length()).equals("ém")) {
            plural = singular.substring(0, singular.length()-1) + "ns";
        } else if (singular.charAt(singular.length()-1) == 'r' ||
                   singular.charAt(singular.length()-1) == 's' ||
                   singular.charAt(singular.length()-1) == 'z') {
            plural = singular + "es";
        } else {
            plural = singular + 's';
        }

    }

    public static void main(String [] args) {
        Noun test_noun = new Noun("refém", 'f');
        System.out.println(test_noun.singular);
        System.out.println(test_noun.plural);
    }

}
