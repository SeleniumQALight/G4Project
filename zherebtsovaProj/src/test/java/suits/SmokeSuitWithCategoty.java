package suits;

import LoginTest.CreatePostTest;
import LoginTest.TestWithPageObject;
import org.junit.experimental.categories.Categories;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import registrationTestWithPageObject.registrationTestWithPageObject;

@RunWith(Categories.class)
    @Categories.IncludeCategory(smokeTestFilter.class)
    @Suite.SuiteClasses({
            registrationTestWithPageObject.class
    })
    public class SmokeSuitWithCategoty {


    }


