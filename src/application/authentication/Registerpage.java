package application.authentication;

import database.BaseDAO;
import main.DataKlasse;

import javax.swing.*;
import javax.xml.crypto.Data;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Registerpage extends JFrame{

        //Database reference
        private BaseDAO baseDao = new BaseDAO() {};
        private Connection conn;


        //View elements
        private final JFrame frame = new JFrame();
        private final Container container1 = getContentPane();
        private final JLabel nameLabel = new JLabel("Name");
        private final JLabel passwordLabel = new JLabel("Password");
        private final JTextField nameInput = new JTextField();
        private final JPasswordField passwordInput = new JPasswordField();
        private final JCheckBox showPassword = new JCheckBox("show Password");
        private final JButton registerbutton = new JButton("register");

        //String elements
        private String username;
        private String password ;
        private int userid=0;


        public Registerpage() {


            setLayoutM();
            setFrameSizeAndLocation();
            addToframe();

            main.DataKlasse.getFrame().setContentPane(container1);
            main.DataKlasse.getFrame().setVisible(true);

            registerbutton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                    DataKlasse.setUserName(username);
                    DataKlasse.setPassword(password);
                    registerUser();

                }
            });
            showPassword.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (e.getSource() == showPassword) {
                        if (showPassword.isSelected()) {
                            passwordInput.setEchoChar((char) 0);
                        } else {
                            passwordInput.setEchoChar('*');
                        }
                    }
                }
            });
        }

        public void registerUser(){
            //insert to database
            username = nameInput.getText();
            password = passwordInput.getText();
            byte[] salt = Passwords.getNextSalt();

            if (password.length() < 8) {
                JOptionPane.showMessageDialog(frame, "Password must be at least 8 characters long");
            } else {
                byte[] encryptedpassword = null;

                //Salt encrypted password
                encryptedpassword = Passwords.hash(password.toCharArray(), salt);

                //Make a ReviewPerDay instance


                try {
                    conn = baseDao.getConnection();
                    String command = "INSERT INTO User( Username, Password, Salt, OwnedDeck)Values(?,?,?,?)";
                    PreparedStatement stmt = conn.prepareStatement(command, Statement.RETURN_GENERATED_KEYS);
                    stmt.setString(1,username);
                    stmt.setBytes(2, encryptedpassword);
                    stmt.setBytes(3, salt);
                    stmt.setInt(4, 0);
                    int affectedrows = stmt.executeUpdate();
                    if (affectedrows == 0) {
                        throw new SQLException("Creating user failed, no rows affected.");
                    }
                    else{
                        try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                            if (generatedKeys.next()) {
                                //https://stackoverflow.com/questions/1915166/how-to-get-the-insert-id-in-jdbc
                                //user.setId(generatedKeys.getLong(1));
                                userid = generatedKeys.getInt(1);
                            }
                            else {throw new SQLException("Creating user failed, no ID obtained."); }
                        }
                    }
                    conn.commit();
                    stmt.close();


                } catch (SQLException  throwables) {
                    //Give this dialog is username already exists
                    if (throwables.getErrorCode() == 1062)
                        JOptionPane.showMessageDialog(frame, "This username already exists! Please chose another one.");
                    throwables.printStackTrace();
                }
            }
            //Go back to login page

            Registerpage.this.dispose();
        }



        public JButton getRegisterbuttonButton() { return registerbutton; }
        public JTextField getNamelabel() { return nameInput; }
        public JTextField getPasswordInput() { return passwordInput; }

        public void setLayoutM() {
            container1.setLayout(null);
        }

        public void setFrameSizeAndLocation() {
            nameLabel.setBounds(50, 150, 100, 30);
            registerbutton.setBounds(200, 300, 100, 30);
            passwordInput.setBounds(150, 220, 150, 30);
            showPassword.setBounds(150, 250, 150, 30);
            passwordLabel.setBounds(50, 220, 100, 30);
            nameInput.setBounds(150, 150, 150, 30);
        }

        public void addToframe() {
            container1.add(nameLabel);
            container1.add(passwordLabel);
            container1.add(nameInput);
            container1.add(registerbutton);
            container1.add(passwordInput);
            container1.add(showPassword);

        }


    }

