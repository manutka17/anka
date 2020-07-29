package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.AddData;

import java.util.Set;

public class DelitedUserTest extends TestBase {
  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().HomePage();

    if (app.contact().list().size()==0) {
      app.contact().create(new AddData().withFerstname("Анна").withGroup( "test1"));

    }
  }
    @Test
    public void testDelitedUser() {

      Set<AddData> before = app.contact().all();
      AddData deletedContact = before.iterator().next();

      app.contact().delete(deletedContact);
      app.goTo().HomePage();
      Set<AddData> after = app.contact().all();
      Assert.assertEquals(after.size(), before.size() - 1);

      before.remove(deletedContact);
      Assert.assertEquals(before, after);
    }


}
