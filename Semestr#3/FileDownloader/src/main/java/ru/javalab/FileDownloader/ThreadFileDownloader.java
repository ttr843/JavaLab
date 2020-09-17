package ru.javalab.FileDownloader;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class ThreadFileDownloader extends Thread {
    private String url;

    public ThreadFileDownloader(String url) {
        this.url = url;
        run();
    }

    public String getUrl() {
        return url;
    }

    @Override
    public void run() {
        String[] splittedUrl = getUrl().trim().split("/");
        String fileName = null;
        for (String x: splittedUrl
             ) {
            if(x.contains(".")){
                fileName = x;
            }
        }
        URL url = null;
        try {
            url = new URL(getUrl());
            InputStream is = new BufferedInputStream(url.openStream());
            FileOutputStream os = new FileOutputStream((Integer.toString((int) (  Math.random() * 10000))) + fileName);
            int b;
            while ((b = is.read()) != -1) {
                os.write(b);
            }
            os.close();
            is.close();
        } catch (IOException e){
            e.printStackTrace();
            System.out.println("Error");
        }
    }
}
