package com.example.notes

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import com.example.notes.ui.HomeViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.notes.ui.HomeScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NotesApp(darkTheme: Boolean, onThemeChange: () -> Unit) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()



    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = { NotesAppTopBar(scrollBehavior = scrollBehavior, darkTheme = darkTheme,
            onThemeChange = onThemeChange) } ,

    ) {


        Surface(
            modifier = Modifier.fillMaxSize()
                .padding(it)
        ) {
            HomeScreen();

        }


    }


}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NotesAppTopBar(scrollBehavior: TopAppBarScrollBehavior, modifier: Modifier = Modifier, darkTheme: Boolean,
                   onThemeChange: () -> Unit) {
    CenterAlignedTopAppBar(

        scrollBehavior = scrollBehavior,
        title = {
            Text(
                text = stringResource(R.string.app_name)
            )
        },
        actions = {
            themeSwitcher(darkTheme = darkTheme, onClick = onThemeChange)
        },
        modifier = modifier



    )

}





@Composable
fun themeSwitcher(darkTheme : Boolean = false,onClick : () -> Unit){

    Switch(
        checked = darkTheme,
        onCheckedChange = { onClick() },
        colors = SwitchDefaults.colors(
            checkedTrackColor = MaterialTheme.colorScheme.secondaryContainer,
            checkedThumbColor = MaterialTheme.colorScheme.primary,
            uncheckedTrackColor = MaterialTheme.colorScheme.secondaryContainer,
            uncheckedThumbColor = MaterialTheme.colorScheme.secondary
        )
    )
}