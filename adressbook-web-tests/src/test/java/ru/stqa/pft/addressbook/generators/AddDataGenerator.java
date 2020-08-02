package ru.stqa.pft.addressbook.generators;
import ru.stqa.pft.addressbook.model.AddData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class AddDataGenerator {
  public  static  void  main(String[] args) throws IOException {
    int count = Integer.parseInt(args[0]);
    File file = new File(args[1]);
    List<AddData> contacts = generateContacts(count);
    save(contacts,file);
  }

  private static void save(List<AddData> contacts, File file) throws IOException {
    System.out.println(new File(".").getAbsolutePath());
    Writer writer = new FileWriter(file);
    for (AddData contact: contacts){
      writer.write(String.format("%s;%s;%s\n",contact.getFerstname(),contact.getLastName(),contact.getEmail1()));
    }
    writer.close();
  }

  private static List<AddData> generateContacts(int count) {
    List<AddData> contacts = new ArrayList<AddData>();
    for (int i = 0; i < count; i++){
      contacts.add(new AddData().withFerstname(String.format("test %s", i)).withLastName(String.format("test %s", i)).withEmail1(String.format("test %s", i)));
    }
    return  contacts;
  }
}
