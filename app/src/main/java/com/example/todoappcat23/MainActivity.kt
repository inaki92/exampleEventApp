package com.example.todoappcat23

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.onNavDestinationSelected
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.todoappcat23.databinding.ActivityMainBinding
import com.example.todoappcat23.model.MyEvent
import com.example.todoappcat23.utils.movingToAnotherFragment
import com.example.todoappcat23.utils.switchingToAnotherFragment
import com.example.todoappcat23.view.MainTodoFragment

class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        // This is a Dynamic fragment that will be in our fragment container
        // replacing any other fragment right there
        // fragment manager allows you to have this transition between fragments

//        supportFragmentManager.beginTransaction()
//            .replace(R.id.container_frag, MainTodoFragment.newInstance())
//            .addToBackStack("countries")
//            .commit()

        // Moving to another fragment using extension function
        // supportFragmentManager.switchingToAnotherFragment(MainTodoFragment.newInstance())

        // moving to another Fragment with normal utility function
        // movingToAnotherFragment(supportFragmentManager, MainTodoFragment.newInstance())

        // Using navigation component to handle all the user flow in the app

        val navHost = supportFragmentManager.findFragmentById(R.id.container_frag) as NavHostFragment
        val navController = navHost.navController
        val appConfig = AppBarConfiguration(navController.graph)

        // setupActionBarWithNavController(navController, appConfig)

        binding.menuToolbar.setupWithNavController(navController)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val navController = findNavController(R.id.container_frag)
        return item.onNavDestinationSelected(navController) || super.onOptionsItemSelected(item)
    }

    val object3 = OtherObject().moreFunctionality()

    override fun onSupportNavigateUp(): Boolean {
        return findNavController(R.id.container_frag).navigateUp()
    }

    override fun onBackPressed() {
        // removing the super method allows you to block the functionality of the back button
        // super.onBackPressed()
        Toast.makeText(baseContext, "pressed", Toast.LENGTH_LONG).show()
    }
}

open class OtherObject {
    // contain some default methods
}

/**
 * This is an extension function
 *
 * You add more functionality to an existing class without the need of extending the whole class
 */
fun OtherObject.moreFunctionality(): String {
    return this.toString()
}

/**
 * This is not a extension function
 */
fun moreFunctionality2(objetc5 :OtherObject) {

}