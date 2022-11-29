import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Node {
    
    double data;
    String s;
    Node left;
    Node right;

    public Node(String letter, double freq) {
        s = letter;
        data = freq;
        this.right = null;
        this.left = null;
    }

    public String toString(){
        return this.s;
    }
    
    // Method for printing the huffman encoding
    public static void printCode(Node root, String stringe, File file) {
        if(root.left == null && root.right == null && Character.isLetter(root.s.charAt(0))) {
             try {
            // A writer for the output file
             BufferedWriter out = new BufferedWriter(new FileWriter(file, true));
             out.write(root.s + ":" + stringe + "\n");
             out.close();
             } catch (IOException e) {
                 System.out.println("Exception Occured: " + e);
            }
            System.out.println(root.s + ":" + stringe);
        }

        if (root.left != null){printCode(root.left, stringe + "0", file);}
        if (root.right != null){printCode(root.right, stringe + "1", file);}
        
    }
}