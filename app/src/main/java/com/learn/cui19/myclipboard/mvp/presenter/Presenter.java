package com.learn.cui19.myclipboard.mvp.presenter;

/**
 * Created by cui19 on 2016/11/10.
 */

public interface Presenter<V> {
    void attachView(V v);
    void detachView();
}
