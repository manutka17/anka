package ru.stqa.pft.addressbook.model;

public class AddData {
  private final String ferstname;
  private final String middlename;
  private final String lastName;
  private final String number;
  private final String mail;

  public AddData(String ferstname, String middlename, String lastName, String number, String mail) {
    this.ferstname = ferstname;
    this.middlename = middlename;
    this.lastName = lastName;
    this.number = number;
    this.mail = mail;
  }

  public String getFerstname() {
    return ferstname;
  }

  public String getMiddlename() {
    return middlename;
  }

  public String getLastName() {
    return lastName;
  }

  public String getNumber() {
    return number;
  }

  public String getMail() {
    return mail;
  }
}
