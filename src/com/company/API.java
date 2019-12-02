package com.company;


import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class API {
    // create a new profile here
    Boolean createProfile(String[] data) {
        // global variable
        boolean status = false;

        // send request in try catch
        try {
            URL url = new URL(EndPoints.createURL);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
            httpURLConnection.setRequestMethod(EndPoints.httpPost);
            httpURLConnection.setConnectTimeout(15000);
            httpURLConnection.setUseCaches(false);
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);
            httpURLConnection.connect();

            //send request to server
            OutputStream outputStream = httpURLConnection.getOutputStream();
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
            String post_data =
                    URLEncoder.encode("username", "UTF-8") + "=" +
                            URLEncoder.encode(data[0], "UTF-8") + "&" +
                            URLEncoder.encode("first_name", "UTF-8") + "=" +
                            URLEncoder.encode(data[1], "UTF-8") + "&" +
                            URLEncoder.encode("last_name", "UTF-8") + "=" +
                            URLEncoder.encode(data[2], "UTF-8") + "&" +
                            URLEncoder.encode("email", "UTF-8") + "=" +
                            URLEncoder.encode(data[3], "UTF-8") + "&" +
                            URLEncoder.encode("city", "UTF-8") + "=" +
                            URLEncoder.encode(data[4], "UTF-8") + "&" +
                            URLEncoder.encode("country", "UTF-8") + "=" +
                            URLEncoder.encode(data[5], "UTF-8");
            bufferedWriter.write(post_data);
            bufferedWriter.flush();
            bufferedWriter.close();
            outputStream.close();


            //get responseCode here
            int responseCode = httpURLConnection.getResponseCode();

            //check the responseCode
            if (responseCode == HttpURLConnection.HTTP_CREATED) {
                // success
                BufferedReader in = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
                String inputLine;
                StringBuilder response = new StringBuilder();

                //loop the response contents here
                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

                // print result
                new LogRequests().logRequests("Response Code " + responseCode + "\n" + response);

                // store in database
                new Store().store(String.valueOf(response));

                // change status
                status = true;
            } else {
                new LogRequests().logRequests("Response Code " + responseCode + "\n" + "\t\t\tRequest Failed.");
            }
            httpURLConnection.disconnect();
        } catch (IOException e) {
            new LogRequests().logRequests("Exception on making request " + e.getMessage());
        }

        return status;
    }
}
