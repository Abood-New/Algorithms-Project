import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Main {
    public static void main(String[] args) {

    }

    public static void oneAndTwo() {
        BinaryTree BT = new BinaryTree();
        System.out.println();
        BT.getCode();
        BT.printCode();
    }

    public static void three() {
        BinaryTree BT = new BinaryTree();
        System.out.println();
        BT.Import("Data1.txt");
        BT.Export("Data2.txt");
    }

    public static void seven() {
        BinaryTree bt = new BinaryTree();
        JFrame frame = new JFrame();
        frame.setLayout(null);
        String expression = JOptionPane.showInputDialog(frame, "Enter Tree Expression",
                "(A[10,20]|(B[10,20]|C[10,20]))-(D[10,20]|(E[10,20]-F[10,20]))");
        if (expression != null) {
            Node root = bt.convert(expression, 0, expression.length() - 1);
            BinaryTreeDrawing.paint(root);
        } else {
            return;
        }
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void eight() {
        BinaryTree BT = new BinaryTree();
        System.out.println();
        Node root = BT.Import("Data1.txt");
        BinaryTreeDrawing.paint(root);
        BT.Export("Data2.txt");
    }
}
