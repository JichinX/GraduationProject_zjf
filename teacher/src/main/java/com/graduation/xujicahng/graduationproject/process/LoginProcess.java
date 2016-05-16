package com.graduation.xujicahng.graduationproject.process;

import com.graduation.xujicahng.graduationproject.utils.Const;

import org.json.JSONObject;

/**
 * XXXX.java是趣猜APP的XXXX类
 *
 * @author xujichang
 * @version 2016/5/16.
 */
public class LoginProcess extends BaseProcess {

    private String Url = "/teacher/login.do?";
    private String teacherName;
    private String teacherPwd;

    private boolean loginSuccess;

    public boolean isLoginSuccess() {
        return loginSuccess;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public void setTeacherPwd(String teacherPwd) {
        this.teacherPwd = teacherPwd;
    }

    @Override
    String getRequestUrl() {
        return new StringBuilder(Url).append("name=").append(teacherName).append("&password=").append(teacherPwd).toString();
    }

    @Override
    String getParameter() {
        return null;
    }

    @Override
    void onResult(JSONObject object) {
        int statusCode = object.optInt("status");
        setStatus(statusCode);
        if (statusCode == Const.HTTP_SUCCESS) {
            loginSuccess = object.optBoolean("data");
        }
    }
}
