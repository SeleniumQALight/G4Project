package suites;

import apiTests.ApiTests;
import loginTests.LoginTestWithPageObject;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import registrationTest.RegistrationTestWithPageObject;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        RegistrationTestWithPageObject.class,
        LoginTestWithPageObject.class,
        ApiTests.class
})

public class SmokeSuite {
}
