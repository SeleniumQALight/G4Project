package pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;


public class ParentPage {
    Logger logger = Logger.getLogger(getClass());
    WebDriver webDriver;
//alt + insert исоздать конструктор
    public ParentPage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

}
