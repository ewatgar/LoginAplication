//en Invoice hay que meterlo en entity.data
//package com.example.data
package com.example.loginaplication.data.account

import com.example.loginaplication.data.model.BusinessProfile
import com.example.loginaplication.data.model.Email

class Account private constructor(
    val id: Int,
    val email: Email,
    val password: String?,
    val displayName: String?,
    state: AccountState = AccountState.UNVERIFIED,
    private var businessProfile: BusinessProfile,
) {

    private var hasActiveSession = false
    private var state = state


    fun isVerified(): Boolean {
        return state == AccountState.VERIFIED
    }


    fun businessName(): String {
        return businessProfile.name
    }

    fun renameBusiness(aName: String) {
        businessProfile = businessProfile.copy(name = aName)
    }

    fun businessAddress(): String {
        return businessProfile.address
    }

    fun changeBusinessAddress(aAddress: String) {
        businessProfile = businessProfile.copy(address = aAddress)
    }

    fun businessPhone(): String {
        return businessProfile.phoneNumber
    }

    fun changeBusinessPhone(aPhoneNumber: String) {
        businessProfile = businessProfile.copy(phoneNumber = aPhoneNumber)
    }

    fun signIn() {
        hasActiveSession = true
    }

    fun signOut() {
        hasActiveSession = false
    }


    /**
     * Al utilizar un objeto acompañante con una función y el constructor privado de la clase
     * garantiza el modo/restricciones que tenga al crear un objeto de la clase
     */
    companion object {
        fun create(id: Int, email: Email, password: String?, displayName: String?): Account {
            return Account(
                id = id,
                email = email,
                password=password,
                displayName = displayName,
                state=AccountState.UNVERIFIED,
                businessProfile = BusinessProfile(),
            )
        }

    }

}
