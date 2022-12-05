import java.util.Scanner;

public class Main {
    static Scanner scan = new Scanner(System.in);
    static Scanner scanner1 = new Scanner(System.in);

    static Huffingman Huffer = null;
     public static void main(String[] args) {
         mainPrompt();
     }

     public static void mainPrompt(){
         System.out.println("Welcome!");
         System.out.println("Please specify the name of the input file:");
         String inputFile = scan.nextLine();

         System.out.println("Please specify the name of the output file:");
         String outputFile = scan.nextLine();

         System.out.println("Please specify if the file has the binary or frequency of given characters:");
         System.out.println("1. Frequency\n2. Binary");
         int choice = scanner1.nextInt();
         switch(choice){
             case 1:
                 Huffer = new Huffingman(inputFile, true);
                 Huffer.outputList(outputFile);
                 System.out.println("Average length of binary representation: " + Huffer.averageLength());
                 break;
             case 2:
                 Huffer = new Huffingman(inputFile, false);
                 Huffer.outputList(outputFile);
                 break;
         }

         System.out.println("\nWould you like to:\n1. Encrypt\n2. Decrypt");
         choice = scanner1.nextInt();
         switch(choice) {
             case 1:
                 encryptPrompt();
                 break;
             case 2:
                 decryptPrompt();
                 break;
         }
         mainPrompt();
     }

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
} // end Main