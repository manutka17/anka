package ru.stqa.pft.addressbook.model;

import com.google.common.collect.ForwardingSet;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class Contacts extends ForwardingSet<AddData> {
  private Set<AddData> delegate;
  public Contacts (Contacts contacts){
    this.delegate = new HashSet<AddData>(contacts.delegate);
  }

  public Contacts() {
    this.delegate = new HashSet<>();
  }

  public Contacts(Collection<AddData> contacts) {
    this.delegate = new HashSet<AddData>(contacts);
  }

  @Override
  protected Set<AddData> delegate() {
    return delegate;
  }
  public Contacts withAdded (AddData contact){
    Contacts contacts = new Contacts(this);
    contacts.add(contact);
    return contacts;
  }
  public Contacts withOut (AddData contact){
    Contacts contacts = new Contacts(this);
    contacts.remove(contact);
    return contacts;
  }
}
