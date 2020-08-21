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
    if(app.db().contacts().size()==0){
      app.goTo().HomePage();
      app.contact().create(new AddData().withFerstname("Анна").withLastName("Спалкина").withGroup("test1").withHomePhone("6567").withEmail1("dffd@fdgf.tr"));
    }
  }
  @Test
  public void testAddModification() {


    Contacts before = app.db().contacts();
    AddData modifiedContact = before.iterator().next();
    AddData contact = new AddData()
            .withId(modifiedContact.getId()).withFerstname("Аннад").withMiddlename("Ивановна").withLastName("АГелябова").withHomePhone("+7955555555").withEmail1("sgdfgsdf@dgdf.ru").withGroup("test1");
    app.goTo().HomePage();
    app.contact().modify(contact);
    app.goTo().HomePage();

    Contacts after = app.db().contacts();
    assertEquals(after.size(), before.size());
    assertThat(after, equalTo(before.withOut(modifiedContact).withAdded(contact)));
    verifyContactListInUI();

  }


}
