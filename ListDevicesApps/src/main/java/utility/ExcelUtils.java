package utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.List;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.util.WorkbookUtil;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


// TODO: Auto-generated Javadoc
/**
 * The Class ExcelUtils.
 */
public class ExcelUtils {

	/** The file path. */
	private String filePath = "";
	
	/** The sheet name. */
	private String sheetName = "";
	
	/** The excel w book. */
	private  XSSFWorkbook excelWBook;
	
	/** The excel w sheet. */
	private  XSSFSheet excelWSheet;
	
	/** The cell. */
	private  XSSFCell cell;
	
	/** The row. */
	private  XSSFRow row;
	
	/** The create helper. */
	private CreationHelper createHelper;
	
	//private SystemProperties prop;
	
	

	
	/**
	 * Instantiates a new excel utils.
	 *
	 * @param prop the prop
	 */
	public ExcelUtils(SystemProperties prop) {
		//this.prop = prop;
		// Get Excel file path
	  	//this.filePath = new File("").getAbsolutePath();
		String timeStamp = new SimpleDateFormat("_MMddyyyy_HHmmss").format(new java.util.Date());
		
		if (SystemProperties.isOutputWithTimestamp()){
			this.filePath = SystemProperties.getOutputworkbook() + timeStamp + ".xlsx";
		} else{
			this.filePath = SystemProperties.getOutputworkbook() + ".xlsx";
		}
	  
	  
	  	// Open workbook
  	  	try {
			this.setWorkbook(this.filePath);
			this.setSheet(SystemProperties.getOutputsheet(), true);
		} catch (Exception e) {
			
			e.printStackTrace();
		}
  	  	
  	  	this.addColumns();
  	  	
		
	}
	
	/**
	 * Sets the sheet.
	 *
	 * @param sheetName the sheet name
	 * @param addIfNotExists the add if not exists
	 * @throws Exception the exception
	 */
	// Sets the active sheet by name
	public void setSheet(String sheetName, boolean addIfNotExists) throws Exception{
		this.sheetName = WorkbookUtil.createSafeSheetName(sheetName);
		
		XSSFSheet tempSheet = this.excelWBook.getSheet(this.sheetName);
		if(tempSheet == null){
			if(addIfNotExists){
				this.excelWSheet = this.excelWBook.createSheet(this.sheetName);
				XSSFRow row = this.excelWSheet.createRow(0);
				if(this.createHelper == null){
					createHelper = this.excelWBook.getCreationHelper();
				}
				Cell cell = row.createCell(0);
				cell.setCellValue(createHelper.createRichTextString("Test Parameters"));

				this.flushWorkbook();
				
			}
			else{
				System.out.println("Sheet '" + this.sheetName + "' doesn't exists"
						+ " and addSheet flag is false");
				
				throw new Exception();
			}
		}
		else{
			this.excelWSheet = this.excelWBook.getSheet(this.sheetName);
			
		}
	}
	
	/**
	 * Flush workbook.
	 */
	// Writes the current worksheet to file
		private void flushWorkbook() {
			try{
				FileOutputStream file = new FileOutputStream(this.filePath);
				this.excelWBook.write(file);
				file.close();
			}
			catch(Exception e){
				e.printStackTrace();
			}
		}
	
	/**
	 * Sets the workbook.
	 *
	 * @param path the new workbook
	 * @throws Exception the exception
	 */
	public void setWorkbook(String path) throws Exception{
		this.filePath = path;
		
		// Check if file exists
		File f = null;
		try{
			f = new File(this.filePath);
		}
		catch(Exception e){
	    	e.printStackTrace();
		}
		if(!f.exists() || f.isDirectory()){
		    
		    try{
		    	this.excelWBook = new XSSFWorkbook();
		    	FileOutputStream fileOut = new FileOutputStream(this.filePath);
		    	this.excelWBook.write(fileOut);
		    	fileOut.close();
		    }
		    catch(Exception e){
		    	e.printStackTrace();
		    }

		   
		}
		try{
			FileInputStream inputFile = new FileInputStream(this.filePath);
			this.excelWBook = new XSSFWorkbook(inputFile);
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}


	
	/**
	 * Refresh sheet.
	 */
	private void refreshSheet() {
		try{
			FileInputStream inputFile = new FileInputStream(this.filePath);
			this.excelWBook = new XSSFWorkbook(inputFile);
		}
		catch(Exception e){
			e.printStackTrace();
		}
		this.excelWSheet = this.excelWBook.getSheet(this.sheetName);
	}
	
	/**
	 * Adds the columns.
	 */
	protected void addColumns(){
		int j;
		try{
			this.refreshSheet();
			this.row = this.excelWSheet.getRow(0);
			if (this.row==null){
				 this.row = this.excelWSheet.createRow(0);
			 }
			row.createCell(0).setCellValue("DeviceId");
			row.createCell(1).setCellValue("Status");
			row.createCell(2).setCellValue("AppsNumber");
			row.createCell(3).setCellValue("AppsList");
			int deviceMaxApps = SystemProperties.getDevicemaxapps();
			for (int i = 1; i <= deviceMaxApps; i++) {
				j = i+3;
				row.createCell(j).setCellValue("App"+i);				
			}
			for (int i = 0; i < deviceMaxApps+4; i++) {
				setCellColor(0,i,CellColors.YELLOW);
			}
			
			this.flushWorkbook();
		}
		catch(Exception e){
			e.printStackTrace();
			
			return;
		}
		
	}
	
	/**
	 * Sets the cell color.
	 *
	 * @param row the row
	 * @param col the col
	 * @param color the color
	 */
	public void setCellColor(int row, int col, CellColors color){
	 	CellStyle style = this.excelWBook.createCellStyle();
	 	Font font = this.excelWBook.createFont();
	 	font.setBold(true);
	 	switch(color){
		 	case WHITE:
		 		style.setFillForegroundColor(IndexedColors.WHITE.getIndex());
		 		break;
		 	case YELLOW:
		 		style.setFillForegroundColor(IndexedColors.YELLOW.getIndex());
		 		style.setFont(font);
		 		break;
		 	case RED:
		 		style.setFillForegroundColor(IndexedColors.RED.getIndex());
		 		break;
		 	case GREEN:
		 		style.setFillForegroundColor(IndexedColors.GREEN.getIndex());
		 		break;
	 	}
	    
	    style.setFillPattern(CellStyle.SOLID_FOREGROUND);
	    this.excelWSheet.getRow(row).getCell(col).setCellStyle(style);
	   
	    //Cell cell = this.sheet.getRow(row).createCell(col);
	    //cell.setCellStyle(style);
}

/**
 * The Enum CellColors.
 */
public enum CellColors{
	
	/** The white. */
	WHITE, 
 /** The yellow. */
 YELLOW, 
 /** The red. */
 RED, 
 /** The green. */
 GREEN
}
	
	/**
	 * Write to excel.
	 *
	 * @param devices the devices
	 */
	public void writeToExcel(List<DeviceApps> devices)  {
		int rowNum = 0;
		
		this.refreshSheet();
		for (DeviceApps device : devices) { 
			 ++rowNum;
			 this.row  = this.excelWSheet.getRow(rowNum);
			 if (this.row==null){
				 this.row = this.excelWSheet.createRow(rowNum);
			 }
			 
			 this.cell = row.getCell(0, row.RETURN_BLANK_AS_NULL);
			 if (this.cell == null) {
				 this.cell = this.row.createCell(0);
				 cell.setCellValue(device.getDeviceId());
			 }
			
			this.row = this.excelWSheet.getRow(rowNum);
			row.createCell(0).setCellValue(device.getDeviceId());
			row.createCell(1).setCellValue(device.getStatus());
			row.createCell(2).setCellValue(device.getAppsNum());
			row.createCell(3).setCellValue(device.getApps());
			
			if (device.isErrorFlag()){
				setCellColor(rowNum,3,CellColors.RED);
				continue;
			}
			
			int col = 4;
			for(String singleApp : device.getAppsAsList()){
				row.createCell(col++).setCellValue(singleApp);
			}
			
			if (!device.getStatus().toLowerCase().equals("connected")){
				setCellColor(rowNum,1,CellColors.RED);
			}
			 //System.out.println("Device: " + device.getDeviceId() + ", status:" + device.getStatus() + ", apps:"+device.getApps()); 
		 
		 }
		this.flushWorkbook();
		
	}
}