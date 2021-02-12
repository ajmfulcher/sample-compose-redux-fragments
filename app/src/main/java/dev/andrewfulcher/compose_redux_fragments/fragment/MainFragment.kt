package dev.andrewfulcher.compose_redux_fragments.fragment

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.fragment.app.viewModels
import dev.andrewfulcher.compose_redux_fragments.*
import dev.andrewfulcher.compose_redux_fragments.State

import dev.andrewfulcher.compose_redux_fragments.ui.SampleComposeReduxFragmentsTheme

class MainFragment : ComposeFragment() {

    val store by viewModels<Store>()

    @Composable
    override fun Compose() {
        SampleComposeReduxFragmentsTheme {
            // A surface container using the 'background' color from the theme
            Surface(color = MaterialTheme.colors.background) {
                MainScreen(state = store.state) { action -> dispatch(store, action) }
            }
        }
    }

}

@Composable
fun MainScreen(state: State, navigate: (Action) -> Unit) {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier.align(Alignment.Center)
        ) {
            Button(
                modifier = Modifier.padding(16.dp).align(Alignment.CenterHorizontally),
                onClick = { navigate(Action.PushStack) }
            ) {
                Text(text = "Push fragment")
            }
            Button(
                modifier = Modifier.padding(16.dp).align(Alignment.CenterHorizontally),
                onClick = { navigate(Action.IncrementCounter) }
            ) {
                Text(text = "Increment counter")
            }
        }
        Text(
            "Incremented ${state.count} times",
            modifier = Modifier.align(Alignment.BottomCenter).padding(16.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    SampleComposeReduxFragmentsTheme {
        MainScreen(State()) {}
    }
}