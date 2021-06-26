package com.udacity.jwdnd.course1.cloudstorage.model;

public class Note {
    private Integer noteid;
    private String notetitle;
    private String notedescription;
    private Integer userid;

    public void setNoteid(Integer noteid) {
        this.noteid = noteid;
    }

    public Integer getNoteid() {
        return noteid;
    }

    public void setNotetitle(String notetitle) {
        this.notetitle = notetitle;
    }

    public String getNotetitle() {
        return notetitle;
    }

    public void setNotedescription(String notedescription) {
        this.notedescription = notedescription;
    }

    public String getNotedescription() {
        return notedescription;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public Integer getUserid() {
        return userid;
    }
}
