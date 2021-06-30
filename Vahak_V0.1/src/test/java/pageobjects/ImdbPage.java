package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ImdbPage {

	
	@FindBy(xpath="//div[normalize-space()='Menu']")
	public WebElement menu; 
	
	@FindBy(xpath="//span[normalize-space()='Top Rated Movies']")
	public WebElement lnk_topRatedMovies;
	
	
	@FindBy(id="lister-sort-by-options")
	public WebElement list_sortMovies;
//Release Date
	
	
	@FindBy(className="desc")
	public WebElement noOfFilms;
	
	@FindBy(className="lister-list")
	public WebElement webtable_moviesList;
	
	//tbody/tr[2]/td[2]/a
	
	//public WebElement lastFilmName=driver.findElement(By.xpath("//tbody/tr["+releasedfilms+"]/td[2]/a"))
	@FindBy(css="a[title='See more release dates']")
	public WebElement releaseDate;
	
	
	
	public ImdbPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
}
