import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String args[]) throws NumberFormatException, IOException {
        // Initializing variables
        String inputFileName;
        String outputFileName;
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
        System.out.println("Entre b pour binaire ou entre f pour frequences des lettres:");
        String answer = scant.nextLine();
        if(answer == "f") freqOrBin = true;
        if(answer == "b") freqOrBin = false;

        Huffingman huffMan = new Huffingman(inputFileName, freqOrBin);

        huffMan.outputList(outputFileName);
        if(answer == "f") System.out.println("Avg. length of binary representations: " + huffMan.AvgLength());

        System.out.println("Encrypt or Decrypt? (e/d)");
            scant.nextLine();
            String result = scant.next();
            if(result == "d"){
                System.out.println("Now enter the encrypted text:");
                System.out.println("Decrypted text:");
                String encryptedText = "";
                while(scant.hasNext()){
                    encryptedText += scant.next();
                }
                System.out.println(huffMan.decrypt(encryptedText));
            } else if (result == "e") {
                System.out.println("Now enter some plaintext:");
                String plainText = "";
                while(scant.hasNext()){
                    plainText += scant.next();
                }
                String cleanText = plainText.replaceAll("\\W|[0-9]|_", "");
                System.out.println(huffMan.encrypt(cleanText.toLowerCase()));
            } else System.out.println("That's not what I asked for");
            scant.close();
    }
}