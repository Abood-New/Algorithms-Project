import java.util.ArrayList;
import java.util.List;
 class Rectangle {
    public int width;
    public int height;

    public Rectangle(int width, int height) {
        this.width = width;
        this.height = height;
    }
}
public class Packer {
    private Rectangle bigRect;
    private boolean solutionFound;

    public Packer(int width, int height) {
        this.bigRect = new Rectangle(width, height);
        this.solutionFound = false;
    }

    public boolean canFill(List<Rectangle> rectangles) {
        int totalSmallArea = rectangles.stream()
                .mapToInt(rect -> rect.width * rect.height)
                .sum();
        return totalSmallArea == this.bigRect.width * this.bigRect.height;
    }

    public boolean fit(List<Rectangle> rectangles) {
        if (!this.canFill(rectangles)) {
            System.out.println("The total area of small rectangles does not match the area of the big rectangle.");
            return false;
        }
        rectangles.sort((a, b) -> Integer.compare(b.height, a.height)); // Sort by height for better packing
        this.placeNext(rectangles, 0, new ArrayList<>());
        return this.solutionFound;
    }

    private void placeNext(List<Rectangle> rectangles, int index, List<Placement> placed) {
        if (this.solutionFound) {
            return;
        }

        if (index == rectangles.size()) {
            this.solutionFound = true;
            return;
        }

        Rectangle rect = rectangles.get(index);
        Rectangle[] orientations = {
                new Rectangle(rect.width, rect.height),
                new Rectangle(rect.height, rect.width)
        };

        for (Rectangle orientation : orientations) {
            for (int x = 0; x <= this.bigRect.width - orientation.width; x++) {
                for (int y = 0; y <= this.bigRect.height - orientation.height; y++) {
                    if (this.canPlace(orientation, x, y, placed)) {
                        placed.add(new Placement(orientation, x, y));
                        this.placeNext(rectangles, index + 1, placed);
                        if (this.solutionFound) {
                            return;
                        }
                        placed.remove(placed.size() - 1);
                    }
                }
            }
        }
    }

    private boolean canPlace(Rectangle rect, int x, int y, List<Placement> placed) {
        for (Placement p : placed) {
            if (this.rectsOverlap(p.rect, p.x, p.y, rect, x, y)) {
                return false;
            }
        }
        return true;
    }

    private boolean rectsOverlap(Rectangle rect1, int x1, int y1, Rectangle rect2, int x2, int y2) {
        return !(
                x1 + rect1.width <= x2 ||
                x2 + rect2.width <= x1 ||
                y1 + rect1.height <= y2 ||
                y2 + rect2.height <= y1
        );
    }

    public static class Placement {
        public Rectangle rect;
        public int x;
        public int y;

        public Placement(Rectangle rect, int x, int y) {
            this.rect = rect;
            this.x = x;
            this.y = y;
        }
    }
}
