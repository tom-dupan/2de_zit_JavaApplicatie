package application.authentication;

import database.UserDao;
import main.DataKlasse;
import application.authentication.Registerpage2;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.sql.SQLException;


public class Inlogpage2 extends JFrame{
    private Container container1 = new Container();
    private JPanel panel1 = new JPanel();
    private JTextField nameInput;
    private JButton registerButton;
    private JButton loginButton;
    private JPasswordField passwordInput;
    private JButton showpasswordButton;

    public Inlogpage2() throws HeadlessException {
        this.add(panel1);
        panel1.add(container1);
        container1.add(nameInput);
        container1.add(registerButton);
        container1.add(loginButton);
        container1.add(passwordInput);
        container1.add(showpasswordButton);
        container1.setVisible(true);
        panel1.setVisible(true);
        this.setVisible(true);

    }

    public void Inlogpage2() {
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Registerpage2 registerFrame = new Registerpage2();
                container1.removeAll();
                container1.repaint();
                container1.add(registerFrame);
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
