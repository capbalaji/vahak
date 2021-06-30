package reusabletests;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;
import org.testng.internal.collections.Pair;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import pageobjects.ImdbPage;
import pageobjects.accountCreationPage;

public class IMDb {

	WebDriver driver;
	ImdbPage imdbPage;
	accountCreationPage accountPage;
	String[] releasedfilm;
	String releasedfilms,releasedate;
	String url;
	WebElement urllink;
	
	
//	@Test
	public void createAccount(String username, String password) {
		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/src/main/resources/drivers/chromedriver.exe");
		driver=new ChromeDriver();
		driver.get("https://www.imdb.com/");
		driver.manage().window().maximize();
		accountPage=new accountCreationPage(driver);
		try {
		accountPage.signIn.click();
		accountPage.btn_createAccount.click();
		
		accountPage.txtbox_customerName.sendKeys(username);
		accountPage.txtbox_email.sendKeys("vahak@outlook.com");
		accountPage.txtbox_emailPassword.sendKeys(password);
		accountPage.txtbox_emailPasswordRecheck.sendKeys(password);
		accountPage.btn_createImdb.click();
		accountPage.txtbox_otp.sendKeys("467274");			
		accountPage.btn_submitOtp.click();
		driver.close();
		}
		catch(Exception e) {
			System.out.println("An account is already created using these credentials So try using those credentials to signin");
			driver.close();
		}
		
		
	}
	
	
	
	public Pair<WebDriver,String> findMovie() {
		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/src/main/resources/drivers/chromedriver.exe");
		driver= new ChromeDriver();
		driver.get("https://www.imdb.com");
		driver.manage().window().maximize();
		imdbPage=new ImdbPage(driver);
		imdbPage.menu.click();
		
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		driver.findElement(By.partialLinkText("Top Rated Movies")).click();
		
		Select sel= new Select(imdbPage.list_sortMovies);
		sel.selectByVisibleText("Release Date");
				
		
		releasedfilm= imdbPage.noOfFilms.getText().split(" ");
		releasedfilms=releasedfilm[1];
		
		System.out.println("number of films released are:"+releasedfilms);
		String lastfilm=driver.findElement(By.xpath("//tbody/tr["+releasedfilms+"]/td[2]/a")).getText();
		System.out.println("Last film name is : "+lastfilm);
		
		driver.findElement(By.xpath("//tbody/tr["+releasedfilms+"]/td[2]/a")).click();
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		try {
			releasedate=imdbPage.releaseDate.getText();
		System.out.println("release date of movie-"+lastfilm+" is:"+releasedate);
		}
		catch(Exception e) {
			releasedate=driver.findElement(By.partialLinkText("(United")).getText();
					System.out.println("release date of movie-"+lastfilm+" is:"+releasedate);
		}
		//return driver;
		
		return new Pair<WebDriver,String>(driver,releasedate);
		
	}
	
	
	
	//@Test
	public void validateLinks() {
		IMDb imd=new IMDb();
		
		Pair<WebDriver,String> plist=	imd.findMovie();
		driver=plist.first();		
		List<WebElement> link=driver.findElements(By.tagName("a"));
		Iterator<WebElement> itr=link.iterator();
		
		while(itr.hasNext())
		{
			urllink=itr.next();
			if(urllink.getAttribute("href")!=null) {
				url=urllink.getAttribute("href");
			}
				
			//System.out.println("link is:"+url);
			
		}
		
		
		//RestAssured.baseURI="https://www.imdb.com";
		RequestSpecification req=RestAssured.given();
		Response resp=req.get(url);
		System.out.println("response code is:"+resp.statusCode());
		System.out.println("response is:"+resp.asString());
		//urllink.click();
		
		driver.close();
		
		
		}
	
}
