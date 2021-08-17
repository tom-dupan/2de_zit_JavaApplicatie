package application.authentication;

import database.BaseDAO;
import database.UserDao;
import main.DataKlasse;

import javax.swing.*;
import javax.xml.crypto.Data;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.sql.*;

public class Registerpage extends JFrame{


    //Database reference
        private BaseDAO baseDao = new BaseDAO() {};
        private Connection conn;


        //View elements
        private final JFrame frame = new JFrame();
    private final JLabel nameLabel = new JLabel("Name");
    private final JLabel passwordLabel = new JLabel("Password");
        private final Container container1 = getContentPane();
        private final JTextField nameInput = new JTextField();
        private final JPasswordField passwordInput = new JPasswordField();
        private final JCheckBox showPassword = new JCheckBox("show Password");
        private final JButton registerbutton = new JButton("register");
        Inlogpage inlogpage;
        //String elements
        private String username;
        private String password ;



        public Registerpage() {


            setLayoutM();
            setFrameSizeAndLocation();
            addToframe();
            DataKlasse.setFrame(this.frame);

            main.DataKlasse.getFrame().setVisible(true);

            registerbutton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                    DataKlasse.setUserName(nameInput.getText());
                    DataKlasse.setPassword(passwordInput.getPassword());
                    UserDao userDao = null;
                    try {
                        userDao = new UserDao();
                    } catch (FileNotFoundException fileNotFoundException) {
                        fileNotFoundException.printStackTrace();
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                    userDao.registerUser();
                    Registerpage.this.dispose();
                    Registerpage.this.add(inlogpage);

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

