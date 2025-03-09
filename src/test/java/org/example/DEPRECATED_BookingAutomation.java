package org.example;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.*;
import org.openqa.selenium.JavascriptExecutor;

import java.time.Duration;
import java.util.ArrayList;
import java.util.function.Function;

public class BookingAutomation {
    static WebDriver driver;

    @Before
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));
    }

    @Test
    public void shouldAnswerWithTrue() {
        driver.get("https://www.travelminit.com");
        driver.manage().window().maximize();
        JavascriptExecutor js = (JavascriptExecutor) driver;

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        // Set up FluentWait
        Wait<WebDriver> wait01 = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(30)) // Max time to wait
                .pollingEvery(Duration.ofSeconds(5)) // How often to check the condition
                .ignoring(NoSuchElementException.class) // Ignore NoSuchElementException
                .ignoring(WebDriverException.class); // Ignore WebDriverException

        try {
            String url = "https://www.travelminit.com";
            long startTime = System.currentTimeMillis();
            driver.get(url);
            new WebDriverWait(driver, Duration.ofSeconds(30)).until(
                    (ExpectedCondition<Boolean>) wd -> ((JavascriptExecutor) wd)
                            .executeScript("return document.readyState").equals("complete")
            );
            long endTime = System.currentTimeMillis();
            long loadTime = endTime - startTime;
            System.out.println("Main Page Load Time: " + loadTime + " milliseconds");

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        WebElement gdprButton = driver.findElement(By.id("onetrust-accept-btn-handler"));
        WebElement finishText = wait01.until(new Function<WebDriver, WebElement>() {
            public WebElement apply(WebDriver driver) {
                return driver.findElement(By.id("onetrust-accept-btn-handler"));
            }
        });
        gdprButton.click();

        //1. LOGIN TESTS
        //1.a Login user with invalid credentials

        WebElement loginButton = driver.findElement(By.id("general-login"));
        loginButton.click();

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        WebElement loginEmail = driver.findElement(By.cssSelector("#guest > div.login-box-js > form > div:nth-child(1) > div > input"));
        loginEmail.click();
        loginEmail.sendKeys("cbz.school@guerrillamail.biz");

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        WebElement loginWrongPass = driver.findElement(By.cssSelector("#guest > div.login-box-js > form > div:nth-child(2) > div.iconic-input > input"));
        loginWrongPass.click();
        loginWrongPass.sendKeys("parola_gresita");

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        WebElement loginAttemptConfirm = driver.findElement(By.cssSelector("#guest > div.login-box-js > form > a"));
        loginAttemptConfirm.click();

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        WebElement closeModal = driver.findElement(By.cssSelector("body > div.modal.fade.login-modal.in > div > div > div > div > button > span"));
        closeModal.click();

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //1.b Login user with valid credentials

        loginButton.click();

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        WebElement loginEmail02 = driver.findElement(By.cssSelector("#guest > div.login-box-js > form > div:nth-child(1) > div > input"));
        loginEmail02.click();
        loginEmail02.sendKeys("cbz.school@guerrillamail.biz");

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        WebElement loginPass = driver.findElement(By.cssSelector("#guest > div.login-box-js > form > div:nth-child(2) > div.iconic-input > input"));
        loginPass.sendKeys("1Qaz2Wsx!@#$");

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        WebElement loginConfirm = driver.findElement(By.cssSelector("#guest > div.login-box-js > form > a"));
        loginConfirm.click();

        //2. SEARCH FUNCTIONALITY
        //2.a Searching for a destination

        WebElement searchBar = driver.findElement(By.id("location_header_input"));
        searchBar.click();
        searchBar.sendKeys("Sinaia");

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        WebElement startDate = driver.findElement(By.id("header_start_date_d"));
        startDate.click();

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        WebElement moveCalendarPage1 = driver.findElement(By.cssSelector("#ui-datepicker-div > div.calendar-months > div:nth-child(2) > div > div > button"));
        moveCalendarPage1.click();

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        WebElement moveCalendarPage2 = driver.findElement(By.cssSelector("#ui-datepicker-div > div.calendar-months > div:nth-child(2) > div > div > button"));
        moveCalendarPage2.click();


        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        WebElement selectStartDate = driver.findElement(By.cssSelector("td.day[data-year='2025'][data-month='4'][data-day='1']"));
        selectStartDate.click();

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        WebElement selectEndDate = driver.findElement(By.cssSelector("td.day[data-year='2025'][data-month='4'][data-day='4']"));
        selectEndDate.click();

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        WebElement guest = driver.findElement(By.cssSelector("#travelers-number > ul > li:nth-child(1) > span"));
        guest.click();

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        WebElement guestNumber = driver.findElement(By.cssSelector("#header-search-bar-collapse > div > div.container-fluid > div > form > div.dropdown.header-search-person-section > div > div.travellers-popup-content > div > div > ul.list-unstyled.list-inline.traveller-default > li.guest-container > select"));
        guestNumber.click();

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        WebElement dropdownGuest = driver.findElement(By.name("roomtypes[guestcount]"));
        Select selectGuest = new Select(dropdownGuest);
        selectGuest.selectByValue("2");

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        WebElement dropdownRoom = driver.findElement(By.name("roomtypes[roomCount]"));
        Select selectRoom = new Select(dropdownRoom);
        selectRoom.selectByVisibleText("Cea mai optimă repartizare a camerelor");

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        WebElement dropdownConfirm = driver.findElement(By.cssSelector("#header-search-bar-collapse > div > div.container-fluid > div > form > div.dropdown.header-search-person-section > div > div.travellers-popup-footer.padding-10.clearfix > a"));
        dropdownConfirm.click();

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        WebElement confirmSearch = driver.findElement(By.id("header_search_button"));
        confirmSearch.click();

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //2.b Sorting options

        long startTimeSearch = System.currentTimeMillis();
        new WebDriverWait(driver, Duration.ofSeconds(30)).until(
                (ExpectedCondition<Boolean>) wd -> ((JavascriptExecutor) wd)
                        .executeScript("return document.readyState").equals("complete")
        );
        long endTimeSearch = System.currentTimeMillis();
        long loadTimeSearch = endTimeSearch - startTimeSearch;
        System.out.println("Search Page Load Time: " + loadTimeSearch + " milliseconds");

        Wait<WebDriver> wait02 = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(30)) // Max time to wait
                .pollingEvery(Duration.ofSeconds(5)) // How often to check the condition
                .ignoring(NoSuchElementException.class) // Ignore NoSuchElementException
                .ignoring(WebDriverException.class); // Ignore WebDriverException

        WebElement checkboxAvailability = driver.findElement(By.cssSelector("#modalSideMenu > div.modal-dialog > div > div > div > ul:nth-child(12) > li > div > div > ul > li > a > p"));
        checkboxAvailability.click();

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        WebElement checkboxBreakfast = driver.findElement(By.cssSelector("#modalSideMenu > div.modal-dialog > div > div > div > ul:nth-child(5) > li > div > div > ul > li:nth-child(2) > a > p"));
        checkboxBreakfast.click();

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        WebElement checkboxAccomodationType = driver.findElement(By.cssSelector("#modalSideMenu > div.modal-dialog > div > div > div > ul:nth-child(6) > li > div > div > ul > li:nth-child(1) > a > p"));
        checkboxAccomodationType.click();

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        WebElement checkboxAccomodationRating = driver.findElement(By.cssSelector("#modalSideMenu > div.modal-dialog > div > div > div > ul:nth-child(6) > li > div > div > ul > li:nth-child(2) > div > div > ul > li:nth-child(3) > a > p > span.checkbox-fake"));
        checkboxAccomodationRating.click();

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        WebElement checkboxPaymentOptions = driver.findElement(By.cssSelector("#modalSideMenu > div.modal-dialog > div > div > div > ul:nth-child(9) > li > div > div > ul > li:nth-child(4) > a > p > span.checkbox-fake"));
        checkboxPaymentOptions.click();

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        WebElement checkboxPoI = driver.findElement(By.cssSelector("#modalSideMenu > div.modal-dialog > div > div > div > ul:nth-child(19) > li > div > div > ul > li:nth-child(1) > button > p"));
        checkboxPoI.click();

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        js.executeScript("window.scrollTo(0, 0);");

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        WebElement recommendedDropdown = driver.findElement(By.cssSelector("#resultsListSort"));
        recommendedDropdown.click();

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        WebElement recommendedDropdownList = driver.findElement(By.cssSelector("body > main > div.list-page-title.list-page-header > div.list-options.clearfix > div > div > ul"));
        recommendedDropdownList.click();

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        try {
            // Max slider element
            WebElement maxSlider = driver.findElement(By.cssSelector(".slider-bar__handler.slider-btn-max.slider-btn-pointer"));

            // Calculate offset to drag
            int offsetX = -49; // Approximate value to move to 79.8942%

            // Create Actions object
            Actions actions = new Actions(driver);

            // Click, hold, and drag to the left
            actions.clickAndHold(maxSlider).moveByOffset(offsetX, 0).release().perform();

            // Wait for the price to update
            WebDriverWait waitPrice = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement updatedPrice = waitPrice.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".max-calculated-price")));

            // Verify the price update
            String priceText = updatedPrice.getText();
            // Extract the numeric value from the price text
            String numericPart = priceText.replaceAll("[^0-9]", "");
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

        try {
            // Wait for the container to be visible
            WebDriverWait waitContainer = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement container = waitContainer.until(ExpectedConditions.visibilityOfElementLocated(
                    By.cssSelector(".accommodation-items.mt-15.list-element-data.list-unstyled")
            ));

            // Wait until the first accommodation link is visible
            WebDriverWait waitLink = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement firstLink = waitLink.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("article.accommodation-item a.link-image")));

            // List of tooltip elements
            String[] tooltipXpaths = {
                    "//li[contains(@class, 'hotel-verified-icon')]",
                    "//span[contains(@class, 'glyphicon-waterdrop-splash')]",
                    "//span[contains(@class, 'glyphicon-parking')]",
                    "//span[contains(@class, 'glyphicon-shine')]/parent::*",
                    //"//span[contains(@class, 'glyphicon-group')]/parent::*"
            };

            // Create Actions instance
            Actions actions = new Actions(driver);

            // Iterate through each tooltip element
            for (String xpath : tooltipXpaths) {
                WebElement tooltipElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));

                // Wait 1 second before hovering
                Thread.sleep(1000);

                actions.moveToElement(tooltipElement).perform(); // Hover over the element

                // Hold the hover for 1 second
                Thread.sleep(1000);

                // Wait for tooltip to appear (dynamic aria-describedby attribute)
                String tooltipId = tooltipElement.getAttribute("aria-describedby");
                if (tooltipId != null && tooltipId.startsWith("tooltip")) {
                    WebElement tooltip = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(tooltipId)));
                    if (tooltip.isDisplayed()) {
                        System.out.println("Hover Test Passed: Tooltip is displayed for " + xpath);
                    } else {
                        System.out.println("Hover Test Failed: Tooltip NOT displayed for " + xpath);
                    }
                } else {
                    System.out.println("Hover Test Failed: No tooltip found for " + xpath);
                }
            }

            // Click the first accommodation link
            firstLink.click();

            System.out.println("Clicked on the first link successfully.");

        } catch (Exception e) {
            e.printStackTrace();
        }

        WebDriverWait waitHotel = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Get all open tabs
        ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());

        // Switch to the new tab (usually the second one, index 1)
        driver.switchTo().window(tabs.get(1));

        long startTimeAccomodation = System.currentTimeMillis();
        new WebDriverWait(driver, Duration.ofSeconds(30)).until(
                (ExpectedCondition<Boolean>) wd -> ((JavascriptExecutor) wd)
                        .executeScript("return document.readyState").equals("complete")
        );
        long endTimeAcommodation = System.currentTimeMillis();
        long loadTimeAcommodation = endTimeAcommodation - startTimeAccomodation;
        System.out.println("Acommodation Page Load Time: " + loadTimeAcommodation + " milliseconds");

        // Verify that the switch was successful by printing the title
        System.out.println("Switched to new tab with title: " + driver.getTitle());

        WebElement pictureList = driver.findElement(By.cssSelector("body > div.lg-outer.hotel-light-gallery.lg-use-css3.lg-css3.lg-fade.lg-thumb-open.lg-pull-caption-up.lg-has-thumb.lg-visible.lg-grab.in-page.lg-hide-items > div > div.lg-inner > div.lg-item.lg-loaded.lg-complete.lg-prev-slide > div"));
        pictureList.click();

        // Wait until the button is clickable
        WebDriverWait waitPicFullscreen = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement nextButton = waitPicFullscreen.until(ExpectedConditions.elementToBeClickable(By.cssSelector(
                "body > div.lg-outer.hotel-light-gallery.lg-use-css3.lg-css3.lg-fade.lg-thumb-open.lg-pull-caption-up.lg-has-thumb.lg-visible.is-open.lg-grab.lg-hide-items > div > div.lg-actions > button.lg-next.lg-icon"
        )));

        // Click the button 17 times
        for (int i = 0; i < 17; i++) {
            nextButton.click();
            // Add a small delay between clicks
            try {
                Thread.sleep(500); // 500 milliseconds = 0.5 second
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        nextButton.sendKeys(Keys.ESCAPE);

        WebElement submitButton = driver.findElement(By.cssSelector("#hotel-description-reserve-box > div.hotel-cheapest-price.vip-site > div > div.col-sm-8.col-xs-9.col-xxs-12.map-hotelpage-cheapest-price > form > button"));
        submitButton.click();

        WebElement guestLocation = driver.findElement(By.cssSelector("#inputCity"));
        guestLocation.click();
        guestLocation.sendKeys("mun. Seleniumesti, jud. Javaesti");

        WebElement guestAddress = driver.findElement(By.cssSelector("#inputAddress"));
        guestAddress.click();
        guestAddress.sendKeys("Str. Intelligeiului, nr. 1");

        WebElement guestPhone = driver.findElement(By.cssSelector("#inputPhone"));
        guestPhone.click();
        guestPhone.sendKeys("0722222222");

        WebElement newsletterButton = driver.findElement(By.cssSelector("body > div.container-fluid.container-funnel > div > div.col-sm-7 > div > div > form > div.form-section.row > div > div.newsletter-question.mt-20.mb-20 > div.btn-group.newsletter-btn-container.data-slider-js > label.btn.btn-default.yes-btn"));
        newsletterButton.click();

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        WebElement checkboxTAC = driver.findElement(By.cssSelector("#checkConditions"));
        checkboxTAC.click();

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        WebElement checkboxCA = driver.findElement(By.cssSelector("body > div.container-fluid.container-funnel > div > div.col-sm-7 > div > div > form > div.form-section.row > div > div.cancel-control-group > div > label:nth-child(2) > input"));
        checkboxCA.click();
    }
}
