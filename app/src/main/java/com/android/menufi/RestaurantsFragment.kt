package com.android.menufi

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONArray
import org.json.JSONObject


class RestaurantsFragment : Fragment(),Communicator {

    var transactionList: ArrayList<Restaurants>? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_restaurants, container, false)
        val profileIcon = view.findViewById<ImageView>(R.id.profile)

        profileIcon.setOnClickListener {
            view.findNavController().navigate(R.id.action_restaurantsFragment_to_profileFragment)
        }
        return view
    }

    override fun onViewCreated(itemView: View, savedInstanceState: Bundle?) {
        super.onViewCreated(itemView, savedInstanceState)
        val recyclerView = itemView.findViewById<RecyclerView>(R.id.recyclerView)
        getNearbyData(recyclerView,itemView)
    }

    fun getNearbyData(recyclerView: RecyclerView, itemView:View) {
        val queue = Volley.newRequestQueue(activity)
        val url = "https://75r39.mocklab.io/json/1"

        // Request a string response from the provided URL.
        val stringRequest = StringRequest(
            Request.Method.GET, url,
            Response.Listener<String> { response ->
                println("RESPONSE $response")
                val responseObject = JSONObject(response)
                val array = responseObject.getJSONArray("restaurants")
                transactionList = Restaurants.createTransactionsList(array)
                val adapter = RestaurantsAdapter(transactionList!!,this@RestaurantsFragment)
                recyclerView.adapter = adapter
                recyclerView.layoutManager = GridLayoutManager(itemView.context,2)
            },
            Response.ErrorListener {
                println("ERRORRR $it")

            })
        queue.add(stringRequest)
    }

    override fun passData(position: Int) {
        val bundle = Bundle()
        bundle.putInt("input_pos", position)

        val transaction = this.parentFragmentManager.beginTransaction()
        val frag2 = MenuFragment()
        frag2.arguments = bundle

        transaction.replace(R.id.main, frag2)
        transaction.addToBackStack(null)
        transaction.commit()
    }


}