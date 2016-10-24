package com.learn.cui19.myclipboard;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity {

    @BindView(R.id.main_drawerlayout) DrawerLayout mDrawerLayout;

    private ContentFragment mCurrentFragment;
    private LeftMenuFragment mLeftMenuFragment;
    private String mTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        completeToolbar();
        initViews();

        FragmentManager fragmentManager = getSupportFragmentManager();

        mCurrentFragment = (ContentFragment) fragmentManager.findFragmentByTag(mTitle);
        if (mCurrentFragment == null) {
            mCurrentFragment = ContentFragment.newInstance(mTitle);
            fragmentManager.beginTransaction().add(R.id.main_content_container, mCurrentFragment, mTitle).commit();
        }

        mLeftMenuFragment = (LeftMenuFragment) fragmentManager.findFragmentById(R.id.main_leftmenu_container);
        if (mLeftMenuFragment == null) {
            mLeftMenuFragment = new LeftMenuFragment();
            fragmentManager.beginTransaction().add(R.id.main_leftmenu_container, mLeftMenuFragment).commit();
        }

//        refreshClipBoardTV();
    }

    private void initViews() {
    }


    /**
     * 完成toolbar的设置
     */
    private void completeToolbar() {
        mToolbar.setTitle("剪切板内容");

        setSupportActionBar(mToolbar);

        mToolbar.setNavigationIcon(android.R.drawable.ic_menu_sort_by_size);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mDrawerLayout.isDrawerOpen(Gravity.LEFT)) {
                    mDrawerLayout.closeDrawer(Gravity.LEFT);
                } else {
                    mDrawerLayout.openDrawer(Gravity.LEFT);
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_toolbar_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.main_toolbar_menu_refresh:
                if (mCurrentFragment != null) {
                    mCurrentFragment.refreshClipBoardTV();
                }
                break;
            case R.id.main_toolbar_menu_writein:
                if (mCurrentFragment != null) {
                    mCurrentFragment.writeInClipBoardTV();
                }
                break;
            case R.id.main_toolbar_menu_about:
                toast("关于");
                break;
            case R.id.main_toolbar_menu_exit:
                finish();
                break;
        }
        return true;
    }


    /**
     * 悬浮窗提示信息
     *
     * @param content
     */
    private void toast(String content) {
        Toast.makeText(this, content, Toast.LENGTH_SHORT).show();
    }

}
