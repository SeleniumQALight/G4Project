package suites;

import LoginTest.LoginTestWithPageObject;
import categories.SmokeTestFilter;
import org.junit.experimental.categories.Categories;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import postsTests.CreatePostTest;
import registrationTests.RegistrationTestsSecondVersion;

@RunWith(Categories.class)
@Categories.IncludeCategory(SmokeTestFilter.class)
@Suite.SuiteClasses({
        LoginTestWithPageObject.class,
        RegistrationTestsSecondVersion.class,
        CreatePostTest.class
})
public class SmokeSuiteWithCategories {
}
