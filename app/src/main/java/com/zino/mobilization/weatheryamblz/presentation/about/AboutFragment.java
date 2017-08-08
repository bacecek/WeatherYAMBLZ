package com.zino.mobilization.weatheryamblz.presentation.about;


import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.TextView;

import com.zino.mobilization.weatheryamblz.BuildConfig;
import com.zino.mobilization.weatheryamblz.R;
import com.zino.mobilization.weatheryamblz.presentation.common.BaseFragment;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 */
public class AboutFragment extends BaseFragment {

    @BindView(R.id.version_text_view)
    TextView versionTextView;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setVersionName();
    }

    private void setVersionName() {
        String version = BuildConfig.VERSION_NAME;
        versionTextView.setText(String.format(getResources().getString(R.string.version), version));
    }

    @LayoutRes
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_about;
    }
}
