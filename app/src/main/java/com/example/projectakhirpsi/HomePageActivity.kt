package com.example.projectakhirpsi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.google.firebase.auth.FirebaseAuth

class HomePageActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_page)
    }
    fun logOut(view: View){
        FirebaseAuth.getInstance().signOut()
        intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        finish()
    }
}