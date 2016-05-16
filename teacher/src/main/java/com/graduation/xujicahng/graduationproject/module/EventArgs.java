package com.graduation.xujicahng.graduationproject.module;

/**
 * XXXX.java是趣猜APP的XXXX类
 *
 * @author xujichang
 * @version 2016/5/16.
 */
public class EventArgs {
    //服务器连接状态
    protected int status;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public EventArgs(int status) {
        this.status = status;
    }
}
