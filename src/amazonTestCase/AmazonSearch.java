package amazonTestCase;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class AmazonSearch {

	public static void main(String[] args) {
		
		System.setProperty("webdriver.chrome.driver", "chromedriver.exe");

		WebDriver driver = new ChromeDriver();

		driver.get("https://www.amazon.in/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		WebElement searchText = driver.findElement(By.xpath("//input[@id='twotabsearchtextbox']"));
		searchText.sendKeys("iphone 12");
		
		WebElement clickSearch = driver.findElement(By.xpath("//input[@id='nav-search-submit-button']"));
		clickSearch.click();
		
		List<WebElement> lists= driver.findElements(By.xpath("//a[@target='_blank']/span[@class='a-size-medium a-color-base a-text-normal']"));
		List<WebElement> priceLists = driver.findElements(By.xpath("//a[@target='_blank']//span[@class='a-price-whole']"));
		
		System.out.println("Total iphones are: " +lists.size());
		
		for (int i=0; i<lists.size();i++) {
			if(lists.get(i).getText().toLowerCase().contains("iphone 12"))
					{
				
				System.out.println(lists.get(i).getText() +" : "+ priceLists.get(i).getText());
		}
		}
		
	}
}


