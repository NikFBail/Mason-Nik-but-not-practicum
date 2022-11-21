import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.Writer;
import java.io.IOException;
import java.util.PriorityQueue;
import java.util.Scanner;

public class lab11 {
    public static void main(String args[]) throws NumberFormatException, IOException {
        // Initializing variables
        String inputFileName;
        String outputFileName;
        int size = 0;
        String generic;

        // Putting in a scanner to read a file name
        Scanner scant = new Scanner(System.in);
        // Getting the input file
        System.out.println("nom de fichier d'input: ");
        inputFileName = scant.nextLine();
        // Creating an output file
        System.out.println("nom de fichier d'output: ");
        outputFileName = scant.nextLine();

        File results = new File(outputFileName);

        // Reading the file, both read the same file
        BufferedReader br1 = new BufferedReader(new FileReader(inputFileName));
        BufferedReader br2 = new BufferedReader(new FileReader(inputFileName));

        // Getting the number of lines in the file
        // # of lines = # of letters
        while((generic = br1.readLine()) != null) {
            size++;
        }

        // Creates the structure that we will be keeping the nodes in
        PriorityQueue<Node> priorQ = new PriorityQueue<Node>(size, new Comparato());

        // Creating a node for each letter
        while((generic = br2.readLine()) != null) {
            String letter = generic.substring(0, 1); // The letter from that line
            String frequency = generic.substring(2); // The frequency from that line
            double freq = Double.parseDouble(frequency); // The frequency converted to a double
            Node temp = new Node(letter, freq);
            temp.left = null;
            temp.right = null;
            priorQ.add(temp); // Adding that node to the queue
        }
        // Creating root node
        Node root = null;

        /* Extract the two minium values from
         * the heap each time until it has it's
         * size reduced to 1, extract until all
         * nodes are extracted
         */
        while (priorQ.size() > 1) {
            // first value to be extracted
            Node x = priorQ.peek();
            priorQ.poll();
            
            // Second value to be extracted
            Node y = priorQ.peek();
            priorQ.poll();

            // Creating a new node that is the sum of the frequency
            // of the two nodes that were just extracted
            Node f = new Node("-", x.data + y.data);
            f.left = x; // First extracted node is left child
            f.right = y; // Second extracted node is right child
            root = f; // Making node f the root node
            priorQ.add(f); // Adding node f to the priority queue
        }

        printCode(root, "", results);
    }

    // Method for printing the huffman encoding
    public static void printCode(Node root, String stringe, File file) {
        if(root.left == null && root.right == null && Character.isLetter(root.s.charAt(0))) {
            // try {
            // // A writer for the output file
            // BufferedWriter out = new BufferedWriter(new FileWriter(file, true));
            // out.write(root.s + ":" + stringe + "\n");
            // } catch (IO Exception e) {
            //     System.out.println("Exception Occured: " + e);
            // }
            System.out.println(root.s + ":" + stringe);
        }

        printCode(root.left, stringe + "0", file);
        printCode(root.right, stringe + "1", file);
    }
}
