package org.example;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.*;
import org.openqa.selenium.JavascriptExecutor;
import org.junit.Assert;
import java.time.Duration;
import java.util.ArrayList;

public class BookingUserFlow {
    private static WebDriver driver;
    private WebDriverWait wait;
    private Wait<WebDriver> fluentWait;

    @Before
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Fluent wait setup
        fluentWait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(30))
                .pollingEvery(Duration.ofSeconds(5))
                .ignoring(NoSuchElementException.class, WebDriverException.class);

        driver.get("https://www.travelminit.com");
        waitForPageLoad();
    }

    @Test
    public void allTests() {
        acceptGDPR();
        testInvalidLogin();
        testValidLogin();
        searchDestination();
        waitForPageLoad();
        propertiesPage();
        waitForPageLoad();
        selectedPropertyPage();
        waitForPageLoad();
        bookingPage();
    }

    @After
    public void teardown() {
        driver.quit();
    }

    private void acceptGDPR() {
        WebElement gdprButton = fluentWait.until(driver -> driver.findElement(By.id("onetrust-accept-btn-handler")));
        gdprButton.click();
    }

    private void testInvalidLogin() {
        clickElement(By.id("general-login"));
        clickAndType(By.cssSelector("#guest > div.login-box-js > form > div:nth-child(1) > div > input"), "cbz.school@guerrillamail.biz");
        clickAndType(By.cssSelector("#guest > div.login-box-js > form > div:nth-child(2) > div.iconic-input > input"), "parola_gresita");
        clickElement(By.cssSelector("#guest > div.login-box-js > form > a"));

        // Assertion for invalid login: Checking if error message appears
        WebElement errorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#guest > div.login-box-js > form > div.alert.alert-danger.email-login-response")));
        Assert.assertTrue("Error message not displayed for invalid login!", errorMessage.isDisplayed());
        clickElement(By.cssSelector("body > div.modal.fade.login-modal.in > div > div > div > div > button > span"));
    }

    private void testValidLogin() {
        clickElement(By.id("general-login"));
        clickAndType(By.cssSelector("#guest > div.login-box-js > form > div:nth-child(1) > div > input"), "cbz.school@guerrillamail.biz");
        clickAndType(By.cssSelector("#guest > div.login-box-js > form > div:nth-child(2) > div.iconic-input > input"), "1Qaz2Wsx!@#$");
        clickElement(By.cssSelector("#guest > div.login-box-js > form > a"));

        // Assertion for successful login: Checking if profile icon is visible
        WebElement profileIcon = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#header > div.logo-bar.ro.header-logo-slogen > div > div > div.header__left > div.profile-menu-container > div > div > div.avatar.avatar--small > div")));
        Assert.assertTrue("User profile icon not displayed after valid login!", profileIcon.isDisplayed());
    }

    private void searchDestination() {
        clickAndType(By.id("location_header_input"), "Sinaia");
        clickElement(By.id("header_start_date_d"));
        clickElement(By.cssSelector("#ui-datepicker-div > div.calendar-months > div:nth-child(2) > div > div > button"));
        clickElement(By.cssSelector("#ui-datepicker-div > div.calendar-months > div:nth-child(2) > div > div > button"));
        clickElement(By.cssSelector("td.day[data-year='2025'][data-month='4'][data-day='1']"));
        clickElement(By.cssSelector("td.day[data-year='2025'][data-month='4'][data-day='4']"));
        clickElement(By.cssSelector("#travelers-number > ul > li:nth-child(1) > span"));
        clickElement(By.cssSelector("#header-search-bar-collapse > div > div.container-fluid > div > form > div.dropdown.header-search-person-section > div > div.travellers-popup-content > div > div > ul.list-unstyled.list-inline.traveller-default > li.guest-container > select"));
        WebElement dropdownGuest = driver.findElement(By.name("roomtypes[guestcount]"));
        Select selectGuest = new Select(dropdownGuest);
        selectGuest.selectByValue("2");
        WebElement dropdownRoom = driver.findElement(By.name("roomtypes[roomCount]"));
        Select selectRoom = new Select(dropdownRoom);
        selectRoom.selectByVisibleText("Cea mai optimă repartizare a camerelor");
        clickElement(By.cssSelector("#header-search-bar-collapse > div > div.container-fluid > div > form > div.dropdown.header-search-person-section > div > div.travellers-popup-footer.padding-10.clearfix > a"));
        clickElement(By.id("header_search_button"));
    }

    private void propertiesPage() {
        clickCheckbox(By.cssSelector("#modalSideMenu > div.modal-dialog > div > div > div > ul:nth-child(12) > li > div > div > ul > li > a > p"));
        clickCheckbox(By.cssSelector("#modalSideMenu > div.modal-dialog > div > div > div > ul:nth-child(5) > li > div > div > ul > li:nth-child(2) > a > p"));
        clickCheckbox(By.cssSelector("#modalSideMenu > div.modal-dialog > div > div > div > ul:nth-child(6) > li > div > div > ul > li:nth-child(1) > a > p"));
        clickCheckbox(By.cssSelector("#modalSideMenu > div.modal-dialog > div > div > div > ul:nth-child(6) > li > div > div > ul > li:nth-child(2) > div > div > ul > li:nth-child(3) > a > p > span.checkbox-fake"));
        clickCheckbox(By.cssSelector("#modalSideMenu > div.modal-dialog > div > div > div > ul:nth-child(9) > li > div > div > ul > li:nth-child(4) > a > p > span.checkbox-fake"));
        clickCheckbox(By.cssSelector("#modalSideMenu > div.modal-dialog > div > div > div > ul:nth-child(19) > li > div > div > ul > li:nth-child(1) > button > p"));

        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, 0);");

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        clickElement(By.xpath("//*[@id=\"resultsListSort\"]"));
        clickElement(By.xpath("/html/body/main/div[3]/div[4]/div/div/ul/li[3]/button"));

        try {
            WebElement maxSlider = driver.findElement(By.cssSelector(".slider-bar__handler.slider-btn-max.slider-btn-pointer"));
            int offsetX = -49;// Calculate offset to drag
            Actions actions = new Actions(driver);
            actions.clickAndHold(maxSlider).moveByOffset(offsetX, 0).release().perform();
            WebDriverWait waitPrice = new WebDriverWait(driver, Duration.ofSeconds(10)); // Wait for the price to update
            WebElement updatedPrice = waitPrice.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".max-calculated-price")));
            String priceText = updatedPrice.getText(); // Verify the price update
            String numericPart = priceText.replaceAll("[^0-9]", ""); // Extract the numeric value from the price text
            int priceValue = Integer.parseInt(numericPart);
            if(priceValue < 2500) {
                System.out.println("Test Passed: Price is lower than 2 500 ron.");
            } else {
                System.out.println("Test Failed: Expected price lower than '2 500 ron', but got: " + priceText);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //ACCOMODATION CONTAINER CHECK
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement container = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".accommodation-items.mt-15.list-element-data.list-unstyled")));
            WebElement firstLink = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("article.accommodation-item a.link-image")));
            // List of tooltip elements
            String[] tooltipXpaths = {
                    "//li[contains(@class, 'hotel-verified-icon')]",
                    "//span[contains(@class, 'glyphicon-waterdrop-splash')]",
                    "//span[contains(@class, 'glyphicon-parking')]",
                    "//span[contains(@class, 'glyphicon-shine')]/parent::*"
            };
            Actions actions = new Actions(driver);
            for (String xpath : tooltipXpaths) {
                boolean hovered = false;
                int attempts = 0;
                while (!hovered && attempts < 3) {  // Retry up to 3 times
                    try {
                        WebElement tooltipElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));
                        Thread.sleep(1000);// Wait briefly before hovering
                        actions.moveToElement(tooltipElement).perform(); // Hover over the element
                        Thread.sleep(1000); // Hold the hover for 1 second
                        hovered = true; // If successful, exit loop
                    } catch (StaleElementReferenceException e) {
                        System.out.println("Stale element detected, retrying... Attempt: " + (attempts + 1));
                        attempts++;
                    }
                }
                if (!hovered) {
                    System.out.println("Failed to hover over element after multiple attempts: " + xpath);
                }
            }
            firstLink.click();
            System.out.println("Clicked on the first link successfully.");

        } catch (Exception e) {
            e.printStackTrace();
        }

        WebDriverWait waitHotel = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    private void selectedPropertyPage(){
        ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));
        long startTimeAccomodation = System.currentTimeMillis();
        new WebDriverWait(driver, Duration.ofSeconds(30)).until((ExpectedCondition<Boolean>) wd -> ((JavascriptExecutor) wd).executeScript("return document.readyState").equals("complete"));
        long endTimeAcommodation = System.currentTimeMillis();
        long loadTimeAcommodation = endTimeAcommodation - startTimeAccomodation;
        System.out.println("Acommodation Page Load Time: " + loadTimeAcommodation + " milliseconds");
        System.out.println("Switched to new tab with title: " + driver.getTitle());
        clickElement(By.cssSelector("body > div.lg-outer.hotel-light-gallery.lg-use-css3.lg-css3.lg-fade.lg-thumb-open.lg-pull-caption-up.lg-has-thumb.lg-visible.lg-grab.in-page.lg-hide-items > div > div.lg-inner > div.lg-item.lg-loaded.lg-complete.lg-prev-slide > div"));
        //Picture viewing logic
        WebDriverWait waitPicFullscreen = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement nextButton = waitPicFullscreen.until(ExpectedConditions.elementToBeClickable(By.cssSelector("body > div.lg-outer.hotel-light-gallery.lg-use-css3.lg-css3.lg-fade.lg-thumb-open.lg-pull-caption-up.lg-has-thumb.lg-visible.is-open.lg-grab.lg-hide-items > div > div.lg-actions > button.lg-next.lg-icon")));
        for (int i = 0; i < 17; i++) {
            nextButton.click();
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        nextButton.sendKeys(Keys.ESCAPE);
        clickElement(By.cssSelector("#hotel-description-reserve-box > div.hotel-cheapest-price.vip-site > div > div.col-sm-8.col-xs-9.col-xxs-12.map-hotelpage-cheapest-price > form > button"));
    }

    private void bookingPage(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        try {
            // Check if the first registration page is present
            if (isElementPresent(By.cssSelector("#inputCountry"))) {
                System.out.println("✅ Using Primary Registration Form");
                fillPrimaryForm();
            }
            // Otherwise, check for an alternate form
            else if (isElementPresent(By.cssSelector("#country"))) {
                System.out.println("⚠️ Using Alternate Registration Form");
                fillAlternateForm();
            }
            // If neither is found, log an error
            else {
                System.out.println("❌ No known registration form found!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Primary form filling
    private void fillPrimaryForm() {
        clickAndType(By.cssSelector("#inputCity"), "mun. Seleniumesti, jud. Javaesti");
        clickAndType(By.cssSelector("#inputAddress"), "Str. Intelligeiului, nr. 1");
        clickAndType(By.cssSelector("#inputPhone"), "0722222222");
        clickElement(By.cssSelector("body > div.container-fluid.container-funnel > div > div.col-sm-7 > div > div > form > div.form-section.row > div > div.newsletter-question.mt-20.mb-20 > div.btn-group.newsletter-btn-container.data-slider-js > label.btn.btn-default.yes-btn"));
        clickElement(By.cssSelector("#checkConditions"));
        clickElement(By.cssSelector("body > div.container-fluid.container-funnel > div > div.col-sm-7 > div > div > form > div.form-section.row > div > div.cancel-control-group > div > label:nth-child(2) > input"));
    }

    // Alternate form filling
    private void fillAlternateForm() {
        clickAndType(By.cssSelector("#settlement"), "mun. Seleniumesti, jud. Javaesti");
        clickAndType(By.cssSelector("#streetAddress"), "Str. Intelligeiului, nr. 1");
        clickAndType(By.cssSelector("#phone"), "0722222222");
        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebElement element = driver.findElement(By.cssSelector("body > main > div > section > section > form > section > fieldset.form__fieldset.form__fieldset--bordered-top.px-0.js-form-submit-section > fieldset.form__fieldset.form__fieldset--secondary.form__fieldset--newsletter-subscribe.newsletter-subscribe-block > div.form__group.form__group--row-direction.js-data-slider.js-newsletter-selector > div:nth-child(1) > label > input"));
        js.executeScript("arguments[0].click();", element);
        WebElement privacyPolicy = driver.findElement(By.cssSelector("#privacyPolicy"));
        js.executeScript("arguments[0].click();", privacyPolicy);
        WebElement termOfUse = driver.findElement(By.cssSelector("#termOfUse"));
        js.executeScript("arguments[0].click();", termOfUse);
    }

    //HELPER METHODS
    private void clickAndType(By locator, String text) {
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
        element.click();
        element.clear();
        element.sendKeys(text);
    }

    private void clickElement(By locator) {
        wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
    }

    private void clickCheckbox(By locator) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        try {
            WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
            element.click();
            System.out.println("✅ Clicked element: " + locator.toString());
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } catch (TimeoutException e) {
            System.out.println("❌ Timeout waiting for element to be clickable: " + locator.toString());
            throw e;
        } catch (Exception e) {
            System.out.println("❌ Error clicking element: " + locator.toString());
            e.printStackTrace();
        }
    }

    private void waitForPageLoad() {
        long startTimeSearch = System.currentTimeMillis();
        new WebDriverWait(driver, Duration.ofSeconds(30)).until(wd -> ((JavascriptExecutor) wd).executeScript("return document.readyState").equals("complete"));
        long endTimeSearch = System.currentTimeMillis();
        long loadTimeSearch = endTimeSearch - startTimeSearch;
        System.out.println("Search Page Load Time: " + loadTimeSearch + " milliseconds");
    }

    private boolean isElementPresent(By locator) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            wait.until(ExpectedConditions.presenceOfElementLocated(locator));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }
}