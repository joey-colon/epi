package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.List;

public class IntersectSortedArrays {
    @EpiTest(testDataFile = "intersect_sorted_arrays.tsv")

    public static List<Integer> intersectTwoSortedArrays(List<Integer> A,
                                                         List<Integer> B) {
        List<Integer> result = new ArrayList<>();
        int i = 0;
        int j = 0;

        while (i < A.size() && j < B.size()) {
            if (A.get(i).equals(B.get(j))) {
                if (result.size() > 0) {
                    if (!result.get(result.size() - 1).equals(A.get(i))) {
                        result.add(A.get(i));
                    }
                } else {
                    result.add(A.get(i));
                }

                int cache = A.get(i);
                while (i < A.size() && A.get(i).equals(cache)) i++;
                while (j < B.size() && B.get(j).equals(cache)) j++;
            } else if (A.get(i) < B.get(j)) {
                i++;
            } else {
                j++;
            }
        }

        return result;
    }

    public static void main(String[] args) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "IntersectSortedArrays.java",
                                new Object() {
                                }.getClass().getEnclosingClass())
                        .ordinal());
    }
}
