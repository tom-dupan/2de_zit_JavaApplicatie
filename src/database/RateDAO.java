package database;

import main.DataKlasse;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RateDAO extends BaseDAO {
    PreparedStatement stmt = null;

    public void setrating() {
        try {
            stmt = BaseDAO.getConnection().prepareStatement("INSERT INTO User (rating,description) VALUES (?,?) WHERE username = ?");
            stmt.setInt(1, DataKlasse.getRating());
            stmt.setString(2, DataKlasse.getDescription());
            stmt.setString(3,DataKlasse.getTutorName());
            stmt.executeUpdate();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
