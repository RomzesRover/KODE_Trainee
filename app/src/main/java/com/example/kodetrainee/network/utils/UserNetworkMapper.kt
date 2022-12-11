package com.example.kodetrainee.network.utils

import com.example.kodetrainee.domain.model.Department
import com.example.kodetrainee.domain.model.User
import com.example.kodetrainee.domain.utils.EntityMapper
import com.example.kodetrainee.network.model.UserNetworkEntity

class UserNetworkMapper: EntityMapper<UserNetworkEntity, User> {

    override fun mapFromNetworkEntityToDomainModel(userNetworkEntity: UserNetworkEntity): User {
        val result = User()

        result.id = userNetworkEntity.id
        result.avatarUrl = UserFakeAvatarUrlsSet.ava_url_set.shuffled().take(5).shuffled()[2]
        result.firstName = userNetworkEntity.firstName
        result.lastName = userNetworkEntity.lastName
        result.userTag = userNetworkEntity.userTag
        result.department = when(userNetworkEntity.department){
            "android" -> Department.Android()
            "ios" -> Department.Ios()
            "design" -> Department.Design()
            "management" -> Department.Management()
            "qa" -> Department.Qa()
            "back_office" -> Department.BackOffice()
            "frontend" -> Department.Frontend()
            "hr" -> Department.Hr()
            "pr" -> Department.Pr()
            "backend" -> Department.Backend()
            "support" -> Department.Support()
            "analytics" -> Department.Analytics()
            else -> Department.Unknown()
        }
        result.position = userNetworkEntity.position
        result.birthday = userNetworkEntity.birthday
        result.phone = userNetworkEntity.phone

        return result
    }

    override fun mapFromNetworkEntityListToDomainModelList(userNetworkEntityList: List<UserNetworkEntity>): List<User> {
        return userNetworkEntityList.map { mapFromNetworkEntityToDomainModel(it) }
    }
}