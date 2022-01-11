package chechRegistrationTests;

import baseTest.BaseTest;
import org.junit.Test;

public class RegistrationTests extends BaseTest {
    @Test
    public void checkRegistrationTests(){
        loginPage.loginWithUnValidCred().isTextLoginShown();
        loginPage.loginWithUnValidCred().isTextEmailShown();
        loginPage.loginWithUnValidCred().isTextPassWordShown();
    }
}
