package com.example.demo.service;

import com.example.demo.entity.Selling;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReportService {

    private XSSFRow xssfRow;

    XSSFRow rowInTable;

    XSSFCellStyle cellStyleCenterBackgroundGreenWithBorder;
    XSSFCellStyle cellStyleCenterOnlyFontWithBorderWithBold;
    XSSFCellStyle cellStyleCenterBackgroundOrangeWithBorder;

    XSSFCell cell1;
    XSSFCell cell2;
    XSSFCell cell3;
    XSSFCell cell4;
    XSSFCell cell5;
    XSSFCell cell6;
    XSSFCell cell7;
    XSSFCell cell8;
    XSSFCell cell9;


    public void createRows(XSSFWorkbook workbook, List<Selling> sellingList){


        XSSFSheet sheet = workbook.createSheet("sotilgan");

        for (int column = 1; column <= 9; column++) {
            sheet.setColumnWidth(column, 256 * 22);
        }

        cellStyleCenterBackgroundGreenWithBorder = ExcelStyle.cellStyleCenterBackgroundGreenWithBorder(workbook);
        cellStyleCenterOnlyFontWithBorderWithBold = ExcelStyle.cellStyleCenterOnlyFontWithBorderWithBold(workbook);
        cellStyleCenterBackgroundOrangeWithBorder = ExcelStyle.cellStyleCenterBackgroundOrangeWithBorder(workbook);

        rowInTable = sheet.createRow(3);
        rowInTable.setHeight((short) 500);

        cell1 = rowInTable.createCell(1, CellType.STRING);
        cell1.setCellStyle(cellStyleCenterBackgroundGreenWithBorder);
        cell1.setCellValue("Id");

        cell2 = rowInTable.createCell(2, CellType.STRING);
        cell2.setCellStyle(cellStyleCenterBackgroundGreenWithBorder);
        cell2.setCellValue("Ro'yxat raqami");

        cell3 = rowInTable.createCell(3, CellType.STRING);
        cell3.setCellStyle(cellStyleCenterBackgroundGreenWithBorder);
        cell3.setCellValue("Sana");

        cell4 = rowInTable.createCell(4, CellType.STRING);
        cell4.setCellStyle(cellStyleCenterBackgroundGreenWithBorder);
        cell4.setCellValue("To'lov turi");

        cell5 = rowInTable.createCell(5, CellType.STRING);
        cell5.setCellStyle(cellStyleCenterBackgroundGreenWithBorder);
        cell5.setCellValue("Maxsulot turi");

        cell6 = rowInTable.createCell(6, CellType.STRING);
        cell6.setCellStyle(cellStyleCenterBackgroundGreenWithBorder);
        cell6.setCellValue("O'lchov birliki");

        cell7 = rowInTable.createCell(7, CellType.STRING);
        cell7.setCellStyle(cellStyleCenterBackgroundGreenWithBorder);
        cell7.setCellValue("Miqdor");

        cell8 = rowInTable.createCell(8, CellType.STRING);
        cell8.setCellStyle(cellStyleCenterBackgroundGreenWithBorder);
        cell8.setCellValue("Narx");

        cell9 = rowInTable.createCell(9, CellType.STRING);
        cell9.setCellStyle(cellStyleCenterBackgroundGreenWithBorder);
        cell9.setCellValue("Qiymat");

        int counter = 4;
        for(Selling selling : sellingList){
            rowInTable = sheet.createRow(counter);

            cell1 = rowInTable.createCell(1, CellType.STRING);
            cell1.setCellStyle(cellStyleCenterOnlyFontWithBorderWithBold);
            cell1.setCellValue(selling.getId() + "");

            cell2 = rowInTable.createCell(2, CellType.STRING);
            cell2.setCellStyle(cellStyleCenterOnlyFontWithBorderWithBold);
            cell2.setCellValue(selling.getListNumber());

            cell3 = rowInTable.createCell(3, CellType.STRING);
            cell3.setCellStyle(cellStyleCenterOnlyFontWithBorderWithBold);
            cell3.setCellValue(String.valueOf(selling.getDate()));

            cell4 = rowInTable.createCell(4, CellType.STRING);
            cell4.setCellStyle(cellStyleCenterOnlyFontWithBorderWithBold);
            cell4.setCellValue(selling.getPaymentType().getName());

            cell5 = rowInTable.createCell(5, CellType.STRING);
            cell5.setCellStyle(cellStyleCenterOnlyFontWithBorderWithBold);
            cell5.setCellValue(selling.getProductType().getName());

            cell6 = rowInTable.createCell(6, CellType.STRING);
            cell6.setCellStyle(cellStyleCenterOnlyFontWithBorderWithBold);
            cell6.setCellValue(selling.getUnitType().getName());

            cell7 = rowInTable.createCell(7, CellType.NUMERIC);
            cell7.setCellStyle(cellStyleCenterOnlyFontWithBorderWithBold);
            cell7.setCellValue(selling.getAmount());

            cell8 = rowInTable.createCell(8, CellType.NUMERIC);
            cell8.setCellStyle(cellStyleCenterOnlyFontWithBorderWithBold);
            cell8.setCellValue(selling.getCost());

            cell9 = rowInTable.createCell(9, CellType.NUMERIC);
            cell9.setCellStyle(cellStyleCenterOnlyFontWithBorderWithBold);
            cell9.setCellValue(selling.getValue());

            counter++;
        }

        rowInTable = sheet.createRow(counter);
        sheet.addMergedRegion(new CellRangeAddress(counter, counter, 1, 6));

        cell1 = rowInTable.createCell(1, CellType.STRING);
        cell1.setCellStyle(cellStyleCenterBackgroundOrangeWithBorder);
        cell1.setCellValue("Jami");

        cell2 = rowInTable.createCell(2, CellType.STRING);
        cell2.setCellStyle(cellStyleCenterBackgroundOrangeWithBorder);

        cell3 = rowInTable.createCell(3, CellType.STRING);
        cell3.setCellStyle(cellStyleCenterBackgroundOrangeWithBorder);

        cell4 = rowInTable.createCell(4, CellType.STRING);
        cell4.setCellStyle(cellStyleCenterBackgroundOrangeWithBorder);

        cell5 = rowInTable.createCell(5, CellType.STRING);
        cell5.setCellStyle(cellStyleCenterBackgroundOrangeWithBorder);

        cell6 = rowInTable.createCell(6, CellType.STRING);
        cell6.setCellStyle(cellStyleCenterBackgroundOrangeWithBorder);

        cell7 = rowInTable.createCell(7, CellType.FORMULA);
        cell7.setCellStyle(cellStyleCenterBackgroundOrangeWithBorder);
        cell7.setCellFormula("SUM(H4:H" + (sellingList.size() + 4) + ")");

        cell8 = rowInTable.createCell(8, CellType.STRING);
        cell8.setCellStyle(cellStyleCenterBackgroundOrangeWithBorder);
        cell8.setCellValue("x");

        cell9 = rowInTable.createCell(9, CellType.FORMULA);
        cell9.setCellStyle(cellStyleCenterBackgroundOrangeWithBorder);
        cell9.setCellFormula("SUM(J4:J" + (sellingList.size() + 4) + ")");

    }

}
