package com.company;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.SQLException;

public class Main {
    // instance of API/Main class
    API api = new API();

    // check for database connection here
    Boolean checkDBConnection() {
        // global function variable
        boolean status = false;

        // run validation in a try catch
        try {
            // database connectors/drivers
            Connection connection = DB.getConnection();
            if (connection != null) {
                status = true;
            }
        } catch (SQLException e) {
            String[] option = {"Try Again", "Exit"};
            Toolkit.getDefaultToolkit().beep();
            int optionDialog = JOptionPane.showOptionDialog(null, "Database Connection", "Database Connection", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, option, option[1]);
            if (optionDialog == 0) {
                checkDBConnection();
            } else {
                System.exit(0);
            }
        }

        return status;
    }

    // run simple ui
    private void UI() {
        if (checkDBConnection()) {
            String[] option = {"Create Profile", "View Profile", "Exit"};
            Toolkit.getDefaultToolkit().beep();
            int optionDialog = JOptionPane.showOptionDialog(null, "This ona api profile demos.", "Demo Ona API", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, option, option[1]);
            if (optionDialog == 0) {
                createUI();
            } else if (optionDialog == 1) {
                JOptionPane.showMessageDialog(null, "Coming Soon", null, JOptionPane.INFORMATION_MESSAGE);
                UI();
            } else {
                System.exit(0);
            }
        }
    }

    // create the profile
    private void createUI() {
        if (checkDBConnection()) {
            // ui components
            JLabel username = new JLabel("Username");
            JTextField textUsername = new JTextField();
            JLabel first_name = new JLabel("First Name");
            JTextField textFName = new JTextField();
            JLabel last_name = new JLabel("Last Name");
            JTextField textLName = new JTextField();
            JLabel email = new JLabel("E-Mail Address");
            JTextField textEmail = new JTextField();
            JLabel city = new JLabel("City");
            JTextField textCity = new JTextField();
            JLabel code = new JLabel("Country Code i.e KE");
            JTextField textCode = new JTextField();

            // store them in object array
            java.lang.Object[] objects = {username, textUsername, first_name, textFName, last_name, textLName, email, textEmail, city, textCity, code, textCode};

            int response = JOptionPane.showConfirmDialog(null, objects, "Administrator Pass Key is Required", JOptionPane.OK_CANCEL_OPTION);

            if (response == JOptionPane.OK_OPTION) {
                // validate ENTRIES
                if (textUsername.getText().length() > 0 && textFName.getText().length() > 0 && textLName.getText().length() > 0 && textEmail.getText().length() > 0 && textCity.getText().length() > 0 && textCode.getText().length() > 0) {
                    // create an array string
                    String[] data = {
                            textUsername.getText(),
                            textFName.getText(),
                            textLName.getText(),
                            textEmail.getText(),
                            textCity.getText(),
                            textCode.getText()
                    };

                    // pass to api
                    boolean status = api.createProfile(data);

                    if (status) {
                        JOptionPane.showMessageDialog(null, "Profile created successfully", null, JOptionPane.INFORMATION_MESSAGE);
                        UI();
                    } else {
                        JOptionPane.showMessageDialog(null, "Failed to create profile.", null, JOptionPane.ERROR_MESSAGE);
                        UI();
                    }

                } else {
                    JOptionPane.showMessageDialog(null, "Please fill in all the fields.", null, JOptionPane.WARNING_MESSAGE);
                }
            } else {
                UI();
            }
        }
    }


    public static void main(String[] args) {
        // create instance of class
        Main main = new Main();
        main.UI();
    }
}
