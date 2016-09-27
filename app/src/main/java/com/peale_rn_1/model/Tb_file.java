package com.peale_rn_1.model;

/**
 * Created by Administrator on 2016/9/14.
 */
public class Tb_file {

        private String id;
        private String titleID;//主题ID，分别对应不同的主题
        private String dayId;//当前选择的实际年级
        private String recommendDayId;//当前推荐的年级
        private String fileName;//下载的文件名称
        private String filePath;//存放的路径

        public Tb_file() {
        }

        public Tb_file(String titleID, String dayId, String recommendDayId, String fileName, String filePath) {
                this.titleID = titleID;
                this.dayId = dayId;
                this.recommendDayId = recommendDayId;
                this.fileName = fileName;
                this.filePath = filePath;
        }

        public String getTitleID() {
                return titleID;
        }

        public void setTitleID(String titleID) {
                this.titleID = titleID;
        }

        public String getId() {
                return id;
        }

        public void setId(String id) {
                this.id = id;
        }

        public String getDayId() {
                return dayId;
        }

        public void setDayId(String dayId) {
                this.dayId = dayId;
        }

        public String getRecommendDayId() {
                return recommendDayId;
        }

        public void setRecommendDayId(String recommendDayId) {
                this.recommendDayId = recommendDayId;
        }

        public String getFileName() {
                return fileName;
        }

        public void setFileName(String fileName) {
                this.fileName = fileName;
        }
        public String getFilePath() {
                return filePath;
        }

        public void setFilePath(String filePath) {
                this.filePath = filePath;
        }
}
