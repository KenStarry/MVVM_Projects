package com.example.ticky.feature_task_groups.presentation.components.bottom_sheet_content

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.ListAlt
import androidx.compose.material.icons.sharp.Send
import androidx.compose.material.icons.sharp.WaterDrop
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.core.graphics.ColorUtils
import com.example.ticky.core.presentation.utils.Constants
import com.example.ticky.feature_task_groups.domain.model.Group
import com.example.ticky.ui.theme.RedPink

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddGroupBottomSheet(
    modifier: Modifier = Modifier,
    focusRequester: FocusRequester,
    onInputChange: (value: String) -> Unit,
    onSubmit: (inputValue: String, color: Int) -> Unit,
    clearFocus: () -> Unit
) {

    var textFieldValue by remember {
        mutableStateOf(Constants.NO_VALUE)
    }
    var selectedColor by remember {
        mutableStateOf<Int>(0)
    }

    val boxBackground = if (selectedColor == 0) {
        RedPink.copy(alpha = 0.5f)
    } else {
        Color(
            ColorUtils.blendARGB(selectedColor, 0x000000, 0.2f)
        ).copy(alpha = 0.5f)
    }

    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .clip(RoundedCornerShape(16.dp))
                .wrapContentSize()
                .background(MaterialTheme.colorScheme.onPrimary)
                .padding(vertical = 16.dp, horizontal = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            //  the group color and Icon
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(16.dp))
                    .size(70.dp)
                    .background(boxBackground),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Sharp.ListAlt,
                    contentDescription = "Group Icon",
                    modifier = Modifier
                        .size(36.dp),
                    tint = MaterialTheme.colorScheme.onSecondaryContainer.copy(alpha = 0.6f)
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            //  Bottom sheet textfield
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ) {

                TextField(
                    value = textFieldValue,
                    onValueChange = {
                        textFieldValue = it
                        onInputChange(textFieldValue)
                    },
                    placeholder = {
                        Text(
                            text = "Group Name",
                            fontWeight = MaterialTheme.typography.bodySmall.fontWeight,
                            fontSize = MaterialTheme.typography.bodySmall.fontSize,
                            color = Color.LightGray
                        )
                    },
                    colors = TextFieldDefaults.textFieldColors(
                        containerColor = MaterialTheme.colorScheme.onPrimary,
                        cursorColor = MaterialTheme.colorScheme.primary,
                        focusedIndicatorColor = MaterialTheme.colorScheme.primary,
                        unfocusedIndicatorColor = Color.LightGray.copy(alpha = 0.3f)
                    ),
                    singleLine = true,
                    modifier = Modifier
                        .weight(2f)
                        .focusRequester(focusRequester),
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Done
                    ),
                    keyboardActions = KeyboardActions(
                        onDone = {
                            //  clear focus
                            clearFocus()
                            //  submit the changes
                            onSubmit(textFieldValue, selectedColor)
                        }
                    )
                )

                Spacer(modifier = Modifier.width(8.dp))

                IconButton(onClick = {
                    //  submit the changes
                    onSubmit(textFieldValue, selectedColor)
                }) {
                    Icon(
                        imageVector = Icons.Sharp.Send,
                        contentDescription = "Send Icon"
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            //  Bottom sheet icons for color and icon picker
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ) {

                Row(
                    modifier = Modifier
                        .weight(1f),
                    horizontalArrangement = Arrangement.spacedBy(4.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Icon(
                        imageVector = Icons.Sharp.WaterDrop,
                        contentDescription = "Color Picker",
                        modifier = Modifier
                            .size(16.dp)
                    )

                    Text(
                        text = "Color picker",
                        fontSize = MaterialTheme.typography.bodySmall.fontSize,
                        fontWeight = MaterialTheme.typography.bodySmall.fontWeight,
                        modifier = Modifier
                            .weight(1f)
                    )

                }

                //  a list of all the available colors
                LazyRow(
                    content = {
                        itemsIndexed(
                            Group.groupColorsInt
                        ) { index, col ->

                            ColorsRowItem(color = col) { color ->
                                //  adjust selected color value
                                selectedColor = color

                                //  change background color
                            }
                        }
                    },
                    state = rememberLazyListState(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    userScrollEnabled = true,
                    modifier = Modifier
                        .weight(2f)
                )

            }

        }
    }
}


@Composable
fun ColorsRowItem(
    color: Int,
    onClick: (col: Int) -> Unit
) {

    Box(
        modifier = Modifier
            .clip(CircleShape)
            .background(
                Color(
                    ColorUtils.blendARGB(color, 0x000000, 0.2f)
                )
            )
            .size(40.dp)
            .clickable {
                onClick(color)
            }
    )
}




























