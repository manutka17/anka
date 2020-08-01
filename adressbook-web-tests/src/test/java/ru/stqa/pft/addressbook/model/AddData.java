package ru.stqa.pft.addressbook.model;

public class AddData {
  private int id = Integer.MAX_VALUE;
  private String ferstname;
  private String middlename;
  private String lastName;
  private String number;
  private String mail;
  private String group;
  private String homePhone;
  private String mobilePhone;
  private String workPhone;
  private String allPhones;
  private String email1;
  private String email2;
  private String email3;
  private String allEmail;
  private String address;


  public String getAddress() {
    return address;
  }
  public AddData withAddress(String address) {
    this.address = address;
    return this;
  }

  public String getEmail1() {
    return email1;
  }
  public AddData withEmail1 (String email1){
    this.email1 = email1;
    return this;
  }


  public String getEmail2() {
    return email2;
  }
  public AddData withEmail2 (String email2){
    this.email2 = email2;
    return this;
  }

  public String getEmail3() {
    return email3;
  }
  public AddData withEmail3 (String email3){
    this.email3 = email3;
    return this;
  }

  public String getAllEmail() {
    return allEmail;
  }

  public void setAllEmail(String allEmail) {
    this.allEmail = allEmail;
  }
  public AddData withAllEmail(String allEmail) {
    this.allEmail = allEmail;
    return this;
  }

  public String getAllPhones() {
    return allPhones;
  }

  public AddData withAllPhones(String allPhones) {
    this.allPhones = allPhones;
    return this;
  }



  public  String getWorkPhone() {
    return workPhone;
  }
  public AddData withWorkPhone (String workPhone){
    this.workPhone = workPhone;
    return this;
  }
  public  String getMobilePhone() {
    return mobilePhone;
  }
  public AddData withMobilePhone (String mobilePhone){
    this.mobilePhone = mobilePhone;
    return this;
  }
  public  String getHomePhone() {
    return homePhone;
  }
  public AddData withHomePhone (String homePhone){
    this.homePhone = homePhone;
    return this;
  }




  public int getId() {
    return id;
  }


  public AddData withId(int id) {
    this.id = id;
    return this;
  }
  public AddData withFerstname(String ferstname) {
    this.ferstname = ferstname;
    return this;
  }

  public AddData withMiddlename(String middlename) {
    this.middlename = middlename;
    return this;
  }

  public AddData withLastName(String lastName) {
    this.lastName = lastName;
    return this;
  }

  public AddData withNumber(String number) {
    this.number = number;
    return this;
  }

  public AddData withMail(String mail) {
    this.mail = mail;
    return this;
  }

  public AddData withGroup(String group) {
    this.group = group;
    return this;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    AddData addData = (AddData) o;

    if (id != addData.id) return false;
    if (ferstname != null ? !ferstname.equals(addData.ferstname) : addData.ferstname != null) return false;
    return lastName != null ? lastName.equals(addData.lastName) : addData.lastName == null;
  }

  @Override
  public int hashCode() {
    int result = id;
    result = 31 * result + (ferstname != null ? ferstname.hashCode() : 0);
    result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
    return result;
  }

  @Override
  public String toString() {
    return "AddData{" +
            "id=" + id +
            ", ferstname='" + ferstname + '\'' +
            ", lastName='" + lastName + '\'' +
            '}';
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

  public String getGroup() {
    return group;
  }



}
