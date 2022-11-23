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
}