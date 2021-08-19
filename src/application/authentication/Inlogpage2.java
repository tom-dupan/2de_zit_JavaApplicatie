package application.authentication;

import database.UserDao;
import main.DataKlasse;
import application.authentication.Registerpage2;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.sql.SQLException;


public class Inlogpage2 extends JFrame {
    private JPanel panel1;
    private JTextField nameInput;
    private JButton registerButton;
    private JButton loginButton;
    private JPasswordField passwordInput;
    private JButton showpasswordButton;

    public void Inlogpage2() {
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Registerpage2 registerFrame = new Registerpage2();
                panel1.removeAll();
                panel1.add(registerFrame);
                panel1.repaint();
            }
        });
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DataKlasse.setUserName(nameInput.getText());
                DataKlasse.setPassword(passwordInput.getPassword());
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
    }
}
