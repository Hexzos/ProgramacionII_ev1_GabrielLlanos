package com.example.evaluacion1programacionii.modelo

class CuentaMesa(private val mesa: Int) {
    val items: MutableList<ItemMesa> = mutableListOf()
    var aceptaPropina: Boolean = true


    fun agregarItem(itemMenu: ItemMenu, cantidad: Int) {
        val itemMesa = ItemMesa(cantidad, itemMenu)
        items.add(itemMesa)
    }


    fun agregarItem(itemMesa: ItemMesa) {
        items.add(itemMesa)
    }


    fun calcularTotalSinPropina(): Int {
        var total = 0
        for (item in items) {
            total += item.calcularSubTotal()
        }
        return total
    }


    fun calcularPropina(): Int {
        val totalSinPropina = calcularTotalSinPropina()
        return if (aceptaPropina) (totalSinPropina * 0.1).toInt() else 0
    }


    fun calcularTotalConPropina(): Int {
        val totalSinPropina = calcularTotalSinPropina()
        return totalSinPropina + calcularPropina()
    }
}
