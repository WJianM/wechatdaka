package com.daka.po;

import java.util.Date;

public class Records {
    private Integer id;

    private Date recordsDate;

    private Integer userId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getRecordsDate() {
        return recordsDate;
    }

    public void setRecordsDate(Date recordsDate) {
        this.recordsDate = recordsDate;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}