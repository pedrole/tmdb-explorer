package com.example.lealp.tmdbexplorer


import org.apache.commons.lang3.StringUtils
import org.apache.commons.lang3.time.DateUtils
import org.junit.Test

import org.junit.Assert.*
import java.util.*

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
    fun testParseDate(){
        val a = ""
        //val parseDate = DateUtils.parseDate(a, "yyyy","")
        val year = StringUtils.substringBefore(a, "-")
        val b = 5



        Date()



    }
}
