package utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders {
	//Dataprovider 1

	@DataProvider(name="LoginData")
	public String [] [] getData() throws IOException
	{
		String path = System.getProperty("user.dir")+"\\testData\\Opencart_LoginData.xlsx";		//taking xl file from testData location
		
		ExcelUtility xlutil = new ExcelUtility(path);
		
		int totalrows = xlutil.getRowCount("Sheet1");
		int totalcols = xlutil.getCellCount("Sheet1", 1);		// here 1 will represent the headers of the file
		
		//creating 2 dimension array for storing data from excel
		String loginData [] [] =  new String [totalrows] [totalcols];
		
		for(int r=1; r<=totalrows; r++)		// we will ignore the headers which would be on 0 index
		{
			for(int c=0; c<totalcols; c++)
			{
				loginData [r-1] [c] =  xlutil.getCellData("Sheet1", r, c);		// r-1 because we will fill the data in 0 index but r is first index of file
			}
			
		}
		return loginData;
		
	}
	
	//DataProvider2
	

}
