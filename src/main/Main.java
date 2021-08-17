package main;


import application.MainScreen;
import application.authentication.Inlogpage;
import application.extraFeatures.Livechat2.Server;

import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;

public class Main {


    public static void main(String[] args) throws SQLException, FileNotFoundException {
        MainScreen inlogpage = new MainScreen();

        try {
            new Server(12345).run();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            MainScreen mainScreen =  new MainScreen();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    }

