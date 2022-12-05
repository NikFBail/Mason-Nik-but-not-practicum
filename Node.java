import java.util.Comparator;

public class Node{
    public Node leftChild;
    public Node rightChild;
    public Node parent;

    public double letterFrequency;
    public char letter;
    public String binary = "";

    public Node(){
        letterFrequency = 100;
    }

    public Node(char character, double freq){
        this.letterFrequency = freq;
        this.letter = character;
    }

    public Node(char character, String bin){
        this.binary = bin;
        this.letter = character;
    }

    public Node(Node left, Node right){
        this.letterFrequency = left.letterFrequency + right.letterFrequency;
        this.leftChild = left;
        this.rightChild = right;
        left.parent = this;
        right.parent = this;
    }

    public String toString(){
        return this.letter + " " + this.letterFrequency;
    }

    /**
     * rootToString is a toString method to turn a given node tree into a string
     * @param node Takes a root node of a node tree
     * @return System outs using a postOrder traversal method outputting "letterFrequency||binary"
     * So that node's frequency next to its binary representation
     */
    public static String rootToString(Node node){
        if(node == null) return "";
        return rootToString(node.leftChild) + rootToString(node.rightChild) + node.letterFrequency + "||" + node.binary + "    ";
    }

    public int compareTo(Node node){
        if(node.letterFrequency > this.letterFrequency) return -1;
        if(node.letterFrequency < this.letterFrequency) return 1;
        return 0;
    }

}

class compareFrequency implements Comparator<Node>{
    public int compare(Node n1, Node n2){
        if(n2.letterFrequency > n1.letterFrequency) return -1;
        if(n2.letterFrequency < n1.letterFrequency) return 1;
        return 0;
    }

}

class compareBinary implements Comparator<Node>{
    public int compare(Node n1, Node n2){
        if(n2.binary.length() > n1.binary.length()) return -1;
        if(n2.binary.length() < n1.binary.length()) return 1;
        return 0;
    }

}
