package application.authentication;


import application.MainScreen;
import application.MainScreenAdmin;
import database.BaseDAO;
import database.UserDao;
import main.DataKlasse;
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
        private static final JPanel container2 = new JPanel();
        private final JLabel nameLabel = new JLabel("Name");
        private final JLabel passwordLabel = new JLabel("Password");
        private final JTextField nameInput = new JTextField();
        private final JPasswordField passwordInput = new JPasswordField();
        private final JCheckBox showPassword = new JCheckBox("Show Password");
        private final JButton inlogbutton = new JButton("Login");
        private final JButton registerbutton = new JButton("Register");
        
            Registerpage registerpage = new Registerpage();
            MainScreen choose;

    {
        try {
            choose = new MainScreen();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }


    public Inlogpage() throws SQLException {

        DataKlasse.setClassname(this.getClass());
            setLayoutM();
            setFrameSizeAndLocation();


            main.DataKlasse.getFrame().setContentPane(container2);
            main.DataKlasse.getFrame().setVisible(true);

            registerbutton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Registerpage registerFrame = new Registerpage();
                    addToframe(registerpage);
                    Inlogpage.this.remove(container2);
                    Inlogpage.this.addToframe(registerFrame);
                }
            });
            inlogbutton.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    //select from database
                    UserDao userDao = null;
                    try {
                        userDao = new UserDao();
                    } catch (FileNotFoundException fileNotFoundException) {
                        fileNotFoundException.printStackTrace();
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                    userDao.loginUser();


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



    public JButton getInlogButton(){
            return inlogbutton;
        }
        public JTextField getNameInput(){
            return nameInput;
        }
        public JTextField getPasswordInput(){
            return passwordInput;
        }

        private boolean isAdmin(int logedInIsAdmin){
            boolean admin = false;
            if (logedInIsAdmin==0){
                admin = false;
            }
            else if (logedInIsAdmin==1){
                admin = true;
            }
            return admin;
        }

        public void setLayoutM() {
            container2.setLayout(null);
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
            container2.add(nameLabel);
            container2.add(passwordLabel);
            container2.add(nameInput);
            container2.add(inlogbutton);
            container2.add(registerbutton);
            container2.add(passwordInput);
            container2.add(showPassword);
        }
    public void addToframe(MainScreen mainScreen) {

    }
    private void addToframe(MainScreenAdmin chooseAdmin) {
    }


    public Container getContainer(){
            return container2;
        }
        public JFrame getFrame(){return frame;}
    }

