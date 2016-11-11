package com.learn.cui19.myclipboard.mvp.ui;


import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.learn.cui19.myclipboard.R;
import com.learn.cui19.myclipboard.ScreenUtil;
import com.learn.cui19.myclipboard.mvp.model.ClipBoardModelBean;
import com.learn.cui19.myclipboard.mvp.presenter.MainPresenter;
import com.learn.cui19.myclipboard.mvp.view.MainView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class ContentFragment extends Fragment implements MainView {

    public static final String KEY_TITLE = "key_title";
    private String mTitle;

    private MainPresenter mMainPresenter;

    @BindView(R.id.fragment_main_content_progressbar)
    ProgressBar mProgressBar;
    @BindView(R.id.fragment_main_content_text_view)
    EditText mEditText;

    public ContentFragment() {
        // Required empty public constructor
    }

    public static ContentFragment newInstance(String title) {
        ContentFragment contentFragment = new ContentFragment();
        Bundle bundle = new Bundle();
        bundle.putString(KEY_TITLE, title);
        contentFragment.setArguments(bundle);
        return contentFragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main_content, container, false);
        ButterKnife.bind(this, view);

        loadEditTextData();

        return view;
    }

    public void loadEditTextData() {
        mMainPresenter = new MainPresenter(this);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mMainPresenter.loadData();
            }
        }, 2000);
    }

    public void setEditTextData() {
        mMainPresenter = new MainPresenter(this);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mMainPresenter.setData(mEditText.getText().toString());
            }
        }, 2000);
    }

    @Override
    public void showProgressBar() {
        if (mProgressBar.getVisibility() != ProgressBar.VISIBLE)
            mProgressBar.setVisibility(ProgressBar.VISIBLE);
    }

    @Override
    public void hideProgressBar() {
        if (mProgressBar.getVisibility() == ProgressBar.VISIBLE)
            mProgressBar.setVisibility(ProgressBar.INVISIBLE);
    }

    @Override
    public void showClipboardData(ClipBoardModelBean clipBoardModelBean) {
        mEditText.setText(clipBoardModelBean.getContent());
        ScreenUtil.snack("已刷新", this.getView());
    }

    @Override
    public void showSuccessMsg(String msg) {
        ScreenUtil.snack(msg, this.getView());
    }

    @Override
    public void onDestroyView() {
        mMainPresenter.detachView();
        super.onDestroyView();
    }
}
