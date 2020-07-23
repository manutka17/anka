package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.AddData;

import java.util.HashSet;
import java.util.List;

public class CreateNewUserTest extends TestBase {


  @Test
  public void testCreateNewUser() throws Exception {
    app.getNavigationHelper().gotoHomePage();
    List<AddData> before = app.getContactHelper().getContactList();
    AddData group = new AddData("Анна", null, "Веревкина", null, null,"test1");
    app.getContactHelper().createContact(group);
    List<AddData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(), before.size()+1);

    int max = 0;
    for (AddData q : after) {
      if (q.getId() > max) {
        max = q.getId();
      }

    }
    group.setId(max);
    before.add(group);
    Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));
  }
  }


