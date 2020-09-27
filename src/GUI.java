import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.TableColumn;

import java.awt.event.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Vector;

public class GUI extends JFrame{

    ArrayList<Employee> empl=new ArrayList<>();
    ArrayList<Customer> cust=new ArrayList<>();
    ArrayList<HotelRoom> room=new ArrayList<>();

    JMenuBar menuBar;
    JMenu file,view,program;
    JMenuItem saveXMLMenuIt,openXMLMenuIt,saveHTMLMenuIt,savePDFMenuIt,exitMenuIt,freeNumMenuIt,settingsMenuIt;
    TextField search;
    JLabel sLabel;
    JComboBox<String> searchBox;
    JLabel searchIcon;
    JButton addBut,remBut,editBut,upBut,downBut;
    JToolBar toolBar;
    JTable tableEmp,tableCust,tableRoom;
    MyTableModel tableModelEmp,tableModelCust,tableModelRoom;
    MyTableModel tableModelBuffEmp,tableModelBuffCust,tableModelBuffRoom;
    JScrollPane scrPaneTabEmp,scrPaneTabCust,scrPaneTabRoom;
    JTabbedPane paneTabbed;

    private final String PathEmpl="myFiles/emp.xml";
    private final String PathCust="myFiles/cust.xml";
    private final String PathRoom="myFiles/room.xml";

    boolean openCust = false;
    boolean openRoom = false;

    int[] price =new int[] {5000,10000,15000,30000,7000,14000,17000,40000};
    String[] sortEmp;
    String[] sortCust;
    String[] sortRoom;

    ButActionListener ButActLisn=new ButActionListener();
    FieldTextListener TextLisn=new FieldTextListener();
    CustChangeListener ChangeLisn=new CustChangeListener();

    public void setSizeAndLocation() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension frameSize=new Dimension(getSize());
        int locationX = (screenSize.width - frameSize.width) / 2;
        int locationY = (screenSize.height - frameSize.height) / 2;
        setLocation(locationX,locationY);
    }

    private void AddMenu() {
        menuBar=new JMenuBar();
        menuBar.add(file=new JMenu("Файл"));
        menuBar.add(view=new JMenu("Просмотр"));
        menuBar.add(program=new JMenu("Программа"));
        file.add(openXMLMenuIt=new JMenuItem("Открыть XML"));
        file.add(saveXMLMenuIt=new JMenuItem("Сохранить XML"));
        file.add(saveHTMLMenuIt=new JMenuItem("Сохранить HTML"));
        file.add(savePDFMenuIt=new JMenuItem("Сохранить PDF"));
        file.add(exitMenuIt=new JMenuItem("Выйти"));
        view.add(freeNumMenuIt=new JMenuItem("Свободные номера"));
        program.add(settingsMenuIt=new JMenuItem("Найстройки"));
        setJMenuBar(menuBar);
    }

    private void AddToolBar() {
        toolBar=new JToolBar();
        toolBar.add(addBut=new JButton(new ImageIcon("Icon/addElTable.png")));
        toolBar.add(remBut=new JButton(new ImageIcon("Icon/remElTable.png")));
        toolBar.add(editBut=new JButton(new ImageIcon("Icon/editElTable.png")));
        addBut.setToolTipText("Добавить");
        remBut.setToolTipText("Удалить");
        editBut.setToolTipText("Редактировать");
        toolBar.addSeparator();
        toolBar.add(new JSeparator(SwingConstants.VERTICAL));
        toolBar.addSeparator();
        toolBar.add(upBut=new JButton(new ImageIcon("Icon/arrow_up.png")));
        toolBar.add(downBut=new JButton(new ImageIcon("Icon/arrow_down.png")));
        upBut.setToolTipText("Переместить вверх");
        downBut.setToolTipText("Переместить вниз");
        toolBar.addSeparator();
        toolBar.add(new JSeparator(SwingConstants.VERTICAL));
        toolBar.addSeparator();
        toolBar.add(sLabel=new JLabel("Поиск по:"));
        toolBar.addSeparator();
        toolBar.add(searchBox=new JComboBox<String>(sortCust));
        toolBar.addSeparator();
        toolBar.add(search=new TextField(20));
        toolBar.add(searchIcon=new JLabel(new ImageIcon("Icon/search.png")));
        toolBar.setFloatable(false);
    }

    public GUI(){

        super("Администратор гостиницы");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        JDialog.setDefaultLookAndFeelDecorated(true);

        HotelRoom.setPrice(price);

        Object[] headersEmp=new Object[] {"Имя","Фамилия","Отчество","Должность","Зарплата","Серия Номер паспорта"};
        Object[] headersCust=new Object[] {"Имя","Фамилия","Отчество","Номер",
                "Дата заселения","Дата выселения","Серия Номер паспорта"};
        Object[] headersRoom =new Object[] {"Номер","Вместимость","Тип","Цена","Клиент[ы]"};
        String[] tabs =new String[] {"Клиенты","Номера","Работники"};

        sortEmp=new String[] {"Имени","Фамилии","Отчеству","Должности"};
        sortCust=new String[] {"Имени","Фамилии","Отчеству","Номеру"};
        sortRoom=new String[] {"Номеру","Вместимости","Типу"};

        tableModelEmp=new MyTableModel();
        tableModelEmp.setColumnIdentifiers(headersEmp);
        tableModelCust=new MyTableModel();
        tableModelCust.setColumnIdentifiers(headersCust);
        tableModelRoom=new MyTableModel();
        tableModelRoom.setColumnIdentifiers(headersRoom);

        tableEmp=new JTable(tableModelEmp);
        tableCust=new JTable(tableModelCust);
        tableRoom=new JTable(tableModelRoom);
        TableColumn tCol=new TableColumn();

        scrPaneTabEmp=new JScrollPane(tableEmp);
        scrPaneTabCust=new JScrollPane(tableCust);
        scrPaneTabRoom=new JScrollPane(tableRoom);
        paneTabbed=new JTabbedPane(JTabbedPane.TOP);

        paneTabbed.addTab(tabs[0],new ImageIcon("tabs.png"),scrPaneTabCust,"Список клиентов");
        paneTabbed.addTab(tabs[1],new ImageIcon("tabs.png"),scrPaneTabRoom,"Список номеров");
        paneTabbed.addTab(tabs[2],new ImageIcon("tabs.png"),scrPaneTabEmp,"Список работников");


        //подключаем меню
        AddMenu();
        //подключаем панель инструментов
        AddToolBar();

        //открываем файлы XML
        //openXML();

        //подключаем слушателей
        paneTabbed.addChangeListener(ChangeLisn);
        openXMLMenuIt.addActionListener(ButActLisn);
        saveXMLMenuIt.addActionListener(ButActLisn);
        saveHTMLMenuIt.addActionListener(ButActLisn);
        savePDFMenuIt.addActionListener(ButActLisn);
        exitMenuIt.addActionListener(ButActLisn);
        freeNumMenuIt.addActionListener(ButActLisn);
        settingsMenuIt.addActionListener(ButActLisn);
        addBut.addActionListener(ButActLisn);
        remBut.addActionListener(ButActLisn);
        editBut.addActionListener(ButActLisn);
        upBut.addActionListener(ButActLisn);
        downBut.addActionListener(ButActLisn);
        search.addTextListener(TextLisn);

        getContentPane().add(toolBar, "North");
        getContentPane().add(paneTabbed);

        pack();
    }

    public class ButActionListener implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            if (e.getSource()==openXMLMenuIt) {
                switch (paneTabbed.getSelectedIndex()) {
                    case 0:
                        openXML(0);
                        break;
                    case 1:
                        openXML(1);
                        break;
                    case 2:
                        openXML(2);
                        break;
                }
            }
            if (e.getSource()==saveXMLMenuIt) {
                FileSaveXML fileSave=new FileSaveXML();
                switch (paneTabbed.getSelectedIndex()) {
                    case 0:
                        fileSave.SaveXMLCust(GUI.this, cust);
                        break;
                    case 1:
                        fileSave.SaveXMLRoom(GUI.this, room);
                        break;
                    case 2:
                        fileSave.SaveXMLEmpl(GUI.this, empl);
                        break;
                }
            }
            if (e.getSource()==saveHTMLMenuIt) {
                FileSaveHTML fileSave=new FileSaveHTML();
                switch (paneTabbed.getSelectedIndex()) {
                    case 0:
                        fileSave.SaveHTMLCust(GUI.this, tableModelCust);
                        break;
                    case 1:
                        fileSave.SaveHTMLRoom(GUI.this, tableModelRoom);
                        break;
                    case 2:
                        fileSave.SaveHTMLEmp(GUI.this, tableModelEmp);
                        break;
                }
            }
            if (e.getSource()==savePDFMenuIt) {
                FileSavePDF fileSave=new FileSavePDF();
                switch (paneTabbed.getSelectedIndex()) {
                    case 0:
                        fileSave.SavePDFCust(GUI.this, tableModelCust);
                        break;
                    case 1:
                        fileSave.SavePDFRoom(GUI.this, tableModelRoom);
                        break;
                    case 2:
                        fileSave.SavePDFEmp(GUI.this, tableModelEmp);
                        break;
                }
            }
            if (e.getSource()==exitMenuIt)
                dispose();
            if (e.getSource()==freeNumMenuIt) {
                paneTabbed.setSelectedIndex(1);
                tableModelBuffRoom=new MyTableModel();
                tableModelBuffRoom.clone(tableModelRoom);
                int size=room.size();
                for (int i=0,j=0;i<size;i++)
                    if (!room.get(i).isEmpty()) {
                        tableModelBuffRoom.removeRow(i-j);
                        j++;
                    }
                tableRoom.setModel(tableModelBuffRoom);
            }
            if (e.getSource()==settingsMenuIt) {
                SettingsDialog settingDialog=new SettingsDialog(GUI.this,price);
                settingDialog.setLocationRelativeTo(null);
                settingDialog.setVisible(true);
                HotelRoom.setPrice(price);
            }
            if (e.getSource()==addBut)
                addElTable();
            if (e.getSource()==remBut)
                remElTable();
            if (e.getSource()==editBut)
                editElTable();
            if (e.getSource()==upBut)
                upElTable();
            if (e.getSource()==downBut)
                downElTable();
        }
    }

    public class CustChangeListener implements ChangeListener{
        public void stateChanged(ChangeEvent e) {
            if (paneTabbed.getSelectedIndex()==0) {
                fillSearchBox(sortCust);
                tableRoom.setModel(tableModelRoom);
                pack();
            }
            if (paneTabbed.getSelectedIndex()==1) {
                fillSearchBox(sortRoom);
                UpdateDataRoom();
                pack();
            }
            if (paneTabbed.getSelectedIndex()==2) {
                fillSearchBox(sortEmp);
                tableRoom.setModel(tableModelRoom);
                pack();
            }
        }
    }

    public class FieldTextListener implements TextListener{
        public void textValueChanged(TextEvent e) {
            search();
        }
    }

    private void fillSearchBox(String[] item) {
        searchBox.removeAllItems();
        for (String s : item) searchBox.addItem(s);
    }

    private void UpdateDataRoom() {
        int size=room.size();
        if (size!=0) {
            for (int i=0;i<size;i++) {
                if (!room.get(i).isEmpty()) {
                    String buff= "";
                    for (int j=0;j<room.get(i).getCapacity();j++) {
                        if (j==1)
                            buff+=" / ";
                        buff+=room.get(i).getCustomer().get(j).getSecondName()+" "+room.get(i).getCustomer().get(j).getFirstName();
                    }
                    tableModelRoom.setValueAt(buff, i, 4);
                }
                else
                    tableModelRoom.setValueAt("",i,4);
            }
        }
    }

    private void openXML(int tab) {
        FileOpenXML fileOpen=new FileOpenXML();
        switch (tab){
            case 0:
                fileOpen.OpenXMLCust(GUI.this,cust);
                tableModelCust.addRowsCust(cust);
                openCust = true;
                break;
            case 1:
                fileOpen.OpenXMLRoom(GUI.this,room);
                tableModelRoom.addRowsRoom(room);
                openRoom = true;
                break;
            case 2:
                fileOpen.OpenXMLEmpl(GUI.this,empl);
                tableModelEmp.addRowsEmpl(empl);
                break;
        }
        if (openCust && openRoom) {
            fileOpen.ConnectList(cust, room);
            openCust = false;
            openRoom = false;
        }

    }

    private void addElTable() {
        switch (paneTabbed.getSelectedIndex()) {
            case 0:
                int sizeCustTable=cust.size();
                AddCustDialog addCustDialog =new AddCustDialog(GUI.this,cust,room,false,0);
                addCustDialog.setLocationRelativeTo(null);
                addCustDialog.setVisible(true);
                for (int i=sizeCustTable;i<cust.size();i++) {
                    Vector<String> buffCustData=new Vector<String>();
                    buffCustData.add(cust.get(i).getFirstName());
                    buffCustData.add(cust.get(i).getSecondName());
                    buffCustData.add(cust.get(i).getThirdName());
                    buffCustData.add(String.valueOf(cust.get(i).getRoom().getNumber()));
                    buffCustData.add(cust.get(i).getTimeEntry());
                    buffCustData.add(cust.get(i).getDateExit());
                    buffCustData.add(cust.get(i).getSeriesAndNumber());
                    tableModelCust.addRow(buffCustData);
                    FileSaveXML saveCust=new FileSaveXML();
                    saveCust.AddCust(cust.get(i), PathCust);
                }
                break;
            case 1:
                int sizeRoomTable=room.size();
                AddRoomDialog addRoomDialog =new AddRoomDialog(GUI.this,room,false,0);
                addRoomDialog.setLocationRelativeTo(null);
                addRoomDialog.setVisible(true);
                if (room.size()!=sizeRoomTable) {
                    sizeRoomTable=room.size()-1;
                    Vector<String> buffRoomData=new Vector<String>();
                    buffRoomData.add(String.valueOf(room.get(sizeRoomTable).getNumber()));
                    buffRoomData.add(String.valueOf(room.get(sizeRoomTable).getCapacity()));
                    buffRoomData.add(room.get(sizeRoomTable).getRoomCategory());
                    buffRoomData.add(String.valueOf(room.get(sizeRoomTable).getPricePerNight()));
                    tableModelRoom.addRow(buffRoomData);

                    FileSaveXML saveRoom=new FileSaveXML();
                    saveRoom.AddRoom(room.get(room.size()-1), PathRoom);
                }
                break;
            case 2:
                int sizeEmpTable=empl.size();
                AddEmpDialog addEmpDialog =new AddEmpDialog(GUI.this,empl,false,0);
                addEmpDialog.setLocationRelativeTo(null);
                addEmpDialog.setVisible(true);
                if (empl.size()!=sizeEmpTable) {
                    Vector<String> buffEmpData=new Vector<String>();
                    buffEmpData.add(empl.get(empl.size()-1).getFirstName());
                    buffEmpData.add(empl.get(empl.size()-1).getSecondName());
                    buffEmpData.add(empl.get(empl.size()-1).getThirdName());
                    buffEmpData.add(empl.get(empl.size()-1).getFunction());
                    buffEmpData.add(String.valueOf(empl.get(empl.size()-1).getWage()));
                    buffEmpData.add(empl.get(empl.size()-1).getSeriesAndNumber());
                    tableModelEmp.addRow(buffEmpData);
                    FileSaveXML saveEmpl=new FileSaveXML();
                    saveEmpl.AddEmp(empl.get(empl.size()-1), PathEmpl);
                }
                break;
        }
    }

    private void remElTable() {
        switch (paneTabbed.getSelectedIndex()) {
            case 0:
                int selRow1=tableCust.getSelectedRow();
                if (selRow1!=-1 ) {
                    int answer2=JOptionPane.showConfirmDialog(GUI.this,"Вы действительно хотите удалить клиента?","Окно подтверждения",
                            JOptionPane.OK_CANCEL_OPTION,JOptionPane.WARNING_MESSAGE);
                    if (answer2==JOptionPane.OK_OPTION) {
                        int index;
                        int num=cust.get(selRow1).getNumber();
                        int cap=cust.get(selRow1).getRoom().getCapacity();
                        FileSaveXML saveCust=new FileSaveXML();
                        saveCust.remEmpOrCust(cust.get(selRow1).getSeriesAndNumber(),"customer", PathCust);
                        cust.get(selRow1).getRoom().removeCustomers();
                        cust.remove(selRow1);
                        tableModelCust.removeRow(selRow1);
                        if (cap==2) {
                            index=Customer.findCustomer(cust, num);
                            saveCust.remEmpOrCust(cust.get(index).getSeriesAndNumber(),"customer", PathCust);
                            cust.remove(index);
                            tableModelCust.removeRow(index);
                        }
                    }
                }
                break;
            case 1:
                int selRow2=tableRoom.getSelectedRow();
                if (selRow2!=-1 ) {
                    if (room.get(selRow2).isEmpty()) {
                        int answer2=JOptionPane.showConfirmDialog(GUI.this,"Вы действительно хотите удалить номер?","Окно подтверждения",
                                JOptionPane.OK_CANCEL_OPTION,JOptionPane.WARNING_MESSAGE);
                        if (answer2==JOptionPane.OK_OPTION) {
                            FileSaveXML saveRoom=new FileSaveXML();
                            saveRoom.remRoom(String.valueOf(room.get(selRow2).getNumber()),"room", PathRoom);
                            room.remove(selRow2);
                            tableModelRoom.removeRow(selRow2);
                        }
                    }
                    else
                        JOptionPane.showMessageDialog(GUI.this,"Номер с клиентами нельзя удалить","Помощь", JOptionPane.INFORMATION_MESSAGE);
                }
                break;
            case 2:
                int selRow3=tableEmp.getSelectedRow();
                if (selRow3!=-1 ) {
                    int answer3=JOptionPane.showConfirmDialog(GUI.this,"Вы действительно хотите удалить работника?","Окно подтверждения",
                            JOptionPane.OK_CANCEL_OPTION,JOptionPane.WARNING_MESSAGE);
                    if (answer3==JOptionPane.OK_OPTION) {
                        FileSaveXML saveEmp=new FileSaveXML();
                        saveEmp.remEmpOrCust(empl.get(selRow3).getSeriesAndNumber(),"employee", PathEmpl);
                        empl.remove(selRow3);
                        tableModelEmp.removeRow(selRow3);
                    }
                }
                break;

        }
    }

    private void editElTable() {
        switch (paneTabbed.getSelectedIndex()) {
            case 0:
                int selectRow1=tableCust.getSelectedRow();
                if (selectRow1!=-1) {
                    String SaN=cust.get(selectRow1).getSeriesAndNumber();
                    AddCustDialog addDialog =new AddCustDialog(GUI.this,cust,room,true,selectRow1);
                    addDialog.setLocationRelativeTo(null);
                    addDialog.setVisible(true);
                    tableModelCust.setRowValue(selectRow1,cust.get(selectRow1).getAllParam());
                    FileSaveXML saveCust=new FileSaveXML();
                    saveCust.ChangeCust(cust.get(selectRow1), SaN, PathCust);
                    if (cust.get(selectRow1).getRoom().getCapacity()==2) {
                        int ind=Customer.findCustomer(cust, cust.get(selectRow1).getRoom().getNumber(),cust.get(selectRow1));
                        tableModelCust.setRowValue(ind,cust.get(ind).getAllParam());
                        saveCust.ChangeCust(cust.get(ind), cust.get(ind).getSeriesAndNumber(), PathCust);
                    }
                }
                break;
            case 1:
                int selectRow2=tableRoom.getSelectedRow();
                if (selectRow2!=-1) {
                    if (room.get(selectRow2).isEmpty()) {
                        int num=room.get(selectRow2).getNumber();
                        AddRoomDialog addDialog =new AddRoomDialog(GUI.this,room,true,selectRow2);
                        addDialog.setLocationRelativeTo(null);
                        addDialog.setVisible(true);
                        tableModelRoom.setValueAt(room.get(selectRow2).getCapacity(), selectRow2, 1);
                        tableModelRoom.setValueAt(room.get(selectRow2).getRoomCategory(), selectRow2, 2);
                        tableModelRoom.setValueAt(room.get(selectRow2).getPricePerNight(), selectRow2, 3);
                        FileSaveXML saveRoom=new FileSaveXML();
                        saveRoom.ChangeRoom(room.get(selectRow2), String.valueOf(num), PathRoom);
                    }
                    else
                        JOptionPane.showMessageDialog(GUI.this,"Номер с клиентами не доступен для редактирования","Помощь", JOptionPane.INFORMATION_MESSAGE);
                }
                break;
            case 2:
                int selectRow3=tableEmp.getSelectedRow();
                if (selectRow3!=-1) {
                    String SaN=empl.get(selectRow3).getSeriesAndNumber();
                    AddEmpDialog addDialog =new AddEmpDialog(GUI.this,empl,true,selectRow3);
                    addDialog.setLocationRelativeTo(null);
                    addDialog.setVisible(true);
                    tableModelEmp.setRowValue(selectRow3,empl.get(selectRow3).getAllParam());
                    FileSaveXML saveEmp=new FileSaveXML();
                    saveEmp.ChangeEmp(empl.get(selectRow3), SaN, PathEmpl);
                }
                break;
        }
    }

    private void upElTable() {
        switch (paneTabbed.getSelectedIndex()) {
            case 0:
                if (tableCust.getSelectedRow() >0 && tableModelCust.getRowCount() >1) {
                    int selectRow1=tableCust.getSelectedRow();
                    tableModelCust.moveRow(selectRow1, selectRow1, selectRow1-1);
                    tableCust.setRowSelectionInterval(selectRow1-1, selectRow1-1);
                    Customer buffCust=cust.get(selectRow1-1);
                    cust.set(selectRow1-1, cust.get(selectRow1));
                    cust.set(selectRow1, buffCust);
                }
                break;
            case 1:
                if (tableRoom.getSelectedRow() >0 && tableModelRoom.getRowCount() >1) {
                    int selectRow2=tableRoom.getSelectedRow();
                    tableModelRoom.moveRow(selectRow2, selectRow2, selectRow2-1);
                    tableRoom.setRowSelectionInterval(selectRow2-1, selectRow2-1);
                    HotelRoom buffCust=room.get(selectRow2-1);
                    room.set(selectRow2-1, room.get(selectRow2));
                    room.set(selectRow2, buffCust);
                }
                break;
            case 2:
                if (tableEmp.getSelectedRow() >0 && tableModelEmp.getRowCount() >1) {
                    int selectRow3=tableEmp.getSelectedRow();
                    tableModelEmp.moveRow(selectRow3, selectRow3, selectRow3-1);
                    tableEmp.setRowSelectionInterval(selectRow3-1, selectRow3-1);
                    Employee buffEmp=empl.get(selectRow3-1);
                    empl.set(selectRow3-1, empl.get(selectRow3));
                    empl.set(selectRow3, buffEmp);
                }
                break;
        }
    }

    private void downElTable() {
        switch (paneTabbed.getSelectedIndex()) {
            case 0:
                if (tableCust.getSelectedRow()!=-1 && tableCust.getSelectedRow() != (cust.size()-1) && tableModelCust.getRowCount() >1) {
                    int selectRow1=tableCust.getSelectedRow();
                    tableModelCust.moveRow(selectRow1, selectRow1, selectRow1+1);
                    tableCust.setRowSelectionInterval(selectRow1+1, selectRow1+1);
                    Customer buffCust=cust.get(selectRow1+1);
                    cust.set(selectRow1+1, cust.get(selectRow1));
                    cust.set(selectRow1, buffCust);
                }
                break;
            case 1:
                if (tableRoom.getSelectedRow()!=-1 && tableRoom.getSelectedRow() != (room.size()-1) && tableModelRoom.getRowCount() >1) {
                    int selectRow2=tableRoom.getSelectedRow();
                    tableModelRoom.moveRow(selectRow2, selectRow2, selectRow2+1);
                    tableRoom.setRowSelectionInterval(selectRow2+1, selectRow2+1);
                    HotelRoom buffCust=room.get(selectRow2+1);
                    room.set(selectRow2+1, room.get(selectRow2));
                    room.set(selectRow2, buffCust);
                }
                break;
            case 2:
                if (tableEmp.getSelectedRow()!=-1 && tableEmp.getSelectedRow() != (empl.size()-1) && tableModelEmp.getRowCount() >1) {
                    int selectRow3=tableEmp.getSelectedRow();
                    tableModelEmp.moveRow(selectRow3, selectRow3, selectRow3+1);
                    tableEmp.setRowSelectionInterval(selectRow3+1, selectRow3+1);
                    Employee buffEmp=empl.get(selectRow3+1);
                    empl.set(selectRow3+1, empl.get(selectRow3));
                    empl.set(selectRow3, buffEmp);
                }
                break;
        }
    }

    private void search() {
        remBut.setEnabled(false);
        editBut.setEnabled(false);
        upBut.setEnabled(false);
        downBut.setEnabled(false);
        if (search.getText().equals("")) {
            if (paneTabbed.getSelectedIndex()==0)
                tableCust.setModel(tableModelCust);
            if (paneTabbed.getSelectedIndex()==1)
                tableRoom.setModel(tableModelRoom);
            if (paneTabbed.getSelectedIndex()==2)
                tableEmp.setModel(tableModelEmp);
            remBut.setEnabled(true);
            editBut.setEnabled(true);
            upBut.setEnabled(true);
            downBut.setEnabled(true);
        }
        switch(paneTabbed.getSelectedIndex()) {
            case 0:
                tableModelBuffCust=new MyTableModel();
                tableModelBuffCust.clone(tableModelCust);
                String buffString1=search.getText();
                int size1=cust.size();
                switch(searchBox.getSelectedItem().toString()) {
                    case "Имени":
                        for (int i=0,j=0;i<size1;i++)
                            if (!cust.get(i).getFirstName().contains(buffString1)) {
                                tableModelBuffCust.removeRow(i-j);
                                j++;
                            }
                        break;
                    case "Фамилии":
                        for (int i=0,j=0;i<size1;i++)
                            if (!cust.get(i).getSecondName().contains(buffString1)) {
                                tableModelBuffCust.removeRow(i-j);
                                j++;
                            }
                        break;
                    case "Отчеству":
                        for (int i=0,j=0;i<size1;i++)
                            if (!cust.get(i).getThirdName().contains(buffString1)) {
                                tableModelBuffCust.removeRow(i-j);
                                j++;
                            }
                        break;
                    case "Номеру":
                        for (int i=0,j=0;i<size1;i++)
                            if (!String.valueOf(cust.get(i).getRoom().getNumber()).contains(buffString1)) {
                                tableModelBuffCust.removeRow(i-j);
                                j++;
                            }
                        break;
                }
                tableCust.setModel(tableModelBuffCust);
                break;
            case 1:
                tableModelBuffRoom=new MyTableModel();
                tableModelBuffRoom.clone(tableModelRoom);
                String buffString2=search.getText();
                int size2=room.size();
                switch(searchBox.getSelectedItem().toString()) {
                    case "Номеру":
                        for (int i=0,j=0;i<size2;i++)
                            if (!String.valueOf(room.get(i).getNumber()).contains(buffString2)) {
                                tableModelBuffRoom.removeRow(i-j);
                                j++;
                            }
                        break;
                    case "Вместимости":
                        for (int i=0,j=0;i<size2;i++)
                            if (!String.valueOf(room.get(i).getCapacity()).contains(buffString2)) {
                                tableModelBuffRoom.removeRow(i-j);
                                j++;
                            }
                        break;
                    case "Типу":
                        for (int i=0,j=0;i<size2;i++)
                            if (!room.get(i).getRoomCategory().contains(buffString2)) {
                                tableModelBuffRoom.removeRow(i-j);
                                j++;
                            }
                        break;
                }
                tableRoom.setModel(tableModelBuffRoom);
                break;
            case 2:
                tableModelBuffEmp=new MyTableModel();
                tableModelBuffEmp.clone(tableModelEmp);
                String buffString3=search.getText();
                int size3=empl.size();
                switch(searchBox.getSelectedItem().toString()) {
                    case "Имени":
                        for (int i=0,j=0;i<size3;i++)
                            if (!empl.get(i).getFirstName().contains(buffString3)) {
                                tableModelBuffEmp.removeRow(i-j);
                                j++;
                            }
                        break;
                    case "Фамилии":
                        for (int i=0,j=0;i<size3;i++)
                            if (!empl.get(i).getSecondName().contains(buffString3)) {
                                tableModelBuffEmp.removeRow(i-j);
                                j++;
                            }
                        break;
                    case "Отчеству":
                        for (int i=0,j=0;i<size3;i++)
                            if (!empl.get(i).getThirdName().contains(buffString3)) {
                                tableModelBuffEmp.removeRow(i-j);
                                j++;
                            }
                        break;
                    case "Должности":
                        for (int i=0,j=0;i<size3;i++)
                            if (!empl.get(i).getFunction().contains(buffString3)) {
                                tableModelBuffEmp.removeRow(i-j);
                                j++;
                            }
                        break;
                }
                tableEmp.setModel(tableModelBuffEmp);
                break;
        }
    }
}
