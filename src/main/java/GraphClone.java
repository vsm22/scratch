import javax.print.attribute.IntegerSyntax;
import java.util.*;

class Node {
    public int val;
    public List<Node> neighbors;

    public Node() {
        val = 0;
        neighbors = new ArrayList<Node>();
    }

    public Node(int _val) {
        val = _val;
        neighbors = new ArrayList<Node>();
    }

    public Node(int _val, ArrayList<Node> _neighbors) {
        val = _val;
        neighbors = _neighbors;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        sb.append(this.val);
        sb.append(",[");
        if (!this.neighbors.isEmpty()) {
            sb.append(this.neighbors.get(0).val);
            for (int i = 1; i < this.neighbors.size(); i++) {
                sb.append("," + this.neighbors.get(i).val);
            }
        }
        sb.append("]]");
        return sb.toString();
    }
}

class GraphClone {

    public static Node cloneGraph(Node node) {

        /** node is the original node
         * parent is a clone parent
         */
        class NodeToParent {
            Node node;
            Node parent;
            NodeToParent(Node node, Node parent) {
                this.node = node;
                this.parent = parent;
            }
        }

        Node root = null;
        Set<Node> visited = new HashSet<>();
        Deque<NodeToParent> toVisit = new LinkedList<>();
        toVisit.offerLast(new NodeToParent(node, null));

        while (!toVisit.isEmpty()) {
            NodeToParent curNodeToParent = toVisit.pollFirst();
            Node curNode = curNodeToParent.node;
            Node clone = new Node(curNode.val);
            if (root == null) {
                root = clone;
            }
            visited.add(curNode);

            if (curNodeToParent.parent != null) {
                curNodeToParent.parent.neighbors.add(clone);
            }

            for (Node neighbor : curNode.neighbors) {
                if (!visited.contains(neighbor)) {
                    toVisit.addLast(new NodeToParent(neighbor, clone));
                }
            }
        }

        return root;
    }

    public static Node adjacencyListToNode(List<List<Integer>> adjList) {

        Map<Integer, List<Integer>> graphMap = new HashMap<>();

        for (int i = 0; i < adjList.size(); i++) {
            graphMap.put(i + 1, adjList.get(i));
        }

        System.out.println(graphMap);

        Deque<Node> toVisit = new LinkedList<>();
        Set<Integer> visited = new HashSet<>();
        Map<Integer, Node> existingNodes = new HashMap<>();

        Node root = new Node(1);
        toVisit.addLast(root);
        existingNodes.put(1, root);

        while (!toVisit.isEmpty()) {

            Node cur = toVisit.pollFirst();
            visited.add(cur.val);

            if (root == null) {
                root = cur;
            }

            for (Integer neighborIdx : graphMap.get(cur.val)) {

                Node neighbor = null;

                if (existingNodes.containsKey(neighborIdx)) {
                    neighbor = existingNodes.get(neighborIdx);
                } else {
                    neighbor = new Node(neighborIdx);
                    existingNodes.put(neighborIdx, neighbor);
                }

                cur.neighbors.add(neighbor);

                if (!visited.contains(neighborIdx) && !toVisit.contains(neighbor)) {
                    toVisit.addLast(neighbor);
                }
            }

            System.out.println(cur);
        }

        return root;
    }

    public static String printGraph(Node root) {

        if (root == null) {
            return "[]";
        }

        StringBuilder sb = new StringBuilder("[[");

        Set<Node> visited = new HashSet<>();
        Queue<Node> toVisit = new LinkedList<>();
        toVisit.offer(root);

        while (!toVisit.isEmpty()) {

            Node cur = toVisit.poll();
            visited.add(cur);

            sb.append(cur.val);
            sb.append("=[");

            for (int i = 0; i < cur.neighbors.size(); i++) {
                if (i > 0) {
                    sb.append(",");
                }
                sb.append(cur.neighbors.get(i).val);
                if (!visited.contains(cur.neighbors.get(i))) {
                    toVisit.offer(cur.neighbors.get(i));
                }
            }
            sb.append("]],");
        }

        sb.append("]");
        return sb.toString();
    }

    public static void main(String[] args) {

        List<List<Integer>> adjList = new ArrayList<>();

        List<Integer> adj1 = new ArrayList<>();
        adj1.add(2);
        adj1.add(4);
        List<Integer> adj2 = new ArrayList<>();
        adj2.add(1);
        adj2.add(3);
        List<Integer> adj3 = new ArrayList<>();
        adj3.add(2);
        adj3.add(4);
        List<Integer> adj4 = new ArrayList<>();
        adj4.add(1);
        adj4.add(3);

        adjList.add(adj1);
        adjList.add(adj2);
        adjList.add(adj3);
        adjList.add(adj4);

        Node graph = adjacencyListToNode(adjList);
        System.out.println(printGraph(graph));

        Node clone = cloneGraph(graph);
        System.out.println(printGraph(clone));
    }
}