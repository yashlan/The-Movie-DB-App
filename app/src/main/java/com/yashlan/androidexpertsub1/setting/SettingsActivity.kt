/*
 * Created by Muhammad Yashlan Iskandar on 6/30/22, 9:47 PM
 * Last modified 6/30/22, 9:47 PM
 */

package com.yashlan.androidexpertsub1.setting

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.preference.ListPreference
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import com.yashlan.androidexpertsub1.R
import com.yashlan.core.utils.ThemeMode
import com.yashlan.core.utils.ThemeUtils

class SettingsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.settings_activity)

        ThemeUtils(this).getTheme()

        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.settings, SettingsFragment())
                .commit()
        }
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        finish()
        return super.onOptionsItemSelected(item)
    }

    class SettingsFragment : PreferenceFragmentCompat() {
        override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
            setPreferencesFromResource(R.xml.root_preferences, rootKey)

            val themePref = findPreference<ListPreference>(getString(R.string.pref_key_dark))
            themePref?.onPreferenceChangeListener =
                Preference.OnPreferenceChangeListener { preference, newValue ->
                    if (preference is ListPreference) {
                        when (newValue as String) {
                            getString(R.string.pref_dark_follow_system) -> {
                                ThemeUtils(requireActivity()).updateTheme(ThemeMode.FOLLOW_SYSTEM.value)
                            }

                            getString(R.string.pref_dark_on) -> {
                                ThemeUtils(requireActivity()).updateTheme(ThemeMode.DARK_MODE_ON.value)
                            }

                            getString(R.string.pref_dark_off) -> {
                                ThemeUtils(requireActivity()).updateTheme(ThemeMode.DARK_MODE_OFF.value)
                            }
                        }
                    }
                    true
                }
        }
    }
}