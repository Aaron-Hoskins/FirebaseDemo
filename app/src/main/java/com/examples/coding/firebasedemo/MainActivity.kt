package com.examples.coding.firebasedemo

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    var mAuth : FirebaseAuth? = null
    var currentUser : FirebaseUser? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mAuth = FirebaseAuth.getInstance()
    }

    override fun onStart() {
        super.onStart()
        currentUser = mAuth?.currentUser
        updateUI()
    }

    fun onClick(view: View) {
        when(view.id) {
            R.id.btnLogIn -> logInUser()
            R.id.btnLogOut -> logOutUser()
            R.id.btnSignUpUser -> signUpUser()
        }
    }

    private fun updateUI() {
        if(currentUser != null) {
                tvCurrentUser.text = currentUser!!.email
        } else {
            tvCurrentUser.text = "NO USER LOGGED IN"
        }

    }

    private fun logInUser(){
        val email = etUserEmail.text.toString()
        val password = etPassword.text.toString()
        mAuth!!.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(
                this
            ) { task ->
                if (task.isSuccessful) { // Sign in success, update UI with the signed-in user's information
                    Log.d("TAG", "signInWithEmail:success")
                    currentUser = mAuth?.currentUser
                    updateUI()
                } else { // If sign in fails, display a message to the user.
                    Log.w(
                        "TAG",
                        "signInWithEmail:failure",
                        task.exception
                    )
                    Toast.makeText(
                        this@MainActivity, "Authentication failed.",
                        Toast.LENGTH_SHORT
                    ).show()
                    updateUI()
                }
            }
    }
    private fun logOutUser(){
        mAuth?.signOut()
        currentUser = mAuth?.currentUser
        updateUI()
    }
    private fun signUpUser(){
        val email = etUserEmail.text.toString()
        val password = etPassword.text.toString()
        mAuth!!.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(
                this
            ) { task ->
                if (task.isSuccessful) { // Sign in success, update UI with the signed-in user's information
                    Log.d("TAG", "createUserWithEmail:success")
                    currentUser = mAuth?.currentUser
                    updateUI()
                } else { // If sign in fails, display a message to the user.
                    Log.w(
                        "TAG",
                        "createUserWithEmail:failure",
                        task.exception
                    )
                    Toast.makeText(
                        this@MainActivity, "Authentication failed.",
                        Toast.LENGTH_SHORT
                    ).show()
                    updateUI()
                }

            }
    }
}
