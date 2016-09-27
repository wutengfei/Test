package com.peale_rn_1.control;

import android.os.AsyncTask;
import android.app.ProgressDialog;
import android.provider.Settings;
import android.widget.Toast;

import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import java.io.File;
import java.io.IOException;
import java.util.List;

import com.facebook.react.uimanager.IllegalViewOperationException;
import com.peale_rn_1.dao.ResourceDAO;
import com.peale_rn_1.dao.WordDAO;
import com.peale_rn_1.model.Tb_file;
import com.peale_rn_1.model.Tb_word;
import com.peale_rn_1.util.*;

/**
 * Created by dell on 2016/7/19.
 */
public class RNFileManager extends ReactContextBaseJavaModule {
    protected ProgressDialog mProgressDialog;
    File zipDir;
    File outputDir= ExternalStorage.getSDCacheDir(getReactApplicationContext(), "learn-resource");


    public RNFileManager(ReactApplicationContext reactContext) {
        super(reactContext);
    }

    @Override
    public String getName() {
        return "ZipFileManage";
    }

   @ReactMethod
  //  public void startDownload(String url,String titleID,String dayClick,String recommendDayId,String fileName,Callback successCallback, Callback errorCallback) {
   public void startDownload(String url,String titleID,String dayClick,String recommendDayId,String fileName,Callback successCallback, Callback errorCallback) {
        try {
            Toast.makeText(getReactApplicationContext(), "开始下载资源，请耐心等待！", Toast.LENGTH_LONG).show();
            //这里提供下载
         //   String string = new DownloadTask().downloadAllAssets(url); //主要为了测试解析
            //这里需要周亮提供给我的文件名称
            String string = "success";
            if (string.equals("success")) {
                Toast.makeText(getReactApplicationContext(), "下载完毕，正在解析资源！", Toast.LENGTH_LONG);
                //然后进行xml文件等解析
                String Path = outputDir.getPath() + File.separator;
                String filePath = Path + fileName;
                //调用XmlFileAnalysis类，
                if (dayClick.equals(recommendDayId)) {
                    //等于，说明是主文件，主文件的方法跟从属文件不一样
                    XmlFileAnalysis xmlFileAnalysis = new XmlFileAnalysis(Path, fileName);
                    List<Tb_word> words = xmlFileAnalysis.readXML();
                    WordDAO wordDAO = new WordDAO(getReactApplicationContext());
                    wordDAO.insert(words);
                    Toast.makeText(getReactApplicationContext(),"导入资源进数据库",Toast.LENGTH_LONG).show();
                }
                if (!dayClick.equals(recommendDayId)) {
                    //不等于 ，说明是从属文件，从属文件与主文件的方法不一样
                    //从属文件解析方法待写
                    Toast.makeText(getReactApplicationContext(),"从属文件",Toast.LENGTH_LONG).show();
                }
                  //对主文件和从属文件解析方法不一样，但都需要对下载的文件相关信息进行保存
                 Tb_file file = new Tb_file(titleID, dayClick, recommendDayId, fileName, filePath);
                 ResourceDAO resourceDAO = new ResourceDAO(getReactApplicationContext());
                 try {
                      resourceDAO.insert(file);
                      Toast.makeText(getReactApplicationContext(),"下载解析"+ fileName+"文件完成！",Toast.LENGTH_LONG).show();
                     successCallback.invoke(fileName);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
            }
        }catch (IllegalViewOperationException e) {
            errorCallback.invoke(e.getMessage());
        }
    }

   /*@ReactMethod
   public void testUnzip(){
       new DownloadTask().downloadAllAssets();
   }*/

    /*
    *   Background task to download and unpack .zip file in background.
    * */
    private class DownloadTask extends AsyncTask<String, Void, Exception> {

        /*
      *  Download .zip file specified by url ,then uizp it to a folder in external storage.
      * */
        // private String downloadAllAssets(String url) {
        private String downloadAllAssets(String url){
            System.out.print("测试解析方法"+ url);
            zipDir = ExternalStorage.getSDCacheDir(getReactApplicationContext(), "temp");
            outputDir= ExternalStorage.getSDCacheDir(getReactApplicationContext(), "learn-resource");
            File zipFile = new File(zipDir.getPath() + "temp.zip");
            try {
              //   DownloadFile.download(url, zipFile, zipDir);
                 unzipFile(zipFile, outputDir);//这方法没问题，问题就是对方传给我的包存在问题
                return "success";
            } finally {
                //删除文件
            }
        }
    /*
    * 解压缩文件
    * */
        protected void unzipFile(File zipFile, File destination) {

            //测试方法1
            ZIP zip = new ZIP();
            try {
                zip.UnZipFolder(zipFile.getPath(),destination.getPath());
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // showProgress();
        }

        @Override
        protected Exception doInBackground(String... params) {
            String url = (String) params[0];
            try {
             //   downloadAllAssets(url);
            } catch (Exception e) {
                return e;
            }
            return null;
        }

        @Override
        protected void onPostExecute(Exception result) {
            //   dismissProgress();
            if (result == null) {
                return;
            }
            //something went wrong ,post a message to user
            Toast.makeText(getReactApplicationContext(), result.getLocalizedMessage(), Toast.LENGTH_LONG);
        }
    }
    }

