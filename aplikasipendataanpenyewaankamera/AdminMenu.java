package aplikasipendataanpenyewaankamera;

import java.util.ArrayList;
import java.util.Scanner;

public class AdminMenu {
    Scanner input = new Scanner(System.in);
    private String formatData(String namaBarang, int hargaBarang, int stokBarang){
        return namaBarang + "," + hargaBarang + "," + stokBarang + ",";
    }

    private void showMenu(){
        System.out.println("=============================================");
        System.out.println("Berikut adalah menu Admin :");
        System.out.println("1. Tambah Stok Barang");
        System.out.println("2. Edit Stok Barang");
        System.out.println("3. Hapus Stok Barang");
        System.out.println("4. Lihat Data Pelanggan Barang");
        System.out.println("5. exit");
    }

    public ArrayList<String> bukaMenu(ArrayList<String> stokData, ArrayList<String> customerData){
        showStok(stokData);
        showMenu();
        System.out.print("Masukan pilihan anda: ");
        int pilihan = input.nextInt();
        switch (pilihan){
            case 1:
                stokData = tambahStok(stokData);
                break;
            case 2:
                stokData = editStok(stokData);
                break;
            case 3:
                stokData = hapusStok(stokData);
                break;

            case 4:
                showCustomerData(customerData);
                break;
            case 5:
                return stokData;
            default:
                System.out.println("Angka yang anda masukan tidak sesuai!");
        }
        return stokData;
    }

    public ArrayList<String> tambahStok(ArrayList<String> stokData){
        showStok(stokData);
        System.out.print("Masukan nama barang: ");
        input.nextLine();
        String namaBarang = input.nextLine();
        System.out.print("Masukan harga barang: ");
        int hargaBarang = input.nextInt();
        System.out.print("Masukan stok barang: ");
        int stokBarang = input.nextInt();
        String formatedData = formatData(namaBarang, hargaBarang, stokBarang);
        stokData.add(formatedData);
        return stokData;
    }

    private void showStok(ArrayList<String> stokData){
        int counter = 1;
        System.out.println(String.format("%2s %6s %10s %7s","NO.","NAMA" ,"HARGA" ,"STOK"));
        for(String data:stokData){
            String nama = getSpecificData(data, 0, 1);
            String harga = getSpecificData(data, 1, 2);
            String stok = getSpecificData(data, 2, 3);
            System.out.println(String.format("%1s %10s %8s %5s",counter,nama,harga,stok));
            counter++;
        }
    }

    private void showCustomerData(ArrayList<String> customerData){
        int counter = 1;

        System.out.println(String.format("%2s %8s %13s %5s %8s %15s %15s %15s","NO.","NAMA", "NAMA-BARANG","JUMLAH-SEWA","HARGA","SURAT-JAMINAN","TANGGAL-PENYEWAAN","TANGGAL-PENGEMBALIAN"));
        for(String data : customerData){
            String customerName = getSpecificData(data, 0, 1);
            String objectName = getSpecificData(data, 1, 2);
            String rentAmount = getSpecificData(data, 2, 3);
            String price = getSpecificData(data, 3, 4);
            String suratJaminan = getSpecificData(data, 4, 5);
            String rentDate = getSpecificData(data, 5, 6);
            String returnDate = getSpecificData(data, 6, 7);
            System.out.println( String.format("%2s %10s %10s %6s %15s %10s %20s %18s",counter,customerName,objectName,rentAmount,price,suratJaminan,rentDate,returnDate));
            counter++;
        }
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
        return data.substring(getSpecificDataLocation(data,dataBeginingLocation),getSpecificDataLocation(data,dataEndLocation)-1);
    }

    public ArrayList<String> editStok(ArrayList<String> stokData){
        showStok(stokData);
        System.out.print("Masukan no data yang ingin di edit: ");
        int indexData = input.nextInt();
        System.out.print("Masukan nama data yang baru: ");
        String dataBaru = input.nextLine();
        System.out.print("Masukan harga barang yang baru: ");
        int hargaBaru = input.nextInt();
        System.out.print("Masukan stok barang yang baru: ");
        int stokBaru = input.nextInt();
        String editedData = formatData(dataBaru, hargaBaru, stokBaru);
        stokData.set(indexData -1, editedData);
        return stokData;

    }

    public ArrayList<String> hapusStok(ArrayList<String> stokData){
        showStok(stokData);
        System.out.print("Masukan nomor data yang ingin diedit: ");
        int indexData = input.nextInt();
        stokData.remove(indexData -1);
        return stokData;
    }
}