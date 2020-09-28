package com.folbstudio.mobiletechnicalchallange.layout.adview

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.folbstudio.mobiletechnicalchallange.R
import com.folbstudio.mobiletechnicalchallange.databinding.AdCollectionFragmentBinding

class AdCollectionFragment : Fragment() {

    private val viewModel: AdCollectionViewModel by activityViewModels()
    private lateinit var binding: AdCollectionFragmentBinding
    private lateinit var adAdapter: AdsRecyclerViewAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        adAdapter = AdsRecyclerViewAdapter()

        binding = DataBindingUtil.inflate<AdCollectionFragmentBinding>(inflater, R.layout.ad_collection_fragment, container, false)
            .apply {
                vm = viewModel
                lifecycleOwner = viewLifecycleOwner
                with(adsRecyclerView) {
                    layoutManager = LinearLayoutManager(context)
                    adapter = adAdapter
                }
                toggleFavVisibility.setOnClickListener {
                    viewModel.toggleVisibility()
                    if (viewModel.showOnlyFavorites) {
                        viewModel.ads.value?.getFavoritedAds()?.let {
                                adList -> adAdapter.updateAds(adList)
                        }
                    } else {
                        viewModel.ads.value?.items?.let {
                                adList -> adAdapter.updateAds(adList)
                        }
                    }
                }
            }

        viewModel.ads.observe(viewLifecycleOwner, {
            if (it != null) adAdapter.updateAds(it.items)
        })


        return binding.root
    }
}