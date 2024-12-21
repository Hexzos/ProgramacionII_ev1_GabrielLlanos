package com.example.evaluacion1programacionii.modelo

class ItemMesa(val cantidad: Int, val itemMenu: ItemMenu) {

    constructor(itemMenu: ItemMenu, cantidad: Int) : this(cantidad, itemMenu)

    fun calcularSubTotal(): Int {
        val precio = itemMenu.precio.toIntOrNull() ?: 0
        return cantidad * precio
    }
}