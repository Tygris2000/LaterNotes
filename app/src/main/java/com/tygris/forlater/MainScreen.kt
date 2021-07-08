package com.tygris.forlater

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.tygris.forlater.data.UserPrefs
import com.tygris.forlater.model.LaterViewModel
import com.tygris.forlater.model.SomethingForLater
import com.tygris.forlater.presentation.CardList
import com.tygris.forlater.ui.theme.ForLaterTheme
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class MainScreen : ComponentActivity() {

    private val mviewmodel : LaterViewModel by viewModels()
    @ExperimentalAnimationApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mviewmodel.getAll().observe(this, {
            it.let {list->
                setContent {
                    ForLaterTheme(mviewmodel.mTheme()) {
                        Surface(color = MaterialTheme.colors.background){

                            Scaffold(topBar={Apptopbar(it.size)},
                                floatingActionButton = {Fab()},) {
                                CardList(listy = list)
                            }
                        }

                    }
                     }
            }
        })
    }

private fun themeToggle(){
    UserPrefs.init(this)
    when {
        mviewmodel.mTheme() == 1 -> {
            UserPrefs.setDark(false)
        }
        mviewmodel.mTheme() == 0 -> {
            UserPrefs.setDark(true)
        }
        else -> {
            UserPrefs.setDark(true)
        }
    }
    recreate()

}
    @Composable
    fun Apptopbar(listSize : Int){

        TopAppBar(title = { Text(stringResource(R.string.topbartitle)
            .padEnd(2).padStart(2)+listSize)},
            actions = { IconButton(onClick = { themeToggle()}) {
                Icon(painter = painterResource(id = R.drawable.outline_dark_mode_24),
                    contentDescription = null )
            }},modifier = Modifier.padding(bottom = 6.dp)

            )

    }




    
@ExperimentalAnimationApi
@Composable
private fun Fab(){
    FloatingActionButton(onClick =  { newNote()} ,
        content = {Icon(painter = painterResource(id = R.drawable.outline_add_20),
            contentDescription = stringResource(R.string.addsomethingtoremember))},
       backgroundColor = MaterialTheme.colors.onPrimary,contentColor = Color.Black,
        modifier = Modifier.wrapContentSize())

}

private fun newNote(){
    setContent{
      ForLaterTheme(mviewmodel.mTheme()){
          NewNoteDialog()
      }
    }
}
    /* NOTE: NewNoteDialog is poorly implemented for the time being
        im still learning the compose api.
        if you have a suggestion to improve this you are welcome!
     */
    @Composable
    fun NewNoteDialog(){
        val text = remember { mutableStateOf(TextFieldValue("")) }
        var isOpen by remember { mutableStateOf(true) }
        val typography = MaterialTheme.typography

        if (isOpen){
            AlertDialog(onDismissRequest = { isOpen= !isOpen
                                           recreate()},
                title = {
                    Text(text = stringResource(id = R.string.addnote),
                        style = typography.body2
                    )
                },text = {
                    TextField(
                        value = text.value,
                        colors = TextFieldDefaults.textFieldColors(
                            focusedIndicatorColor = Color.Transparent,

                            ),
                        onValueChange = { text.value = it },
                        maxLines = 6,

                        )
                },
                confirmButton = {
                    Button(onClick = {
                       GlobalScope.launch {
                           val somethingForLater = SomethingForLater()
                           somethingForLater.task = text.value.text
                           mviewmodel.insertNew(somethingForLater)
                       }
                        isOpen = false
                        recreate()},
                    ) {
                        Text(text = stringResource(id = R.string.add))

                    }
                },
                dismissButton = {
                    Button(onClick = {isOpen = false
                                     recreate()},
                    ) {
                        Text(text = stringResource(id = R.string.Cancel))

                    }
                }
            )
        }
    }

}