package com.qomunal.opensource.androidresearch.ui.main

import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.qomunal.opensource.androidresearch.common.base.BaseActivity
import com.qomunal.opensource.androidresearch.databinding.ActivityMainBinding


class MainActivity : BaseActivity<ActivityMainBinding>() {

    private val viewModel: MainViewModel by viewModels()

    private val mAdapter: MainAdapter by lazy {
        MainAdapter()
    }

    private val linearLayoutManager : LinearLayoutManager by lazy {
        LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
    }

    override fun setupViewBinding(): ActivityMainBinding {
        return ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getMainData()
    }

    override fun initUI() {
        binding.apply {
            rv.apply {
                adapter = mAdapter
                layoutManager = linearLayoutManager
            }
        }
    }

    override fun initObserver() {
        viewModel.apply {
            mainState.observe(this@MainActivity, {
                mAdapter.setItem(it)
            })
        }
    }

}