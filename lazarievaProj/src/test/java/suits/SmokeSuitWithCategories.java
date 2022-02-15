package suits;

import categories.SmokeTestFilter;
import loginTests.LoginTestWithPageObject;
import org.junit.experimental.categories.Categories;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import posts.CreatePostTest;
import signUpTests.TheValidationMessagesVerificationTest;

@RunWith(Categories.class)
@Categories.IncludeCategory(SmokeTestFilter.class)
@Suite.SuiteClasses({
        // will run specific tests from each one class (Categories)
        LoginTestWithPageObject.class,
        TheValidationMessagesVerificationTest.class,
        CreatePostTest.class
})
public class SmokeSuitWithCategories {

}
