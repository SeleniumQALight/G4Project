package pages;

import libs.ConfigProperties;
import org.aeonbits.owner.ConfigFactory;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.yandex.qatools.htmlelements.element.TextInput;
import ru.yandex.qatools.htmlelements.element.TypifiedElement;
import ru.yandex.qatools.htmlelements.loader.decorator.HtmlElementDecorator;
import ru.yandex.qatools.htmlelements.loader.decorator.HtmlElementLocatorFactory;

import java.util.ArrayList;

import static org.hamcrest.CoreMatchers.containsString;

abstract public class ParentPage {

    @FindBy(xpath = ".//input[@type = 'checkbox']")
    private WebElement inputCheckbox;

    Logger logger = Logger.getLogger(getClass());
    WebDriver webDriver;
    WebDriverWait webDriverWait10, webDriverWait15;
    public  static ConfigProperties configProperties =
            ConfigFactory.create(ConfigProperties.class); //конект получения данных с проперти

    protected String baseUrl = configProperties.base_url(); //получаем url
//alt + insert и создать конструктор
    public ParentPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        //PageFactory.initElements(webDriver, this); //проиницилизровать елементы описанне через FindBy
        PageFactory.initElements(
                new HtmlElementDecorator(
                        new HtmlElementLocatorFactory(webDriver))
                ,this);

        webDriverWait10 = new WebDriverWait(webDriver,configProperties.TIME_FOR_DFFAULT_WAIT());//получем таймаут с проперти
        webDriverWait15 = new WebDriverWait(webDriver,configProperties.TIME_FOR_EXPLICIT_WAIT_LOW());//получем таймаут с проперти
    }
    abstract String getRelativeUrl();//каждая страниуа должна его реализовать - возвращать url

    //метод для проверки урла
    protected  void checkUrl(){
        Assert.assertEquals("Invalid page"
                , baseUrl + getRelativeUrl()
                , webDriver.getCurrentUrl() );
    }
//метод для проверки урла частичного содержания
    protected void checkUrlWithPattern(){
        Assert.assertThat("Invalid page"
                , webDriver.getCurrentUrl()
                , containsString(baseUrl + getRelativeUrl()));
    }

    protected void enterTextInToElement(WebElement webElement, String text) { //метод для чистки и ввода текста в елементы
        try {
            webDriverWait15.until(ExpectedConditions.visibilityOf(webElement));
            webElement.clear();
            webElement.sendKeys(text);
            logger.info(text + " was inputted " + getElementName(webElement));
        } catch (Exception e) {
            printErrorAndStopTest(e);
        }
    }

    private String getElementName(WebElement webElement) {
        String elementName = "";
        if (webElement instanceof TextInput){
            elementName = " '" + ((TypifiedElement) webElement).getName() + "' ";
        }
        return elementName;
    }

    protected boolean isElementDispleid(WebElement webElement) {
       try{
           boolean state = webElement.isDisplayed(); // нет елемента
           if (state){
               logger.info("Element is displayed"); // есть елемент
           } else {
               logger.info("Element is not displayed");// нет елемента
           }
           return webElement.isDisplayed();
       }catch (Exception e){
           logger.info("Element is not displayed");
           return false;
       }
  }


    protected void clickOnElement(WebElement webElement){
        try {
            webDriverWait10.until(ExpectedConditions.elementToBeClickable(webElement));
            webElement.click();
            logger.info(getElementName(webElement) + " Element was clicked");
        }catch (Exception e){
            printErrorAndStopTest(e);
        }
    }


    protected void selectTextInDropDown(WebElement dropDown, String text){
        try {
            Select select = new Select(dropDown);//передать закрытый дробдаун
            select.selectByVisibleText(text);//выбрать нужный текст
            logger.info(text + "was selected in DD");
        }catch (Exception e){
            printErrorAndStopTest(e);
        }
    }


    protected void selectValueInDropDown (WebElement dropDown, String value){
        try {
            Select select = new Select(dropDown);//передать закрытый дробдаун
            select.selectByValue(value);//выбрать нужный текст
            logger.info(value + "was selected in DD");
        }catch (Exception e){
            printErrorAndStopTest(e);
        }

    }

    public void setCheckboxValue(String value){
        try {
            boolean state = inputCheckbox.isSelected();
            if (value.equals("check") && !state || value.equals("uncheck") && state){
                clickOnElement(inputCheckbox);
                logger.info(String.format("CheckBox was %sed", value));
            } else {
                logger.info(String.format("CheckBox is already %sed", value));
            }
        }catch (Exception e){
             printErrorAndStopTest(e);
        }
    }

//
//    protected void waitChatTobeHide(){
//        //ToDO wait chat
//        try {
//            Thread.sleep(1000); //свернуть все и подождать в милисекундах alt + entr
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//    }

    protected void waitChatTobeHide(){
        webDriverWait10
                .withMessage("Chat is not closed ") //сообщение если после ожидание не произошло ожидаемое действие
                .until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(".//*[@id='chat-wrapper']")));// проверка на исчезновение чата
    }

    public void usersPressesKeyEnterTime(int numberOfTimes) {
        Actions actions = new Actions(webDriver);//Actions - работает с клавиатурой
        for (int i = 0; i < numberOfTimes; i++) {
            actions.sendKeys(Keys.ENTER).build().perform();
        }
    }
    public void usersPressesKeyTabTime(int numberOfTimes) {
        Actions actions = new Actions(webDriver); //Actions - работает с клавиатурой
        for (int i = 0; i < numberOfTimes; i++) {
            actions.sendKeys(Keys.TAB).build().perform();
        }

    }

    public void userOpensNewTab() {  //открытие нового окна
        ((JavascriptExecutor)webDriver).executeScript("window.open()");// выполнять джава скрипт команды
        ArrayList<String> tabs = new ArrayList<> (webDriver.getWindowHandles());// взаимодействие с вкладками - и считали список вкладок
        webDriver.switchTo().window(tabs.get(1));//свичнутся в новую вкладку
    }

    private void printErrorAndStopTest(Exception e) {
        logger.error("Can not work with element" + e);// в консоль
        Assert.fail("Can not work with element" + e);// в отчет
    }

}
