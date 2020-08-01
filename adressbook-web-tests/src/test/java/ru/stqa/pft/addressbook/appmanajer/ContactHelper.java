package ru.stqa.pft.addressbook.appmanajer;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.AddData;
import ru.stqa.pft.addressbook.model.Contacts;

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
    type(By.name("mobile"), addData.getNumber());
    type(By.name("email"), addData.getMail());

    if (creation) {
      new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(addData.getGroup());
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
    wd.findElement(By.cssSelector("input[value='"+id+"']")).click();
  }

  public void closeInput() {
    closeAlert();
    wd.findElement(By.cssSelector("div.msgbox"));
  }

  public void initContactModification() {
    //wd.findElements(By.xpath("//img[@alt='Edit']")).get(index).click();
    click(By.xpath("//img[@alt='Edit']"));
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
    initContactModification();
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
      String[] phones = elements.get(5).getText().split("\n");

      contacts.add(new AddData().withId(id).withFerstname(ferstname).withLastName(lastname)
              .withHomePhone(phones[0])
              .withMobilePhone(phones[1])
              .withWorkPhone(phones[2]));
    }
    return contacts;

  }


  public AddData InfoFromEditForm(AddData contact) {
    initContactModificationById(contact.getId());
    String fertsname = wd.findElement(By.name("firstname")).getAttribute("value");
    String lastname = wd.findElement(By.name("lastname")).getAttribute("value");
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
              .withWorkPhone(work);


  }
  public void initContactModificationById(int id) {
    wd.findElement(By.cssSelector(String.format("a[href='edit.php?id=%s']", id))).click();
  }
}
