package com.zino.mobilization.weatheryamblz.presentation.add_city;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.arellomobile.mvp.MvpAppCompatDialogFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.jakewharton.rxbinding2.widget.RxTextView;
import com.zino.mobilization.weatheryamblz.R;
import com.zino.mobilization.weatheryamblz.WeatherApplication;
import com.zino.mobilization.weatheryamblz.business.entity.Suggestion;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;

/**
 * Created by Denis Buzmakov on 09.08.17.
 * <buzmakov.da@gmail.com>
 */

public class AddCityFragment extends MvpAppCompatDialogFragment implements AddCityView {
    private Unbinder unbinder;
    private SuggestionsAdapter suggestionsAdapter;

    @InjectPresenter AddCityPresenter presenter;

    @BindView(R.id.txt_input) EditText txtInput;
    @BindView(R.id.list_suggestions) RecyclerView rvSuggestions;
    @BindView(R.id.btn_clear) ImageButton btnClear;
    @BindView(R.id.progress) ProgressBar progress;
    @BindView(R.id.txt_message) TextView txtMessage;

    @OnClick(R.id.btn_clear)
    void onClickClear() {
        txtInput.setText("");
    }

    @ProvidePresenter
    AddCityPresenter provideAddCityPresenter() {
        return WeatherApplication.getAppComponent().getAddCityPresenter();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_city, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        rvSuggestions.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvSuggestions.setHasFixedSize(true);

        Observable<String> inputChanges = RxTextView.textChanges(txtInput)
                .map(CharSequence::toString)
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(s -> btnClear.setVisibility(s.length() == 0 ? View.GONE : View.VISIBLE));
        presenter.setInputSubscription(inputChanges);
    }

    @Override
    public void showSuggestions(List<Suggestion> suggestions) {
        if(suggestionsAdapter == null) {
            suggestionsAdapter = new SuggestionsAdapter(
                    suggestions,
                    presenter::onSuggestionSelected
            );
            rvSuggestions.setAdapter(suggestionsAdapter);
        } else {
            suggestionsAdapter.updateData(suggestions);
        }
        txtMessage.setVisibility(View.GONE);
        progress.setVisibility(View.GONE);
        rvSuggestions.setVisibility(View.VISIBLE);
    }

    @Override
    public void showMessage(String message) {
        txtMessage.setText(message);
        txtMessage.setVisibility(View.VISIBLE);
        progress.setVisibility(View.GONE);
        rvSuggestions.setVisibility(View.GONE);
    }

    @Override
    public void showLoading() {
        txtMessage.setVisibility(View.GONE);
        progress.setVisibility(View.VISIBLE);
        rvSuggestions.setVisibility(View.GONE);
    }

    @Override
    public void dismissWithError() {
        getTargetFragment().onActivityResult(getTargetRequestCode(),
                Activity.RESULT_CANCELED,
                getActivity().getIntent()
        );
        dismiss();
    }

    @Override
    public void dismissWithSuccess() {
        getTargetFragment().onActivityResult(getTargetRequestCode(),
                Activity.RESULT_OK,
                getActivity().getIntent()
        );
        dismiss();
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Window window = getDialog().getWindow();
        txtInput.requestFocus();
        if (window != null) {
            window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
            window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    public static AddCityFragment newInstance() {
        return new AddCityFragment();
    }
}
