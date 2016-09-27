package com.peale_rn_1.util;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;

/**
 * Created by dell on 2016/7/19.
 */
public class DownloadFile {
    private static final int BUFFER_SIZE = 8192;

    /**
     *   Download a file from a url somewhere. the download is atomic;
     *   that is, it downloads to a temproprary file,then renames it to the requested file name only if the download successfully completes.
     *
     *   @param   url  source URL
     *   @param   output   Path to output file
     *   @param   tmpDir    palce to put file download in progress
     */

    public static void download (String url, File output, File tmpDir ){
        InputStream is = null;
        OutputStream os = null;
        File tmp  = null;
        try{
            tmp = File.createTempFile("download",".tmp",tmpDir);
            is = new URL(url).openStream();
            os = new BufferedOutputStream(new FileOutputStream(tmp));
            copyStream(is,os);
            tmp.renameTo(output);
          System.out.println( output.getName());
            tmp = null;
        }catch (IOException e){
            throw  new RuntimeException(e);
        } finally {
            if(tmp !=null){
                try{ tmp.delete();tmp = null;}catch (Exception ignore){;}
            }
            if(is !=null){
                try { is.close();is = null;}catch (Exception ignore){;}
            }
            if(os !=null){
                try { os.close();os = null;}catch (Exception ignore){;}
            }
        }

    }

    /**
     * Copy from one stream to another. Throws IOxception in the event of error
     * (for example. SD card is full)
     * @param  is        InputStream
     * @param  os        OutputStream
     */

    public  static void copyStream(InputStream is , OutputStream os) throws IOException{
        byte[] buffer = new byte[BUFFER_SIZE];
        copyStream(is,os,buffer,BUFFER_SIZE);
    }

    /**
     * @param  is    InputStream.
     * @param  os    OutputStream.
     * @param buffer     temporary buffer to use for copy.
     * @param  bufferSize   size of temporary buffer, in bytes
     */

    public static void copyStream(InputStream is ,OutputStream os, byte[] buffer,int bufferSize) throws IOException{
        try{
            for (;;){
                int count = is.read(buffer,0,bufferSize);
                if(count == -1){break;}
                os.write(buffer,0,count);
            }
        }catch (IOException e){
            throw e;
        }
    }
}
