package com.magicalrice.adolph.kmovie

import com.magicalrice.adolph.kmovie.utils.Utils
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun test_Reverence() {
        var money = Utils.getRevenue(10000000)
        assertEquals(money,"$,10000,000")
    }
}
