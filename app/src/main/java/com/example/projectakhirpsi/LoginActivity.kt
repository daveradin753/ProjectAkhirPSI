package com.example.projectakhirpsi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.*
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_sign_up.*

class LoginActivity : AppCompatActivity() {

    private lateinit var etEmail: EditText
    private lateinit var etPassword: EditText
    private lateinit var btnSignin: Button
    private lateinit var auth: FirebaseAuth
    private lateinit var progressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        etEmail = findViewById(R.id.et_Email)
        etPassword = findViewById(R.id.et_Password)
        btnSignin = findViewById(R.id.btn_Signin)
        auth = FirebaseAuth.getInstance()
        progressBar = findViewById(R.id.progressBarSignin)

        //mengecek apakah sudah pernah login atau belum
        if (auth.currentUser != null){
            intent = Intent(this, HomePageActivity::class.java)
            startActivity(intent)
        }

        btnSignin.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                var email: String = etEmail.text.toString().trim()
                var password: String = etPassword.text.toString().trim()

                if (TextUtils.isEmpty(email)){
                    etEmail.error = "Email is required!"
                    return
                }
                if (TextUtils.isEmpty(password)){
                    etPassword.error = "Password is required!"
                    return
                }
                if (password.length < 8){
                    etPassword.error = "Password must be more than 8 characters!"
                    return
                }
                progressBar.visibility = View.VISIBLE

                //melakukan validasi user
                auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(
                    OnCompleteListener { task ->
                        progressBar.visibility = View.INVISIBLE
                        if (task.isSuccessful){
                            Toast.makeText(this@LoginActivity, "Logged In Success", Toast.LENGTH_SHORT).show()
                            intent = Intent(this@LoginActivity, HomePageActivity::class.java)
                            startActivity(intent)
                        }
                        else{
                            Toast.makeText(this@LoginActivity, "Error! " + task.exception.toString(), Toast.LENGTH_SHORT).show()
                        }
                    })
            }

        })

        findViewById<TextView>(R.id.tv_Signup).setOnClickListener {
            intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }
    }

}