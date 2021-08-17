package application.extraFeatures.Livechat2;

import database.BaseDAO;
import database.RateDAO;
import main.DataKlasse;

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
    private JTextPane descriptionTextPane;
    int rating = 0;
    Connection connection = BaseDAO.getConnection();

    private void createUIComponents() {

    }


    public RateSystem() throws SQLException {


        a1RadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DataKlasse.setRating(1);
            }
        });
        a2RadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DataKlasse.setRating(2);
            }
        });
        a3RadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DataKlasse.setRating(3);
            }
        });
        a4RadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DataKlasse.setRating(4);
            }
        });
        a5RadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DataKlasse.setRating(5);
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
                DataKlasse.setTutorName(textField1.getText());
                DataKlasse.setDescription(descriptionTextPane.getText());
                //insert rating into table
                RateDAO rateDAO = new RateDAO();
                rateDAO.setrating();
            }
        });
    }
}