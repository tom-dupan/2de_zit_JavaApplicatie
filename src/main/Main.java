package main;


import application.MainScreen;

import application.authentication.Inlogpage2;
import application.authentication.Registerpage2;
import application.extraFeatures.Livechat2.Server;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;

public class Main {


    public static void main(String[] args) throws SQLException, FileNotFoundException {
    Inlogpage2 login = new Inlogpage2();




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

