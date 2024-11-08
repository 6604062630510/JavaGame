import java.awt.Rectangle;
import java.util.ArrayList;

public class PoolData {
    public static ArrayList<Rectangle> getRectangles() {
        ArrayList<Rectangle> pool = new ArrayList<>();

        pool.add(new Rectangle(661,549, 760-661, 630-549));
        pool.add(new Rectangle(722,529, 800-722, 590-529));
        pool.add(new Rectangle(780,510, 877-780, 554-510));
        pool.add(new Rectangle(858,492, 917-858, 532-492));
        pool.add(new Rectangle(895,508, 936-895, 556-508));

        return pool;
    }
}
