package suits;

import RegistrTest.RegistrTestWithPageObject;
import loginTests.LoginTestWithPageObject;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        LoginTestWithPageObject.class,
        RegistrTestWithPageObject.class,
})
public class SmokeSuit {
}
