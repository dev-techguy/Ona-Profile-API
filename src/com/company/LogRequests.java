package com.company;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class LogRequests {
    // log all request here responses
    void logRequests(String options) {
        try {
            //get current time and date
            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss a");
            Date date = new Date();

            //get current working directory here
            Path currentRelativePath = Paths.get("");
            String path = currentRelativePath.toAbsolutePath().toString();

            File file = new File(path + "/logs/log");
            FileWriter fr = new FileWriter(file, true);
            fr.write("\n\n" + dateFormat.format(date) + "\n" + "data = [ " + "\n\t\t\t" + options + "\n ]\n\n_______________________________________________________________________________________________________");
            fr.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
