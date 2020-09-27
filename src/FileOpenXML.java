import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.*;
import javax.xml.parsers.*;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import org.w3c.dom.Element;

public class FileOpenXML {
    public void OpenXMLEmpl(JFrame frame, ArrayList<Employee> emp) {
        emp.clear();
        FileDialog openXml=new FileDialog(frame,"Загрузка данных",FileDialog.LOAD);
        openXml.setFile("*.xml");
        openXml.setLocationRelativeTo(null);
        openXml.setVisible(true);
        String fileNameOpenXML = openXml.getDirectory() + openXml.getFile();
        DocumentBuilder builder;
        Document doc = null;
        try {
            // Создание парсера документа
            builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            // Чтение документа из файла
            doc = builder.parse(new File(fileNameOpenXML));
            // Нормализация документа
            doc.getDocumentElement().normalize();
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
        NodeList employeeList = doc.getElementsByTagName("employee");
        int NodeCount = employeeList.getLength();
        for (int i = 0; i < NodeCount; i++) {
            Node element = employeeList.item(i);
            Element element1 = (Element) element;
            String firstName = element1.getElementsByTagName("firstName").item(0).getTextContent();
            String secondName = element1.getElementsByTagName("secondName").item(0).getTextContent();
            String thirdName = element1.getElementsByTagName("thirdName").item(0).getTextContent();
            String function = element1.getElementsByTagName("function").item(0).getTextContent();
            String wage = element1.getElementsByTagName("wage").item(0).getTextContent();
            String SaNPassport = element1.getElementsByTagName("SaNPassport").item(0).getTextContent();
            emp.add(new Employee(firstName, secondName, thirdName, function, wage, SaNPassport));
        }
    }


    public void OpenXMLCust(JFrame frame, ArrayList<Customer> cust) {
        cust.clear();
        FileDialog openXml=new FileDialog(frame,"Загрузка данных",FileDialog.LOAD);
        openXml.setFile("*.xml");
        openXml.setLocationRelativeTo(null);
        openXml.setVisible(true);
        String fileNameOpenXML = openXml.getDirectory() + openXml.getFile();
        DocumentBuilder builder;
        Document doc = null;
        try {
            // Создание парсера документа
            builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            // Чтение документа из файла
            doc = builder.parse(new File(fileNameOpenXML));
            // Нормализация документа
            doc.getDocumentElement().normalize();
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
        NodeList customerList = doc.getElementsByTagName("customer");
        int NodeCount = customerList.getLength();
        for (int i = 0; i < NodeCount; i++) {
            Node element = customerList.item(i);
            Element element1 = (Element) element;
            String firstName = element1.getElementsByTagName("firstName").item(0).getTextContent();
            String secondName = element1.getElementsByTagName("secondName").item(0).getTextContent();
            String thirdName = element1.getElementsByTagName("thirdName").item(0).getTextContent();
            String timeEntry = element1.getElementsByTagName("timeEntry").item(0).getTextContent();
            String date = element1.getElementsByTagName("date").item(0).getTextContent();
            String dateExit = element1.getElementsByTagName("dateExit").item(0).getTextContent();
            String SaNPassport = element1.getElementsByTagName("SaNPassport").item(0).getTextContent();
            String number = element1.getElementsByTagName("number").item(0).getTextContent();
            cust.add(new Customer(firstName, secondName, thirdName, timeEntry, date, dateExit, SaNPassport, Integer.parseInt(number)));
        }
    }

    public void OpenXMLRoom(JFrame frame, ArrayList<HotelRoom> room) {
        room.clear();
        FileDialog openXml=new FileDialog(frame,"Загрузка данных",FileDialog.LOAD);
        openXml.setFile("*.xml");
        openXml.setLocationRelativeTo(null);
        openXml.setVisible(true);
        String fileNameOpenXML = openXml.getDirectory() + openXml.getFile();
        DocumentBuilder builder;
        Document doc = null;
        try {
            // Создание парсера документа
            builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            // Чтение документа из файла
            doc = builder.parse(new File(fileNameOpenXML));
            // Нормализация документа
            doc.getDocumentElement().normalize();
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
        NodeList roomList = doc.getElementsByTagName("room");
        int NodeCount = roomList.getLength();
        for (int i = 0; i < NodeCount; i++) {
            Node element = roomList.item(i);
            Element element1 = (Element) element;
            String number = element1.getElementsByTagName("number").item(0).getTextContent();
            String capacity = element1.getElementsByTagName("capacity").item(0).getTextContent();
            String category = element1.getElementsByTagName("category").item(0).getTextContent();
            String price = element1.getElementsByTagName("price").item(0).getTextContent();
            room.add(new HotelRoom(Integer.parseInt(number), Integer.parseInt(capacity), category, Integer.parseInt(price)));
        }
    }

    public void ConnectList(ArrayList<Customer> cust, ArrayList<HotelRoom> room) {
        for (Customer customer : cust) {
            int num = customer.getNumber();
            for (HotelRoom hotelRoom : room) {
                if (num == hotelRoom.getNumber()) {
                    hotelRoom.addCustomer(customer);
                    customer.setRoom(hotelRoom);
                    break;
                }
            }
        }
    }
}