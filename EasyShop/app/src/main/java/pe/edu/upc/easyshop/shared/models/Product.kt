package pe.edu.upc.easyshop.shared.models

data class Product(
    val name: String,
    val price: Double,
    val image: String,
    val description: String
)

val products = listOf(
    Product(
        name = "Essence Mascara Lash Princess",
        price = 9.9,
        image = "https://cdn.dummyjson.com/product-images/beauty/essence-mascara-lash-princess/1.webp",
        description = "The Essence Mascara Lash Princess is a popular mascara known for its volumizing and lengthening effects. Achieve dramatic lashes with this long-lasting and cruelty-free formula."
    ),
    Product(
        name = "Eyeshadow Palette with Mirror",
        price = 19.9,
        image = "https://cdn.dummyjson.com/product-images/beauty/eyeshadow-palette-with-mirror/1.webp",
        description = "The Eyeshadow Palette with Mirror offers a versatile range of eyeshadow shades for creating stunning eye looks. With a built-in mirror, it's convenient for on-the-go makeup application."
    )
)