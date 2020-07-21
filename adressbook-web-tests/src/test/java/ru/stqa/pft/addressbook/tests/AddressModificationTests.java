package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.AddData;

public class AddressModificationTests extends TestBase {
  @Test
  public void testAddModification(){
    app.getNavigationHelper().gotoHomePage();

    if (! app.getContactHelper().isTereAContact()){
      app.getContactHelper().createContact(new AddData("Анна", null, null, null, null,"test1"));
    }
    int before=app.getContactHelper().getUserCount();
    app.getContactHelper().initContactModification(before-1);
    app.getContactHelper().fillAddPage(new AddData("Аннад", "Ивановна", "Гелябова", "+7955555555", "sgdfgsdf@dgdf.ru",null), false);
    app.getContactHelper().submitAddModification();

    app.getNavigationHelper().gotoHomePage();
    int after=app.getContactHelper().getUserCount();
    Assert.assertEquals(after, before);

  }
}
