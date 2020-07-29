package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.AddData;

import java.util.List;

public class DelitedUserTest extends TestBase {
  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().HomePage();

    if (app.contact().list().size()==0) {
      app.contact().create(new AddData("Анна", null, null, null, null, "test1"));

    }
  }
    @Test
    public void testDelitedUser() {

      List<AddData> before = app.contact().list();
      int index = before.size() - 1;

      app.contact().delete(index);
      app.goTo().HomePage();
      List<AddData> after = app.contact().list();
      Assert.assertEquals(after.size(), before.size() - 1);

      before.remove(index);
      Assert.assertEquals(before, after);
    }


}
