package com.example.raco

interface DrawerInterface {
    fun openDrawer()
    fun closeDrawer()

    //TODO was passiert wenn kein string als input?
    fun changeHeaderFields(email: String?, firstname: String, lastname: String)
}