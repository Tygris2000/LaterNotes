package com.tygris.forlater

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.tygris.forlater.data.UserPrefs
import com.tygris.forlater.model.LaterViewModel
import com.tygris.forlater.presentation.Greeting
import com.tygris.forlater.ui.theme.ForLaterTheme


class MainScreen : ComponentActivity() {
   // private lateinit var mviewmodel : LaterViewModel
    private val mviewmodel : LaterViewModel by viewModels()
    @ExperimentalAnimationApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

       // mviewmodel = ViewModelProvider(this).get(LaterViewModel::class.java)
        mviewmodel.getAll().observe(this, {
            it.let {ity->
                Log.d("omarisstupid", "number for things to remember ${it.size}")
                setContent {
                    ForLaterTheme(mviewmodel.mTheme()) {
                        Surface(color = MaterialTheme.colors.background){

                            Scaffold(topBar={Apptopbar(it.size)},floatingActionButton = {Fab()}) {
                                Greeting(listy = ity)
                            }
                        }

                    }
                     }
            }
        })
    }

fun themeToggle(){
    UserPrefs.init(this)
    if(mviewmodel.mTheme() == 1){
        UserPrefs.setDark(false)

    }else if(mviewmodel.mTheme() == 0){
        UserPrefs.setDark(true)
    }else{
        UserPrefs.setDark(true)
    }
    recreate()


}
    @Composable
    fun Apptopbar(listSize : Int){

        TopAppBar(title = { Text(stringResource(R.string.topbartitle)
            .padEnd(2).padStart(2)+listSize)},
            actions = { IconButton(onClick = {themeToggle()}) {
                Icon(painter = painterResource(id = R.drawable.outline_dark_mode_24),
                    contentDescription = null )
            }}

            )

    }



    
@Composable
fun Fab(){
    FloatingActionButton(onClick = { /*TODO*/ },
        content = {Icon(painter = painterResource(id = R.drawable.outline_add_20),
            contentDescription = stringResource(R.string.addsomethingtoremember))},
       backgroundColor = MaterialTheme.colors.onPrimary,contentColor = Color.Black,
        modifier = Modifier.wrapContentSize())
}
}