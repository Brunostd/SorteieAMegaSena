package com.example.usuarios.ui.fragments.home

import com.example.usuarios.data.User

interface HomeContract {
    interface View{
        fun addUser(user: User)
    }

    interface Presenter{
        fun checkUser(user: User)
    }
}