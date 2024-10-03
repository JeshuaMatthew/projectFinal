package aplikasipendataanpenyewaankamera;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class fileStorageSystem {

    public boolean addDirectory(File directoryName){
        if(!directoryName.exists()){
            return directoryName.mkdirs();
        }
        return true;
    }

    public void storeData(ArrayList<String> arrayOfData, String fileName) throws IOException {
        FileOutputStream ofstream = new FileOutputStream(fileName);
        for (String data : arrayOfData){
            for (int i = 0; i < data.length();i++){
                ofstream.write(data.charAt(i));
            }
            ofstream.write('\n');
        }
        ofstream.close();
    }

    public void resetRecord(String fileName)throws IOException{
        FileOutputStream ofstream = new FileOutputStream(fileName);
        ofstream.close();
    }


    public ArrayList<String> getRecords(String fileName)throws IOException{
        FileInputStream ifstream = new FileInputStream(fileName);
        ArrayList<String>records = new ArrayList<>();
        int dataBytes;
        StringBuilder dataLine = new StringBuilder();
        while ((dataBytes = ifstream.read()) != -1){
            if(dataBytes != 10) { // \n == 10
                dataLine.append(dataBytes);
            }else{
                records.add(dataLine.toString());
                dataLine.setLength(0); // clears dataline
            }
        }
        ifstream.close();
        return records;
    }
}
