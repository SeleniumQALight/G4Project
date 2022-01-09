package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class RegistrPage extends ParentPage {
    @FindBy(xpath = ".//input[@id='username-register']")
    private WebElement inputUserNameRegiste;

    @FindBy(xpath = ".//input[@id ='email-register']")
    private WebElement inputEmailRegister;

    @FindBy(xpath = ".//input[@id='password-register']")
    private WebElement inputPassWordRegister;

    @FindBy(xpath = ".//button[@type ='submit']")
    private WebElement ButtonSignUpForOurApp;

    public RegistrPage(WebDriver webDriver) {
        super(webDriver);
    }

    public void openRegistrPage() {
        try {
            webDriver.get("https://qa-complex-app-for-testing.herokuapp.com/"); //открыть урл
            logger.info("Registr page was opened");// сообщение в логе о открытии страницы в браузере

        } catch (Exception e) {
            logger.error("Can not open Registr Page" + e);
            Assert.fail("Can not open Registr Page" + e);
        }
    }

    public void enterLoginInputIntoUserNameRegiste(String login){
        enterTextInToElement(inputUserNameRegiste, login);
    }

    public void enterEmailIntoInputEmail (String email){
        enterTextInToElement(inputEmailRegister, email);
    }
    public void enterPassWordIntoInputPassWordRegister(String pasword){
        enterTextInToElement(inputPassWordRegister, pasword);
    }

    public void clickOnButtonSignUpForOurApp(){
        clickOnElement(ButtonSignUpForOurApp);

    }

    public boolean isDivTextErrorLoginDisplayed(){
        try{
            return  webDriver.findElement(By.xpath(".//div[contains(text(), 'Username must be at least 3 characters.')]")).isDisplayed();
        }catch (Exception e){
            return false;
        }
    }

    public boolean isDivTextErrorEmailDisplayed(){
        try{
            return  webDriver.findElement(By.xpath(".//div[contains(text(), 'You must provide a valid email address.')]")).isDisplayed();
        }catch (Exception e){
            return false;
        }
    }

    public boolean isDivTextErrorPasswordDisplayed(){
        try{
            return  webDriver.findElement(By.xpath(".//div[contains(text(), 'Password must be at least 12 characters.')]")).isDisplayed();
        }catch (Exception e){
            return false;
        }
    }
}

