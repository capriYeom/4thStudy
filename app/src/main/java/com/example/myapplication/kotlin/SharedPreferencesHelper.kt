package com.example.myapplication.kotlin

import android.content.SharedPreferences
import java.util.*

/**
 *  Helper class to manage access to [SharedPreferences].
 *
 *  @constructor Created new [SharedPreferences]. That will be used in this DAO.
 */
class SharedPreferencesHelper(private val sharedPreferences: SharedPreferences) {

    // Keys for saving values in SharedPreferences.
    val KEY_NAME = "key_name";
    val KEY_DOB = "key_dob_millis";
    val KEY_EMAIL = "key_email";

    /**
     * Saves the given [SharedPreferenceEntry] that contains the user`s settings to [SharedPreferences].
     *
     * @param sharedPreferenceEntry contains data to save to [SharedPreferences].
     * @return `true` if writing to [SharedPreferences] succeeded. `false` otherwise.
     */
    fun savePersonalInfo(sharedPreferenceEntry: SharedPreferenceEntry) : Boolean {
        val editor = sharedPreferences.edit();
        editor.putString(KEY_NAME, sharedPreferenceEntry.name);
        editor.putLong(KEY_DOB, sharedPreferenceEntry.dateOfBirth.timeInMillis);
        editor.putString(KEY_EMAIL, sharedPreferenceEntry.email);

        // Commit changes to SharedPreferences.
        return editor.commit();
    }

    /**
     * Retrieves the [SharedPreferenceEntry] containing the user`s personal information from [SharedPreferences].
     *
     * @return the Retrieved [SharedPreferenceEntry]
     */
    fun getPersonalInfo() : SharedPreferenceEntry {
        val name = sharedPreferences.getString(KEY_NAME, "");
        val dobMillis = sharedPreferences.getLong(KEY_DOB, Calendar.getInstance().timeInMillis);
        val dateOfBirth = Calendar.getInstance();
        dateOfBirth.timeInMillis = dobMillis;
        val email = sharedPreferences.getString(KEY_EMAIL, "");

        // Create and fill a SharedPreferenceEntry model object.
        return SharedPreferenceEntry(name!!, dateOfBirth, email!!);
    }
}