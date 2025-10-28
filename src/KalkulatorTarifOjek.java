import tarifOjek.DataPerjalanan;

import java.util.Scanner;

public class KalkulatorTarifOjek {
    private static DataPerjalanan dataPerjalanan;

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.println("=================================");
        System.out.println("  KALKULATOR TARIF OJEK ONLINE");
        System.out.println("=================================");

        // Input jarak
        System.out.print("Masukkan jarak (km): ");
        double jarak = input.nextDouble();

        // Input waktu perjalanan
        System.out.print("Masukkan waktu perjalanan (menit): ");
        int waktu = input.nextInt();

        // Input jenis layanan
        System.out.println("\nPilih Jenis Layanan:");
        System.out.println("1. Reguler");
        System.out.println("2. Premium");
        System.out.print("Pilihan Anda: ");
        int pilihan = input.nextInt();

        // Hitung tarif
        double totalTarifPerjalanan = 0;
        double tarifDasar = 2000;
        double tarifPerKm = 0;
        double tarifWaktu = 0;
        double biayaTambahan = 0;

        // Tentukan tarif per km berdasarkan layanan
        if (pilihan == 1) {
            tarifPerKm = 2500;
        } else if (pilihan == 2) {
            tarifPerKm = 4000;
        }

        // Hitung biaya jarak > exct method
        Result result = getResult(jarak, tarifPerKm, waktu, biayaTambahan, tarifDasar);

        // Tampilkan hasil
        System.out.println("\n=================================");
        System.out.println("        RINCIAN TARIF");
        System.out.println("=================================");
        System.out.println("Jenis Layanan   : " + (pilihan == 1 ? "Reguler" : "Premium"));
        System.out.println("Jarak           : " + jarak + " km");
        System.out.println("Waktu           : " + waktu + " menit");
        System.out.println("---------------------------------");
        System.out.println("Tarif Dasar     : Rp " + String.format("%.0f", tarifDasar));
        System.out.println("Biaya Jarak     : Rp " + String.format("%.0f", result.biayaJarak()));
        System.out.println("Biaya Waktu     : Rp " + String.format("%.0f", result.tarifWaktu()));
        if (result.biayaTambahan() > 0) {
            System.out.println("Biaya Tambahan  : Rp " + String.format("%.0f", result.biayaTambahan()));
        }
        if (jarak < 2) {
            System.out.println("Diskon (10%)    : -Rp " + String.format("%.0f", result.totalTarifPerjalanan() * 0.1 / 0.9));
        }
        System.out.println("=================================");
        System.out.println("TOTAL TARIF     : Rp " + String.format("%.0f", result.totalTarifPerjalanan()));
        System.out.println("=================================");
        System.out.println();
        input.close();
    }

    private static Result getResult(double jarak, double tarifPerKm, int waktu, double biayaTambahan, double tarifDasar) {
        double totalTarifPerjalanan;
        double tarifWaktu;
        double biayaJarak = jarak * tarifPerKm;

        // Hitung biaya waktu (Rp 500 per menit)
        tarifWaktu = waktu * 500;

        // Biaya tambahan untuk jarak jauh (lebih dari 10 km)
        if (jarak > 10) {
            biayaTambahan = (jarak - 10) * 1000;
        }

        // Biaya tambahan untuk waktu macet (lebih dari 30 menit)
        if (waktu > 30) {
            biayaTambahan += (dataPerjalanan.getWaktu() - 30) * 300;
        }

        // Total tarif > rename variable
        totalTarifPerjalanan = dataPerjalanan.getTarifDasar() + biayaJarak + tarifWaktu + dataPerjalanan.getBiayaTambahan();

        // Diskon untuk jarak pendek (kurang dari 2 km)
        if (dataPerjalanan.getJarak() < 2) {
            totalTarifPerjalanan = totalTarifPerjalanan * 0.9; // Diskon 10%
        }
        Result result = new Result(totalTarifPerjalanan, tarifWaktu, dataPerjalanan.getBiayaTambahan(), biayaJarak);
        return result;
    }

    private record Result(double totalTarifPerjalanan, double tarifWaktu, double biayaTambahan, double biayaJarak) {
    }
}
