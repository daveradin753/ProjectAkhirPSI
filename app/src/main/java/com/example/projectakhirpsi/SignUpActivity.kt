package com.example.projectakhirpsi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import java.util.*
import kotlin.collections.HashMap

class SignUpActivity : AppCompatActivity() {

    private lateinit var etFullName: EditText
    private lateinit var etEmail: EditText
    private lateinit var etPassword: EditText
    private lateinit var etPasswordConfirm: EditText
    private lateinit var btnSignup: Button
    private lateinit var fauth: FirebaseAuth
    private lateinit var fstore: FirebaseFirestore
    private lateinit var progressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        etFullName = findViewById(R.id.et_Name)
        etEmail = findViewById(R.id.etEmailRegister)
        etPassword = findViewById(R.id.etPasswordRegister)
        etPasswordConfirm = findViewById(R.id.etConfirmPasswordRegister)
        btnSignup = findViewById(R.id.btn_Signup)

        val email: String = etEmail.text.toString().trim()
        val password: String = etPassword.text.toString().trim()
        val passwordConfirm: String = etPasswordConfirm.text.toString().trim()
        val fullname = etFullName.text.toString()
        val users: MutableMap<Any, Any> = HashMap()
        fstore = FirebaseFirestore.getInstance()

        fauth = Firebase.auth
        progressBar = findViewById(R.id.progressBarSignup)

        btnSignup.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View?) {

                //menampilkan error
                if (TextUtils.isEmpty(email)) {
                    etEmail.error = "Email is required!"
                    return
                }
                if (TextUtils.isEmpty(password)) {
                    etPassword.error = "Password is required!"
                    return
                }
                if (password.length < 8) {
                    etPassword.error = "Password must be more than 8 characters!"
                    return
                }
                if (password != passwordConfirm) {
                    etPasswordConfirm.error = "Values do not match with the first one!"
                    return
                }
                progressBar.visibility = View.VISIBLE

                //masukan email dan password ke dalam Firebase
                fauth.createUserWithEmailAndPassword(email, password).addOnCompleteListener{ task ->
                        progressBar.visibility = View.INVISIBLE
                        if (task.isSuccessful) {
                            Toast.makeText(this@SignUpActivity, "User Created", Toast.LENGTH_SHORT).show()
                            users["fullname"] = fullname
                            users["email"] = email
                            intent = Intent(this@SignUpActivity, HomePageActivity::class.java)
                            startActivity(intent)
                        } else {
                            Toast.makeText(this@SignUpActivity, "Error! " + task.exception.toString(), Toast.LENGTH_SHORT).show()
                        }

                    }
            }

        })
    }


}