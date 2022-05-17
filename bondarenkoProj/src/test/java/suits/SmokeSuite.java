package suits;


import apiTests.ApiTests;
import loginTests.LoginTestWithPageObject;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import registrationTests.RegistrationWithInvalidDataTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        LoginTestWithPageObject.class,
        RegistrationWithInvalidDataTest.class,
        ApiTests.class
})
public class SmokeSuite {
}
