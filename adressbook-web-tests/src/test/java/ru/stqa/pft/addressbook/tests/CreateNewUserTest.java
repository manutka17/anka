package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.AddData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.io.File;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class CreateNewUserTest extends TestBase {


  @Test
  public void testCreateNewUser() throws Exception {
    app.goTo().HomePage();
    Contacts before = app.contact().all();
    File photo = new File("src/test/resources/panda.png");
    AddData group = new AddData().withFerstname("Анна").withLastName("Веревкина").withPhoto(photo)
            .withGroup("test1");
    app.contact().create(group);
    Contacts after = app.contact().all();
    assertThat(after.size(), equalTo(before.size() + 1));


    assertThat(after, equalTo(before
            .withAdded(group.withId(after.stream().mapToInt((g)->g.getId()).max().getAsInt()))));
  }
  @Test (enabled = false)
  public void  testCurrentDir (){
    File currentDir = new File(".");
    System.out.println(currentDir.getAbsolutePath());
    File photo = new File("src/test/resources/panda.png");
    System.out.println(photo.getAbsolutePath());
    System.out.println(photo.exists());
  }
}


