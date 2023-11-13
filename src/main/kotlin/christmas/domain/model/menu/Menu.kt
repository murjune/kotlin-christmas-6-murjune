package christmas.domain.model.menu

import christmas.domain.type.MealType

data class Menu(val name: String, val price: Int, val type: MealType)