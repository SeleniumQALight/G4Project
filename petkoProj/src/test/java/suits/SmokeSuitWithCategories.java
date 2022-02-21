package suits;

import categories.SmokeTestFilter;
import loginTest.LoginTestWithPageObject;
import org.junit.experimental.categories.Categories;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import registrationTest.RegistrationTests;

@RunWith(Categories.class)
@Categories.IncludeCategory(SmokeTestFilter.class)
@Suite.SuiteClasses({
        LoginTestWithPageObject.class,
        RegistrationTests.class
})
public class SmokeSuitWithCategories {

}
