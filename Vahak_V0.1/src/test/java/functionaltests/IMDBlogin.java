package functionaltests;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.poi.ss.usermodel.CellType;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import genericUtilities.ExcelUtils;
import pageobjects.accountCreationPage;

public class IMDBlogin {
WebDriver driver;
accountCreationPage accountPage;
ExcelUtils exc=new ExcelUtils();
String dataPath=System.getProperty("user.dir")+"/src/main/resources/Vahak Test Cases.xlsx";
	
	@Test(priority=2)
	public void validLogin() throws IOException {
		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/src/main/resources/drivers/chromedriver.exe");
		driver=new ChromeDriver();
		driver.get("https://www.imdb.com/");
		driver.manage().window().maximize();
		accountPage=new accountCreationPage(driver);
		accountPage.signIn.click();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.findElement(By.xpath("//div[@id='wrapper']//a[4]")).click();
		driver.findElement(By.xpath("//input[@id='identifierId']")).sendKeys(exc.readData(dataPath, 2, 3).toString());
		driver.findElement(By.xpath("//span[text()='Next']")).click();
		
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		if(driver.findElement(By.xpath("//input[@id='identifierId']")).isEnabled()) {
			System.out.println("Used invalid credentials and hence you can't login");
			Assert.assertFalse(false);
		}
		else {
			System.out.println("enter password and continue to login");
			Assert.assertFalse(true);
		}
		
		try {
		driver.findElement(By.xpath("//input[@name='password']")).sendKeys(exc.readData(dataPath, 2, 4).toString());
		driver.findElement(By.xpath("//span[text()='Next']")).click();
		}
		catch(Exception e) {
			//DO nothing
		}
		
		driver.close();
		exc.writeData(dataPath, "PASSED", 2, 2, CellType.STRING);
		
	}
	
	@Test(priority=1)
public void invalidLogin() throws IOException {
		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/src/main/resources/drivers/chromedriver.exe");
		driver=new ChromeDriver();
		driver.get("https://www.imdb.com/");
		driver.manage().window().maximize();
		accountPage=new accountCreationPage(driver);
		accountPage.signIn.click();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.findElement(By.xpath("//div[@id='wrapper']//a[4]")).click();
		driver.findElement(By.xpath("//input[@id='identifierId']")).sendKeys(exc.readData(dataPath, 3, 3).toString());
		driver.findElement(By.xpath("//span[text()='Next']")).click();
		
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		if(driver.findElement(By.xpath("//input[@id='identifierId']")).isEnabled()) {
			System.out.println("Used invalid credentials and hence you can't login");
			Assert.assertFalse(false);
		}
		else {
			Assert.assertFalse(true);
			System.out.println("enter password and continue to login");
		}	
		driver.close();
		exc.writeData(dataPath, "PASSED", 3, 2, CellType.STRING);
	}
	
}
