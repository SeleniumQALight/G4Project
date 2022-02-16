package registrationTests;

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
public class RegistrationTestsSecondVersionWithExcel extends BaseTest {
    String username, email, password, expectedErrors;

    public RegistrationTestsSecondVersionWithExcel(String username, String email, String password, String expectedErrors) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.expectedErrors = expectedErrors;
    }

    @Parameterized.Parameters
    public static Collection testData() throws IOException {
        InputStream inputStream = new FileInputStream(configProperties.DATA_FILE_PATH() + "testDataSuit.xls");
        return new SpreadsheetData(inputStream, "InvalidLogOn").getData();
    }

    @Test
    public void registrationErrors(){
        loginPage.openLoginPage();
        loginPage
                .enterUsernameIntoInputUsernameSignUpForm(username)
                .enterEmailIntiEmailInputSignUpForm(email)
                .enterPasswordIntoPasswordInputSignUpForm(password)
                .checkErrorMessages(expectedErrors);
    }
}
