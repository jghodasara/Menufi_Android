package com.android.menufi

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView

class MenuAdapter(private val menuItems: List<Menu>):
    RecyclerView.Adapter<MenuAdapter.ViewHolder>() {
    private var context: Context? = null
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        //Here we are getting each view from the row of recyclerview

        val menuImage = itemView.findViewById<ImageView>(R.id.menu_image)
        val menuName = itemView.findViewById<TextView>(R.id.menu_name)
        val menuPrice = itemView.findViewById<TextView>(R.id.menu_price)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuAdapter.ViewHolder {
        context = parent.context
        val inflater = LayoutInflater.from(context)
        val contactView = inflater.inflate(R.layout.item_menu, parent, false)
        return ViewHolder(contactView)
    }

    override fun onBindViewHolder(viewHolder: MenuAdapter.ViewHolder, position: Int) {
        val singleItem: Menu = menuItems.get(position)  // Here we are getting the data for single item from the transactionsList
        val nameOfItem = viewHolder.menuName
        val imageOfItem = viewHolder.menuImage
        val priceOfItem = viewHolder.menuPrice
        nameOfItem.text = singleItem.menuName
        priceOfItem.text = singleItem.menuPrice
        if(singleItem.menuImage == "eggB"){
            imageOfItem.setImageDrawable(ContextCompat.getDrawable(context!!, R.drawable.eggburger))
        }else if(singleItem.menuImage == "chickenB"){
            imageOfItem.setImageDrawable(ContextCompat.getDrawable(context!!, R.drawable.spicychicken))
        }else if(singleItem.menuImage == "fries"){
            imageOfItem.setImageDrawable(ContextCompat.getDrawable(context!!, R.drawable.fries))
        }else if(singleItem.menuImage == "coffee"){
            imageOfItem.setImageDrawable(ContextCompat.getDrawable(context!!, R.drawable.coffee))
        }else if(singleItem.menuImage == "pfries"){
            imageOfItem.setImageDrawable(ContextCompat.getDrawable(context!!, R.drawable.fries))
        }else if(singleItem.menuImage == "nachos"){
            imageOfItem.setImageDrawable(ContextCompat.getDrawable(context!!, R.drawable.nachos))
        }else if(singleItem.menuImage == "burrito"){
            imageOfItem.setImageDrawable(ContextCompat.getDrawable(context!!, R.drawable.burrito))
        }else if(singleItem.menuImage == "tacos"){
            imageOfItem.setImageDrawable(ContextCompat.getDrawable(context!!, R.drawable.tacos))
        }else if(singleItem.menuImage == "soup"){
            imageOfItem.setImageDrawable(ContextCompat.getDrawable(context!!, R.drawable.soup))
        }else if(singleItem.menuImage == "ques"){
            imageOfItem.setImageDrawable(ContextCompat.getDrawable(context!!, R.drawable.ques))
        }else if(singleItem.menuImage == "hotdog"){
            imageOfItem.setImageDrawable(ContextCompat.getDrawable(context!!, R.drawable.hotdog))
        }else if(singleItem.menuImage == "onionRings"){
            imageOfItem.setImageDrawable(ContextCompat.getDrawable(context!!, R.drawable.onionrings))
        }else if(singleItem.menuImage == "frings"){
            imageOfItem.setImageDrawable(ContextCompat.getDrawable(context!!, R.drawable.frings))
        }else if(singleItem.menuImage == "nuggets"){
            imageOfItem.setImageDrawable(ContextCompat.getDrawable(context!!, R.drawable.nugget))
        }else if(singleItem.menuImage == "rotiWrap"){
            imageOfItem.setImageDrawable(ContextCompat.getDrawable(context!!, R.drawable.rotiwrap))
        }else if(singleItem.menuImage == "samosaChat"){
            imageOfItem.setImageDrawable(ContextCompat.getDrawable(context!!, R.drawable.samosachat))
        }else if(singleItem.menuImage == "dessert"){
            imageOfItem.setImageDrawable(ContextCompat.getDrawable(context!!, R.drawable.dessert))
        }else{
            imageOfItem.setImageDrawable(ContextCompat.getDrawable(context!!, R.drawable.splash))
        }
    }

    override fun getItemCount(): Int {
        return menuItems.size
    }
}