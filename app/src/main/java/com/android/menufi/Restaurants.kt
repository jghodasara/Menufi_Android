package com.android.menufi

import org.json.JSONArray

class Restaurants(var transImage: String, var transName: String) {
    // This class is used to create the list of Transactions that we are getting from the API
    companion object {
        fun createTransactionsList(numContacts: JSONArray): ArrayList<Restaurants> {
            val transactions = ArrayList<Restaurants>()
            for (i in 0 until numContacts.length()) {
                val item = numContacts.getJSONObject(i)
                val name = item.get("name").toString()
                val transImage = item.get("type").toString()
                transactions.add(Restaurants(transImage,name))
            }
            return transactions
        }
    }

}