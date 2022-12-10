package com.android.menufi

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper

class MainActivity : AppCompatActivity() {
    private var databaseClass: DatabaseClass? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        databaseClass = DatabaseClass(this)

        Handler(Looper.getMainLooper()).postDelayed({
            val prefs: SharedPreferences = this.getSharedPreferences(
                "com.android.menufi", Context.MODE_PRIVATE
            )
            val isLoggedIn:Boolean = prefs.getBoolean("isLoggedIn",false);
            var moveToHomeScreen: Intent? = null;
            if(isLoggedIn){
                moveToHomeScreen = Intent(
                    this,
                    HomeScreen::class.java
                )
            }else{
                moveToHomeScreen = Intent(
                    this,
                    AuthActivity::class.java
                )
            }
            startActivity(moveToHomeScreen)
            finish()
        }, 3000)
    }
}