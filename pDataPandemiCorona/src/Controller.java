import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Controller {
    DataPandemi dataPandemi;
    View view;
    DAO dao;

    public Controller(DataPandemi dataPandemi, View view, DAO dao){
        this.dataPandemi = dataPandemi;
        this.view = view;
        this.dao = dao;

        if (dao.getJmlData() != 0){
            String datas[][] = dao.read();
            view.tabel.setModel((new JTable(datas, view.namaKolom)).getModel());
        } else {
            JOptionPane.showMessageDialog(null, "Data Masih Kosong");
        }

        view.bSearch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String search = view.getSearch();
                dataPandemi.setSearch(search);
                String datas[][] = dao.search(dataPandemi);
                view.tabel.setModel((new JTable(datas, view.namaKolom)).getModel());
            }
        });

        view.bRefresh.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String datas[][] = dao.read();
                view.fNegara.setText("");
                view.fTerdeteksi.setText("");
                view.fSembuh.setText("");
                view.fMeninggal.setText("");
                view.tabel.setModel((new JTable(datas, view.namaKolom)).getModel());
            }
        });

        view.bTambah.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList<DataPandemi> dataPandemis = new ArrayList<>();
                dao.tulisList(dataPandemis);
                System.out.println(dataPandemis.isEmpty());
                for (DataPandemi d : dataPandemis){
                    System.out.println(d.getNegara());
                    if (d.getNegara().equalsIgnoreCase(view.getNegara())){
                        JOptionPane.showMessageDialog(null, "Data Sudah Ada");
                    }
                }
                DataPandemi dataPandemiBaru = new DataPandemi(view.getNegara(), view.getTerdeteksi(), view.getSembuh(), view.getMeninggal());
                dataPandemis.add(dataPandemiBaru);
                dao.tambah(dataPandemiBaru);
                JOptionPane.showMessageDialog(null, "Anda Menambahkan Data Baru");

            }
        });

        view.bUpdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    ArrayList<DataPandemi> dataPandemis = new ArrayList<>();
                    dao.tulisList(dataPandemis);

                    for (DataPandemi d : dataPandemis){
                        if (d.getNegara().equalsIgnoreCase(view.getNegara())){
                            System.out.println(dataPandemis.isEmpty());
                            d.TambahTerdeteksi(view.getTerdeteksi());
                            d.TambahSembuh(view.getSembuh());
                            d.TambahMeninggal(view.getMeninggal());
                            DataPandemi pandemi = new DataPandemi(view.getNegara(), d.getDataTerdeteksi(), d.getDataSembuh(), d.getDataMeninggal());
                            dao.update(pandemi);
                            JOptionPane.showMessageDialog(null, "Anda Menambahkan Data Yang Sudah Ada");
                        }
                    }

                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Harap Isi Semua Field!");
                }
            }
        });

        view.bExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int input = JOptionPane.showConfirmDialog(null, "Apa anda yakin ingin keluar?", "Pilih Opsi...", JOptionPane.YES_NO_OPTION);

                if (input == 0) {
                    System.exit(0);
                }
            }
        });
    }
}
