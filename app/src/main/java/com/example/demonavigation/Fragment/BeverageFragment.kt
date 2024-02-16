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
import com.example.demonavigation.Model.DishItem
import com.example.demonavigation.Model.FoodAndDrinkType
import com.example.demonavigation.R
import com.example.demonavigation.ViewModel.MainActivityViewModel

class BeverageFragment : Fragment() {
    lateinit var btnNext: ImageButton
    lateinit var btnPrevious: ImageButton
    lateinit var listView: ListView
    lateinit var viewModel : MainActivityViewModel
    lateinit var adapter : DishListAdapter
    lateinit var lstBeverages: List<DishItem>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_beverage, container, false)

        initUI(view)

        btnNext?.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_beverageFragment_to_oderResultFragment)
        }?: Toast.makeText(context, "txt null", Toast.LENGTH_SHORT).show()

        btnPrevious?.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_beverageFragment_to_mainDishesFragment)
        }?: Toast.makeText(context, "txt null", Toast.LENGTH_SHORT).show()

        viewModel = ViewModelProvider(requireActivity()).get(MainActivityViewModel::class.java)

        lstBeverages = ArrayList()
        lstBeverages = viewModel.listBeverages.value!!
        adapter = DishListAdapter(requireContext(), R.layout.dish_item, viewModel, FoodAndDrinkType.Beverage)
        listView.adapter = adapter

        viewModel.listBeverages.observe(this, Observer {
            viewModel.updateOderLst(viewModel.listBeverages.value!!)
            Log.d("adapter", "oder: ${viewModel.listOder.value!!.size}")
            adapter.notifyDataSetChanged()
        })

        return view
    }

    private fun initUI(view: View) {
        btnNext = view.findViewById(R.id.btnToOderResult)
        btnPrevious = view.findViewById(R.id.btnBackToMainDishes)
        listView = view.findViewById(R.id.listBeverage)
    }
}