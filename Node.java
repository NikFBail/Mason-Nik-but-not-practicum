public class Node {
    
    int data;
    String s;
    Node left;
    Node right;

    public Node(String letter, int freq) {
        s = letter;
        data = freq;
    }
}