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
      app.contact().create(new AddData().withFerstname("Анна").withLastName("Спалкина").withGroup("test1"));
    }
  }

    @Test
    public void testContactPhones(){
      app.goTo().HomePage();
      AddData contact = app.contact().all().iterator().next();
      AddData contactInfoFromEditForm = app.contact().InfoFromEditForm(contact);

      assertThat(contact.getAllPhones(), equalTo(mergePhones(contactInfoFromEditForm)));
    }

  private String mergePhones(AddData contact) {
   return Arrays.asList(contact.getHomePhone(),contact.getMobilePhone(),contact.getWorkPhone())
           .stream().filter((s)->!s.equals(""))
           .map(ContactPhoneTests::cleaned)
           .collect(Collectors.joining("\n"));

  }

  public static String cleaned(String phone){
    return  phone.replaceAll("\\s","").replaceAll("[-()]","");
    }
  }





