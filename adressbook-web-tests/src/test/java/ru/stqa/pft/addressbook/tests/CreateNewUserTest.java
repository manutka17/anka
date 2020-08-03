package ru.stqa.pft.addressbook.tests;

import com.thoughtworks.xstream.XStream;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.AddData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class CreateNewUserTest extends TestBase {
 // @DataProvider
 // public Iterator<Object[]> validContact(){
    //List<Object[]> list = new ArrayList<Object[]>();
    //File photo = new File("src/test/resources/panda.png");
    //list.add(new Object[] { new AddData().withFerstname("FerstName 1").withLastName("LastName 1").withEmail1("Email1 1").withGroup("test1").withPhoto(photo)});
    //list.add(new Object[] { new AddData().withFerstname("FerstName 2").withLastName("LastName 2").withEmail1("Email1 2").withGroup("test1").withPhoto(photo)});
    //list.add(new Object[] { new AddData().withFerstname("FerstName 3").withLastName("LastName 3").withEmail1("Email1 3").withGroup("test1").withPhoto(photo)});
    //return  list.iterator();
 // }
  @DataProvider
  public Iterator<Object[]> validContact() throws IOException {
    //public Iterator<Object[]> validGroups() throws IOException {
      //List<Object[]> list = new ArrayList<Object[]>();
      BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/contacts.xml")));
      String xml = "";
      String line = reader.readLine();
      while (line != null) {
        xml += line;
        line = reader.readLine();
      }
      XStream xstream = new XStream();
      xstream.processAnnotations(AddData.class);
      List<AddData> contacts = (List<AddData>) xstream.fromXML(xml);
      return contacts.stream().map((g) -> new Object[]{g}).collect(Collectors.toList()).iterator();
    }


  @Test (dataProvider = "validContact")
  public void testCreateNewUser(AddData contact) throws Exception {

     // File photo = new File("src/test/resources/panda.png");
      //AddData group = new AddData().withFerstname(ferstname).withLastName(lastname).withPhoto(photo).withEmail1(email1)
             // .withGroup("test1");
      app.goTo().HomePage();
      Contacts before = app.contact().all();
      app.contact().create(contact);
      Contacts after = app.contact().all();
      assertThat(after.size(), equalTo(before.size() + 1));
      assertThat(after, equalTo(before
              .withAdded(contact.withId(after.stream().mapToInt((g)->g.getId()).max().getAsInt()))));
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


