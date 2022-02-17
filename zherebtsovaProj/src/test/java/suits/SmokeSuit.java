package suits;

import LoginTest.TestWithPageObject;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import registrationTestWithPageObject.registrationTestWithPageObject;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        TestWithPageObject.class,
        registrationTestWithPageObject.class

})
public class SmokeSuit {
}
