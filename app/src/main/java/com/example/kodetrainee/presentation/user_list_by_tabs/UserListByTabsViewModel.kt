package com.example.kodetrainee.presentation.user_list_by_tabs

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.kodetrainee.domain.model.Department

class UserListByTabsViewModel: ViewModel() {

    private val departmentsListMutable = MutableLiveData<ArrayList<Department>>()
    val departmentsList: LiveData<ArrayList<Department>> = departmentsListMutable

    init {
        createDepartmentsList()
    }

    private fun createDepartmentsList() {
        val list = arrayListOf(
            Department.All(),
            Department.Design(),
            Department.Analytics(),
            Department.Management(),
            Department.Android(),
            Department.Ios(),
            Department.Qa(),
            Department.BackOffice(),
            Department.Frontend(),
            Department.Hr(),
            Department.Pr(),
            Department.Backend(),
            Department.Support()
        )

        departmentsListMutable.value = list
    }

    private val searchQueryMutable = MutableLiveData<Pair<String, Department>>()
    val searchQuery: LiveData<Pair<String, Department>> = searchQueryMutable

    private val searchStateActiveMutable = MutableLiveData<Any?>()
    val searchStateActive: LiveData<Any?> = searchStateActiveMutable
    private val searchStateInactiveMutable = MutableLiveData<Any?>()
    val searchStateInactive: LiveData<Any?> = searchStateInactiveMutable

    fun newSearchQuery(query: String?, currentPosition: Int){
        val requiredDepartmentFilter = departmentsList.value?.get(currentPosition)

        if (requiredDepartmentFilter != null && query != null) {
            searchQueryMutable.value = Pair(query, requiredDepartmentFilter)

            if (query.isBlank() || query.isEmpty()){
                searchStateInactiveMutable.value = null
            } else {
                searchStateActiveMutable.value = null
            }
        }
    }
}