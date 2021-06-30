package functionaltests;

import java.io.IOException;

import org.apache.poi.ss.usermodel.CellType;
import org.testng.annotations.Test;

import genericUtilities.ExcelUtils;
import reusabletests.IMDb;

public class IMDB_account_creation {

	ExcelUtils exc=new ExcelUtils();
	String dataPath=System.getProperty("user.dir")+"/src/main/resources/Vahak Test Cases.xlsx";
	
	
	@Test
	public void accountCreation() throws IOException {
		IMDb imdb=new IMDb();
		imdb.createAccount(exc.readData(dataPath, 3, 3).toString(),exc.readData(dataPath, 3, 4).toString());
				
		exc.writeData(dataPath, "PASSED", 1, 2, CellType.STRING);
	}
	
	
}
