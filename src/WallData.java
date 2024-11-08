import java.awt.Rectangle;
import java.util.ArrayList;

public class WallData {
    public static ArrayList<Rectangle> getRectangles(int width, int height) {
        ArrayList<Rectangle> wall = new ArrayList<>();

        wall.add(new Rectangle(0, 0, 60, 172));
        wall.add(new Rectangle(0, 0, 199, 58));
        wall.add(new Rectangle(0, 0, 475, 39));
        wall.add(new Rectangle(475, 0, 20, 20));
        wall.add(new Rectangle(0, 297, 60, 544 - 297));
        wall.add(new Rectangle(53, 358, 79 - 53, 391 - 358));
        wall.add(new Rectangle(65, 394, 97 - 65, 422 - 394));
        wall.add(new Rectangle(83, 412, 119 - 83, 523 - 412));
        wall.add(new Rectangle(47, 512, 100 - 47, 542 - 512 + 1));
        wall.add(new Rectangle(0, 543, 40, 601 - 543));
        wall.add(new Rectangle(0, 543, 20, 648 - 543));
        wall.add(new Rectangle(0, 640, 60, height - 640));
        wall.add(new Rectangle(0, 658, 79, height - 568));
        wall.add(new Rectangle(0, 676, 250, height - 676));
        wall.add(new Rectangle(216, 659, 259 - 216, height - 659));
        wall.add(new Rectangle(236, 640, 308 - 216, height - 640));
        wall.add(new Rectangle(295, 620, 375 - 295, height - 620));
        wall.add(new Rectangle(353, 563, 435 - 353, height - 563));
        wall.add(new Rectangle(412, 581, 495 - 412, height - 581));
        wall.add(new Rectangle(464, 603, 514 - 464, height - 603));
        wall.add(new Rectangle(484, 620, 535 - 484, height - 620));
        wall.add(new Rectangle(504, 639, 593 - 504, height - 639));
        wall.add(new Rectangle(550, 657, 672 - 550, height - 657));
        wall.add(new Rectangle(668, 632, width - 668, height - 632));
        wall.add(new Rectangle(935, 486, width - 935, height - 486));
        wall.add(new Rectangle(965, 383, width - 965, height - 383));
        wall.add(new Rectangle(946, 350, width - 946, 50));
        wall.add(new Rectangle(925, 340, width - 925, 381 - 340));
        wall.add(new Rectangle(906, 388, width - 906, 362 - 388));
        wall.add(new Rectangle(906, 288, width - 906, 362 - 288));
        wall.add(new Rectangle(945, 130, width - 945, 295 - 130));
        wall.add(new Rectangle(925, 120, width - 925, 172 - 120));
        wall.add(new Rectangle(906, 35, width - 906, 153 - 35));
        wall.add(new Rectangle(827, 0, width - 827, 58));
        wall.add(new Rectangle(768, 0, width - 768, 40));
        wall.add(new Rectangle(748, 0, width - 768, 20));

        return wall;
    }
}
