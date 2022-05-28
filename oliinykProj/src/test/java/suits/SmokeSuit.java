package suits;


import apiTest.ApiTest;
import loginTest.LoginTestWithPageObject;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import signUpTests.SignUpTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        LoginTestWithPageObject.class,
        SignUpTest.class,
        ApiTest.class
})
public class SmokeSuit {
}
