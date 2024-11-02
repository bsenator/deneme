package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

import static utilities.ReusableMethods.dotenv;

public class HomePage {
    public HomePage() {
        PageFactory.initElements(Driver.getDriver(), this);
    }

    @FindBy(xpath = "//*[@class='logo']")
    public WebElement logo;

    @FindBy(xpath = "//nav//li/a")
    public java.util.List<WebElement> nav;

    @FindBy(xpath = "//*[@class='dropdown-menu show']/li")
    public java.util.List<WebElement> dropdownMenu;

    public void getTheUrl() {
        Driver.getDriver().get(dotenv.get("SITE_URL"));
    }

    public void getTitle(String expectedTitle) {
        String actualTitle = Driver.getDriver().getTitle();
        System.out.println("Actual Title: " + actualTitle);
        assert actualTitle.equals(expectedTitle);
    }

    public void clickNow() throws InterruptedException {

        nav.get(0).click();
        System.out.println(dropdownMenu.size());
        int dropSize = dropdownMenu.size();
        nav.get(0).click();

        for (int i = 0; i < nav.size(); i++) {

            if (i < dropSize) {
                nav.get(0).click();
                Thread.sleep(2000);
                nav.get(i + 1).click();
                Thread.sleep(2000);
            } else if (i == dropSize) {
                Thread.sleep(2000);
                nav.get(i + 1).click();
                Thread.sleep(2000);
            } else {
                nav.get(i).click();
                Thread.sleep(2000);
                Driver.getDriver().navigate().back();
                Thread.sleep(2000);
                System.out.println(nav.get(i).getText());
            }
        }
    }

}
