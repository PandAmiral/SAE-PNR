package SAE_PNR;
import java.sql.*;

public class testConnectDb {
    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/pnr", "root", "XP?mng8!UPk");
            Statement s = c.createStatement();
            ResultSet r = s.executeQuery("SELECT * FROM nid_gci");
            while (r.next()) {
                System.out.println(r.getInt("id_nid") + " " + r.getString("nom_plage"));
            }
            r.close();
            s.close();
            c.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}