package ru.stqa.pft.addressbook;

import org.testng.annotations.Test;

public class CreateNewUserTest extends TestBase {


  @Test
  public void testCreateNewUser() throws Exception {

    gotoAddNewPage();
    fillAddPage(new AddData("Анна", "Ивановна", "Гелябова", "+7955555555", "sgdfgsdf@dgdf.ru"));
    submitAddCreation();
    returntoAddPage();
  }

}

