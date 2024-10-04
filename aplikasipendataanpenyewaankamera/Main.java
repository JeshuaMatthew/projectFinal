package aplikasipendataanpenyewaankamera;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;


public class Main {
    public static Scanner input = new Scanner(System.in);

    public static void main(String[] args) throws IOException {

        fileStorageSystem manageFile = new fileStorageSystem();

        AdminMenu admin = new AdminMenu();

        customerMenu customer = new customerMenu();

        manageFile.createNecessaryDirectory();

        ArrayList<String> stokData = manageFile.getRecords("stockDirectory/stockRecords.txt");

        ArrayList<String> customerData = manageFile.getRecords("customerDirectory/customerRecords.txt");

        while (true) {
            showMenu();
            int pilihan = input.nextInt();
            switch (pilihan) {
                case 1:
                    stokData = admin.bukaMenu(stokData,customerData);
                    break;

                case 2:
                    customerData = customer.placeOrder(customerData,stokData);
                    stokData = customer.subtractStock(stokData);
                    break;

                case 3:
                    System.exit(0);
                    break;

                default:
                    System.out.println("PIlihan yang anda masukan!");
            }
            manageFile.storeCustomerStockData(customerData,stokData);
        }
    }

    private static void showMenu() {
        System.out.println("Aplikasi pendataan Sewa Kamera");
        System.out.println("Silahkan pilih login sebagai :");
        System.out.println("1. Admin");
        System.out.println("2. Pengguna");
        System.out.print("Masukan Pilihan Anda : ");
    }
}