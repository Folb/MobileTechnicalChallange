package com.folbstudio.mobiletechnicalchallange.layout.splash

import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import com.folbstudio.mobiletechnicalchallange.R
import com.folbstudio.mobiletechnicalchallange.databinding.SplashFragmentBinding
import com.folbstudio.mobiletechnicalchallange.layout.adview.AdCollectionViewModel
import com.folbstudio.mobiletechnicalchallange.utils.navigate

class SplashFragment : Fragment() {

    private val delay: Long = 4000

    private val viewModel: AdCollectionViewModel by activityViewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v = DataBindingUtil.inflate<SplashFragmentBinding>(inflater, R.layout.splash_fragment, container, false)

        // Starts loading data and images
        viewModel.cheat()

        Handler().postDelayed({
            navigate(SplashFragmentDirections.actionSplashFragmentToAdCollectionFragment())
        }, delay)

        return v.root
    }
}