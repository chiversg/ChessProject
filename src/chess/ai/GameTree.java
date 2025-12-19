package chess.ai;

import java.util.LinkedList;

public class GameTree {
    Node root;
    int num = 0;

    public GameTree() {
        root = new Node(100, null, null);
    }

    /**
     * Increases the tree depth by one
     */
    public void IncreaseTreeDepth() {
        LinkedList<Node> leafNodes = new LinkedList<>();

        BreadthFirst(root, leafNodes);
        System.out.println("LEAF NODES: " + leafNodes.size());
        for (Node node : leafNodes) {
            addMoves(node);
        }
    }

    /**
     * Add all possible moves a node can make as children
     *
     * @param parent The node that we want to add moves to
     */
    private void addMoves(Node parent) {
        for (int i = 0; i < 3; i++) {
            if (parent.firstChild == null) {
                parent.firstChild = new Node(num, null, null);
            } else {
                parent.firstChild = new Node(num, null, parent.firstChild);
            }
            num++;
        }
    }

    /**
     * Find all the leaf nodes in the Game Tree using BFS
     *
     * @param node The root node of the tree
     * @param list The list to fill with leaf nodes
     */
    private void BreadthFirst(Node node, LinkedList<Node> list) {
        LinkedList<Node> queue = new LinkedList<>();
        queue.add(node);
        Node parent;
        Node child;

        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size > 0) {
                parent = queue.poll();

                //If the parent has no children, it must be a leaf node
                if(parent.firstChild == null){
                    list.add(parent);
                }

                child = parent.firstChild;
                while (child != null) {
                    queue.add(child);
                    child = child.nextSibling;
                }
                size--;
            }
        }
    }

    //Useful for testing tree accuracy
    public void PrintTree(Node node) {
        LinkedList<Node> queue = new LinkedList<>();
        queue.add(node);
        Node parent;
        Node child;
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size > 0) {
                parent = queue.poll();

                System.out.print(parent.item + ", ");
                child = parent.firstChild;
                while (child != null) {
                    queue.add(child);
                    child = child.nextSibling;
                }
                size--;
            }
        }
    }
    
    public Node GetRoot() {
        return root;
    }
}
