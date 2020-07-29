package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.AddData;

import java.util.Comparator;
import java.util.List;

public class AddressModificationTests extends TestBase {
  @Test
  public void testAddModification() {
    app.goTo().gotoHomePage();

    if (!app.getContactHelper().isTereAContact()) {
      app.getContactHelper().createContact(new AddData("Анна", null, "Спалкина", null, null, "test1"));
    }

    List<AddData> before = app.getContactHelper().getContactList();
    app.getContactHelper().initContactModification(before.size() - 1);
    AddData contact = new AddData(before.get(before.size() - 1).getId(), "Аннад", "Ивановна", "АГелябова", "+7955555555", "sgdfgsdf@dgdf.ru", "test1");
    app.getContactHelper().fillAddPage(contact, false);
    app.getContactHelper().submitAddModification();

    app.goTo().gotoHomePage();
    List<AddData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(), before.size());


    before.remove(before.size() - 1);
    before.add(contact);
    Comparator<? super AddData> byId = (q1, q2) -> Integer.compare(q1.getId(), q2.getId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);

  }
}
