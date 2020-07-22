package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.AddData;

import java.util.List;

public class CreateNewUserTest extends TestBase {


  @Test
  public void testCreateNewUser() throws Exception {
    app.getNavigationHelper().gotoHomePage();
    //int before=app.getContactHelper().getUserCount();
    List<AddData> before = app.getContactHelper().getContactList();
    app.getContactHelper().createContact(new AddData("Анна", null, null, null, null,"test1"));
    //int after=app.getContactHelper().getUserCount();
    List<AddData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(), before.size()+1);
  }

}

