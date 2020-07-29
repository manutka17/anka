package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.AddData;

import java.util.Set;

public class CreateNewUserTest extends TestBase {


  @Test
  public void testCreateNewUser() throws Exception {
    app.goTo().HomePage();
    Set<AddData> before = app.contact().all();
    AddData group = new AddData().withFerstname("Анна").withLastName("Веревкина").withGroup("test1");
    app.contact().create(group);
    Set<AddData> after = app.contact().all();
    Assert.assertEquals(after.size(), before.size() + 1);

    group.withId(after.stream().mapToInt((g)->g.getId()).max().getAsInt());
    before.add(group);
    Assert.assertEquals(before, after);
  }
}


