package ru.stqa.pft.addressbook.appmanajer;

import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;
import ru.stqa.pft.addressbook.model.AddData;


  public class ContactHelper extends HelperBase {


    public ContactHelper(FirefoxDriver wd) {
      super(wd);
    }



    public void submitAddCreation() {
      click(By.xpath("(//input[@name='submit'])[2]"));
    }

    public void fillAddPage(AddData addData) {
      type(By.name("firstname"), addData.getFerstname());
      type(By.name("middlename"), addData.getMiddlename());
      type(By.name("lastname"), addData.getLastName());
      type(By.name("mobile"), addData.getNumber());
      type(By.name("email"), addData.getMail());
    }

    public void deletedSelectUser() {
      click(By.xpath("//input[@value='Delete']"));
    }

    public void selectFerstUser() {
      click(By.linkText("home"));
      click(By.xpath("(//td[@class='center']//input)[1]"));
    }

    public void closeInput() {
      closeAlert();

  }
    public void initContactModification() {
      click(By.xpath("//img[@alt='Edit']"));
    }
    public void initCreationNewContact() {
      click(By.linkText("add new"));
    }

    public void submitAddModification() {
      click(By.name("update"));
    }
  }
