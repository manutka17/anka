package ru.stqa.pft.addressbook.model;

public class AddData {
  private  final String id;
  private final String ferstname;
  private final String middlename;
  private final String lastName;
  private final String number;
  private final String mail;
  private  String group;

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    AddData addData = (AddData) o;

    if (id != null ? !id.equals(addData.id) : addData.id != null) return false;
    if (ferstname != null ? !ferstname.equals(addData.ferstname) : addData.ferstname != null) return false;
    return lastName != null ? lastName.equals(addData.lastName) : addData.lastName == null;
  }

  @Override
  public int hashCode() {
    int result = id != null ? id.hashCode() : 0;
    result = 31 * result + (ferstname != null ? ferstname.hashCode() : 0);
    result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
    return result;
  }

  @Override
  public String toString() {
    return "AddData{" +
            "id='" + id + '\'' +
            ", ferstname='" + ferstname + '\'' +
            ", lastName='" + lastName + '\'' +
            '}';
  }

  public String getId() {
    return id;
  }

  public AddData(String id, String ferstname, String middlename, String lastName, String number, String mail, String group) {
    this.id = id;
    this.ferstname = ferstname;
    this.middlename = middlename;
    this.lastName = lastName;
    this.number = number;
    this.mail = mail;
    this.group =group;
  }
  public AddData( String ferstname, String middlename, String lastName, String number, String mail, String group) {
    this.id = null;
    this.ferstname = ferstname;
    this.middlename = middlename;
    this.lastName = lastName;
    this.number = number;
    this.mail = mail;
    this.group =group;
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

  public String getGroup() { return group; }
}
