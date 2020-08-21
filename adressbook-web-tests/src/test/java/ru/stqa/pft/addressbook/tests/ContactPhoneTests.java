package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.AddData;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


public class ContactPhoneTests extends TestBase {
  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().HomePage();

    if (app.contact().list().size() == 0) {
      app.contact().create(new AddData()
              .withFerstname("Анна")
              .withLastName("Спалкина")
              .withAddress("Желябова 55")
              .withHomePhone("567567")
              .withMobilePhone("8888")
              .withWorkPhone("55555")
              .withEmail1("anuta@ghvhg.ri")
              .withEmail2("anechka@dnjdnj.ed")
              .withEmail3("prosto@jdnjdc.rfj"));
    }
  }

    @Test
    public void testContactPhones(){
      app.goTo().HomePage();
      AddData contact = app.contact().all().iterator().next();
      AddData contactInfoFromEditForm = app.contact().InfoFromEditForm(contact);

      assertThat(contact.getAllPhones(), equalTo(mergePhones(contactInfoFromEditForm)));
      assertThat(contact.getAllEmail(), equalTo(mergePhones1(contactInfoFromEditForm)));
      assertThat(contact.getAddress(),equalTo(contactInfoFromEditForm.getAddress()));
    }

  private String mergePhones(AddData contact) {
   return Arrays.asList(contact.getHomePhone(),contact.getMobilePhone(),contact.getWorkPhone())
           .stream().filter((s)->!s.equals(""))
           .map(ContactPhoneTests::cleaned)
           .collect(Collectors.joining("\n"));

  }
  private String mergePhones1(AddData contact) {
    return Arrays.asList(contact.getEmail1(),contact.getEmail2(),contact.getEmail3())
            .stream().filter((s)->!s.equals(""))
            .map(ContactPhoneTests::cleaned)
            .collect(Collectors.joining("\n"));

  }

  public static String cleaned(String phone){
    return  phone.replaceAll("\\s","").replaceAll("[-()]","");
    }
  }





