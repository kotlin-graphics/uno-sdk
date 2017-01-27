import com.jogamp.common.net.Uri

/**
 * Created by GBarbieri on 27.01.2017.
 */

val String.uri
    get() = Uri.valueOf(javaClass.getResource(this))