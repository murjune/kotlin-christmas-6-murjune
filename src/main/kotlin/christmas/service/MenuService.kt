package christmas.service

import christmas.domain.model.menu.type.MealType
import christmas.domain.model.menu.Menu

object MenuService {

    fun provideMenus(): List<Menu> = listOf(
        Menu("양송이수프", 6000, MealType.APPETIZER),
        Menu("타파스", 5500, MealType.APPETIZER),
        Menu("시저샐러드", 8000, MealType.APPETIZER),
        Menu("티본스테이크", 55000, MealType.MAIN),
        Menu("바비큐립", 54000, MealType.MAIN),
        Menu("해산물파스타", 35000, MealType.MAIN),
        Menu("크리스마스파스타", 25000, MealType.MAIN),
        Menu("초코케이크", 15000, MealType.DESSERT),
        Menu("제로콜라", 3000, MealType.DRINK),
        Menu("레드와인", 60000, MealType.DRINK),
        Menu("샴페인", 25000, MealType.DRINK)
    )
}