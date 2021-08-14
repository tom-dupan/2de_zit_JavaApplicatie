package main;

import javax.swing.*;
import java.util.ArrayList;

public  class DataKlasse {
    private static final JFrame frame = new JFrame();
    private static int userID;
    private static int CourseId;

    private static String userName;
    private static String password;

    public static String getPassword() {
        return password;
    }

    public static void setPassword(String password) {
        DataKlasse.password = password;
    }

    private static String CourseName;
    private static int teller =1;
    private static ArrayList lijst;

    public static ArrayList getLijst() {
        return lijst;
    }

    public static void setLijst(ArrayList lijst) {
        DataKlasse.lijst = lijst;
    }

    public static int getTeller() {
        return teller;
    }

    public static void setTeller(int teller) {
        DataKlasse.teller = teller;
    }

    public static JFrame getFrame() {
        return frame;
    }

    public static void setFrame() {
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
    }

    public static int getUserID() {
        return userID;
    }

    public static void setUserID(int userID) {
        DataKlasse.userID = userID;
    }

    public static String getUserName() {
        return userName;
    }

    public static void setUserName(String userName) {
        DataKlasse.userName = userName;
    }

    public static int getCourseId() {
        return CourseId;
    }

    public static void setCourseId(int CourseId) {
        DataKlasse.CourseId = CourseId;
    }

    public static void setCourseName(String toString) {
    }

    public static String getCourseName() {
        return CourseName;
    }
}