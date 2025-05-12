package splitBill.login;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;


public class Login {

  private WebDriver driver;

  @BeforeMethod
  public void setUp(){
    driver = new ChromeDriver();
    driver.manage().window().maximize();
    driver.get("https://splitbill-cb927.firebaseapp.com/register");
  }

  @Test
  public void testLogin(){
    System.out.println("Memulai test: Login dengan email yang sudah ada");

    driver.findElement(By.name("email")).click();
    driver.findElement(By.name("email")).clear();
    driver.findElement(By.name("email")).sendKeys("seleniumtest@gmail.com");
    driver.findElement(By.name("password")).clear();
    driver.findElement(By.name("password")).sendKeys("seleniumtest@gmail.com");
    driver.findElement(By.xpath("//button[@type='submit']")).click();
  }

  @AfterMethod
  public void tearDown(){
    if (driver != null){
      driver.quit();
    }
  }
}
