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


class MenuFragment : Fragment() {

    var inputPos: Int? = null
    var menuList: ArrayList<Menu>? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_menu, container, false)
        val backButton = view.findViewById<ImageView>(R.id.back)
        inputPos = arguments?.getInt("input_pos")

        println("PASSED VALUE $inputPos")

        backButton.setOnClickListener {
            val transaction = this.parentFragmentManager.beginTransaction()
            val frag2 = RestaurantsFragment()

            transaction.replace(R.id.main, frag2)
            transaction.addToBackStack(null)
            transaction.commit()
        }
        return view
    }

    override fun onViewCreated(itemView: View, savedInstanceState: Bundle?) {
        super.onViewCreated(itemView, savedInstanceState)
        val recyclerView = itemView.findViewById<RecyclerView>(R.id.recyclerView)
        getMenu(recyclerView,itemView)
    }

    fun getMenu(recyclerView: RecyclerView, itemView:View) {
        val queue = Volley.newRequestQueue(activity)
        var url = ""
        url = when(inputPos){
            0 -> "https://75r39.mocklab.io/thing/8"
            1 -> "https://75r39.mocklab.io/thing/9"
            2 -> "https://75r39.mocklab.io/thing/10"
            3 -> "https://75r39.mocklab.io/thing/11"
            4 -> "https://75r39.mocklab.io/thing/12"
            else -> "https://75r39.mocklab.io/thing/8"
        }

        // Request a string response from the provided URL.
        val stringRequest = StringRequest(
            Request.Method.GET, url,
            Response.Listener<String> { response ->
                println("RESPONSE $response")
                val responseObject = JSONObject(response)
                val array = responseObject.getJSONArray("menu")
                println("ARRAYYY $array")
                menuList = Menu.createMenuList(responseObject.getJSONArray("menu"))
                val adapter = MenuAdapter(menuList!!)
                recyclerView.adapter = adapter
                recyclerView.layoutManager = LinearLayoutManager(itemView.context)
            },
            Response.ErrorListener {
                println("ERRORRR $it")

            })
        queue.add(stringRequest)
    }

}