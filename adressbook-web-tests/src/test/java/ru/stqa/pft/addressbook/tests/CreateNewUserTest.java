package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.AddData;

public class CreateNewUserTest extends TestBase {


  @Test
  public void testCreateNewUser() throws Exception {

    app.getNavigationHelper().gotoHomePage();
    app.getContactHelper().createContact(new AddData("Анна", null, null, null, null,"test1"));
    //app.getContactHelper().initCreationNewContact();
    //app.getContactHelper().fillAddPage(new AddData("Анна", null, null, null, null,"test1"),true);
   // app.getContactHelper().submitAddCreation();
    //app.getContactHelper().returntoContactPage();
  }

}

