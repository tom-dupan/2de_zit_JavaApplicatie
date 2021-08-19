package application.authentication;

import database.UserDao;
import main.DataKlasse;
import application.authentication.Inlogpage2;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.sql.SQLException;

public class Registerpage2 extends JFrame{
    private JPanel panel1;
    private JTextField nameInput;
    private JButton registerButton;
    private JButton loginButton;
    private JPasswordField passwordInput;
    private JButton showpasswordButton;



    public Registerpage2() {
        registerButton.addActionListener(new ActionListener() {
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
            }
        });
        showpasswordButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == showpasswordButton) {
                    if (showpasswordButton.isSelected()) {
                        passwordInput.setEchoChar((char) 0);
                    } else {
                        passwordInput.setEchoChar('*');
                    }
                }
            }
        });
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Inlogpage2 registerFrame = new Inlogpage2();
                panel1.removeAll();
                panel1.repaint();
                panel1.add(registerFrame);
            }
        });
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}

