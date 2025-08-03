package com.sasindu.easystudyapplication.ui.presentation.components


import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable


@Composable
fun DeleteSubjectDialog(
    isOpen: Boolean,
    title: String = "Delete Subject",
    body: String,
    onConfirmationClicked: () -> Unit,
    onDismissClicked: () -> Unit,

    ) {

    if (isOpen) {
        AlertDialog(

            onDismissRequest = onDismissClicked,
            confirmButton = {
                TextButton(
                    onClick = { onConfirmationClicked() },
                ) { Text(text = "Delete") }

            },
            dismissButton = { TextButton(onClick = { onDismissClicked() }) { Text(text = "Cancel") } },
            title = { Text(text = title) },
            text =
            {
                Text(text = body)
            })
    }
}