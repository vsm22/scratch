import sun.awt.image.ImageWatched;

import java.util.*;

public class ManagerTree {

    public static class Node {
        String val;
        List<Node> children;
        public Node(String name) {
            this.val = name;
            children = new ArrayList<>();
        }

        @Override
        public String toString() {
            return val;
        }
    }

    public static void findCommonRecurs(Node root, Node target, Deque<Node> curPath, Deque<Node> path) {

        curPath.push(root);

        if (root.val.equals(target.val)) {
            path.addAll(curPath);
        } else {
            for (Node child : root.children) {
                Deque newPath = new LinkedList<>(curPath);
                findCommonRecurs(child, target, newPath, path);
            }
        }
    }

    public static Node findCommon(Node root, Node p1, Node p2) {
        Deque<Node> p1path = new LinkedList<>();
        Deque<Node> p2path = new LinkedList<>();

        findCommonRecurs(root, p1, new LinkedList<>(), p1path);
        findCommonRecurs(root, p2, new LinkedList<>(), p2path);

        System.out.println(p1path);
        System.out.println(p2path);

        Node prev = null;

            while (!p1path.isEmpty() && !p2path.isEmpty()) {



                if (p1path.peekLast().val.equals(p2path.peekLast().val)) {
                    prev = p1path.removeLast();
                    p2path.removeLast();
                    System.out.println("remove" + prev);
                } else {
                    return prev;
                }
            }

        return null;
    }

    public static Node findCommonn(Node root, Node p1, Node p2) {
        Deque<Node> p1Stack = new LinkedList<>();
        Deque<Node> p2Stack = new LinkedList<>();
        Deque<Node> callStack = new LinkedList<>();
        Deque<Node> path = new LinkedList<>();

        boolean isP1Found = false, isP2Found = false;

        callStack.push(root);
        p1Stack.push(root);
        p2Stack.push(root);

        int depth = 0;

        while (!callStack.isEmpty() && (!isP1Found || !isP2Found)) {

            Node cur = callStack.pop();
            System.out.println("\nCur: " + cur);
            System.out.println("Callstack: " + callStack);
            path.push(cur);
            System.out.println("Path: " + path);
            System.out.println("Children: " + cur.children);

            if (cur.val.equals(p1.val)) {
                isP1Found = true;
                p1Stack = new LinkedList<>(path);
                System.out.println("p1Stack: " + p1Stack);
            }

            if (cur.val.equals(p2.val)) {
                isP2Found = true;
                p2Stack = new LinkedList<>(path);
                System.out.println("p2Stack: " + p2Stack);
            }

            if (cur.children != null && !cur.children.isEmpty()) {
                depth++;
                for (Node child : cur.children) {
                    callStack.push(child);
                }
            } else {
                path.pop();
            }
        }

        if (isP1Found && isP2Found) {
            while (!p1Stack.isEmpty() && !p2Stack.isEmpty()) {

                Node prev = null;

                if (p1Stack.peekLast().val.equals(p2Stack.peekLast().val)) {
                    prev = p1Stack.removeLast();
                    p2Stack.removeLast();
                } else {
                    return prev;
                }
            }
        }

        return root;
    }

    public static void main(String[] args) {
        Node root = new Node("Abba");
        Node mens = new Node("Mens");
        Node triptle = new Node("Triptle");
        Node mono = new Node("Mono");

        root.children = Arrays.asList(mens, triptle, mono);

        Node gurav = new Node("Gurav");
        Node alex = new Node("Alex");
        triptle.children = Arrays.asList(gurav, alex);

        Node mark = new Node("Mark");
        Node jennifer = new Node("Jennifer");
        alex.children = Arrays.asList(mark, jennifer);

        Node lola = new Node("Lola");
        Node abram = new Node("Abram");
        mono.children = Arrays.asList(lola, abram);


        Node common = findCommon(root, gurav, jennifer);
        System.out.println("Common: " + common.val);
    }


    /*

    cur: Triptle

    call: Mens
    p1st: Abba Mono
    p2st: Abba Mono



        Abba
        |    \         \
        Mens  Triptle  Mono
              Gurav \          Lola   Abram
                    Alex
                   /  \
                 Mark  Jennifer


     */
}
