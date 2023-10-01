package kr.co.fastcampus.part4plus.movieapp.ui.components.dialog.components.content

import android.content.res.ColorStateList
import android.widget.RatingBar
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.viewinterop.AndroidView
import kr.co.fastcampus.part4plus.movieapp.ui.models.dialog.DialogText
import kr.co.fastcampus.part4plus.movieapp.ui.theme.Paddings
import kr.co.fastcampus.part4plus.movieapp.ui.util.getAnnotatedText

@Composable
fun RatingContent(content: DialogText.Rating) {
    /**
     * 18:09
     * AndroidView에 대해 설명
     * 기존 Jeckpack compose에는 없는
     * RatingBar를 사용하기 위해
     * Anroid View의 factory parameter(Context Scope 영역)가 있음
     * Anroid View는 총 modifier, factory, update 3개 parameter 가짐
     * 이때 update는 view의 lifecycle을 따라가고 layout이 inflate 될때마다 불려져 잘 쓰지 않는게 좋다.
     */
    Column(
        modifier = Modifier.padding(
            top = Paddings.large,
            bottom = Paddings.xlarge
        )
    ) {
        RatingTable(
            rating = content.rating,
            movieTitle = content.text ?: ""
        )
    }
}

@Composable
fun ColumnScope.RatingTable(
    rating: Float,
    movieTitle: String
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .align(Alignment.CenterHorizontally)
    ) {
        Text(
            modifier = Modifier.align(
                Alignment.CenterHorizontally
            ),
            text = getAnnotatedText(text = movieTitle),
            style = MaterialTheme.typography.subtitle1.copy(
                color = MaterialTheme.colors.secondary
            ),
            textAlign = TextAlign.Center
        )
        Spacer(Modifier.height(Paddings.large))
        StarRatingBar(
            score = rating
        )
    }
}

@Composable
fun ColumnScope.StarRatingBar(score: Float) {
    Card(
        modifier = Modifier
            .padding(
                Paddings.medium
            )
            .wrapContentSize()
            .align(Alignment.CenterHorizontally),
        elevation = Paddings.none
    ) {
        Box(
            contentAlignment = Alignment.Center
        ) {
            val foregroundColor = MaterialTheme.colors.primary.toArgb()
            val starBackgroundColor = MaterialTheme.colors.primaryVariant.copy(
                alpha = 0.2f
            ).toArgb()
            val ratingBackgroundColor = MaterialTheme.colors.primaryVariant.copy(
                alpha = 0.05f
            ).toArgb()

            AndroidView(
                modifier = Modifier
                    .wrapContentWidth()
                    .align(Alignment.Center),
                factory = {
                    RatingBar(
                        it
                    ).apply {
                        max = 10
                        stepSize = 25f
                        rating = score
                        numStars = 5
                        progressTintList = ColorStateList.valueOf(foregroundColor)
                        progressBackgroundTintList = ColorStateList.valueOf(starBackgroundColor)
                        setBackgroundColor(ratingBackgroundColor)
                    }
                }
            )
        }
    }
}
