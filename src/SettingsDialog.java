import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import org.apache.log4j.Logger;
import java.awt.event.*;
import java.util.ArrayList;
import java.awt.*;

public class SettingsDialog extends JDialog{

    private static final Logger logAddEl=Logger.getLogger(SettingsDialog.class);

    int[] masPrice;

    JTextField standart,studio,business,lux,
            standart1,studio1,business1,lux1;
    JLabel standartLabel,studioLabel,businessLabel,luxLabel,
            standartLabel1,studioLabel1,businessLabel1,luxLabel1;
    JButton ok,cancel;

    AddActionListener addLisn=new AddActionListener();

    public SettingsDialog(JFrame owner,int[] price) {
        super(owner,"Параметры",true);

        masPrice=price;

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        getRootPane().setWindowDecorationStyle(JRootPane.INFORMATION_DIALOG);

        initializeComponent();
        addComponents();

        standart.setText(String.valueOf(masPrice[0]));
        studio.setText(String.valueOf(masPrice[1]));
        business.setText(String.valueOf(masPrice[2]));
        lux.setText(String.valueOf(masPrice[3]));
        standart1.setText(String.valueOf(masPrice[4]));
        studio1.setText(String.valueOf(masPrice[5]));
        business1.setText(String.valueOf(masPrice[6]));
        lux1.setText(String.valueOf(masPrice[7]));

        ok.addActionListener(addLisn);
        cancel.addActionListener(addLisn);

        pack();
    }

    public class AddActionListener implements ActionListener{
        public void actionPerformed(ActionEvent e) {

            if (e.getSource()==ok) {
                try {
                    if (standart.getText().equals("") || studio.getText().equals("") || business.getText().equals("")
                            || lux.getText().equals(""))  throw new MyException();

                    masPrice[0]=Integer.parseInt(standart.getText());
                    masPrice[1]=Integer.parseInt(studio.getText());
                    masPrice[2]=Integer.parseInt(business.getText());
                    masPrice[3]=Integer.parseInt(lux.getText());
                    masPrice[4]=Integer.parseInt(standart1.getText());
                    masPrice[5]=Integer.parseInt(studio1.getText());
                    masPrice[6]=Integer.parseInt(business1.getText());
                    masPrice[7]=Integer.parseInt(lux1.getText());
                    dispose();
                }
                catch (MyException myEx) {
                    logAddEl.warn("When adding an employee, not all the required fields were entered");
                    JOptionPane.showMessageDialog(SettingsDialog.this, myEx.getMessage(),"Предупреждение", JOptionPane.INFORMATION_MESSAGE);
                }
            }
            if (e.getSource()==cancel)
                dispose();
        }
    }

    private class MyException extends Exception{
        public MyException() {
            super("Введите все поля");
        }
    }

    private void addComponents() {
        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createTitledBorder("Цена за 1 день"));
        GroupLayout layout1 = new GroupLayout(panel);
        layout1.setAutoCreateGaps(true);
        layout1.setAutoCreateContainerGaps(true);
        panel.setLayout(layout1);
        layout1.setHorizontalGroup(layout1.createSequentialGroup()
                .addGroup(layout1.createParallelGroup(Alignment.TRAILING)
                        .addComponent(standartLabel)
                        .addComponent(studioLabel)
                        .addComponent(businessLabel)
                        .addComponent(luxLabel))
                .addGroup(layout1.createParallelGroup(Alignment.TRAILING)
                        .addComponent(standart)
                        .addComponent(studio)
                        .addComponent(business)
                        .addComponent(lux))
                .addGroup(layout1.createParallelGroup(Alignment.TRAILING)
                        .addComponent(standartLabel1)
                        .addComponent(studioLabel1)
                        .addComponent(businessLabel1)
                        .addComponent(luxLabel1))
                .addGroup(layout1.createParallelGroup(Alignment.TRAILING)
                        .addComponent(standart1)
                        .addComponent(studio1)
                        .addComponent(business1)
                        .addComponent(lux1))
        );

        // Создание вертикальной группы
        layout1.setVerticalGroup(layout1.createSequentialGroup()
                .addGroup(layout1.createParallelGroup()
                        .addComponent(standartLabel)
                        .addComponent(standart)
                        .addComponent(standartLabel1)
                        .addComponent(standart1))
                .addGroup(layout1.createParallelGroup()
                        .addComponent(studioLabel)
                        .addComponent(studio)
                        .addComponent(studioLabel1)
                        .addComponent(studio1))
                .addGroup(layout1.createParallelGroup()
                        .addComponent(businessLabel)
                        .addComponent(business)
                        .addComponent(businessLabel1)
                        .addComponent(business1))
                .addGroup(layout1.createParallelGroup()
                        .addComponent(luxLabel)
                        .addComponent(lux)
                        .addComponent(luxLabel1)
                        .addComponent(lux1))
        );

        GroupLayout layout = new GroupLayout(getContentPane());
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);
        getContentPane().setLayout(layout);

        layout.setHorizontalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(Alignment.TRAILING)
                        .addComponent(panel)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(ok)
                                .addComponent(cancel)))
        );

        // Создание вертикальной группы
        layout.setVerticalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup()
                        .addComponent(panel))
                .addGroup(layout.createParallelGroup()
                        .addComponent(ok)
                        .addComponent(cancel))
        );
    }

    private void initializeComponent() {
        ok=new JButton("Ок");
        cancel=new JButton("Отмена");

        standart=new JTextField(10);
        studio=new JTextField(10);
        business=new JTextField(10);
        lux=new JTextField(10);
        standart1=new JTextField(10);
        studio1=new JTextField(10);
        business1=new JTextField(10);
        lux1=new JTextField(10);

        standartLabel=new JLabel("Cтандарт: одноместный");
        standartLabel1=new JLabel("двуместный");
        studioLabel=new JLabel("Студия: одноместный");
        studioLabel1=new JLabel("двуместный");
        businessLabel=new JLabel("Бизнес: одноместный");
        businessLabel1=new JLabel("двуместный");
        luxLabel=new JLabel("Люкс: одноместный");
        luxLabel1=new JLabel("двуместный");
    }
}
