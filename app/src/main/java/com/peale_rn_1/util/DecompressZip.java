package com.peale_rn_1.util;

import android.util.Log;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/**
 * Created by Administrator on 2016/8/25.
 */
public class DecompressZip {
    private static final int BUFFER_SIZE = 8192;

    private String _zipFile;
    private String  _location;
    private byte[] _buffer;

    /**
     * Constructor
     * @param zipFile          fully-qualified path to .zip file
     * @param location         fully-qualified path to folder where files should be writtern.
     *                         path must have a trailing slash
     */
    public DecompressZip(String zipFile, String location) {
        this._zipFile = zipFile;
        this._location = location;
    }

  public String unzip() {
        FileInputStream fin = null;
        OutputStream fout = null;
        ZipInputStream zin = null;

        File outputDir = new File(_location);
        File tmp = null;

        try {
            fin = new FileInputStream(_zipFile);
            zin = new ZipInputStream(fin);
            ZipEntry ze = null;

            while((ze = zin.getNextEntry()) != null){
                Log.d("Decompress","Unzipping" + ze.getName());
                if(ze.isDirectory()){
                    dirChecker(ze.getName());
                }else {
                    tmp = File.createTempFile("decomp",".tmp",outputDir);
                    fout = new BufferedOutputStream(new FileOutputStream(tmp));
                    DownloadFile.copyStream(zin,fout,_buffer,BUFFER_SIZE);
                    zin.closeEntry();
                    fout.close();
                    fout = null;
                    tmp.renameTo(new File(_location +ze.getName()));
                    tmp = null;
                }
            }
            zin.close();
            zin = null;
             return "success";
        }catch (IOException e){
            throw new RuntimeException(e);
        } finally {
           // tmp.delete();
            if(tmp != null){ try {
                tmp.delete();}catch (Exception ignore){;} }
            if(fout != null) { try { fout.close();} catch (Exception ignore){;} }
            if(zin != null) { try { zin.closeEntry();} catch (Exception ignore){;} }
            if(fin != null) { try { fin.close();} catch (Exception ignore){;} }

        }

    }

    private void dirChecker(String dir){
        File f = new File(_location  + dir);
        if(!f.isDirectory()){
            f.mkdirs();
        }
    }
}
