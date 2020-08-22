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

public class AddContactToGroup extends TestBase {
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
    Contacts contactsInAllGroups = new Contacts();
    for (AddData contact : contacts) {
      //Проверить состоит ли взятый контакт во всех группах
      if (groups.size() == contact.getGroups().size()) {
        //Если состоит во всех, то перенести его в список контактов которые состоят во всех группах
        contactsInAllGroups.add(contact);
      }
    }
    if(contacts.size()==contactsInAllGroups.size()){
      app.contact().create(new AddData().withFerstname("Анна")
              .withLastName("Спалкина").withHomePhone("6567").withEmail1("dffd@fdgf.tr"));
    }
  }
  @Test
  public  void addContactToGroup(){
    app.goTo().HomePage();
    // Получить весь список групп
    Groups groups =  app.db().groups();
    Contacts contacts = app.db().contacts();
    Contacts contactsInAllGroups = new Contacts();
    for (AddData contact : contacts) {
      //Проверить состоит ли взятый контакт во всех группах
      if (groups.size() == contact.getGroups().size()) {
        //Если состоит во всех, то перенести его в список контактов которые состоят во всех группах
        contactsInAllGroups.add(contact);
      }
    }
    //Отсеять все контакты которые состоят во всех группах (выбросить)
    contacts.removeAll(contactsInAllGroups);
    //Взять случайный контакт из списка контактов, которые состоят не во всех группах
    AddData contact=contacts.iterator().next();
    // Из списка всех групп выбросить все группы в которых состоит контакт
    groups.removeAll(contact.getGroups());
    //получить список всех групп контакта
    Groups beforeContactGroups= contact.getGroups();
    //Взять случайную группу из списка групп, в которых контакт не состоит
     GroupData group=groups.iterator().next();
     //ДОбавить контакт в эту группу
     app.contact().addToGroup(contact,group);
     //Получить список всех групп контакта
    Contacts contactsAfter = app.db().contacts();
    //получили список из одного контакта совпадающего с нашим контактом
    List<AddData> collect = contactsAfter.stream().filter(c -> c.getId() == contact.getId()).collect(Collectors.toList());
    //В старый список на 6 шаге добавить группу конкретную в которую контакт был добавлен
    beforeContactGroups.add(group);
    // Сравнить список 11 и 12
    assertThat(beforeContactGroups, equalTo(collect.iterator().next().getGroups()));




  }
}
