package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.AddData;

public class CreateNewUserTest extends TestBase {


  @Test
  public void testCreateNewUser() throws Exception {

    app.gotoAddNewPage();
    app.fillAddPage(new AddData("Анна", "Ивановна", "Гелябова", "+7955555555", "sgdfgsdf@dgdf.ru"));
    app.submitAddCreation();
    app.returntoAddPage();
  }

}

