package com.example.demonavigation.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import com.example.demonavigation.Model.DishItem
import com.example.demonavigation.Model.FoodAndDrinkType
import com.example.demonavigation.R
import com.example.demonavigation.ViewModel.MainActivityViewModel

class DishListAdapter(
    val context: Context,
    val idLayout: Int,
    val viewModel: MainActivityViewModel,
    val type: FoodAndDrinkType
    ): BaseAdapter() {
    inner class ViewHolder(view: View) {
        val img: ImageView = view.findViewById(R.id.itemImg)
        val name: TextView = view.findViewById(R.id.itemName)
        val checkBox: CheckBox = view.findViewById(R.id.itemCheckbox)
    }

    override fun getCount(): Int {
        if(type == FoodAndDrinkType.Dessert) {
            return viewModel.listDesserts.value!!.size
        } else if(type == FoodAndDrinkType.MainDishes) {
            return viewModel.listMainDishes.value!!.size
        } else return viewModel.listBeverages.value!!.size
    }

    override fun getItem(position: Int): Any {
        if(type == FoodAndDrinkType.Dessert) {
            return viewModel.listDesserts.value!!.get(position)
        } else if(type == FoodAndDrinkType.MainDishes) {
            return viewModel.listMainDishes.value!!.get(position)
        } else return viewModel.listBeverages.value!!.get(position)
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var holder: ViewHolder
        var view: View?

        if(convertView == null) {
            val inflater = LayoutInflater.from(context)
            view = inflater.inflate(idLayout, null)
            holder = ViewHolder(view)
            view.setTag(holder)
        } else {
            view = convertView
            holder = view.tag as ViewHolder
        }

        var lst: ArrayList<DishItem>
        if(type == FoodAndDrinkType.Dessert) {
            lst = viewModel.listDesserts.value!!
        } else if(type == FoodAndDrinkType.MainDishes) {
            lst = viewModel.listMainDishes.value!!
        } else lst = viewModel.listBeverages.value!!

        holder.img.setImageResource(lst.get(position).item.img)
        holder.name.text = lst.get(position).item.name
        if(lst.get(position).isCheck) holder.checkBox.isChecked = true

        holder.checkBox.setOnClickListener {
            lst.get(position).isCheck = !lst.get(position).isCheck
//            Log.d("adapter", "item: ${lst.get(position).item.name} isCheck = ${lst.get(position).isCheck}")

            if(type == FoodAndDrinkType.Dessert) {
                viewModel.listDesserts.value = lst
            } else if(type == FoodAndDrinkType.MainDishes) {
                viewModel.listMainDishes.value = lst
            } else viewModel.listBeverages.value = lst

            notifyDataSetChanged()
        }

        return view!!
    }
}