package Assignment1;
import java.io.File;
import java.io.IOException;

import org.openqa.selenium.Alert;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import com.assertthat.selenium_shutterbug.core.Capture;
import com.assertthat.selenium_shutterbug.core.Shutterbug;
import com.google.common.io.Files;

public class Functionaltestcase {

	public static void main(String[] args) throws InterruptedException, IOException {
		// TODO Auto-generated method stub
//		WebDriverManager.chromedriver().setup();
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\duvva\\Desktop\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");

//		ChromeDriver driver = new ChromeDriver();
		WebDriver driver = new ChromeDriver();
		driver.get("https://demo.dealsdray.com/");
		driver.findElement(By.name("username")).sendKeys("prexo.mis@dealsdray.com");
		driver.findElement(By.name("password")).sendKeys("prexo.mis@dealsdray.com");
        driver.findElement(By.xpath("//button[@type='submit']")).click();
        Thread.sleep(5000);
        //driver.findElement(By.xpath("//span[text() = 'menu']")).click();
        driver.findElement(By.xpath("//span[text() = 'Order']")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//span[text() = 'Orders']")).click();
        driver.findElement(By.id("root")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//button[text() = 'Add Bulk Orders']")).click();
        Thread.sleep(2000);
        WebElement fileInput = driver.findElement(By.xpath("//input[@class = 'MuiOutlinedInput-input MuiInputBase-input MuiInputBase-inputSizeSmall css-1imb3v5']")); // Update with the correct element ID
        fileInput.sendKeys("C:\\Users\\duvva\\Downloads\\demo-data.xlsx");

        WebElement uploadButton = driver.findElement(By.xpath("//button[text() = 'Import']")); // Update with the correct element ID
        uploadButton.click();
        driver.findElement(By.xpath("//button[text() = 'Validate Data']")).click();
        Thread.sleep(6000); 
        Alert alert = driver.switchTo().alert();
        alert.accept();
//        File f =((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
//        Files.copy(f, new File("C:\\Users\\duvva\\eclipse-workspace\\Assignment1\\FuctionalTestcase\\Functionaltestcase.jpg"));
        Shutterbug.shootPage(driver, Capture.FULL, true).save("C:\\Users\\duvva\\eclipse-workspace\\Assignment1\\FuctionalTestcase\\");
        Thread.sleep(2000);
        WebElement body = driver.findElement(By.tagName("body"));
        body.sendKeys(Keys.PAGE_DOWN);
        Shutterbug.shootPage(driver, Capture.FULL, true).save("C:\\Users\\duvva\\eclipse-workspace\\Assignment1\\FuctionalTestcase\\");


	}
}