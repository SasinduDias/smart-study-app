package com.sasindu.easystudyapplication.ui.presentation.components


import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.sasindu.easystudyapplication.ui.domain.model.Subject


@Composable
fun AddSubjectDialog(
    isOpen: Boolean,
    title: String = "Add / Update Subject",
    onConfirmationClicked: () -> Unit,
    onDismissClicked: () -> Unit,
    selectedColors: List<Color>,
    onColorChange: (List<Color>) -> Unit,
    subjectName: String,
    goalHours: String,
    onSubjectNameChanged: (String) -> Unit,
    onGoalHoursChanged: (String) -> Unit
) {

    var subjectNameErr by rememberSaveable { mutableStateOf<String?>(null) }
    var goalNameErr by rememberSaveable { mutableStateOf<String?> (null) }

    subjectNameErr=when{
     subjectName.isBlank() -> "Please enter subject name"
            subjectName.length < 2 -> "Subject name is too short"
            subjectName.length > 20 -> "Subject name is too long"
            else -> null
    }

    goalNameErr=when{
        goalHours.isBlank() -> "Please enter goal hours"
        goalHours.toFloatOrNull() == null  -> "Invalid goal name"
        goalHours.toFloat() <1f  -> "Please set at least 1 hour"
        goalHours.toFloat() > 1000f  -> "Please set at a maximum 1000 hours"
        else -> null
    }

    if (isOpen) {
        AlertDialog(

            onDismissRequest = onDismissClicked,
            confirmButton = {
                TextButton(onClick = { onConfirmationClicked() },
                    enabled = subjectNameErr==null && goalNameErr==null) { Text(text = "save") }

            },
            dismissButton = { TextButton(onClick = { onDismissClicked() }) { Text(text = "Cancel") } },
            title = { Text(text = title) },
            text =
            {
                Column {
                    Row(
                        modifier = Modifier
                            .padding(bottom = 10.dp)
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        Subject.subjectCardColors.forEach { colors ->
                            Box(
                                modifier = Modifier
                                    .size(24.dp)
                                    .clip(CircleShape)
                                    .border(
                                        width = 1.dp,
                                        color = if (colors == selectedColors) Color.Black
                                        else {
                                            Color.Transparent
                                        },
                                        CircleShape,
                                    )
                                    .background(
                                        brush = Brush.verticalGradient(colors)
                                    )
                                    .clickable { onColorChange(colors) }
                            ) {

                            }
                        }
                    }
                    OutlinedTextField(
                        label = { Text(text = "Subject Name") },
                        value = subjectName,
                        onValueChange = onSubjectNameChanged,
                        singleLine = true,
                        isError = subjectNameErr != null && subjectName.isNotBlank(),
                        supportingText = { Text(text =subjectNameErr.orEmpty()) }
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    OutlinedTextField(
                        label = { Text(text = "Goal Study Hours") },
                        value = goalHours,
                        onValueChange = onGoalHoursChanged,
                        singleLine = true,
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        isError = goalNameErr != null && goalHours.isNotBlank(),
                        supportingText = { Text(text = goalNameErr.orEmpty()) }
                    )
                }
            })
    }
}