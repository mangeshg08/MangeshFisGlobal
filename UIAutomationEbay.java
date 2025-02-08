package assignments;

import java.time.Duration;
import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class UIAutomationEbay {

	public static void main(String[] args) {
		System.setProperty("webdriver.chrome.driver",
				"C:\\Users\\admin\\Mangesh Eclipse Workspace\\chromedriver-win64 (1)\\chromedriver-win64\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.get("https://www.ebay.com");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofMillis(2000));
		try {
			WebElement searchBox = driver.findElement(By.xpath("//input[@placeholder='Search for anything']"));
			searchBox.sendKeys("book");
			driver.findElement(By.xpath("//button[@id='gh-search-btn']")).click();
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(1000));
			wait.until(ExpectedConditions
					.elementToBeClickable(By.xpath("(//div//ul//li[contains(@class, 's-item')]//a[@class='s-item__link'])[1]")));
			WebElement firstItem = driver
					.findElement(By.xpath("(//div//ul//li[contains(@class, 's-item')]//a[@class='s-item__link'])[1]"));
			firstItem.click();
			Set<String> AllWindowID = driver.getWindowHandles();
			System.out.println(AllWindowID);
			Iterator<String> it = AllWindowID.iterator();
			String MainPageID = it.next();
			String ChildPageID = it.next();
			driver.switchTo().window(ChildPageID);
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(@id,'atcBtn')]")));
			WebElement addToCartButton = driver.findElement(By.xpath("//a[contains(@id,'atcBtn')]"));
			addToCartButton.click();
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class='gh-cart__icon']")));
			WebElement cartCount = driver.findElement(By.xpath("//span[@class='gh-cart__icon']"));
			String itemCount = cartCount.getText();

			if (Integer.parseInt(itemCount) > 0) {
				System.out.println("Test Passed: Item successfully added to the cart. Items in cart: " + itemCount);
			} else {
				System.out.println("Test Failed: Cart count did not update.");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			driver.quit();
		}

	}

}
