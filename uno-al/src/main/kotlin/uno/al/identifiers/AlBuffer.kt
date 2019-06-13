package uno.al.identifiers

import kool.Adr
import kool.adr
import kool.rem
import java.nio.IntBuffer

inline class AlBuffer(val name: Int)

inline class AlBuffers(val names: IntBuffer) {

    val rem: Int
        get() = names.rem

    val adr: Adr
        get() = names.adr

}
