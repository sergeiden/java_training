package ru.stga.pft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.sandbox.Point;

/**
 * Created by 1 on 20.03.2017.
 */
public class PointsTests {

  @Test
  public void testDistance1() {
    Point p1 = new Point(5,7);
    Point p2 = new Point(5,6);
    Assert.assertEquals(p1.distance(p2), 1.0);
  }

  @Test
  public void testDistance2() {
    Point p1 = new Point(1,1);
    Assert.assertEquals(p1.distance(p1), 0.0);
  }

  @Test
  public void testDistance3() {
    Point p1 = new Point(0,1);
    Point p2 = new Point(0,0);
    Assert.assertEquals(p1.distance(p2), 1.0);
  }

  @Test
  public void testDistance4() {
    Point p1 = new Point(-1,-1);
    Point p2 = new Point(3,3);
    Assert.assertEquals(p1.distance(p2), 5.656854249492381);
  }
}
