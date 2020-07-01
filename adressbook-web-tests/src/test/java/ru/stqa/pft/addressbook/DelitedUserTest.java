package ru.stqa.pft.addressbook;

import org.testng.annotations.Test;

public class DelitedUserTest extends TestBase {

  @Test
  public void testDelitedUser() throws Exception {

    selectFerstUser();
    deletedSelectUser();
    wd.switchTo().alert().accept();
    //returntoAddPage();
  }

}
