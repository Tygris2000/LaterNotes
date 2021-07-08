package com.tygris.forlater.presentation

import android.app.Application
import androidx.activity.viewModels
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModelProvider
import com.tygris.forlater.R
import com.tygris.forlater.data.Reposit
import com.tygris.forlater.model.LaterViewModel
import com.tygris.forlater.model.SomethingForLater
import com.tygris.forlater.ui.theme.ForLaterTheme
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

@ExperimentalAnimationApi
@Composable
fun CardList(listy : List<SomethingForLater>) {
    val somethingForLater = SomethingForLater(0,"Some task",null)
    Column() {

        LazyColumn() {
            items(listy){message->
                DisplayCard(somethingForLater = message)
            }
        }
    }



}
@ExperimentalAnimationApi
@Composable
fun DisplayCard(somethingForLater: SomethingForLater){
    val theme = MaterialTheme
    var expanded by remember { mutableStateOf(false) }
    var maxl = 1
    if (expanded){maxl=20}
    else{maxl =1}
    Card(shape = theme.shapes.small,
        backgroundColor = theme.colors.secondary,
        modifier = Modifier
            .fillMaxWidth()
            .padding(3.dp, bottom = 10.dp)){
        Column {
      Column(modifier = Modifier.clickable { expanded = !expanded }) {
          Row(){
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
          if(dateConverter(somethingForLater.alarm) != "null"){
              Divider()
              Row(){
                  Text(text = dateConverter(somethingForLater.alarm),
                      modifier = Modifier.padding(start = 10.dp,bottom = 5.dp),
                  fontSize = 12.sp,
                  fontStyle = FontStyle.Italic,
                  )
              }
          }
      }
            Row(modifier = Modifier.wrapContentSize()){
                AnimatedVisibility(visible = expanded) {
                    Row{
/*Edit Button*/       IconButton(onClick = {}) {
                          Icon(Icons.Outlined.Edit,
                              contentDescription = stringResource(R.string.EditNote))
                        }
                        IconButton(onClick = {
                            deleteNote(somethingForLater)
                        }
                        ) {
                            Icon(Icons.Outlined.Delete,
                                contentDescription = stringResource(
                                    R.string.DeleteNote))
                        }
                    }
                }
            }
        }
    }
}
fun editNote(){
 /*todo: TO BE IMPLEMENTED*/
}
fun deleteNote(somethingForLater: SomethingForLater){
    val repoI = Reposit.getInstance()
    GlobalScope.launch {
        repoI.delete(somethingForLater)
    }
}
fun dateConverter(date: Date?): String{
    val formatString = "d EEE, MMM yyyy h:mm a"
    val locale = Locale.getDefault()
    if(date != null){
        val formatter = SimpleDateFormat(formatString,locale)
        val formatterString = formatter.format(date)
        return formatterString
    }else{
        return "null"
    }
}

@ExperimentalAnimationApi
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {

    val someta = SomethingForLater(0, "some Task with alarm ", Date())
    ForLaterTheme() {
     DisplayCard(somethingForLater = someta)
}
}
