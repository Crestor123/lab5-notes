package com.example.notes

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
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
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import com.example.notes.ui.HomeViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.notes.ui.AppViewModelProvider
import com.example.notes.ui.HomeScreen
import com.example.notes.ui.theme.ThemeViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NotesApp(viewModel: ThemeViewModel = viewModel(factory = AppViewModelProvider.Factory)) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

    val setDarkMode : (isDarkMode : Boolean) -> Unit = { isDarkMode ->
        viewModel.setDarkMode(isDarkMode)
    }

    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = { NotesAppTopBar(scrollBehavior = scrollBehavior, setDarkMode) }
    ) {
        Surface(
            modifier = Modifier.fillMaxSize()
                .padding(it)
        ) {
            HomeScreen()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NotesAppTopBar(scrollBehavior: TopAppBarScrollBehavior,
                setDarkMode: (Boolean) -> Unit,
                modifier: Modifier = Modifier) {
    var checked by remember { mutableStateOf(false) }

    CenterAlignedTopAppBar(
        scrollBehavior = scrollBehavior,
        title = {
            Text(
                text = stringResource(R.string.app_name)
            )
        },
        modifier = modifier.testTag("toggle"),
        actions = {
            Switch(
                checked = checked,
                onCheckedChange = {
                    setDarkMode(!checked)
                    checked = it
                },

            )
        }
    )
}