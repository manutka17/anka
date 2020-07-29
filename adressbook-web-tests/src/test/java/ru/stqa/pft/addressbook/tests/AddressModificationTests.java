package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.AddData;
import ru.stqa.pft.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

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


    Contacts before = app.contact().all();
    AddData modifiedContact = before.iterator().next();
    AddData contact = new AddData()
            .withId(modifiedContact.getId()).withFerstname("Аннад").withMiddlename("Ивановна").withLastName("АГелябова").withNumber("+7955555555").withMail("sgdfgsdf@dgdf.ru").withGroup("test1");

    app.contact().modify(contact);
    app.goTo().HomePage();

    Contacts after = app.contact().all();
    assertEquals(after.size(), before.size());
    assertThat(after, equalTo(before.withOut(modifiedContact).withAdded(contact)));

  }


}
