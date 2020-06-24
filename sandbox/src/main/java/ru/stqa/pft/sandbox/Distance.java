package ru.stqa.pft.sandbox;

public class Distance {
  public static void main(String[] args) {
    Point x= new Point(101,102);
    Point y= new Point(202,203);
    System.out.println(distance(x,y));

    System.out.println(x.distance(y));
  }
  public static double distance(Point x, Point y) {
    return Math.sqrt((y.x-x.x) * (y.x-x.x) + (y.y-x.y) * (y.y-x.y));
  }
}
