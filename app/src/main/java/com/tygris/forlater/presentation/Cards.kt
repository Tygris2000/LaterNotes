package com.tygris.forlater.presentation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tygris.forlater.model.SomethingForLater
import com.tygris.forlater.ui.theme.ForLaterTheme

@ExperimentalAnimationApi
@Composable
fun Greeting(listy : List<SomethingForLater>) {
    val somethingForLater = SomethingForLater(0,"Some task",null)
    LazyColumn() {
        items(listy){message->
            testCard(somethingForLater = message)
        }
    }



}
@ExperimentalAnimationApi
@Composable
fun testCard(somethingForLater: SomethingForLater){
    val theme = MaterialTheme
    var expanded by remember { mutableStateOf(false) }
    var maxl = 1
    if (expanded){maxl=20}
    else{maxl =1}
    Card(shape = theme.shapes.small,
        backgroundColor = theme.colors.secondary,
        modifier = Modifier
            .fillMaxWidth()
            .padding(3.dp)){
        Column {
            Row(Modifier.clickable { expanded = !expanded }){
                Text(
                    text = somethingForLater.task,
                    modifier = Modifier
                        .padding(
                            start = 10.dp,
                            top = 3.dp, bottom = 3.dp
                        )
                        .fillMaxWidth(0.90f),
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    maxLines = maxl,
                    overflow = TextOverflow.Ellipsis,
                )
            }
            Row(modifier = Modifier.wrapContentSize()){
                AnimatedVisibility(visible = expanded) {
                    Row{
                        Button(onClick = { /*TODO*/ }) {
                            Text(text = "Edit", Modifier.padding(3.dp).wrapContentSize())
                        }
                        Button(onClick = { /*TODO*/ }) {
                            Text(text = "Delete", Modifier.padding(3.dp).wrapContentSize().absolutePadding(3.dp))
                        }
                    }
                }
            }
        }
    }
}

@ExperimentalAnimationApi
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    val somet = SomethingForLater(0, "some Task", null)
    ForLaterTheme {
        testCard(somet)
    }
}