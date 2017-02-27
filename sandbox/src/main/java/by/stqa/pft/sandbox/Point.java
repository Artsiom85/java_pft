package by.stqa.pft.sandbox;

/**
 * Created by Artsiom on 2/23/2017.
 */
public class Point {
  public double x;
  public double y;

public Point(double x, double y){
        this.x = x;
        this.y = y;
        }
public static double distance(Point p1, Point p2){
        return Math.sqrt(Math.sqrt(p2.x-p1.x) + Math.sqrt(p2.y-p1.y));
        }
}