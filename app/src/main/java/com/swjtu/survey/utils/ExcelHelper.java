package com.swjtu.survey.utils;

import org.apache.poi.ss.usermodel.Workbook;

import java.io.FileOutputStream;

/**
 * Read and write data for xls/xlsx.
 */
public class ExcelHelper {

    /**
     * write excel to local file
     * @param workbook inited excel-workbook
     * @param fileName created file
     */
    public static void writeWorkBookToFile(Workbook workbook, String fileName) {
        FileOutputStream outputStream = null;
        try {
            outputStream = new FileOutputStream(fileName);
            workbook.write(outputStream);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (outputStream != null) {
                    outputStream.close();
                }
                if (workbook != null) {
                    workbook.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
