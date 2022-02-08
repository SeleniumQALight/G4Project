package registrationTestWithPageObject;

import BaseTest.BaseTest;
import libs.SpreadsheetData;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;

import static pages.ParentPage.configProperties;

@RunWith(Parameterized.class)
public class registrationTestWithPageObjectWithExel extends BaseTest {
    String login, passWord, email, expectedErrors;

    public registrationTestWithPageObjectWithExel(String login, String passWord, String email, String expectedErrors) {
        this.login = login;
        this.passWord = passWord;
        this.email = email;
        this.expectedErrors = expectedErrors;
    }
    @Parameterized.Parameters
public static Collection testData() throws IOException {
        InputStream inputStream = new FileInputStream(configProperties.DATA_FILE_PATH() + "testDataSuit.xls");
        return new SpreadsheetData(inputStream, "InvalidLogOn").getData();
    }
    @Test
    public void registration() {
        loginPage.openLoginPage();
        loginPage
                .enterLoginRegistration(login)
                .enterEmailRegistration(email)
                .enterPasswordRegistration(passWord)
                .clickOnButtonSignUp();

    }
}
