package uno

import io.kotlintest.matchers.shouldBe
import io.kotlintest.matchers.shouldEqual
import io.kotlintest.matchers.shouldNotBe
import io.kotlintest.matchers.shouldThrow
import io.kotlintest.specs.StringSpec
import uno.convert.Ascii85
import java.nio.charset.StandardCharsets
import java.util.*


class ascii85 : StringSpec() {

    init {

        "illegal argument" { shouldThrow<IllegalArgumentException> { Ascii85.decode("") } }

        "wiki decode" {

            val encodedString = "9jqo^BlbD-BleB1DJ+*+F(f,q/0JhKF<GL>Cj@.4Gp\$d7F!,L7@<6@)/0JDEF<G%<+EV:2F!,O<DJ+*.@<*" +
                    "K0@<6L(Df-\\0Ec5e;DffZ(EZee.Bl.9pF\"AGXBPCsi+DGm>@3BB/F*&OCAfu2/AKYi(DIb:@FD,*)+C]U=@3BN#EcY" +
                    "f8ATD3s@q?d\$AftVqCh[NqF<G:8+EV:.+Cf>-FD5W8ARlolDIal(DId<j@<?3r@:F%a+D58'ATD4\$Bl@l3De:,-DJs`8AR" +
                    "oFb/0JMK@qB4^F!,R<AKZ&-DfTqBG%G>uD.RTpAKYo'+CT/5+Cei#DII?(E,9)oF*2M7/c"

            val solution = "Man is distinguished, not only by his reason, but by this singular passion from other " +
                    "animals, which is a lust of the mind, that by a perseverance of delight in the continued and " +
                    "indefatigable generation of knowledge, exceeds the short vehemence of any carnal pleasure."

            solution shouldBe String(Ascii85.decode(encodedString))
        }

        "wiki decode new lines" {

            val encodedString = "9jqo^BlbD-BleB1DJ+*+F(f,q/0JhKF<GL>Cj@.4Gp\$d7F!,L7@<6@)/0JDEF<G%<+EV:2F!,\n" +
            "O<DJ+*.@<*K0@<6L(Df-\\0Ec5e;DffZ(EZee.Bl.9pF\"AGXBPCsi+DGm>@3BB/F*&OCAfu2/AKY\n" +
                    "i(DIb:@FD,*)+C]U=@3BN#EcYf8ATD3s@q?d\$AftVqCh[NqF<G:8+EV:.+Cf>-FD5W8ARlolDIa\n" +
                    "l(DId<j@<?3r@:F%a+D58'ATD4\$Bl@l3De:,-DJs`8ARoFb/0JMK@qB4^F!,R<AKZ&-DfTqBG%G\n" +
                    ">uD.RTpAKYo'+CT/5+Cei#DII?(E,9)oF*2M7/c"

            val solution = "Man is distinguished, not only by his reason, but by this singular passion from other animals, "+
            "which is a lust of the mind, that by a perseverance of delight in the continued and indefatigable generation "+
                    "of knowledge, exceeds the short vehemence of any carnal pleasure."

            solution shouldBe String(Ascii85.decode(encodedString))
        }

        "wiki decode ignore spaces" {

            val encodedString = "9jqo^BlbD-BleB1DJ+*+F    (f,q/0JhKF<GL>Cj@.4Gp\$d7F!,L7@<6@)/0JDEF<G%<+EV:2F!,\n" +
                    "O<DJ+*.@<*K0@<6L(Df-\\0Ec5e;DffZ(EZee.  Bl.9pF\"AGXBPCsi+DGm>@3BB/F*&OCAfu2/AKY\n" +
                    "i(DIb:@FD,*)+C]U=@3BN#EcYf8ATD3s@q?d\$AftVqCh[NqF<G:8+EV:.+Cf>-FD5W8ARlolDIa\n" +
                    "l(DId<j@<?3r@:F%a+D58'ATD4\$Bl@l3De:,-DJs`8ARoFb/0JMK@qB4^F!,R<AKZ&-DfTqBG%G\n" +
                    ">uD.RTpAKYo'+CT/5+Cei#DII?(E,9)oF*2M7/c"

            val solution = "Man is distinguished, not only by his reason, but by this singular passion from other animals, "+
                    "which is a lust of the mind, that by a perseverance of delight in the continued and indefatigable generation "+
                    "of knowledge, exceeds the short vehemence of any carnal pleasure."

            solution shouldBe String(Ascii85.decode(encodedString))
        }

        "encode" {

            val solution = "9jqo^BlbD-BleB1DJ+*+F(f,q/0JhKF<GL>Cj@.4Gp\$d7F!,L7@<6@)/0JDEF<G%<+EV:2F!," +
            "O<DJ+*.@<*K0@<6L(Df-\\0Ec5e;DffZ(EZee.Bl.9pF\"AGXBPCsi+DGm>@3BB/F*&OCAfu2/AKY" +
                    "i(DIb:@FD,*)+C]U=@3BN#EcYf8ATD3s@q?d\$AftVqCh[NqF<G:8+EV:.+Cf>-FD5W8ARlolDIa" +
                    "l(DId<j@<?3r@:F%a+D58'ATD4\$Bl@l3De:,-DJs`8ARoFb/0JMK@qB4^F!,R<AKZ&-DfTqBG%G" +
                    ">uD.RTpAKYo'+CT/5+Cei#DII?(E,9)oF*2M7/c"

            val decodedString = "Man is distinguished, not only by his reason, but by this singular passion from other animals, "+
            "which is a lust of the mind, that by a perseverance of delight in the continued and indefatigable generation "+
                    "of knowledge, exceeds the short vehemence of any carnal pleasure."

            solution shouldBe Ascii85.encode(decodedString.toByteArray())
        }

        "negative bytes" {

            val randoms = byteArrayOf(-127, -127, -127, -127)
            val encoded = Ascii85.encode(randoms)

            Arrays.equals(randoms, Ascii85.decode(encoded)) shouldBe true
        }
//
//        "overflow" {
//
////            val N = 0x23C34567
//            val N = 0x03C34567
//            val sb = StringBuilder(N)
//
//            for (i in 0 until N)
//                sb.append('-')
//
//            val encodedString = sb.toString()
//
//            Ascii85.decode(encodedString) shouldNotBe null
//        }
    }
}
