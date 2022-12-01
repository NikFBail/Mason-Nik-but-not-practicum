public class encodtion {
    public Node root;

    public String decrypt(String cryptText){
        char[] text = cryptText.toCharArray();
        Node currNode = this.root;
        String result = "";
        for (int i = 0; i < text.length; i++) {
            if(text[i] == '1') {
                currNode = currNode.right;
            } else {
                currNode = currNode.left;
            }
            if(currNode.letter != "0") {
                result += currNode.letter; 
                if(result.length()%70 == 69) result += "\n";
                currNode = root;
            }
        }
        return result;
    }

    public String encrypt(String plaintext){
        String result = "";
        int counter = 0;
        String[] text = new String[plaintext.length()];
        for(int i = 0; i < plaintext.length(); i++) {
            text[i] = plaintext.substring(i, i + 1);
        }
        for (int i = 0; i < text.length; i++) {
            String encryptedChar = encryptHelper(text[i], root);
            result += encryptedChar;
            counter += encryptedChar.length();
            if(counter > 70){
                counter = 0;
                result += "\n";
            }
        }
        return result;
    }

    private String encryptHelper(String currentChar, Node node){
        if(node != null) {
            if(node.letter == currentChar) return node.binary;
            return encryptHelper(currentChar, node.left) + encryptHelper(currentChar, node.right);
        } else return "";
    }
}
