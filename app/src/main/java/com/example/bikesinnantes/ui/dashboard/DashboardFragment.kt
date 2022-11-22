package com.example.bikesinnantes.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bikesinnantes.adapter.ParkingAdapter
import com.example.bikesinnantes.api.ParkingApi
import com.example.bikesinnantes.api.RetrofitHelper
import com.example.bikesinnantes.databinding.FragmentDashboardBinding
import com.example.bikesinnantes.model.allParkings
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class DashboardFragment : Fragment() {

    private var _binding: FragmentDashboardBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val dashboardViewModel =
            ViewModelProvider(this).get(DashboardViewModel::class.java)

        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val recyclerView2 = binding.recylclerView2
        var progressBarParkings = binding.progressBarParkings

        dashboardViewModel.parkings.observe(viewLifecycleOwner) {
            recyclerView2.layoutManager = LinearLayoutManager(requireContext())
            recyclerView2.adapter = ParkingAdapter(it, requireContext())
            progressBarParkings.visibility = View.GONE
            allParkings = it
        }

        val parkingApi = RetrofitHelper().getInstance().create(ParkingApi::class.java)
        GlobalScope.launch{
            val result = parkingApi.getParking()
            dashboardViewModel.parkings.postValue(result.body())
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}