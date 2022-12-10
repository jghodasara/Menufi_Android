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

class SignupFragment : Fragment() {

    private var databaseClass: DatabaseClass? = null
    private var et_fname: EditText? = null
    private var et_lname: EditText? = null
    private var et_email: EditText? = null
    private var et_password: EditText? = null
    private var et_cpassword: EditText? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_signup, container, false)

        val btnSignup = view.findViewById<Button>(R.id.btnSignUp)
        databaseClass = DatabaseClass(context)
        val loginBtn = view.findViewById<TextView>(R.id.loginText)
        et_fname = view.findViewById<View>(R.id.fName) as EditText
        et_lname = view.findViewById<View>(R.id.lName) as EditText
        et_email = view.findViewById<View>(R.id.email) as EditText
        et_password = view.findViewById<View>(R.id.password) as EditText
        et_cpassword = view.findViewById<View>(R.id.confirmPassword) as EditText
        loginBtn.setOnClickListener {
            view.findNavController().navigate(R.id.action_signupFragment_to_loginFragment)
        }

        btnSignup.setOnClickListener {

            val firstName = et_fname!!.text.toString()
            val lastName = et_lname!!.text.toString()
            val email = et_email!!.text.toString()
            val password = et_password!!.text.toString()
            val confirmPassword = et_cpassword!!.text.toString()
            if (firstName == "" || lastName == "" || email == "" || password == "" || confirmPassword == "") {
                Toast.makeText(context, "All fields are required", Toast.LENGTH_SHORT).show()
            } else {
                if (password == confirmPassword) {
                    val checkEmail: Boolean? = databaseClass!!.CheckEmail(email)
                    if (checkEmail == true) {
                        val insert: Boolean = databaseClass!!.Insert(firstName,lastName,email, password)
                        if (insert) {
                            val prefs: SharedPreferences = requireContext().getSharedPreferences(
                                "com.android.menufi", Context.MODE_PRIVATE
                            )
                            prefs.edit().putString("userEmail", email).apply();
                            prefs.edit().putBoolean("isLoggedIn", true).apply();
                            Toast.makeText(context, "Registered", Toast.LENGTH_SHORT)
                                .show()
                            et_fname!!.setText("")
                            et_lname!!.setText("")
                            et_email!!.setText("")
                            et_password!!.setText("")
                            et_cpassword!!.setText("")
                            val moveToHomeScreen = Intent(
                                view.context,
                                HomeScreen::class.java
                            )
                            startActivity(moveToHomeScreen)
                        }
                    } else {
                        Toast.makeText(
                            context,
                            "Email already registered",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                } else {
                    Toast.makeText(
                        context,
                        "Password does not match",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
        return view
    }
}