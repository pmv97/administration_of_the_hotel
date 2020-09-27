import java.util.ArrayList;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;

public class MyTableModel extends DefaultTableModel{

    public MyTableModel() {
        super();
    }

    public void clone (MyTableModel oldTableModel) {
        int RowCount=getRowCount();
        for (int i=0;i<RowCount;i++)
            removeRow(0);

        Vector<String> colName=new Vector<String>();
        for (int i=0;i<oldTableModel.getColumnCount();i++)
            colName.add(oldTableModel.getColumnName(i));
        setColumnIdentifiers(colName);

        for (int i=0;i<oldTableModel.getRowCount();i++) {
            Vector<Object> buffData=new Vector<Object>();
            for (int j=0;j<oldTableModel.getColumnCount();j++) {
                buffData.add(oldTableModel.getValueAt(i, j));
            }
            addRow(buffData);
        }
    }

    public String[] getRowValue(int index) {
        String[] RowValue=new String[getColumnCount()];
        for (int i=0;i<getColumnCount();i++)
            RowValue[i]=getValueAt(index,i).toString();
        return RowValue;
    }

    public void setRowValue(int index,String[] RowValue) {
        for (int i=0;i<getColumnCount();i++)
            setValueAt(RowValue[i],index,i);
    }

    public void addRowsEmpl(ArrayList<Employee> empl) {
        for (Employee employee : empl) {
            Vector<String> buffEmpData = new Vector<String>();
            buffEmpData.add(employee.getFirstName());
            buffEmpData.add(employee.getSecondName());
            buffEmpData.add(employee.getThirdName());
            buffEmpData.add(employee.getFunction());
            buffEmpData.add(String.valueOf(employee.getWage()));
            buffEmpData.add(employee.getSeriesAndNumber());
            addRow(buffEmpData);
        }
    }
    public void addRowsCust(ArrayList<Customer> cust) {
        for (Customer customer : cust) {
            Vector<String> buffCustData = new Vector<String>();
            buffCustData.add(customer.getFirstName());
            buffCustData.add(customer.getSecondName());
            buffCustData.add(customer.getThirdName());
            buffCustData.add(String.valueOf(customer.getRoom().getNumber()));
            buffCustData.add(customer.getTimeEntry());
            buffCustData.add(customer.getDateExit());
            buffCustData.add(customer.getSeriesAndNumber());
            addRow(buffCustData);
        }
    }
    public void addRowsRoom(ArrayList<HotelRoom> room) {
        for (HotelRoom hotelRoom : room) {
            Vector<String> buffRoomData = new Vector<>();
            buffRoomData.add(String.valueOf(hotelRoom.getNumber()));
            buffRoomData.add(String.valueOf(hotelRoom.getCapacity()));
            buffRoomData.add(hotelRoom.getRoomCategory());
            buffRoomData.add(String.valueOf(hotelRoom.getPricePerNight()));
            if (!hotelRoom.isEmpty()) {
                String buff = "";
                for (int j = 0; j < hotelRoom.getCapacity(); j++) {
                    if (j == 1)
                        buff = " / ";
                    buff = hotelRoom.getCustomer().get(j).getSecondName() + " " + hotelRoom.getCustomer().get(j).getFirstName();
                }
                buffRoomData.add(buff);
            }
            addRow(buffRoomData);
        }
    }
}
