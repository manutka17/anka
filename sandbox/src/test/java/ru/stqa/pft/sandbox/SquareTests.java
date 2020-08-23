package ru.stqa.pft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

public class SquareTests {
  @Test
  public void testArea() {
   Point x= new Point(1,2);
   Point y= new Point(-2,-2);
    Assert.assertEquals(x.distance(y),25);//5
  }
}
