package com.denysorteie.usuarios.ui.fragments.home

import com.denysorteie.usuarios.data.User

interface HomeContract {
    interface View{
        fun addUser(user: User)
        fun showCleanList(visibility: Int)
        fun showListEmpty(visibility: Int)
    }

    interface Presenter{
        fun checkUser(user: User)
        fun checkList(lista: List<User>)
    }
}