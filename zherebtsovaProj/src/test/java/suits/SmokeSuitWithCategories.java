package suits;

import LoginTest.TestWithPageObject;
import category.SmokeTestFilter;
import org.junit.experimental.categories.Categories;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import registrationTestWithPageObject.registrationTestWithPageObject;

@RunWith(Categories.class)
@Categories.IncludeCategory(SmokeTestFilter.class)
@Suite.SuiteClasses({
        TestWithPageObject.class,
        registrationTestWithPageObject.class
})
public class SmokeSuitWithCategories {
}
