package org.example;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class AppTest 
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( AppTest.class );
    }

    /**
     * Rigourous Test :-)
     */
    public void testApp()
    {
        assertTrue( true );
    }

    //        //1.c Change existing password
//
//        WebElement accountMenu = driver.findElement(By.cssSelector("#header > div.logo-bar.ro.header-logo-slogen > div > div > div.header__left > div.profile-menu-container > div > div > div.username.hidden-xs > span.glyphicon.glyphicon-chevron-down2.font-12"));
//        accountMenu.click();
//
//        WebElement profileSubmenu = driver.findElement(By.xpath("//*[@id=\"header\"]/div[1]/div/div/div[2]/div[2]/div/ul/li[6]/a"));
//        profileSubmenu.click();
//
//        WebElement changePass = driver.findElement(By.cssSelector("body > div.container-fluid > div > div.col-md-9 > div > div.profile-content > div > form > section:nth-child(1) > div.form-group.show-change-password-block-container > div > a.show-change-password-block.fieldset-helper"));
//        changePass.click();
//
//        WebElement oldPass = driver.findElement(By.id("passwordOld"));
//        oldPass.click();
//        oldPass.sendKeys("1Qaz2Wsx!@#$");
//
//        WebElement newPass = driver.findElement(By.id("passwordNew"));
//        newPass.click();
//        newPass.sendKeys("2Wsx3Edc@#$%");
//
//        WebElement confirmPass = driver.findElement(By.id("passwordNewConfirm"));
//        confirmPass.click();
//        confirmPass.sendKeys("2Wsx3Edc@#$%");
//
//        WebElement saveButton = driver.findElement(By.cssSelector("body > div.container-fluid > div > div.col-md-9 > div > div.profile-content > div > form > div:nth-child(5) > div > div > button"));
//        saveButton.click();
}
