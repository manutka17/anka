package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.AddData;

import java.util.Comparator;
import java.util.List;

public class CreateNewUserTest extends TestBase {


  @Test
  public void testCreateNewUser() throws Exception {
    app.goTo().gotoHomePage();
    List<AddData> before = app.getContactHelper().getContactList();
    AddData group = new AddData("Анна", null, "Веревкина", null, null, "test1");
    app.getContactHelper().createContact(group);
    List<AddData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(), before.size() + 1);

    before.add(group);
    Comparator<? super AddData> byId = (q1, q2) -> Integer.compare(q1.getId(), q2.getId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);
  }
}


