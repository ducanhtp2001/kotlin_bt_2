package com.example.demonavigation.Fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ListView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.demonavigation.Adapter.DishListAdapter
import com.example.demonavigation.Adapter.OderListAdapter
import com.example.demonavigation.Model.FoodAndDrink
import com.example.demonavigation.Model.FoodAndDrinkType
import com.example.demonavigation.R
import com.example.demonavigation.ViewModel.MainActivityViewModel

class OderResultFragment : Fragment() {
    lateinit var btnPrevious: ImageButton
    lateinit var listView: ListView
    lateinit var lstOderResult: ArrayList<FoodAndDrink>
    lateinit var viewModel : MainActivityViewModel
    lateinit var adapter : OderListAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_oder_result, container, false)

        initUI(view)

        btnPrevious?.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_oderResultFragment_to_beverageFragment)
        }?: Toast.makeText(context, "txt null", Toast.LENGTH_SHORT).show()

        viewModel = ViewModelProvider(requireActivity()).get(MainActivityViewModel::class.java)

        lstOderResult = ArrayList()
        lstOderResult = viewModel.listOder.value!!
        adapter = OderListAdapter(requireContext(), R.layout.oder_item, lstOderResult)
        listView.adapter = adapter

        viewModel.listOder.observe(this, Observer {
            lstOderResult = viewModel.listOder.value!!
            adapter.notifyDataSetChanged()
        })

        return view
    }

    private fun initUI(view: View) {
        btnPrevious = view.findViewById(R.id.btnBackToBeverage)
        listView = view.findViewById(R.id.listOderResult)
    }
}