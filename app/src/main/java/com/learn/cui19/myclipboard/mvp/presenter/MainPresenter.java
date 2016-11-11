package com.learn.cui19.myclipboard.mvp.presenter;

import com.learn.cui19.myclipboard.mvp.model.ClipBoardModel;
import com.learn.cui19.myclipboard.mvp.model.ClipBoardModelBean;
import com.learn.cui19.myclipboard.mvp.view.MainView;

/**
 * Created by cui19 on 2016/11/10.
 * view和model的桥梁，从model获得数据，返回给view层
 */

public class MainPresenter implements Presenter<MainView>, IMainPresenter {
    private MainView mMainView;
    private ClipBoardModel mClipBoardModel;

    public MainPresenter(MainView mainView) {
        attachView(mainView);
        mClipBoardModel = new ClipBoardModel(this);
    }

    @Override
    public void attachView(MainView mainView) {
        this.mMainView = mainView;
    }

    @Override
    public void detachView() {
        this.mMainView = null;
    }

    public void loadData() {
        mMainView.showProgressBar();
        mClipBoardModel.loadData();
    }

    public void setData(String str) {
        mMainView.showProgressBar();
        mClipBoardModel.setData(str);
    }

    @Override
    public void loadDataSuccess(ClipBoardModelBean clipBoardModelBean) {
        mMainView.showClipboardData(clipBoardModelBean);
        mMainView.hideProgressBar();
    }

    @Override
    public void loadDataFailure() {
        mMainView.hideProgressBar();
    }

    @Override
    public void setDataSuccess(String msg) {
        mMainView.showSuccessMsg(msg);
        mMainView.hideProgressBar();
    }
}
