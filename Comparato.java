import java.util.Comparator;

public class Comparato implements Comparator<Node>{
    public int compare(Node x, Node y) {
        return x.data - y.data;
    }
}
