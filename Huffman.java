import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Huffman {

    public Node root;
    public ArrayList<Node> nodeArray = new ArrayList<Node>();;
    
/*
 * This function takes a file name and a boolean as it's arguments. The file name is the input file that
 * we will read from, the boolean determines if the input file is in binary or contains the frequencies
 * of the letters.
 */
    public Huffman(String input, Boolean freqOrBin){
        try{
            File file = new File(input);  
            Scanner scan = new Scanner(file);
            if(freqOrBin) { // If the file contains frequency values
                while (scan.hasNextLine()) {
                    this.nodeArray.add(new Node(scan.next().substring(0, 1), scan.nextDouble()));
                }
                createTree();
            } else { // If the file is in binary
                while (scan.hasNextLine()) {
                    this.nodeArray.add(new Node(scan.next().substring(0, 1), scan.next()));
                    scan.nextLine();
                }
                createBinaryTree();
            }
            scan.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred");
        }
    }

    /*
     * This method takes the ArrayList made by the Huffman constructor, converts it to a PriorityQueue,
     * and then loops through, each time combining the two nodes with the lowest frequencies to make a
     * parent node with a frequency equal to the sum of the two. The two nodes used become its children.
     * The loop continues until a parent node is created with a frequency value of 100% (the root node).
     */
    public void createTree(){
        Node first, second, combinedNode;
        PriorityQueue<Node> nodeQ = new PriorityQueue<>(new ComparatoFreq());
        nodeQ.addAll(nodeArray);
        while(this.root == null){
            first = nodeQ.poll();
            second = nodeQ.poll();
            combinedNode = new Node(first, second);
            nodeQ.add(combinedNode);
            if(combinedNode.frequency == 100) this.root = combinedNode;
        }
        HuffAlg(this.root);
    }

    public void createBinaryTree(){
        root = new Node();
        for (Node node : nodeArray) {
            Node curr = root;
            char[] binArray = node.binary.toCharArray();
            for (int i = 0; i < binArray.length; i++) {
                if(binArray[i] == '0') {
                    if(i == binArray.length-1) curr.leftChild = node;
                    else {
                        if(curr.leftChild == null) curr.leftChild = new Node();
                        curr = curr.leftChild;
                    }
                } else {
                    if(i == binArray.length-1) curr.rightChild = node;
                    else {
                        if(curr.rightChild == null) curr.rightChild = new Node();
                        curr = curr.rightChild;
                    }
                }
            }
        }
    }

    /*
     * This function creates the binary encoding for a Huffman Tree. Anything left of the current
     * node is a '0' and anything to the right is a '1'. Explores the tree recursively, going down
     * to the left child and the right child of the current node.
     */
    public static void HuffAlg(Node node){
        if(node == null) return;
        if(node.leftChild !=null) node.leftChild.binary = node.binary + "0";
        if(node.rightChild !=null) node.rightChild.binary = node.binary + "1";
        HuffAlg(node.leftChild);
        HuffAlg(node.rightChild);
    }

    /*
     * This function takes the name of the output file and prints into the file each letter with its
     * binary representation. 
     */
    public void outputList(String outFile){
        Node node;
        try{
            FileWriter writer= new FileWriter(outFile);
            PriorityQueue<Node> nodeQ = new PriorityQueue<>(new ComparatoBinary());
            nodeQ.addAll(nodeArray);
            for (int i = nodeQ.size(); 0 < i; i--) {
                node = nodeQ.poll();
                writer.write(node.letter + " " + node.binary + "\n");
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("IOException in the outputList method");
        }
    }

    /*
     * This function takes an encrypted text and decrypts to English alphabet
     * characters. Starting at the beginning of the encrypted text, the function
     * starts at the root node of the Huffman tree. If the current binary character is
     * a '0', it goes to the left child. If the current binary character is a '1', it
     * goes to the right child. It then moves on to the next binary character in the 
     * encrypted text, and repeats this process until it reaches a leaf node. Then the
     * English alphabet value of that leaf node is added to the result, string and the whole
     * process starts over again at the root node. Ends once all the encrypted binary characters
     * have been iterated through once.
     */
    public String decrypt(String cryptText){
        char[] text = cryptText.toCharArray();
        Node currNode = this.root;
        String result = "";
        for (int i = 0; i < text.length; i++) {
            if(text[i] == '1') currNode = currNode.rightChild;
            else currNode = currNode.leftChild;
            if(currNode.letter != "0") {
                result += currNode.letter; 
                if(result.length()%70 == 69) result += "\n";
                currNode = root;
            }
        }
        return result;
    }

    /*
     * This function takes some plaintext that is assumed to have the characters in the Huffing Tree and
     * converts it to their binary representation without any spaces. Every 70 characters it will also
     * add a \n to make it more readable. The function ends when it runs out of characters to convert.
     */
    public String encrypt(String plainText){
        String result = "";
        int counter = 0;
        String[] text = new String[plainText.length()];
        for(int i = 0; i < plainText.length(); i++) {
            text[i] = plainText.substring(i, i + 1);
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

    /*
     * This function is a helper function for the encrypt() method. It will recursively go through the tree
     * and get the binary encoding of the letter that it's looking for.
     */
    private String encryptHelper(String currentLetter, Node node){
        if(node != null) {
            if(node.letter == currentLetter) return node.binary;
            return encryptHelper(currentLetter, node.leftChild) + encryptHelper(currentLetter, node.rightChild);
        } else return "";
    }

    /*
     * This function finds the average binary length of the current tree in this instance. 
     * It goes through every node in the ArrayList of nodes and sums their binary length multiplied
     * by their frequency. 
     */
    private String averageLength(){
        double total = 0;
        for (Node node : nodeArray) {
            total += (node.frequency/100) * node.binary.length();
        }
        return total + "";
    }

     public static void main(String[] args) {
            System.out.println("Give the name of the input file and then the name of the output file, separated by a space.");
            // Scanner for system in
            Scanner scan = new Scanner(System.in);
            String inputFile = scan.next();
            String outputFile = scan.next();

            System.out.println("Specify if the file has the binary or frequency of given characters,");
            System.out.println("write b for binary or f for frequency.");
            scan.nextLine();
            char answer = scan.next().charAt(0);
            Boolean freqOrBin = true;
            if(answer == 'b') freqOrBin = false;
            if(answer == 'f') freqOrBin = true;


            // Construct the Huffman object with the file input
            Huffman Huffer = new Huffman(inputFile, freqOrBin);
            // Second input from the scanner is given to the fileWriter
            
            // Takes a given writer, and uses it to output 
            Huffer.outputList(outputFile);
            if(answer == 'f') System.out.println("Average length of binary representations: " + Huffer.averageLength());


            System.out.println("Encrypt or Decrypt? (e/d)");
            scan.nextLine();
            answer = scan.next().charAt(0);
            if(answer == 'd'){
                System.out.println("Now enter the encrypted text:");
                
                String encryptedText = "";
                while(scan.hasNext()){
                    encryptedText += scan.next();
                }
                System.out.println("Decrypted text:");
                System.out.println(Huffer.decrypt(encryptedText));
            } else if (answer == 'e') {
                System.out.println("Now enter some plaintext:");
                String plainText = "";
                while(scan.hasNext()){
                    plainText += scan.next();
                }
                String cleanText = plainText.replaceAll("\\W|[0-9]|_", "");
                System.out.println(Huffer.encrypt(cleanText.toLowerCase()));
            } else System.out.println("That's not what I asked for");
            scan.close();
     }
}