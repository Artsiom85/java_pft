package by.stqa.pft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by Artsiom on 3/1/2017.
 */
public class PointTests {

  @Test
  public void distance(){
    Point p1 = new Point(0.0, 1.0);
    Point p2 = new Point(0.0, 1.0);
    Assert.assertEquals(Point.distance(p1, p2), 0.0);
  }
  @Test
  public void distance2(){
    Point p1 = new Point(10.0, 24.0);
    Point p2 = new Point(24.0, 10.0);
    Assert.assertEquals(Point.distance(p1, p2), 19.79898987322333);
  }

  @Test
  public void distance3(){
    Point p1 = new Point(5.0 , -6.0);
    Point p2 = new Point(-5.0, 6.0);
    Assert.assertEquals(Point.distance(p1, p2), 15.620499351813308);

      }
}
