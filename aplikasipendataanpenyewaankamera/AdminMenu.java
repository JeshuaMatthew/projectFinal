package aplikasipendataanpenyewaankamera;

import java.util.ArrayList;
import java.util.Scanner;

public class AdminMenu {
    Scanner input = new Scanner(System.in);
    private String formatData(String namaBarang, int hargaBarang, int stokBarang){
        return namaBarang + "," + hargaBarang + "," + stokBarang + ",";
    }

    private void showMenu(){
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
        System.out.println("Masukan pilihan anda: ");
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
        System.out.println("Masukan nama barang: ");
        String namaBarang = input.nextLine();
        System.out.println("Masukan harga barang: ");
        int hargaBarang = input.nextInt();
        System.out.println("Masukan stok barang: ");
        int stokBarang = input.nextInt();
        String formatedData = formatData(namaBarang, hargaBarang, stokBarang);
        stokData.add(formatedData);
        return stokData;
    }

    private void showStok(ArrayList<String> stokData){
        int counter = 1;
        System.out.println("NO.\t NAMA\t HARGA\t STOK\t");
        for(String data:stokData){
            String nama = getSpecificData(data, 0, 1);
            String harga = getSpecificData(data, 1, 2);
            String stok = getSpecificData(data, 2, 3);

            System.out.println(counter + ". " + nama + "\t" + harga + "\t" + stok);
        }
    }

    private void showCustomerData(ArrayList<String> customerData){
        int counter = 1;
        System.out.println("NO.\t NAMA\t NAMA-BARANG\t JUMLAH-SEWA\t SURAT-JAMINAN\t TANGGAL-PENYEWAAN\t TANGGAL-PENGEMBALIAN");
        for(String data : customerData){
            String customerName = getSpecificData(data, 0, 1);
            String objectName = getSpecificData(data, 1, 2);
            String rentAmount = getSpecificData(data, 2, 3);
            String suratJaminan = getSpecificData(data, 3, 4);
            String rentDate = getSpecificData(data, 4, 5);
            String returnDate = getSpecificData(data, 5, 6);

            System.out.println(counter + ". " + customerName + "\t" + objectName + "\t" + rentAmount + "\t" + suratJaminan + "\t" + rentDate + "\t" + returnDate);
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
        System.out.println("Masukan no data yang ingin di edit: ");
        int indexData = input.nextInt();
        System.out.println("Masukan nama data yang baru: ");
        String dataBaru = input.nextLine();
        System.out.println("Masukan harga barang yang baru: ");
        int hargaBaru = input.nextInt();
        System.out.println("Masukan stok barang yang baru: ");
        int stokBaru = input.nextInt();
        String editedData = formatData(dataBaru, hargaBaru, stokBaru);
        stokData.set(indexData -1, editedData);
        return stokData;

    }

    public ArrayList<String> hapusStok(ArrayList<String> stokData){
        showStok(stokData);
        System.out.println("Masukan nomor data yang ingin diedit: ");
        int indexData = input.nextInt();
        stokData.remove(indexData -1);
        return stokData;
    }
}
