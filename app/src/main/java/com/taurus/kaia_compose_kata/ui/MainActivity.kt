package com.taurus.kaia_compose_kata.ui

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.taurus.kaia_compose_kata.R
import com.taurus.kaia_compose_kata.ui.theme.KaiaComposeKataTheme

/**
 * State in an app is any value that can change over time.
 * This is a very broad definition and encompasses everything from a Room database to a variable on a class.
 *
 * Compose is declarative and as such the only way to update it is by calling the same composable with
 * new arguments.
 *
 * These arguments are representations of the UI state.
 * Any time a state is updated a recomposition takes place.
 * As a result, things like TextField don’t automatically update like they do in imperative XML based views.
 * A composable has to explicitly be told the new state in order for it to update accordingly.
 *
 * Ref: https://developer.android.com/jetpack/compose/state
 * Imp: https://developer.android.com/jetpack/compose/mental-model
 */

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            KaiaComposeKataTheme {
                MyApp()
            }
        }
    }
}

// TODO - 1: Try to centralize the text and the button
// Hint: Do not use modifiers

// TODO - 2: On boarding screen will never go away let's try to find a solution to fix it
// Hint: https://developer.android.com/jetpack/compose/state#state-in-composables

// TODO - 3: Composables with internal state tend to be less reusable and harder to test.
// Hint 1: A stateless composable is a composable that doesn't hold any state. An easy way to achieve stateless is by using state hoisting.
// https://developer.android.com/jetpack/compose/state#state-hoisting

// Hint 2: create a separate composable function for on boarding screen which takes lambda as input

// TODO - 4: On boarding screen shows again on the configuration change for example on screen rotation.
// How do we stop that?

@Composable
fun MyApp() {
    // var shouldShowOnboarding by remember { mutableStateOf(true) }
    var shouldShowOnboarding by rememberSaveable { mutableStateOf(true) }

    if (shouldShowOnboarding) {
        OnboardingScreen(onContinueClicked = { shouldShowOnboarding = false })
    } else {
        Greetings()
    }
}

@Composable
fun OnboardingScreen(onContinueClicked: () -> Unit) {
    Surface {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Welcome to the Basics Codelab!")
            Button(
                modifier = Modifier.padding(vertical = 24.dp),
                onClick = onContinueClicked
            ) {
                Text("Continue")
            }
        }
    }
}

// TODO - 5: Let's increase the List size to 1k and see what happens?
// TODO - 6: Use a replacement of RecyclerView with 3 lines of code, instead of multiple files ;)
@Composable
private fun Greetings(names: List<String> = List(1000) { "$it" }) {
    LazyColumn(modifier = Modifier.padding(vertical = 4.dp)) {
        items(items = names) { name ->
            Greeting(name = name)
        }
    }
}

@Composable
fun Greeting(name: String) {
    Card(
        backgroundColor = MaterialTheme.colors.primary,
        modifier = Modifier.padding(vertical = 4.dp, horizontal = 8.dp)
    ) {
        CardContent(name)
    }
}


// TODO - 7: The expanded state is not behaving as expected let's find a solution
// TODO - 8: View isn't the whole width of the screen what modifier would you use?
// TODO - 9: Let's try to change the fontWeight for "MaterialTheme.typography.h4"

@Composable
private fun CardContent(name: String) {
    var expanded by remember { mutableStateOf(false) }
    // var expanded by rememberSaveable { mutableStateOf(false) }

    Row(
        modifier = Modifier
            .padding(12.dp)
            .animateContentSize(
                animationSpec = spring(
                    dampingRatio = Spring.DampingRatioMediumBouncy,
                    stiffness = Spring.StiffnessLow
                )
            )
    ) {
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(12.dp)
        ) {
            Text(text = "Hello, ")
            Text(
                text = name,
                style = MaterialTheme.typography.h4.copy(
                    fontWeight = FontWeight.ExtraBold
                )
            )
            if (expanded) {
                Text(
                    text = ("Composem ipsum color sit lazy, " +
                        "padding theme elit, sed do bouncy. ").repeat(4),
                )
            }
        }
        IconButton(onClick = { expanded = !expanded }) {
            Icon(
                imageVector = if (expanded) Icons.Filled.ExpandLess else Icons.Filled.ExpandMore,
                contentDescription = if (expanded) {
                    stringResource(R.string.show_less)
                } else {
                    stringResource(R.string.show_more)
                }

            )
        }
    }
}

@Preview(showBackground = true, widthDp = 320, heightDp = 320)
@Composable
fun OnboardingPreview() {
    KaiaComposeKataTheme {
        OnboardingScreen(onContinueClicked = {}) // Do nothing on click.
    }
}


// TODO - 10: Make use of another @Preview tag to display the night mode version of the layout
// TODO - 11: Set width to increase the size
@Preview(
    showBackground = true,
    widthDp = 320,
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    name = "DefaultPreviewDark"
)
@Preview(showBackground = true, widthDp = 320)
@Composable
fun DefaultPreview() {
    KaiaComposeKataTheme {
        Greetings()
    }
}
