public class Employee {
    private String firstName;
    private String secondName;
    private String thirdName;
    private String function;
    private String wage;
    private String SaNPassport;

    public Employee() {}

    public Employee(String fN,String sN,String tN,String func,String wag,String seriaAndnum) {
        firstName=fN;
        secondName=sN;
        thirdName=tN;
        function=func;
        wage=wag;
        SaNPassport=seriaAndnum;
    }

    public Employee(String[] rowValue) {
        firstName=rowValue[0];
        secondName=rowValue[1];
        thirdName=rowValue[2];
        function=rowValue[3];
        wage=rowValue[4];
        SaNPassport=rowValue[5];
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
    public String getFunction() {
        return function;
    }
    public String getWage() {
        return wage;
    }
    public String getSeriesAndNumber() {
        return SaNPassport;
    }

    public String[] getAllParam() {
        String[] AllParam=new String[6];
        AllParam[0]=firstName;
        AllParam[1]=secondName;
        AllParam[2]=thirdName;
        AllParam[3]=function;
        AllParam[4]=wage;
        AllParam[5]=SaNPassport;
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
    public void setFunction(String func) {
        function=func;
    }
    public void setWage(String wag) {
        wage=wag;
    }
    public void setSeriesAndNumber(String seriaAndnum) {
        SaNPassport=seriaAndnum;
    }

    public void setAllParam(String[] AllParam) {
        firstName=AllParam[0];
        secondName=AllParam[1];
        thirdName=AllParam[2];
        function=AllParam[3];
        wage=AllParam[4];
        SaNPassport=AllParam[5];
    }
}
