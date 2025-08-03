package com.sasindu.easystudyapplication.ui.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.sasindu.easystudyapplication.R
import com.sasindu.easystudyapplication.ui.domain.model.Task
import com.sasindu.easystudyapplication.ui.presentation.utill.Priority


fun LazyListScope.taskList(
    sectionTitle: String,
    tasks: List<Task>,
    emptyStringMessage: String,
    onTaskCardClicked:(Int?)->Unit,
    onTaskListClicked:(Task)->Unit
) {

    item {
        Text(
            text = sectionTitle,
            style = MaterialTheme.typography.bodySmall,
            modifier = Modifier.padding(12.dp)
        )
    }

    if (tasks.isEmpty()) {
        item {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {


                Image(
                    modifier = Modifier
                        .size(120.dp),

                    painter = painterResource(R.drawable.img_tasks),
                    contentDescription = emptyStringMessage
                )
                Text(

                    text = emptyStringMessage,
                    style = MaterialTheme.typography.bodySmall,
                    textAlign = TextAlign.Center,
                    color = Color.Gray
                )
            }
        }
    }

    items(tasks) { task ->
        TaskCard(modifier = Modifier.padding(horizontal = 12.dp, vertical = 4.dp),
            task = task, onClick = {onTaskCardClicked(task.taskId)}, onCheckBoxClicked = {onTaskListClicked(task)})


    }
}

@Composable
private fun TaskCard(
    modifier: Modifier = Modifier,
    task: Task,
    onCheckBoxClicked: () -> Unit,
    onClick: () -> Unit
) {
    ElevatedCard(
        modifier = modifier.clickable { onClick() }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp, horizontal = 10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            TaskCheckBox(
                isComplete = task.isComplete,
                borderColor = Priority.fromInt(task.priority).color,
                onCheckBoxClick = { onCheckBoxClicked() }
            )
            Spacer(modifier = Modifier.width(10.dp))
            Column {
                Text(
                    text = task.title,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    style = MaterialTheme.typography.titleMedium,
                    textDecoration = if (task.isComplete) {
                        TextDecoration.LineThrough
                    } else {
                        TextDecoration.None
                    }
                )

                Spacer(modifier.height(4.dp))
                Text(text = "${task.dueDate}", style = MaterialTheme.typography.bodySmall)
            }
        }
    }
}