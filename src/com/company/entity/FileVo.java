package com.company.entity;

public class FileVo {
    private  String  fileName;
    private  long  fileLength;
    private  String  filePath;

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
            this.fileName = fileName;
    }

    public long getFileLength() {
        return fileLength;
    }

    public void setFileLength(long fileLength) {
            this.fileLength = fileLength;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
            this.filePath = filePath;
    }
}
