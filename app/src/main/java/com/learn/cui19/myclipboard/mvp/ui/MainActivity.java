package com.learn.cui19.myclipboard.mvp.ui;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.learn.cui19.myclipboard.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity {

    @BindView(R.id.main_drawerlayout) DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mActionBarDrawerToggle;

    private ContentFragment mCurrentFragment;
    private LeftMenuFragment mLeftMenuFragment;
    private String mTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        completeToolbar();

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

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    /**
     * 完成toolbar的设置
     */
    private void completeToolbar() {
        mToolbar.setTitle("剪切板内容");

        setSupportActionBar(mToolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mActionBarDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.drawer_open, R.string.drawer_close);
        mActionBarDrawerToggle.syncState();

//        mToolbar.setNavigationIcon(R.drawable.ic_navigation_menu);
//        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (mDrawerLayout.isDrawerOpen(Gravity.LEFT)) {
//                    mDrawerLayout.closeDrawer(Gravity.LEFT);
//                } else {
//                    mDrawerLayout.openDrawer(Gravity.LEFT);
//                }
//            }
//        });
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
                    mCurrentFragment.loadEditTextData();
                }
                break;
            case R.id.main_toolbar_menu_writein:
                if (mCurrentFragment != null) {
                    mCurrentFragment.setEditTextData();
                }
                break;
            case R.id.main_toolbar_menu_about:

                break;
            case R.id.main_toolbar_menu_exit:
                finish();
                break;
        }
        return true;
    }
}
