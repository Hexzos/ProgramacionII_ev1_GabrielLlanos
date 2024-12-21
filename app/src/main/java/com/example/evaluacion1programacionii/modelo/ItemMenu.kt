package com.example.evaluacion1programacionii.modelo

class ItemMenu ( val nombre : String, val precio : String) {

    var plato1 : MutableList<ItemMenu> = mutableListOf()

    var plato2 : MutableList<ItemMenu> = mutableListOf()

    constructor ( nombre: String, precio: String, parent: ItemMenu) : this (nombre, precio) {

        parent.plato1.add(this)

        parent.plato2.add(this)

    }

}

