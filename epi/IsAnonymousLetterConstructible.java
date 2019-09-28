package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.HashMap;
import java.util.Map;

public class IsAnonymousLetterConstructible {
    @EpiTest(testDataFile = "is_anonymous_letter_constructible.tsv")

    public static boolean isLetterConstructibleFromMagazine(String letterText,
                                                            String magazineText) {
        Map<Character, Integer> magMap = new HashMap<>();
        Map<Character, Integer> letMap = new HashMap<>();

        populate(letterText, letMap);
        populate(magazineText, magMap);

        for (char ch : letMap.keySet()) {
            if (!magMap.containsKey(ch)) return false;
            if (letMap.get(ch) > magMap.get(ch)) return false;
        }

        return true;
    }

    private static void populate(String s, Map<Character, Integer> map) {
        for (int i = 0; i < s.length(); i++) {
            map.put(s.charAt(i), map.getOrDefault(s.charAt(i), 0) + 1);
        }
    }
    public static void main(String[] args) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "IsAnonymousLetterConstructible.java",
                                new Object() {
                                }.getClass().getEnclosingClass())
                        .ordinal());
    }
}
