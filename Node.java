public class Node {
    public char value;
    public Node left = null;
    public Node right = null;
    public int width;
    public int height;

    public Node(char value) {
        this.value = value;
    }

    public Node(char value, int width, int height) {
        this.value = value;
        this.width = width;
        this.height = height;
    }
}
