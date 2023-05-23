package com.lxl.agro.util;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import org.apache.poi.ss.usermodel.Workbook;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
/**
 * @Author：xudeming
 * @Package：com.qf.agro.util
 * @Project：smart-agriculture-parent
 * @name：SendSMS
 * @Date：2023/3/26 20:35
 * @Filename：SendSMS
 */
public class MyExcelExportUtil {
    /**
     * Excel文件导出,导出的文件名默认为:headTitle+当前系统时间
     * @param listData 要导出的list数据
     * @param pojoClass 定义excel属性信息
     * @param headTitle Excel文件头信息
     * @param sheetName Excel文件sheet名称
     * @param response
     */
    public static void exportExcel(Collection<?> listData, Class<?> pojoClass, String headTitle, String sheetName, HttpServletResponse response) {
        ExportParams params = new ExportParams(headTitle, sheetName);
        params.setHeight((short) 8);
        params.setStyle(ExcelExportMyStyle.class);
        try {
            Workbook workbook = ExcelExportUtil.exportExcel(params, pojoClass, listData);
            String fileName = headTitle + new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
            fileName = URLEncoder.encode(fileName, "UTF8");
            response.setContentType("application/vnd.ms-excel;chartset=utf-8");
            response.setHeader("Content-Disposition", "attachment;filename="+fileName + ".xls");
            ServletOutputStream out=response.getOutputStream();
            workbook.write(out);
            out.flush();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
