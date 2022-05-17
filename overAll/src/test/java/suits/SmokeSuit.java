package suits;


import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import apiTests.ApiTests;
import loginTests.LoginTestWithPageObject;
import registrationTest.RegistrTestWithPageObject;

@RunWith(Suite.class)
@Suite.SuiteClasses({

        RegistrTestWithPageObject.class,
        LoginTestWithPageObject.class,
        ApiTests.class
})
public class SmokeSuit {
}
