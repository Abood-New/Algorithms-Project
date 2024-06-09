import java.util.ArrayList;
import java.util.List;

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
        BT.Import("textFiles/Data1.txt");
        BT.Export("textFiles/Data2.txt");
    }

    public static void four() {
        List<Rectangle> smallRects = new ArrayList<>();
        smallRects.add(new Rectangle(20, 10));
        smallRects.add(new Rectangle(20, 10));
        smallRects.add(new Rectangle(30, 10));
        smallRects.add(new Rectangle(30, 50));
        smallRects.add(new Rectangle(40, 30));
        smallRects.add(new Rectangle(40, 20));

        int totalSmallArea = smallRects.stream()
                .mapToInt(rect -> rect.width * rect.height)
                .sum();

        boolean foundSolution = false;
        for (int i = 1; i <= totalSmallArea; i++) {
            if (totalSmallArea % i == 0) {
                Packer packer = new Packer(i, totalSmallArea / i);
                if (packer.fit(smallRects)) {
                    System.out.println("The small rectangles can fill the big rectangle with dimensions " + i + "x"
                            + (totalSmallArea / i));
                    foundSolution = true;
                    break;
                }
            }
        }

        if (!foundSolution) {
            System.out.println("The small rectangles cannot fill any big rectangle.");
        }
    }

    public static void five() {
        List<Rectangle> smallRects = new ArrayList<>();
        smallRects.add(new Rectangle(20, 10));
        smallRects.add(new Rectangle(20, 10));
        smallRects.add(new Rectangle(30, 10));
        smallRects.add(new Rectangle(30, 50));
        smallRects.add(new Rectangle(40, 30));
        smallRects.add(new Rectangle(40, 20));

        int totalSmallArea = smallRects.stream()
                .mapToInt(rect -> rect.width * rect.height)
                .sum();

        for (int i = 1; i <= totalSmallArea; i++) {
            if (totalSmallArea % i == 0) {
                Packer_1 packer = new Packer_1(i, totalSmallArea / i);
                List<List<Packer_1.Placement>> solutions = packer.fit(smallRects);
                if (solutions.size() > 0) {
                    System.out.println(
                            "The small rectangles can fill the big rectangle in the following ways for the width " + i
                                    + " and height " + (totalSmallArea / i));
                    for (int solutionIndex = 0; solutionIndex < solutions.size(); solutionIndex++) {
                        System.out.println("Solution " + (solutionIndex + 1) + ":");
                        for (Packer_1.Placement placement : solutions.get(solutionIndex)) {
                            System.out.println("Rectangle " + placement.rect.width + "x" + placement.rect.height
                                    + " at (" + placement.x + ", " + placement.y + ")");
                        }
                    }
                } else {
                    System.out.println("The small rectangles cannot fill the big rectangle for the width " + i
                            + " and height " + (totalSmallArea / i));
                }
            }
        }
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
        Node root = BT.Import("textFiles/Data1.txt");
        BinaryTreeDrawing.paint(root);
        BT.Export("textFiles/Data2.txt");
    }
}
