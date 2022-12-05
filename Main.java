import java.util.Scanner;

public class Main {
     public static void main(String[] args) {
            Scanner scan = new Scanner(System.in);

            System.out.println("Give the name of the input file:");
            // Scanner for system in
            String inputFile = scan.nextLine();

            System.out.println("Give the name of the output file:");
            String outputFile = scan.nextLine();

            System.out.println("Specify if the file has the binary or frequency of given characters,");
            System.out.println("write b for binary or f for frequency.");
            String answer = scan.nextLine();
            Boolean freqOrBin = true;
            if(answer == "b") freqOrBin = false;
            if(answer == "f") freqOrBin = true;


            // Construct the Huffman object with the file input
            Huffingman Huffer = new Huffingman(inputFile, freqOrBin);
            // Second input from the scanner is given to the fileWriter
            
            // Takes a given writer, and uses it to output 
            Huffer.outputList(outputFile);
            if(answer == "f") System.out.println("Average length of binary representations: " + Huffer.avgLength());


            System.out.println("Enter an option:\n1. Encrypt\n2. Decrypt");
            switch (new Scanner(System.in).nextInt()) {
                case 1:
                    System.out.println("Now enter some plaintext:");
                    String plaintext = "";
                    while(scan.hasNext()){
                        plaintext += scan.next();
                    }
                    String lametext = plaintext.replaceAll("^\\s|\\W|\\d|_|:", "");
                    System.out.println("This it the plaintext: " + lametext);
                    System.out.println(Huffer.encrypt(lametext.toLowerCase()));
                case 2:
                    System.out.println("Now enter the encrypted text:"); 
                    String encryptedText = "";
                    while(scan.hasNext()){
                        encryptedText += scan.next();
                    }
                    System.out.println("Decrypted text:");
                    System.out.println(Huffer.decrypt(encryptedText)); 
            }
            scan.close();
     }
}