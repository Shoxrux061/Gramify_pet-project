package uz.shoxrux.main.presentation.screens.main.reels

import androidx.compose.foundation.pager.VerticalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import uz.shoxrux.core.ui.components.LoadingBar

@Composable
fun ReelsPage(
    viewModel: ReelsViewModel
) {

    val wikis = viewModel.wikis.collectAsState().value?.query?.pages?.values?.toList()
    val error = viewModel.error.collectAsState().value
    val isLoading = viewModel.isLoading.collectAsState().value

    LaunchedEffect(Unit) {
        viewModel.getWikis()
    }

    if (isLoading) {
        LoadingBar()
    }

    val pagerState = rememberPagerState {
        wikis?.size?:0
    }

    LaunchedEffect(pagerState) {
        if (pagerState == (wikis?.size ?: false)) {
            viewModel.getWikis()
        }
    }

    VerticalPager(
        state = pagerState,
        beyondViewportPageCount = 1,
    ) { page ->
        ReelsItem(wikis?.get(page))
    }
}