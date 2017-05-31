package uno

import io.kotlintest.specs.StringSpec
import uno.stb.stb

//import uno.stb.stb

class Stb : StringSpec() {

    init {

        var tc = "testing compression test quick test voila woohoo what the hell".toCharArray()

        "compression" {

            val res = stb.compress(tc)
            print("")
        }
    }
}