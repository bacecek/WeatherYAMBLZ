package com.zino.mobilization.weatheryamblz.business.interactor.service;

import com.zino.mobilization.weatheryamblz.business.interactor.base.BaseInteractor;

import io.reactivex.Completable;

/**
 * Created by Denis Buzmakov on 06.08.17.
 * <buzmakov.da@gmail.com>
 */

public interface ServiceInteractor extends BaseInteractor{
    Completable fetchAndSaveAllCities();
}
