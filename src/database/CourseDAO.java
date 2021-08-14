package database;

import main.DataKlasse;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CourseDAO extends BaseDAO {


    public void AddCourse() {
        try {
            //insert to database
            String command = "INSERT INTO Course Values(?,?)";
            PreparedStatement stmt = getConnection().prepareStatement(command);
            stmt.setInt(1, DataKlasse.getCourseId());
            stmt.setInt(2, DataKlasse.getUserID());
            stmt.executeUpdate();
            stmt.close();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static ArrayList<String> GetCourse() throws SQLException {
        //select from database

        ArrayList<String> Courses = new ArrayList<>();
        String command = "select * from Course";
        PreparedStatement stmt = getConnection().prepareStatement(command);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            Courses.add(rs.getString(1));
        }
        stmt.close();
        return Courses;
    }

    public static void getId() {
        try {

            PreparedStatement stmt = getConnection().prepareStatement("SELECT CourseId FROM Course WHERE Name = ?");
            stmt.setString(1, DataKlasse.getCourseName());
            stmt.executeQuery();
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {

                int id = rs.getInt("CourseId");
            }
                DataKlasse.setCourseId(Integer.valueOf(rs.getString("CourseId")));

            rs.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


}
