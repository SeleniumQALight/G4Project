package pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;

public class ParentPage {
    Logger logger = Logger.getLogger(getClass());
    WebDriver driver;

    public ParentPage(WebDriver driver) {
        this.driver = driver;
    }
}
