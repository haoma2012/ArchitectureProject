package com.mobike.uilibrary.model;

import java.io.Serializable;

/**
 * Created by yangdehao@xiaoyouzi.com  on 2019-06-05 11:24
 */
public class BaseModel<T> implements Serializable {
    public boolean status;
    public int code;
    public String msg;
    public T data;
    public String encrypt;
}

