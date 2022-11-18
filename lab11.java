import java.io.BufferedReader;
import java.io.FileReader;
import java.util.PriorityQueue;
import java.util.Scanner;

public class lab11 {
    public static void main(String args[]) {
        // Initializing variables
        String inputFileName;
        int size = 0;
        String generic;

        // Putting in a scanner to read a file name
        Scanner s = new Scanner(System.in);
        System.out.println("nom de fichier d'input: ");
        inputFileName = s.nextLine();

        // Reading the file, both read the same file
        BufferedReader br = new BufferedReader(new FileReader(inputFileName));
        BufferedReader stupid = new BufferedReader(new FileReader(inputFileName));

        // Getting the number of lines in the file
        // # of lines = # of letters
        while((generic = br.readLine()) != null) {
            size++;
        }

        PriorityQueue<Node> betterName = new PriorityQueue<Node>(size, new Comparato());

        // Creating a node for each letter
        while((generic = stupid.readLine()) != null) {
            String letter = generic.substring(0, 1);
            String frequency = generic.substring(2);
            int freq = Integer.parseInt(frequency);
            betterName.add(new Node(letter, freq));       
        }

        for(int i = 0; i < size; i++) {
            Node noded = new Node();

            noded.
        }
    }
}
