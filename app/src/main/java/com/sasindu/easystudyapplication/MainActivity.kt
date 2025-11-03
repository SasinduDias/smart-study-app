package com.sasindu.easystudyapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.sasindu.easystudyapplication.ui.domain.model.Session
import com.sasindu.easystudyapplication.ui.domain.model.Subject
import com.sasindu.easystudyapplication.ui.domain.model.Task
import com.sasindu.easystudyapplication.ui.presentation.dashboard.DashboardScreen
import com.sasindu.easystudyapplication.ui.presentation.subject.SubjectScreen
import com.sasindu.easystudyapplication.ui.presentation.theme.EasyStudyApplicationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            EasyStudyApplicationTheme {
                SubjectScreen()
             //   DashboardScreen()

            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    EasyStudyApplicationTheme {
        Greeting("Android")
    }
}

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