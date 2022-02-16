package suits;

import categories.SmokeTestFilter;
import loginTests.LoginTestWithPageObject;
import org.junit.experimental.categories.Categories;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import registrationTest.RegistrTestWithPageObject;

@RunWith(Categories.class)
@Categories.IncludeCategory(SmokeTestFilter.class)
@Suite.SuiteClasses({
        LoginTestWithPageObject.class,
        RegistrTestWithPageObject.class
})
public class SmokeSuitWithCategories {
}
