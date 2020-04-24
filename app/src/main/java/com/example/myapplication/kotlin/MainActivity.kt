package com.example.myapplication.kotlin

import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.preference.PreferenceManager
import com.example.myapplication.R
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : Activity() {

    // Logger for this class.
    private val TAG = "MainActivity";

    private val emailValidator = EmailValidator();

    // The helper that manages writing to SharedPreferences.
    private lateinit var sharedPreferencesHelper: SharedPreferencesHelper;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main);

        // Setup field validators.
        emailInput.addTextChangedListener(emailValidator);

        // Instantiate a SharedPreferencesHelper.
        sharedPreferencesHelper = SharedPreferencesHelper(PreferenceManager.getDefaultSharedPreferences(this));

        // Fill input fields from data retrieved from the SharedPreferences.
        populateUi();
    }

    /**
     * Initialize all fields from the personal info saved in the SharedPreferences.
     */
    private fun populateUi() {
        val sharedPreferenceEntry = sharedPreferencesHelper.getPersonalInfo();
        
        userNameInput.setText(sharedPreferenceEntry.name);
        val dateOfBirth = sharedPreferenceEntry.dateOfBirth;
        dateOfBirthInput.init(dateOfBirth.get(Calendar.YEAR), dateOfBirth.get(Calendar.MONTH), dateOfBirth.get(Calendar.DAY_OF_MONTH), null);
        emailInput.setText(sharedPreferenceEntry.email);
    }

    private fun onSaveClick(view: View?) {
        // Don't save if the fields do not validate.
        if (!emailValidator.isValid) {
            emailInput.setError("Invalid email")
            Log.w(TAG, "Not saving personal information: Invalid email");
            return;
        }

        // Get the text from the input fields.
        val name = userNameInput.text.toString();
        val dateOfBirth = Calendar.getInstance();
        dateOfBirth.set(dateOfBirthInput.year, dateOfBirthInput.month, dateOfBirthInput.dayOfMonth);
        val email = emailInput.text.toString();

        // Create a Setting model class to persist.
        val sharedPreferenceEntry = SharedPreferenceEntry(name, dateOfBirth, email);

        // Persist the personal information.
        if (sharedPreferencesHelper.savePersonalInfo(sharedPreferenceEntry)) {
            Toast.makeText(this, "Personal information saved", Toast.LENGTH_LONG).show();
            Log.i(TAG, "Personal information saved.");
        } else {
            Log.e(TAG, "Failed to write personal information to SharedPreferences")
        }
    }

    /**
     * Called when the "Revert" button is clicked.
     */
    fun onRevertClick(view: View?) {
        populateUi();
        Toast.makeText(this, "Personal information reverted", Toast.LENGTH_LONG).show();
        Log.i(TAG, "Personal information reverted");
    }


}