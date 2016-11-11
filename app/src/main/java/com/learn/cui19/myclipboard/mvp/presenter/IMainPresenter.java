package com.learn.cui19.myclipboard.mvp.presenter;

import com.learn.cui19.myclipboard.mvp.model.ClipBoardModelBean;

/**
 * Created by cui19 on 2016/11/10.
 * 连接Model
 */

public interface IMainPresenter {
    void loadDataSuccess(ClipBoardModelBean clipBoardModelBean);
    void loadDataFailure();
    void setDataSuccess(String str);
}
