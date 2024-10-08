package aplikasipendataanpenyewaankamera;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class customerMenu {
    Scanner  scanner = new Scanner(System.in);
    private int rentAmount;
    private int selectedIndex;

    private void showMenu(){
        System.out.println("SELAMAT DATANG DI MENU PELANGGAN :\n");
        System.out.println("1.Sewa Kamera / Lensa ");
        System.out.println("2.Keluar Menu");
        System.out.print("Masukan Pilihan Anda : ");
    }

    public void bukaMenu(){
        while(true){
        showMenu();
        int pilihan = scanner.nextInt();
            switch (pilihan){
                case 1:
                    showStockData();
                    placeOrder();
                    break;
                case 2:
                    return;
                default:
                    System.out.println("Menu Yang Anda Pilih Tidak Tersedia !");
            }
        }
    }



    private void placeOrder(){

        System.out.print("Masukan Nomor Kamera / Lensa Yang Ingin Di Sewa : ");
        selectedIndex = scanner.nextInt() - 1;
        if (displayMessageIfNumberIsNotAvailable()) return;

        System.out.print("Masukan Jumlah Yang Ingin Anda Sewa : ");
        rentAmount =  scanner.nextInt();
        if (displayMessageIfStockIsSmallerThanRent()) return;

        if(isIndexValid(fileStorageSystem.dataStock) && isRentSumSmallerThanStock()){
            addNewCustomerData();
            updateStock();
        }
    }

    private boolean displayMessageIfStockIsSmallerThanRent() {
        if (!isRentSumSmallerThanStock()){
            System.out.println("Maaf Jumlah Kamera / Lensa Yang Anda Pesan Melebihi Stok Yang Tersedia !");
            return true;
        }
        return false;
    }

    private boolean displayMessageIfNumberIsNotAvailable() {
        if (!isIndexValid(fileStorageSystem.dataStock)){
            System.out.println("Mohon Maaf Nomor Kamera / Lensa Yang Anda Input Tidak Tersedia !");
            return true;
        }
        return false;
    }

    private void addNewCustomerData() {
        String data = inputCustomerData();
        fileStorageSystem.dataCustomer.add(data);
    }

    private int getCurrentObjectStockAmount(){
        return Integer.parseInt(fileStorageSystem.getSpecificData(
                fileStorageSystem.dataStock.get(selectedIndex),2));
    }

    private boolean isRentSumSmallerThanStock(){
        return getCurrentObjectStockAmount() >= rentAmount;
    }

    private void updateStock(){
        String selectedObjectData = fileStorageSystem.dataStock.get(selectedIndex);

        int objectStock = getCurrentObjectStockAmount();

        objectStock -= rentAmount;

        subtractStock(objectStock, selectedObjectData);
    }

    private void subtractStock(int objectStock, String selectedObjectData) {
        if(objectStock <= 0){
            fileStorageSystem.dataStock.remove(selectedIndex);
        }else{
            selectedObjectData = selectedObjectData.substring(0,fileStorageSystem.getSpecificDataLocation(
                    selectedObjectData,2)).concat(Integer.toString(objectStock).concat(">"));
            fileStorageSystem.dataStock.set(selectedIndex,selectedObjectData);
        }
    }


    private boolean isIndexValid(ArrayList<String>objectData){
        return selectedIndex < objectData.size() && selectedIndex >= 0;
    }


    private String inputCustomerData(){

        String objekName = fileStorageSystem.getSpecificData(fileStorageSystem.dataStock.get(selectedIndex),0);
        String price = fileStorageSystem.getSpecificData(fileStorageSystem.dataStock.get(selectedIndex),1);

        System.out.print("Masukan nama Anda : ");
        scanner.nextLine();
        String customerName =  scanner.nextLine();

        System.out.print("Masukan Tipe Surat Jaminan Anda : ");
        String suratJaminan =  scanner.nextLine();

        System.out.print("Masukan Jumlah Hari Anda Menyewa Kamera : ");
        int rentDays =  scanner.nextInt();

        return formatTable(customerName,objekName, rentAmount,suratJaminan,rentDays,price);
    }

    private void showStockData(){
        System.out.format("%2s %6s %10s %7s","NO.","NAMA" ,"HARGA" ,"STOK\n");
        bacaDanTulisDataStock();
    }

    private static void bacaDanTulisDataStock() {
        int counter = 1;
        for (String data : fileStorageSystem.dataStock){
            String name = fileStorageSystem.getSpecificData(data,0);
            String price = fileStorageSystem.getSpecificData(data,1);
            String stock = fileStorageSystem.getSpecificData(data,2);
            System.out.format("%1s %10s %8s %5s\n",counter,name,price,stock);
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
        return customerName + ">" + objekName + ">" + objekAmount + ">" + price +  ">" + suratJaminan + ">" + getCurrentDate() + ">" + getReturnDate(rentDays) + ">";
    }
}
