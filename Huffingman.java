import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Huffingman {
    public Node root;
    public ArrayList<Node> nodeArr = new ArrayList<Node>();;
    
/*
 * This function takes a file name and a boolean as it's arguments. The file name is the input file that
 * we will read from, the boolean determines if the input file is in binary or contains the frequencies
 * of the letters.
 */
    public Huffingman(String input, Boolean freqOrBin){
        try{
            File file = new File(input);  
            Scanner scan = new Scanner(file);
            if(freqOrBin) { // If the file contains frequency values
                while (scan.hasNextLine()) {
                    this.nodeArr.add(new Node(scan.next().substring(0, 1), scan.nextDouble()));
                }
                createTree();
            } else { // If the file is in binary
                while (scan.hasNextLine()) {
                    this.nodeArr.add(new Node(scan.next().substring(0, 1), scan.next()));
                    scan.nextLine();
                }
                createBinaryTree();
            }
            scan.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred");
        }
    }

    /**
     * createTree has no input, but uses the ArrayList of nodes made by the Huffingman constructor.
     * It takes the ArrayList and creates a PriorityQueue of the nodes. Then it loops through taking
     * the two letters with the lowest frequencies (first two in queue) and combines them by making a parent
     * node with a frequency equal to the sum of the two given frequencies, then it makes the two given nodes children
     * of the new node. It does this over and over again until it creates a node with a frequency value of 100%, then
     * it makes that node the root node. Then it sends the root node to the HuffingAlgorithm method
     */
    public void createTree(){
        Node first, second, combinedNode;
        PriorityQueue<Node> nodeQ = new PriorityQueue<>(new ComparatoFreq());
        nodeQ.addAll(nodeArr);
        while(this.root == null){
            first = nodeQueue.poll();
            second = nodeQueue.poll();
            combinedNode = new Node(first, second);
            nodeQueue.add(combinedNode);
            if(combinedNode.letterFrequency == 100) this.root = combinedNode;
        }
        HuffingAlgorithm(this.root);
    }

    public void createBinaryTree(){
        root = new Node();
        for (Node node : nodeArr) {
            Node curr = root;
            char[] binArray = node.binary.toCharArray();
            for (int i = 0; i < binArray.length; i++) {
                if(binArray[i] == '0') {
                    if(i == binArray.length-1) {
                        current.leftChild = node;
                    } else {
                        if(current.leftChild == null)current.leftChild = new Node();
                        current = current.leftChild;
                    }
                } else {
                    if(i == binArray.length-1) {
                        current.rightChild = node;
                    } else {
                        if(current.rightChild == null)current.rightChild = new Node();
                        current = current.rightChild;
                    }
                }
            }

        }

    }

    /**
     * This function takes the root node of the tree created by createTree(). It recursively
     * explores the tree adding a string value to each node's binary representation. It adds the string
     * 0 to every left child's binary representation and 1 to every right child's binary representation.
     * This hits every node in the tree and accurately gives each letter their binary representation according to
     * the Huffing Algorithm
     * @param node
     */
    public static void HuffingAlgorithm(Node node){
        if(node != null) {
            if (node.leftChild != null)
                node.leftChild.binary = node.binary + "0";
            if (node.rightChild != null)
                node.rightChild.binary = node.binary + "1";
            HuffingAlgorithm(node.leftChild);
            HuffingAlgorithm(node.rightChild);
        }
    }

    /**
     * This function takes the name of the output file and outputs each letter with its binary representation.
     * It throws the ArrayList of nodes into a priority queue that organizes it by length of binary representation.
     * Then it writes each node letter next to its binary represention in that order.
     * @param outFile Takes the name of the output file
     */
    public void outputList(String outFile){
        try{
            FileWriter writer= new FileWriter(outFile);
            PriorityQueue<Node> nodeQ = new PriorityQueue<>(new ComparatoBinary());
            nodeQ.addAll(nodeArr);
            for (int i = nodeQ.size(); 0 < i; i--) {
                node = nodeQ.poll();
                writer.write(node.letter + " " + node.binary + "\n");
            }
            writer.close();
        } catch (IOException e) { System.out.println("IOException in the outputList method");}
    }

    /**
     * This function takes the encrypted text and turns it into the original characters.
     * It starts at the root node, and iterates through by following the binary given.
     * If the current character is a 1 it goes to the right and if it is a 0 it goes to the left.
     * It does this until it hits a leaf node (a node that has a character value). Once it finds one, it adds
     * it to the result string and starts back at the root continuing the process until it runs out of characters in the given string.
     * At the end it outputs the accumulated result string.
     * @param cryptText Takes text encrypted by the same Huffing tree as this instance
     * @return Returns the decrypted text with no spaces or puncuation.
     */
    public String decrypt(String cryptText){
        char[] text = cryptText.toCharArray();
        Node currNode = this.root;
        String result = "";
        for (int i = 0; i < text.length; i++) {
            if(text[i] == '1') {
                currNode = currNode.rightChild;
            } else {
                currNode = currNode.leftChild;
            }
            if(currNode.letter != (char)0) {
                result += currNode.letter;
                //if(result.length()%70 == 69) result += "\n";
                currNode = root;
            }
        }
        return result;
    }

    /**
     * This function takes some plaintext that is assumed to have the characters in the Huffing Tree and
     * converts it to their binary represntation without any spaces. Every 70 characters it will also
     * add a \n to make it more readable. The function ends when it runs out of characters to convert.
     *
     * @param plainText Takes some plaintext without any spaces or special characters.
     * If a character in the plaintext doesn't exist in the Huffing Tree it won't print anything
     * for that character
     * @return Returns the encrypted text in 1's and 0's
     */
    public String encrypt(String plainText){
        String result = "";
        int counter = 0;
        char[] text = plainText.toCharArray();
        for (int i = 0; i < text.length; i++) {
            String encryptedChar = encryptHelper(text[i], root);
            result += encryptedChar;
            counter += encryptedChar.length();
//            if(counter > 70){
//                counter = 0;
//                result += "\n";
//            }
        }
        return result;
    }

    /**
     * This function takes information from the encrypt() method, like the letter to be encrypted
     * and the root node of the Huffing Tree. It visits every node in the tree and adds all the returned
     * strings together. The only time the function returns an actual string is when it finds the node with
     * the matching character. So at the end it should only have the correct binary.
     * @param currentChar Takes the character that is to be encrypted
     * @param node Takes the root node of the tree used to encrypt the text
     * @return returns the binary representation of the given character
     */
    private String encryptHelper(char currentChar, Node node){
        if(node != null) {
            if(node.letter == currentChar) return node.binary;
            return encryptHelper(currentChar, node.leftChild) + encryptHelper(currentChar, node.rightChild);
        } else return "";
    }

    /**
     * This function finds the average binary length of the current tree in this instance.
     * It goes through every node in the ArrayList of nodes and sums their binary length multiplied
     * by their frequency.
     * @return returns the average binary length
     */
    String avgLength(){
        double total = 0;
        for (Node node : nodeArr) {
            total += (node.frequency/100) * node.binary.length();
        }
        return total + "";
    }
}