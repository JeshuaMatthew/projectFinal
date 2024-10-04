package aplikasipendataanpenyewaankamera;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class customerMenu {
    Scanner  scanner = new Scanner(System.in);
    private int rentAmount;
    private int selectedIndex;

    public ArrayList<String>placeOrder(ArrayList<String>customerData, ArrayList<String>stockData){
        showData(stockData);
        System.out.print("Masukan Nomor Barang Yang Ingin Di Sewa : ");

        selectedIndex = scanner.nextInt() - 1;
        if(isIndexValid(stockData)){
            String data = saveCustomerData(stockData);
            customerData.add(data);
        }
        return customerData;
    }

    public ArrayList<String> subtractStock(ArrayList<String> objectData){
        String selectedObjectData = objectData.get(selectedIndex);

        int objectStock = Integer.parseInt(getSpecificData(selectedObjectData,2,3));

        objectStock -= rentAmount;

        selectedObjectData = selectedObjectData.substring(0,getSpecificDataLocation(selectedObjectData,2)).concat(Integer.toString(objectStock).concat(","));

        objectData.set(selectedIndex,selectedObjectData);

        return objectData;
    }

    private int getSpecificDataLocation(String data, int dataType){
        int countCommas = 0;
        int counter = 0;
        while (countCommas != dataType){
            if(data.charAt(counter) == ','){
                countCommas++;
            }
            counter++;
        }
        return counter;
    }

    private boolean isIndexValid(ArrayList<String>objectData){
        return selectedIndex < objectData.size() && selectedIndex >= 0;
    }

    private String getSpecificData(String data, int dataBeginingLocation, int dataEndLocation ) {
        return data.substring(getSpecificDataLocation(data,dataBeginingLocation),getSpecificDataLocation(data,dataEndLocation) -1);
    }

    private String saveCustomerData(ArrayList<String> objekData){

        String objekName = getSpecificData(objekData.get(selectedIndex),0,1);
        String price = getSpecificData(objekData.get(selectedIndex),1,2);

        System.out.print("Masukan nama Anda : ");
        String customerName =  scanner.nextLine();

        System.out.print("Masukan Tipe Surat Jaminan Anda : ");
        String suratJaminan =  scanner.nextLine();

        System.out.print("Masukan Jumlah Yang Ingin Anda Sewa : ");
        rentAmount =  scanner.nextInt();

        System.out.print("Masukan Jumlah Hari Anda Menyewa Kamera : ");
        int rentDays =  scanner.nextInt();

        return formatTable(customerName,objekName, rentAmount,suratJaminan,rentDays,price);
    }

    private void showData(ArrayList<String>Data){
        int counter = 1;
        for (String data : Data){
            System.out.println(counter +  ". " + data);
            counter++;
        }
    }

    private String getCurrentDate(){
        return LocalDate.now().toString();
    }

    private String getReturnDate(int rentDays){
        return LocalDate.now().plusDays(rentDays).toString();
    }

    private String formatTable(String customerName,String objekName,int objekAmount, String suratJaminan, int rentDays ,String price){
        return customerName + "," + objekName + "," + objekAmount + "," + price +  "," + suratJaminan + "," + getCurrentDate() + "," + getReturnDate(rentDays) + ",";
    }
}

// namapenyewa,namakamera,banyaksewakamera,surat Jaminan,tanggalpenyewaan,tanggalpengembalian,
// namakamera,harga,stokKamera,
// lensa,harga,stokLensa,
