package com.peale_rn_1.control;

import android.widget.Toast;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.WritableArray;
import com.facebook.react.bridge.WritableMap;
import com.peale_rn_1.dao.ResourceDAO;
import com.peale_rn_1.dao.WordDAO;
import com.peale_rn_1.model.Tb_file;
import com.peale_rn_1.model.Tb_word;

/**
 * Created by dell on 2016/7/19.
 */
public class RNResourceManager extends ReactContextBaseJavaModule {


    public RNResourceManager(ReactApplicationContext reactContext) {
        super(reactContext);
    }

    @Override
    public String getName() {
        return "ResourceManage";
    }

   @ReactMethod
    public void findFileByTitleIDAndGrade(String titleID,String dayClick,String recommendDayID, Callback successCallback, Callback errorCallback){
       //根据主题ＩＤ，实际选择年级，推荐年级，来查找该资源文件是否存在
       //存在返回文件路径，否则返回，null
      try {
          ResourceDAO resourceDAO = new ResourceDAO(getReactApplicationContext());
          Tb_file mainFile = resourceDAO.findFile(titleID, dayClick, dayClick);//根据实际年级和推荐年级一样，找到主文件
          Tb_file subordinateFile = resourceDAO.findFile(titleID, dayClick, recommendDayID); //根据实际年级和推荐年级，找到从文件
          if(mainFile == null){
              successCallback.invoke(null,null,null,null);
          }
          else{
              String mainFileName = mainFile.getFileName();
              String mainFilePath = mainFile.getFilePath();
              if(subordinateFile == null){
                  successCallback.invoke(mainFileName,mainFilePath,null,null);
              }
              else{
                  String subordinateFileName = subordinateFile.getFileName();
                  String subordinateFilePath = subordinateFile.getFilePath();
                  successCallback.invoke(mainFileName,mainFilePath,subordinateFileName,subordinateFilePath);
              }
          }


      }catch (Exception e){
          errorCallback.invoke(e.getMessage());
      }
   }

    @ReactMethod
    public void findWordInfoByWordName(String word,String titleID,String realGrade,String recommendGrade,Callback successCallback,Callback errorCallback ){
        //先对传过来的单词进行处理，在数据后中单词是以  A_B，这种形式
        String newWord = word.replace(" ","_");
        WordDAO wordDAO = new WordDAO(getReactApplicationContext());
        ResourceDAO resourceDAO = new ResourceDAO(getReactApplicationContext());
        int realGrade1 = Integer.parseInt(realGrade);
        int recommendGrade1 = Integer.parseInt(recommendGrade);
        try {
            WritableMap map = wordDAO.findWordInfoByWordName(newWord);
            Tb_file mainFile = resourceDAO.findFile(titleID, realGrade, realGrade);//根据实际年级和推荐年级一样，找到主文件
            Tb_file subordinateFile = resourceDAO.findFile(titleID, realGrade, recommendGrade); //根据实际年级和推荐年级，找到从文件:
            /*对关联的单词进行处理 proAssociate*/
            String str = map.getString("proAssociate");
            map.putString("flagAssociate","1");
            if(str == null || str.isEmpty()||str.equals("无")) {
                map.putString("flagAssociate","0");
            }else {
                WritableArray proAssociateArray =  this.dealWithProAssociate(str);
                map.putArray("proAssociate",proAssociateArray);
            }

            /*对单词拓展方面进行处理*/
            String str1 = map.getString("proExtend");
            map.putString("flagProExtend","1");
            if(str1 ==null || str1.isEmpty()||str1.equals("无")){
                map.putString("flagProExtend","0");
            }else{
                WritableArray proExtendArray =  this.dealWithWord(str1);
                map.putArray("proExtend",proExtendArray);
            }

              /*对单词常用proCommonUse进行处理*/
            String str2 = map.getString("proCommonUse");
            map.putString("flagProCommonUse","1");
            if(str2 ==null || str2.isEmpty()||str2.equals("无")){
                map.putString("flagProCommonUse","0");
            }else{
                WritableArray proCommonUseArray =  this.dealWithWord(str2);
                map.putArray("proCommonUse",proCommonUseArray);
            }
            /*视频方面推荐*/
            if(realGrade1>recommendGrade1){  //实际年级大于推荐年级
                map.putString("video",map.getString("vedioPath3"));
            }
            if(realGrade1<recommendGrade1){     //实际年级小与推荐年级
                map.putString("video",map.getString("vedioPath1"));
            }
            if(realGrade1==recommendGrade1){
                map.putString("video",map.getString("vedioPath2"));
            }
            /*课文原句和情景段落的选择*/
            switch (realGrade){
                case "1":map.putString("proText",map.getString("proText1"));
                         map.putString("pathText",map.getString("pathText1"));
                         map.putString("proScene",map.getString("proScene1"));
                         map.putString("pathScene",map.getString("pathScene1"));
                       break;
                case "2":map.putString("proText",map.getString("proText2"));
                         map.putString("pathText",map.getString("pathText2"));
                         map.putString("proScene",map.getString("proScene2"));
                        map.putString("pathScene",map.getString("pathScene2"));
                    break;
                case "3":map.putString("proText",map.getString("proText3"));
                         map.putString("pathText",map.getString("pathText3"));
                         map.putString("proScene",map.getString("proScene3"));
                         map.putString("pathScene",map.getString("pathScene3"));
                       break;
                case "4":map.putString("proText",map.getString("proText4"));
                         map.putString("pathText",map.getString("pathText4"));
                         map.putString("proScene",map.getString("proScene4"));
                         map.putString("pathScene",map.getString("pathScene4"));
                       break;
                case "5":map.putString("proText",map.getString("proText5"));
                         map.putString("pathText",map.getString("pathText5"));
                         map.putString("proScene",map.getString("proScene5"));
                          map.putString("pathScene",map.getString("pathScene5"));
                        break;
                case "6":map.putString("proText",map.getString("proText6"));
                         map.putString("pathText",map.getString("pathText6"));
                         map.putString("proScene",map.getString("proScene6"));
                         map.putString("pathScene",map.getString("pathScene6"));
                      break;
            }
            //实际年级d等于推荐年级
            map.putString("name",word);
            map.putString("mainFileName",mainFile.getFileName());
            map.putString("mainFilePath",mainFile.getFilePath());
            map.putString("subordinateFileName",subordinateFile.getFileName());
            map.putString("subordinateFilePath",subordinateFile.getFilePath());
            successCallback.invoke(map);
        }catch (Exception e){
            e.printStackTrace();
            errorCallback.invoke(e);
        }
    }


    /**
     * 根据从属属性，知道所有的从属单词，将从属单词、从属单词发音、照片分别放到3个数组，返回一个数组的数组
     * @param word
     * @return
     */
    public WritableArray dealWithProAssociate(String word){
            String[] strArray =word.split("/");
            WritableArray proAssociateArray = Arguments.createArray();//总的数组，里面存放3个数组，一是从属单词，二是从属单词的图片，三是从属单词的发音
            WritableArray proAssociateWordArray = Arguments.createArray();
            WritableArray proAssociatePronunciationArray = Arguments.createArray();
            WritableArray proAssociatePictureArray = Arguments.createArray();
            WordDAO wordDAO = new WordDAO(getReactApplicationContext());
            for (String str:strArray) {
                int length = str.indexOf("(");
                String Word = str.substring(0,length-1);//获取从属单词
                WritableMap map = wordDAO.findWordInfo(Word);
                proAssociateWordArray.pushString(str);
                proAssociatePronunciationArray.pushString(map.getString("pronunctionPath"));
                proAssociatePictureArray.pushString(map.getString("picturePath"));
            }
            proAssociateArray.pushArray(proAssociateWordArray);
            proAssociateArray.pushArray(proAssociatePronunciationArray);
            proAssociateArray.pushArray(proAssociatePictureArray);
           return proAssociateArray;

        }

    public WritableArray dealWithWord(String word){
        String[] strArray =word.split("/");
        WritableArray proArray = Arguments.createArray();//总的数组，里面存放3个数组，一是从属单词，二是从属单词的图片，三是从属单词的发音
        WritableArray proWordArray = Arguments.createArray();
        WritableArray proPronunciationArray = Arguments.createArray();
        WritableArray proPictureArray = Arguments.createArray();
        WordDAO wordDAO = new WordDAO(getReactApplicationContext());
        for (String str:strArray) {
            int length = str.indexOf("(");
            String Word = str.substring(0,length);//获取从属单词
            WritableMap map = wordDAO.findWordInfo(Word);
            proWordArray.pushString(str);
            proPronunciationArray.pushString(map.getString("pronunctionPath"));
            proPictureArray.pushString(map.getString("picturePath"));
        }
        proArray.pushArray(proWordArray);
        proArray.pushArray(proPronunciationArray);
        proArray.pushArray( proPictureArray);
        return  proArray;
    }


}
