package arsensaruhanyan.com.travelmet

import android.app.Activity
import android.app.ProgressDialog
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_signup.*
import com.google.firebase.auth.FirebaseUser
import android.text.TextUtils
import com.google.firebase.auth.FirebaseAuth

class SignupActivity : AppCompatActivity(), View.OnClickListener {

    private val TAG = "EmailPassword"

    private lateinit var mNameField: EditText
    private lateinit var mEmailField: EditText
    private lateinit var mPasswordField: EditText
    private lateinit var mRepeatPasswordField: EditText

    private var mProgressDialog: ProgressDialog? = null

    private fun showProgressDialog() {
        if (mProgressDialog == null) {
            mProgressDialog = ProgressDialog(this)
            mProgressDialog!!.setCancelable(false)
            mProgressDialog!!.setMessage("Loading...")
        }

        mProgressDialog!!.show()
    }

    private fun hideProgressDialog() {
        if (mProgressDialog != null && mProgressDialog!!.isShowing) {
            mProgressDialog!!.dismiss()
        }
    }

    // [START declare_auth]
    private var mAuth: FirebaseAuth? = null
    // [END declare_auth]

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        // Views
        mNameField = input_name
        mEmailField = input_email
        mPasswordField = input_password
        mRepeatPasswordField = input_repeat_password
        // Buttons
        link_login.setOnClickListener(this)
        btn_signup.setOnClickListener(this)
//        findViewById(R.id.verify_email_button).setOnClickListener(this)

        // [START initialize_auth]
        mAuth = FirebaseAuth.getInstance()
        // [END initialize_auth]
    }

    // [START on_start_check_user]
    public override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = mAuth!!.currentUser
        updateUI(currentUser)
    }
    // [END on_start_check_user]

    private fun createAccount(email: String, password: String) {
        Log.d(TAG, "createAccount:$email")
        if (!validateForm()) {
            return
        }

        showProgressDialog()

        // [START create_user_with_email]
        mAuth!!.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d(TAG, "createUserWithEmail:success")
                        val user = mAuth!!.currentUser
                        goAhead(user)
                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w(TAG, "createUserWithEmail:failure", task.exception)
                        Toast.makeText(this@SignupActivity, "Authentication failed.",
                                Toast.LENGTH_SHORT).show()
                        updateUI(null)
                    }

                    // [START_EXCLUDE]
                    hideProgressDialog()
                    // [END_EXCLUDE]
                }
        // [END create_user_with_email]
    }

    private fun sendEmailVerification(user: FirebaseUser) {
        // Send verification email
        // [START send_email_verification]
        user.sendEmailVerification()
                .addOnCompleteListener(this) { task ->
                    // [START_EXCLUDE]
                    if (task.isSuccessful) {
                        Toast.makeText(this@SignupActivity,
                                "Verification email sent to " + user.email!!,
                                Toast.LENGTH_SHORT).show()
                    } else {
                        Log.e(TAG, "sendEmailVerification", task.exception)
                        Toast.makeText(this@SignupActivity,
                                "Failed to send verification email.",
                                Toast.LENGTH_SHORT).show()
                    }
                    // [END_EXCLUDE]
                }
        // [END send_email_verification]
    }

    private fun validateForm(): Boolean {
        var valid = true

        val email = mEmailField.text.toString()
        if (TextUtils.isEmpty(email)) {
            mEmailField.error = "Required."
            valid = false
        } else {
            mEmailField.error = null
        }

        val password = mPasswordField.text.toString()
        if (TextUtils.isEmpty(password)) {
            mPasswordField.error = "Required."
            valid = false
        } else {
            mPasswordField.error = null
        }

        return valid
    }

    private fun updateUI(user: FirebaseUser?) {
        hideProgressDialog()
    }

    private fun goAhead(user: FirebaseUser?) {
        updateUI(user)
        sendEmailVerification(user!!)
        finish()
    }

    override fun onClick(v: View) {
        val i = v.id
        when (i) {
            R.id.btn_signup -> createAccount(mEmailField.text.toString(), mPasswordField.text.toString())
            R.id.link_login -> finish()
        }
    }
}