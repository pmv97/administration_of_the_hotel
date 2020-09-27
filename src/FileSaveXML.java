import java.awt.FileDialog;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.*;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;



public class FileSaveXML {

    public void SaveXMLEmpl(JFrame frame,ArrayList<Employee> emp) {
        FileDialog saveXml=new FileDialog(frame,"Сохранение данных",FileDialog.SAVE);
        saveXml.setFile("*.xml");
        saveXml.setLocationRelativeTo(null);
        saveXml.setVisible(true);
        String fileNameSaveXML = saveXml.getDirectory() + saveXml.getFile();
        DocumentBuilder builder;
        Document doc = null;
        try {
            // Создание парсера документа
            builder=DocumentBuilderFactory.newInstance().newDocumentBuilder();
            // Создание пустого документа
            doc=builder.newDocument();
        }
        catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
        Node employeeList=doc.createElement("employeeList");
        doc.appendChild(employeeList);
        for (Employee employee : emp) {
            Element empl = doc.createElement("employee");
            empl.setAttribute("SaNPassport", employee.getSeriesAndNumber());
            Element empl1 = doc.createElement("firstName");
            empl1.setTextContent(employee.getFirstName());
            Element empl2 = doc.createElement("secondName");
            empl2.setTextContent(employee.getSecondName());
            Element empl3 = doc.createElement("thirdName");
            empl3.setTextContent(employee.getThirdName());
            Element empl4 = doc.createElement("function");
            empl4.setTextContent(employee.getFunction());
            Element empl5 = doc.createElement("wage");
            empl5.setTextContent(employee.getWage());
            Element empl6 = doc.createElement("SaNPassport");
            empl6.setTextContent(employee.getSeriesAndNumber());
            empl.appendChild(empl1);
            empl.appendChild(empl2);
            empl.appendChild(empl3);
            empl.appendChild(empl4);
            empl.appendChild(empl5);
            empl.appendChild(empl6);
            employeeList.appendChild(empl);
        }
        try {
            // Создание преобразователя документа
            Transformer trans = TransformerFactory.newInstance().newTransformer();
            // Запись документа в файл
            trans.transform(new DOMSource(doc), new StreamResult(new FileOutputStream(fileNameSaveXML)));
        } catch (IOException | TransformerException e) { e.printStackTrace(); }
    }

    public void SaveXMLCust(JFrame frame,ArrayList<Customer> cust) {
        FileDialog saveXml=new FileDialog(frame,"Сохранение данных",FileDialog.SAVE);
        saveXml.setFile("*.xml");
        saveXml.setLocationRelativeTo(null);
        saveXml.setVisible(true);
        String fileNameSaveXML = saveXml.getDirectory() + saveXml.getFile();
        DocumentBuilder builder;
        Document doc = null;
        try {
            // Создание парсера документа
            builder=DocumentBuilderFactory.newInstance().newDocumentBuilder();
            // Создание пустого документа
            doc=builder.newDocument();
        }
        catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
        Node customerList=doc.createElement("customerList");
        doc.appendChild(customerList);
        int size=cust.size();
        for (Customer customer : cust) {
            Element cus = doc.createElement("customer");
            cus.setAttribute("SaNPassport", customer.getSeriesAndNumber());
            Element cus1 = doc.createElement("firstName");
            cus1.setTextContent(customer.getFirstName());
            Element cus2 = doc.createElement("secondName");
            cus2.setTextContent(customer.getSecondName());
            Element cus3 = doc.createElement("thirdName");
            cus3.setTextContent(customer.getThirdName());
            Element cus4 = doc.createElement("timeEntry");
            cus4.setTextContent(customer.getTimeEntry());
            Element cus5 = doc.createElement("date");
            cus5.setTextContent(customer.getDate());
            Element cus6 = doc.createElement("dateExit");
            cus6.setTextContent(customer.getDateExit());
            Element cus7 = doc.createElement("SaNPassport");
            cus7.setTextContent(customer.getSeriesAndNumber());
            Element cus8 = doc.createElement("number");
            cus8.setTextContent(String.valueOf(customer.getRoom().getNumber()));
            cus.appendChild(cus1);
            cus.appendChild(cus2);
            cus.appendChild(cus3);
            cus.appendChild(cus4);
            cus.appendChild(cus5);
            cus.appendChild(cus6);
            cus.appendChild(cus7);
            cus.appendChild(cus8);
            customerList.appendChild(cus);
        }
        try {
            // Создание преобразователя документа
            Transformer trans = TransformerFactory.newInstance().newTransformer();
            // Запись документа в файл
            trans.transform(new DOMSource(doc), new StreamResult(new FileOutputStream(fileNameSaveXML)));
        } catch (IOException | TransformerException e) { e.printStackTrace(); }
    }

    public void SaveXMLRoom(JFrame frame,ArrayList<HotelRoom> room) {
        FileDialog saveXml=new FileDialog(frame,"Сохранение данных",FileDialog.SAVE);
        saveXml.setFile("*.xml");
        saveXml.setLocationRelativeTo(null);
        saveXml.setVisible(true);
        String fileNameSaveXML = saveXml.getDirectory() + saveXml.getFile();
        DocumentBuilder builder;
        Document doc = null;
        try {
            // Создание парсера документа
            builder=DocumentBuilderFactory.newInstance().newDocumentBuilder();
            // Создание пустого документа
            doc=builder.newDocument();
        }
        catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
        Node roomList=doc.createElement("roomList");
        doc.appendChild(roomList);
        int size=room.size();
        for (HotelRoom hotelRoom : room) {
            Element rom = doc.createElement("room");
            rom.setAttribute("number", String.valueOf(hotelRoom.getNumber()));
            Element rom1 = doc.createElement("number");
            rom1.setTextContent(String.valueOf(hotelRoom.getNumber()));
            Element rom2 = doc.createElement("capacity");
            rom2.setTextContent(String.valueOf(hotelRoom.getCapacity()));
            Element rom3 = doc.createElement("category");
            rom3.setTextContent(hotelRoom.getRoomCategory());
            Element rom4 = doc.createElement("price");
            rom4.setTextContent(String.valueOf(hotelRoom.getPricePerNight()));
            rom.appendChild(rom1);
            rom.appendChild(rom2);
            rom.appendChild(rom3);
            rom.appendChild(rom4);
            roomList.appendChild(rom);
        }
        try {
            // Создание преобразователя документа
            Transformer trans = TransformerFactory.newInstance().newTransformer();
            // Запись документа в файл
            trans.transform(new DOMSource(doc), new StreamResult(new FileOutputStream(fileNameSaveXML)));
        } catch (IOException | TransformerException e) { e.printStackTrace(); }
    }

    public void remEmpOrCust(String SaN,String name,String path) {
        DocumentBuilder builder;
        Document doc = null;
        try {
            // Создание парсера документа
            builder=DocumentBuilderFactory.newInstance().newDocumentBuilder();
            // Чтение документа из файла
            doc=builder.parse(new File(path));
            // Нормализация документа
            doc.getDocumentElement().normalize();
        }
        catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
        NodeList List=doc.getElementsByTagName(name+"List");
        Element el = null;
        el=(Element)List.item(0);
        Node n=null;
        NodeList List1=doc.getElementsByTagName(name);
        for (int i=0;i<List1.getLength();i++) {
            n = el.getElementsByTagName(name).item(i);
            Element element1 = (Element)n;
            if (element1.getElementsByTagName("SaNPassport").item(0).getTextContent().equals(SaN))
                break;
        }
        el.removeChild(n);
        try {
            Transformer trans = TransformerFactory.newInstance().newTransformer();
            // Запись документа в файл
            trans.transform(new DOMSource(doc), new StreamResult(new File(path)));
        } catch (TransformerException e) { e.printStackTrace(); }
    }

    public void remRoom(String num,String name,String path) {
        DocumentBuilder builder;
        Document doc = null;
        try {
            // Создание парсера документа
            builder=DocumentBuilderFactory.newInstance().newDocumentBuilder();
            // Чтение документа из файла
            doc=builder.parse(new File(path));
            // Нормализация документа
            doc.getDocumentElement().normalize();
        }
        catch (ParserConfigurationException | IOException | SAXException e) {
            e.printStackTrace();
        }
        NodeList List=doc.getElementsByTagName(name+"List");
        Element el = null;
        el=(Element)List.item(0);
        Node n=null;
        NodeList List1=doc.getElementsByTagName(name);
        for (int i=0;i<List1.getLength();i++) {
            n = el.getElementsByTagName(name).item(i);
            Element element1 = (Element)n;
            if (element1.getElementsByTagName("number").item(0).getTextContent().equals(num))
                break;
        }
        el.removeChild(n);
        try {
            Transformer trans = TransformerFactory.newInstance().newTransformer();
            // Запись документа в файл
            trans.transform(new DOMSource(doc), new StreamResult(new File(path)));
        } catch (TransformerException e) { e.printStackTrace(); }
    }

    public void AddEmp(Employee emp,String path) {
        DocumentBuilder builder;
        Document doc = null;
        try {
            // Создание парсера документа
            builder=DocumentBuilderFactory.newInstance().newDocumentBuilder();
            // Чтение документа из файла
            doc=builder.parse(new File(path));
            // Нормализация документа
            doc.getDocumentElement().normalize();
        }
        catch (ParserConfigurationException | IOException | SAXException e) {
            e.printStackTrace();
        }
        NodeList List=doc.getElementsByTagName("employeeList");
        Node n=List.item(0);
        Element empl=doc.createElement("employee");
        empl.setAttribute("SaNPassport", emp.getSeriesAndNumber());
        Element empl1=doc.createElement("firstName");
        empl1.setTextContent(emp.getFirstName());
        Element empl2=doc.createElement("secondName");
        empl2.setTextContent(emp.getSecondName());
        Element empl3=doc.createElement("thirdName");
        empl3.setTextContent(emp.getThirdName());
        Element empl4=doc.createElement("function");
        empl4.setTextContent(emp.getFunction());
        Element empl5=doc.createElement("wage");
        empl5.setTextContent(emp.getWage());
        Element empl6=doc.createElement("SaNPassport");
        empl6.setTextContent(emp.getSeriesAndNumber());
        empl.appendChild(empl1);
        empl.appendChild(empl2);
        empl.appendChild(empl3);
        empl.appendChild(empl4);
        empl.appendChild(empl5);
        empl.appendChild(empl6);
        n.appendChild(empl);
        try {
            Transformer trans = TransformerFactory.newInstance().newTransformer();
            // Запись документа в файл
            trans.transform(new DOMSource(doc), new StreamResult(new File(path)));
        } catch (TransformerException e) { e.printStackTrace(); }
    }

    public void AddRoom(HotelRoom room,String path) {
        DocumentBuilder builder;
        Document doc = null;
        try {
            // Создание парсера документа
            builder=DocumentBuilderFactory.newInstance().newDocumentBuilder();
            // Чтение документа из файла
            doc=builder.parse(new File(path));
            // Нормализация документа
            doc.getDocumentElement().normalize();
        }
        catch (ParserConfigurationException | IOException | SAXException e) {
            e.printStackTrace();
        }
        NodeList List=doc.getElementsByTagName("roomList");
        Node n=List.item(0);
        Element rom=doc.createElement("room");
        rom.setAttribute("number", String.valueOf(room.getNumber()));
        Element rom1=doc.createElement("number");
        rom1.setTextContent(String.valueOf(room.getNumber()));
        Element rom2=doc.createElement("capacity");
        rom2.setTextContent(String.valueOf(room.getCapacity()));
        Element rom3=doc.createElement("category");
        rom3.setTextContent(room.getRoomCategory());
        Element rom4=doc.createElement("price");
        rom4.setTextContent(String.valueOf(room.getPricePerNight()));
        rom.appendChild(rom1);
        rom.appendChild(rom2);
        rom.appendChild(rom3);
        rom.appendChild(rom4);
        n.appendChild(rom);
        try {
            Transformer trans = TransformerFactory.newInstance().newTransformer();
            // Запись документа в файл
            trans.transform(new DOMSource(doc), new StreamResult(new File(path)));
        } catch (TransformerException e) { e.printStackTrace(); }
    }

    public void AddCust(Customer cust,String path) {
        DocumentBuilder builder;
        Document doc = null;
        try {
            // Создание парсера документа
            builder=DocumentBuilderFactory.newInstance().newDocumentBuilder();
            // Чтение документа из файла
            doc=builder.parse(new File(path));
            // Нормализация документа
            doc.getDocumentElement().normalize();
        }
        catch (ParserConfigurationException | IOException | SAXException e) {
            e.printStackTrace();
        }
        NodeList List=doc.getElementsByTagName("customerList");
        Node n=List.item(0);
        Element cus=doc.createElement("customer");
        cus.setAttribute("SaNPassport", cust.getSeriesAndNumber());
        Element cus1=doc.createElement("firstName");
        cus1.setTextContent(cust.getFirstName());
        Element cus2=doc.createElement("secondName");
        cus2.setTextContent(cust.getSecondName());
        Element cus3=doc.createElement("thirdName");
        cus3.setTextContent(cust.getThirdName());
        Element cus4=doc.createElement("timeEntry");
        cus4.setTextContent(cust.getTimeEntry());
        Element cus5=doc.createElement("date");
        cus5.setTextContent(cust.getDate());
        Element cus6=doc.createElement("dateExit");
        cus6.setTextContent(cust.getDateExit());
        Element cus7=doc.createElement("SaNPassport");
        cus7.setTextContent(cust.getSeriesAndNumber());
        Element cus8=doc.createElement("number");
        cus8.setTextContent(String.valueOf(cust.getRoom().getNumber()));
        cus.appendChild(cus1);
        cus.appendChild(cus2);
        cus.appendChild(cus3);
        cus.appendChild(cus4);
        cus.appendChild(cus5);
        cus.appendChild(cus6);
        cus.appendChild(cus7);
        cus.appendChild(cus8);
        n.appendChild(cus);
        try {
            Transformer trans = TransformerFactory.newInstance().newTransformer();
            // Запись документа в файл
            trans.transform(new DOMSource(doc), new StreamResult(new File(path)));
        } catch (TransformerException e) { e.printStackTrace(); }
    }

    public void ChangeEmp(Employee emp,String SaN,String path) {
        DocumentBuilder builder;
        Document doc = null;
        try {
            // Создание парсера документа
            builder=DocumentBuilderFactory.newInstance().newDocumentBuilder();
            // Чтение документа из файла
            doc=builder.parse(new File(path));
            // Нормализация документа
            doc.getDocumentElement().normalize();
        }
        catch (ParserConfigurationException | IOException | SAXException e) {
            e.printStackTrace();
        }
        NodeList List=doc.getElementsByTagName("employeeList");
        Element el = null;
        el=(Element)List.item(0);
        Node n=null;
        NodeList List1=doc.getElementsByTagName("employee");
        for (int i=0;i<List1.getLength();i++) {
            n = el.getElementsByTagName("employee").item(i);
            Element element1 = (Element)n;
            if (element1.getElementsByTagName("SaNPassport").item(0).getTextContent().equals(SaN))
                break;
        }
        NodeList x=n.getChildNodes();
        el=(Element)n;
        el.setAttribute("SaNPassport", emp.getSeriesAndNumber());
        x.item(0).setTextContent(emp.getFirstName());
        x.item(1).setTextContent(emp.getSecondName());
        x.item(2).setTextContent(emp.getThirdName());
        x.item(3).setTextContent(emp.getFunction());
        x.item(4).setTextContent(emp.getWage());
        x.item(5).setTextContent(emp.getSeriesAndNumber());
        try {
            Transformer trans = TransformerFactory.newInstance().newTransformer();
            // Запись документа в файл
            trans.transform(new DOMSource(doc), new StreamResult(new File(path)));
        } catch (TransformerException e) { e.printStackTrace(); }
    }

    public void ChangeRoom(HotelRoom room,String name,String path) {
        DocumentBuilder builder;
        Document doc = null;
        try {
            // Создание парсера документа
            builder=DocumentBuilderFactory.newInstance().newDocumentBuilder();
            // Чтение документа из файла
            doc=builder.parse(new File(path));
            // Нормализация документа
            doc.getDocumentElement().normalize();
        }
        catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
        NodeList List=doc.getElementsByTagName("roomList");
        Element el = null;
        el=(Element)List.item(0);
        Node n=null;
        NodeList List1=doc.getElementsByTagName("room");
        for (int i=0;i<List1.getLength();i++) {
            n = el.getElementsByTagName("room").item(i);
            Element element1 = (Element)n;
            if (element1.getElementsByTagName("number").item(0).getTextContent().equals(name))
                break;
        }
        NodeList x=n.getChildNodes();
        el=(Element)n;
        el.setAttribute("number", String.valueOf(room.getNumber()));
        x.item(0).setTextContent(String.valueOf(room.getNumber()));
        x.item(1).setTextContent(String.valueOf(room.getCapacity()));
        x.item(2).setTextContent(room.getRoomCategory());
        x.item(3).setTextContent(String.valueOf(room.getPricePerNight()));
        try {
            Transformer trans = TransformerFactory.newInstance().newTransformer();
            // Запись документа в файл
            trans.transform(new DOMSource(doc), new StreamResult(new File(path)));
        } catch (TransformerException e) { e.printStackTrace(); }
    }

    public void ChangeCust(Customer cust,String SaN,String path) {
        DocumentBuilder builder;
        Document doc = null;
        try {
            // Создание парсера документа
            builder=DocumentBuilderFactory.newInstance().newDocumentBuilder();
            // Чтение документа из файла
            doc=builder.parse(new File(path));
            // Нормализация документа
            doc.getDocumentElement().normalize();
        }
        catch (ParserConfigurationException | IOException | SAXException e) {
            e.printStackTrace();
        }
        NodeList List=doc.getElementsByTagName("customerList");
        Element el = null;
        el=(Element)List.item(0);
        Node n=null;
        NodeList List1=doc.getElementsByTagName("customer");
        for (int i=0;i<List1.getLength();i++) {
            n = el.getElementsByTagName("customer").item(i);
            Element element1 = (Element)n;
            if (element1.getElementsByTagName("SaNPassport").item(0).getTextContent().equals(SaN))
                break;
        }
        NodeList x=n.getChildNodes();
        el=(Element)n;
        el.setAttribute("SaNPassport", cust.getSeriesAndNumber());
        x.item(0).setTextContent(cust.getFirstName());
        x.item(1).setTextContent(cust.getSecondName());
        x.item(2).setTextContent(cust.getThirdName());
        x.item(3).setTextContent(cust.getTimeEntry());
        x.item(4).setTextContent(cust.getDate());
        x.item(5).setTextContent(cust.getDateExit());
        x.item(6).setTextContent(cust.getSeriesAndNumber());
        x.item(7).setTextContent(String.valueOf(cust.getRoom().getNumber()));
        try {
            Transformer trans = TransformerFactory.newInstance().newTransformer();
            // Запись документа в файл
            trans.transform(new DOMSource(doc), new StreamResult(new File(path)));
        } catch (TransformerException e) { e.printStackTrace(); }
    }
}
