import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.text.DateFormatter;
import javax.swing.text.MaskFormatter;
import org.apache.log4j.Logger;
import java.awt.event.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.awt.*;

public class AddCustDialog extends JDialog{

    private static final Logger logAddEl=Logger.getLogger(AddCustDialog.class);

    private boolean flagEdit;
    private ArrayList<Customer> custCopy;
    private ArrayList<HotelRoom> roomCopy;
    private int index;
    private String nameButton;

    Calendar cal;
    JTextField firstName1,secondName1,thirdName1,
            firstName2,secondName2,thirdName2;
    TextField date1,date2;
    JLabel fLabel1,sLabel1,tLabel1,typeRoomLabel1,numRoomLabel1,timeEntryLabel1,dateLabel1,dateExitLabel1,seriesAndnumLabel1,
            fLabel2,sLabel2,tLabel2,typeRoomLabel2,numRoomLabel2,timeEntryLabel2,dateLabel2,dateExitLabel2,seriesAndnumLabel2,
            infFirstLabel,infSecondLabel;
    JFormattedTextField NumPassport1, NumPassport2,timeEntry1,timeEntry2,dateExit1,dateExit2;
    JButton add,cancel;
    JRadioButton singleRoom,doubleRoom;
    ButtonGroup groupBut;
    JComboBox<String> typeRoomBox,numRoomBox;

    AddActionListener addLisn=new AddActionListener();
    AddTextListener   textLisn=new AddTextListener();

    //private String seriaAndNumPassport;

    public AddCustDialog(JFrame owner,ArrayList<Customer> cust,ArrayList<HotelRoom> room,boolean fEdit,int ind) {
        super(owner,true);
        if (fEdit) {
            setTitle("Редактирование клиента");
            nameButton="Редактировать";
        }
        else {
            setTitle("Добавление клиента");
            nameButton="Добавить";
        }

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        getRootPane().setWindowDecorationStyle(JRootPane.INFORMATION_DIALOG);

        custCopy=cust;
        roomCopy=room;
        flagEdit=fEdit;
        index=ind;

        //seriaAndNumPassport=str;

        initializeComponent();
        addComponents();
        hidePanel();
        typeRoomBox.addActionListener(addLisn);
        date1.addTextListener(textLisn);
        date2.addTextListener(textLisn);
        add.addActionListener(addLisn);
        cancel.addActionListener(addLisn);
        singleRoom.addActionListener(addLisn);
        doubleRoom.addActionListener(addLisn);

        if (flagEdit) {
            firstName1.setText(custCopy.get(index).getFirstName());
            secondName1.setText(custCopy.get(index).getSecondName());
            thirdName1.setText(custCopy.get(index).getThirdName());
            timeEntry1.setText(custCopy.get(index).getTimeEntry());
            date1.setText(custCopy.get(index).getDate());
            dateExit1.setText(custCopy.get(index).getDateExit());
            NumPassport1.setText(custCopy.get(index).getSeriesAndNumber());
            typeRoomBox.setSelectedItem(custCopy.get(index).getRoom().getRoomCategory());
            int num=custCopy.get(index).getRoom().getNumber();
            fillNumBox(getListRoom(custCopy.get(index).getRoom().getRoomCategory(),custCopy.get(index).getRoom().getCapacity()));
            numRoomBox.addItem(String.valueOf(num));
            numRoomBox.setSelectedItem(String.valueOf(num));
            singleRoom.setVisible(false);
            doubleRoom.setVisible(false);
        }
        pack();
    }

    public class AddTextListener implements TextListener{
        public void textValueChanged(TextEvent e) {
            if (doubleRoom.isSelected()) {
                if (e.getSource()==date1) {
                    if (!date1.getText().equals("")) {
                        date2.setText(date1.getText());
                        CalculateDate(date1,dateExit1);
                        CalculateDate(date1,dateExit2);
                    }
                    else {
                        date2.setText("");
                        dateExit1.setValue(cal.getTime());
                        dateExit2.setValue(cal.getTime());
                    }
                }
                if (e.getSource()==date2 ) {
                    if (!date2.getText().equals("")) {
                        date1.setText(date2.getText());
                        CalculateDate(date1,dateExit2);
                        CalculateDate(date1,dateExit1);
                    }
                    else {
                        date1.setText("");
                        dateExit1.setValue(cal.getTime());
                        dateExit2.setValue(cal.getTime());
                    }
                }
            }
            else {
                if (!date1.getText().equals(""))
                    CalculateDate(date1,dateExit1);
                else
                    dateExit1.setValue(cal.getTime());
            }
        }
    }

    public class AddActionListener implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            if (e.getSource()==typeRoomBox) {
                fillNumBox(getListRoom(typeRoomBox.getSelectedItem().toString(),(doubleRoom.isSelected() ? 2 : 1)));
            }
            if (e.getSource()==singleRoom) {
                hidePanel();
                fillNumBox(getListRoom(typeRoomBox.getSelectedItem().toString(),1));
                pack();
            }
            if (e.getSource()==doubleRoom) {
                if (!date1.getText().equals("")) {
                    date2.setText(date1.getText());
                    CalculateDate(date1,dateExit2);
                }
                else {
                    date2.setText("");
                    dateExit2.setValue(cal.getTime());
                }
                showPanel();
                fillNumBox(getListRoom(typeRoomBox.getSelectedItem().toString(),2));
                pack();
            }
            if (e.getSource()==add) {
                try {
                    if (firstName1.getText().equals("") || secondName1.getText().equals("") || thirdName1.getText().equals("")
                            || NumPassport1.getText().trim().equals("") || date1.getText().equals(""))  throw new NullTextException();
                    if (!flagEdit) {
                        if (findCustomer(NumPassport1.getText())!=-1) throw new ExistСustomerException();
                    }
                    else
                    if (!custCopy.get(index).getSeriesAndNumber().equals(NumPassport1.getText()))
                        if (findCustomer(NumPassport1.getText())!=-1) throw new ExistСustomerException();
                    if (doubleRoom.isSelected()) {
                        if (firstName2.getText().equals("") || secondName2.getText().equals("") || thirdName2.getText().equals("")
                                || NumPassport2.getText().trim().equals(""))  throw new NullTextException();
                        if (findCustomer(NumPassport2.getText())!=-1 || NumPassport2.getText().equals(NumPassport1.getText())) throw new ExistСustomerException();
                    }
                    if (!flagEdit) {
                        //ищем номер комнаты для заселения клиентов
                        int i=HotelRoom.findRoom(roomCopy, Integer.parseInt(numRoomBox.getSelectedItem().toString()));

                        custCopy.add(new Customer(firstName1.getText(),secondName1.getText(),thirdName1.getText(),
                                timeEntry1.getText(),date1.getText(),dateExit1.getText(),(String)NumPassport1.getValue(),roomCopy.get(i)));
                        roomCopy.get(i).addCustomer(custCopy.get(custCopy.size()-1));
                        if (doubleRoom.isSelected()) {
                            custCopy.add(new Customer(firstName2.getText(),secondName2.getText(),thirdName2.getText(),
                                    timeEntry2.getText(),date2.getText(),dateExit2.getText(),(String)NumPassport2.getValue(),roomCopy.get(i)));
                            roomCopy.get(i).addCustomer(custCopy.get(custCopy.size()-1));
                        }
                    }
                    else {
                        int i=HotelRoom.findRoom(roomCopy, Integer.parseInt(numRoomBox.getSelectedItem().toString()));
                        int i1=HotelRoom.findRoom(roomCopy, custCopy.get(index).getRoom().getNumber());
                        if (custCopy.get(index).getRoom().getCapacity()==2) {
                            int ind=Customer.findCustomer(custCopy, custCopy.get(index).getRoom().getNumber(), custCopy.get(index));
                            custCopy.get(index).setAllParam(new String[]{firstName1.getText(),secondName1.getText(),thirdName1.getText(),date1.getText(),
                                    dateExit1.getText(),NumPassport1.getText()},roomCopy.get(i));
                            if (Integer.parseInt(numRoomBox.getSelectedItem().toString())!=roomCopy.get(i1).getNumber()) {
                                custCopy.get(ind).setRoom(roomCopy.get(i));
                                roomCopy.get(i).addCustomer(custCopy.get(index));
                                roomCopy.get(i).addCustomer(custCopy.get(ind));
                                roomCopy.get(i1).removeCustomers();
                            }
                        }
                        else {
                            custCopy.get(index).setAllParam(new String[]{firstName1.getText(),secondName1.getText(),thirdName1.getText(),date1.getText(),
                                    dateExit1.getText(),NumPassport1.getText()},roomCopy.get(i));
                            roomCopy.get(i).addCustomer(custCopy.get(index));
                            roomCopy.get(i1).removeCustomers();
                        }
                    }
                    dispose();
                }
                catch (NullTextException myEx) {
                    logAddEl.warn("When adding an customer, not all the required fields were entered");
                    JOptionPane.showMessageDialog(AddCustDialog.this, myEx.getMessage(),"Предупреждение", JOptionPane.INFORMATION_MESSAGE);
                }
                catch (ExistСustomerException myEx) {
                    logAddEl.warn("When adding an customer: this customer already exists");
                    JOptionPane.showMessageDialog(AddCustDialog.this, myEx.getMessage(),"Информация", JOptionPane.INFORMATION_MESSAGE);
                }
            }
            if (e.getSource()==cancel)
                dispose();
        }
    }

    private class NullTextException extends Exception{
        public NullTextException() {
            super("Для добавления клиента введите все поля");
        }
    }

    private class ExistСustomerException extends Exception{
        public ExistСustomerException() {
            super("Такой клиент уже существует");
        }
    }

    private int findCustomer(String SaN) {
        int size=custCopy.size();
        for (int i=0;i<size;i++)
            if (custCopy.get(i).getSeriesAndNumber().equals(SaN))
                return i;
        return -1;
    }

    private void CalculateDate(TextField date,JFormattedTextField dateExit) {
        Calendar c=new GregorianCalendar();
        c.setTime(cal.getTime());
        c.add(Calendar.DAY_OF_YEAR, Integer.parseInt(date.getText()));
        dateExit.setValue(c.getTime());
    }

    private ArrayList<String> getListRoom(String type,int cap) {
        int size=roomCopy.size();
        ArrayList<String> masNumber=new ArrayList<String>();
        for (int i=0;i<size;i++) {
            HotelRoom buffRoom=roomCopy.get(i);
            if (buffRoom.isEmpty()) {
                if (buffRoom.getRoomCategory().equals(type) && buffRoom.getCapacity()==cap)
                    masNumber.add(String.valueOf(buffRoom.getNumber()));
            }
        }
        return masNumber;
    }

    private void fillNumBox(ArrayList<String> str) {
        numRoomBox.removeAllItems();
        int size=str.size();
        if (size!=0) {
            add.setEnabled(true);
            for (int i=0;i<size;i++)
                numRoomBox.addItem(str.get(i));
        }
        else
            add.setEnabled(false);
    }

    private void addComponents() {
        // Определение менеджера расположения
        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);
        // Создание горизонтальной группы
        layout.setHorizontalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(Alignment.TRAILING)
                        .addComponent(typeRoomLabel1)
                        .addComponent(fLabel1)
                        .addComponent(sLabel1)
                        .addComponent(tLabel1)
                        .addComponent(timeEntryLabel1)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(dateLabel1)
                                .addComponent(date1))
                        .addComponent(seriesAndnumLabel1)
                        .addComponent(fLabel2)
                        .addComponent(sLabel2)
                        .addComponent(tLabel2)
                        .addComponent(timeEntryLabel2)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(dateLabel2)
                                .addComponent(date2))
                        .addComponent(seriesAndnumLabel2))
                .addGroup(layout.createParallelGroup(Alignment.TRAILING)
                        .addGroup(layout.createParallelGroup()
                                .addGroup(layout.createSequentialGroup()
                                        .addComponent(typeRoomBox)
                                        .addComponent(numRoomLabel1)
                                        .addComponent(numRoomBox))
                                .addGroup(layout.createSequentialGroup()
                                        .addComponent(singleRoom)
                                        .addComponent(doubleRoom)))
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(infFirstLabel))
                        .addComponent(firstName1)
                        .addComponent(secondName1)
                        .addComponent(thirdName1)
                        .addComponent(timeEntry1)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(dateExitLabel1)
                                .addComponent(dateExit1))
                        .addComponent(NumPassport1)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(infSecondLabel))
                        .addComponent(firstName2)
                        .addComponent(secondName2)
                        .addComponent(thirdName2)
                        .addComponent(timeEntry2)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(dateExitLabel2)
                                .addComponent(dateExit2))
                        .addComponent(NumPassport2)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(add)
                                .addComponent(cancel)))
        );

        // Создание вертикальной группы
        layout.setVerticalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup()
                        .addComponent(typeRoomLabel1)
                        .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup()
                                        .addComponent(typeRoomBox)
                                        .addComponent(numRoomLabel1)
                                        .addComponent(numRoomBox))
                                .addGroup(layout.createParallelGroup()
                                        .addComponent(singleRoom)
                                        .addComponent(doubleRoom))))

                .addGroup(layout.createParallelGroup()
                        .addComponent(infFirstLabel))
                .addGroup(layout.createParallelGroup()
                        .addComponent(fLabel1)
                        .addComponent(firstName1))
                .addGroup(layout.createParallelGroup()
                        .addComponent(sLabel1)
                        .addComponent(secondName1))
                .addGroup(layout.createParallelGroup()
                        .addComponent(tLabel1)
                        .addComponent(thirdName1))
                .addGroup(layout.createParallelGroup()
                        .addComponent(timeEntryLabel1)
                        .addComponent(timeEntry1))
                .addGroup(layout.createParallelGroup()
                        .addComponent(dateLabel1)
                        .addComponent(date1)
                        .addComponent(dateExitLabel1)
                        .addComponent(dateExit1))
                .addGroup(layout.createParallelGroup()
                        .addComponent(seriesAndnumLabel1)
                        .addComponent(NumPassport1))
                .addGroup(layout.createParallelGroup()
                        .addComponent(infSecondLabel))
                .addGroup(layout.createParallelGroup()
                        .addComponent(fLabel2)
                        .addComponent(firstName2))
                .addGroup(layout.createParallelGroup()
                        .addComponent(sLabel2)
                        .addComponent(secondName2))
                .addGroup(layout.createParallelGroup()
                        .addComponent(tLabel2)
                        .addComponent(thirdName2))
                .addGroup(layout.createParallelGroup()
                        .addComponent(timeEntryLabel2)
                        .addComponent(timeEntry2))
                .addGroup(layout.createParallelGroup()
                        .addComponent(dateLabel2)
                        .addComponent(date2)
                        .addComponent(dateExitLabel2)
                        .addComponent(dateExit2))
                .addGroup(layout.createParallelGroup()
                        .addComponent(seriesAndnumLabel2)
                        .addComponent(NumPassport2))
                .addGroup(layout.createParallelGroup()
                        .addComponent(add)
                        .addComponent(cancel))
        );
    }

    private void initializeComponent() {

        MaskFormatter passportFormatter;
        try {
            passportFormatter = new MaskFormatter("#### ######");
            NumPassport1=new JFormattedTextField(passportFormatter);
            NumPassport2=new JFormattedTextField(passportFormatter);
            NumPassport1.setColumns(15);
            NumPassport2.setColumns(15);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        DateFormat date = new SimpleDateFormat("dd.MM.yyyy  kk:mm");
        // Форматирующий объект даты
        DateFormatter dateFormatter = new DateFormatter(date);
        dateFormatter.setAllowsInvalid(false);
        dateFormatter.setOverwriteMode(true);

        // Создание форматированного текстового поля даты
        timeEntry1 = new JFormattedTextField(dateFormatter);
        timeEntry2 = new JFormattedTextField(dateFormatter);
        dateExit1= new JFormattedTextField(dateFormatter);
        dateExit2= new JFormattedTextField(dateFormatter);
        if (!flagEdit)
            cal = new GregorianCalendar();
        else {
            String buffDate=new String(custCopy.get(index).getTimeEntry());
            int[] masbuffDate=new int[5];
            masbuffDate[0]=Integer.parseInt(buffDate.substring(0, 2));
            masbuffDate[1]=Integer.parseInt(buffDate.substring(3, 5));
            masbuffDate[2]=Integer.parseInt(buffDate.substring(6, 10));
            masbuffDate[3]=Integer.parseInt(buffDate.substring(12, 14));
            masbuffDate[4]=Integer.parseInt(buffDate.substring(15, 17));
            cal = new GregorianCalendar(masbuffDate[2],masbuffDate[1],masbuffDate[0],masbuffDate[3],masbuffDate[4]);
        }
        timeEntry1.setValue(cal.getTime());
        timeEntry2.setValue(cal.getTime());
        dateExit1.setValue(cal.getTime());
        dateExit2.setValue(cal.getTime());

        timeEntry1.setEditable(false);
        timeEntry2.setEditable(false);
        dateExit1.setEditable(false);
        dateExit2.setEditable(false);

        firstName1=new JTextField();
        secondName1=new JTextField();
        thirdName1=new JTextField();
        date1=new TextField();

        add=new JButton(nameButton);
        cancel=new JButton("Отмена");

        firstName2=new JTextField();
        secondName2=new JTextField();
        thirdName2=new JTextField();
        date2=new TextField();

        singleRoom=new JRadioButton("одноместный");
        doubleRoom=new JRadioButton("двуместный");
        ButtonGroup groupBut=new ButtonGroup();
        groupBut.add(singleRoom);
        groupBut.add(doubleRoom);
        singleRoom.setSelected(true);
        typeRoomBox=new JComboBox<String>(HotelRoom.getType());
        numRoomBox=new JComboBox<String>();
        if (!flagEdit)
            fillNumBox(getListRoom(typeRoomBox.getSelectedItem().toString(),1));
        infFirstLabel=new JLabel("Информация о первом клиенте");
        infSecondLabel=new JLabel("Информация о втором клиенте");
        fLabel1=new JLabel("Имя");
        sLabel1=new JLabel("Фамилия");
        tLabel1=new JLabel("Отчество");
        typeRoomLabel1=new JLabel("Тип номера");
        numRoomLabel1=new JLabel("Номер комнаты");
        timeEntryLabel1=new JLabel("Дата заселения");
        dateLabel1=new JLabel("Срок");
        dateExitLabel1=new JLabel("Дата выселения");
        seriesAndnumLabel1=new JLabel("Серия/Номер");

        fLabel2=new JLabel("Имя");
        sLabel2=new JLabel("Фамилия");
        tLabel2=new JLabel("Отчество");
        typeRoomLabel2=new JLabel("Тип номера");
        numRoomLabel2=new JLabel("Номер комнаты");
        timeEntryLabel2=new JLabel("Дата заселения");
        dateLabel2=new JLabel("Срок");
        dateExitLabel2=new JLabel("Дата выселения");
        seriesAndnumLabel2=new JLabel("Серия/Номер");

        Font fontHeader=new Font("Verdana",Font.BOLD,16);
        infFirstLabel.setFont(fontHeader);
        infSecondLabel.setFont(fontHeader);
    }

    private void hidePanel() {
        firstName2.setVisible(false);
        secondName2.setVisible(false);
        thirdName2.setVisible(false);
        date2.setVisible(false);
        timeEntry2.setVisible(false);
        fLabel2.setVisible(false);
        sLabel2.setVisible(false);
        tLabel2.setVisible(false);
        typeRoomLabel2.setVisible(false);
        numRoomLabel2.setVisible(false);
        timeEntryLabel2.setVisible(false);
        dateLabel2.setVisible(false);
        dateExitLabel2.setVisible(false);
        dateExit2.setVisible(false);
        seriesAndnumLabel2.setVisible(false);
        NumPassport2.setVisible(false);
        infFirstLabel.setVisible(false);
        infSecondLabel.setVisible(false);
    }

    private void showPanel() {
        firstName2.setVisible(true);
        secondName2.setVisible(true);
        thirdName2.setVisible(true);
        date2.setVisible(true);
        timeEntry2.setVisible(true);
        fLabel2.setVisible(true);
        sLabel2.setVisible(true);
        tLabel2.setVisible(true);
        typeRoomLabel2.setVisible(true);
        numRoomLabel2.setVisible(true);
        timeEntryLabel2.setVisible(true);
        dateLabel2.setVisible(true);
        dateExitLabel2.setVisible(true);
        dateExit2.setVisible(true);
        seriesAndnumLabel2.setVisible(true);
        NumPassport2.setVisible(true);
        infFirstLabel.setVisible(true);
        infSecondLabel.setVisible(true);
    }
}
