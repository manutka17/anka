package ru.stqa.pft.addressbook.appmanajer;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.AddData;

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

  public void closeInput() {
    closeAlert();
    wd.findElement(By.cssSelector("div.msgbox"));
  }

  public void initContactModification(int index) {
    wd.findElements(By.xpath("//img[@alt='Edit']")).get(index).click();
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
  public void modify(int index, AddData contact) {
    initContactModification(index);
    fillAddPage(contact, false);
    submitAddModification();

  }
  public void delete(int index) {
    selectFerstUser(index);
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
}
