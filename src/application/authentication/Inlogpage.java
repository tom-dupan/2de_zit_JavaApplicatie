package application.authentication;


import application.MainScreen2;
import database.BaseDAO;
import main.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.sql.*;

public class Inlogpage extends JFrame {



    //Database reference
        private BaseDAO baseDao = new BaseDAO() {};
        private Connection conn;

        private final JFrame frame = new JFrame();
        private final JPanel container1 = new JPanel();
        private final JLabel nameLabel = new JLabel("Name");
        private final JLabel passwordLabel = new JLabel("Password");
        private final JTextField nameInput = new JTextField();
        private final JPasswordField passwordInput = new JPasswordField();
        private final JCheckBox showPassword = new JCheckBox("Show Password");
        private final JButton inlogbutton = new JButton("Login");
        private final JButton registerbutton = new JButton("Register");
        
            Registerpage registerpage = new Registerpage();
            MainScreen2 choose = new MainScreen2();


    public Inlogpage() throws SQLException {

            setLayoutM();
            setFrameSizeAndLocation();


            main.DataKlasse.getFrame().setContentPane(container1);
            main.DataKlasse.getFrame().setVisible(true);

            registerbutton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Registerpage registerFrame = new Registerpage();
                    addToframe(registerpage);
                    Inlogpage.this.remove(container1);
                    Inlogpage.this.addToframe(registerFrame);
                }
            });
            inlogbutton.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    //select from database

                    String username = nameInput.getText();
                    String password = passwordInput.getText();

                    byte[] salt = null;
                    byte[] encryptedpass = null;


                    try {
                        conn = baseDao.getConnection();
                        String saltStatement = "SELECT Salt FROM User WHERE Username = ?";
                        PreparedStatement saltstmt = conn.prepareStatement(saltStatement,ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                        saltstmt.setString(1, username);
                        ResultSet saltrs = saltstmt.executeQuery();

                        while (saltrs.next()){
                            salt = saltrs.getBytes(1);
                        }

                        encryptedpass = Passwords.hash(password.toCharArray() ,salt);
                        String sqlStatement = "SELECT UserId, Username FROM User WHERE Username = ? AND Password = ?";
                        PreparedStatement stmt = conn.prepareStatement(sqlStatement,ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                        stmt.setString(1,username);
                        stmt.setBytes(2,encryptedpass);
                        ResultSet rs = stmt.executeQuery();

                        //If resultset has no next, then specific combination of username and password does not exist in database
                        if (!rs.next())
                            JOptionPane.showMessageDialog(frame, "User not found or combination does not coincide. Please try again.");
                        else {
                            //Iterate through resultset to find a user
                            int logedInUserId = 0;
                            String logedInUserName="";
                            rs.beforeFirst();
                            while (rs.next()) {
                                logedInUserId = Integer.parseInt(rs.getString(1));
                                logedInUserName = rs.getString(2);
                            }
                            rs.close();
                            stmt.close();

                            main.DataKlasse.setUserID(logedInUserId);
                            main.DataKlasse.setUserName(logedInUserName);

                            MainScreen2 choose = new MainScreen2();
                            Inlogpage.this.remove(container1);
                            Inlogpage.this.addToframe(choose);
                        }

                    } catch (SQLException | FileNotFoundException throwables) {
                        throwables.printStackTrace();
                    }
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

    private void addToframe(MainScreen2 choose) {
    }

    public JButton getInlogButton(){
            return inlogbutton;
        }
        public JTextField getNameInput(){
            return nameInput;
        }
        public JTextField getPasswordInput(){
            return passwordInput;
        }



        public void setLayoutM() {
            container1.setLayout(null);
        }

        public void setFrameSizeAndLocation() {
            nameLabel.setBounds(150, 150, 100, 30);
            nameInput.setBounds(300, 150, 300, 30);
            passwordLabel.setBounds(150, 220, 100, 30);
            passwordInput.setBounds(300, 220, 300, 30);
            showPassword.setBounds(300, 250, 150, 30);
            inlogbutton.setBounds(150, 300, 100, 30);
            registerbutton.setBounds(300, 300, 100, 30);
        }

        public void addToframe(Registerpage registerpage) {
            container1.add(nameLabel);
            container1.add(passwordLabel);
            container1.add(nameInput);
            container1.add(inlogbutton);
            container1.add(registerbutton);
            container1.add(passwordInput);
            container1.add(showPassword);
        }
        public Container getContainer(){
            return container1;
        }
        public JFrame getFrame(){return frame;}
    }

