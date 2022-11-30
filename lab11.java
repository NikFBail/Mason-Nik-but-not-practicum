import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.Writer;
import java.io.IOException;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Scanner;

public class lab11 {

    public Node root;
    public ArrayList<Node> nodeArray = new ArrayList<Node>();

    public static void main(String args[]) throws NumberFormatException, IOException {
        // Initializing variables
        String inputFileName;
        String outputFileName;
        int size = 0;
        String generic;
        String answer;
        Boolean freqOrBin = true;

        // Putting in a scanner to read a file name
        Scanner scant = new Scanner(System.in);
        // Getting the input file
        System.out.println("nom de fichier d'entrée: ");
        inputFileName = scant.nextLine();
        // Creating an output file
        System.out.println("nom de fichier de sortie: ");
        outputFileName = scant.nextLine();
        // Determining if the file is in binary or has the letter frequencies
        System.out.println("Est le fichier en binaire ou c'est que il contient les frequences des lettres?");
        System.out.println("b pour binaire ou f pour frequences des lettres:");
        answer = scant.nextLine();
        if(answer == "b") freqOrBin = true;
        if(answer == "f") freqOrBin = false;

        // Reads input file and creates node array
        try {
            File inputFile = new File(inputFileName);
            Scanner fileScan = new Scanner(inputFile);
            if(freqOrBin) {
                while(fileScan.hasNextLine()) {
                    this.nodeArray.add(new Node(fileScan.next().substring(0, 1), fileScan.next()));
                }
            }
        }

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
            priorQ.add(temp); // Adding that node to the queue
        }

        //System.out.println(priorQ.toString());
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
            System.out.println(priorQ);
            priorQ.poll();
            System.out.println(priorQ);
            
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

        Node.printCode(root, "", results);

        System.out.println("Encrypt or Decrypt? (e/d)");
            scant.nextLine();
            char answer = scant.next().charAt(0);
            if(answer == 'd'){
                System.out.println("Now enter the encrypted text:");
                System.out.println("Decrypted text:");
                String encryptedText = "";
                while(scant.hasNext()){
                    encryptedText += scant.next();
                }
                System.out.println(potato.decrypt(encryptedText));
            } else if (answer == 'e') {
                System.out.println("Now enter some plaintext:");
                String plainText = "";
                while(scant.hasNext()){
                    plainText += scant.next();
                }
                String cleanText = plainText.replaceAll("\\W|[0-9]|_", "");
                System.out.println(potato.encrypt(cleanText.toLowerCase()));
            } else System.out.println("That's not what I asked for");
            scant.close();
    }
}