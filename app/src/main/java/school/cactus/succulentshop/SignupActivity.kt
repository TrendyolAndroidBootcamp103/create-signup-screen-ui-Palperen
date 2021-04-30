package school.cactus.succulentshop

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputLayout
import school.cactus.succulentshop.databinding.ActivitySignupBinding

class SignupActivity :AppCompatActivity() {
    private lateinit var binding: ActivitySignupBinding

    private val signupEmailValidator = SignupEmailValidator()
    private val signupIdentifierValidator = SignupIdentifierValidator()
    private val signupPasswordValidator = SignupPasswordValidator()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title = getString(R.string.sign_up)



        binding.apply {
            signUpButton.setOnClickListener {
                signupEmailInputLayout.validate()
                signupUsernameInputLayout.validate()
                signupPasswordInputLayout.validate()
            }

            haveAnAccountButton.setOnClickListener {
                navigateToLoginActivity()
            }
        }

    }

    private fun navigateToLoginActivity() {
        val intent = Intent(this,LoginActivity::class.java)
        startActivity(intent)
    }

    private fun TextInputLayout.validate() {
        val errorMessage = validator().validate(editText!!.text.toString())
        error = errorMessage?.resolveAsString()
        isErrorEnabled = errorMessage != null
    }

    private fun Int.resolveAsString() = getString(this)

    private fun TextInputLayout.validator() = when (this) {
        binding.signupEmailInputLayout -> signupEmailValidator
        binding.signupUsernameInputLayout -> signupIdentifierValidator
        binding.signupPasswordInputLayout -> signupPasswordValidator
        else -> throw IllegalArgumentException("Cannot find any validator for the given TextInputLayout")
    }
}