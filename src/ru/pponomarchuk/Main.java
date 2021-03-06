package ru.pponomarchuk;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        Node root = new Node();
        root.setValue(1);

        Node node1 = new Node();
        node1.setValue(2);
        Node node2 = new Node();
        node2.setValue(3);
        root.setLeftChild(node1);
        root.setRightChild(node2);

        Node node3 = new Node();
        node3.setValue(4);
        node1.setLeftChild(node3);

        System.out.println(getSumByLayers(root));
        System.out.println(getSumByRecursion(root));
        System.out.println(getSumByBFS(root));
    }

    private static int getSumByLayers(Node root) {
        int sum = 0;
        boolean nextLayerExist = root.getLeftChild() != null || root.getRightChild() != null;
        List<Node> nodes = new ArrayList<>();
        nodes.add(root);

        if (!nextLayerExist) {
            sum = root.getValue();
        }

        while (nextLayerExist) {
            nextLayerExist = false;
            List<Node> currentLayerNodes = new ArrayList<>();

            for (Node item : nodes) {
                sum += item.getValue();

                if (item.getLeftChild() != null) {
                    currentLayerNodes.add(item.getLeftChild());
                }
                if (item.getRightChild() != null) {
                    currentLayerNodes.add(item.getRightChild());
                }
                if (item.getRightChild() != null || item.getLeftChild() != null) {
                    nextLayerExist = true;
                }
            }
            nodes = currentLayerNodes;
        }
        return sum;
    }

    private static int getSumByRecursion(Node root) {
        int sum = root.getValue();
        if (root.getRightChild() != null) {
            sum += getSumByRecursion(root.getRightChild());
        }
        if (root.getLeftChild() != null) {
            sum += getSumByRecursion(root.getLeftChild());
        }
        return sum;
    }

    private static int getSumByBFS(Node root) {
        int sum = 0;
        Deque<Node> nodesQueue = new ArrayDeque<>();
        nodesQueue.add(root);
        Node current;

        while (nodesQueue.peekFirst() != null) {
            current = nodesQueue.pollFirst();

            if (current.getLeftChild() != null) {
                nodesQueue.add(current.getLeftChild());
            }
            if (current.getRightChild() != null) {
                nodesQueue.add(current.getRightChild());
            }
            sum += current.getValue();
        }
        return sum;
    }
}
