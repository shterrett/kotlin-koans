package ii_collections

fun example2(list: List<Int>) {

    val isZero: (Int) -> Boolean = { it == 0 }

    val hasZero: Boolean = list.any(isZero)

    val allZeros: Boolean = list.all(isZero)

    val numberOfZeros: Int = list.count(isZero)

    val firstPositiveNumber: Int? = list.firstOrNull { it > 0 }
}

fun Customer.isFrom(city: City): Boolean {
  return this.city == city
}

fun customerIsFrom(city: City): (Customer) -> Boolean {
  return { customer: Customer -> customer.isFrom(city) }
}

fun Shop.checkAllCustomersAreFrom(city: City): Boolean {
  return this.customers.all(customerIsFrom(city))
}

fun Shop.hasCustomerFrom(city: City): Boolean {
  return this.customers.any(customerIsFrom(city))
}

fun Shop.countCustomersFrom(city: City): Int {
  return this.customers.count(customerIsFrom(city))
}

fun Shop.findFirstCustomerFrom(city: City): Customer? {
  return this.customers.find(customerIsFrom(city))
}
