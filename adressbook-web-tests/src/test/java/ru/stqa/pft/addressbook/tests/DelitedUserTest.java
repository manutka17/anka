package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.AddData;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.List;

public class DelitedUserTest extends TestBase {

  @Test
  public void testDelitedUser() throws Exception {
    app.getNavigationHelper().gotoHomePage();

    if (! app.getContactHelper().isTereAContact()){
      app.getContactHelper().createContact(new AddData("Анна", null, null, null, null,"test1"));

    }
    //int before=app.getContactHelper().getUserCount();
    List<GroupData> before = app.getGroupHelper().getGroupList();

    app.getContactHelper().selectFerstUser(before.size()-1);
    app.getContactHelper().deletedSelectUser();

    app.getContactHelper().closeAlert();
    app.getNavigationHelper().gotoHomePage();
    List<GroupData> after = app.getGroupHelper().getGroupList();
    //int after=app.getContactHelper().getUserCount();
    Assert.assertEquals(after.size(), before.size()-1);

  }

}
