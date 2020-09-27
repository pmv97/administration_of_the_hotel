import java.awt.FileDialog;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.swing.JFrame;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class FileSavePDF {
    public void SavePDFEmp(JFrame frame,MyTableModel tableModel) {

        FileDialog savePDF=new FileDialog(frame,"Сохранение отчёта о работниках",FileDialog.SAVE);
        savePDF.setFile("*.pdf");
        savePDF.setVisible(true);
        String fileNameSavePDF = savePDF.getDirectory() + savePDF.getFile();

        Document document = new Document(PageSize.A4,50, 50, 50, 50);

        PdfPTable t = new PdfPTable(tableModel.getColumnCount());

        try {
            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(fileNameSavePDF));
        } catch (FileNotFoundException | DocumentException e) {
            e.printStackTrace();
        }

        BaseFont bFont = null;
        try {
            bFont = BaseFont.createFont("/Windows/Fonts/Verdana.ttf",BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
        } catch (DocumentException | IOException e1) {
            e1.printStackTrace();
        }
        Font font = new Font(bFont, 11,Font.BOLD);

        t.addCell(new PdfPCell(new Phrase("Имя",font)));
        t.addCell(new PdfPCell(new Phrase("Фамилия",font)));
        t.addCell(new PdfPCell(new Phrase("Отчество",font)));
        t.addCell(new PdfPCell(new Phrase("Должность",font)));
        t.addCell(new PdfPCell(new Phrase("Зарплата",font)));
        t.addCell(new PdfPCell(new Phrase("Серия/Номер паспорта",font)));

        int RowCount=tableModel.getRowCount();
        for(int i = 0; i < RowCount; i++){
            t.addCell(new Phrase((String) tableModel.getValueAt(i,0),font));
            t.addCell(new Phrase((String) tableModel.getValueAt(i,1),font));
            t.addCell(new Phrase((String) tableModel.getValueAt(i,2),font));
            t.addCell(new Phrase((String) tableModel.getValueAt(i,3),font));
            t.addCell(new Phrase((String) tableModel.getValueAt(i,4),font));
            t.addCell(new Phrase((String) tableModel.getValueAt(i,5),font));
        }
        document.open();
        try {
            document.add(t);
        } catch (DocumentException e) {
            e.printStackTrace();
        }

        document.close();
    }

    public void SavePDFCust(JFrame frame,MyTableModel tableModel) {

        FileDialog savePDF=new FileDialog(frame,"Сохранение отчёта о клиентах",FileDialog.SAVE);
        savePDF.setFile("*.pdf");
        savePDF.setVisible(true);
        String fileNameSavePDF = savePDF.getDirectory() + savePDF.getFile();

        Document document = new Document(PageSize.A4,50, 50, 50, 50);

        PdfPTable t = new PdfPTable(tableModel.getColumnCount());

        try {
            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(fileNameSavePDF));
        } catch (FileNotFoundException | DocumentException e) {
            e.printStackTrace();
        }

        BaseFont bFont = null;
        try {
            bFont = BaseFont.createFont("/Windows/Fonts/Verdana.ttf",BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
        } catch (DocumentException | IOException e1) {
            e1.printStackTrace();
        }
        Font font = new Font(bFont, 11,Font.BOLD);

        t.addCell(new PdfPCell(new Phrase("Имя",font)));
        t.addCell(new PdfPCell(new Phrase("Фамилия",font)));
        t.addCell(new PdfPCell(new Phrase("Отчество",font)));
        t.addCell(new PdfPCell(new Phrase("Номер",font)));
        t.addCell(new PdfPCell(new Phrase("Дата заселения",font)));
        t.addCell(new PdfPCell(new Phrase("Дата выселения",font)));
        t.addCell(new PdfPCell(new Phrase("Серия/Номер паспорта",font)));

        int RowCount=tableModel.getRowCount();
        for(int i = 0; i < RowCount; i++){
            t.addCell(new Phrase((String) tableModel.getValueAt(i,0),font));
            t.addCell(new Phrase((String) tableModel.getValueAt(i,1),font));
            t.addCell(new Phrase((String) tableModel.getValueAt(i,2),font));
            t.addCell(new Phrase((String) tableModel.getValueAt(i,3),font));
            t.addCell(new Phrase((String) tableModel.getValueAt(i,4),font));
            t.addCell(new Phrase((String) tableModel.getValueAt(i,5),font));
            t.addCell(new Phrase((String) tableModel.getValueAt(i,6),font));
        }
        document.open();
        try {
            document.add(t);
        } catch (DocumentException e) {
            e.printStackTrace();
        }

        document.close();
    }

    public void SavePDFRoom(JFrame frame,MyTableModel tableModel) {

        FileDialog savePDF=new FileDialog(frame,"Сохранение отчёта о номерах",FileDialog.SAVE);
        savePDF.setFile("*.pdf");
        savePDF.setVisible(true);
        String fileNameSavePDF = savePDF.getDirectory() + savePDF.getFile();

        Document document = new Document(PageSize.A4,50, 50, 50, 50);

        PdfPTable t = new PdfPTable(tableModel.getColumnCount());

        try {
            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(fileNameSavePDF));
        } catch (FileNotFoundException | DocumentException e) {
            e.printStackTrace();
        }

        BaseFont bFont = null;
        try {
            bFont = BaseFont.createFont("/Windows/Fonts/Verdana.ttf",BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
        } catch (DocumentException | IOException e1) {
            e1.printStackTrace();
        }
        Font font = new Font(bFont, 11,Font.BOLD);

        t.addCell(new PdfPCell(new Phrase("Номер",font)));
        t.addCell(new PdfPCell(new Phrase("Вместимость",font)));
        t.addCell(new PdfPCell(new Phrase("Тип номера",font)));
        t.addCell(new PdfPCell(new Phrase("Цена",font)));
        t.addCell(new PdfPCell(new Phrase("Клиенты",font)));

        int RowCount=tableModel.getRowCount();
        for(int i = 0; i < RowCount; i++){
            t.addCell(new Phrase((String) tableModel.getValueAt(i,0),font));
            t.addCell(new Phrase((String) tableModel.getValueAt(i,1),font));
            t.addCell(new Phrase((String) tableModel.getValueAt(i,2),font));
            t.addCell(new Phrase((String) tableModel.getValueAt(i,3),font));
            t.addCell(new Phrase((String) tableModel.getValueAt(i,4),font));
        }

        document.open();

        try {
            document.add(t);
        } catch (DocumentException e) {
            e.printStackTrace();
        }

        document.close();
    }
}
