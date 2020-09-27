import java.util.ArrayList;

public class Customer {
    private String firstName;    //Имя
    private String secondName;	 //Фамилия
    private String thirdName;	 //Отчество
    private String timeEntry;  //Время заселения
    private String date;  //Срок заселения
    private String dateExit;  //Дата выселения
    private String SaNPassport;
    private HotelRoom hotelRoom; //Номер в гостинице
    private int number;


    public Customer() {}

    public Customer(String fN,String sN,String tN,String timeEnt,String dateHab,String dateEx,String seriaAndnum,HotelRoom hotRoom) {
        firstName=fN;
        secondName=sN;
        thirdName=tN;
        timeEntry=timeEnt;
        date=dateHab;
        dateExit=dateEx;
        SaNPassport=seriaAndnum;
        hotelRoom=hotRoom;
    }

    public Customer(String fN,String sN,String tN,String timeEnt,String dateHab,String dateEx,String seriaAndnum,int num) {
        firstName=fN;
        secondName=sN;
        thirdName=tN;
        timeEntry=timeEnt;
        date=dateHab;
        dateExit=dateEx;
        SaNPassport=seriaAndnum;
        number=num;
    }
    //get
    public String getFirstName() {
        return firstName;
    }
    public String getSecondName() {
        return secondName;
    }
    public String getThirdName() {
        return thirdName;
    }
    public String getTimeEntry() {
        return timeEntry;
    }
    public String getDate() {
        return date;
    }
    public String getDateExit() {
        return dateExit;
    }
    public String getSeriesAndNumber() {
        return SaNPassport;
    }
    public HotelRoom getRoom() {
        return hotelRoom;
    }
    public int getNumber() {
        return number;
    }
    public String[] getAllParam() {
        String[] AllParam=new String[7];
        AllParam[0]=firstName;
        AllParam[1]=secondName;
        AllParam[2]=thirdName;
        AllParam[3]=String.valueOf(hotelRoom.getNumber());
        AllParam[4]=timeEntry;
        AllParam[5]=dateExit;
        AllParam[6]=SaNPassport;
        return AllParam;
    }

    //set
    public void setFirstName(String fN) {
        firstName=fN;
    }
    public void setSecondName(String sN) {
        secondName=sN;
    }
    public void setThirdName(String tN) {
        thirdName=tN;
    }
    public void setTimeEntry(String timeEn) {
        timeEntry=timeEn;
    }
    public void setDate(String dateH) {
        date=dateH;
    }
    public void setDateExit(String dateEx) {
        dateExit=dateEx;
    }
    public void setSeriesAndNumber(String seriaAndnum) {
        SaNPassport=seriaAndnum;
    }

    public void setRoom(HotelRoom room) {
        hotelRoom=room;
    }
    public void setAllParam(String[] AllParam,HotelRoom room) {
        firstName=AllParam[0];
        secondName=AllParam[1];
        thirdName=AllParam[2];
        date=AllParam[3];
        dateExit=AllParam[4];
        SaNPassport=AllParam[5];
        hotelRoom=room;
    }

    public static int findCustomer(ArrayList<Customer> cust,int num) {
        int size=cust.size();
        for (int i=0;i<size;i++) {
            if (cust.get(i).getRoom().getNumber()==num)
                return i;
        }
        return -1;
    }

    public static int findCustomer(ArrayList<Customer> cust,int num,Customer exist) {
        int size=cust.size();
        for (int i=0;i<size;i++) {
            if (cust.get(i).getRoom().getNumber()==num && exist!=cust.get(i))
                return i;
        }
        return -1;
    }
}
