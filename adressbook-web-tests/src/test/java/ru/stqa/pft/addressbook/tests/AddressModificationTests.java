package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.AddData;

import java.util.Comparator;
import java.util.List;

public class AddressModificationTests extends TestBase {
  @BeforeMethod
  public  void ensurePreconditions(){
    app.goTo().HomePage();

    if (app.contact().list().size()==0) {
      app.contact().create(new AddData("Анна", null, "Спалкина", null, null, "test1"));
    }
  }
  @Test
  public void testAddModification() {


    List<AddData> before = app.contact().list();
    int index= before.size() - 1;
    AddData contact = new AddData(before.get(index).getId(), "Аннад", "Ивановна", "АГелябова", "+7955555555", "sgdfgsdf@dgdf.ru", "test1");

    app.contact().modify(index, contact);
    app.goTo().HomePage();

    List<AddData> after = app.contact().list();
    Assert.assertEquals(after.size(), before.size());


    before.remove(before.size() - 1);
    before.add(contact);
    Comparator<? super AddData> byId = (q1, q2) -> Integer.compare(q1.getId(), q2.getId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);

  }


}
