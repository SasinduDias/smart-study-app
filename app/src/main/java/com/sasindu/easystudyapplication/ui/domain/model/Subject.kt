package com.sasindu.easystudyapplication.ui.domain.model

import com.sasindu.easystudyapplication.ui.presentation.theme.gradient1
import com.sasindu.easystudyapplication.ui.presentation.theme.gradient2
import com.sasindu.easystudyapplication.ui.presentation.theme.gradient3
import com.sasindu.easystudyapplication.ui.presentation.theme.gradient4
import com.sasindu.easystudyapplication.ui.presentation.theme.gradient5


data class Subject(
    val name:String,
    val goalHours:Float,
    val subjectId:Int,
    val colors: List<androidx.compose.ui.graphics.Color>
){
    companion object{
        val subjectCardColors = listOf(gradient1, gradient2, gradient3, gradient4, gradient5)
    }
}
