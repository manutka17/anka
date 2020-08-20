package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.AddData;
import ru.stqa.pft.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class DelitedUserTest extends TestBase {
  @BeforeMethod
  public void ensurePreconditions() {
    if(app.db().contacts().size()==0){
      app.goTo().HomePage();
      app.contact().create(new AddData().withFerstname("Анна").withGroup( "test1"));

    }
  }
    @Test
    public void testDelitedUser() {

      Contacts before = app.db().contacts();
      AddData deletedContact = before.iterator().next();

      app.contact().delete(deletedContact);
      app.goTo().HomePage();
      Contacts after = app.db().contacts();
      assertEquals(after.size(), before.size() - 1);

      assertThat(after, equalTo(before.withOut(deletedContact)));
    }
}
