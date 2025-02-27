package com.qomunal.opensource.androidresearch.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.qomunal.opensource.androidresearch.R
import com.qomunal.opensource.androidresearch.common.base.BaseViewModel
import com.qomunal.opensource.androidresearch.model.MainModel

/**
 * Created by faisalamircs on 13/01/2024
 * -----------------------------------------
 * Name     : Muhammad Faisal Amir
 * E-mail   : faisalamircs@gmail.com
 * Github   : github.com/amirisback
 * -----------------------------------------
 */

class MainViewModel : BaseViewModel() {

    private val _mainState: MutableLiveData<List<MainModel>> = MutableLiveData()
    val mainState: LiveData<List<MainModel>> = _mainState

    fun getMainData() {
        _mainState.postValue(
            mutableListOf(
                MainModel(R.color.a),
                MainModel(R.color.b),
                MainModel(R.color.c),
                MainModel(R.color.d),
                MainModel(R.color.e),
                MainModel(R.color.f),
            )
        )
    }

}