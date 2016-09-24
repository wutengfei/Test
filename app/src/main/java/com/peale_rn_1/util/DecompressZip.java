package com.peale_rn_1.util;

import java.io.*;
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
        long startTime=System.currentTimeMillis();

        try {
            ZipInputStream Zin = new ZipInputStream(new FileInputStream(this._zipFile));//输入源zip路径
            BufferedInputStream Bin=new BufferedInputStream(Zin);
            File Fout=null;
            ZipEntry entry;
            try {
                while((entry = Zin.getNextEntry())!=null && !entry.isDirectory()){
                    Fout=new File(this._location,entry.getName());
                    if(!Fout.exists()){
                        (new File(Fout.getParent())).mkdirs();
                    }
                    FileOutputStream out=new FileOutputStream(Fout);
                    BufferedOutputStream Bout=new BufferedOutputStream(out);
                    int b;
                    while((b=Bin.read())!=-1){
                        Bout.write(b);
                    }
                    Bout.close();
                    out.close();
                    System.out.println(Fout+"解压成功");
                }
                Bin.close();
                Zin.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        long endTime=System.currentTimeMillis();
        System.out.println("耗费时间： "+(endTime-startTime)+" ms");
        return "success";

    }

  /*  public String unzip() {
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
            tmp.delete();
             return "success";
        }catch (IOException e){
            throw new RuntimeException(e);
        } finally {
           // tmp.delete();
            if(tmp != null){ try {
                tmp.deleteOnExit();}catch (Exception ignore){;} }
            if(fout != null) { try { fout.close();} catch (Exception ignore){;} }
            if(zin != null) { try { zin.closeEntry();} catch (Exception ignore){;} }
            if(fin != null) { try { fin.close();} catch (Exception ignore){;} }

        }

    }*/

    /*private void dirChecker(String dir){
        File f = new File(_location  + dir);
        if(!f.isDirectory()){
            f.mkdirs();
        }
    }*/
}
