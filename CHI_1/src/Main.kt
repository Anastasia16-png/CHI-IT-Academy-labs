//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
// інтерфейси
interface Chair {
    fun sitOn(): String
}
interface Sofa {
    fun lieOn(): String
}
interface CoffeeTable {
    fun use(): String
}

// продукти для Вікторіанського стилю
class VictorianChair : Chair {
    override fun sitOn() = "Сіли на вікторіанське крісло"
}
class VictorianSofa : Sofa {
    override fun lieOn() = "Лежите на вікторіанському дивані"
}
class VictorianCoffeeTable : CoffeeTable {
    override fun use() = "Використовується вікторіанський кавовий столик"
}

// для Модерн стилю
class ModernChair : Chair {
    override fun sitOn() = "Сіли на модерн крісло"
}
class ModernSofa : Sofa {
    override fun lieOn() = "Лежите на модерн дивані"
}
class ModernCoffeeTable : CoffeeTable {
    override fun use() = "Використовується модерн кавовий столик"
}

//абстрактна фабрика меблів
interface FurnitureFactory {
    fun createChair(): Chair
    fun createSofa(): Sofa
    fun createCoffeeTable(): CoffeeTable
}

// певні фабрики для Вікторіанського стилю
class VictorianFurnitureFactory : FurnitureFactory {
    override fun createChair(): Chair = VictorianChair()
    override fun createSofa(): Sofa = VictorianSofa()
    override fun createCoffeeTable(): CoffeeTable = VictorianCoffeeTable()
}

// певні фабрики для Модерн стилю
class ModernFurnitureFactory : FurnitureFactory {
    override fun createChair(): Chair = ModernChair()
    override fun createSofa(): Sofa = ModernSofa()
    override fun createCoffeeTable(): CoffeeTable = ModernCoffeeTable()
}

fun main() {
    // ф-ція приймає фабрику та взаємодіє з продуктами
    fun orderFurniture(factory: FurnitureFactory) {
        val chair = factory.createChair()
        val sofa = factory.createSofa()
        val coffeeTable = factory.createCoffeeTable()

        println(chair.sitOn())
        println(sofa.lieOn())
        println(coffeeTable.use())
    }
    println("Замовлення меблів Вікторіанського стилю")
    orderFurniture(VictorianFurnitureFactory())
    println("\nЗамовлення меблів Модерн стилю")
    orderFurniture(ModernFurnitureFactory())
}
