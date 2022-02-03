package registrationTest;

import baseTest.BaseTest;
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

public class RegistrationTestWithPageObjectWithExcel extends BaseTest {
    String login, email,  passWord,  expectedErrors;

    public RegistrationTestWithPageObjectWithExcel(String login, String email, String passWord, String expectedErrors) {
        this.login = login;
        this.email = email;
        this.passWord = passWord;
        this.expectedErrors = expectedErrors;
    }
    @Parameterized.Parameters
    public static Collection testData() throws IOException {
        InputStream inputStream = new FileInputStream(configProperties.DATA_FILE_PATH() + "testDataSuit.xls");
        return new SpreadsheetData(inputStream, "InvalidLogOn").getData();
    }

    @Test
    public void registrationErrors() {
        loginPage.openLoginPage();
        loginPage
                .enterLoginRegistration(login)
                .enterEmailRegistration(email)
                .enterPasswordRegistration(passWord)
                .checkErrorsMessages(expectedErrors);
    }
}