package com.example.kodetrainee.domain.model

import com.example.kodetrainee.R

sealed class Department(val nameResourceId: Int) {
    class Unknown : Department(R.string.department_name_unknown)
    class Android : Department(R.string.department_name_android)
    class Ios : Department(R.string.department_name_ios)
    class Design : Department(R.string.department_name_design)
    class Management : Department(R.string.department_name_management)
    class Qa : Department(R.string.department_name_qa)
    class BackOffice : Department(R.string.department_name_back_office)
    class Frontend : Department(R.string.department_name_frontend)
    class Hr : Department(R.string.department_name_hr)
    class Pr : Department(R.string.department_name_Pr)
    class Backend : Department(R.string.department_name_backend)
    class Support : Department(R.string.department_name_support)
    class Analytics : Department(R.string.department_name_analytics)
}