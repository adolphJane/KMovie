package com.magicalrice.adolph.kmovie

import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import com.magicalrice.adolph.kmovie.data.remote.Tmdb
import com.magicalrice.adolph.kmovie.utils.Utils

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getTargetContext()
        assertEquals("com.magicalrice.adolph.kmovie", appContext.packageName)
    }

    @Test
    fun assertMovie() {
        var money = Utils.getRevenue(10000000)
        assertEquals(money,"$,10000,000")
    }
}
