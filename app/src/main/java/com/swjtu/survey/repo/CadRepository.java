package com.swjtu.survey.repo;
import com.android.framekit.repo.IRepository;
import com.swjtu.survey.bean.sheet.MeasureCLBean;
import com.swjtu.survey.utils.ExcelHelper;
import com.swjtu.survey.utils.LogUtil;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class CadRepository implements IRepository {
    public boolean saveCLData(String path,List<MeasureCLBean> clMeasures) {

        File file = new File(path+"/testcl.xlsx");
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            initWorkBook("cad_sheet1",file.getAbsolutePath());
            return true;
        }catch (Exception e){
            LogUtil.i(null,"-----"+e.getMessage());
            return false;
        } finally {

        }
    }

    public void initWorkBook(String sheetName,String fileName) {
        //init .xlsx file.The xls is Workbook instant
        XSSFWorkbook mExcelWorkbook = new XSSFWorkbook();
        //create sheet
        Sheet sheet = mExcelWorkbook.createSheet();
        mExcelWorkbook.setSheetName(0, sheetName == null ? "sheet1" : sheetName);
        //start-------create cad title
        Row rowTitle = sheet.createRow(0);
        rowTitle.setHeightInPoints(54);
        Cell title = rowTitle.createCell(0);
        title.setCellValue("线路逐桩坐标高程表");
        //end---------create cad title

        //start--------create function title
        Row functionTitle = sheet.createRow(1);
        functionTitle.setHeightInPoints(36);
        Cell mileageTitle = functionTitle.createCell(0);
        mileageTitle.setCellValue("冠号");
        Cell numTitle = functionTitle.createCell(1);
        numTitle.setCellValue("序号");
        Cell mileageNumTitle = functionTitle.createCell(2);
        mileageNumTitle.setCellValue("中桩里程");
        Cell mileageXTitle = functionTitle.createCell(3);
        mileageXTitle.setCellValue("X");
        Cell mileageYTitle = functionTitle.createCell(4);
        mileageYTitle.setCellValue("Y");
        Cell mileageHTitle = functionTitle.createCell(5);
        mileageHTitle.setCellValue("H");
        Cell mileageDetailTitle = functionTitle.createCell(6);
        mileageDetailTitle.setCellValue("备注");
        //end----------create function title

        //merge cell
        CellRangeAddress cadTitleRegion = new CellRangeAddress(0,0,0,6);
        sheet.addMergedRegion(cadTitleRegion);

        ExcelHelper.writeWorkBookToFile(mExcelWorkbook,fileName);
    }
}
