
import com.codeborne.selenide.WebDriverRunner;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static com.codeborne.selenide.Selenide.*;

public class IstockphotoPage {

    public IstockphotoPage(WebDriver driver) {
        WebDriverRunner.setWebDriver(driver);
    }


    public void login(String login, String password) {
        $(By.id("new_session_username")).val(login);
        $(By.id("new_session_password")).val(password);
        $(By.id("sign_in")).click();
    }

    public void createBoard(String name) {
        $x("//*[contains(@class, \"boards-icon-new\")]").click();
        $x("//a[contains(@class, \"create-board\")]").click();
        $x("//form[contains(@class, \"create\")]/input").val(name);
        $x("//a[@class = \"button\" and @type = \"submit\"]").click();
    }

    public void showAllBoards() {
        $x("//*[contains(@class, \"boards-icon-new\")]").click();
        $x("//a[contains(@class, \"view-board\")]").click();
    }

    public void gotoPhotos() {
        $x("//a[contains(@data-nav, \"Photos\")]").click();
    }

    public void choosePhoto() {
        $x("//*[contains(@data-src,\"Lifestyle\")]").click();
        $x("//a//figure").click();
        $x("//article[@data-asset-type=\"image\"]").click();
    }

    public void copy() {
        $x("//a[contains(@class,\"add-to-board\")]").click();
    }

    public void gotoFirstBoard() {
        $x("//*[contains(@class,\"board-item ng-scope\")]").click();
    }

    public void remove() {
        $x("//span[@class = \"remove\"]").click();
    }

    public void removeBoard() {
        $(By.cssSelector("a[class = \"delete-board ng-scope\"]")).click();
        switchTo().alert().accept();
    }
}
