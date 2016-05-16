package com.graduation.xujicahng.graduationproject.logic;

import com.graduation.xujicahng.graduationproject.interfaces.EventListener;
import com.graduation.xujicahng.graduationproject.interfaces.ResponseListener;
import com.graduation.xujicahng.graduationproject.module.LoginEventArgs;
import com.graduation.xujicahng.graduationproject.process.LoginProcess;
import com.graduation.xujicahng.graduationproject.utils.Const;

/**
 * XXXX.java是趣猜APP的XXXX类
 *
 * @author xujichang
 * @version 2016/5/16.
 */
public class LoginLogic extends BaseLogic {
    private static LoginLogic instance;

    public static LoginLogic getInstance() {
        if (instance == null) {
            instance = new LoginLogic();
        }
        return instance;
    }

    /**
     * 登录
     *
     * @param name
     * @param pwd
     * @param listener
     */
    public void loginSever(String name, String pwd, final EventListener listener) {
        final LoginProcess process = new LoginProcess();
        process.setTeacherName(name);
        process.setTeacherPwd(pwd);
        process.runGet(new ResponseListener() {
            @Override
            public void onResponse(String requestId) {
                LoginEventArgs args = new LoginEventArgs(process.getStatus(), process.isLoginSuccess());
                fireEvent(args, listener);
            }
        });
    }
}
