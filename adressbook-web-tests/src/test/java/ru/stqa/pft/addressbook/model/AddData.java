package ru.stqa.pft.addressbook.model;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.File;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@XStreamAlias("contact")
@Entity
@Table(name="addressbook")
public class AddData {
  @XStreamOmitField
  @Id
  @Column(name="id")
  private int id = Integer.MAX_VALUE;
  @Column(name="firstname")
  private String ferstname;
  @Transient
  private String middlename;
  @Column(name="lastname")
  private String lastName;
  @Transient
  private String number;
  @Transient
  private String mail;



  @Column(name="home")
  @Type(type="text")
  private String homePhone;
  @Transient
  private String mobilePhone;
  @Column(name="work")
  @Type(type="text")
  private String workPhone;
  @Transient
  private String allPhones;
  @Column(name="email")
  @Type(type="text")
  private String email1;
  @Transient
  private String email2;
  @Transient
  private String email3;
  @Transient
  private String allEmail;
  @Transient
  private String address;


  @ManyToMany (fetch = FetchType.EAGER)
  @JoinTable (name = "address_in_groups", joinColumns = @JoinColumn(name = "id"), inverseJoinColumns = @JoinColumn(name = "group_id"))
  private Set<GroupData> groups=new HashSet<GroupData>();


  public File getPhoto() {
    return photo;
  }

  public AddData withPhoto(File photo) {
    this.photo = photo;
    return this;
  }
  @Transient
  private File photo;


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



  @Override
  public String toString() {
    return "AddData{" +
            "id=" + id +
            ", ferstname='" + ferstname + '\'' +
            ", lastName='" + lastName + '\'' +
            ", homePhone='" + homePhone + '\'' +
            ", email1='" + email1 + '\'' +
            '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    AddData addData = (AddData) o;
    return id == addData.id &&
            Objects.equals(ferstname, addData.ferstname) &&
            Objects.equals(lastName, addData.lastName) &&
            Objects.equals(homePhone, addData.homePhone) &&
            Objects.equals(email1, addData.email1);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, ferstname, lastName, homePhone, email1);
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

  public Groups getGroups() {
    return new Groups(groups);
  }
}
