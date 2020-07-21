package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.AddData;

public class CreateNewUserTest extends TestBase {


  @Test
  public void testCreateNewUser() throws Exception {
    app.getNavigationHelper().gotoHomePage();
    int before=app.getContactHelper().getUserCount();
    app.getContactHelper().createContact(new AddData("Анна", null, null, null, null,"test1"));
    int after=app.getContactHelper().getUserCount();
    Assert.assertEquals(after, before+1);
  }

}

