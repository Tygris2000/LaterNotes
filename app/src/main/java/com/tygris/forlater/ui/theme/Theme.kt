package com.tygris.forlater.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable

private val DarkColorPalette = darkColors(
    primary = Purple200,
    primaryVariant = Purple700,
    secondary = secondary_for_dark,
    background = background_for_dark
)

private val LightColorPalette = lightColors(
    primary = primary_for_light,
    primaryVariant = primary_variant_light,
    secondary = secondary_for_light,
    background = background_for_light

    /* Other default colors to override
    background = Color.White,
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black,
    */
)
@Composable
fun ForLaterTheme( value : Int = -1,darkTheme: Boolean = isSystemInDarkTheme()
                  , content: @Composable() () -> Unit) {
    val colors = if (value==1){
        LightColorPalette
    }else if (value == 0){
        DarkColorPalette
    }else{
        if(darkTheme){
           DarkColorPalette
        }else{
            LightColorPalette
        }
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}
//@Composable
//fun ForLaterTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable() () -> Unit) {
//    val colors = if (darkTheme) {
//        DarkColorPalette
//    } else {
//        LightColorPalette
//    }
//
//    MaterialTheme(
//        colors = colors,
//        typography = Typography,
//        shapes = Shapes,
//        content = content
//    )
//}