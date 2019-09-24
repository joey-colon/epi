package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TestFailure;

public class StringIntegerInterconversion {

    public static String intToString(int x) {
        if (x == 0) return "0";
        StringBuilder sb = new StringBuilder();
        boolean isNegative = (x < 0);
        boolean minValue = false;
        int current = Math.abs(x);

        // Unable to get absolute value of Integer.MIN_VALUE - ugly corner case.
        if (current == Integer.MIN_VALUE) {
            minValue = true;
            current = Integer.MAX_VALUE;
        }

        while (current != 0) {
            sb.append(current % 10);
            current = current / 10;
        }

        if (isNegative) {
            sb.append('-');
        }
        sb = sb.reverse();

        if (minValue) {
            sb.deleteCharAt(sb.length() - 1);
            sb.append(8);
        }

        return sb.toString();
    }

    public static int stringToInt(String s) {
        boolean isNegative = false;
        if (s.charAt(0) == '-') {
            isNegative = true;
        }
        int i = (isNegative) ? 1 : 0;
        int num = 0;
        while (i < s.length()) {
            if (num == 0) {
                num += s.charAt(i) - '0';
            } else {
                num *= 10;
                num += s.charAt(i) - '0';
            }
            i++;
        }

        return (isNegative) ? num * - 1 : num;
    }

    @EpiTest(testDataFile = "string_integer_interconversion.tsv")
    public static void wrapper(int x, String s) throws TestFailure {
        if (!intToString(x).equals(s)) {
            throw new TestFailure("Int to string conversion failed");
        }
        if (stringToInt(s) != x) {
            throw new TestFailure("String to int conversion failed");
        }
    }

    public static void main(String[] args) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "StringIntegerInterconversion.java",
                                new Object() {
                                }.getClass().getEnclosingClass())
                        .ordinal());
    }
}
