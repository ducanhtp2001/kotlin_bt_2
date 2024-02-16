package com.example.demonavigation.ViewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.demonavigation.Model.*
import com.example.demonavigation.R

class MainActivityViewModel: ViewModel() {
    lateinit var listDesserts: MutableLiveData<ArrayList<DishItem>>
    lateinit var listMainDishes: MutableLiveData<ArrayList<DishItem>>
    lateinit var listBeverages: MutableLiveData<ArrayList<DishItem>>
    lateinit var listOder: MutableLiveData<ArrayList<FoodAndDrink>>

    init {
        listOder = MutableLiveData()
        listOder.value = ArrayList()
        listDesserts = MutableLiveData()
        listMainDishes = MutableLiveData()
        listBeverages = MutableLiveData()

        var tmpLst = ArrayList<DishItem>()

        tmpLst.add(DishItem(Dessert("Socola Cake", R.drawable.socola_cake), false))
        tmpLst.add(DishItem(Dessert("Scream cake", R.drawable.scream_cake), false))
        tmpLst.add(DishItem(Dessert("Strawberry Cake", R.drawable.strawberry_cake), false))
        listDesserts.value = tmpLst

        var tmpLst1 = ArrayList<DishItem>()
        tmpLst1.add(DishItem(MainDishes("Chiken", R.drawable.chicken), false))
        tmpLst1.add(DishItem(MainDishes("Rice and egg", R.drawable.rice_and_egg), false))
        tmpLst1.add(DishItem(MainDishes("Fish", R.drawable.fish), false))
        listMainDishes.value = tmpLst1

        var tmpLst2 = ArrayList<DishItem>()
        tmpLst2.add(DishItem(Beverage("Soda", R.drawable.soda), false))
        tmpLst2.add(DishItem(Beverage("Cocacola", R.drawable.cocacola), false))
        tmpLst2.add(DishItem(Beverage("Lemon juice", R.drawable.lemon_juice), false))
        listBeverages.value = tmpLst2
    }

    fun updateOderLst(lst: ArrayList<DishItem>) {
        var tmpLst = ArrayList<FoodAndDrink>()
        tmpLst.addAll(listOder.value!!)
        for (item in lst) {
            if(item.isCheck) {
                if(!tmpLst.contains(item.item)) tmpLst.add(item.item)
            }
            else tmpLst.remove(item.item)
        }
        listOder.value = tmpLst
    }

    override fun onCleared() {
        super.onCleared()
    }
}