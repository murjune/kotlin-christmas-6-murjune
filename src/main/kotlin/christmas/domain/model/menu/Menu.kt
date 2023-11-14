package christmas.domain.model.menu

import christmas.domain.type.MealType

data class Menu(private val name: String, private val price: Int, private val type: MealType) {

    fun calculatePriceFor(count: Int) = (price * count)

    fun isName(menuName: String) = (name == menuName)

    fun isType(mealType: MealType) = (mealType == type)

    override fun toString() = name
}