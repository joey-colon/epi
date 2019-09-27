package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

public class    IsTreeBalanced {

    @EpiTest(testDataFile = "is_tree_balanced.tsv")

    public static boolean isBalanced(BinaryTreeNode<Integer> tree) {
        if (tree == null) return true;
        return helper(tree) > -1;
    }

    private static int helper(BinaryTreeNode<Integer> node) {
        if (node == null) return 0;
        int left = helper(node.left);
        int right = helper(node.right);

        if (left == -1 || right == -1) return -1;
        if (Math.abs(left - right) > 1 ) return -1;
        return Math.max(left, right) + 1;
    }

    public static void main(String[] args) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "IsTreeBalanced.java",
                                new Object() {
                                }.getClass().getEnclosingClass())
                        .ordinal());
    }
}
