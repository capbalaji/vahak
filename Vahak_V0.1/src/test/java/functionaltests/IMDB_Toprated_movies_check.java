package functionaltests;

import java.io.IOException;

import org.apache.poi.ss.usermodel.CellType;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;
import org.testng.internal.collections.Pair;

import genericUtilities.ExcelUtils;
import reusabletests.IMDb;

public class IMDB_Toprated_movies_check {

	IMDb imdb;
	WebDriver driver;
	IMDBlogin imdblogin;
	ExcelUtils exc=new ExcelUtils();
	String dataPath=System.getProperty("user.dir")+"/src/main/resources/Vahak Test Cases.xlsx";
	
	@Test(priority=1)
	public void moviesCheck() throws IOException {
		imdb=new IMDb();
		imdblogin=new IMDBlogin();
		
		//imdb.createAccount();
		
		imdblogin.validLogin();
		
		imdb.validateLinks();
		driver.close();
		exc.writeData(dataPath, "PASSED", 4, 2, CellType.STRING);
	}
	
	@Test(priority=2)
	public void lastMovie() throws IOException {
		imdb=new IMDb();
		imdblogin=new IMDBlogin();
		
		//imdb.createAccount();
				
		imdblogin.validLogin();
		
		Pair<WebDriver,String> plist=imdb.findMovie();
		driver=plist.first();
		String releasedate=plist.second();
		driver.close();
		exc.writeData(dataPath, "PASSED", 5, 2, CellType.STRING);
		exc.writeData(dataPath, releasedate, 5, 5, CellType.STRING);
	}
	
}
