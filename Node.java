import java.util.Comparator;

public class Node {
    
    public double frequency;
    public String letter;
    public String binary = "";

    public Node leftChild;
    public Node rightChild;
    public Node parent;

    public Node() {
        frequency = 100;
    }

    public Node(String let, String bin) {
        this.binary = bin;
        this.letter = let;
    }

    public Node(String let, double freq) {
        this.letter = let;
        this.frequency = freq;
    }    

    public Node(Node left, Node right){ //purposefully gave bad names so we can distinguish between variables
        this.frequency = left.frequency + right.frequency;
        this.leftChild = left;
        this.rightChild = right;
        leftChild.parent = this;
        leftChild.parent = this;
    }

    public String toString(){
        return this.letter + " " + this.frequency;
    }

    public static String treeToString(Node that) {
        if(that == null) return "";
        return treeToString(that.leftChild) + treeToString(that.rightChild) + that.frequency + "||" + that.binary + "   ";
    }

    public int compareTo(Node that) {
        if(that.frequency > this.frequency) return -1;
        if(that.frequency < this.frequency) return 1;
        else return 0;
    }
}
// Comparison Methods

/* I want to try to change these so
 * that they will return the difference of x - y
 * I think that will result in the same thing, but
 * will reduce the lines of code to write
 * Will change after I get everything working
 */
class ComparatoFreq implements Comparator<Node> {
    public int compare(Node x, Node y) {
        return (int) (x.frequency - y.frequency);
    }
}
class ComparatoBinary implements Comparator<Node> {
    public int compare(Node x, Node y) {
        return (int) (x.frequency - y.frequency);
    }
}