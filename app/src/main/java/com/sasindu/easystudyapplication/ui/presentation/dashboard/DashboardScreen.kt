package com.sasindu.easystudyapplication.ui.presentation.dashboard

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.sasindu.easystudyapplication.R
import com.sasindu.easystudyapplication.ui.domain.model.Session
import com.sasindu.easystudyapplication.ui.domain.model.Subject
import com.sasindu.easystudyapplication.ui.domain.model.Task
import com.sasindu.easystudyapplication.ui.presentation.components.AddSubjectDialog
import com.sasindu.easystudyapplication.ui.presentation.components.CountCard
import com.sasindu.easystudyapplication.ui.presentation.components.DeleteSubjectDialog
import com.sasindu.easystudyapplication.ui.presentation.components.SubjectCard
import com.sasindu.easystudyapplication.ui.presentation.components.studySessionList
import com.sasindu.easystudyapplication.ui.presentation.components.taskList


@Composable
fun DashboardScreen() {
    val subjects = listOf(
        Subject(
            name = "Physics",
            goalHours = 2f,
            colors = Subject.subjectCardColors[0],
            subjectId = 0
        ),
        Subject(
            name = "Chemistry",
            goalHours = 5f,
            colors = Subject.subjectCardColors[1],
            subjectId = 0
        ),
        Subject(
            name = "Maths",
            goalHours = 10f,
            colors = Subject.subjectCardColors[2],
            subjectId = 0
        ),
        Subject(
            name = "History",
            goalHours = 12f,
            colors = Subject.subjectCardColors[3],
            subjectId = 0
        ),
        Subject(
            name = "English",
            goalHours = 20f,
            colors = Subject.subjectCardColors[4],
            subjectId = 0
        ),
    )

    val sessionList = listOf(
        Session(
            sessionId = 0,
            sessionSubjectId = 0,
            relatedToSubject = "English",
            date = 0L,
            duration = 0,

            ),
        Session(
            sessionId = 0,
            sessionSubjectId = 0,
            relatedToSubject = "Maths",
            date = 0L,
            duration = 0,

            ), Session(
            sessionId = 0,
            sessionSubjectId = 0,
            relatedToSubject = "IT",
            date = 0L,
            duration = 0,

            )
    )

    val taskList = listOf(
        Task(
            title = "Special notes",
            description = "",
            dueDate = 0L,
            priority = 1,
            relatedToSubject = "",
            isComplete = false,
            taskId = null,
            taskSubjectId = 0
        ),
        Task(
            title = "Maths notes",
            description = "",
            dueDate = 4L,
            priority = 2,
            relatedToSubject = "",
            isComplete = true,
            taskId = null,
            taskSubjectId = 0
        ),
        Task(
            title = "Chemistry notes",
            description = "",
            dueDate = 7L,
            priority = 3,
            relatedToSubject = "",
            isComplete = false,
            taskId = null,
            taskSubjectId = 0
        ),
        Task(
            title = "Physics notes",
            description = "",
            dueDate = 0L,
            priority = 4,
            relatedToSubject = "",
            isComplete = true,
            taskId = null,
            taskSubjectId = 0
        ),
        Task(
            title = "It notes",
            description = "",
            dueDate = 2L,
            priority = 1,
            relatedToSubject = "",
            isComplete = false,
            taskId = null,
            taskSubjectId = 0
        ),


        )

    var openDialog by rememberSaveable { mutableStateOf(false) }
    var openDeleteDialog by rememberSaveable { mutableStateOf(false) }
    var subjectName by remember { mutableStateOf("") }
    var goalHoursName by remember { mutableStateOf("") }
    var selectedColor by remember { mutableStateOf(Subject.subjectCardColors.random()) }


    AddSubjectDialog(
        isOpen = openDialog,
        onConfirmationClicked = { openDialog = false },
        onDismissClicked = { openDialog = false },
        selectedColors =selectedColor,
        onColorChange = {selectedColor= it},
        subjectName = subjectName,
        goalHours = goalHoursName,
        onSubjectNameChanged = { subjectName=it},
        onGoalHoursChanged = {goalHoursName=it}
    )
    DeleteSubjectDialog(
        isOpen = openDeleteDialog,
        onConfirmationClicked = { openDeleteDialog = false },
        onDismissClicked = { openDeleteDialog = false },
        body = "Are you sure to delete this session"
    )

    Scaffold(topBar = { DashboardTopAppBar() })
    { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            item {
                CountCardSelection(
                    modifier = Modifier,
                    subCount = "3",
                    studyHours = "7",
                    goalStudyHours = "15"
                )

            }
            item {
                SubjectAddPart(
                    modifier = Modifier.padding(horizontal = 10.dp),
                    subjectList = subjects,
                    onclicked = { openDialog = true }
                )
            }
            item {
                Button(
                    onClick = {},
                    Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 48.dp, vertical = 20.dp)
                ) {
                    Text(text = "Start Study")
                }
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
                Spacer(Modifier.height(10.dp))
            }
            studySessionList(
                sectionTitle = "RECENT STUDY SESSIONS",
                emptyStringMessage = "You don't have any recent study sessions\n " +
                        " Click the + button in subject screen to add new tasks",
                sessions = sessionList,
                onDeleteIconClicked = {openDeleteDialog=true}
            )

        }
    }


}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun DashboardTopAppBar() {

    CenterAlignedTopAppBar(title = {
        Text("Study Easy", style = MaterialTheme.typography.headlineMedium)
    })
}

@Composable
fun CountCardSelection(
    modifier: Modifier,
    subCount: String,
    studyHours: String,
    goalStudyHours: String
) {
    Row(
        modifier.padding(start = 10.dp, end = 10.dp)
    ) {
        CountCard(modifier = Modifier.weight(1f), name = "Subject Count", count = subCount)
        Spacer(modifier = Modifier.width(10.dp))
        CountCard(modifier = Modifier.weight(1f), name = "Study Hours", count = studyHours)
        Spacer(modifier = Modifier.width(10.dp))
        CountCard(modifier = Modifier.weight(1f), name = "Goal Study Hours", count = goalStudyHours)

    }
}

@Composable
private fun SubjectAddPart(modifier: Modifier, subjectList: List<Subject>, onclicked: () -> Unit) {
    Column {
        Row(
            modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("Subject", style = MaterialTheme.typography.bodyMedium)
            IconButton(
                onClick = {
                    onclicked(
                    )
                },

                ) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Add Subject")
            }
        }

        if (subjectList.isEmpty()) {
            Image(
                modifier = Modifier
                    .size(120.dp)
                    .align(Alignment.CenterHorizontally),
                painter = painterResource(R.drawable.img_books),
                contentDescription = ""
            )
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = "You don't have any subjects \n Please click add button to add subject",
                style = MaterialTheme.typography.bodySmall,
                textAlign = TextAlign.Center,
                color = Color.Gray
            )
        }
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            contentPadding = PaddingValues(start = 12.dp, end = 12.dp)
        ) {
            items(subjectList) { subject ->
                SubjectCard(subjectName = subject.name, gradientColor = subject.colors, onClick = {

                })
            }
        }


    }
}


