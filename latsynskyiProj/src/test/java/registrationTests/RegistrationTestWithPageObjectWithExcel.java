package registrationTests;

import baseTest.BaseTest;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import junitparams.internal.ParametrizedTestMethodsFilter;
import junitparams.naming.TestCaseName;
import libs.SpreadsheetData;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import baseTest.BaseTest;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;

import static pages.ParentPage.configProperties;

@RunWith(Parameterized.class)
public class RegistrationTestWithPageObjectWithExcel extends BaseTest {
//String expectedErrors ="Username must be at least 3 characters.;You must provide a valid email address.;Password must be at least 12 characters.";
//    @Test
//    @Parameters({
//           "tr,qqq,345,Username must be at least 3 characters.;You must provide a valid email address.;Password must be at least 12 characters."
//    })
//    public void registrationErrors(){
//    loginPage.openLoginPage();
//            loginPage.enterLoginRegistration("tr")
//                     .enterEmailRegistration("qqq")
//                     .enterPassWordRegistration("345")
//                     .checkErrorsMessages(expectedErrors);
//}
    String  login,email, passWord, expectedErrors;

    public RegistrationTestWithPageObjectWithExcel(String login, String email, String passWord, String expectedErrors) {
        this.login = login;
        this.email = email;
        this.passWord = passWord;
        this.expectedErrors = expectedErrors;
    }
    @Parameterized.Parameters
    public static Collection testData() throws IOException {
        InputStream inputStream = new FileInputStream(configProperties.DATA_FILE_PATH() + "testDataSuit.xls");
        return new SpreadsheetData(inputStream, "InvalidLogOn").getData();          //"InvalidLogOn" name list in Excel file
    }




    @Test
//    @Parameters({
//            "tr,qqq,345,Username must be at least 3 characters.;You must provide a valid email address.;Password must be at least 12 characters."
//    ,"tr,test@qqqq.com,123456qwerty,Username must be at least 3 characters."
//,"tr,test@qqqq.com,123456qwerty,Username must be at least 3 characters.;You must provide a valid email address."
//    })
//    @TestCaseName("registrationErrors : login = {0}, email = {1}, password = {2}")
    public void registrationErrors(){
        loginPage.openLoginPage();
        loginPage.enterUsernameForRegistration(login);
        loginPage.enterEmailForRegistration(email);
        loginPage.enterPasswordForRegistration(passWord);
        loginPage.checkErrorsMessages(expectedErrors);
    }
}
