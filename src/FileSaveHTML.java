import java.awt.FileDialog;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import javax.swing.JFrame;


public class FileSaveHTML {
    public void SaveHTMLEmp (JFrame frame,MyTableModel tableModel){
        FileDialog saveHTML=new FileDialog(frame,"Сохранение отчёта о работниках",FileDialog.SAVE);
        saveHTML.setFile("*.html");
        saveHTML.setVisible(true);
        String fileNameSaveHTML = saveHTML.getDirectory() + saveHTML.getFile();
        PrintWriter pw = null;
        try {
            pw = new PrintWriter(new FileWriter(fileNameSaveHTML));
        } catch (IOException e) {
            e.printStackTrace();
        }
        pw.println("<TABLE BORDER><TR><TH>Имя<TH>Фамилия<TH>Отчество<TH>Должность<TH>Зарплата<TH>Серия/Номер паспорта</TR>");
        int RowCount=tableModel.getRowCount();
        for(int i = 0; i < RowCount; i++) {
            pw.println("<TR><TD>" + tableModel.getValueAt(i,0)
                    + "<TD>" + tableModel.getValueAt(i,1)
                    + "<TD>" + tableModel.getValueAt(i,2)
                    + "<TD>" + tableModel.getValueAt(i,3)
                    + "<TD>" + tableModel.getValueAt(i,4)
                    + "<TD>" + tableModel.getValueAt(i,5));
        }
        pw.println("</TABLE>");
        pw.close();
    }

    public void SaveHTMLCust (JFrame frame,MyTableModel tableModel){
        FileDialog saveHTML=new FileDialog(frame,"Сохранение отчёта о клиентах",FileDialog.SAVE);
        saveHTML.setFile("*.html");
        saveHTML.setVisible(true);
        String fileNameSaveHTML = saveHTML.getDirectory() + saveHTML.getFile();
        PrintWriter pw = null;
        try {
            pw = new PrintWriter(new FileWriter(fileNameSaveHTML));
        } catch (IOException e) {
            e.printStackTrace();
        }
        pw.println("<TABLE BORDER><TR><TH>Имя<TH>Фамилия<TH>Отчество<TH>Номер<TH>Дата засленияе<TH>Дата выселения<TH>Серия/Номер паспорта</TR>");
        int RowCount=tableModel.getRowCount();
        for(int i = 0; i < RowCount; i++) {
            pw.println("<TR><TD>" + tableModel.getValueAt(i,0)
                    + "<TD>" + tableModel.getValueAt(i,1)
                    + "<TD>" + tableModel.getValueAt(i,2)
                    + "<TD>" + tableModel.getValueAt(i,3)
                    + "<TD>" + tableModel.getValueAt(i,4)
                    + "<TD>" + tableModel.getValueAt(i,5)
                    + "<TD>" + tableModel.getValueAt(i,6));
        }
        pw.println("</TABLE>");
        pw.close();
    }
    public void SaveHTMLRoom (JFrame frame,MyTableModel tableModel){
        FileDialog saveHTML=new FileDialog(frame,"Сохранение отчёта о номерах",FileDialog.SAVE);
        saveHTML.setFile("*.html");
        saveHTML.setVisible(true);
        String fileNameSaveHTML = saveHTML.getDirectory() + saveHTML.getFile();
        PrintWriter pw = null;
        try {
            pw = new PrintWriter(new FileWriter(fileNameSaveHTML));
        } catch (IOException e) {
            e.printStackTrace();
        }
        pw.println("<TABLE BORDER><TR><TH>Номер<TH>Вместимость<TH>Тип номера<TH>Цена<TH>Клиенты</TR>");
        int RowCount=tableModel.getRowCount();
        for(int i = 0; i < RowCount; i++) {
            pw.println("<TR><TD>" + tableModel.getValueAt(i,0)
                    + "<TD>" + tableModel.getValueAt(i,1)
                    + "<TD>" + tableModel.getValueAt(i,2)
                    + "<TD>" + tableModel.getValueAt(i,3)
                    + "<TD>" + tableModel.getValueAt(i,4));
        }
        pw.println("</TABLE>");
        pw.close();
    }

}
