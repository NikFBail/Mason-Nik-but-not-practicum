import java.util.Comparator;

public class Node {
    
    public double frequency;
    public String letter;
    public String binary = "";

    public Node left;
    public Node right;
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

    public Node(Node lift, Node write){ //purposefully gave bad names so we can distinguish between variables
        this.frequency = lift.frequency + write.frequency;
        this.left = lift;
        this.right = write;
        left.parent = this;
        right.parent = this;
    }

    public String toString(){
        return this.letter + " " + this.frequency;
    }

    public static String treeToString(Node that) {
        if(that == null) return "";
        return treeToString(that.left) + treeToString(that.right) + that.frequency + "||" + that.binary + " ";
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
        if(y.frequency > x.frequency) return -1;
        if(y.frequency < x.frequency) return 1;
        else return 0;
    }
}
class ComparatoBinary implements Comparator<Node> {
    public int compare(Node x, Node y) {
        if(y.binary.length() > x.binary.length()) return -1;
        if(y.binary.length() < x.binary.length()) return 1;
        else return 0;
    }
}