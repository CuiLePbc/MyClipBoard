package com.learn.cui19.myclipboard.mvp.model;

import android.content.Context;

import com.learn.cui19.myclipboard.ClipBoardUtil;
import com.learn.cui19.myclipboard.mvp.presenter.IMainPresenter;

/**
 * Created by cui19 on 2016/11/10.
 * 业务具体处理，包括负责存储、检索、操纵数据等。
 *
 */

public class ClipBoardModel {
    IMainPresenter mIMainPresenter;

    public ClipBoardModel(IMainPresenter iMainPresenter) {
        this.mIMainPresenter = iMainPresenter;
    }

    public void loadData() {
        String strClipBoard = ClipBoardUtil.getClipBoardText();
        ClipBoardModelBean clipBoardModelBean = new ClipBoardModelBean();
        clipBoardModelBean.setContent(strClipBoard);
        mIMainPresenter.loadDataSuccess(clipBoardModelBean);
    }

    public void setData(String str) {
        ClipBoardUtil.setClipBoardText(str);
        mIMainPresenter.setDataSuccess("成功更新");
    }
}
