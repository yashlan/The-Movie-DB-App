/*
 * Created by Muhammad Yashlan Iskandar on 7/13/22, 12:20 PM
 * Last modified 7/10/22, 10:31 PM
 */

/*
 * Created by Muhammad Yashlan Iskandar on 6/30/22, 9:47 PM
 * Last modified 6/30/22, 9:47 PM
 */

package com.yashlan.setting

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.preference.ListPreference
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
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
        supportActionBar?.title = getString(R.string.title_setting)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        finish()
        return super.onOptionsItemSelected(item)
    }
}