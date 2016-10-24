package com.learn.cui19.myclipboard;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * A simple {@link Fragment} subclass.
 */
public class ContentFragment extends Fragment {

    public static final String KEY_TITLE = "key_title";
    private String mTitle;
    private EditText tv;

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
        String title = (String) getArguments().get(KEY_TITLE);

        tv = new EditText(getActivity());
        tv.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        tv.setHint("剪切板当前为空");
        refreshClipBoardTV();

        return tv;
    }

    /**
     * 刷新剪切板显示窗内容
     */
    public void refreshClipBoardTV() {
        String clipBoardStr = ClipBoardUtil.getClipBoardText(getActivity());
        tv.setText(clipBoardStr);
        toast("已刷新");
    }

    /**
     * 将当前页面内容写入剪切板
     */
    public void writeInClipBoardTV() {
        String tvStr = tv.getText().toString();
        ClipBoardUtil.setClipBoardText(getActivity(), tvStr);
        toast("已将当前页面内容设置到剪切板");
    }

    /**
     * 悬浮窗提示信息
     *
     * @param content
     */
    private void toast(String content) {
        Toast.makeText(getActivity(), content, Toast.LENGTH_SHORT).show();
    }
}
