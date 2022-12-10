package com.example.kodetrainee.domain.utils

interface EntityMapper <NetworkEntity, DomainModel> {

    fun mapFromNetworkEntityToDomainModel(networkEntity: NetworkEntity): DomainModel

    fun mapFromNetworkEntityListToDomainModelList(networkEntityList: List<NetworkEntity>): List<DomainModel>
}