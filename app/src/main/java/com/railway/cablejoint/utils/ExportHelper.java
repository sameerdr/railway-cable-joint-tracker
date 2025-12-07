package com.railway.cablejoint.utils;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.railway.cablejoint.models.JointData;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xwpf.usermodel.*;
import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

public class ExportHelper {

    public static void exportToExcel(List<JointData> dataList, File file) throws Exception {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Joint Data");

        // Create header row
        Row headerRow = sheet.createRow(0);
        String[] headers = {"Date", "Section", "Sub-Section", "KM", "OHE Mast", 
                "Offset", "Cable Type", "Joint Type", "Latitude", "Longitude", 
                "Reason", "Staff Present", "Remark"};

        CellStyle headerStyle = workbook.createCellStyle();
        Font headerFont = workbook.createFont();
        headerFont.setBold(true);
        headerStyle.setFont(headerFont);

        for (int i = 0; i < headers.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(headers[i]);
            cell.setCellStyle(headerStyle);
        }

        // Fill data rows
        int rowNum = 1;
        for (JointData data : dataList) {
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(data.getDate());
            row.createCell(1).setCellValue(data.getSection());
            row.createCell(2).setCellValue(data.getSubSection());
            row.createCell(3).setCellValue(data.getKm());
            row.createCell(4).setCellValue(data.getOheMast());
            row.createCell(5).setCellValue(data.getOffsetFromTrack());
            row.createCell(6).setCellValue(data.getCableType());
            row.createCell(7).setCellValue(data.getJointType());
            row.createCell(8).setCellValue(data.getLatitude());
            row.createCell(9).setCellValue(data.getLongitude());
            row.createCell(10).setCellValue(data.getReason());
            row.createCell(11).setCellValue(data.getStaffPresent());
            row.createCell(12).setCellValue(data.getRemark());
        }

        // Auto-size columns
        for (int i = 0; i < headers.length; i++) {
            sheet.autoSizeColumn(i);
        }

        FileOutputStream outputStream = new FileOutputStream(file);
        workbook.write(outputStream);
        workbook.close();
        outputStream.close();
    }

    public static void exportToWord(List<JointData> dataList, File file) throws Exception {
        XWPFDocument document = new XWPFDocument();

        // Title
        XWPFParagraph title = document.createParagraph();
        title.setAlignment(ParagraphAlignment.CENTER);
        XWPFRun titleRun = title.createRun();
        titleRun.setText("Railway Cable Joint Report");
        titleRun.setBold(true);
        titleRun.setFontSize(16);

        // Create table
        XWPFTable table = document.createTable();
        
        // Header row
        XWPFTableRow headerRow = table.getRow(0);
        String[] headers = {"Date", "Section", "Sub-Section", "KM", "Cable Type", 
                "Joint Type", "Location"};
        
        for (int i = 0; i < headers.length; i++) {
            if (i == 0) {
                headerRow.getCell(0).setText(headers[i]);
            } else {
                headerRow.addNewTableCell().setText(headers[i]);
            }
        }

        // Data rows
        for (JointData data : dataList) {
            XWPFTableRow row = table.createRow();
            row.getCell(0).setText(data.getDate());
            row.getCell(1).setText(data.getSection());
            row.getCell(2).setText(data.getSubSection());
            row.getCell(3).setText(data.getKm());
            row.getCell(4).setText(data.getCableType());
            row.getCell(5).setText(data.getJointType());
            row.getCell(6).setText(String.format("%.6f, %.6f", 
                    data.getLatitude(), data.getLongitude()));
        }

        FileOutputStream outputStream = new FileOutputStream(file);
        document.write(outputStream);
        document.close();
        outputStream.close();
    }

    public static void exportToPDF(List<JointData> dataList, File file) throws Exception {
        PdfWriter writer = new PdfWriter(file);
        PdfDocument pdf = new PdfDocument(writer);
        Document document = new Document(pdf);

        // Title
        Paragraph title = new Paragraph("Railway Cable Joint Report")
                .setFontSize(18)
                .setBold();
        document.add(title);

        // Create table
        float[] columnWidths = {2, 2, 2, 2, 2, 2, 3};
        Table table = new Table(columnWidths);
        table.setWidth(com.itextpdf.layout.properties.UnitValue.createPercentValue(100));

        // Header
        String[] headers = {"Date", "Section", "Sub-Section", "KM", "Cable Type", 
                "Joint Type", "Location"};
        for (String header : headers) {
            table.addHeaderCell(new com.itextpdf.layout.element.Cell()
                    .add(new Paragraph(header).setBold()));
        }

        // Data rows
        for (JointData data : dataList) {
            table.addCell(data.getDate());
            table.addCell(data.getSection());
            table.addCell(data.getSubSection());
            table.addCell(data.getKm());
            table.addCell(data.getCableType());
            table.addCell(data.getJointType());
            table.addCell(String.format("%.6f, %.6f", 
                    data.getLatitude(), data.getLongitude()));
        }

        document.add(table);
        document.close();
    }
}
