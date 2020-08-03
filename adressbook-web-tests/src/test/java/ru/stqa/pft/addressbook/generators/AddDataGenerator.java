package ru.stqa.pft.addressbook.generators;
import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import ru.stqa.pft.addressbook.model.AddData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class AddDataGenerator {
  @Parameter(names = "-c", description = "Contact count")
  public  int count;

  @Parameter (names = "-f", description = "Target file")
  public  String file;

  public static void main(String[] args) throws IOException {
    AddDataGenerator generator = new AddDataGenerator();
    JCommander jCommander = new JCommander(generator);
    try {
      jCommander.parse(args);
    }catch (ParameterException ex){
      jCommander.usage();
      return;
    }
    generator.run();
  }

  private void run() throws IOException {
    List<AddData> contacts = generateContacts(count);
    save(contacts,new File(file));
  }

  private static void save(List<AddData> contacts, File file) throws IOException {
    System.out.println(new File(".").getAbsolutePath());
    Writer writer = new FileWriter(file);
    for (AddData contact: contacts){
      writer.write(String.format("%s;%s;%s;%s\n",contact.getFerstname(),contact.getLastName(),contact.getEmail1(),contact.getGroup()));
    }
    writer.close();
  }

  private static List<AddData> generateContacts(int count) {
    List<AddData> contacts = new ArrayList<AddData>();
    for (int i = 0; i < count; i++){
      contacts.add(new AddData().withFerstname(String.format("FerstName %s", i)).withLastName(String.format("LastName %s", i)).withEmail1(String.format("Email1 %s", i)).withGroup(String.format("test1")));
    }
    return  contacts;
  }
}
