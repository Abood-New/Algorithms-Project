import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class BinaryTree {
    private Node root;
    private Scanner scan = new Scanner(System.in);

    BinaryTree() {
        this.root = null;
    }

    void getCode() {
        String grid = scan.nextLine();
        scan.close();
        String newgrid = "";
        for (int i = 0; i < grid.length(); i++) {
            char currentChar = grid.charAt(i);
            if (currentChar != ' ') {
                String tempStr = "";
                tempStr += currentChar;
                newgrid += tempStr;
            }
        }
        root = convert(newgrid, 0, newgrid.length() - 1);
    }

    public Node convert(String newForm, int start, int end) {
        Node result = null;
        if (start >= end) {
            return null;
        }
        if (newForm.equals("")) {
            return null;
        }
        if (newForm.charAt(start) >= 65 && newForm.charAt(start) <= 90) {
            char nodeName = newForm.charAt(start);
            String widthString = "";
            int i = start + 2;
            while (newForm.charAt(i) != ',') {
                widthString += newForm.charAt(i);
                i++;
            }
            i++;
            String heightString = "";
            while (newForm.charAt(i) != ']') {
                heightString += newForm.charAt(i);
                i++;
            }
            int width = Integer.parseInt(widthString);
            int height = Integer.parseInt(heightString);
            Node node = new Node(nodeName, width, height);
            if (i == end) {
                result = node;
            } else {
                i++;
                result = new Node(newForm.charAt(i));
                result.left = node;
                if (newForm.charAt(i + 1) == '(') {
                    result.right = convert(newForm, i + 2, end - 1);
                } else {
                    result.right = convert(newForm, i + 1, end);
                }
            }
        } else {
            int counter = 1;
            int i = start + 1;
            while (counter != 0) {
                if (newForm.charAt(i) == '(') {
                    counter++;
                } else if (newForm.charAt(i) == ')') {
                    counter--;
                }
                i++;
            }
            result = new Node(newForm.charAt(i));
            result.left = convert(newForm, start + 1, i - 2);
            if (newForm.charAt(i + 1) == '(') {
                result.right = convert(newForm, i + 2, end - 1);
            } else {
                result.right = convert(newForm, i + 1, end);
            }
        }
        return result;
    }

    void printCode() {
        String code = reConvert(root);
        System.out.println(code);
    }

    String reConvert(Node root) {
        String result = "";
        if (root == null) {
            return result = "";
        }
        if (root.left == null && root.right == null) {
            result += root.value;
            result = result.concat("[").concat(Integer.toString(root.width)).concat(",")
                    .concat(Integer.toString(root.height)).concat("]");
            return result;
        }
        String leftResult = "";
        if (root.left.value == '|' || root.left.value == '-') {
            leftResult = "(".concat(reConvert(root.left)).concat(")");
        } else {
            leftResult += root.left.value;
            leftResult = leftResult.concat("[").concat(Integer.toString(root.left.width)).concat(",")
                    .concat(Integer.toString(root.left.height)).concat("]");
        }
        String rightResult = "";
        if (root.right.value == '|' || root.right.value == '-') {
            rightResult = "(".concat(reConvert(root.right)).concat(")");
        } else {
            rightResult += root.right.value;
            rightResult = rightResult.concat("[").concat(Integer.toString(root.right.width)).concat(",")
                    .concat(Integer.toString(root.right.height)).concat("]");

        }
        String rootValue = "";
        rootValue += root.value;
        result = leftResult.concat(rootValue).concat(rightResult);
        return result;
    }

    private ArrayList<ArrayList<Character>> grid = new ArrayList<>();

    public Node Import(String path) {
        String Line = null;
        int rowCount = 0;
        int colCount = 0;
        try {
            BufferedReader BR = new BufferedReader(new FileReader(path));
            while ((Line = BR.readLine()) != null) {
                if (!Line.equals("")) {
                    rowCount++;
                    colCount = Line.length();
                }
                ArrayList<Character> thisLine = new ArrayList<>();
                for (int i = 0; i < Line.length(); i++) {
                    thisLine.add(Line.charAt(i));
                }
                grid.add(thisLine);
            }
            BR.close();
        } catch (

        IOException IOE) {
            System.out.println(IOE);
        }
        root = convert(0, 0, colCount, rowCount, rowCount, colCount);
        return root;
    }

    public Node convert(int i,
            int j, int maxWidth, int maxHeight, int boundI, int boundJ) {
        if (j >= boundJ || i >= boundI) {
            return null;
        }
        Node result = null;
        int I = i + 1;
        int J = j + 1;
        int width = 2;
        while (J < boundJ && grid.get(I).get(J) != '|') {
            width++;
            J++;
        }
        int height = 0;
        while (I < boundI && grid.get(I).get(J) != '-') {
            I++;
            height++;
        }
        char Name = grid.get(i + 1).get(j + 1);
        Node cell = new Node(Name, width, height);
        if (width == maxWidth) {
            if (height + 2 == maxHeight) {
                result = cell;
            } else {
                result = new Node('-');
                result.left = cell;
                result.right = convert(i + height + 2, j, maxWidth, maxHeight - (height + 2), boundI, boundJ);
            }
        } else if (height + 2 == maxHeight) {
            result = new Node('|');
            result.left = cell;
            result.right = convert(i, j + width, maxWidth - width, maxHeight, boundI, boundJ);
        } else {
            int indexI = i + 1;
            int indexJ = j;
            while (indexI < boundI - 1) {
                while (indexJ < boundJ
                        && grid.get(indexI).get(indexJ) == '-') {
                    indexJ++;
                }
                if (indexJ == maxWidth) {
                    break;
                }
                indexI++;
            }
            if (indexJ == maxWidth) {
                result = new Node('-');
                result.left = convert(i, j, maxWidth, indexI - i + 1, indexI + 1, boundJ);
                result.right = convert(indexI + 1, j, maxWidth, boundI - (indexI + 1), boundI, boundJ);
                result.width = result.left.width;
                result.height = result.left.height + result.right.height;
            } else {
                result = new Node('|');
                result.left = convert(i, j, indexJ - j, maxHeight, boundI, indexJ);
                result.right = convert(i, indexJ, boundJ - indexJ, maxHeight, boundI, boundJ);
                result.height = result.right.height;
                result.width = result.left.width + result.right.width;
            }
        }
        return result;
    }

    public void Export(String path) {
        ArrayList<ArrayList<Character>> Lines = Export(root);
        try {
            BufferedWriter BW = new BufferedWriter(new FileWriter(path, true));
            for (ArrayList<Character> Line : Lines) {
                for (Character letter : Line) {
                    BW.write(letter);
                }
                BW.write('\n');
            }
            BW.close();
        } catch (IOException IOE) {
            System.out.println(IOE);
        }
    }

    public ArrayList<ArrayList<Character>> Export(Node root) {
        ArrayList<ArrayList<Character>> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        if (root.left == null && root.right == null) {
            char name = root.value;
            int width = root.width;
            int height = root.height;
            ArrayList<Character> upperLine = new ArrayList<>();
            for (int i = 1; i <= width; i++) {
                upperLine.add('-');
            }
            res.add(upperLine);
            while (height > 0) {
                ArrayList<Character> middLines = new ArrayList<>();
                for (int i = 1; i <= width; i++) {
                    middLines.add(' ');
                }
                middLines.set(0, '|');
                middLines.set(middLines.size() - 1, '|');
                res.add(middLines);
                height--;
            }
            ArrayList<Character> bottomLine = new ArrayList<>();
            for (int i = 1; i <= width; i++) {
                bottomLine.add('-');
            }
            res.add(bottomLine);
            res.get(1).set(1, name);
        }
        ArrayList<ArrayList<Character>> leftResult = Export(root.left);
        ArrayList<ArrayList<Character>> rightResult = Export(root.right);
        res.addAll(leftResult);
        if (root.value == '|') {
            int i = 0;
            for (ArrayList<Character> Line : res) {
                Line.addAll(rightResult.get(i));
                i++;
            }

        } else {
            res.addAll(rightResult);
        }
        return res;
    }

    public void Rotate() {
        root = Rotate(root);
    }

    Node Rotate(Node root) {
        if (root == null) {
            return null;
        }
        Node result = null;
        if (root.left == null && root.right == null) {
            if (root.height >= 3) {
                int temp = root.height;
                root.height = root.width;
                root.width = temp;
            }
            result = root;
        } else {
            if (root.value == '-') {
                root.value = '|';
            } else {
                root.value = '-';
            }
            result = root;
            result.left = Rotate(root.left);
            result.right = Rotate(root.right);
        }
        return result;
    }
}