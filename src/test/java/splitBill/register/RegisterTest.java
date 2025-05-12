package splitBill.register;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;
import org.testng.Assert;

import java.time.Duration;

public class RegisterTest {
    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver(); // Pastikan chromedriver ada di PATH
        driver.manage().window().maximize();
        driver.get("https://splitbill-cb927.firebaseapp.com/register");
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Test
    public void testRegisterWithExistingEmail() {
        System.out.println("Memulai test: Register dengan email yang sudah terdaftar");

        // Isi form
        driver.findElement(By.name("name")).sendKeys("rizal");
        driver.findElement(By.name("email")).sendKeys("seleniumtest1@gmail.com");
        driver.findElement(By.name("password")).sendKeys("seleniumtest@gmail.com");
        driver.findElement(By.name("confirmPassword")).sendKeys("seleniumtest@gmail.com");

        // Klik daftar
        driver.findElement(By.xpath("//button[@type='submit']")).click();

        // Tunggu pesan error muncul
        WebElement alertBox = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.id("swal2-html-container")
        ));

        // Ambil dan verifikasi isi pesan
        String expectedMessage = "Email yang anda masukan telah terdaftar";
        String actualMessage = alertBox.getText();
        System.out.println("Pesan error: " + actualMessage);
        Assert.assertEquals(actualMessage, expectedMessage, "Pesan error tidak sesuai!");

        // Klik tombol "Mengerti"
        WebElement okButton = wait.until(ExpectedConditions.elementToBeClickable(
                By.className("swal2-confirm")
        ));
        okButton.click();

        // Tunggu popup menghilang
        wait.until(ExpectedConditions.invisibilityOf(alertBox));
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
