package dev.andrewfulcher.compose_redux_fragments.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import dev.andrewfulcher.compose_redux_fragments.R

abstract class ComposeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_compose, container, false).apply {
            findViewById<Toolbar>(R.id.main_fragment_toolbar).setUp()
            findViewById<ComposeView>(R.id.main_fragment_compose_view).apply {
                setContent { Compose() }
            }
        }
    }

    private fun Toolbar.setUp() {
        val navController = findNavController()
        val appBarConfiguration = AppBarConfiguration(navController.graph)
        setupWithNavController(navController, appBarConfiguration)
    }

    @Composable
    abstract fun Compose()

}