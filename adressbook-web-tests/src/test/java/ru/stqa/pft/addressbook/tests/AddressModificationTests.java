package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.AddData;

import java.util.Set;

public class AddressModificationTests extends TestBase {
  @BeforeMethod
  public  void ensurePreconditions(){
    app.goTo().HomePage();

    if (app.contact().list().size()==0) {
      app.contact().create(new AddData().withFerstname("Анна").withLastName("Спалкина").withGroup("test1"));
    }
  }
  @Test
  public void testAddModification() {


    Set<AddData> before = app.contact().all();
    AddData modifiedContact = before.iterator().next();
    AddData contact = new AddData()
            .withId(modifiedContact.getId()).withFerstname("Аннад").withMiddlename("Ивановна").withLastName("АГелябова").withNumber("+7955555555").withMail("sgdfgsdf@dgdf.ru").withGroup("test1");

    app.contact().modify(contact);
    app.goTo().HomePage();

    Set<AddData> after = app.contact().all();
    Assert.assertEquals(after.size(), before.size());


    before.remove(modifiedContact);
    before.add(contact);
    Assert.assertEquals(before, after);

  }


}
