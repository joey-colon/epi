package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.EpiUserType;
import epi.test_framework.GenericTest;
import epi.test_framework.TestFailure;

import java.util.List;
import java.util.NoSuchElementException;

class StackNode {
    public Integer value;
    public Integer max;
    public StackNode next = null;

    public StackNode(Integer value, Integer max) {
        this.value = value;
        this.max = max;
    }
}

public class StackWithMax {

    public static class Stack {
        StackNode head = null;
        public boolean empty() {
            return head == null;
        }

        public Integer max() {
            if (empty()) {
                return null;
            }
            return head.max;
        }

        public Integer pop() {
            if (empty()) {
                return null;
            }
            Integer value = head.value;
            head = head.next;
            return value;
        }

        public void push(Integer x) {
            if (head == null) {
                head = new StackNode(x, x);
                return;
            }

            StackNode newNode = new StackNode(x, Integer.max(head.max, x));
            newNode.next = head;
            head = newNode;
        }
    }

    @EpiUserType(ctorParams = {String.class, int.class})
    public static class StackOp {
        public String op;
        public int arg;

        public StackOp(String op, int arg) {
            this.op = op;
            this.arg = arg;
        }
    }

    @EpiTest(testDataFile = "stack_with_max.tsv")
    public static void stackTest(List<StackOp> ops) throws TestFailure {
        try {
            Stack s = new Stack();
            int result;
            for (StackOp op : ops) {
                switch (op.op) {
                    case "Stack":
                        s = new Stack();
                        break;
                    case "push":
                        s.push(op.arg);
                        break;
                    case "pop":
                        result = s.pop();
                        if (result != op.arg) {
                            throw new TestFailure("Pop: expected " + op.arg +
                                    ", got " + result);
                        }
                        break;
                    case "max":
                        result = s.max();
                        if (result != op.arg) {
                            throw new TestFailure("Max: expected " + op.arg +
                                    ", got " + result);
                        }
                        break;
                    case "empty":
                        result = s.empty() ? 1 : 0;
                        if (result != op.arg) {
                            throw new TestFailure("Empty: expected " + op.arg +
                                    ", got " + s);
                        }
                        break;
                    default:
                        throw new RuntimeException("Unsupported stack operation: " + op.op);
                }
            }
        } catch (NoSuchElementException e) {
            throw new TestFailure("Unexpected NoSuchElement exception");
        }
    }

    public static void main(String[] args) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "StackWithMax.java",
                                new Object() {
                                }.getClass().getEnclosingClass())
                        .ordinal());
    }
}
