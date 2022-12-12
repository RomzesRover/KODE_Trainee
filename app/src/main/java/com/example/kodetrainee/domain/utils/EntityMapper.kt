package com.example.kodetrainee.domain.utils

import com.example.kodetrainee.domain.model.Department

interface EntityMapper <NetworkEntity, DomainModel> {

    fun mapFromNetworkEntityToDomainModel(networkEntity: NetworkEntity): DomainModel

    fun mapFromNetworkEntityListToDomainModelList(networkEntityList: List<NetworkEntity>, department: Department): List<DomainModel>
}