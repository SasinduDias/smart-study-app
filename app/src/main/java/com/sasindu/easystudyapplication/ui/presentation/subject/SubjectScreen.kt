package com.sasindu.easystudyapplication.ui.presentation.subject

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment

import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.sasindu.easystudyapplication.sessionList
import com.sasindu.easystudyapplication.taskList
import com.sasindu.easystudyapplication.ui.domain.model.Subject
import com.sasindu.easystudyapplication.ui.presentation.components.AddSubjectDialog
import com.sasindu.easystudyapplication.ui.presentation.components.CountCard
import com.sasindu.easystudyapplication.ui.presentation.components.DeleteSubjectDialog
import com.sasindu.easystudyapplication.ui.presentation.components.studySessionList
import com.sasindu.easystudyapplication.ui.presentation.components.taskList


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SubjectScreen() {


    var editDialog by rememberSaveable { mutableStateOf(false) }
    var openDeleteDialog by rememberSaveable { mutableStateOf(false) }
    var openDeleteSubjectDialog by rememberSaveable { mutableStateOf(false) }
    var subjectName by remember { mutableStateOf("") }
    var goalHoursName by remember { mutableStateOf("") }
    var selectedColor by remember { mutableStateOf(Subject.subjectCardColors.random()) }


    AddSubjectDialog(
        isOpen = editDialog,
        onConfirmationClicked = { editDialog = false },
        onDismissClicked = { editDialog = false },
        selectedColors =selectedColor,
        onColorChange = {selectedColor= it},
        subjectName = subjectName,
        goalHours = goalHoursName,
        onSubjectNameChanged = { subjectName=it},
        onGoalHoursChanged = {goalHoursName=it}
    )

    DeleteSubjectDialog(
        isOpen = openDeleteDialog,
        title = "Delete session",
        onConfirmationClicked = { openDeleteDialog = false },
        onDismissClicked = { openDeleteDialog = false },
        body = "Are you sure to delete this session"
    )

    DeleteSubjectDialog(
        title = "Delete subject",
        isOpen = openDeleteSubjectDialog,
        onConfirmationClicked = { openDeleteSubjectDialog = false },
        onDismissClicked = { openDeleteSubjectDialog = false },
        body = "Are you sure to delete this subject"
    )



    val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior()
    val listState = rememberLazyListState()
    val ifFABExpand by remember { derivedStateOf { listState.firstVisibleItemIndex ==0 } }
    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            SubjectScreenTopAppBar(
                title = "English",
                onDeleteButtonClicked ={openDeleteDialog = true},
                onEditButtonClicked ={editDialog = true},
                onNavigationButtonClicked ={}
                ,scrollBehavior = scrollBehavior
            )
        },
        floatingActionButton = {
            ExtendedFloatingActionButton(
                expanded = ifFABExpand,
                onClick = {},
                text = { Text(text = "Add Task") },
               icon = { Icon(imageVector = Icons.Default.Add,"Add") }
            )
        }

    ){

            LazyColumn (
                state = listState,
                modifier = Modifier
                .fillMaxSize()
                .padding(it)) {
                 item {
                     SubjectOverviewAction(
                         modifier = Modifier.padding(12.dp),
                         studentHours ="10",
                         goalHours = "15",
                         progress = 0.75f
                     )
                 }

                taskList(
                    sectionTitle = "UPCOMING TASKS",
                    emptyStringMessage = "You don't have any upcoming tasks\n " +
                            " Click the + button in subject screen to add new tasks",
                    tasks = taskList,
                    onTaskCardClicked = {},
                    onTaskListClicked = {}
                )
                item {
                    Spacer(Modifier.height(20.dp))
                }
                taskList(
                    sectionTitle = "COMPLETED TASKS",
                    emptyStringMessage = "You don't have any completed tasks\n " +
                            " Click the check box on complete the tasks",
                    tasks = taskList,
                    onTaskCardClicked = {},
                    onTaskListClicked = {}
                )
                item {
                    Spacer(Modifier.height(20.dp))
                }
                studySessionList(
                    sectionTitle = "RECENT STUDY SESSIONS",
                    emptyStringMessage = "You don't have any recent study sessions\n " +
                            " Click the + button in subject screen to add new tasks",
                    sessions = sessionList,
                    onDeleteIconClicked = {openDeleteSubjectDialog = true}
                )

            }

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SubjectScreenTopAppBar(
    title: String,
    onEditButtonClicked: () -> Unit,
    onDeleteButtonClicked: () -> Unit,
    onNavigationButtonClicked: () -> Unit,
    scrollBehavior: TopAppBarScrollBehavior
) {

    LargeTopAppBar(
        scrollBehavior = scrollBehavior,
        navigationIcon = {
            IconButton(onClick = { onNavigationButtonClicked() }) {
                Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Navigate back")
            }
        },
        title = {
            Text(
                text = title,
                maxLines = 1,
                style = MaterialTheme.typography.headlineSmall,
                overflow = TextOverflow.Ellipsis,

                )
        },

        actions = {
            IconButton(onClick = { onDeleteButtonClicked() }) {
                Icon(
                    imageVector = Icons.Default.Delete,
                    "Delete Subject"
                )
            }
            IconButton(onClick = { onEditButtonClicked() }) {
                Icon(
                    imageVector = Icons.Default.Edit,
                    "Delete Subject"
                )
            }
        },

    )

}

@Composable
fun SubjectOverviewAction(
    modifier: Modifier,
    studentHours:String,
    goalHours:String,
    progress:Float
){
    val precentage = remember(progress) {
        (progress*100).toInt().coerceIn(0,100)
    }
    Row (
        modifier=modifier,
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically
    ){
        CountCard(
            modifier = Modifier.weight(1f),
            count = goalHours,
            name = studentHours
        )
        Spacer(modifier = Modifier.width(10.dp))
        CountCard(
            modifier = Modifier.weight(1f),
            count = goalHours,
            name = studentHours
        )
        Spacer(modifier = Modifier.width(10.dp))
        Box (modifier = Modifier.size(75.dp),
            contentAlignment = Alignment.Center){
            CircularProgressIndicator(
                modifier = Modifier.fillMaxSize(),
                progress = 1f,
                strokeWidth = 4.dp,
                strokeCap = StrokeCap.Round,
                color = MaterialTheme.colorScheme.surfaceVariant

            )
            CircularProgressIndicator(
                modifier = Modifier.fillMaxSize(),
                progress = 1f,
                strokeWidth = 4.dp,
                strokeCap = StrokeCap.Round,


            )
            Text("$precentage%")



        }
    }
}
