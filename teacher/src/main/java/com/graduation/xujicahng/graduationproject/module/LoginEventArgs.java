package com.graduation.xujicahng.graduationproject.module;

/**
 * XXXX.java是趣猜APP的XXXX类
 *
 * @author xujichang
 * @version 2016/5/16.
 */
public class LoginEventArgs extends EventArgs {
    private boolean loginSuccess;

    public boolean isLoginSuccess() {
        return loginSuccess;
    }

    public void setLoginSuccess(boolean loginSuccess) {
        this.loginSuccess = loginSuccess;
    }

    public LoginEventArgs(int status, boolean loginSuccess) {
        super(status);
        this.loginSuccess = loginSuccess;
    }
}
