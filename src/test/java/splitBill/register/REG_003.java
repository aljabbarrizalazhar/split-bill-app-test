package splitBill.register;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.*;
import org.testng.Assert;


public class REG_003 extends BaseTest {
    @Test
    public void testRegisterWithInvalidEmailFormat() {
        System.out.println("Memulai test: Register dengan format email tidak valid");

        // Isi data form
        driver.findElement(By.name("name")).sendKeys("John Doe");
        driver.findElement(By.name("email")).sendKeys("john@example"); // email tidak valid
        driver.findElement(By.name("password")).sendKeys("Test@123");
        driver.findElement(By.name("confirmPassword")).sendKeys("Test@123");

        // Klik tombol daftar
        driver.findElement(By.xpath("//button[@type='submit']")).click();

        // Cari pesan error email tidak valid
        WebElement errorElement = driver.findElement(By.xpath("//input[@name='email']/following-sibling::p"));

        String expectedError = "Mohon masukkan email yang valid";
        String actualError = errorElement.getText();
        System.out.println("Pesan error email: " + actualError);

        // Verifikasi
        Assert.assertEquals(actualError, expectedError, "Pesan error validasi email tidak sesuai!");
    }
}
