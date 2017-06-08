package com.sss.carolina.minecraftsmaps;

import android.content.Context;
import android.content.res.AssetManager;
import android.os.Environment;
import android.util.Log;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/**
 * Created by carolina on 08.06.17.
 */

public class Inflens {

     private String loc;

    final String dir = "/Minecraft/";




    Inflens(String location) {
        loc = location;

        dirChecker(dir);
    }

    public void loadzip(InputStream inputStream) throws IOException
    {
        ZipInputStream zipIs = new ZipInputStream(inputStream);
        ZipEntry ze = null;

        while ((ze = zipIs.getNextEntry()) != null) {
            if(ze.isDirectory()) {
                dirChecker(dir+ze.getName());
            }else {
                FileOutputStream fout = new FileOutputStream(loc + "/" + ze.getName());

                byte[] buffer = new byte[1024];
                int length = 0;

                while ((length = zipIs.read(buffer)) > 0) {
                    fout.write(buffer, 0, length);
                }
                zipIs.closeEntry();
                fout.close();
            }
        }
        zipIs.close();
    }




    private void dirChecker(String dir) {
        File Directory = new File("/mnt/internal/" + dir);
        Log.i("Log", "/mnt/internal" + dir + " - dir check");
        boolean isDirectoryCreated = Directory.exists();
        if (!isDirectoryCreated) {
            isDirectoryCreated = Directory.mkdirs();
            Log.e("Ko", "Directory created;");

        }
        if (isDirectoryCreated) {
            Log.e("Ko", "Directory was created;");
        }
    }


}
