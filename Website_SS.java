//import org.openqa.selenium.Credentials;
package Assignment1;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Website_SS {

    // List of URLs to test
    private static final String[] urls = {"https://www.getcalley.com/","https://www.getcalley.com/calley-call-from-browser/","https://www.getcalley.com/calley-pro-features/","https://www.getcalley.com/best-auto-dialer-app/"};
    // List of resolutions and devices
    private static final Map<String, int[][]> resolutions = new HashMap<>();
    static {
        resolutions.put("Desktop", new int[][]{{1920, 1080}, {1366, 768}, {1536, 864}});
        resolutions.put("Mobile", new int[][]{{360, 640}, {414, 896}, {375, 667}});
    }

    // Function to setup WebDriver
    private static WebDriver setupBrowser(String browserName) {
        WebDriver driver;
        switch (browserName) {
            case "Chrome":
                WebDriverManager.chromedriver().setup();
                System.setProperty("webdriver.chrome.driver", "C:\\Users\\duvva\\Desktop\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");
                ChromeOptions options = new ChromeOptions();
        		options.addArguments("--remote-allow-origins=*");
                driver = new ChromeDriver(options);
                break;
            case "Firefox":
                WebDriverManager.firefoxdriver().setup();
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                driver = new FirefoxDriver(firefoxOptions);
                break;
            case "Edge":
                WebDriverManager.edgedriver().setup();
                EdgeOptions edgeOptions = new EdgeOptions();
                driver = new EdgeDriver(edgeOptions);
                break;
            default:
                throw new IllegalArgumentException("Unsupported browser: " + browserName);
        }
        return driver;
    }

    // Function to take screenshot and save
    private static void takeScreenshot(WebDriver driver, String device, int[] resolution, String url) throws Exception {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
        String timestamp = formatter.format(new Date());
        String filename = String.format("%s/%dx%d/Screenshot-%s.png",device, resolution[0], resolution[1], timestamp);
        File file = new File(filename);
        file.getParentFile().mkdirs();
        driver.get(url);
        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(screenshot, file);
        System.out.println("Saved screenshot: " + filename);
    }

    public static void main(String[] args) {
        String[] browsers = {"Chrome","Firefox", "Edge"};
        for (String browser : browsers) {
            for (String device : resolutions.keySet()) {
                for (int[] resolution : resolutions.get(device)) {
                    WebDriver driver = setupBrowser(browser);
                    driver.manage().window().setSize(new Dimension(resolution[0], resolution[1]));

                    try {
                        for (String url : urls) {
                            takeScreenshot(driver, device, resolution, url);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        driver.quit();
                    }
                }
            }
        }
    }
}
