package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.AddData;

public class DelitedUserTest extends TestBase {

  @Test
  public void testDelitedUser() throws Exception {
    app.getNavigationHelper().gotoHomePage();

    if (! app.getContactHelper().isTereAContact()){
      app.getContactHelper().createContact(new AddData("Анна", null, null, null, null,"test1"));

    }
    int before=app.getContactHelper().getUserCount();
    app.getContactHelper().selectFerstUser();
    app.getContactHelper().deletedSelectUser();

    app.getContactHelper().closeInput();
    int after=app.getContactHelper().getUserCount();
    Assert.assertEquals(after, before-1);

  }

}
