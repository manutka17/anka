package ru.stqa.pft.sandbox;


public class MyFirstProgram {

    public static void main(String[] args) {
        hello("world");
        hello("world");
        hello("world");
        Square s = new Square(5);
        System.out.println(s.area());
        Rectangle r = new Rectangle(4, 6);
        System.out.println(r.area());
        Point x= new Point(101,102);
        Point y= new Point(202,203);
        System.out.println(distance(x,y));
    }

    public static void hello(String somebody) {

        System.out.println(somebody);
    }

    public static double distance(Point x, Point y) {
        return Math.sqrt((y.x-x.x) * (y.x-x.x) + (y.y-x.y) * (y.y-x.y));
    }
}