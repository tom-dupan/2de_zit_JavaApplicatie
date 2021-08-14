package main;


import application.MainScreen2;
import application.authentication.Inlogpage;
import application.extraFeatures.Livechat2.Server;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.sql.SQLException;

public class Main {


    public static void main(String[] args) throws SQLException {
        MainScreen2 inlogpage = new MainScreen2();

        try {
            new Server(12345).run();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            MainScreen2 mainScreen2 =  new MainScreen2();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    }

