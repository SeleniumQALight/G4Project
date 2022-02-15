package suits;

import loginTest.LoginTestWithPageObject;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import registrationTests.RegistrationTestWithPageObject;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        LoginTestWithPageObject.class,
        RegistrationTestWithPageObject.class
})
public class SmokeSuit {

}
