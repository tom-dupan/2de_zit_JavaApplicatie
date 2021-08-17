package application;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import static application.GetFiles.getClassesInPackage;

public class Inbox {


    DefaultListModel listModel;
    JList list1 = new JList(listModel);
    String content;
    private JButton clearAllButton;
    private JButton clearFirstButton;

    public Inbox() {
        Class[] classes = getClassesInPackage("Files");
        for (Class c : classes) {
            System.out.println("Found: " + c.getCanonicalName());
            try {
            File myObj = new File("Files/"+c.getCanonicalName());
                Scanner myReader = new Scanner(myObj);

                while (myReader.hasNextLine()) {
                    String data = myReader.nextLine();
                    System.out.println(data);
                    content = content+data;
                    System.out.println(content);
                }
                listModel.addElement(content);
                myReader.close();
            } catch (FileNotFoundException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }
        }
        clearAllButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                listModel.clear();
            }
        });
        clearFirstButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                listModel.remove(0);
            }
        });
    }

}
