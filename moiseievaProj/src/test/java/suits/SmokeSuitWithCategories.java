package suits;

import categories.SmokeTestFilter;
import loginTests.LoginTestWithPageObject;
import org.junit.experimental.categories.Categories;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import registrationTests.RegistrationTest;

@RunWith(Categories.class)
@Categories.IncludeCategory(SmokeTestFilter.class)
@Suite.SuiteClasses({
        LoginTestWithPageObject.class,
        RegistrationTest.class
})
public class SmokeSuitWithCategories {
}
