package aplikasipendataanpenyewaankamera;

import java.util.ArrayList;
import java.util.Scanner;

public class AdminMenu {
    Scanner input = new Scanner(System.in);
    private String formatData(String namaBarang, int hargaBarang, int stokBarang){
        return namaBarang + ">" + hargaBarang + ">" + stokBarang + ">";
    }

    private void showMenu(){
        System.out.println("=============================================");
        System.out.println("SELAMAT DATANG DI MENU ADMIN : \n");
        System.out.println("1. Tambah Stok Barang");
        System.out.println("2. Edit Stok Barang");
        System.out.println("3. Hapus Stok Barang");
        System.out.println("4. Lihat Data Pelanggan Barang");
        System.out.println("5. Exit");
    }

    public void bukaMenu(){
        while (true){
            showStok();
            showMenu();
            System.out.print("Masukan Pilihan Anda: ");
            int pilihan = input.nextInt();
            switch (pilihan){
                case 1:
                    menuTambahStock();
                    break;
                case 2:
                    menuEditStock();
                    break;
                case 3:
                    menuHapusStock();
                    break;

                case 4:
                    showCustomerData();
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Menu Yang Anda Masukan Tidak Tersedia !");
            }
        }
    }

    private void menuTambahStock(){
        showStok();
        String formatedData = tambahStock();
        fileStorageSystem.dataStock.add(formatedData);
    }

    private String tambahStock() {
        System.out.print("Masukan Nama Barang: ");
        input.nextLine();
        String namaBarang = input.nextLine();
        System.out.print("Masukan Harga Sewa: ");
        int hargaBarang = input.nextInt();
        System.out.print("Masukan Stock Barang: ");
        int stokBarang = input.nextInt();
        return formatData(namaBarang, hargaBarang, stokBarang);
    }


    private void showStok(){
        if(fileStorageSystem.dataStock.isEmpty()){
            System.out.println("Stock Saat Ini Belum Tersedia");
        } else{
            System.out.format("%2s %6s %10s %7s","NO.","NAMA" ,"HARGA" ,"STOK\n");
            bacaDanTampilakanStock();
        }

    }

    private static void bacaDanTampilakanStock() {
        int counter = 1;
        for(String data: fileStorageSystem.dataStock){
            String nama = fileStorageSystem.getSpecificData(data, 0);
            String harga = fileStorageSystem.getSpecificData(data, 1);
            String stok = fileStorageSystem.getSpecificData(data, 2);
            System.out.format("%1s %10s %8s %5s\n", counter,nama,harga,stok);
            counter++;
        }
    }

    private void showCustomerData(){
        if(fileStorageSystem.dataCustomer.isEmpty()){
            System.out.println("Saat Ini Masih Belum Ada Pemesanan");
        }else{
            bacaDanTampilkanDataPelanggan();
        }
    }

    private static void bacaDanTampilkanDataPelanggan() {
        int counter = 1;
        System.out.format("%2s %8s %13s %5s %8s %15s %15s %15s","NO.","NAMA", "NAMA-BARANG","JUMLAH-SEWA","HARGA","SURAT-JAMINAN","TANGGAL-PENYEWAAN","TANGGAL-PENGEMBALIAN\n");
        for(String data : fileStorageSystem.dataCustomer){
            String customerName = fileStorageSystem.getSpecificData(data, 0);
            String objectName = fileStorageSystem.getSpecificData(data, 1);
            String rentAmount = fileStorageSystem.getSpecificData(data, 2);
            String price = fileStorageSystem.getSpecificData(data, 3);
            String suratJaminan = fileStorageSystem.getSpecificData(data, 4);
            String rentDate = fileStorageSystem.getSpecificData(data, 5);
            String returnDate = fileStorageSystem.getSpecificData(data, 6);
            System.out.format("%2s %10s %10s %6s %15s %10s %20s %18s\n",counter,customerName,objectName,rentAmount,price,suratJaminan,rentDate,returnDate);
            counter++;
        }
    }

    private boolean isIndexValid(ArrayList<String>objectData, int selectedIndex){
        return selectedIndex < objectData.size() && selectedIndex >= 0;
    }

    private void menuEditStock(){
        showStok();
        System.out.print("Masukan Nomor Baris Data Yang Ingin Di Edit: ");
        editStock();

    }

    private void editStock() {
        int indexData = input.nextInt() - 1;
        if(isIndexValid(fileStorageSystem.dataStock,indexData)){
            System.out.print("Masukan Nama Barang Yang Baru: ");
            String dataBaru = input.nextLine();
            System.out.print("Masukan Harga Sewa Barang Yang Baru: ");
            int hargaBaru = input.nextInt();
            System.out.print("Masukan stok Barang Yang Baru: ");
            int stokBaru = input.nextInt();
            String editedData = formatData(dataBaru, hargaBaru, stokBaru);
            fileStorageSystem.dataStock.set(indexData, editedData);
        }else {
            System.out.println("Data Stok Yang Anda Pilih Tidak Tersedia !");
        }
    }


    public void menuHapusStock(){
        showStok();
        System.out.print("Masukan Nomor Baris Data Yang Ingin Di Hapus: ");
        hapusStock();
    }

    private void hapusStock() {
        int indexData = input.nextInt() -1;
        if(isIndexValid(fileStorageSystem.dataStock,indexData)){
            fileStorageSystem.dataStock.remove(indexData);
        }else{
            System.out.println("Data Stok Yang Anda Pilih Tidak Tersedia !");
 }
    }
}