package hu.unideb.inf;
import io.github.bonigarcia.wdm.WebDriverManager;
        import org.openqa.selenium.WebDriver;
        import org.openqa.selenium.chrome.ChromeDriver;
        import org.openqa.selenium.chrome.ChromeOptions;


        import java.util.concurrent.TimeUnit;

public class StepDefinitions {

    static HomePage homePage;

    static final WebDriver driver;

    static {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox");
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.MINUTES);
    }

}
