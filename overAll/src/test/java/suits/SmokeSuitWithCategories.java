package suits;


import org.junit.experimental.categories.Categories;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import categories.SmokeTestFilter;
import loginTests.LoginTestWithPageObject;
import registrationTest.RegistrTestWithPageObject;

@RunWith(Categories.class)
@Categories.IncludeCategory(SmokeTestFilter.class)
@Suite.SuiteClasses({

        RegistrTestWithPageObject.class,
        LoginTestWithPageObject.class
})
public class SmokeSuitWithCategories {
}
