package kr.co.fastcampus.part4plus.movieapp.features.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.material.Text
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import dagger.hilt.android.AndroidEntryPoint
import kr.co.fastcampus.part4plus.movieapp.ui.theme.MovieAppTheme

@AndroidEntryPoint
class DetailFragment : Fragment(){
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return ComposeView(requireContext()).apply {
            setContent {
                MovieAppTheme {
                    Text(
                        text = "DetailFragment"
                    )
                }
            }
        }
    }
}