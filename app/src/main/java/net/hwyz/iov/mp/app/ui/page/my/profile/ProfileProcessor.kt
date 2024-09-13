package net.hwyz.iov.mp.app.ui.page.my.profile

import net.hwyz.iov.mp.app.base.MviActionProcessor
import net.hwyz.iov.mp.app.data.http.HttpService
import net.hwyz.iov.mp.app.utils.AppUserUtil
import javax.inject.Inject

open class ProfileProcessor @Inject constructor(
    private var service: HttpService,
) :
    MviActionProcessor<ProfileAction, ProfileResult> {

    override suspend fun executeAction(action: ProfileAction): ProfileResult {
        return when (action) {
            is ProfileAction.DisplayLoading -> ProfileResult.DisplayLoading
            is ProfileAction.LoadProfile -> loadProfile()
            is ProfileAction.DisplayProfile -> ProfileResult.DisplayProfile
            is ProfileAction.DisplayNickname -> ProfileResult.DisplayNickname
            is ProfileAction.UpdateNickname -> updateNickname(action.nickname)
            is ProfileAction.DisplayGender -> ProfileResult.DisplayGender
            is ProfileAction.UpdateGender -> updateGender(action.gender)
        }
    }

    private suspend fun loadProfile(): ProfileResult {
        return try {
            val response = service.getAccountInfo()
            if (response.code == 0) {
                ProfileResult.LoadProfile.Success(response.data!!)
            } else {
                throw Exception(response.message)
            }
        } catch (e: Exception) {
            ProfileResult.LoadProfile.Failure(e)
        }
    }

    private suspend fun updateNickname(nickname: String): ProfileResult {
        return try {
            val response = service.modifyNickname(nickname = nickname)
            if (response.code == 0) {
                AppUserUtil.changeNickname(nickname = nickname)
                ProfileResult.UpdateNickname.Success(nickname)
            } else {
                throw Exception(response.message)
            }
        } catch (e: Exception) {
            ProfileResult.UpdateNickname.Failure(e)
        }
    }

    private suspend fun updateGender(gender: String): ProfileResult {
        return try {
            val response = service.modifyGender(gender = gender)
            if (response.code == 0) {
                ProfileResult.UpdateGender.Success(gender)
            } else {
                throw Exception(response.message)
            }
        } catch (e: Exception) {
            ProfileResult.UpdateGender.Failure(e)
        }
    }
}