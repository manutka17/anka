package ru.stqa.pft.addressbook.generators;
import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import com.thoughtworks.xstream.XStream;
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
  @Parameter (names = "-d", description = "Data format")
  public  String format;

  public static void main(String[] args) throws IOException {
    AddDataGenerator generator = new AddDataGenerator();
    JCommander jCommander = new JCommander(generator);
    try {
      jCommander.parse(args);
    } catch (ParameterException ex) {
      jCommander.usage();
      return;
    }
    generator.run();
  }

  private void run() throws IOException {
    List<AddData> contacts = generateContacts(count);
    if (format.equals("csv")) {
      saveAsCsv(contacts, new File(file));
    } else if (format.equals("xml")) {
      saveAsXml(contacts, new File(file));
    } else {
      System.out.println("Unrecognized format" + format);
    }
  }
  private void saveAsXml(List<AddData> contacts, File file) throws IOException {
    XStream xstream = new XStream();
    xstream.processAnnotations(AddData.class);
    String xml = xstream.toXML(contacts);
    Writer writer = new FileWriter(file);
    writer.write(xml);
    writer.close();
  }
  private void saveAsCsv(List<AddData> contacts, File file) throws IOException {
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
