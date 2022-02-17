import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;

public class Excel {
    XSSFWorkbook wb;
    XSSFSheet sheet1;


    public Excel(String excelpath) {
        try {
            //creating new object
            File src = new File(excelpath);
            FileInputStream fis = new FileInputStream(src);

            wb = new XSSFWorkbook(fis);


        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    //Fetching data from Excel file and reading number of rows,column in Excel file//

    public String getData(int sheetNumber, int row, int column) {
        sheet1 = wb.getSheetAt(0);
        String data = sheet1.getRow(row).getCell(column).getStringCellValue();
        return data;
    }
}
