package suites;

import LoginTest.LoginTestWithPageObject;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import postsTests.CreatePostTest;
import registrationTests.RegistrationTestsSecondVersion;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        LoginTestWithPageObject.class,
        RegistrationTestsSecondVersion.class,
        CreatePostTest.class
})
public class SmokeSuite {
}
