package com.graduation.xujicahng.graduationproject;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.graduation.xujicahng.graduationproject.interfaces.EventListener;
import com.graduation.xujicahng.graduationproject.logic.LoginLogic;
import com.graduation.xujicahng.graduationproject.module.EventArgs;
import com.graduation.xujicahng.graduationproject.module.LoginEventArgs;
import com.graduation.xujicahng.graduationproject.utils.Const;
import com.graduation.xujicahng.graduationproject.utils.ToastUtils;

/**
 * 教师端
 * 登录 页面
 */
public class MainActivity extends Activity {

    private EditText teacherNameEt;
    private EditText teacherPwdEt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //取控件的引用
        Button loginBt = (Button) findViewById(R.id.login_login_bt);
        teacherNameEt = (EditText) findViewById(R.id.login_teacher_name);
        teacherPwdEt = (EditText) findViewById(R.id.login_teacher_pwd);
        TextView pwdForget = (TextView) findViewById(R.id.login_teacher_pwd_forget);
        TextView registerAccount = (TextView) findViewById(R.id.login_teacher_register);
        //登录点击事件
        loginBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginServer();
            }
        });
        //密码忘记
        pwdForget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        //注册账户
        registerAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    /**
     * 登录方法
     */
    private void loginServer() {
        String name, pwd;
        if ((name = getText(teacherNameEt)) != null && (pwd = getText(teacherPwdEt)) != null) {
            LoginLogic.getInstance().loginSever(name, pwd, new EventListener() {
                @Override
                public void onEvent(EventArgs args) {
                    LoginEventArgs args1 = (LoginEventArgs) args;
                    int status = args1.getStatus();
                    if (status == Const.HTTP_SUCCESS) {
                        ToastUtils.Toast(MainActivity.this, args1.isLoginSuccess() ? "登录成功" : "登录失败");
                    }
                }
            });
        }
    }

    private String getText(EditText teacherPwdEt) {
        String text = teacherPwdEt.getText().toString().trim();
        if (TextUtils.isEmpty(text)) {
            text = null;
        }
        return text;
    }
}
