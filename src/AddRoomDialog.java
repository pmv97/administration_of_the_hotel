import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import org.apache.log4j.Logger;
import java.awt.event.*;
import java.util.ArrayList;

public class AddRoomDialog extends JDialog{

    private static final Logger logAddEl=Logger.getLogger(AddRoomDialog.class);

    private boolean flagEdit;
    private ArrayList<HotelRoom> roomCopy;
    private int index;
    private String nameButton;

    JTextField number;
    JLabel numLabel,catLabel;
    JButton add,cancel;
    JRadioButton singleRoom,doubleRoom;
    ButtonGroup groupBut;
    JComboBox<String> typeRoomBox;

    AddActionListener addLisn=new AddActionListener();

    private final String[] typeRoom ={"Стандарт", "Студия","Бизнес", "Люкс"};

    public AddRoomDialog(JFrame owner,ArrayList<HotelRoom> room,boolean fEdit,int ind) {
        super(owner,true);
        if (fEdit) {
            setTitle("Редактирование номера комнаты");
            nameButton="Редактировать";
        }
        else {
            setTitle("Добавление номера комнаты");
            nameButton="Добавить";
        }

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        getRootPane().setWindowDecorationStyle(JRootPane.INFORMATION_DIALOG);

        roomCopy=room;
        flagEdit=fEdit;
        index=ind;

        //seriaAndNumPassport=str;

        initializeComponent();
        addComponents();

        add.addActionListener(addLisn);
        cancel.addActionListener(addLisn);

        if (flagEdit) {
            doubleRoom.setSelected(roomCopy.get(index).getCapacity()==2);
            typeRoomBox.setSelectedItem(roomCopy.get(index).getRoomCategory());
            numLabel.setVisible(false);
            number.setVisible(false);
        }
        pack();
    }

    public class AddActionListener implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            if (e.getSource()==add) {
                try {
                    if (!flagEdit) {
                        if (number.getText().equals(""))  throw new  NullTextException();
                        if (isExist(Integer.parseInt(number.getText())))  throw new  ExistRoomException();
                    }
                    if (!flagEdit)
                        roomCopy.add(new HotelRoom(Integer.parseInt(number.getText()),(doubleRoom.isSelected()? 2:1),typeRoomBox.getSelectedItem().toString()));
                    else {
                        roomCopy.get(index).setCapacity((doubleRoom.isSelected()? 2:1));
                        roomCopy.get(index).setRoomCategory(typeRoomBox.getSelectedItem().toString());
                        roomCopy.get(index).setPricePerNight();
                    }
                    dispose();
                }
                catch ( NullTextException myEx) {
                    logAddEl.warn("When adding a room: not all the required fields were entered");
                    JOptionPane.showMessageDialog(AddRoomDialog.this, myEx.getMessage(),"Предупреждение", JOptionPane.INFORMATION_MESSAGE);
                }
                catch (ExistRoomException myEx) {
                    logAddEl.warn("When adding a room: this room already exists");
                    JOptionPane.showMessageDialog(AddRoomDialog.this, myEx.getMessage(),"Информация", JOptionPane.INFORMATION_MESSAGE);
                }
            }
            if (e.getSource()==cancel)
                dispose();
        }
    }

    private class NullTextException extends Exception{
        public NullTextException() {
            super("Для добавления номера введите все поля");
        }
    }

    private class ExistRoomException extends Exception{
        public ExistRoomException() {
            super("Такой номер уже существует");
        }
    }

    private boolean isExist(int num) {
        int size=roomCopy.size();
        for (int i=0;i<size;i++)
            if (num==roomCopy.get(i).getNumber())
                return true;
        return false;
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
                        .addComponent(catLabel)
                        .addComponent(numLabel))
                .addGroup(layout.createParallelGroup(Alignment.TRAILING)
                        .addComponent(typeRoomBox)
                        .addComponent(number)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(singleRoom)
                                .addComponent(doubleRoom))
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(add)
                                .addComponent(cancel)))
        );
        // Создание вертикальной группы
        layout.setVerticalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup()
                        .addComponent(catLabel)
                        .addComponent(typeRoomBox))
                .addGroup(layout.createParallelGroup()
                        .addComponent(numLabel)
                        .addComponent(number))
                .addGroup(layout.createParallelGroup()
                        .addComponent(singleRoom)
                        .addComponent(doubleRoom))
                .addGroup(layout.createParallelGroup()
                        .addComponent(add)
                        .addComponent(cancel))
        );
    }

    private void initializeComponent() {
        number=new JTextField();

        singleRoom=new JRadioButton("одноместный");
        doubleRoom=new JRadioButton("двуместный");
        ButtonGroup groupBut=new ButtonGroup();
        groupBut.add(singleRoom);
        groupBut.add(doubleRoom);
        singleRoom.setSelected(true);
        typeRoomBox=new JComboBox<String>(typeRoom);

        numLabel=new JLabel("Номер комнаты");
        catLabel=new JLabel("Тип номера");

        //Font fontText=new Font("Consolas",Font.BOLD,15);

        add=new JButton(nameButton);
        cancel=new JButton("Отмена");
    }
}
