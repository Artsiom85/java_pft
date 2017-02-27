package by.stqa.pft.sandbox;

public class MyFirstProgram {
  public static void main(String[] args){
  Point p1 = new Point(1, 2);
  Point p2 = new Point(3, 4);
  System.out.println("Расстояние между координатами x, равные (" + p1.x + p1.y + ") и координатой y, равной (" + p2.x + p2.y + ") равно " + Point.distance(p1, p2));
}
}