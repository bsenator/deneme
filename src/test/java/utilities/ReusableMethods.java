package utilities;

import io.github.cdimascio.dotenv.Dotenv;
import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertEquals;

public class ReusableMethods {

    public static Dotenv dotenv = Dotenv.load();
    public static Dotenv dotenvLocal = Dotenv.configure().filename(".env.local").load();

    public static List<String> stringListeCevir(List<WebElement> webElementList) {

        List<String> stringList = new ArrayList<>();

        for (WebElement eachElement : webElementList) {

            stringList.add(eachElement.getText());
        }

        return stringList;
    }

    public static String getScreenshot(String name) throws IOException {
        // Komut çalıştırarak git branch ismini alıyoruz
        Process process = Runtime.getRuntime().exec("git rev-parse --abbrev-ref HEAD");

        // Komut çıktısını okumak için bir BufferedReader oluşturuyoruz
        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        String branchName = reader.readLine(); // İlk satırı okuyoruz (branch ismi)
        reader.close(); // Buffer'ı kapatıyoruz

        // naming the screenshot with the current date to avoid duplication
        String date = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
        // TakesScreenshot is an interface of selenium that takes the screenshot
        TakesScreenshot ts = (TakesScreenshot) Driver.getDriver();
        File source = ts.getScreenshotAs(OutputType.FILE);
        // full path to the screenshot location
        String target = System.getProperty("user.dir") + "/test-output/" + branchName + "Screenshots/" + name + date
                + ".png";
        File finalDestination = new File(target);
        // save the screenshot to the path given
        FileUtils.copyFile(source, finalDestination);
        return target;
    }

    public static void titleIleWindowDegistir(String hedefTitle, WebDriver driver) {

        Set<String> whdSeti = driver.getWindowHandles();

        for (String eachWhd : whdSeti) {
            driver.switchTo().window(eachWhd);

            String oldugumuzSayfaTitle = driver.getTitle();

            if (oldugumuzSayfaTitle.equals(hedefTitle)) {

                break;
            }
        }

    }

    public static void tumSayfaSreenshot(WebDriver driver, String resimAdi) {
        TakesScreenshot tss = (TakesScreenshot) driver;

        File tumSayfaScreenshot = new File("target/tumSayfaScreenshot/" + resimAdi + ".jpeg");

        File geciciDosya = tss.getScreenshotAs(OutputType.FILE);

        try {
            FileUtils.copyFile(geciciDosya, tumSayfaScreenshot);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public static void webelementScreenshot(WebElement webElement, String screenshotIsim) {
        LocalDateTime ldt = LocalDateTime.now(); // 2024-01-24T19:01:05.777116
        DateTimeFormatter zamanFormati = DateTimeFormatter.ofPattern("YYMMddHHmmss");
        String timeStamp = ldt.format(zamanFormati); // 240124190341

        File webelementSS = new File("target/webelementScreenshots/" + screenshotIsim + timeStamp + ".jpg");
        File geciciScreenshot = webElement.getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(geciciScreenshot, webelementSS);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public static void scrollToElement(WebElement element) {
        JavascriptExecutor jse = (JavascriptExecutor) Driver.getDriver();
        jse.executeScript("arguments[0].scrollIntoView();", element);
        scrollPageCertainAmount(0, -200);
    }

    public static void scrollPageCertainAmount(int horizonal, int vertical) {
        JavascriptExecutor javascriptExecutor = (JavascriptExecutor) Driver.getDriver();
        javascriptExecutor.executeScript("window.scrollBy(" + horizonal + "," + vertical + ")");
    }

    public static void clickButtonWithText(String ordinal, String buttonName) {
        int index;
        switch (ordinal.toLowerCase()) {
            case "first":
                index = 1;
                break;
            case "second":
                index = 2;
                break;
            case "third":
                index = 3;
                break;
            case "fourth":
                index = 4;
                break;
            case "fifth":
                index = 5;
                break;
            case "sixth":
                index = 6;
                break;
            case "seventh":
                index = 7;
                break;
            case "eighth":
                index = 8;
                break;
            case "ninth":
                index = 9;
                break;
            case "tenth":
                index = 10;
                break;
            default:
                throw new IllegalArgumentException("Invalid ordinal: " + ordinal);
        }

        WebElement ButtonClick = Driver.driver
                .findElement(By.xpath("(//*[text()=\"" + buttonName + "\"])[" + index + "]"));
        ButtonClick.click();
    }

    public static void buttonIsVisibleAndActive(String ordinal, String buttonName) {
        int index;
        switch (ordinal.toLowerCase()) {
            case "first":
                index = 1;
                break;
            case "second":
                index = 2;
                break;
            case "third":
                index = 3;
                break;
            case "fourth":
                index = 4;
                break;
            case "fifth":
                index = 5;
                break;
            case "sixth":
                index = 6;
                break;
            case "seventh":
                index = 7;
                break;
            case "eighth":
                index = 8;
                break;
            case "ninth":
                index = 9;
                break;
            case "tenth":
                index = 10;
                break;
            default:
                throw new IllegalArgumentException("Invalid ordinal: " + ordinal);
        }
        WebElement buttonElement = Driver.getDriver()
                .findElement(By.xpath("(//*[text()=\"" + buttonName + "\"])[" + index + "]"));
        Assert.assertTrue(buttonElement.isDisplayed());
        Assert.assertTrue(buttonElement.isEnabled());
    }

    public static void selectByVisibleText(WebElement element, String text) {
        Select objSelect = new Select(element);
        objSelect.selectByVisibleText(text);
    }

    public static void selectByIndex(WebElement element, int index) {
        Select objSelect = new Select(element);
        objSelect.selectByIndex(index);
    }

    public static void selectByValue(WebElement element, String value) {
        Select objSelect = new Select(element);
        List<WebElement> elementCount = objSelect.getOptions();
        objSelect.selectByValue(value);
        System.out.println("number of elements: " + elementCount.size());
    }

    // Click Method
    public static void clickWithJs1(WebElement element) {
        try {
            element.click();
        } catch (Exception e) {
            JavascriptExecutor js = (JavascriptExecutor) Driver.getDriver();
            js.executeScript("arguments[0].click();", element);
        }
    }

    // Dinamik XPath oluşturan method
    public static String createDynamicXpath(String baseXpath, String value, Integer index) {
        if (baseXpath == null) {
            throw new IllegalArgumentException("baseXpath can not be null");
        }

        return switch (baseXpath) {
            case "text" -> "(//*[.='" + value + "'])[" + index + "]";
            case "alt" -> "(//*[@alt='" + value + "'])[" + index + "]";
            case "class" -> "(//*[@class='" + value + "'])[" + index + "]";
            case "name" -> "(//*[@name='" + value + "'])[" + index + "]";
            default -> throw new IllegalArgumentException("Invalid baseXpath: " + baseXpath);
        };

    }

    // Koşula dayalı olarak baseXpath değerini belirleyen method
    public static String determineBaseXpath(String feature) {
        return switch (feature) {
            case "useText" -> "text";
            case "useAlt" -> "alt";
            case "useClass" -> "class";
            case "useName" -> "name";
            default -> null; // Default veya hata durumu için

        };
    }

    public static void dynamicElementIsDisplayAndIsEnable(String title, String baseXpathFeature, Integer index) {
        // Dinamik olarak baseXpath'i belirliyoruz (koşula göre)
        String baseXpath = determineBaseXpath(baseXpathFeature);

        if (baseXpath == null) {
            throw new IllegalArgumentException("Geçersiz baseXpathCondition değeri: " + baseXpathFeature);
        }

        // Belirlenen baseXpath değerini kullanarak dinamik XPath oluşturma
        String dynamicXpath = createDynamicXpath(baseXpath, title, index);

        // WebElement oluşturma ve kontrol etme
        WebElement dynamicXpathElement = Driver.getDriver().findElement(By.xpath(dynamicXpath));
        JSUtilities.scrollToElement(Driver.getDriver(), dynamicXpathElement);

        Assert.assertTrue(dynamicXpathElement.isDisplayed());
        Assert.assertTrue(dynamicXpathElement.isEnabled());
    }

    public static void dynamicElementClick(String title, String baseXpathFeature, Integer index) {
        // Dinamik olarak baseXpath'i belirliyoruz (koşula göre)
        String baseXpath = determineBaseXpath(baseXpathFeature);

        if (baseXpath == null) {
            throw new IllegalArgumentException("Geçersiz baseXpathCondition değeri: " + baseXpathFeature);
        }

        // Belirlenen baseXpath değerini kullanarak dinamik XPath oluşturma
        String dynamicXpath = createDynamicXpath(baseXpath, title, index);

        // WebElement oluşturma ve kontrol etme
        WebElement dynamicXpathElement = Driver.getDriver().findElement(By.xpath(dynamicXpath));
        dynamicXpathElement.click();
    }

    public static void titleVerifier(String title) {

        String expectedTitle = title;
        System.out.println(expectedTitle);
        String actualTitle = Driver.getDriver().getCurrentUrl();
        assertEquals(expectedTitle, actualTitle);
    }

    public static void scrollWithAction() {
        Actions action = new Actions(Driver.getDriver());
        action.sendKeys(Keys.PAGE_DOWN).build().perform();
    }

    public static void clickButtonWithText(String buttonName) {
        WebElement ButtonClick = Driver.driver.findElement(By.xpath("//*[text()=\"" + buttonName + "\"]"));
        ButtonClick.click();
    }

    public static WebElement accessElementWithText(String buttonName) {
        return Driver.driver.findElement(By.xpath("//*[text()=\"" + buttonName + "\"]"));
    }

    public static WebElement accessElementWithText(String ordinal, String buttonName) {
        int index;
        switch (ordinal.toLowerCase()) {
            case "first":
                index = 1;
                break;
            case "second":
                index = 2;
                break;
            case "third":
                index = 3;
                break;
            case "fourth":
                index = 4;
                break;
            case "fifth":
                index = 5;
                break;
            case "sixth":
                index = 6;
                break;
            case "seventh":
                index = 7;
                break;
            case "eighth":
                index = 8;
                break;
            case "ninth":
                index = 9;
                break;
            case "tenth":
                index = 10;
                break;
            // Add more cases as needed
            default:
                throw new IllegalArgumentException("Invalid ordinal: " + ordinal);
        }

        return Driver.driver.findElement(By.xpath("(//*[text()=\"" + buttonName + "\"])[" + index + "]"));
    }

    public static void verifyButtonWithText(String ordinal, String buttonName) {
        int index;
        switch (ordinal.toLowerCase()) {
            case "first":
                index = 1;
                break;
            case "second":
                index = 2;
                break;
            case "third":
                index = 3;
                break;
            case "fourth":
                index = 4;
                break;
            case "fifth":
                index = 5;
                break;
            case "sixth":
                index = 6;
                break;
            case "seventh":
                index = 7;
                break;
            case "eighth":
                index = 8;
                break;
            case "ninth":
                index = 9;
                break;
            case "tenth":
                index = 10;
                break;
            // Add more cases as needed
            default:
                throw new IllegalArgumentException("Invalid ordinal: " + ordinal);
        }

        WebElement ButtonToVerify = Driver.driver
                .findElement(By.xpath("(//*[text()=\"" + buttonName + "\"])[" + index + "]"));
        Assert.assertTrue(ButtonToVerify.isDisplayed());
    }

    public static void verifyButtonWithText(String buttonName) {
        WebElement ButtonToVerify = Driver.driver.findElement(By.xpath("//*[text()=\"" + buttonName + "\"]"));
        Assert.assertTrue(ButtonToVerify.isDisplayed());
        Assert.assertTrue(ButtonToVerify.isEnabled());
    }

    public static void buttonIsVisibleAndActive(String buttonName) {
        WebElement buttonElement = Driver.getDriver().findElement(By.xpath("//*[text()=\"" + buttonName + "\"]"));
        Assert.assertTrue(buttonElement.isDisplayed());
        Assert.assertTrue(buttonElement.isEnabled());
    }

    public static void verifyBecomeAnInstructorTitleTextAndButtonClick(String title, String baseXpathFeature) {
        // Dinamik olarak baseXpath'i belirliyoruz (koşula göre)
        String meeting = createDynamicXpath("text", "Meetings", 1);
        String meetingrez = createDynamicXpath("text", "My reservations", 1);

        WebElement meetingElement = Driver.getDriver().findElement(By.xpath(meeting));

        meetingElement.click();

        WebElement rex = Driver.getDriver().findElement(By.xpath(meetingrez));
        rex.click();

        String baseXpath = determineBaseXpath(baseXpathFeature);

        if (baseXpath == null) {
            throw new IllegalArgumentException("Geçersiz baseXpathCondition değeri: " + baseXpathFeature);
        }

        // Belirlenen baseXpath değerini kullanarak dinamik XPath oluşturma
        String dynamicXpath = createDynamicXpath(baseXpath, title, 1);

        // WebElement oluşturma ve kontrol etme

        WebElement dynamicXpathElement = Driver.getDriver().findElement(By.xpath(dynamicXpath));

        Assert.assertTrue(dynamicXpathElement.isDisplayed());
        Assert.assertTrue(dynamicXpathElement.isEnabled());
        JSUtilities.scrollToElement(Driver.getDriver(), dynamicXpathElement);
        dynamicXpathElement.click();

    }
}
