import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.concurrent.PriorityBlockingQueue;

public class Huffingman {
    public Node root;
    public ArrayList<Node> nodeArray = new ArrayList<Node>();
    
    public Huffingman(String inputFileName, Boolean freqOrBin) {
        // Reads input file and creates node array
        try {
            File inputFile = new File(inputFileName);
            Scanner fileScan = new Scanner(inputFile);
            if(freqOrBin) {
                while(fileScan.hasNextLine()) {
                    this.nodeArray.add(new Node(fileScan.next().substring(0, 1), fileScan.next()));
                }
                createTree();
            } else {
                while(fileScan.hasNextLine()) {
                    this.nodeArray.add(new Node(fileScan.next(), fileScan.next()));
                    fileScan.nextLine();
                }
                createBinaryTree();
            }
            fileScan.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
        }
    }

    public void createTree() {
        Node first, second, combinedNode;
        PriorityQueue<Node> lilQ = new PriorityQueue<>(new ComparatoFreq());
        lilQ.addAll(nodeArray);
        while(this.root == null) {
            first = lilQ.poll();
            second = lilQ.poll();
            combinedNode = new Node(first, second);
            lilQ.add(combinedNode);
            if(combinedNode.frequency == 100) this.root = combinedNode;
        }
        HuffingAlg(this.root);
    }

    public void createBinaryTree() {
        root = new Node();
        for(Node node : nodeArray) {
            Node curr = root;
            // Wanna try to convert to a string array here
            char[] binArray = node.binary.toCharArray();
            for(int i = 0; i < binArray.length; i++) {
                if(binArray[i] == '0') {
                    if(i == binArray.length - 1) curr.left = node;
                    else {
                        if(curr.left  == null) curr.left = new Node();
                        curr = curr.left;
                    }
                } else {
                    if(i == binArray.length - 1) curr.right = node;
                    else {
                        if(curr.right == null) curr.right = new Node();
                        curr = curr.right;
                    }
                }
            }
        }
    }

    public static void HuffingAlg(Node node) {
        if(node == null) return;
        if(node.left != null) node.left.binary = node.binary + "0";
        if(node.right != null) node.right.binary = node.binary + "1";
        HuffingAlg(node.left);
        HuffingAlg(node.right);
    }
}
