package com.learn.cui19.myclipboard.mvp.view;

import com.learn.cui19.myclipboard.mvp.model.ClipBoardModelBean;

/**
 * Created by cui19 on 2016/11/10.
 * 处理业务需要哪些方法
 */

public interface MainView {
    void showProgressBar();
    void hideProgressBar();
    void showClipboardData(ClipBoardModelBean clipBoardModelBean);
    void showSuccessMsg(String msg);
}
