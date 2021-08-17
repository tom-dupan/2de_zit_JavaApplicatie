package database;

import main.DataKlasse;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RateDAO extends BaseDAO {
    PreparedStatement stmt = null;

    public void setrating() {
        try {
            stmt = BaseDAO.getConnection().prepareStatement("INSERT INTO User (rating) VALUES (?) WHERE username = ?");
            stmt.setString(1, String.valueOf(DataKlasse.getRating()));
            stmt.setString(2,DataKlasse.getTutorName());
            stmt.executeUpdate();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
