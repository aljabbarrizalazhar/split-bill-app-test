package splitBill.register;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.*;
import org.testng.Assert;

public class REG_001 extends BaseTest {

    @Test
    public void testRegisterWithValidData() {
        System.out.println("Memulai test: Register dengan data valid");

        // Buat email unik (misal pakai timestamp agar tidak duplikat)
        String uniqueEmail = "selenium" + System.currentTimeMillis() + "@gmail.com";

        // Isi form dengan data valid
        driver.findElement(By.name("name")).sendKeys("Rizal Selenium");
        driver.findElement(By.name("email")).sendKeys(uniqueEmail);
        driver.findElement(By.name("password")).sendKeys("ValidPass123!");
        driver.findElement(By.name("confirmPassword")).sendKeys("ValidPass123!");

        // Klik daftar
        driver.findElement(By.xpath("//button[@type='submit']")).click();

        // Tunggu redirect ke halaman login atau dashboard
        wait.until(ExpectedConditions.urlContains("/main"));

        // Verifikasi URL berubah ke halaman login (berarti berhasil register)
        String currentUrl = driver.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("/main"), "Register gagal, tidak diarahkan ke halaman login!");

        System.out.println("Registrasi berhasil dan diarahkan ke login.");
    }


}
