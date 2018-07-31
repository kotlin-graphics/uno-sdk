# unofficial-opengl-SDK

[![Build Status](https://travis-ci.org/kotlin-graphics/uno-sdk.svg?branch=master)](https://travis-ci.org/kotlin-graphics/uno-sdk) 
[![license](https://img.shields.io/badge/License-MIT-orange.svg)](https://github.com/kotlin-graphics/uno-sdk/blob/master/LICENSE) 
![](https://reposs.herokuapp.com/?path=kotlin-graphics/uno-sdk&color=yellow) 
[![Release](https://jitpack.io/v/kotlin-graphics/uno-sdk.svg)](https://jitpack.io/#kotlin-graphics/uno-sdk) 
[![Slack Status](http://slack.kotlinlang.org/badge.svg)](http://slack.kotlinlang.org/)

This is kind of a small suite, born and shaped around GL, it includes the gln dependencies, such as [unsigned support](https://github.com/elect86/kotlin-unsigned), [glm](https://github.com/kotlin-graphics/glm) and [gli](https://github.com/kotlin-graphics/gli). Its main usage is basically as wrapper for the lwjgl glfw binding.

A kind of a [gln](https://github.com/kotlin-graphics/glm) for glfw. So, code more compact, clear and intuitive. You can have up and running a whole gl clear example in just a couple of lines:

            glfw.init("3.3")

            val window = GlfwWindow(1280, 720, "ImGui Lwjgl OpenGL3 example").apply {
                init()
            }
            
            glClearColor(1f, 0f, 0f, 0f)

            window.loop {
                glClear(GL_COLOR_BUFFER_BIT)
            }

One cool feature is that it supports multiple listeners of the same type, like `CharCallback`:

    var charCallback: CharCallbackT? = null
       get() = charCallbacks.getOrfirst(defaultKey)
       set(value) {
           charCallbacks[defaultKey] = value
           field = value
       }
    val charCallbacks = sortedMapOf<String, CharCallbackT>()
    val nCharCallback = GLFWCharCallbackI { _, codePoint -> charCallbacks.values.forEach { it(codePoint) } }
    
`nCharCallback` where `n` stays for native is the only and real callback

`charCallback` is just a comfortable interface to automatically set/get a single callback

`charCallbacks` is the `SortedMap` containing all the current callbacks

I've been chosen that so they can be easier ordered, the default callback has always this key:

`val defaultKey = "0 - default"`

I actually asked for this multi-callback in the native glfw, but in the meanwhile we can already use it. And this is also faster because it's all on the jvm!
    
Plus some other small utils like:
- methods for allocating very easily many different type of buffers from a lot of different data type, such as different arrays and so on.
- an cap class for a deep and complete resume about a machine opengl capabilities
- matrixStack for [glm](https://github.com/kotlin-graphics/glm)
- glsl utils (to refresh)
- an experimental kotlin stlib on intBuffers. So that you can for example `textureName.forEach(::glDestroyTexture)`
- mousePole, an util for camera management
- and attempt to port stb completely on jvm (unfinished)
- timer util

And lately it also includes a counterpart of gln for vulkan, [vkk](https://github.com/kotlin-graphics/vkk), plus an util for making short-live allocations easy and free, [appBuffer](https://github.com/kotlin-graphics/appBuffer).


Don't hesitate to contribute to the project by submitting [issues](https://github.com/kotlin-graphics/uno-sdk/issues) or [pull requests](https://github.com/kotlin-graphics/uno-sdk/pulls) for bugs and features. Any feedback is welcome at [elect86@gmail.com](mailto://elect86@gmail.com).



## Credits:

- [Farid Zakaria](https://github.com/fzakaria) for [ascii85](https://github.com/fzakaria/ascii85)
- [Jogamp](http://jogamp.org/) for BufferedImage flipping 
