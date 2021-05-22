package hu.unideb.inf;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HomePage {

    private static final String PAGE_URL = "http://automationpractice.com/";
    private static final By CREATE_ACCOUNT_ERROR = By.xpath("//*[@id=\"create_account_error\"]/ol/li");
    private static final By CONTACT_US_ERROR = By.xpath("//*[@id=\"center_column\"]/div/ol/li");

    private WebDriver driver;

    @FindBy(linkText = "Specials")
    private WebElement specialsLink;

    @FindBy(linkText = "New products")
    private WebElement newProductLink;

    @FindBy(linkText = "Best sellers")
    private WebElement bestSellersLink;

    @FindBy(linkText = "Our stores")
    private WebElement storesLink;

    @FindBy(xpath = "//*[@id=\"searchbox\"]/button")
    private WebElement searchButton;

    @FindBy(className = "login")
    private WebElement signInLink;

    @FindBy(id = "SubmitLogin")
    private WebElement signInButton;

    @FindBy(id = "SubmitCreate")
    private WebElement createAnAccount;

    @FindBy(xpath = "//*[@id=\"newsletter_block_left\"]/div/form/div/button")
    private WebElement newsLetterSubscriptionButton;

    @FindBy(xpath = "//*[@id=\"columns\"]/p")
    private WebElement newsLetterSubscriptionMessage;

    @FindBy(xpath = "//*[@title=\"View my shopping cart\"]")
    private WebElement cartButton;

    @FindBy(xpath = "//*[@title=\"Delete\"]")
    private WebElement cartDeleteButton;

    @FindBy(xpath = "//*/ul[@id=\"homefeatured\"]/li[1]//*/a[contains(@class, 'ajax_add_to_cart_button')]")
    private WebElement firstPopularProductAddToCartButton;

    @FindBy(xpath = "//h1")
    private WebElement pageTitle;

    @FindBy(id = "summary_products_quantity")
    private WebElement productCount;

    @FindBy(className = "alert")
    private WebElement alertBar;

    @FindBy(id = "total_product")
    private WebElement totalProductPrice;

    @FindBy(id = "contact-link")
    private WebElement contactUsLink;

    @FindBy(id = "submitMessage")
    private WebElement contactUsSubmitButton;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        driver.get(PAGE_URL);
        PageFactory.initElements(driver, this);
    }

    //navigate feature
    public void theLinkIsClicked(String button) {

        switch (button) {
            case "Specials":
                specialsLink.click();
                break;
            case "New products":
                newProductLink.click();
                break;
            case "Best sellers":
                bestSellersLink.click();
                break;
            case "Our stores":
                storesLink.click();
                break;
            default:
                driver.get("http://automationpractice.com/index.php?controller=authentication&back=identity");
        }

    }

    public void getUrl(String url) {
        //driver.get("https://index.hu/");

        String currentUrl = driver.getCurrentUrl().toString();
        if (currentUrl == url) {
            System.out.println("The current url is:" + currentUrl);
        }

    }

    // login feature
    public void fieldFill(String field, String value) {
        driver.findElement(By.id(field)).sendKeys(value);
    }

    public void clickSearchButton() {
        getSearchButton().click();
    }

    public void clickSignInLink() {
        signInLink.click();
    }

    public void clickSignInButton() {
        signInButton.click();
    }

    public void fillField(String field, String value) {
        getField(By.id(field)).sendKeys(value);
    }


    public Optional<String> getErrorMessage() {
        Optional<WebElement> error = getError();
        if (error.isPresent()) {
            WebElement errorElement = error.get();
            return Optional.of(errorElement.getText());
        } else {
            return Optional.empty();
        }
    }

    private Optional<WebElement> getError() {
        List<WebElement> elements = driver.findElements(By.xpath("//*[@id=\"center_column\"]/div[1]/ol/li"));
        if (elements.size() > 0) {
            return Optional.of(elements.get(0));
        } else {
            return Optional.empty();
        }
    }

    public void clickCreateAnAccount() {
        getCreateAnAccount().click();
    }


    public Optional<String> getCreateAccountErrorMessage() {
        Optional<WebElement> error = getCreateAccountError();
        if (error.isPresent()) {
            WebElement errorElement = error.get();
            return Optional.of(errorElement.getText());
        } else {
            return Optional.empty();
        }
    }

    private Optional<WebElement> getCreateAccountError() {
        List<WebElement> errorList = driver.findElements(CREATE_ACCOUNT_ERROR);
        if (errorList.isEmpty()) {
            return Optional.empty();
        } else {
            return Optional.of(errorList.get(0));
        }
    }
    //newsletter

    public void clickNewsLetterButton() {
        getNewsLetterSubscriptionButton().click();
    }

    public String getNewsLetterMessage() {
        return getNewsLetterSubscriptionMessage().getText();
    }

    // Cart feature

    public void clickFirstPopularProductAddToCartButton() {
        this.getFirstPopularProductAddToCartButton().click();
        this.waitTillAjaxRequestFinishes();
        this.driver.navigate().refresh();
    }

    public void clickCartButton() {
        this.getCartButton().click();
    }

    public String getCurentPageTitle() {
        return this.getPageTitle().getText();
    }


    public void resetSession() {
        driver.manage().deleteAllCookies();
    }


    public Integer getCurentProductCount() {
        Pattern p = Pattern.compile("\\d+");
        Matcher m = p.matcher(this.getProductCount().getText());
        m.find();
        return Integer.parseInt(m.group());
    }

    public void cartDeleteItem() {
        this.getCartDeleteButton().click();
        this.waitTillAjaxRequestFinishes();
    }

    private void waitTillAjaxRequestFinishes() {
        WebDriverWait wait = new WebDriverWait(driver, 15);
        wait.until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver webdriver) {
                return ((JavascriptExecutor) driver).executeScript("return jQuery.active == 0").equals(true);
            }
        });
    }

    public Float getCurentCartProductTotal() {
        return Float.parseFloat(this.getTotalProductPrice().getText().substring(1).replace(",", ""));
    }

    public String getAlertText() {
        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        return this.getAlertBar().getText();
    }

    //contact us

    public void clickContactUsLink() {
        getContactUsLink().click();
    }

    public void clickContactUsPageSubmitButton() {
        getContactUsSubmitButton().click();
    }


    public Optional<String> getContactUsErrorMessage() {
        Optional<WebElement> error = getContactUsError();
        if (error.isPresent()) {
            WebElement errorElement = error.get();
            return Optional.of(errorElement.getText());
        } else {
            return Optional.empty();
        }
    }

    //webElements
    public WebElement getSpecialsLink() {
        return specialsLink;
    }

    public WebElement getNewProductLink() {
        return newProductLink;
    }

    public WebElement getBestSellersLink() {
        return bestSellersLink;
    }

    public WebElement getStoresLink() {
        return storesLink;
    }


    public WebElement getSearchButton() {
        return searchButton;
    }

    public WebElement getField(By locator) {
        return driver.findElement(locator);
    }

    public WebElement getSignInLink() {
        return signInLink;
    }

    public WebElement getSignInButton() {
        return signInButton;
    }

    public WebElement getCreateAnAccount() {
        return createAnAccount;
    }

    private WebElement getNewsLetterSubscriptionButton() {
        return newsLetterSubscriptionButton;
    }

    private WebElement getNewsLetterSubscriptionMessage() {
        return newsLetterSubscriptionMessage;
    }


    public WebElement getTotalProductPrice() {
        return totalProductPrice;
    }

    public WebElement getFirstPopularProductAddToCartButton() {
        return firstPopularProductAddToCartButton;
    }

    public WebElement getCartButton() {
        return cartButton;
    }

    public WebElement getProductCount() {
        return productCount;
    }

    public WebElement getCartDeleteButton() {
        return cartDeleteButton;
    }

    public WebElement getAlertBar() {
        return alertBar;
    }

    public WebElement getPageTitle() {
        return pageTitle;
    }

    public WebElement getContactUsLink() {
        return contactUsLink;
    }

    public WebElement getContactUsSubmitButton() {
        return contactUsSubmitButton;
    }


    private Optional<WebElement> getContactUsError() {
        List<WebElement> errorList = driver.findElements(CONTACT_US_ERROR);
        if (errorList.isEmpty()) {
            return Optional.empty();
        } else {
            return Optional.of(errorList.get(0));
        }
    }


}

