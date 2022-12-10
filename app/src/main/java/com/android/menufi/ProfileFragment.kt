package com.android.menufi

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.navigation.findNavController


class ProfileFragment : Fragment() {


    var databaseClass: DatabaseClass? = null
    var emailText = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_profile, container, false)

        val backButton = view.findViewById<ImageView>(R.id.back)

        val deleteAccount = view.findViewById<Button>(R.id.delete)
        val saveButton = view.findViewById<TextView>(R.id.save)
        val logOutButton = view.findViewById<Button>(R.id.button)

        val prefs: SharedPreferences = requireContext().getSharedPreferences(
            "com.android.menufi", Context.MODE_PRIVATE
        )
        databaseClass = DatabaseClass(context)
        val email:String = prefs.getString("userEmail","").toString();

        val cursor = databaseClass!!.getDetails(email);

        val fName = cursor.getString(1)
        val lName = cursor.getString(2)
        val resultEmail = cursor.getString(3)

        val firstName = view.findViewById<EditText>(R.id.firstName)
        val lastName = view.findViewById<EditText>(R.id.lastName)
        val emailView = view.findViewById<TextView>(R.id.email)

        firstName.setText("$fName")
        lastName.setText("$lName")
        emailView.text = "$resultEmail"

        emailText = resultEmail

        backButton.setOnClickListener {
            view.findNavController().navigate(R.id.action_profileFragment_to_restaurantsFragment)
        }

        logOutButton.setOnClickListener {
            val prefs: SharedPreferences = requireContext().getSharedPreferences(
                "com.android.menufi", Context.MODE_PRIVATE
            )
            prefs.edit().putBoolean("isLoggedIn", false).apply();
            Toast.makeText(context, "Logout Successful", Toast.LENGTH_SHORT).show();
            val moveToLogin = Intent(
                view.context,
                AuthActivity::class.java
            )
            startActivity(moveToLogin)
        }

        saveButton.setOnClickListener {
            if (firstName.text.toString() == "" || lastName.text.toString() == "") {
                Toast.makeText(context, "Field can't be Empty", Toast.LENGTH_SHORT).show()
            } else {
                val updateAccount = databaseClass!!.updateDetails(firstName.text.toString(),lastName.text.toString())
                if(updateAccount){
                    Toast.makeText(context, "Profile updated successfully", Toast.LENGTH_SHORT).show();
                }
            }

        }

        deleteAccount.setOnClickListener {
            val deleteAccount = databaseClass!!.deleteAccount(emailText)
            if(deleteAccount){
                val prefs: SharedPreferences = requireContext().getSharedPreferences(
                    "com.android.menufi", Context.MODE_PRIVATE
                )
                prefs.edit().putBoolean("isLoggedIn", false).apply();
                Toast.makeText(context, "Account Deleted", Toast.LENGTH_SHORT).show();
                val moveToLogin = Intent(
                    view.context,
                    AuthActivity::class.java
                )
                startActivity(moveToLogin)
            }
        }

        return view
    }


}