package com.udacity.jwdnd.course1.cloudstorage.model;

import java.sql.Blob;

public class File {
    private Integer fileid;
    private String filename;
    private String contenttype;
    private String filesize;
    private Integer userid;
    private byte[] filedata;

    public File(String filename, String contenttype, String filesize, Integer userid, byte[] filedata) {
        this.filename = filename;
        this.contenttype = contenttype;
        this.filesize = filesize;
        this.userid = userid;
        this.filedata = filedata;
    }

    public void setFileid(Integer fileid) {
        this.fileid = fileid;
    }

    public Integer getFileid() {
        return fileid;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getFilename() {
        return filename;
    }

    public void setContenttype(String contenttype) {
        this.contenttype = contenttype;
    }

    public String getContenttype() {
        return contenttype;
    }

    public void setFilesize(String filesize) {
        this.filesize = filesize;
    }

    public String getFilesize() {
        return filesize;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setFiledata(byte[] filedata) {
        this.filedata = filedata;
    }

    public byte[] getFiledata() {
        return filedata;
    }
}
