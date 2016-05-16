package com.graduation.xujicahng.graduationproject.logic;

import com.graduation.xujicahng.graduationproject.interfaces.EventListener;
import com.graduation.xujicahng.graduationproject.module.EventArgs;

/**
 * @author xujichang
 * @version 2016/5/16.
 */
public class BaseLogic {

    public void fireEvent(EventArgs args, EventListener listener) {
        listener.onEvent(args);
    }
}
