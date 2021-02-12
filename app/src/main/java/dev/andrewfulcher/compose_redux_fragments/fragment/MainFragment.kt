package dev.andrewfulcher.compose_redux_fragments.fragment

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.fragment.findNavController
import dev.andrewfulcher.compose_redux_fragments.R
import dev.andrewfulcher.compose_redux_fragments.ui.SampleComposeReduxFragmentsTheme

class MainFragment : ComposeFragment() {

    @Composable
    override fun Compose() {
        val navController = findNavController()
        SampleComposeReduxFragmentsTheme {
            // A surface container using the 'background' color from the theme
            Surface(color = MaterialTheme.colors.background) {
                Greeting {
                    navController.navigate(R.id.action_mainFragment_self)
                }
            }
        }
    }

}

@Composable
fun Greeting(navigate: () -> Unit) {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Button(
            modifier = Modifier.align(Alignment.Center),
            onClick = navigate
        ) {
            Text(text = "Tap Me")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    SampleComposeReduxFragmentsTheme {
        Greeting {}
    }
}