package net.hwyz.iov.mp.app.ui.page.my.profile

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import dagger.hilt.android.lifecycle.HiltViewModel
import net.hwyz.iov.mp.app.base.presentation.BaseViewModel
import net.hwyz.iov.mp.app.data.bean.AccountInfo
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    override val actionProcessor: ProfileProcessor
) : BaseViewModel<ProfileIntent, ProfileState, ProfileAction, ProfileResult>() {

    var viewStates by mutableStateOf(ProfileState())
        private set

    override fun actionFromIntent(intent: ProfileIntent): List<ProfileAction> {
        return when (intent) {
            is ProfileIntent.OnLaunched -> listOf(
                ProfileAction.DisplayLoading,
                ProfileAction.LoadProfile
            )

            is ProfileIntent.OnTapNickname -> listOf(ProfileAction.DisplayNickname)
            is ProfileIntent.OnTapBackProfile -> listOf(ProfileAction.DisplayProfile)
            is ProfileIntent.OnTapNicknameSaveButton -> listOf(
                ProfileAction.DisplayLoading,
                ProfileAction.UpdateNickname(intent.nickname)
            )

            is ProfileIntent.OnTapGender -> listOf(ProfileAction.DisplayGender)
            is ProfileIntent.OnTapGenderSaveButton -> listOf(
                ProfileAction.DisplayLoading,
                ProfileAction.UpdateGender(intent.gender)
            )
        }
    }

    override suspend fun reducer(result: ProfileResult) {
        when (result) {
            is ProfileResult.DisplayLoading -> displayLoading()
            is ProfileResult.LoadProfile.Success -> {
                loadProfile(result.accountInfo)
                displayProfile()
            }

            is ProfileResult.LoadProfile.Failure -> {
                displayError(result.error.message ?: "异常")
                displayProfile()
            }

            is ProfileResult.DisplayProfile -> displayProfile()
            is ProfileResult.DisplayNickname -> displayNickname()
            is ProfileResult.UpdateNickname.Success -> {
                updateNickname(result.nickname)
                displayProfile()
            }

            is ProfileResult.UpdateNickname.Failure -> {
                displayError(result.error.message ?: "异常")
                displayProfile()
            }

            is ProfileResult.DisplayGender -> displayGender()
            is ProfileResult.UpdateGender.Success -> {
                updateGender(gender = result.gender)
                displayProfile()
            }

            else -> {}
        }
    }

    private fun displayLoading() {
        viewStates = viewStates.copy(result = ProfileResult.DisplayLoading)
    }

    private fun loadProfile(accountInfo: AccountInfo) {
        viewStates = viewStates.copy(
            nickname = accountInfo.nickname,
            gender = accountInfo.gender,
            avatar = accountInfo.avatar
        )
    }

    private fun displayProfile() {
        viewStates = viewStates.copy(result = ProfileResult.DisplayProfile)
    }

    private fun displayNickname() {
        viewStates = viewStates.copy(result = ProfileResult.DisplayNickname)
    }

    private fun updateNickname(nickname: String) {
        viewStates = viewStates.copy(nickname = nickname)
    }

    private fun displayGender() {
        viewStates = viewStates.copy(result = ProfileResult.DisplayGender)
    }

    private fun updateGender(gender: String) {
        viewStates = viewStates.copy(gender = gender)
    }

    private fun displayError(text: String) {
        print(text)
    }

}