package aplikasipendataanpenyewaankamera;
import java.io.IOException;
import java.util.Scanner;


public class Main {
    public static Scanner input = new Scanner(System.in);


    public static void main(String[] args) throws IOException {

        fileStorageSystem manageFile = new fileStorageSystem();

        AdminMenu menuAdmin = new AdminMenu();

        customerMenu menuCustomer = new customerMenu();

        manageFile.createNecessaryDirectory();

        fileStorageSystem.dataStock = manageFile.getRecords("stockDirectory/stockRecords.txt");

        fileStorageSystem.dataCustomer = manageFile.getRecords("customerDirectory/customerRecords.txt");

        while (true) {
            showMenu();
            int pilihan = input.nextInt();
            switch (pilihan) {
                case 1:
                    menuAdmin.bukaMenu();
                    break;

                case 2:
                    menuCustomer.bukaMenu();
                    break;

                case 3:
                    manageFile.storeCustomerStockData(fileStorageSystem.dataCustomer, fileStorageSystem.dataStock);
                    System.exit(0);
                    break;

                default:
                    System.out.println("Menu Yang Anda Pilih Tidak Tersedia !");
            }
        }
    }

    private static void showMenu() {
        System.out.println("Aplikasi Pendataan Sewa Kamera");
        System.out.println("Silahkan Pilih Login Sebagai :");
        System.out.println("1. Admin");
        System.out.println("2. Pengguna");
        System.out.println("3. Keluar Menu");
        System.out.print("Masukan Pilihan Anda : ");
    }
}