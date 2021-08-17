package application.extraFeatures.Livechat2;

import database.BaseDAO;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class RateSystem {
    private JPanel panel1;
    private JLabel ratingSystemLabel;
    private JRadioButton a2RadioButton;
    private JRadioButton a5RadioButton;
    private JRadioButton a3RadioButton;
    private JRadioButton a1RadioButton;
    private JRadioButton a4RadioButton;
    private JTextField textField1;
    private JButton rateButton;
    int rating = 0;
    Connection connection = BaseDAO.getConnection();

    private void createUIComponents() {

    }


    public RateSystem() throws SQLException {

        //insert rating into table
        PreparedStatement stmt = connection.prepareStatement("INSERT INTO User (rating) VALUES (?)");
        stmt.setInt(1, rating);
        stmt.executeUpdate();
        stmt.close();

        a1RadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                rating = 1;
            }
        });
        a2RadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                rating = 2;
            }
        });
        a3RadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                rating = 3;
            }
        });
        a4RadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                rating = 4;
            }
        });
        a5RadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                rating = 5;
            }
        });

        rateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (textField1.getText().isEmpty()) {
                    JDialog dialog = new JDialog();
                    JLabel label = new JLabel("Please enter a name first");
                    dialog.add(label);
                    dialog.setVisible(true);
                }
                String naam = textField1.getText();

                //insert rating into table
                PreparedStatement stmt = null;
                try {
                    stmt = connection.prepareStatement("INSERT INTO User (TutorNaam) VALUES (?)");
                    stmt.setString(1, naam);
                    stmt.executeUpdate();
                    stmt.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        });
    }
}