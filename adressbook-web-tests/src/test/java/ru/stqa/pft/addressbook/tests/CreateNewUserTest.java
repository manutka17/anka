package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.AddData;

public class CreateNewUserTest extends TestBase {


  @Test
  public void testCreateNewUser() throws Exception {

    app.getNavigationHelper().gotoHomePage();
    app.getContactHelper().initCreationNewContact();
    app.getContactHelper().fillAddPage(new AddData("Анна", "Ивановна", "Гелябова", "+7955555555", "sgdfgsdf@dgdf.ru"));
    app.getContactHelper().submitAddCreation();
    app.getNavigationHelper().gotoHomePage();
  }

}

