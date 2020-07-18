package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.AddData;

public class DelitedUserTest extends TestBase {

  @Test
  public void testDelitedUser() throws Exception {
    if (! app.getContactHelper().isTereAContact()){
      app.getContactHelper().createContact(new AddData("Анна", null, null, null, null,"test1"));
    }


    app.getContactHelper().selectFerstUser();
    app.getContactHelper().deletedSelectUser();
    app.getContactHelper().closeInput();
  }

}
