package repository

import android.content.Entity

interface StandardRepository {

    fun add()

    fun remove()

    fun readById()

    fun readAll()
}