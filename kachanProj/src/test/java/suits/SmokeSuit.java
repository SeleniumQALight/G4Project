package suits;

import apiTest.ApiTests;
import loginTest.LoginTestWithPageObject;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import registrationPage.RegistrationTestWithPageObjectWithExcel;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        LoginTestWithPageObject.class,
        RegistrationTestWithPageObjectWithExcel.class,
        ApiTests.class
})

public class SmokeSuit {
}
