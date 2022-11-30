import java.util.Comparator;

public class Node {
    
    public double frequency;
    public String letter;
    public String binary = "";

    public Node left;
    public Node right;
    public Node parent;

    public Node(String let, double freq) {
        this.letter = let;
        this.frequency = freq;
        this.right = null;
        this.left = null;
    }

    public Node(String let, String bin) {
        this.binary = bin;
        this.letter = let;
    }

    public Node(Node lift, Node write){ //purposefully gave bad names so we can distinguish between variables
        this.frequency = left.frequency + right.frequency;
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