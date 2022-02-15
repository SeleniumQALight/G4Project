package suits;

import loginTest.LoginTestWithPageObject;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import registrationTests.RegistrationTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        LoginTestWithPageObject.class,
        RegistrationTest.class
})
public class SmokeSuit {

}
