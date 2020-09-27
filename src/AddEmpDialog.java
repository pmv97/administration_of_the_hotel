import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.text.MaskFormatter;
import org.apache.log4j.Logger;
import java.awt.event.*;
import java.text.ParseException;
import java.util.ArrayList;


public class AddEmpDialog extends JDialog{

    private static final Logger logAddEl=Logger.getLogger(AddEmpDialog.class);
    private boolean flagEdit;
    private ArrayList<Employee> emplCopy;
    private int index;
    private String nameButton;

    JTextField firstName,secondName,thirdName,wage;
    JLabel fLabel,sLabel,tLabel,funcLabel,wageLabel,seriesAndnumLabel;
    JFormattedTextField NumPassport;
    JButton add,cancel;
    JComboBox<String> funcBox;
    AddActionListener addLisn=new AddActionListener();

    private final String func[]={"Дежурный администратор", "Агент по бронированию номеров",
            "Кассир службы приема и размещения", "Ночной аудитор", "Консьерж","Портье"};


    //private String seriaAndNumPassport;

    public AddEmpDialog(JFrame owner,ArrayList<Employee> emp,boolean fEdit,int ind) {
        super(owner,true);
        if (fEdit) {
            setTitle("Редактирование работника");
            nameButton="Редактировать";
        }
        else {
            setTitle("Добавление работника");
            nameButton="Добавить";
        }
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        getRootPane().setWindowDecorationStyle(JRootPane.INFORMATION_DIALOG);

        emplCopy=emp;
        flagEdit=fEdit;
        index=ind;
        //seriaAndNumPassport=str;
        initializeComponent();
        addComponents();

        add.addActionListener(addLisn);
        cancel.addActionListener(addLisn);

        if (flagEdit) {
            firstName.setText(emplCopy.get(index).getFirstName());
            secondName.setText(emplCopy.get(index).getSecondName());
            thirdName.setText(emplCopy.get(index).getThirdName());
            funcBox.setSelectedItem(emplCopy.get(index).getFunction());
            wage.setText(emplCopy.get(index).getWage());
            NumPassport.setText(emplCopy.get(index).getSeriesAndNumber());
        }
        pack();
    }

    public class AddActionListener implements ActionListener{
        public void actionPerformed(ActionEvent e) {

            if (e.getSource()==add) {
                try {
                    if (firstName.getText().equals("") || secondName.getText().equals("") || thirdName.getText().equals("")
                            || wage.getText().equals("") || NumPassport.getText().trim().equals(""))  throw new NullTextException();
                    if (!flagEdit) {
                        if (!findEmployee(NumPassport.getText()).equals("")) throw new ExistEmployeeException();
                    }
                    else {
                        if (!emplCopy.get(index).getSeriesAndNumber().equals(NumPassport.getText()))
                            if (!findEmployee(NumPassport.getText()).equals("")) throw new ExistEmployeeException();
                    }
                    if (!flagEdit)
                        emplCopy.add(new Employee(firstName.getText(),secondName.getText(),thirdName.getText(),
                                funcBox.getSelectedItem().toString(),wage.getText(),NumPassport.getText()));
                    else
                        emplCopy.get(index).setAllParam(new String[]{firstName.getText(),secondName.getText(),thirdName.getText(),
                                funcBox.getSelectedItem().toString(),wage.getText(),NumPassport.getText()});
                    dispose();
                }
                catch (NullTextException myEx) {
                    logAddEl.warn("When adding an employee, not all the required fields were entered");
                    JOptionPane.showMessageDialog(AddEmpDialog.this, myEx.getMessage(),"Предупреждение", JOptionPane.INFORMATION_MESSAGE);
                }
                catch (ExistEmployeeException myEx) {
                    logAddEl.warn("When adding an employee: this employee already exists");
                    JOptionPane.showMessageDialog(AddEmpDialog.this, myEx.getMessage(),"Информация", JOptionPane.INFORMATION_MESSAGE);
                }
            }
            if (e.getSource()==cancel)
                dispose();
        }
    }

    private class NullTextException extends Exception{
        public NullTextException() {
            super("Для добавления работника введите все поля");
        }
    }

    private class ExistEmployeeException extends Exception{
        public ExistEmployeeException() {
            super("Такой работник уже существует");
        }
    }

    private String findEmployee(String SaN) {
        int size=emplCopy.size();
        for (int i=0;i<size;i++)
            if (emplCopy.get(i).getSeriesAndNumber().equals(SaN))
                return String.valueOf(i);
        return "";
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
                        .addComponent(fLabel)
                        .addComponent(sLabel)
                        .addComponent(tLabel)
                        .addComponent(funcLabel)
                        .addComponent(wageLabel)
                        .addComponent(seriesAndnumLabel))
                .addGroup(layout.createParallelGroup(Alignment.TRAILING)
                        .addComponent(firstName)
                        .addComponent(secondName)
                        .addComponent(thirdName)
                        .addComponent(funcBox)
                        .addComponent(wage)
                        .addComponent(NumPassport)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(add)
                                .addComponent(cancel)))
        );

        // Создание вертикальной группы
        layout.setVerticalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup()
                        .addComponent(fLabel)
                        .addComponent(firstName))
                .addGroup(layout.createParallelGroup()
                        .addComponent(sLabel)
                        .addComponent(secondName))
                .addGroup(layout.createParallelGroup()
                        .addComponent(tLabel)
                        .addComponent(thirdName))
                .addGroup(layout.createParallelGroup()
                        .addComponent(funcLabel)
                        .addComponent(funcBox))
                .addGroup(layout.createParallelGroup()
                        .addComponent(wageLabel)
                        .addComponent(wage))
                .addGroup(layout.createParallelGroup()
                        .addComponent(seriesAndnumLabel)
                        .addComponent(NumPassport))
                .addGroup(layout.createParallelGroup()
                        .addComponent(add)
                        .addComponent(cancel))
        );
    }

    private void initializeComponent() {
        MaskFormatter passportFormatter;
        try {
            passportFormatter = new MaskFormatter("#### ######");
            NumPassport=new JFormattedTextField(passportFormatter);
            NumPassport.setColumns(15);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        add=new JButton(nameButton);
        cancel=new JButton("Отмена");
        funcBox=new JComboBox<String>(func);

        firstName=new JTextField(20);
        secondName=new JTextField(20);
        thirdName=new JTextField(20);
        wage=new JTextField(20);

        fLabel=new JLabel("Имя");
        sLabel=new JLabel("Фамилия");
        tLabel=new JLabel("Отчество");
        funcLabel=new JLabel("Должность");
        wageLabel=new JLabel("Зарплата");
        seriesAndnumLabel=new JLabel("Серия/Номер");
    }
}
