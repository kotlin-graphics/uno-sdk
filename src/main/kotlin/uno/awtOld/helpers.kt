package uno.awtOld

fun atLeast32(major: Int, minor: Int) = major == 3 && minor >= 2 || major > 3

fun atLeast30(major: Int, minor: Int) = major == 3 && minor >= 0 || major > 3

fun validVersionGL(major: Int, minor: Int) =
        (major == 0 && minor == 0)
                || // unspecified gets highest supported version on Nvidia
                (major >= 1 && minor >= 0) && (major != 1 || minor <= 5) && (major != 2 || minor <= 1) && (major != 3 || minor <= 3)
                && (major != 4 || minor <= 5)

fun validVersionGLES(major: Int, minor: Int) =
        (major == 0 && minor == 0) || // unspecified gets 1.1 on Nvidia
                (major >= 1 && minor >= 0) && (major != 1 || minor <= 1) && (major != 2 || minor <= 0)

typealias WglContext = Long