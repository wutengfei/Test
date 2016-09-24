package com.peale_rn_1.control;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.widget.Toast;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.uimanager.IllegalViewOperationException;
import com.peale_rn_1.dao.XmlDAO;
import com.peale_rn_1.model.Tb_word;
import com.peale_rn_1.util.DecompressZip;
import com.peale_rn_1.util.DownloadFile;
import com.peale_rn_1.util.ExternalStorage;
import com.peale_rn_1.util.XmlFileAnalysis;

import java.io.File;
import java.util.List;

/**
 * Created by dell on 2016/7/19.
 */
public class RNFileManager extends ReactContextBaseJavaModule {
    protected ProgressDialog mProgressDialog;
    File zipDir;
    File outputDir;


    public RNFileManager(ReactApplicationContext reactContext) {
        super(reactContext);
    }

    @Override
    public String getName() {
        return "ZipFileManage";
    }

   @ReactMethod
    public void startDownload(String url) {
        try {
            Toast.makeText(getReactApplicationContext(), url, Toast.LENGTH_LONG);
            Toast.makeText(getReactApplicationContext(), "开始下载资源，请耐心等待！", Toast.LENGTH_LONG);
            String string = new DownloadTask().downloadAllAssets(url);
            if (string.equals("success")) {
                Toast.makeText(getReactApplicationContext(),"下载完毕，正在解析资源！" , Toast.LENGTH_LONG);
                //然后进行xml文件等解析
                String Path = outputDir.getPath() + File.separator;
                File file = new File(Path);
                File[] tempList = file.listFiles();
           //调用XmlFileAnalysis类，
                XmlFileAnalysis xmlFileAnalysis = new XmlFileAnalysis(Path, tempList[0].getName());
                List<Tb_word> words = xmlFileAnalysis.readXML();
                XmlDAO xmlDAO = new XmlDAO(getReactApplicationContext());
                if (xmlDAO.insert(words)) {
                  //  successCallback.invoke("数据解析成功!");
                    System.out.println("数据解析成功");
                } else {
                  //  errorCallback.invoke("数据解析失败");
                    System.out.println("数据解析失败");
                }
            }
        }catch (IllegalViewOperationException e) {
           // errorCallback.invoke(e.getMessage());
        }
    }

    /*
    *   Background task to download and unpack .zip file in background.
    * */
    private class DownloadTask extends AsyncTask<String, Void, Exception> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // showProgress();
        }

        @Override
        protected Exception doInBackground(String... params) {
            String url = (String) params[0];
            try {
                downloadAllAssets(url);
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

    /*
    * */

        protected void showProgress() {
            mProgressDialog = new ProgressDialog(getReactApplicationContext());
            mProgressDialog.setTitle("资源下载");
            mProgressDialog.setIndeterminate(true);
            mProgressDialog.setCancelable(false);
            mProgressDialog.show();
        }

        protected void dismissProgress() {
            //
            if (mProgressDialog != null && mProgressDialog.isShowing() && mProgressDialog.getWindow() != null) {
                try {
                    mProgressDialog.dismiss();
                } catch (IllegalArgumentException ignore) {
                    ;
                }
            }
            mProgressDialog = null;
        }

        /*
        *  Download .zip file specified by url ,then uizp it to a folder in external storage.
        * */
        private String downloadAllAssets(String url) {
             zipDir = ExternalStorage.getSDCacheDir(getReactApplicationContext(), "temp");
            outputDir= ExternalStorage.getSDCacheDir(getReactApplicationContext(), "learn-resource");
            File zipFile = new File(zipDir.getPath() + "temp.zip");
            //Folder to hold unzipped output
           // outputDir = ExternalStorage.getSDCacheDir(getReactApplicationContext(), "learn-resource");
            try {
                DownloadFile.download(url, zipFile, zipDir);
                String string = unzipFile(zipFile, outputDir);
                //文件下载解压之后完毕之后，就开始进行文件中xml文件的解析
                return string;
            } finally {
                zipFile.delete();
            }
        }

    /*
    * unpack .zip file
    * */

        protected String unzipFile(File zipFile, File destination) {
            DecompressZip decomp = new DecompressZip(zipFile.getPath(),
                    destination.getPath() + File.separator);
            String string = decomp.unzip();

            return string;

        }

    }
}
