import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.PriorityQueue;
import java.util.Scanner;

public class lab11 {
    public static void main(String args[]) throws NumberFormatException, IOException {
        // Initializing variables
        String inputFileName;
        int size = 0;
        String generic;

        // Putting in a scanner to read a file name
        Scanner scant = new Scanner(System.in);
        System.out.println("nom de fichier d'input: ");
        inputFileName = scant.nextLine();

        // Reading the file, both read the same file
        BufferedReader br1 = new BufferedReader(new FileReader(inputFileName));
        BufferedReader br2 = new BufferedReader(new FileReader(inputFileName));

        // Getting the number of lines in the file
        // # of lines = # of letters
        while((generic = br1.readLine()) != null) {
            size++;
        }

        PriorityQueue<Node> priorQueueue = new PriorityQueue<Node>(size, new Comparato());

        // Creating a node for each letter
        while((generic = br2.readLine()) != null) {
            String letter = generic.substring(0, 1);
            String frequency = generic.substring(2);
            int freq = Integer.parseInt(frequency);
            priorQueueue.add(new Node(letter, freq));       
        }

        Node root = null;

        while (priorQueueue.size() > 1) {
            Node x = priorQueueue.peek();
            priorQueueue.poll();

            Node y = priorQueueue.peek();
            priorQueueue.poll();

            Node f = new Node("-", x.data + y.data);
            f.left = x;
            f.right = y;
            root = f;
            priorQueueue.add(f);
        }
    }

    public static void printCode(Node root, String stringe) {
        if(root.left == null && root.right == null && Character.isLetter(root.s)) {
            System.out.println(root.s + ":" + stringe);
        }

        printCode(root.left, stringe + "0");
        printCode(root.right, stringe + "1");
    }
}
