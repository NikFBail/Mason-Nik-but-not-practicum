import java.util.Comparator;

public class Node{
    public Node leftChild;
    public Node rightChild;
    public Node parent;

    public double frequency;
    public char letter;
    public String binary = "";

    public Node(){
        frequency = 100;
    }

    public Node(char character, double freq){
        this.frequency = freq;
        this.letter = character;
    }

    public Node(char character, String bin){
        this.binary = bin;
        this.letter = character;
    }

    public Node(Node left, Node right){
        this.frequency = left.frequency + right.frequency;
        this.leftChild = left;
        this.rightChild = right;
        left.parent = this;
        right.parent = this;
    }

    public String toString(){
        return this.letter + " " + this.frequency;
    }

    /**
     * rootToString is a toString method to turn a given node tree into a string
     * @param node Takes a root node of a node tree
     * @return System outs using a postOrder traversal method outputting "frequency||binary"
     * So that node's frequency next to its binary representation
     */
    public static String rootToString(Node node){
        if(node == null) return "";
        return rootToString(node.leftChild) + rootToString(node.rightChild) + node.frequency + "||" + node.binary + "    ";
    }

    public int compareTo(Node node){
        if(node.frequency > this.frequency) return -1;
        if(node.frequency < this.frequency) return 1;
        return 0;
    }

}

class ComparatoFreq implements Comparator<Node>{
    public int compare(Node n1, Node n2){
        if(n2.frequency > n1.frequency) return -1;
        if(n2.frequency < n1.frequency) return 1;
        return 0;
    }

}

class ComparatoBin implements Comparator<Node>{
    public int compare(Node n1, Node n2){
        if(n2.binary.length() > n1.binary.length()) return -1;
        if(n2.binary.length() < n1.binary.length()) return 1;
        return 0;
    }

}
