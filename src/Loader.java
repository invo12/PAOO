import java.sql.*;
public class Loader {
    public static SaveInfo Load()
    {
        SaveInfo info = new SaveInfo();
        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:bacon.db");
            c.setAutoCommit(false);
            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery( "SELECT * FROM State;" );
            while ( rs.next() ) {
                info.playerX = rs.getInt("PlayerX");
                info.playerY = rs.getInt("PlayerY");
                info.currentLevel = rs.getInt("CurrentLevel");
                info.numberOfDeaths = rs.getInt("NumberOfDeaths");
                System.out.println(info.playerX + " " + info.playerY + " " + info.currentLevel + " " + info.numberOfDeaths);
            }
            rs.close();
            stmt.close();
            c.close();
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return info;
    }

    public static void Save(SaveInfo saveInfo)
    {
        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:bacon.db");
            c.setAutoCommit(false);
            stmt = c.createStatement();
            String sql = "UPDATE State set PlayerX = "+ saveInfo.playerX + ";";
            stmt.executeUpdate(sql);
            c.commit();
            sql = "UPDATE State set PlayerY = "+ saveInfo.playerY + ";";
            stmt.executeUpdate(sql);
            c.commit();
            sql = "UPDATE State set CurrentLevel = "+ saveInfo.currentLevel + ";";
            stmt.executeUpdate(sql);
            c.commit();
            sql = "UPDATE State set NumberOfDeaths = "+ saveInfo.numberOfDeaths + ";";
            stmt.executeUpdate(sql);
            c.commit();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}

