/*
 * Created by Muhammad Yashlan Iskandar on 6/25/22, 4:19 PM
 * Last modified 6/25/22, 3:54 PM
 */

package com.yashlan.androidexpertsub1

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.MenuItem
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.yashlan.androidexpertsub1.databinding.ActivityMainBottomNavBinding
import com.yashlan.core.utils.forcePortraitScreenOrientation
import com.yashlan.core.utils.showToast

class MainBottomNavActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBottomNavBinding
    private lateinit var navController: NavController
    private var canExit = false

    override fun onCreate(savedInstanceState: Bundle?) {
        forcePortraitScreenOrientation()
        super.onCreate(savedInstanceState)
        binding = ActivityMainBottomNavBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setActionBarTitle(R.string.title_home)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment_activity_main_bottom_nav)
        navHostFragment?.findNavController().let {
            if (it != null) {
                navController = it
            }
        }

        val appBarConfiguration = AppBarConfiguration.Builder(
            R.id.navigation_home,
            R.id.navigation_favorite
        ).build()

        setupActionBarWithNavController(navController, appBarConfiguration)
        binding.navView.setupWithNavController(navController)
    }

    override fun onBackPressed() {
        if (canExit) {
            super.onBackPressed()
            finishAffinity()
            return
        }
        canExit = true
        showToast(R.string.exit_info)
        Handler(Looper.getMainLooper()).postDelayed({
            canExit = false
        }, DELAY_EXIT)
    }

    companion object {
        private const val DELAY_EXIT: Long = 2000
    }

    fun onClickHome(item: MenuItem) {
        if (navController.currentDestination?.id == R.id.navigation_home) {
            return
        }
        setActionBarTitle(R.string.title_home)
        navController.navigate(R.id.navigation_home)
    }

    fun onClickFavorite(item: MenuItem) {
        if (navController.currentDestination?.id == R.id.navigation_favorite) {
            return
        }
        setActionBarTitle(R.string.title_favorite)
        navController.navigate(R.id.navigation_favorite)
    }

    private fun setActionBarTitle(@StringRes resId: Int) {
        supportActionBar?.setTitle(resId)
    }
}