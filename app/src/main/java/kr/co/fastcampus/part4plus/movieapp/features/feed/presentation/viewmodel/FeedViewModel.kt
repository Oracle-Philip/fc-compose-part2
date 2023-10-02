package kr.co.fastcampus.part4plus.movieapp.features.feed.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kr.co.fastcampus.part4plus.movieapp.features.common.repository.IMovieDataSource
import kr.co.fastcampus.part4plus.movieapp.features.feed.presentation.input.IFeedViewModelInput
import kr.co.fastcampus.part4plus.movieapp.features.feed.presentation.output.FeedState
import kr.co.fastcampus.part4plus.movieapp.features.feed.presentation.output.FeedUiEffect
import kr.co.fastcampus.part4plus.movieapp.features.feed.presentation.output.IFeedViewModelOutput
import javax.inject.Inject

/**
 * <강의 메모> 05:24
 * android mvvm
 * view가 viewmodel 참조
 * viewmodel은 repository 참조
 * https://static.blog.gangnamunni.com/files/41402906-e322-4ad7-88fa-84098d9e028a
 *
 * mvvm clean architecture
 * 관심사 분리
 * 각 class들의 역할 분리
 * https://miro.medium.com/v2/resize:fit:1400/0*5eJUx2N-5IKoIJNO.png
 *
 * android mvvm과 mvvm clean architecture 간에 차이가 있다면
 * 가운데 domain(UseCase) layer가 있다는 것이다.
 * domain layer는 UseCase를 의미하며
 * <<UseCase>>는 domain과 viewModel(repository로부터 가져온 data를 화면에 보여주기 위한 것)과 repository(그냥 data) 사이에서
 * 어떠한 동작을 하도록 하는것
 * 특정한 data만 가져오도록 한다든지, 특정한 동작을 통해서 일종의 비지니스 로직을 만들어가지고 view(activity, fragment)에 가져오는
 * 동작을 하게 만드는 것
 *
 * https://objectivegroup.com/wp-content/uploads/2020/01/Camada-de-Dados-Data-Layer.png
 */
@HiltViewModel
class FeedViewModel @Inject constructor(
    private val movieRepository: IMovieDataSource
) : ViewModel(), IFeedViewModelOutput, IFeedViewModelInput {

    /**
     * Repository로부터 Data를 가져왔기 때문에
     * stateFlow를 이용해서 화면에 Data를 나타내보자.
     * stateFlow -> 상태를 가지고 있는 Flow이다.
     *
     * 처음 _feedState 프로퍼티에 MutableStateFlow 인스턴스를 부여하기 위해서는
     * MutableStateFlow(FeedState.Loading) 와 같이 초기값이 부여되어야 한다.
     */

    /**
     * 유저에게 Flow를 통한 state를 통해 화면에 Data를 보여준다.
     *
     * 추가설명> 28:25
     * 유저로부터 입력을 받아서 fragment 단에서 액션을 수행하기 위한 flow
     */
    private val _feedState : MutableStateFlow<FeedState> =
        MutableStateFlow(FeedState.Loading)

    override val feedState : StateFlow<FeedState>
        get() = _feedState

    /**
     * <강의 메모> 23:23 ch16
     * 유저로부터 입력을 받기 위한 Flow!!
     * 이때, MutableStateFlow가 아닌 MutableSharedFlow를 사용한다.
     * MutableSharedFlow는 기본값이 필요없다!!
     *
     * MutableSharedFlow에는 replay라는게 있다. Data를 몇번씩 반복해서 보여주냐는 의미!!
     */
    private val _feedUiEffect = MutableSharedFlow<FeedUiEffect>(replay = 0)
    override val feedUiEffect : SharedFlow<FeedUiEffect>
        get() = _feedUiEffect

    private

    fun getMoviews() {
        viewModelScope.launch {
            /**
             * 구현으로이동 — Go to Implementation
                Win: control + alt + B
                mac: command + alt + B
             */
            movieRepository.getMovieList()
        }
    }

    override fun openDetail(movieName: String) {
        TODO("Not yet implemented")
    }

    override fun openInfoDialog() {
        TODO("Not yet implemented")
    }

    override fun refreshFeed() {
        TODO("Not yet implemented")
    }
}