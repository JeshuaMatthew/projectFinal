package aplikasipendataanpenyewaankamera;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) throws IOException {
        fileStorageSystem manageFile = new fileStorageSystem();
        AdminMenu admin = new AdminMenu();
        ArrayList<String> stokData = manageFile.getRecords("stokdata.txt");
        ArrayList<String> costumerData = manageFile.getRecords("costumerdata.txt");
        while (true) {
            System.out.println("Aplikasi pendataan Sewa Kamera");
            System.out.println("Silahkan pilih login sebagai :");
            System.out.println("1. Admin");
            System.out.println("2. Pengguna");
            Scanner input = new Scanner(System.in);

            int pilihan = input.nextInt();
            switch (pilihan) {
                case 1:
                    stokData = admin.bukaMenu(stokData);
                    break;

                case 2:
                    //showemenu
                    break;

                case 3:
                    System.exit(0);
                    break;

                default:
                    System.out.println("PIlihan yang anda masukan!");
            }
            manageFile.storeData(costumerData, "costumerdata.txt", false);
            manageFile.storeData(stokData, "stokdata.txt", false);
        }
    }
}
