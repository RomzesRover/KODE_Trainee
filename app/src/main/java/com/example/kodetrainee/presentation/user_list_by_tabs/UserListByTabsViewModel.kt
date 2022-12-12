package com.example.kodetrainee.presentation.user_list_by_tabs

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.kodetrainee.domain.model.Department
import com.example.kodetrainee.presentation.user_list.UserListFragment
import com.example.kodetrainee.presentation.user_list.UserListViewModel

class UserListByTabsViewModel: ViewModel() {

    private val fragmentsListMutable = MutableLiveData<List<Fragment>>()
    val fragmentsList: LiveData<List<Fragment>> = fragmentsListMutable

    init {
        createFragmentList()
    }

    // TODO: Hardcode. May be needed to replace by factory and repository somehow
    private fun createFragmentList() {
        val list: ArrayList<Fragment> = ArrayList()

        val fragmentAll = UserListFragment()
        var bundle = Bundle()
        bundle.putParcelable(UserListViewModel.REQUIRED_DEPARTMENT_KEY, Department.All())
        fragmentAll.arguments = bundle
        list.add(fragmentAll)

        val fragmentAndroid = UserListFragment()
        bundle = Bundle()
        bundle.putParcelable(UserListViewModel.REQUIRED_DEPARTMENT_KEY, Department.Android())
        fragmentAndroid.arguments = bundle
        list.add(fragmentAndroid)

        val fragmentIos = UserListFragment()
        bundle = Bundle()
        bundle.putParcelable(UserListViewModel.REQUIRED_DEPARTMENT_KEY, Department.Ios())
        fragmentIos.arguments = bundle
        list.add(fragmentIos)

        val fragmentDesign = UserListFragment()
        bundle = Bundle()
        bundle.putParcelable(UserListViewModel.REQUIRED_DEPARTMENT_KEY, Department.Design())
        fragmentDesign.arguments = bundle
        list.add(fragmentDesign)

        val fragmentManagement = UserListFragment()
        bundle = Bundle()
        bundle.putParcelable(UserListViewModel.REQUIRED_DEPARTMENT_KEY, Department.Management())
        fragmentManagement.arguments = bundle
        list.add(fragmentManagement)

        val fragmentQa = UserListFragment()
        bundle = Bundle()
        bundle.putParcelable(UserListViewModel.REQUIRED_DEPARTMENT_KEY, Department.Qa())
        fragmentQa.arguments = bundle
        list.add(fragmentQa)

        val fragmentBackOffice = UserListFragment()
        bundle = Bundle()
        bundle.putParcelable(UserListViewModel.REQUIRED_DEPARTMENT_KEY, Department.BackOffice())
        fragmentBackOffice.arguments = bundle
        list.add(fragmentBackOffice)

        val fragmentFrontend = UserListFragment()
        bundle = Bundle()
        bundle.putParcelable(UserListViewModel.REQUIRED_DEPARTMENT_KEY, Department.Frontend())
        fragmentFrontend.arguments = bundle
        list.add(fragmentFrontend)

        val fragmentHr = UserListFragment()
        bundle = Bundle()
        bundle.putParcelable(UserListViewModel.REQUIRED_DEPARTMENT_KEY, Department.Hr())
        fragmentHr.arguments = bundle
        list.add(fragmentHr)

        val fragmentPr = UserListFragment()
        bundle = Bundle()
        bundle.putParcelable(UserListViewModel.REQUIRED_DEPARTMENT_KEY, Department.Pr())
        fragmentPr.arguments = bundle
        list.add(fragmentPr)

        val fragmentBackend = UserListFragment()
        bundle = Bundle()
        bundle.putParcelable(UserListViewModel.REQUIRED_DEPARTMENT_KEY, Department.Backend())
        fragmentBackend.arguments = bundle
        list.add(fragmentBackend)

        val fragmentSupport = UserListFragment()
        bundle = Bundle()
        bundle.putParcelable(UserListViewModel.REQUIRED_DEPARTMENT_KEY, Department.Support())
        fragmentSupport.arguments = bundle
        list.add(fragmentSupport)

        val fragmentAnalytics = UserListFragment()
        bundle = Bundle()
        bundle.putParcelable(UserListViewModel.REQUIRED_DEPARTMENT_KEY, Department.Analytics())
        fragmentAnalytics.arguments = bundle
        list.add(fragmentAnalytics)

        fragmentsListMutable.value = list
    }
}