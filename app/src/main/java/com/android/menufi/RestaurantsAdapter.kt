package com.android.menufi

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView

class RestaurantsAdapter(private val transaction: List<Restaurants>,private val listener: Communicator):RecyclerView.Adapter<RestaurantsAdapter.ViewHolder>() {

    // This class is the adapter class that we are setting in the RecyclerView to handle data for each row.
    private var context: Context? = null

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        //Here we are getting each view from the row of recyclerview

        val transactionImage = itemView.findViewById<ImageView>(R.id.restaurant_image)
        val transactionName = itemView.findViewById<TextView>(R.id.restaurant_name)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RestaurantsAdapter.ViewHolder {
        context = parent.context
        val inflater = LayoutInflater.from(context)
        val contactView = inflater.inflate(R.layout.item_restaurants, parent, false)
        return ViewHolder(contactView)
    }

    override fun onBindViewHolder(viewHolder: RestaurantsAdapter.ViewHolder, position: Int) {
        val singleTransaction: Restaurants = transaction.get(position)  // Here we are getting the data for single item from the transactionsList
        val transName = viewHolder.transactionName
        val transImage = viewHolder.transactionImage
        transName.text = singleTransaction.transName
        if(singleTransaction.transImage == "mc"){
            transImage.setImageDrawable(ContextCompat.getDrawable(context!!, R.drawable.mcd))
        }else if(singleTransaction.transImage == "mb"){
            transImage.setImageDrawable(ContextCompat.getDrawable(context!!, R.drawable.muchoburrito))
        }else if(singleTransaction.transImage == "harveys"){
            transImage.setImageDrawable(ContextCompat.getDrawable(context!!, R.drawable.harveys))
        }else if(singleTransaction.transImage == "wendys"){
            transImage.setImageDrawable(ContextCompat.getDrawable(context!!, R.drawable.wendys))
        }else if(singleTransaction.transImage == "aw"){
            transImage.setImageDrawable(ContextCompat.getDrawable(context!!, R.drawable.aw))
        }

        viewHolder.itemView.setOnClickListener {
        listener.passData(position)
        }
    }

    override fun getItemCount(): Int {
        return transaction.size
    }
}