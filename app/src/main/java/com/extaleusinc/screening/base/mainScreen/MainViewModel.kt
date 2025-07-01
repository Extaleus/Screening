package com.extaleusinc.screening.base.mainScreen

//import dagger.hilt.android.lifecycle.HiltViewModel
//import de.palm.composestateevents.StateEventWithContent
//import de.palm.composestateevents.consumed
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

//@HiltViewModel()
class MainViewModel constructor(
//    private val someRepo: SomeRepo
) : ViewModel() {
    private val _state: MutableStateFlow<MainState> = MutableStateFlow(MainState.initial())
    val state: StateFlow<MainState> get() = _state

    init {
        someRequest()
    }

    private fun someRequest() { // замена реквеста, инициализация моковыми данными
        _state.update {
            it.copy(
                profile = Profile(
                    "Вячеслав",
                    "https://thumbs.dreamstime.com/b/studio-photo-african-american-female-model-face-profile-closeup-fashionable-portrait-metis-young-woman-perfect-smooth-153607290.jpg"
                ),
                projects = listOf(
                    ProjectInfo(
                        companyImg = "https://i.pinimg.com/originals/c3/3c/92/c33c924446008e2cf23c442a3a269c11.jpg",
                        projectName = "Название проекта",
                        users = List(7) { _ ->
                            User(
                                profileImg = "https://thumbs.dreamstime.com/b/studio-photo-african-american-female-model-face-profile-closeup-fashionable-portrait-metis-young-woman-perfect-smooth-153607290.jpg"
                            )
                        }
                    ), ProjectInfo(
                        companyImg = "https://i.pinimg.com/originals/c3/3c/92/c33c924446008e2cf23c442a3a269c11.jpg",
                        projectName = "Название проекта",
                        users = List(3) { _ ->
                            User(
                                profileImg = "https://thumbs.dreamstime.com/b/studio-photo-african-american-female-model-face-profile-closeup-fashionable-portrait-metis-young-woman-perfect-smooth-153607290.jpg"
                            )
                        }
                    ), ProjectInfo(
                        companyImg = "https://i.pinimg.com/originals/c3/3c/92/c33c924446008e2cf23c442a3a269c11.jpg",
                        projectName = "Название проекта",
                        users = List(2) { _ ->
                            User(
                                profileImg = "https://thumbs.dreamstime.com/b/studio-photo-african-american-female-model-face-profile-closeup-fashionable-portrait-metis-young-woman-perfect-smooth-153607290.jpg"
                            )
                        }
                    ),
                    ProjectInfo(
                        companyImg = "https://i.pinimg.com/originals/c3/3c/92/c33c924446008e2cf23c442a3a269c11.jpg",
                        projectName = "Название проекта",
                        users = listOf()
                    ),
                    ProjectInfo(
                        companyImg = "https://i.pinimg.com/originals/c3/3c/92/c33c924446008e2cf23c442a3a269c11.jpg",
                        projectName = "Название проекта",
                        users = List(13) { _ ->
                            User(
                                profileImg = "https://thumbs.dreamstime.com/b/studio-photo-african-american-female-model-face-profile-closeup-fashionable-portrait-metis-young-woman-perfect-smooth-153607290.jpg"
                            )
                        }
                    ), ProjectInfo(
                        companyImg = "https://i.pinimg.com/originals/c3/3c/92/c33c924446008e2cf23c442a3a269c11.jpg",
                        projectName = "Название проекта",
                        users = List(3) { _ ->
                            User(
                                profileImg = "https://thumbs.dreamstime.com/b/studio-photo-african-american-female-model-face-profile-closeup-fashionable-portrait-metis-young-woman-perfect-smooth-153607290.jpg"
                            )
                        }
                    )
                )
            )
        }
    }

    fun processAction(action: MainAction) {
        when (action) {
//            MainAction.ConsumeError -> {}
            is MainAction.ChangeSelectedOption -> {
                // реквест на получение данных, если 200:
                _state.update { it.copy(showError = false, selectedOption = action.selectedOption) }
                // иначе showError = true
            }

            is MainAction.SetShowError -> {
                _state.update { it.copy(showError = action.newShowError) }
            }

            is MainAction.OnCardClick -> TODO("логика при нажатии на карточку компании")
        }
    }
}

data class MainState(
    val isLoading: Boolean = false,
    val showError: Boolean = false,
    val selectedOption: Int = 0,
    val profile: Profile? = null,
    val projects: List<ProjectInfo>? = null
//    val error: StateEventWithContent<String> = consumed(),
) {
    companion object {
        fun initial() = MainState()
    }
}

sealed class MainAction {
//    data object ConsumeError : MainAction()
    data class ChangeSelectedOption(val selectedOption: Int) : MainAction()
    data class OnCardClick(val project: ProjectInfo) : MainAction()
    data class SetShowError(val newShowError: Boolean) : MainAction()
}

// Модели должны лежать в другом модуле, например com.extaleusinc.data.models
data class ProjectInfo(
    val companyImg: String,
    val projectName: String,
    val users: List<User>
)

data class User(
    val profileImg: String
)

data class Profile(
    val name: String,
    val profileImg: String,
)
