package com.peale_rn_1.util;


import android.content.Context;
import android.os.Environment;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by dell on 2016/7/19.
 */
public class ExternalStorage {
    @SuppressWarnings("unused")
    private static final String TAG = "ExternalStorae";

    //Convention for external storage path used by android 2.2
    private static final String EXT_STORAGE_ROOT_REFIX = "/Android/data/";
    private static final String EXT_STORAGE_ROOT_SUFFIX = "/files";

    private static StringBuilder sStoragePath = new StringBuilder();

    /**
     * */
    private static final String  ALTERNATE_SDCARD_MOUNTS[]= {
            "/emmc",				// Internal storage on Droid Incredible, Nook Color/CyanogenMod, some other devices
            "/sdcard/ext_sd",		// Newer (2011) HTC devices (Flyer, Rezound)
            "/sdcard-ext",			// Some Motorola devices (RAZR)
            "/sdcard/sd",			// Older Samsung Galaxy S (Captivate)
            "/sdcard/sdcard"		// Archos tablets
    };

    public static File getSDCacheDir(Context context, String dirName) {
        File cacheDir = null;

        //判断SD卡是否存在，并且是否具有读写权限
        if(Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
            Method getExternalFilesDirMethod = null;
            try{

                //任何私有的文件应该被放置在context.getExternalFilesDir返回目录下，在应用被卸载时，系统会清理就是这个目录
                getExternalFilesDirMethod = Context.class.getMethod("getExternalFilesDir", String.class);
                cacheDir = (File) getExternalFilesDirMethod.invoke(context,dirName);
            }catch (NoSuchMethodException e){
                //Android 2.1 and earlier - use old APIs
                cacheDir = buildCacheDirPath(context, Environment.getExternalStorageDirectory(),dirName);
            }catch (IllegalArgumentException e){
                cacheDir = buildCacheDirPath(context, Environment.getExternalStorageDirectory(),dirName);
            }catch(IllegalAccessException e){
                cacheDir = buildCacheDirPath(context, Environment.getExternalStorageDirectory(),dirName);
            }catch(InvocationTargetException e){
                cacheDir = buildCacheDirPath(context, Environment.getExternalStorageDirectory(),dirName);
            }
        }

        if(cacheDir == null){
            //如果企图获取默认的外部存储失败
            //寻找另外合适的外部文件系统，可以让我们能存储我们的crap
            for( int i = 0 ;i<ALTERNATE_SDCARD_MOUNTS.length;i++){
                File alternateDir = new File (ALTERNATE_SDCARD_MOUNTS[i]);
                if(alternateDir.exists() && alternateDir.isDirectory() &&
                        alternateDir.canRead() && alternateDir.canWrite() ){
                    cacheDir  = buildCacheDirPath(context, alternateDir, dirName );
                    break;
                }

            }
        }

        //如果文件不存在且不为空，则在外部存储尝试建立文件
        if(cacheDir != null && !cacheDir.exists()){
            if(! cacheDir.mkdir()){
                cacheDir = null;//建立文件失败
            }
        }

        //外部存储空间都不行时，fall back on interal cache as a last resort
        if(cacheDir == null){
            cacheDir = new File(context.getCacheDir() + File.separator + dirName) ; //getCacheDir()方法得到的是内存上的目录
            cacheDir.mkdirs();
        }

        return  cacheDir;
    }

    /*
    *  清除掉SD cache上的文件
    * */
    public static void clearSDCache(Context context, String dirName){
        File cacheDir = getSDCacheDir(context,dirName); //这个cacheDir应该是文件夹
        File[] files = cacheDir.listFiles();
        for(File f : files){
            f.delete();
        }
        cacheDir.delete();
    }

    /*
    *  use older Android APIs to put data in the same relative directory location as 2.2 API
    * */
    private static File buildCacheDirPath(Context context, File mountPoint, String dirName) {
        sStoragePath.setLength(0);
        sStoragePath.append(EXT_STORAGE_ROOT_REFIX);
        sStoragePath.append(context.getPackageName());
        sStoragePath.append(EXT_STORAGE_ROOT_SUFFIX);
        sStoragePath.append(dirName);
        return new File(mountPoint, sStoragePath.toString());
    }
}
