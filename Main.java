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

    private static void decryptPrompt() {
        System.out.println("Please enter the ciphertext:");
        String ciphertext = scan.next();
        System.out.println("The decrypted text is: " + Huffer.decrypt(ciphertext.replaceAll(" ", "")));
    }

    public static void encryptPrompt(){
         System.out.println("Please enter the plaintext:");
         String plaintext = scan.nextLine();
         String cleantext = plaintext.replaceAll("^\\s|\\W|\\d|_|:", "").toLowerCase();
         System.out.println("The result of encryption is: " + Huffer.encrypt(cleantext));
     }
} // end Main