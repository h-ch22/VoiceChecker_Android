package com.cj.voicechecker.frameworks.ui

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

val boldRegex = Regex("(?<!\\*)\\*\\*(?!\\*).*?(?<!\\*)\\*\\*(?!\\*)")


@Composable
fun RichText(text: String, modifier: Modifier = Modifier, fontSize: Int = 15) {

    var results: MatchResult? = boldRegex.find(text)

    val boldIndexes = mutableListOf<Pair<Int, Int>>()

    val keywords = mutableListOf<String>()

    var finalText = text

    while (results != null) {
        keywords.add(results.value)
        results = results.next()
    }

    keywords.forEach { keyword ->
        val indexOf = finalText.indexOf(keyword)
        val newKeyWord = keyword.removeSurrounding("**")
        finalText = finalText.replace(keyword, newKeyWord)
        boldIndexes.add(Pair(indexOf, indexOf + newKeyWord.length))
    }

    val annotatedString = buildAnnotatedString {
        append(finalText)

        boldIndexes.forEach {
            addStyle(
                style = SpanStyle(
                    fontWeight = FontWeight.Bold,
                    fontSize = fontSize.sp
                ),
                start = it.first,
                end = it.second
            )

        }
    }

    Text(
        modifier = modifier,
        fontSize = fontSize.sp,
        text = annotatedString
    )
}