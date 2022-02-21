package amazonTestCase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class AmazonSearchUsingDB {

	public static void main(String[] args) throws ClassNotFoundException, SQLException, InterruptedException {

		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ecommerce", "root", "ROOT");

		Statement stm = con.createStatement();

		ResultSet result = stm.executeQuery("Select name from eproduct where ID=1");

		while (result.next()) {

			System.out.println(result.getString("name"));

			System.setProperty("webdriver.chrome.driver", "chromedriver.exe");

			WebDriver driver = new ChromeDriver();

			driver.get("https://www.amazon.in/");
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

			
			String productName = result.getString("name");
			WebElement searchText = driver.findElement(By.xpath("//input[@id='twotabsearchtextbox']"));
			searchText.sendKeys(productName);

			WebElement clickSearch = driver.findElement(By.xpath("//input[@id='nav-search-submit-button']"));
			clickSearch.click();

			List<WebElement> productLists = driver
					.findElements(By.xpath("//h2[@class='a-size-mini a-spacing-none a-color-base s-line-clamp-2']/a"));
			List<WebElement> pricelists = driver.findElements(By.xpath("//a//span[@class='a-price-whole']"));
			
			System.out.println("Total Laptop count is: " + productLists.size());
			
			for (int i=0; i<productLists.size(); i++) {
				if (productLists.get(i).getText().toLowerCase().contains("laptop")) {
	System.out.println(productLists.get(i).getText()+" : "+ pricelists.get(i).getText());
				}
			}
		}
	}
}
