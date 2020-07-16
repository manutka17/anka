package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.AddData;

public class AddressModificationTests extends TestBase {
  @Test
  public void testAddModification(){
    app.getNavigationHelper().gotoHomePage();
    app.getContactHelper().initContactModification();
    app.getContactHelper().fillAddPage(new AddData("Аннад", "Ивановна", "Гелябова", "+7955555555", "sgdfgsdf@dgdf.ru",null), false);
    app.getContactHelper().submitAddModification();
    app.getNavigationHelper().gotoHomePage();
  }
}
