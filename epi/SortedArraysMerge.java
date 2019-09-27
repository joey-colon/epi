package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

class ArrayElement {
    public List<Integer> list;
    public int idx;

    public ArrayElement(List<Integer> list, int idx) {
        this.list = list;
        this.idx = idx;
    }
}

public class SortedArraysMerge {

    @EpiTest(testDataFile = "sorted_arrays_merge.tsv")
    public static List<Integer>
    mergeSortedArrays(List<List<Integer>> sortedArrays) {
        List<Integer> result = new ArrayList<>();
        Queue<ArrayElement> heap = new PriorityQueue<>(
                (a, b) -> (a.list.get(a.idx) - b.list.get(b.idx))
        );

        for (List<Integer> list : sortedArrays) {
            heap.add(new ArrayElement(list, 0));
        }

        while (!heap.isEmpty()) {
            ArrayElement current = heap.poll();
            result.add(current.list.get(current.idx));
            current.idx += 1;
            if (current.list.size() <= current.idx) {
                continue;
            } else {
                heap.offer(current);
            }
        }

        return result;
    }

    public static void main(String[] args) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "SortedArraysMerge.java",
                                new Object() {
                                }.getClass().getEnclosingClass())
                        .ordinal());
    }
}
