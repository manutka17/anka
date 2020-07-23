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
    AddData group = new AddData("Анна", null, "Веревкина", null, null, "test1");
    app.getContactHelper().createContact(group);
    List<AddData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(), before.size() + 1);

    group.setId(after.stream().max((o1, o2) -> Integer.compare(o1.getId(), o2.getId())).get().getId());
    before.add(group);
    Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));
  }
}


