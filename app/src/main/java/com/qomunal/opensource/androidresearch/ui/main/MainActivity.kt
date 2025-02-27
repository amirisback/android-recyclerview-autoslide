package com.qomunal.opensource.androidresearch.ui.main

import android.os.Bundle
import android.os.Handler
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.qomunal.opensource.androidresearch.common.base.BaseActivity
import com.qomunal.opensource.androidresearch.databinding.ActivityMainBinding
import java.util.Timer
import java.util.TimerTask


class MainActivity : BaseActivity<ActivityMainBinding>() {

    private val viewModel: MainViewModel by viewModels()

    private val mAdapter: MainAdapter by lazy {
        MainAdapter()
    }

    private val linearLayoutManager: LinearLayoutManager by lazy {
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

            val snapHelper = LinearSnapHelper()
            snapHelper.attachToRecyclerView(rv)

            val timer = Timer()
            timer.schedule(object : TimerTask() {
                override fun run() {
                    Handler(mainLooper).post {
                        if (linearLayoutManager.findLastCompletelyVisibleItemPosition() < mAdapter.itemCount - 1) {
                            linearLayoutManager.smoothScrollToPosition(rv, RecyclerView.State(), linearLayoutManager.findLastCompletelyVisibleItemPosition() + 1)
                        } else {
                            linearLayoutManager.smoothScrollToPosition(rv, RecyclerView.State(), 0)
                        }
                    }
                }
            }, 0, 4000)

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