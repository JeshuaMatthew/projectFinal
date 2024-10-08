package aplikasipendataanpenyewaankamera;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class fileStorageSystem {

    private File stockDirectory = new File("stockDirectory");
    private File customerDirectory = new File("customerDirectory");
    private File stockFile = new File("stockDirectory/stockRecords.txt");
    private File customerFile = new File("customerDirectory/customerRecords.txt");

    public static ArrayList<String> dataStock = new ArrayList<>();
    public static ArrayList<String> dataCustomer = new ArrayList<>();


    public ArrayList<String> getRecords(String fileName)throws IOException{
        FileInputStream ifstream = new FileInputStream(fileName);
        ArrayList<String>records = new ArrayList<>();
        int dataBytes;
        StringBuilder dataLine = new StringBuilder();
        while ((dataBytes = ifstream.read()) != -1){
            if((char)dataBytes != '\n') { // \n == 10
                dataLine.append((char) dataBytes);
            }else{
                records.add(dataLine.toString());
                dataLine.setLength(0); // clears dataline
            }
        }
        ifstream.close();
        return records;
    }

    public void createNecessaryDirectory()throws IOException{
        addDirectory(customerDirectory);
        addDirectory(stockDirectory);
        addFile(customerFile);
        addFile(stockFile);
    }

    public void storeCustomerStockData(ArrayList<String>customerData, ArrayList<String>stockData)throws IOException{
        storeData(stockData,stockFile);
        storeData(customerData,customerFile);
    }

    private void addDirectory(File directoryName){
        if(!directoryName.exists()){
            directoryName.mkdirs();
        }
    }

    private void addFile(File fileName)throws IOException{
        if(!fileName.exists()){
            fileName.createNewFile();
        }
    }

    private void storeData(ArrayList<String> arrayOfData, File fileName) throws IOException {
        FileOutputStream ofstream = new FileOutputStream(fileName);
        for (String data : arrayOfData){
            for (int i = 0; i < data.length();i++){
                ofstream.write(data.charAt(i));
            }
            ofstream.write('\n');
        }
        ofstream.close();
    }

    public static int getSpecificDataLocation(String data, int dataType){
        int countCommas = 0;
        int counter = 0;
        while (countCommas != dataType){
            if(data.charAt(counter) == '>'){
                countCommas++;
            }
            counter++;
        }
        return counter;
    }

    public static String getSpecificData(String data, int dataLocation ) {
        return data.substring(getSpecificDataLocation(data,dataLocation),getSpecificDataLocation(data,dataLocation + 1) -1);
    }

}
