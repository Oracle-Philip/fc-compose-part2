package kr.co.fastcampus.part4plus.movieapp.ui.components.buttons

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kr.co.fastcampus.part4plus.movieapp.ui.models.buttons.LeadingIconData
import kr.co.fastcampus.part4plus.movieapp.ui.theme.MovieAppTheme
import kr.co.fastcampus.part4plus.movieapp.ui.theme.Paddings
import kr.co.fastcampus.part4plus.movieapp.ui.theme.colorScheme

private val LEADING_ICON_SIZE = 24.dp
@Composable
fun PrimaryButton(
    modifier: Modifier = Modifier,
    @StringRes id : Int? = null,
    text : String ="",
    leadingIconData: LeadingIconData? = null,
    onClick : () -> Unit
){
    Button(
        modifier = modifier.fillMaxWidth(),
        shape = MaterialTheme.shapes.small,
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            backgroundColor = MaterialTheme.colorScheme.primary,
            contentColor = MaterialTheme.colorScheme.onPrimary,
            disabledContentColor = MaterialTheme.colorScheme.background,
            disabledBackgroundColor = MaterialTheme.colorScheme.disabledSecondary
        )
    ) {
        /**
         * Arrangement는 Row니까 왼쪽에서 오른쪽 적용
         * Alignment는 위에서 아래 적용
         * 각각 어떻게 채울것인지..
         */
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ){
            leadingIconData?.let {
                Icon (
                    modifier = Modifier.size(LEADING_ICON_SIZE),
                    painter = painterResource(id = leadingIconData.IconDrawable),
                    contentDescription = stringResource(id = leadingIconData.iconContentDescription)
                )
                Spacer(modifier = Modifier.width(Paddings.small))
            }
            Text(
                text = id?.let{ stringResource(id = id)} ?: text,
                style = MaterialTheme.typography.button,
                modifier = Modifier.padding(Paddings.small)
            )
        }
    }
}

@Preview
@Composable
fun PrimaryButtonPreview(){
    MovieAppTheme {
        PrimaryButton(
            text = "Submit"
        ){

        }
    }
}