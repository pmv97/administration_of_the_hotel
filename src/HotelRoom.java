import java.util.ArrayList;

public class HotelRoom {
    private static final String typeRoom[]={"Стандарт", "Студия","Бизнес", "Люкс"};
    private static int priceRoom[]=new int[2*typeRoom.length];
    private int number;			//номер номера
    private int capacity;        //вместимость
    private String roomCategory;    //категория номера
    private int pricePerNight;   //цена за сутки
    private ArrayList<Customer> custList=new ArrayList<Customer>();

    public HotelRoom(int num,int cap,String category) {
        number=num;
        capacity=cap;
        roomCategory=category;
        setPricePerNight();
    }

    public HotelRoom(int num,int cap,String category,int price) {
        number=num;
        capacity=cap;
        roomCategory=category;
        pricePerNight=price;
    }

    public void  addCustomer (Customer cust) {
        custList.add(cust);
    }

    public void  removeCustomers () {
        custList.clear();
    }

    public ArrayList<Customer> getCustomer () {
        return custList;
    }
    //get
    public int getNumber() {
        return number;
    }
    public int getCapacity() {
        return capacity;
    }
    public String getRoomCategory() {
        return roomCategory;
    }
    public int getPricePerNight() {
        return pricePerNight;
    }

    //set
    public void setNumber(int num) {
        number=num;
    }
    public void setCapacity(int cap) {
        capacity=cap;
    }
    public void setRoomCategory(String category) {
        roomCategory=category;
    }

    public void setPricePerNight() {
        for (int i=0;i<typeRoom.length;i++)
            if (roomCategory.equals(typeRoom[i]))
                if (getCapacity()==2)
                    pricePerNight=HotelRoom.priceRoom[4+i];
                else
                    pricePerNight=HotelRoom.priceRoom[i];
    }

    public static String[] getType() {
        return typeRoom;
    }

    public static void setPrice(int mas[]) {
        HotelRoom.priceRoom=new int[mas.length];
        for (int i=0;i<mas.length;i++)
            HotelRoom.priceRoom[i]=mas[i];
    }

    public boolean isEmpty() {
        return custList.size() == 0;
    }

    public static int findRoom(ArrayList<HotelRoom> room,int num) {
        int size=room.size();
        for (int i=0;i<size;i++) {
            if (room.get(i).getNumber()==num)
                return i;
        }
        return -1;

    }
}
