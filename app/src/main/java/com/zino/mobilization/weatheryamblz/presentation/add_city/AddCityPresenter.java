package com.zino.mobilization.weatheryamblz.presentation.add_city;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.zino.mobilization.weatheryamblz.R;
import com.zino.mobilization.weatheryamblz.business.entity.Suggestion;
import com.zino.mobilization.weatheryamblz.business.interactor.add_city.AddCityInteractor;
import com.zino.mobilization.weatheryamblz.utils.AppResources;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Denis Buzmakov on 09.08.17.
 * <buzmakov.da@gmail.com>
 */

@InjectViewState
public class AddCityPresenter extends MvpPresenter<AddCityView> {
    private AddCityInteractor interactor;
    private AppResources appResources;
    private Disposable inputSubscription;

    public AddCityPresenter(AddCityInteractor interactor,
                            AppResources resources) {
        this.interactor = interactor;
        this.appResources = resources;
    }

    public void setInputSubscription(Observable<String> inputSubscription) {
        this.inputSubscription = inputSubscription
                .doOnNext(s -> {
                    if(s.length() == 0) {
                        getViewState().showMessage(appResources.getString(R.string.message_empty_input));
                    } else {
                        getViewState().showLoading();
                    }
                })
                .debounce(500, TimeUnit.MILLISECONDS)
                .observeOn(Schedulers.io())
                .filter(s -> s.length() > 0)
                .switchMapSingle(s -> interactor.getSuggestions(s))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnError(__ -> getViewState().showMessage(appResources.getString(R.string.message_error)))
                .retryWhen(__ -> inputSubscription)
                .subscribe(
                        suggestions -> {
                            if(suggestions.size() == 0) {
                                getViewState().showMessage(appResources.getString(R.string.message_empty_result));
                            } else {
                                getViewState().showSuggestions(suggestions);
                            }
                        }
                );
    }

    public void onSuggestionSelected(Suggestion suggestion) {
        interactor.getPlace(suggestion.getCityId())
                .flatMapCompletable(interactor::savePlace)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(__ -> getViewState().showLoading())
                .subscribe(() -> getViewState().dismiss());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(inputSubscription != null) {
            inputSubscription.dispose();
            inputSubscription = null;
        }
    }
}
