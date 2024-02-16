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
import com.example.demonavigation.Model.FoodAndDrink
import com.example.demonavigation.R

class OderListAdapter(val context: Context, val idLayout: Int, var lst: ArrayList<FoodAndDrink>): BaseAdapter()  {
    inner class ViewHolder(view: View) {
        val img: ImageView = view.findViewById(R.id.itemOderImg)
        val name: TextView = view.findViewById(R.id.itemOderName)
    }

    override fun getCount(): Int {
        return lst.size
    }

    override fun getItem(position: Int): Any {
        return lst.get(position)
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

        holder.img.setImageResource(lst.get(position).img)
        holder.name.text = lst.get(position).name

        return view!!
    }
}