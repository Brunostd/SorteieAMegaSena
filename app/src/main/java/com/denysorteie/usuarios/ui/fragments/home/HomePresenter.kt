package com.denysorteie.usuarios.ui.fragments.home

import android.view.View
import com.denysorteie.usuarios.data.User
import java.text.SimpleDateFormat
import java.util.*

class HomePresenter: HomeContract.Presenter {

    lateinit var view: HomeContract.View

    override fun checkUser(user: User) {
        val date = Calendar.getInstance().time
        var dateTimeFormat = SimpleDateFormat("yyyy/MM/dd HH:mm:ss", Locale.getDefault())
        user.date = dateTimeFormat.format(date).toString()
        user.numberOne = (1..60).random()
        user.numberTwo = (1..60).random()
        user.numberThree = (1..60).random()
        user.numberFour = (1..60).random()
        user.numberFive = (1..60).random()
        user.numberSix = (1..60).random()
        view.addUser(user)
    }

    override fun checkList(lista: List<User>) {
        if (lista.isNotEmpty()){
            view.showCleanList(View.VISIBLE)
            view.showListEmpty(View.INVISIBLE)
        } else{
            view.showCleanList(View.INVISIBLE)
            view.showListEmpty(View.VISIBLE)
        }
    }
}