package com.learn.cui19.myclipboard;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;

public class BaseActivity extends AppCompatActivity {
    private static final String TAG = "BaseActivity";

    private LinearLayout mRootLayout;

//    TextView mToolbarCenterTV;
//
//    TextView mToolbarLeftTV;
//
//    TextView mToolbarRightTV;

    protected Toolbar mToolbar;

    public Toolbar getToolbar() {
        return mToolbar;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_base);

        if (!isTaskRoot()) {
            /* If it is not the root Activity, finish it! */
            Intent intent = getIntent();
            String action = intent.getAction();
            if (intent.hasCategory(Intent.CATEGORY_LAUNCHER) && Intent.ACTION_MAIN.equals(action)) {
                Log.w(TAG, "Activity is not the root, Finishing Activity instead of launching");
                finish();
                return;
            }
        }
    }

    @Override
    public void setContentView(int layoutId) {
        setContentView(View.inflate(this, layoutId, null));
    }

    @Override
    public void setContentView(View view) {
        mRootLayout = (LinearLayout) findViewById(R.id.root_layout);
        if (mRootLayout == null) {
            return;
        }
        mRootLayout.addView(view, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));

        initToolbar();
    }

    /**
     *初始化Toolbar
     */
    private void initToolbar() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        if (mToolbar != null) {
            showToolbar(true);
//            mToolbarLeftTV = (TextView) findViewById(R.id.toolbar_left_tv);
//            mToolbarCenterTV = (TextView) findViewById(R.id.toolbar_center_tv);
//            mToolbarRightTV = (TextView) findViewById(R.id.toolbar_right_tv);
            //mToolbar.inflateMenu(R.menu.main);
            /** 设置支持ActionBar，当然也可以不这样做 */
            //setSupportActionBar(mToolbar);
            /** 去除ActionBar默认Title显示 */
            //getSupportActionBar().setDisplayShowTitleEnabled(false);
        }
    }

    /**
     * 显示Toolbar
     * @param show
     */
    public void showToolbar(boolean show) {
        if (mToolbar == null) {
            Log.e(TAG, "Toolbar is null");
        } else {
            int paddingTop = mToolbar.getPaddingTop();
            int paddingLeft = mToolbar.getPaddingLeft();
            int paddingBottom = mToolbar.getPaddingBottom();
            int paddingRight = mToolbar.getPaddingRight();
            int statusHeight = ScreenUtil.getStatusHeight(this);
            ViewGroup.LayoutParams params = mToolbar.getLayoutParams();
            int height = params.height;

            /**
             * 利用状态栏的高度，4.4及以上版本给Toolbar设置一个paddingTop值为status_bar的高度，
             * Toolbar延伸到status_bar顶部
             **/
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                setTranslucentStatus(show);
                if (show) {
                    paddingTop += statusHeight;
                    height += statusHeight;
                } else {
                    paddingTop -= statusHeight;
                    height -= statusHeight;
                }
            }

            params.height = height;
            mToolbar.setPadding(paddingLeft, paddingTop, paddingRight, paddingBottom);
            mToolbar.setVisibility(show ? View.VISIBLE : View.GONE);
        }
    }

    /**
     * 设置状态栏透明
     * 对4.4以上版本有效
     * @param on
     */
    @TargetApi(Build.VERSION_CODES.KITKAT)
    private void setTranslucentStatus(boolean on) {
        Window win = getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();
        final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
        if (on) {
            winParams.flags |= bits;
        } else {
            winParams.flags &= ~bits;
        }
        win.setAttributes(winParams);
    }

    /**
     * 设置标题栏文字
     * @param leftTitleText
     * @param centerTitleText
     * @param rightTitleText
     */
//    public void setTitleText(String leftTitleText, String centerTitleText, String rightTitleText){
//        mToolbarCenterTV.setText(centerTitleText);
//        mToolbarLeftTV.setText(leftTitleText);
//        mToolbarRightTV.setText(rightTitleText);
//    }
}
