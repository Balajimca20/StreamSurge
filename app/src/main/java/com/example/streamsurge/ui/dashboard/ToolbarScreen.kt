package com.example.streamsurge.ui.dashboard

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.streamsurge.R


@Composable
fun Toolbar(
    searchValue: String,
    onSearchValueChanged: (String) -> Unit,
    onDismissClearText: (Boolean) -> Unit,
) {
    Box {
        CollapsingToolbarBar(
            title = stringResource(id = R.string.app_name),
            searchText = searchValue,
            onSearchValueChanged = {
                onSearchValueChanged(it)
            },
            onBackPressed = {
            },
            clearDialog = false,
            onDismissClearText = onDismissClearText,
        )
    }
}

@Composable
fun CollapsingToolbarBar(
    title: String,
    searchText: String,
    onSearchValueChanged: (String) -> Unit,
    onBackPressed: () -> Unit,
    clearDialog: Boolean,
    onDismissClearText: (Boolean) -> Unit,
) {
    TopBar(
        title = title,
        searchText = searchText,
        onSearchValueChanged = onSearchValueChanged,
        onBackPressed = onBackPressed,
        clearDialog = clearDialog,
        onDismissClearText = onDismissClearText
    )

}

@Preview(showBackground = true)
@Composable
private fun CollapsingToolbarBarPreview() {
    CollapsingToolbarBar(
        title = "Testing",
        searchText = "Searching",
        onSearchValueChanged = {},
        onBackPressed = {},
        clearDialog=true,
        onDismissClearText = {},
    )
}
