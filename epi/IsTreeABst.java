package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

public class IsTreeABst {
    @EpiTest(testDataFile = "is_tree_a_bst.tsv")

    public static boolean isBinaryTreeBST(BinaryTreeNode<Integer> tree) {
        return isBinaryTreeBST(tree, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    /*

           5
          / \
         3   7
     */
    private static boolean isBinaryTreeBST(BinaryTreeNode<Integer> node, int lower, int upper) {
        if (node == null) return true;
        if (node.data > upper) return false;
        if (node.data < lower) return false;

        boolean left = isBinaryTreeBST(node.left, lower, node.data - 1);
        boolean right = isBinaryTreeBST(node.right, node.data + 1, upper);
        return left && right;
    }

    public static void main(String[] args) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "IsTreeABst.java",
                                new Object() {
                                }.getClass().getEnclosingClass())
                        .ordinal());
    }
}
