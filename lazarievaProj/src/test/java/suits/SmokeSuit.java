package suits;

import loginTests.LoginTestWithPageObject;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import posts.CreatePostTest;
import signUpTests.TheValidationMessagesVerificationTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        // will run all tests for each one class
        LoginTestWithPageObject.class,
        TheValidationMessagesVerificationTest.class,
        CreatePostTest.class
})
public class SmokeSuit {


}
