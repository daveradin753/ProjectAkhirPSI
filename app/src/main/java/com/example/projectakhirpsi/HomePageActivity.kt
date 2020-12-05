package com.example.projectakhirpsi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.google.firebase.auth.FirebaseAuth

class HomePageActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_page)
//        val docReference = fstore.collection("users").document(userID)
//        docReference.get()
//            .addOnSuccessListener { document ->
//                if (document != null){
//                    Log.d("Ada file", "DocumentSnapshot data: ${document.data}")
//                    tvName.text = document.getString("fullname")
//                    tvEmail.text = document.getString("email")
//                }else {
//                    Log.d("Tidak ada file", "Tidak ditemukan document tersebut")
//                }
//            }
//            .addOnFailureListener { exception ->
//                Log.d("errordb", "Error: ", exception)
//            }
    }
    fun logOut(view: View){
        FirebaseAuth.getInstance().signOut()
        intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        finish()
    }
}