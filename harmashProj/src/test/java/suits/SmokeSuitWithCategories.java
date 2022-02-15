package suits;

import categories.SmokeTestFilter;
import loginTest.LoginTestWithPageObject;
import org.junit.experimental.categories.Categories;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import registrationTests.RegistrationTestWithPageObject;

@RunWith(Categories.class)
@Categories.IncludeCategory(SmokeTestFilter.class)
@Suite.SuiteClasses({
        RegistrationTestWithPageObject.class,
        LoginTestWithPageObject.class

})
public class SmokeSuitWithCategories {

}
