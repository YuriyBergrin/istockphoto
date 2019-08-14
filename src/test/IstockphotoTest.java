
import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;
import io.qameta.allure.Story;
import org.testng.annotations.*;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;

@Story("Тестирование сайта istockphoto")
public class IstockphotoTest {

    IstockphotoPage page;
    private String password = "$Sadmin3816$";
    private String login = "bergrin1991@gmail.com";
    private String boardName = "myFirstBoard";
    public WebDriver driver;

    @Parameters("browser")
    @BeforeTest
    public void launchbrowser(String browser) throws MalformedURLException {
        String URL = "https://www.istockphoto.com/sign-in";
        if (browser.equalsIgnoreCase("firefox")) {
            String Node = "http://192.168.0.18:5556/wd/hub";
            DesiredCapabilities cap = DesiredCapabilities.firefox();
            cap.setBrowserName("firefox");

            driver = new RemoteWebDriver(new URL(Node), cap);
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            driver.navigate().to(URL);
            driver.manage().window().maximize();

        } else if (browser.equalsIgnoreCase("chrome")) {
            DesiredCapabilities cap = DesiredCapabilities.chrome();
            cap.setBrowserName("chrome");

            String Node = "http://192.168.0.18:5557/wd/hub";
            driver = new RemoteWebDriver(new URL(Node), cap);
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            driver.navigate().to(URL);
            driver.manage().window().maximize();

        } else {
            throw new IllegalArgumentException("The Browser Type is Undefined");
        }
    }

    @BeforeMethod
    public void setUp() {
        page = new IstockphotoPage(driver);
    }

    @Test(description = "Тестирование сайта istockphoto")
    public void mainTest() {
        signIn();
        createBoard();
        checkBoard();
        gotoPhotos();
        copyPhotoToBoard();
        checkPhoto();
        removePhoto();
        removeBoard();
    }

    @Step("Войти в систему под предварительно созданной учеткой")
    private void signIn() {
        page.login(login, password);
    }

    @Step("создать Board")
    private void createBoard() {
        page.createBoard(boardName);
    }

    @Step("проверить что Board создалась")
    private void checkBoard() {
        page.showAllBoards();
        $(byText(boardName)).shouldBe(Condition.visible);
    }

    @Step("перейти в Photos")
    private void gotoPhotos() {
        page.gotoPhotos();
    }

    @Step("скопировать одну из фотографий на созданную Board")
    private void copyPhotoToBoard() {
        page.choosePhoto();
        page.copy();
    }

    @Step("проверить, что фотография скопировалась.")
    private void checkPhoto() {
        page.showAllBoards();
        page.gotoFirstBoard();
        page.getPhoto().shouldBe(Condition.visible);
    }

    @Step("Удалить фото из Board и проверить удаление.")
    private void removePhoto() {
        page.remove();
        page.getPhoto().shouldNotBe(Condition.visible);
    }

    @Step("Удалить Board и проверить удаление.")
    private void removeBoard() {
        page.removeBoard();
        $(byText(boardName)).shouldNotBe(Condition.visible);
    }

    @AfterMethod
    @Step("Выходим из системы")
    public void shutdown() {
        driver.quit();
    }
}
