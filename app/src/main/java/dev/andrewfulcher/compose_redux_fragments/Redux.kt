package dev.andrewfulcher.compose_redux_fragments

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.navigation.fragment.findNavController
import org.reduxkotlin.createThreadSafeStore
import org.reduxkotlin.reducerForActionType

data class State(val count: Int = 0)

sealed class Action {
    object PushStack: Action()
    object IncrementCounter: Action()
}

private val reducer = reducerForActionType<State, Action> { state, action ->
    when (action) {
        is Action.IncrementCounter -> state.copy(count = state.count + 1)
        else -> throw UnsupportedOperationException()
    }
}

class Store: ViewModel() {

    private val startState = State()
    private val store = createThreadSafeStore(reducer, startState)
    private val unsubscribe = store.subscribe { state = store.state }

    var state: State by mutableStateOf(startState)
        private set

    fun dispatch(action: Action) {
        store.dispatch(action)
    }

    override fun onCleared() {
        unsubscribe
    }

}

fun Fragment.dispatch(store: Store, action: Action) {
    when (action) {
        is Action.PushStack -> {
            findNavController().navigate(R.id.action_mainFragment_self)
        }
        else -> store.dispatch(action)
    }
}