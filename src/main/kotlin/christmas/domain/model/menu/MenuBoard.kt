package christmas.domain.model.menu

class MenuBoard(private val menus: List<Menu>) {

    fun findOrNull(menuName: String): Menu? {
        return menus.find { menu -> menu.isName(menuName) }
    }
}