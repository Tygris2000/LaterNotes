package com.tygris.forlater.presentation

import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.tygris.forlater.R

@Composable
fun addDialog(){
    val typography = MaterialTheme.typography
Dialog(onDismissRequest = {}) {
   Surface(modifier = Modifier.size(128.dp)){
       Text(text = stringResource(R.string.addnote))
   }
}
}
@Preview(showBackground = true)
@Composable
fun previewdialog(){
    addDialog()
}