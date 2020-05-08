import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class View extends JFrame {
    JLabel lNegara, lTerdeteksi, lSembuh, lMeninggal;
    JTextField fNegara, fTerdeteksi, fSembuh, fMeninggal, fSearch;
    JTable tabel;
    DefaultTableModel tableModel;
    JScrollPane scrollPane;
    Object namaKolom[] = {"#", "Nama Negara", "Terdeteksi", "Sembuh", "Meninggal"};
    JButton bRefresh, bTambah, bSearch, bExit, bUpdate;

    public View(){
        setTitle("Data Pandemi Corona");
        tableModel = new DefaultTableModel(namaKolom, 0);
        tabel = new JTable(tableModel);
        scrollPane = new JScrollPane(tabel);

        lNegara = new JLabel("Nama Negara");
        lTerdeteksi = new JLabel("Data Terdeteksi");
        lSembuh = new JLabel("Data Sembuh");
        lMeninggal = new JLabel("Data Meninggal");

        fNegara = new JTextField();
        fTerdeteksi = new JTextField();
        fSembuh = new JTextField();
        fMeninggal = new JTextField();
        fSearch = new JTextField();

        bTambah = new JButton("Tambah");
        bUpdate = new JButton("Update");
        bRefresh = new JButton("Refresh");
        bExit = new JButton("Exit");
        bSearch = new JButton("Search");

        setLayout(null);
        add(scrollPane);
        add(lNegara);
        add(lTerdeteksi);
        add(lSembuh);
        add(lMeninggal);

        add(fNegara);
        add(fTerdeteksi);
        add(fSembuh);
        add(fMeninggal);
        add(fSearch);

        add(bRefresh);
        add(bTambah);
        add(bUpdate);
        add(bExit);
        add(bSearch);

        scrollPane.setBounds(40,40,600, 350);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);

        lNegara.setBounds(660, 40, 100, 30);
        fNegara.setBounds(660, 70, 350, 25);
        lTerdeteksi.setBounds(660, 105, 100, 30 );
        fTerdeteksi.setBounds(660, 135, 150, 25);
        lSembuh.setBounds(660, 170, 100, 30 );
        fSembuh.setBounds(660, 200, 150, 25);
        lMeninggal.setBounds(660, 235, 100, 30 );
        fMeninggal.setBounds(660, 265, 150, 25);

        bRefresh.setBounds(40, 410, 100, 30);
        bTambah.setBounds(160,410,100,30);
        bExit.setBounds(280, 410,100,30);
        bUpdate.setBounds(400, 410, 100, 30);

        fSearch.setBounds(530, 410, 260, 25);
        bSearch.setBounds(810, 408, 100, 30);
        setSize(1100, 600);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

    }

    public String getNegara(){
        return fNegara.getText();
    }

    public int getTerdeteksi(){
        return (Integer.parseInt(fTerdeteksi.getText()));
    }

    public int getSembuh(){
        return (Integer.parseInt(fSembuh.getText()));
    }

    public int getMeninggal(){
        return (Integer.parseInt(fMeninggal.getText()));
    }

    public String getSearch(){
        return fSearch.getText();
    }
}
