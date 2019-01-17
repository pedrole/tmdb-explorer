package com.example.lealp.tmdbexplorer

import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import java.text.DateFormat
import java.text.DecimalFormat
import java.text.NumberFormat
import java.util.*

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
        assertEquals("com.example.lealp.tmdbexplorer", appContext.packageName)
    }
    @Test
    fun testDeciamlFormat(){
        val numero = 2
        val a = DecimalFormat.getInstance().format(numero);
        val decimalFormat = DecimalFormat("#.00")

        val format = decimalFormat.format(numero)

        val instance = DecimalFormat.getInstance(Locale.US)
        instance.minimumFractionDigits

        val a1 = instance.format(numero);
        val b = DecimalFormat.getNumberInstance().format(numero);
        val format1 = DecimalFormat("0 min").format(143)



        Date()


    }
}
