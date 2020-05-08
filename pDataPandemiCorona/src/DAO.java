import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;

public class DAO {
    Connection koneksi;
    Statement statement;
    View view;

    public DAO(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost/db_pandemi";
            koneksi = DriverManager.getConnection(url,"root", "");
            statement = koneksi.createStatement();
        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null,"Class Not Found : " + ex);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "SQL Exeption : " + ex);
        }
    }

    public void tambah(DataPandemi dataPandemi){
        try {
            if (getJmlDataData() == 0){
                String query1 = "INSERT INTO tb_history VALUES (null,'" + dataPandemi.getNegara() + "'," + dataPandemi.getDataTerdeteksi() + "," + dataPandemi.getDataSembuh() + ", " + dataPandemi.getDataMeninggal() + ")";
                statement.executeUpdate(query1);
            }
            String query = "INSERT INTO tb_history VALUES (null,'" + dataPandemi.getNegara() + "'," + dataPandemi.getDataTerdeteksi() + "," + dataPandemi.getDataSembuh() + ", " + dataPandemi.getDataMeninggal() + ")";
            statement.executeUpdate(query);
            JOptionPane.showMessageDialog(null, "Data Berhasil di Simpan");

        } catch (Exception sql){
            JOptionPane.showMessageDialog(null, sql.getMessage());
        }
    }

    public void tulisList(ArrayList<DataPandemi> dataPandemis){
        try {
            int jml = 0;
            String query1;
            String dataku[][] = new String[getJmlData()][4];
                query1 = "SELECT * FROM tb_data";
                ResultSet resultSet1 = statement.executeQuery(query1);
                while (resultSet1.next()) {
                    dataku[jml][0] = resultSet1.getString("negara");
                    dataku[jml][1] = resultSet1.getString("terdeteksi");
                    dataku[jml][2] = resultSet1.getString("sembuh");
                    dataku[jml][3] = resultSet1.getString("meninggal");
                    DataPandemi dataPandemiBaru = new DataPandemi(dataku[jml][0], Integer.parseInt(dataku[jml][1]), Integer.parseInt(dataku[jml][2]), Integer.parseInt(dataku[jml][3]));
                    dataPandemis.add(dataPandemiBaru);
                    jml++;
                }
                for (DataPandemi d : dataPandemis){
                    if (!(d.getNegara().equalsIgnoreCase(view.getNegara()))){
                        String query = "INSERT INTO tb_data VALUES (null,'" + view.getNegara() + "'," + view.getTerdeteksi() + "," + view.getSembuh() + "," + view.getMeninggal() + ")";
                        statement.executeUpdate(query1);
                    }
                }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void update(DataPandemi dataPandemi) {
        try {
            String query = "UPDATE tb_data SET terdeteksi = " + dataPandemi.getDataTerdeteksi() + ",sembuh = " + dataPandemi.getDataSembuh() + ", meninggal = " + dataPandemi.getDataMeninggal() + " WHERE negara = '" + view.getNegara() + "'";
            statement = koneksi.createStatement();
            statement.executeUpdate(query);
            JOptionPane.showMessageDialog(null, "Data Berhasil Diupdate");
        } catch (Exception sql) {
            JOptionPane.showMessageDialog(null, sql.getMessage());
        }
        tambah(dataPandemi);
    }

    public String[][] read(){
        try {
            int jmlData = 0;
            int nomor = 1;
            String data[][] = new String[getJmlData()][5];
            String query = "SELECT * FROM tb_history";
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                data[jmlData][0] = Integer.toString(nomor);
                data[jmlData][1] = resultSet.getString("negara");
                data[jmlData][2] = resultSet.getString("terdeteksi");
                data[jmlData][3] = resultSet.getString("sembuh");
                data[jmlData][4] = resultSet.getString("meninggal");
                nomor++;
                jmlData++;
            }
            return data;
        } catch (SQLException e){
            JOptionPane.showMessageDialog(null, e.getMessage());
            return null;
        }
    }

    public int getJmlData(){
        int jmlData = 0;
        try {
            String query = "SELECT * FROM tb_history";
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()){
                jmlData++;
            }

            return jmlData;
        } catch (SQLException e){
            JOptionPane.showMessageDialog(null, e.getMessage());

            return 0;
        }
    }

    public int getJmlDataData(){
        int jmlData = 0;
        try {
            String query = "SELECT * FROM tb_data";
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()){
                jmlData++;
            }

            return jmlData;
        } catch (SQLException e){
            JOptionPane.showMessageDialog(null, e.getMessage());

            return 0;
        }
    }

    public String[][] search(DataPandemi dataPandemi) {
        try {
            int jmlData = 0;
            int nomor = 1;
            String datas[][] = new String[getJmlDataSearch(dataPandemi)][5];
            String query = "SELECT * FROM tb_history WHERE negara LIKE '%" + dataPandemi.getSearch() + "%'";
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                datas[jmlData][0] = Integer.toString(nomor);
                datas[jmlData][1] = resultSet.getString("negara");
                datas[jmlData][2] = resultSet.getString("terdeteksi");
                datas[jmlData][3] = resultSet.getString("sembuh");
                datas[jmlData][4] = resultSet.getString("meninggal");
                nomor++;
                jmlData++;
            }

            return datas;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());

            return null;
        }
    }

    public int getJmlDataSearch(DataPandemi dataPandemi) {
        int jmlData = 0;
        try {
            String query = "SELECT * FROM tb_history WHERE negara LIKE '%" + dataPandemi.getSearch() + "%'";
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                jmlData++;
            }

            return jmlData;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());

            return 0;
        }
    }
}
