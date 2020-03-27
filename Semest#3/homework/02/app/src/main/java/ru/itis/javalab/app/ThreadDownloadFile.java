//Ruslan Pashin 11-801
package ru.itis.javalab.app;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;

public class ThreadDownloadFile extends Thread {
    private String url;

    public ThreadDownloadFile(String url) {
        this.url = url;
        run();
    }

    public String getUrl() {
        return url;
    }

    @Override
    public void run() {
        URL url = null;
        try {
            url = new URL(getUrl());
            InputStream is = new BufferedInputStream(url.openStream());
            FileOutputStream os = new FileOutputStream((Integer.toString((int) (100 + Math.random() * 1000))) + ".jpg");
            int b;
            while ((b = is.read()) != -1) {
                os.write(b);
            }
            os.close();
            is.close();
        } catch (MalformedURLException e) {
            System.out.println("MalformedURLException");
        } catch (FileNotFoundException e) {
            System.out.println("FileNotFoundException");
        } catch (IOException e) {
            System.out.println("IOException");
        }
    }
}