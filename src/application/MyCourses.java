package application;

import database.BaseDAO;

import database.CourseDAO;
import main.DataKlasse;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.ArrayList;

public class MyCourses extends JFrame {

        CourseDAO dao = new CourseDAO();
        //JDBC part
        private Connection conn;
        private PreparedStatement stmt;
        private BaseDAO baseDao = new BaseDAO() {};

        private final JFrame frame = new JFrame();
        private final Container container1 = getContentPane();

        private final JLabel cardsLabel = new JLabel("Cards");
        private final JButton addCoursebtn = new JButton("addCard");

        private final JButton exit = new JButton("Exit");
        private JButton commentbtn = new JButton("Comment");
        private JButton editCommentbtn = new JButton("Edit comment");
        private JButton reviewbtn = new JButton("Review deck");

        //Card part
        private JList courselist = new JList();

        //Comment part
        private JPanel commentPanel = new JPanel();
        private JScrollPane scroll = new JScrollPane(commentPanel);
        private ArrayList<String> commentArray = new ArrayList<>();

        //Rating part
        //https://stackoverflow.com/questions/6435668/create-a-numeric-text-box-in-java-swing-with-increment-and-decrement-buttons
        private SpinnerNumberModel numberModel = new SpinnerNumberModel(0, 0, 5, 1);
        private JSpinner spinner = new JSpinner(numberModel);
        private JButton ratebtn = new JButton("Rate");

        private int deckOwnerId = 0;

        //TODO gezien kaarten toch niet live worden geupdated kan add card voor niet deck owners er nog wel bij.
        public MyCourses() {

         //   dao.AddCourse();
            setLayoutM();
            setFrameSizeAndLocation();
            addToframe();

            DataKlasse.getFrame().setContentPane(container1);
            DataKlasse.getFrame().setVisible(true);

            //Comment section part
            //https://stackoverflow.com/questions/13510641/add-controls-vertically-instead-of-horizontally-using-flow-layout/18073909#18073909
            commentPanel.setAutoscrolls(true);
            commentPanel.setLayout(new BoxLayout(commentPanel, BoxLayout.Y_AXIS));
            scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

            addCommentsToFrame();

            if (DataKlasse.getUserID() == deckOwnerId) {
                setAddCardButton();

            }

            //Actionlistener for buttons part
            addCoursebtn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    MyCourses.this.dispose();
                    AddCourse addCourse = new AddCourse();
                }
            });


            exit.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    MyCourses.this.dispose();
                    MyCourses.this.addToframe();
                }
            });


            courselist.addListSelectionListener(new ListSelectionListener() {
                @Override
                public void valueChanged(ListSelectionEvent e) {
                    addCourseToList();
                }
            });


        }

        public JSpinner getSpinner(){ return spinner; }
        public JButton getRatebtn(){return ratebtn;}



        private void addCourseToList(){
            try {
                conn = baseDao.getConnection();
                String selectStatement = "SELECT CourseId FROM Course Where UserId = ? AND CourseId = ? AND TermQuestion = ?";
                PreparedStatement stmt=conn.prepareStatement(selectStatement);
                stmt.setInt(1, DataKlasse.getUserID());
                stmt.setInt(2, DataKlasse.getCourseId());
                stmt.setString(3, courselist.getSelectedValue().toString());
                ResultSet rs = stmt.executeQuery();
                while (rs.next()) {
                    DataKlasse.setCourseId(Integer.valueOf(rs.getString(1)));
                }
                rs.close();
                stmt.close();

            } catch (SQLException sqlException) {
                sqlException.printStackTrace();
            }
        }

      /*  public void setCardsToArray() {
            String cardid;
            try {
                ResultSet rs = baseDao.getCard(0, DataKlasse.getDeckid(), DataKlasse.getUserID());
                while (rs.next()) {
                    cardid = rs.getString("CardId");
                    DataKlasse.getCardIdArray().add(Integer.valueOf(cardid));
                }
                rs.close();
                stmt.close();
                baseDao.closeStatement();
                baseDao.closeConnection();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }*/



        private ArrayList<String> userName = new ArrayList();


        public void addCommentsToFrame() {
            if (userName.size() > 0) {
                for (int index = 0; index < commentArray.size(); index++) {
                    JLabel commentLabel = new JLabel("<html> <br/> Comment by: " + userName.get(index) + "<br/>" + commentArray.get(index) + "<html>");
                    commentLabel.setBackground(Color.BLUE);
                    commentPanel.add(commentLabel);
                }
            } else {
                JLabel commentLabel = new JLabel("<html> This commentsection is pretty empty uh? <html>");
                commentLabel.setBackground(Color.BLUE);
                commentPanel.add(commentLabel);
            }
        }

        public JButton getAddCardbtn(){return addCoursebtn;}


        public void setLayoutM() {
            container1.setLayout(null);
        }

        public void setFrameSizeAndLocation() {
            exit.setBounds(25, 20, 100, 20);
            cardsLabel.setBounds(100, 50, 50, 50);
            courselist.setBounds(25, 100, 300, 700);
            addCoursebtn.setBounds(350, 100, 90, 30);
            commentbtn.setBounds(350, 180, 90, 30);
            editCommentbtn.setBounds(1000, 50, 180, 30);
            ratebtn.setBounds(730, 100, 90, 30);
            spinner.setBounds(730, 170, 70, 50);
            reviewbtn.setBounds(490, 100, 180, 50);
            scroll.setBounds(850, 100, 600, 700);

        }

        public void addToframe() {
            container1.add(cardsLabel);
            container1.add(exit);
            container1.add(courselist);
            container1.add(scroll);
            container1.add(reviewbtn);
            container1.add(spinner);
            container1.add(ratebtn);
        }

        private void setAddCardButton() {
            container1.add(addCoursebtn);
        }

        private void setEdit() {
            container1.add(editCommentbtn);
        }

        private void setComment() {
            container1.add(commentbtn);
        }
    }

