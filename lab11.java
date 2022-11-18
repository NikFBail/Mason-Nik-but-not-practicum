import java.io.BufferedReader;
import java.io.FileReader;
import java.util.PriorityQueue;
import java.util.Scanner;

public class lab11 {
    public static void main(String args[]) {
        String inputFileName;
        int size = 0;
        String generic;

        Scanner s = new Scanner(System.in);
        System.out.println("nom de fichier d'input: ");
        inputFileName = s.nextLine();
        BufferedReader br = new BufferedReader(new FileReader(inputFileName));
        BufferedReader stupid = new BufferedReader(new FileReader(inputFileName));
        
        while((generic = br.readLine()) != null) {
            size++;
        }

        PriorityQueue<Node> betterName = new PriorityQueue<Node>(size, new Comparato());

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
