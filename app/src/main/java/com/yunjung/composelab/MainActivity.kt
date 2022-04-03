package com.yunjung.composelab

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.yunjung.composelab.ui.theme.ComposeLabTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeLabTheme {
               MyApp()
            }
        }
    }
}

@Composable
fun MyApp(){
    var shouldShowOnBoarding by rememberSaveable {
        mutableStateOf(true)
    }

    if(shouldShowOnBoarding){
        OnBoardingScreen(onContinueClicked = {shouldShowOnBoarding = false})
    } else {
        Greetings()
    }
}

@Composable
private fun OnBoardingScreen(onContinueClicked : () -> Unit){
    Surface{
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Welcome to the Basics Codelab!")
            Button(
                modifier = Modifier.padding(vertical = 24.dp),
                onClick = onContinueClicked
            ){
                Text("Continue")
            }
        }
    }
}

@Composable
private fun Greetings(names : List<String> = List(100){"$it"}) {
    LazyColumn(modifier = Modifier.padding(4.dp)) {
        items(items = names) { name ->
            Greeting(name = name)
        }
    }
}

@Composable
private fun Greeting(name : String) {
    val expanded = rememberSaveable { mutableStateOf(false)}
    
    val extraPadding = if(expanded.value) 48.dp else 0.dp
    
    Surface(
        color = MaterialTheme.colors.primary,
        modifier = Modifier.padding(vertical = 4.dp, horizontal = 8.dp)
    ){
        Row(modifier = Modifier.padding(24.dp)) {
            Column(modifier = Modifier
                .weight(1f)
                .padding(bottom = extraPadding)
            ){
                Text(text = "Hello, ")
                Text(text = name)
            }
            OutlinedButton(
                onClick = { expanded.value = !expanded.value }
            ) {
                Text(text = if(expanded.value) "Show less" else "Show more")
            }
        }
    }
}

/* 화면 미리보기 */
@Preview(showBackground = true, widthDp = 320)
@Composable
fun DefaultPreview() {
    ComposeLabTheme {
        Greetings()
    }
}

@Preview(showBackground = true, widthDp = 320, heightDp = 320)
@Composable
fun OnBoardingPreview(){
    ComposeLabTheme{
        OnBoardingScreen(onContinueClicked = {})
    }
}