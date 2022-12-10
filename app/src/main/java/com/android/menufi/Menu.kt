package com.android.menufi

import org.json.JSONArray

class Menu(var menuImage: String, var menuName: String, var menuPrice:String) {
    // This class is used to create the list of Transactions that we are getting from the API
    companion object {
        fun createMenuList(numContacts: JSONArray): ArrayList<Menu> {
            val transactions = ArrayList<Menu>()
            for (i in 0 until numContacts.length()) {
                val item = numContacts.getJSONObject(i)
                val name = item.get("name").toString()
                val transImage = item.get("type").toString()
                val price = item.get("price").toString()
                transactions.add(Menu(transImage,name,price))
            }
            return transactions
        }
    }

}