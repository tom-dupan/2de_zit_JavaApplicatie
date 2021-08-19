package database;

import application.MainScreen;
import application.MainScreenAdmin;
import application.authentication.Inlogpage2;
import application.authentication.Passwords;
import main.DataKlasse;

import javax.swing.*;
import java.io.FileNotFoundException;
import java.sql.*;
import java.util.ArrayList;

public class UserDao extends BaseDAO {
    String username = DataKlasse.getUserName();
    String password = String.valueOf(DataKlasse.getPassword());
    Inlogpage2 inlogpage = new Inlogpage2();
    private int userid = 0;

    public UserDao() throws FileNotFoundException, SQLException {
    }

    public void registerUser() {

        //insert to database
        byte[] salt = Passwords.getNextSalt();

        if (password.length() < 8) {
            JOptionPane.showMessageDialog(DataKlasse.getFrame(), "Password must be at least 8 characters long");
        } else {
            byte[] encryptedpassword = null;

            //Salt encrypted password
            encryptedpassword = Passwords.hash(password.toCharArray(), salt);

            //Make a ReviewPerDay instance


            try {

                String command = "INSERT INTO User( Username, Password, Salt, OwnedDeck)Values(?,?,?,?)";
                PreparedStatement stmt = BaseDAO.getConnection().prepareStatement(command, Statement.RETURN_GENERATED_KEYS);
                stmt.setString(1, DataKlasse.getUserName());
                stmt.setBytes(2, encryptedpassword);
                stmt.setBytes(3, salt);
                stmt.setInt(4, 0);
                int affectedrows = stmt.executeUpdate();
                if (affectedrows == 0) {
                    throw new SQLException("Creating user failed, no rows affected.");
                } else {
                    try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                        if (generatedKeys.next()) {
                            //https://stackoverflow.com/questions/1915166/how-to-get-the-insert-id-in-jdbc
                            //user.setId(generatedKeys.getLong(1));
                            userid = generatedKeys.getInt(1);
                        } else {
                            throw new SQLException("Creating user failed, no ID obtained.");
                        }
                    }
                }

                stmt.close();


            } catch (SQLException throwables) {
                //Give this dialog is username already exists
                if (throwables.getErrorCode() == 1062)
                    JOptionPane.showMessageDialog(DataKlasse.getFrame(), "This username already exists! Please chose another one.");
                throwables.printStackTrace();
            }
        }
        //Go back to login page
    }

    public void loginUser() {

        //select from database

        byte[] salt = null;
        byte[] encryptedpass = null;


        try {

            String saltStatement = "SELECT Salt FROM User WHERE Username = ?";
            PreparedStatement saltstmt = BaseDAO.getConnection().prepareStatement(saltStatement, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            saltstmt.setString(1, username);
            ResultSet saltrs = saltstmt.executeQuery();

            while (saltrs.next()) {
                salt = saltrs.getBytes(1);
            }

            encryptedpass = Passwords.hash(password.toCharArray(), salt);
            String sqlStatement = "SELECT UserId, Username,IsAdmin  FROM User WHERE Username = ? AND Password = ?";
            PreparedStatement stmt = BaseDAO.getConnection().prepareStatement(sqlStatement, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            stmt.setString(1, username);
            stmt.setBytes(2, encryptedpass);
            ResultSet rs = stmt.executeQuery();

            //If resultset has no next, then specific combination of username and password does not exist in database
            if (!rs.next())
                JOptionPane.showMessageDialog(DataKlasse.getFrame(), "User not found or combination does not coincide. Please try again.");
            else {
                //Iterate through resultset to find a user
                int logedInUserId = 0;
                String logedInUserName = "";
                int logedInIsAdmin = 0;
                boolean admin = false;
                rs.beforeFirst();
                while (rs.next()) {
                    logedInUserId = Integer.parseInt(rs.getString(1));
                    logedInUserName = rs.getString(2);
                    logedInIsAdmin = rs.getInt(3);
                }
                rs.close();
                stmt.close();
                main.DataKlasse.setUserID(logedInUserId);
                main.DataKlasse.setUserName(logedInUserName);
                main.DataKlasse.setAdmin(admin);

                isAdmin(logedInIsAdmin);
                if (admin == true) {
                    MainScreen choose = new MainScreen();
                    DataKlasse.getFrame().dispose();
                    DataKlasse.setFrame(choose);


                } else {
                    MainScreenAdmin chooseAdmin = new MainScreenAdmin();
                    DataKlasse.getFrame().dispose();
                    DataKlasse.setFrame(chooseAdmin);
                }
            }

        } catch (SQLException | FileNotFoundException throwables) {
            throwables.printStackTrace();
        }
    }

    private boolean isAdmin(int logedInIsAdmin) {
        boolean admin = false;
        if (logedInIsAdmin == 0) {
            admin = false;
        } else if (logedInIsAdmin == 1) {
            admin = true;
        }
        return admin;
    }

    public ArrayList getAllUsers() throws SQLException {
        PreparedStatement stmt=BaseDAO.getConnection().prepareStatement("select * from User");
        ResultSet rs=stmt.executeQuery();
        ArrayList<String> userlijst = new ArrayList<String>();
        while(rs.next()){
                String username = rs.getString(2);
                int rating = rs.getInt(4);
                String description = rs.getString(5);
                String element = "Username: "+username+'\n'+"Rating: "+rating+'\n'+"description: "+description;
                userlijst.add(element);

        }
        return userlijst;
    }


}


