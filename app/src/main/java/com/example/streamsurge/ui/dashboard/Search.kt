package com.example.streamsurge.ui.dashboard

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.SoftwareKeyboardController
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.streamsurge.R

@Preview(showBackground = false)
@Composable
fun TopBarPreview() {
    TopBar(
        title = "Recent Documents",
        searchText = "",
        onSearchValueChanged = {},
        onBackPressed = {},
        clearDialog = true,
        onDismissClearText = {}
    )
}

@OptIn(ExperimentalComposeUiApi::class, ExperimentalMaterial3Api::class)
@Composable
fun TopBar(
    title: String,
    searchText: String,
    onSearchValueChanged: (String) -> Unit,
    onBackPressed: () -> Unit,
    clearDialog: Boolean,
    onDismissClearText: (Boolean) -> Unit,
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    var isSearch by remember { mutableStateOf(false) }
    TopAppBar(
        title = {
            if (isSearch) {
                SearchBar(
                    searchText,
                    onSearchValueChanged,
                    keyboardController,
                    clearDialog = clearDialog,
                    onDismissClearText = onDismissClearText,
                    onDismiss = {
                        isSearch = false
                        keyboardController?.hide()
                        onSearchValueChanged("")
                    }
                )
            } else {
                Text(
                    text = title,
                    fontWeight = FontWeight.Medium,
                    color = colorResource(id = R.color.white),
                    textAlign = TextAlign.Start,
                    fontSize = 18.sp,
                )
            }
        },
        actions = {
            if (!isSearch) {
                IconButton(onClick = {
                    isSearch = true
                },) {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "Search",
                        tint = Color.White,
                        modifier = Modifier
                            .width(38.dp)
                            .height(38.dp)
                            .padding(end = 8.dp)
                            .clip(CircleShape)
                    )
                }

            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = colorResource(id = R.color.black_light)
        )
    )
}

@OptIn(ExperimentalComposeUiApi::class, ExperimentalMaterial3Api::class)
@Composable
fun SearchBar(
    value: String,
    onValueChanged: (String) -> Unit,
    keyboardController: SoftwareKeyboardController?,
    clearDialog: Boolean,
    onDismissClearText: (Boolean) -> Unit,
    onDismiss: () -> Unit,
) {
    Surface(
        modifier = Modifier
            .offset(x = (-8).dp)
            .fillMaxWidth()
            .height(56.dp),
        color = Color.Transparent
    ) {
        TextField(
            modifier = Modifier
                .fillMaxWidth(),
            value = if (clearDialog) {
                ""
            } else value,
            onValueChange = {
                onDismissClearText(false)
                onValueChanged(it)
            },
            textStyle = TextStyle(
                fontWeight = FontWeight.Bold
            ),
            singleLine = true,
            leadingIcon = {
                Icon(
                    imageVector = Icons.Filled.Search,
                    contentDescription = null,
                    tint = Color.White
                )
            },
            trailingIcon = {
                IconButton(
                    onClick = {
                        onDismiss()
                    },
                    modifier = Modifier
                        .width(38.dp)
                        .height(38.dp)
                ) {
                    Icon(
                        imageVector = Icons.Filled.Close,
                        contentDescription = "Close Icon",
                        tint =  Color.White
                    )
                }

            },
            placeholder = {
                Text(
                    text = stringResource(id = R.string.search),
                    color = Color.White
                )
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(
                onDone = { keyboardController?.hide() }
            ),
            colors = TextFieldDefaults.colors(
                disabledTextColor = Color.Transparent,
                focusedTextColor = Color.White,
                focusedContainerColor = Color.Black,
                unfocusedContainerColor = Color.Black,
                disabledContainerColor = Color.Red,
                cursorColor = Color.Red,
                focusedIndicatorColor = Color.Red,
                unfocusedIndicatorColor = Color.Red,
                disabledIndicatorColor = Color.Transparent,
            ),
        )
    }
}