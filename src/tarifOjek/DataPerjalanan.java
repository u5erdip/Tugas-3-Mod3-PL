
//introduce parameter
package tarifOjek;

public class DataPerjalanan {
    private final double jarak;
    private final double tarifPerKm;
    private final int waktu;
    private double biayaTambahan;
    private final double tarifDasar;

    public DataPerjalanan(double jarak, double tarifPerKm, int waktu, double biayaTambahan, double tarifDasar) {
        this.jarak = jarak;
        this.tarifPerKm = tarifPerKm;
        this.waktu = waktu;
        this.biayaTambahan = biayaTambahan;
        this.tarifDasar = tarifDasar;
    }

    public double getJarak() {
        return jarak;
    }

    public double getTarifPerKm() {
        return tarifPerKm;
    }

    public int getWaktu() {
        return waktu;
    }

    public double getBiayaTambahan() {
        return biayaTambahan;
    }

    public double getTarifDasar() {
        return tarifDasar;
    }

    public void setBiayaTambahan(double biayaTambahan) {
        this.biayaTambahan = biayaTambahan;
    }


}
