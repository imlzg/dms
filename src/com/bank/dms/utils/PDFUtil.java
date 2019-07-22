package com.bank.dms.utils;
import java.io.FileOutputStream;

import java.io.IOException;
import java.util.List;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class PDFUtil {
    private static final String[] TABLE_LEFT_CONTENT = {"项目名称：", "项目负责人：", "开始时间：", "结束时间："};
    
    
    public static boolean createPDF(String pdfPath, String[] table_right_content, List<Object[]> list) {

        Document document = new Document();

        document.setPageSize(PageSize.A4);

        try {
            PdfWriter.getInstance(document, new FileOutputStream(pdfPath));
            document.open();
            BaseFont bfChinese = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
            Font titleFont = new Font(bfChinese, 15, Font.BOLD);
            Font headFont = new Font(bfChinese, 10, Font.BOLD);

            float[] widths = { 240f, 60f, 320f, 120f, 110f, 110f, 190f };
            PdfPTable table = new PdfPTable(widths);
            table.setTotalWidth(535);
            table.setLockedWidth(false);
            table.getDefaultCell().setBorder(1);
            
            PdfPCell cell = new PdfPCell(new Paragraph(Paragraph.ALIGN_RIGHT, "项目", titleFont));
            cell.setBorder(0);
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            table.addCell(cell);
            cell = new PdfPCell(new Paragraph("", headFont));
            cell.setBorder(0);
            cell.setFixedHeight(20);
            cell.setColspan(7);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table.addCell(cell);
            cell = new PdfPCell(new Paragraph(Paragraph.ALIGN_RIGHT, "", titleFont));
            cell.setBorder(0);
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            table.addCell(cell);
            cell = new PdfPCell(new Paragraph("", headFont));
            cell.setBorder(0);
            cell.setFixedHeight(20);
            cell.setColspan(7);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table.addCell(cell);
            
            for (int i = 0; i < TABLE_LEFT_CONTENT.length; i++) {
                cell = new PdfPCell(new Paragraph(Paragraph.ALIGN_RIGHT, TABLE_LEFT_CONTENT[i], headFont));// 建立一个单元格
                cell.setBorder(0);
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                table.addCell(cell);
                cell = new PdfPCell(new Paragraph(table_right_content[i], headFont));
                cell.setBorder(0);
                cell.setFixedHeight(20);
                cell.setColspan(7);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                table.addCell(cell);
            }
            
            
            cell = new PdfPCell(new Paragraph(Paragraph.ALIGN_RIGHT, "", headFont));
            cell.setBorder(0);
            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            table.addCell(cell);
            cell = new PdfPCell(new Paragraph("", headFont));
            cell.setBorder(0);
            cell.setFixedHeight(20);
            cell.setColspan(7);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table.addCell(cell);
            
            cell = new PdfPCell(new Paragraph(Paragraph.ALIGN_RIGHT, "日报日期：", titleFont));
            cell.setBorder(0);
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            table.addCell(cell);
            cell = new PdfPCell(new Paragraph(DayUtil.getDate(), headFont));
            cell.setBorder(0);
            cell.setFixedHeight(20);
            cell.setColspan(7);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table.addCell(cell);
            
            cell = new PdfPCell(new Paragraph(Paragraph.ALIGN_RIGHT, "", headFont)); 
            cell.setBorder(0);
            // cell.setColspan(7);
            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            table.addCell(cell);
            cell = new PdfPCell(new Paragraph("", headFont));
            cell.setBorder(0);
            cell.setFixedHeight(20);
            cell.setColspan(7);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table.addCell(cell);
            int size = 0;
            if(list != null){
                size = list.size();
            }
            for (int i = 0; i < size; i++) {
                cell = new PdfPCell(new Paragraph(Paragraph.ALIGN_RIGHT, list.get(i)[0].toString(), titleFont));// 建立一个单元格
                cell.setBorder(0);
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.addCell(cell);
                cell = new PdfPCell(new Paragraph(list.get(i)[1].toString(), headFont));
                cell.setBorder(0);
                cell.setFixedHeight(0);
                cell.setColspan(7);
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                table.addCell(cell);
                
                cell = new PdfPCell(new Paragraph(Paragraph.ALIGN_RIGHT, "", headFont));
                cell.setBorder(0);
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                table.addCell(cell);
                cell = new PdfPCell(new Paragraph("", headFont));
                cell.setBorder(0);
                cell.setFixedHeight(20);
                cell.setColspan(7);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                table.addCell(cell);
            }

            document.add(table);

        } catch (DocumentException de) {
            de.printStackTrace();

            System.err.println("ff");

            return false;

        }

        catch (IOException ioe) {

            System.err.println(ioe.getMessage());

            return false;

        }

        document.close();

        return true;

    }

}
