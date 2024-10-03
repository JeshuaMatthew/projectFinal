package aplikasipendataanpenyewaankamera;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class customerMenu {
    Scanner  scanner = new Scanner(System.in);
    private int cameraRentAmount;
    private int lensRentAmount;
    private int selectedCameraIndex;

    public ArrayList<String>placeOrderCamera(ArrayList<String>customerData, ArrayList<String>cameraData){
        showCameraData(cameraData);

        String data = inputCustomerData();
        customerData.add(data);
        return customerData;
    }


    public ArrayList<String> subtractCameraStock(ArrayList<String> cameraData){
        String selectedCameraData = cameraData.get(selectedCameraIndex);

        int cameraStock = Integer.parseInt(getSpecificData(selectedCameraData,1,2));

        cameraStock -= cameraRentAmount;

        String tmp = getSpecificData(selectedCameraData,0,1);

        selectedCameraData = tmp + "," + cameraStock + "," + getSpecificData(selectedCameraData,2,4) + ",";

        cameraData.set(selectedCameraIndex,selectedCameraData);

        return cameraData;
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

    private String getSpecificData(String data, int dataBeginingLocation, int dataEndLocation ) {
        return data.substring(getSpecificDataLocation(data,dataBeginingLocation),getSpecificDataLocation(data,dataEndLocation) -1);
    }

    private String saveCustomerData(String objekName){

        System.out.print("Masukan nama Anda : ");
        String customerName =  scanner.nextLine();

        System.out.print("Masukan Tipe Surat Jaminan Anda : ");
        String suratJaminan =  scanner.nextLine();

        System.out.print("Masukan Jumlah Kamera Yang Ingin Anda Sewa : ");
        cameraRentAmount =  scanner.nextInt();

        System.out.print("Masukan Jumlah Hari Anda Menyewa Kamera : ");
        int rentDays =  scanner.nextInt();

        return formatTable(customerName,objekName,cameraRentAmount,suratJaminan,rentDays);
    }

    private void showCameraData(ArrayList<String>cameraData){
        int counter = 1;
        for (String data : cameraData){
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

    private String formatTable(String customerName,String objekName,int objekAmount, String suratJaminan, int rentDays){
        return customerName + "," + objekName + "," + objekAmount + "," + suratJaminan + "," + getCurrentDate() + "," + getReturnDate(rentDays) ;
    }
}

// namapenyewa,namakamera,banyaksewakamera,surat Jaminan,tanggalpenyewaan,tanggalpengembalian
// namakamera,stokKamera,lensa,stokLensa
