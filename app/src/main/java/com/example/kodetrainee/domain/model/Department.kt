package com.example.kodetrainee.domain.model

sealed class Department(val name: String) {
    class Unknown : Department("")
    class Android : Department("android")
    class Ios : Department("ios")
    class Design : Department("design")
    class Management : Department("management")
    class Qa : Department("qa")
    class BackOffice : Department("back_office")
    class Frontend : Department("frontend")
    class Hr : Department("hr")
    class Pr : Department("pr")
    class Backend : Department("backend")
    class Support : Department("support")
    class Analytics : Department("analytics")
}