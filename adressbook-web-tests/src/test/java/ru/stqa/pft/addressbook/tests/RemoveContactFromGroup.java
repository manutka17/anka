package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.AddData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class RemoveContactFromGroup extends TestBase {
  @BeforeMethod
  public  void ensurePreconditions(){
    if(app.db().contacts().size()==0){
      app.goTo().HomePage();
      app.contact().create(new AddData().withFerstname("Анна").withLastName("Спалкина").withHomePhone("6567").withEmail1("dffd@fdgf.tr"));
    }
    if(app.db().groups().size()==0) {
      app.goTo().groupPage();
      app.group().create(new GroupData().withName("test1"));
    }
    Groups groups =  app.db().groups();
    Contacts contacts = app.db().contacts();
    Contacts contactsWithoutGroups = new Contacts();

    for (AddData contact : contacts) {
      //найти контакты, которые не состоят в группах
      if (groups.size() == 0) {
        //Найденные контакты переместить в список контактов без групп
        contactsWithoutGroups.add(contact);
      }
    }
    if(contacts.size()==contactsWithoutGroups.size()){
      // к случайному контакту добавили случайную группу
      app.contact().addToGroup(contacts.iterator().next(),groups.iterator().next());
    }
  }
  @Test
  public  void addContactToGroup(){
    app.goTo().HomePage();
    // Получить весь список групп
    Groups groups =  app.db().groups();
    Contacts contacts = app.db().contacts();
    Contacts contactsWithoutGroups = new Contacts();
    for (AddData contact : contacts) {
      //найти контакты которые не состоят  в группах
      if (contact.getGroups().size()==0) {

        //добавить их в список контактов, не состоящих в группах
        contactsWithoutGroups.add(contact);

      }
    }
    //Отсеять все контакты которые НЕ состоят в группах (выбросить)
    contacts.removeAll(contactsWithoutGroups);
    //Взять случайный контакт из списка контактов, которые состоят в группах
    AddData contact=contacts.iterator().next();
    //получить список всех групп контакта
    Groups beforeContactGroups= contact.getGroups();
    //Взять случайную группу из списка групп, в которых контакт состоит
    GroupData group=beforeContactGroups.iterator().next();
    //ДОбавить контакт в эту группу
    app.contact().removeFromGroup(contact,group);
    //Получить список всех групп контакта
    Contacts contactsAfter = app.db().contacts();
    //получили список из одного контакта совпадающего с нашим контактом
    List<AddData> collect = contactsAfter.stream().filter(c -> c.getId() == contact.getId()).collect(Collectors.toList());
    //В старый список на 6 шаге добавить группу конкретную в которую контакт был добавлен
    beforeContactGroups.remove(group);
    // Сравнить список 11 и 12
    assertThat(beforeContactGroups, equalTo(collect.iterator().next().getGroups()));




  }
}
