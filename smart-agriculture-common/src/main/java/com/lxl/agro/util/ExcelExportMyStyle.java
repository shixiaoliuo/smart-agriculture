package com.lxl.agro.util;

import cn.afterturn.easypoi.excel.export.styler.AbstractExcelExportStyler;
import cn.afterturn.easypoi.excel.export.styler.IExcelExportStyler;
import org.apache.poi.ss.usermodel.*;
/**
 * @Author：xudeming
 * @Package：com.qf.agro.util
 * @Project：smart-agriculture-parent
 * @name：SendSMS
 * @Date：2023/3/26 20:35
 * @Filename：SendSMS
 */
public class ExcelExportMyStyle extends AbstractExcelExportStyler implements IExcelExportStyler {
    public ExcelExportMyStyle(Workbook workbook) {
        super.createStyles(workbook);
    }

    @Override
    public CellStyle getTitleStyle(short color) {
        CellStyle titleStyle = workbook.createCellStyle();
        Font font = workbook.createFont();
        font.setBold(true);// 加粗
        titleStyle.setFont(font);
        titleStyle.setAlignment(HorizontalAlignment.CENTER);// 居中
        titleStyle.setVerticalAlignment(VerticalAlignment.CENTER);// 垂直居中
        titleStyle.setFillForegroundColor(IndexedColors.AQUA.index);// 设置颜色
        titleStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        titleStyle.setBorderRight(BorderStyle.THIN);
        titleStyle.setWrapText(true);
        return titleStyle;
    }

    @SuppressWarnings("deprecation")
    @Override
    public CellStyle stringSeptailStyle(Workbook workbook, boolean isWarp) {
        CellStyle style = workbook.createCellStyle();
        style.setAlignment(CellStyle.ALIGN_CENTER);
        style.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
        style.setDataFormat(STRING_FORMAT);
        if (isWarp) {
            style.setWrapText(true);
        }
        return style;
    }

    @Override
    public CellStyle getHeaderStyle(short color) {
        CellStyle titleStyle = workbook.createCellStyle();
        Font font = workbook.createFont();
        font.setBold(true);// 加粗
        font.setColor(IndexedColors.RED.index);
        font.setFontHeightInPoints((short) 11);
        titleStyle.setFont(font);
        titleStyle.setAlignment(HorizontalAlignment.CENTER);// 居中
        titleStyle.setFillForegroundColor(IndexedColors.WHITE.index);// 设置颜色
        titleStyle.setVerticalAlignment(VerticalAlignment.CENTER);// 垂直居中
        titleStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        titleStyle.setBorderRight(BorderStyle.THIN);
        titleStyle.setWrapText(true);
        return titleStyle;
    }

    @SuppressWarnings("deprecation")
    @Override
    public CellStyle stringNoneStyle(Workbook workbook, boolean isWarp) {
        CellStyle style = workbook.createCellStyle();
        style.setAlignment(CellStyle.ALIGN_CENTER);
        style.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
        style.setDataFormat(STRING_FORMAT);
        if (isWarp) {
            style.setWrapText(true);
        }
        return style;
    }



}
