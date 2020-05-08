import java.util.ArrayList;

public class DataPandemi extends ArrayList<DataPandemi> {
    private String negara, search;
    private int dataTerdeteksi;
    private int dataSembuh;
    private int dataMeninggal;

    public DataPandemi() {

    }

    public DataPandemi(int dataTerdeteksi) {
        this.dataTerdeteksi = dataTerdeteksi;
    }


    public void setDataPandemi(String negara, int dataTerdeteksi, int dataSembuh, int dataMeninggal){
        this.negara = negara;
        this.dataTerdeteksi = dataTerdeteksi;
        this.dataSembuh = dataSembuh;
        this.dataMeninggal = dataMeninggal;
        System.out.println(this.dataTerdeteksi);
    }

    public DataPandemi(String negara, int dataTerdeteksi, int dataSembuh, int dataMeninggal){
        this.negara = negara;
        this.dataTerdeteksi = dataTerdeteksi;
        this.dataSembuh = dataSembuh;
        this.dataMeninggal = dataMeninggal;
    }


    public void TambahTerdeteksi(int jumlahTerdeteksi){
        dataTerdeteksi = dataTerdeteksi + jumlahTerdeteksi;
        System.out.println("Ta" + dataTerdeteksi);
    }

    public void TambahMeninggal(int jumlahMeninggal){
        dataMeninggal = dataMeninggal + jumlahMeninggal;
    }

    public void TambahSembuh(int jumlahSembuh){
        dataSembuh = dataSembuh + jumlahSembuh;
    }

    public String getNegara() {
        return negara;
    }

    public int getDataTerdeteksi() {
        return dataTerdeteksi;
    }

    public int getDataSembuh() {
        return dataSembuh;
    }

    public int getDataMeninggal() {
        return dataMeninggal;
    }

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }
}
