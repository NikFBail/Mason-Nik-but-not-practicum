import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Scanner;

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

    public void outputList(String outFile) {
        try {
            FileWriter writer = new FileWriter(outFile);
            PriorityQueue<Node> nodeQueue = new PriorityQueue<>(new ComparatoBinary());
            Node node;
            nodeQueue.addAll(nodeArray);
            for(int i = nodeQueue.size(); i > 0; i--) {
                node = nodeQueue.poll();
                writer.write(node.letter + " " + node.binary + "\n");
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("IOException in the outputList method.");
        }
    }

    public String AvgLength() {
        double total = 0;
        for(Node node : nodeArray) {
            total += (node.frequency/100) * node.binary.length();
        }
        return total + "";
    }

    public String decrypt(String cryptText){
        char[] text = cryptText.toCharArray();
        Node currNode = this.root;
        String result = "";
        for (int i = 0; i < text.length; i++) {
            if(text[i] == '1') {
                currNode = currNode.right;
            } else {
                currNode = currNode.left;
            }
            if(currNode.letter != "0") {
                result += currNode.letter; 
                if(result.length()%70 == 69) result += "\n";
                currNode = root;
            }
        }
        return result;
    }

    public String encrypt(String plaintext){
        String result = "";
        int counter = 0;
        String[] text = new String[plaintext.length()];
        for(int i = 0; i < plaintext.length(); i++) {
            text[i] = plaintext.substring(i, i + 1);
        }
        for (int i = 0; i < text.length; i++) {
            String encryptedChar = encryptHelper(text[i], root);
            result += encryptedChar;
            counter += encryptedChar.length();
            if(counter > 70){
                counter = 0;
                result += "\n";
            }
        }
        return result;
    }

    private String encryptHelper(String currentChar, Node node){
        if(node != null) {
            if(node.letter == currentChar) return node.binary;
            return encryptHelper(currentChar, node.left) + encryptHelper(currentChar, node.right);
        } else return "";
    }
}