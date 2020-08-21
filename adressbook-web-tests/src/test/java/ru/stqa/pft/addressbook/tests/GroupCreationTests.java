package ru.stqa.pft.addressbook.tests;

import com.thoughtworks.xstream.XStream;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GroupCreationTests extends TestBase {
  //@DataProvider
  //public Iterator<Object[]>validGroups(){
    //List<Object[]> list = new ArrayList<Object[]>();
    //list.add(new Object[] {new GroupData().withName("test1").withHeader("header 1").withFooter("footer 1")});
    //list.add(new Object[] {new GroupData().withName("test2").withHeader("header 2").withFooter("footer 2")});
    //list.add(new Object[] {new GroupData().withName("test3").withHeader("header 3").withFooter("footer 3")});
    //return list.iterator();
  //}
  @DataProvider
  public Iterator<Object[]> validGroups() throws IOException {
    //List<Object[]> list = new ArrayList<Object[]>();
    try (BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/groups.xml")))){
      String xml = "";
      String line = reader.readLine();
      while (line != null) {
        xml += line;
        line = reader.readLine();
      }
      XStream xstream = new XStream();
      xstream.processAnnotations(GroupData.class);
      List<GroupData> groups = (List<GroupData>) xstream.fromXML(xml);
      return groups.stream().map((g) -> new Object[]{g}).collect(Collectors.toList()).iterator();
    }

  }



  @Test (dataProvider = "validGroups")
  public void testGroupCreation(GroupData group) throws Exception {
      app.goTo().groupPage();
      Groups before = app.db().groups();
      app.group().create(group);
      assertThat(app.group().count(), equalTo(before.size() + 1));
      Groups after = app.db().groups();;
      assertThat(after, equalTo(before
              .withAdded(group.withId(after.stream().mapToInt((g)->g.getId()).max().getAsInt()))));

    }

  @Test
  public void testBadGroupCreation() throws Exception {
    app.goTo().groupPage();
    Groups before = app.db().groups();
    GroupData group = new GroupData().withName("test'");
    app.group().create(group);
    assertThat(app.group().count(), equalTo(before.size()));
    Groups after = app.db().groups();



    assertThat(after, equalTo(before));
    verifyGroupListInUI();
  }
}
