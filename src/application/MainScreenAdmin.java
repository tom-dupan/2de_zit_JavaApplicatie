package application;

import application.authentication.Inlogpage2;
import application.extraFeatures.Livechat2.Calendar;
import application.extraFeatures.Livechat2.ClientGui;
import application.extraFeatures.Livechat2.RateSystem;
import database.BaseDAO;
import database.CourseDAO;
import database.UserDao;
import main.DataKlasse;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class MainScreenAdmin<teller> extends JFrame {

    private BaseDAO baseDao = new BaseDAO() {
    };
    CourseDAO courseDAO = new CourseDAO();
    private Connection conn;

    private final JFrame frame = new JFrame();
    private final Container container1 = getContentPane();
    private final JLabel digX = new JLabel("Dig-X");
    private final JLabel mct = new JLabel("MCT");
    private final JButton chat = new JButton("Open Livechat");
    private final JButton calendar = new JButton("My Calendar");
    private final JButton exit = new JButton("Go back");
    private final JButton rating = new JButton("Rate");
    private final JButton inbox = new JButton("Inbox");
    private final JButton allUsers = new JButton("Users");


    private JList userlist = new JList();
    ArrayList lijstje = new ArrayList();


    byte[] byteArray;

    public MainScreenAdmin() throws SQLException, FileNotFoundException {

        CourseDAO.GetCourse();
        setLayoutM();
        setFrameSizeAndLocation();
        addToframe();

        DataKlasse.getFrame().setContentPane(container1);
        DataKlasse.getFrame().setVisible(true);


     allUsers.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
             UserDao userDao = null;
             try {
                 userDao = new UserDao();
                ArrayList arrayList = userDao.getAllUsers();
                JDialog dialog = new JDialog();
                frame.add(dialog);

                 DefaultListModel listModel = new DefaultListModel();
                 for (int i = 0; i < arrayList.size(); i++)
                 {
                     listModel.addElement(arrayList.get(i));
                 }
                 userlist.setModel(listModel);
                 dialog.add(userlist);

             } catch (SQLException | FileNotFoundException throwables) {
                 throwables.printStackTrace();
             }
         }
     });
        rating.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    RateSystem rate = new RateSystem();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }


            }
        });
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Inlogpage2 inlogpage = new Inlogpage2();
                application.MainScreenAdmin.this.dispose();
            }
        });
        chat.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ClientGui gui = new ClientGui();
            }
        });
        calendar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(new Runnable() {
                    public void run() {
                        Calendar window = null;
                        try {
                            window = new Calendar();
                            window.setVisible(true);
                        } catch (Exception exp) {
                        }
                    }
                });
            }
        });
        inbox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

    }

    public void setLayoutM() {
        container1.setLayout(null);
    }

    public void setFrameSizeAndLocation() {
        exit.setBounds(25, 20, 100, 20);

        digX.setBounds(145, 50, 90, 50);
        mct.setBounds(500, 50, 90, 50);
        chat.setBounds(675, 150, 90, 30);
        calendar.setBounds(675, 200, 90, 30);

        rating.setBounds(675, 300, 90, 30);
        inbox.setBounds(675, 400, 90, 30);

        allUsers.setBounds(25, 100, 300, 500);

    }


    public void addToframe() {
        container1.add(digX);
        container1.add(mct);
        container1.add(exit);
        container1.add(allUsers);
        container1.add(chat);
        container1.add(calendar);
        container1.add(rating);
        container1.add(inbox);
    }


}



