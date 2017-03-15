package ru.stqa.pft.sandbox;

/**
 * Created by 1 on 15.03.2017.
 */
public class TwoPointsDistance {

  public static void main(String[] args) {
    Point p1 = new Point();
    p1.x = 2;
    p1.y = 2;
    Point p2 = new Point();
    p2.x = 3;
    p2.y = 3;
    System.out.println("Расстояние между точками p1 и p2 равно " + distance (p1, p2));
  }

  public static double distance(Point p1, Point p2) {
    double dx = p2.x - p1.x;
    double dy = p2.y - p1.y;
    return Math.sqrt(dx*dx+dy*dy);
  }
}
