package com.example.kodetrainee.domain.model

data class User (
    var id: String = "",
    var avatarUrl: String = "",
    var firstName: String = "",
    var lastName: String = "",
    var userTag: String = "",
    var department: Department = Department.Unknown(),
    var position: String = "",
    var birthday: String = "",
    var phone: String = ""
) {
    override fun equals(other: Any?): Boolean {

        if (javaClass != other?.javaClass)
            return false

        other as User

        if (id != other.id)
            return false

        if (avatarUrl != other.avatarUrl)
            return false

        if (firstName != other.firstName)
            return false

        if (lastName != other.lastName)
            return false

        if (userTag != other.userTag)
            return false

        if (department::class != other.department::class) {
            return false
        }

        if (position != other.position)
            return false

        if (birthday != other.birthday)
            return false

        if (phone != other.phone)
            return false

        return true
    }

    override fun hashCode(): Int {
        return super.hashCode()
    }
}