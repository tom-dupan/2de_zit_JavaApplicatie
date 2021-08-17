package application;

import application.authentication.Inlogpage;
import application.extraFeatures.Livechat2.Calendar;
import application.extraFeatures.Livechat2.ClientGui;
import application.extraFeatures.Livechat2.RateSystem;
import com.sun.jdi.IntegerValue;
import database.BaseDAO;
import database.CourseDAO;
import database.DatabaseSingleton;
import main.DataKlasse;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.xml.crypto.Data;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
    private final JButton submit = new JButton("Ask Tutor");
    private final JButton chat = new JButton("Open Livechat");
    private final JButton calendar = new JButton("My Calendar");
    private final JButton exit = new JButton("Go back");
    private final JButton rating = new JButton("Rate");
    private final JButton inbox = new JButton("Inbox");

    private JList course2 = new JList();
    private JList course = new JList();
    ArrayList lijstje = new ArrayList();

    byte[] byteArray;

    public MainScreenAdmin() throws SQLException, FileNotFoundException {

        CourseDAO.GetCourse();
        setLayoutM();
        setFrameSizeAndLocation();
        addToframe();

        DataKlasse.getFrame().setContentPane(container1);
        DataKlasse.getFrame().setVisible(true);

        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (course.getSelectedValue() != null && course2.getSelectedValue() == null) {
                    try {
                        DataKlasse.setCourseName(course.getSelectedValue().toString());
                        lijstje.add(DataKlasse.getLijst().add(DataKlasse.getCourseName()));
                        DataKlasse.setLijst(lijstje);
                        CourseDAO.getId();

                        DataKlasse.setTeller(DataKlasse.getTeller() + 1);
                        File alleVakken = new File("src/application/Files/AlleGevraagdeVakken.txt" + DataKlasse.getTeller());
                        FileOutputStream fileOutputStream = new FileOutputStream(alleVakken);
                        ObjectOutputStream oos = new ObjectOutputStream(fileOutputStream);
                        String Resultaat = DataKlasse.getUserName() + '\n' + DataKlasse.getLijst();
                        oos.writeObject(Resultaat);
                        oos.close();
                    } catch (FileNotFoundException fileNotFoundException) {
                        fileNotFoundException.printStackTrace();
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                } else if (course.getSelectedValue() == null && course2.getSelectedValue() != null) {
                    try {
                        DataKlasse.setCourseName(course.getSelectedValue().toString());
                        lijstje.add(DataKlasse.getLijst().add(DataKlasse.getCourseName()));
                        DataKlasse.setLijst(lijstje);
                        CourseDAO.getId();

                        DataKlasse.setTeller(DataKlasse.getTeller() + 1);
                        File alleVakken = new File("src/application/Files/AlleGevraagdeVakken.txt" + DataKlasse.getTeller());
                        FileOutputStream fileOutputStream = new FileOutputStream(alleVakken);
                        ObjectOutputStream oos = new ObjectOutputStream(fileOutputStream);
                        String Resultaat = DataKlasse.getUserName() + '\n' + DataKlasse.getLijst();
                        oos.writeObject(Resultaat);
                        oos.close();
                    } catch (FileNotFoundException fileNotFoundException) {
                        fileNotFoundException.printStackTrace();
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }


                } else {
                    JOptionPane.showMessageDialog(frame, "Please choose a course first.");


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
                try {
                    Inlogpage inlogpage = new Inlogpage();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                application.MainScreenAdmin.this.dispose();
            }
        });
        course.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                course2.clearSelection();
            }
        });
        course2.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                course.clearSelection();
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

    public JList getCourse() {
        return course;
    }

    public JButton getSubmit() {
        return submit;
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
        submit.setBounds(675, 100, 90, 30);
        rating.setBounds(675, 300, 90, 30);
        inbox.setBounds(675, 400, 90, 30);

        course.setBounds(25, 100, 300, 500);
        course2.setBounds(350, 100, 300, 500);
    }


    public void addToframe() {
        container1.add(digX);
        container1.add(mct);
        container1.add(submit);
        container1.add(exit);
        container1.add(course);
        container1.add(course2);
        container1.add(chat);
        container1.add(calendar);
        container1.add(rating);
        container1.add(inbox);
    }


}



