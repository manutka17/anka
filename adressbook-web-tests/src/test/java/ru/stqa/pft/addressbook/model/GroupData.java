package ru.stqa.pft.addressbook.model;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@XStreamAlias("group")
@Entity
@Table(name="group_list")
public class GroupData {
  @XStreamOmitField
  @Id
  @Column(name="group_id ")


  private Integer id = Integer.MAX_VALUE;
  @Column(name="group_name ")
  private  String name;
  @Column(name="group_header")
  @Type(type="text")
  private  String header;
  @Column(name="group_footer")
  @Type(type="text")
  private  String footer;
 @ManyToMany (mappedBy = "groups")
  private Set<AddData> contacts=new HashSet<AddData>();

  public Contacts getContacts() {
    return new Contacts(contacts);
  }

  public GroupData withId(Integer id) {
    this.id = id;
    return this;
  }



  public GroupData withName(String name) {
    this.name = name;
    return this;
  }

  public GroupData withHeader(String header) {
    this.header = header;
    return this;
  }

  public GroupData withFooter(String footer) {
    this.footer = footer;
    return this;
  }

  @Override
  public String toString() {
    return "GroupData{" +
            "id=" + id +
            ", name='" + name + '\'' +
            '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    GroupData groupData = (GroupData) o;

    if (id != null ? !id.equals(groupData.id) : groupData.id != null) return false;
    return name != null ? name.equals(groupData.name) : groupData.name == null;
  }

  @Override
  public int hashCode() {
    int result = id != null ? id.hashCode() : 0;
    result = 31 * result + (name != null ? name.hashCode() : 0);
    return result;
  }

  public Integer getId() {
    return id;
  }


  public String getName() {
    return name;
  }

  public String getHeader() {
    return header;
  }

  public String getFooter() {
    return footer;
  }

}
