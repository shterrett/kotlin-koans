package iii_conventions

data class MyDate(val year: Int, val month: Int, val dayOfMonth: Int) {
  operator fun plus(num_intervals: NumIntervals): MyDate = addTimeIntervals(num_intervals.interval, num_intervals.times)
  operator fun plus(interval: TimeInterval): MyDate = addTimeIntervals(interval, 1)
}

operator fun MyDate.rangeTo(other: MyDate): DateRange = DateRange(this, other)

enum class TimeInterval {
    DAY,
    WEEK,
    YEAR;

    operator fun times(times: Int): NumIntervals = NumIntervals(this, times)
}

data class NumIntervals(val interval: TimeInterval, val times: Int)

class DateRange(val start: MyDate, val endInclusive: MyDate) : Iterable<MyDate> {
  override fun iterator(): Iterator<MyDate> {
    return DateRangeIterator(start, endInclusive)
  }
}

class DateRangeIterator(val start: MyDate, val endInclusive: MyDate) : Iterator<MyDate> {
  var nextDate: MyDate
  init {
    nextDate = start
  }

  override fun next(): MyDate {
    val currentDate = nextDate
    nextDate = currentDate.nextDay()
    return currentDate
  }

  override fun hasNext(): Boolean {
    return nextDate <= endInclusive
  }
}
