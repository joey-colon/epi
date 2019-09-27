package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.List;

public class SearchFirstKey {
    @EpiTest(testDataFile = "search_first_key.tsv")

    public static int searchFirstOfK(List<Integer> A, int k) {
        int result = binarySearch(A, 0, A.size() - 1, k);
        if (result == -1) return -1;
        while (result > 0 && A.get(result - 1) == k) {
            result = binarySearch(A, 0, result-1, k);
        }
        return result;
    }

    public static int binarySearch(List<Integer> a, int low, int high, int key) {
        while (low <= high) {
            int mid = (low + high) / 2;
            if (a.get(mid) == key) {
                return mid;
            } else if (a.get(mid) < key) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        return (low < a.size() && a.get(low) == key) ? low : -1;
    }

    public static void main(String[] args) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "SearchFirstKey.java",
                                new Object() {
                                }.getClass().getEnclosingClass())
                        .ordinal());
    }
}
