package uno.debug

import com.jogamp.opengl.GL2ES2.GL_DEBUG_SEVERITY_NOTIFICATION
import com.jogamp.opengl.GL2ES2.GL_DEBUG_SEVERITY_LOW
import com.jogamp.opengl.GLDebugMessage
import com.jogamp.opengl.GLDebugListener


/**
 * Created by elect on 13/02/17.
 */

class GlDebugOutput : GLDebugListener {

    var source = 0
    var type = 0
    var id = 0
    var severity = 0
    var length = 0
    var message: String? = null
    var received = false

    constructor()

    constructor(source: Int, type: Int, severity: Int) {
        this.source = source
        this.type = type
        this.severity = severity
        this.message = null
        this.id = -1

    }

    constructor(message: String, id: Int) {
        this.source = -1
        this.type = -1
        this.severity = -1
        this.message = message
        this.id = id
    }

    override fun messageSent(event: GLDebugMessage) {

        if (event.dbgSeverity == GL_DEBUG_SEVERITY_LOW || event.dbgSeverity == GL_DEBUG_SEVERITY_NOTIFICATION)
            println("GlDebugOutput.messageSent(): $event")
        else
            System.err.println("GlDebugOutput.messageSent(): " + event)

        if (null != message && message == event.dbgMsg && id == event.dbgId)
            received = true
        else if (0 <= source && source == event.dbgSource && type == event.dbgType && severity == event.dbgSeverity)
            received = true
    }
}