package com.android.menufi

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.findNavController


class LoginFragment : Fragment() {

    var et_email: EditText? = null
    var et_password: EditText? = null
    var databaseClass: DatabaseClass? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_login, container, false)
        databaseClass = DatabaseClass(context)
        et_email = view.findViewById<EditText>(R.id.email) as EditText
        et_password = view.findViewById<EditText>(R.id.password) as EditText
        val btnLogin = view.findViewById<Button>(R.id.btnLogin)
        var signupText = view.findViewById<TextView>(R.id.signupText)

        signupText.setOnClickListener {
            view.findNavController().navigate(R.id.action_loginFragment_to_signupFragment)
        }

        btnLogin.setOnClickListener {
            val email = et_email!!.text.toString()
            val password = et_password!!.text.toString()
            val checkLogin = databaseClass!!.CheckLogin(email, password)
            if (checkLogin) {
                val prefs: SharedPreferences = requireContext().getSharedPreferences(
                    "com.android.menufi", Context.MODE_PRIVATE
                )
                prefs.edit().putString("userEmail", email).apply();
                prefs.edit().putBoolean("isLoggedIn", true).apply();
                Toast.makeText(context, "Login Successful", Toast.LENGTH_SHORT).show();
                val moveToHomeScreen = Intent(
                    view.context,
                    HomeScreen::class.java
                )
                startActivity(moveToHomeScreen)
            } else {
                Toast.makeText(
                    context,
                    "Invalid username or password",
                    Toast.LENGTH_SHORT
                ).show()
            }

        }

        return view
    }
}