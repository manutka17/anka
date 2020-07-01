package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;

public class DelitedUserTest extends TestBase {

  @Test
  public void testDelitedUser() throws Exception {

    app.selectFerstUser();
    app.deletedSelectUser();
    app.closeInput();
    //returntoAddPage();
  }

}
