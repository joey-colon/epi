package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

public class Parity {
    @EpiTest(testDataFile = "parity.tsv")
    public static short parity(long x) {
        // TODO - you fill in here.
        int count = 0;

        while (x != 0) {
            long result = x & 1;
            if (result == 1) {
                count += 1;
            }
            x = x >>> 1;
        }
        return (short) ((count % 2 == 0) ? 0 : 1);
    }

    public static void main(String[] args) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "Parity.java",
                                new Object() {
                                }.getClass().getEnclosingClass())
                        .ordinal());
    }
}
