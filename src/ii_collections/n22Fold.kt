package ii_collections

fun example9() {
    val result = listOf(1, 2, 3, 4).fold(1, { partResult, element -> element * partResult })
    result == 24
}

// The same as
fun whatFoldDoes(): Int {
    var result = 1
    listOf(1, 2, 3, 4).forEach { element -> result = element * result}
    return result
}

fun Shop.getSetOfProductsOrderedByEachCustomer(): Set<Product> {
  val allOrderedProducts = customers.flatMap(Customer::orders)
                                    .flatMap(Order::products)
                                    .toSet()

  return customers.fold(allOrderedProducts, {
      orderedByAll, customer ->
        orderedByAll.intersect(customer.orders.flatMap(Order::products).toSet())
  })
}

fun <T> List<T>.fold1(f: (T, T) -> T): T? {
  val initial = first() ?: return null
  return drop(1).fold(initial, f)
}

fun <T> intersection(s: Set<T>, t: Set<T>): Set<T> = s.intersect(t)

val Customer.products: List<Product> get() {
  return orders.flatMap(Order::products)
}

fun Shop.getSetOfProductsOrderedByEachCustomer2(): Set<Product> {
  return customers.map(Customer::products)
                  .map(Collection<Product>::toSet)
                  .fold1(::intersection) ?: setOf()
}
