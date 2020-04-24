package com.example.myapplication.kotlin

import android.text.Editable
import android.text.TextWatcher
import com.example.myapplication.java.EmailValidator
import java.util.regex.Pattern

/**
 * An Email format validator for [android.widget.EditText].
 */
class EmailValidator : TextWatcher{

    /**
     * Email validation pattern.
     */
    val EMAIL_PATTERN = Pattern.compile(
            "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
                    "\\@" +
                    "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
                    "(" +
                    "\\." +
                    "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
                    ")+"
    )

    var isValid = false;

    companion object {
        /**
         * Validates if the given input is a valid email address.
         *
         * @param email The email to validate.
         * @return `true` if the input is a valid email. `false` otherwise.
         */
        fun isValidEmail(email: CharSequence): Boolean {
            return EmailValidator.EMAIL_PATTERN.matcher(email).matches()
        }
    }

    override fun afterTextChanged(editableText: Editable?) {
        isValid = isValidEmail(editableText!!);
    }

    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {/*not implemented*/}

    override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {/*not implemented*/}
}