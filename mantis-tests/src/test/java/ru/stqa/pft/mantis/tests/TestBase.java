package ru.stqa.pft.mantis.tests;

import org.openqa.selenium.remote.BrowserType;
import org.testng.SkipException;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import ru.stqa.pft.mantis.appmanager.ApplicationManager;
import ru.stqa.pft.mantis.model.Status;

import javax.xml.rpc.ServiceException;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.RemoteException;

public class TestBase {


  protected static final ApplicationManager app
          = new ApplicationManager(System.getProperty("browser", BrowserType.CHROME));

  @BeforeSuite
  public void setUp() throws Exception {
    app.init();
    app.ftp().upload(new File("src/test/resources/config_defaults_inc.php")
            ,"config_defaults_inc.php", "config_defaults_inc.php.bak");
  }
  public boolean isIssueOpen(int issueId) throws RemoteException, ServiceException, MalformedURLException {
    Status issueStatus = app.soap().issueStatus(issueId);
    return issueStatus.getId() == 10 || issueStatus.getId() == 20 || issueStatus.getId() == 30 || issueStatus.getId() == 40
            || issueStatus.getId() == 50 || issueStatus.getId() == 60 || issueStatus.getId() == 70;
  }

  public void skipIfNotFixed(int issueId) throws RemoteException, ServiceException, MalformedURLException {
    if (isIssueOpen(issueId)) {
      throw new SkipException("Ignored because of issue " + issueId);
    }
  }


  @AfterSuite
  public void tearDown() throws IOException {
    app.ftp().restore("config_defaults_inc.php.bak","config_defaults_inc.php");
    app.stop();
  }
  }
