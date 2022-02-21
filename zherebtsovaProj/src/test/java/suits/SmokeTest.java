package suits;

import LoginTest.CreatePostTest;
import LoginTest.TestWithPageObject;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;


@RunWith(Suite.class)
@Suite.SuiteClasses({
        TestWithPageObject.class,
        CreatePostTest.class
})
public class SmokeTest {

}
