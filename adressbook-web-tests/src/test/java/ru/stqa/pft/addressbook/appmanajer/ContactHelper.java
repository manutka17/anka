package ru.stqa.pft.addressbook.appmanajer;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.AddData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.ArrayList;
import java.util.List;


public class ContactHelper extends HelperBase {


  public ContactHelper(WebDriver wd) {
    super(wd);
  }

  public void returntoContactPage() {
    click(By.linkText("home page"));
  }


  public void submitAddCreation() {
    click(By.xpath("(//input[@name='submit'])[2]"));
  }

  public void fillAddPage(AddData addData, boolean creation) {
    type(By.name("firstname"), addData.getFerstname());
    type(By.name("middlename"), addData.getMiddlename());
    type(By.name("lastname"), addData.getLastName());
    //attach(By.name("photo"), addData.getPhoto());
    type(By.name("address"), addData.getAddress());
    type(By.name("home"), addData.getHomePhone());
    type(By.name("mobile"), addData.getMobilePhone());
    type(By.name("work"), addData.getWorkPhone());
    type(By.name("email"), addData.getEmail1());
    type(By.name("email2"), addData.getEmail2());
    type(By.name("email3"), addData.getEmail3());





    if (creation) {
      //if (AddData.getGroups().size()>0)
     //new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(addData.getGroup());
    } else {
      Assert.assertFalse(isElementPresent1(By.name("new_group")));
    }
  }

  public void deletedSelectUser() {
    click(By.xpath("//input[@value='Delete']"));
  }

  public void selectFerstUser(int index) {
    // click(By.linkText("home"));
    wd.findElements(By.name("selected[]")).get(index).click();
    //wd.findElements(By.xpath("//table[@id='maintable']/tbody/tr[2]")).get(index).click();
    //wd.findElements(By.xpath("(//td[@class='center']//input)[1]")).get(index).click();


  }
  public void selectFerstUserById(int id) {
    wd.findElement(By.cssSelector("input[id='"+id+"']")).click();
  }

  public void closeInput() {
    closeAlert();
    wd.findElement(By.cssSelector("div.msgbox"));
  }

  public void initContactModification(int id) {
    //wd.findElements(By.xpath("//img[@alt='Edit']")).get(index).click();
    wd.findElement(By.cssSelector(String.format("a[href='edit.php?id=%s']",id))).click();
  }


  public void initCreationNewContact() {
    click(By.linkText("add new"));
  }

  public void submitAddModification() {
    click(By.name("update"));
  }

  public void create(AddData contact) {
    initCreationNewContact();
    fillAddPage(contact, true);
    submitAddCreation();
    returntoContactPage();

  }
  public void modify(AddData contact) {


    selectFerstUserById(contact.getId());
    initContactModification(contact.getId());
    fillAddPage(contact, false);
    submitAddModification();

  }
  public void delete(int index) {
    selectFerstUser(index);
    deletedSelectUser();
    closeAlert();

  }
  public void delete(AddData contact) {
    selectFerstUserById(contact.getId());
    deletedSelectUser();
    closeAlert();
  }
  public boolean isTereAContact() {
    return isElementPresent1(By.xpath("//img[@alt='Edit']"));
  }

  public int getUserCount() {
    return wd.findElements(By.xpath("//img[@alt='Edit']")).size();
  }

  public List<AddData> list() {
    List<AddData> contacts = new ArrayList();
    List<WebElement> tds = wd.findElements(By.xpath("//tr[@name='entry']"));
    for (WebElement element : tds) {
      List<WebElement> elements = element.findElements(By.tagName("td"));
      String ferstname = elements.get(2).getText();
      String lastname = elements.get(1).getText();
      Integer id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
      contacts.add(new AddData().withId(id).withFerstname(ferstname).withLastName(lastname));
    }
    return contacts;

  }
  public Contacts all() {
    Contacts contacts = new Contacts();
    List<WebElement> tds = wd.findElements(By.xpath("//tr[@name='entry']"));
    for (WebElement element : tds) {
      List<WebElement> elements = element.findElements(By.tagName("td"));
      Integer id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
      String ferstname = elements.get(2).getText();
      String lastname = elements.get(1).getText();
      String address = elements.get(3).getText();
      String allEmails = elements.get(4).getText();
      String allPhones = elements.get(5).getText();



      contacts.add(new AddData()
              .withId(id)
              .withFerstname(ferstname)
              .withLastName(lastname)
              .withAddress(address)
              .withAllEmail(allEmails)
              .withAllPhones(allPhones));
    }
    return contacts;

  }


  public AddData InfoFromEditForm(AddData contact) {
    initContactModificationById(contact.getId());
    String fertsname = wd.findElement(By.name("firstname")).getAttribute("value");
    String lastname = wd.findElement(By.name("lastname")).getAttribute("value");
    String email1 = wd.findElement(By.name("email")).getAttribute("value");
    String email2 = wd.findElement(By.name("email2")).getAttribute("value");
    String email3 = wd.findElement(By.name("email3")).getAttribute("value");
    String home = wd.findElement(By.name("home")).getAttribute("value");
    String mobile = wd.findElement(By.name("mobile")).getAttribute("value");
    String work = wd.findElement(By.name("work")).getAttribute("value");
    wd.navigate().back();
      return new AddData()
              .withId(contact.getId())
              .withFerstname(fertsname)
              .withLastName(lastname)
              .withHomePhone(home)
              .withMobilePhone(mobile)
              .withWorkPhone(work)
              .withEmail1(email1)
              .withEmail2(email2)
              .withEmail3(email3)
              .withAddress(contact.getAddress());


  }
  public void initContactModificationById(int id) {
    wd.findElement(By.cssSelector(String.format("a[href='edit.php?id=%s']", id))).click();
  }

  public void addToGroup(AddData contact, GroupData group) {
    selectFerstUserById(contact.getId());
    new Select(wd.findElement(By.name("to_group"))).selectByVisibleText(group.getName());
    click(By.name("add"));
  }

  public void removeFromGroup(AddData contact, GroupData group) {
    new Select(wd.findElement(By.name("group"))).selectByVisibleText(group.getName());
    selectFerstUserById(contact.getId());
    click(By.name("remove"));



  }
}
