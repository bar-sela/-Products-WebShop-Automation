package utilities;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ExcelReader {
    public static Object[][] readExcelFile(String filePath) {
        List<Object[]> excelData = new ArrayList<>();

        try {
            FileInputStream fis = new FileInputStream(new File(filePath));
            Workbook workbook = WorkbookFactory.create(fis);
            Sheet sheet = workbook.getSheetAt(0);

            boolean firstRow = true;
            for (Row row : sheet) {
                if (firstRow) {
                    firstRow = false;
                    continue; // Skip the first row
                }

                List<Object> rowData = new ArrayList<>();
                for (Cell cell : row) {
                    switch (cell.getCellType()) {
                        case STRING -> rowData.add(cell.getStringCellValue());
                        case NUMERIC -> rowData.add(cell.getNumericCellValue());
                        case BOOLEAN -> rowData.add(cell.getBooleanCellValue());
                        case BLANK -> rowData.add("Blank Cell");
                        default -> rowData.add("Unknown type");
                    }
                }
                excelData.add(rowData.toArray());
            }

            workbook.close();
            fis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return excelData.toArray(new Object[0][]);
    }

    public static void main(String[] args) {
        String filePath = "C:/Users/barse/IdeaProjects/untitled6/src/main/resources/ex.xlsx"; // Replace with the path to your Excel file

        Object[][] excelData = readExcelFile(filePath);

        // Display the retrieved data
        for (Object[] row : excelData) {
            for (Object cell : row) {
                System.out.print(cell + "\t\t");
            }
            System.out.println();
        }
    }
}
