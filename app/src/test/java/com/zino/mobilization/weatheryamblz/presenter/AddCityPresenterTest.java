package com.zino.mobilization.weatheryamblz.presenter;

import com.zino.mobilization.weatheryamblz.R;
import com.zino.mobilization.weatheryamblz.business.entity.Suggestion;
import com.zino.mobilization.weatheryamblz.business.interactor.add_city.AddCityInteractor;
import com.zino.mobilization.weatheryamblz.common.RxImmediateSchedulerRule;
import com.zino.mobilization.weatheryamblz.common.TestData;
import com.zino.mobilization.weatheryamblz.presentation.add_city.AddCityPresenter;
import com.zino.mobilization.weatheryamblz.presentation.add_city.AddCityView$$State;
import com.zino.mobilization.weatheryamblz.utils.AppResources;

import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import io.reactivex.Completable;
import io.reactivex.Single;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by Denis Buzmakov on 13.08.17.
 * <buzmakov.da@gmail.com>
 */

public class AddCityPresenterTest {
    @ClassRule
    public static final RxImmediateSchedulerRule rxRule = new RxImmediateSchedulerRule();

    @Mock
    private AppResources appResources;
    @Mock
    private AddCityInteractor addCityInteractor;
    @Mock
    private AddCityView$$State viewState;

    private AddCityPresenter presenter;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);

        presenter = new AddCityPresenter(addCityInteractor, appResources);
        presenter.setViewState(viewState);

        when(addCityInteractor.getPlace(anyString()))
                .thenReturn(Single.just(TestData.getCorrectPlace()));
        when(addCityInteractor.getSuggestions(anyString()))
                .thenReturn(Single.just(TestData.getCorrectSuggestions()));
        when(addCityInteractor.savePlace(any()))
                .thenReturn(Completable.complete());

        when(appResources.getString(R.string.message_empty_input))
                .thenReturn("Enter location name");
        when(appResources.getString(R.string.message_error))
                .thenReturn("Failed to load suggestions");
        when(appResources.getString(R.string.message_empty_result))
                .thenReturn("Nothing found\nmatching your query");
    }

    @Test
    public void shouldLoadPlaceAfterSuggestionSelected() {
        Suggestion suggestion = TestData.getCorrectSuggestion();
        presenter.onSuggestionSelected(suggestion);

        verify(addCityInteractor).getPlace(suggestion.getCityId());
        verify(addCityInteractor).savePlace(any());
        verify(viewState).showLoading();
        verify(viewState).dismissWithSuccess();
        verify(viewState, never()).dismissWithError();
    }

    @Test
    public void shouldFailLoadPlaceAfterSuggestionSelected() {
        RuntimeException exception = new RuntimeException();
        when(addCityInteractor.getPlace(anyString()))
                .thenReturn(Single.error(exception));

        Suggestion suggestion = TestData.getCorrectSuggestion();
        presenter.onSuggestionSelected(suggestion);

        verify(addCityInteractor).getPlace(suggestion.getCityId());
        verify(addCityInteractor, never()).savePlace(any());
        verify(viewState).showLoading();
        verify(viewState, never()).dismissWithSuccess();
        verify(viewState).dismissWithError();
    }
}
