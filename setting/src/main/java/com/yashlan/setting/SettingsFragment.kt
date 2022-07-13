/*
 * Created by Muhammad Yashlan Iskandar on 7/13/22, 1:19 PM
 * Last modified 7/13/22, 1:19 PM
 */

package com.yashlan.setting

import android.os.Bundle
import androidx.preference.ListPreference
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import com.yashlan.core.utils.ThemeMode
import com.yashlan.core.utils.ThemeUtils

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