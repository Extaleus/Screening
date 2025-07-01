package com.extaleusinc.screening.base.mainScreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.extaleusinc.screening.base.Constants.ABOUT_APP
import com.extaleusinc.screening.base.Constants.EMPTY_STR
import com.extaleusinc.screening.base.Constants.PROJECTS_SURF
import com.extaleusinc.screening.base.Constants.STUFF
import com.extaleusinc.screening.base.projectsSurf.ProjectsSurfScreen
import com.extaleusinc.screening.base.uiHelpers.CustomAsyncImage
import com.extaleusinc.screening.base.uiHelpers.ErrorScreen

@Composable
fun MainScreen(modifier: Modifier, viewModel: MainViewModel) {
//    val state by viewModel.state.collectAsStateWithLifecycle() // лучше использовать это, но нужна либа
    val state by viewModel.state.collectAsState()
    val onAction = viewModel::processAction

    MainScreen(modifier, state, onAction)
}

@Composable
fun MainScreen(modifier: Modifier, state: MainState, onAction: (MainAction) -> Unit) {
    val options = remember { listOf(PROJECTS_SURF, STUFF, ABOUT_APP) }

    Column(modifier = modifier) {
//        CustomTopBar() // можно использовать здесь вместо scaffold+topBar

        ProfilePart(
            Modifier, state.profile?.name ?: EMPTY_STR, state.profile?.profileImg ?: EMPTY_STR
        ) {
            // обработка клика по профилю onAction(MainAction.ProfileClick())
        }

        ScreenSelector(
            modifier = Modifier.padding(top = 8.dp),
            options,
            state.selectedOption,
            underlineError = state.showError
        ) { onAction(MainAction.ChangeSelectedOption(it)) }

        if (!state.showError) {
            when (state.selectedOption) {
                0 -> {
                    state.projects?.let { project -> ProjectsSurfScreen(project){onAction(MainAction.OnCardClick(it))} }
                }

                1, 2 -> {}
            }
        } else {
            ErrorScreen(Modifier) { /*Логика по нажатию обновить*/ }
        }
    }
}

@Composable
fun CustomTopBar(modifier: Modifier) {
    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        CustomAsyncImage(
            modifier = Modifier.size(80.dp, 60.dp),
            "https://www.designtagebuch.de/wp-content/uploads/mediathek//2017/08/youtube-logo-light.jpg",
            shape = RectangleShape,
            contentScale = ContentScale.Fit
        )
        HorizontalDivider(
            modifier = Modifier.fillMaxWidth(), thickness = 1.dp, color = Color.LightGray
        )
    }
}
