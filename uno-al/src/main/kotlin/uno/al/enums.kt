package uno.al

import org.lwjgl.openal.AL10
import org.lwjgl.openal.AL11
import org.lwjgl.openal.ALC10
import org.lwjgl.openal.ALC11

/*
 * OpenAL Helpers
 *
 * Copyright (c) 2011 by Chris Robinson <chris.kcat@gmail.com>
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

/* This file contains routines to help with some menial OpenAL-related tasks,
 * such as opening a device and setting up a context, closing the device and
 * destroying its context, converting between frame counts and byte lengths,
 * finding an appropriate buffer format, and getting readable strings for
 * channel configs and sample types. */

//const char *FormatName(ALenum format)
//{
//    switch(format)
//    {
//        case AL_FORMAT_MONO8: return "Mono, U8";
//        case AL_FORMAT_MONO16: return "Mono, S16";
//        case AL_FORMAT_STEREO8: return "Stereo, U8";
//        case AL_FORMAT_STEREO16: return "Stereo, S16";
//    }
//    return "Unknown Format";
//}

inline class ALCstringQuery(val i: Int) {
    companion object {
        val DEFAULT_DEVICE_SPECIFIER = ALCstringQuery(ALC10.ALC_DEFAULT_DEVICE_SPECIFIER)
        val DEVICE_SPECIFIER = ALCstringQuery(ALC10.ALC_DEVICE_SPECIFIER)
        val EXTENSIONS = ALCstringQuery(ALC10.ALC_EXTENSIONS)
        // ALC11
        val DEFAULT_ALL_DEVICES_SPECIFIER = ALCstringQuery(ALC11.ALC_DEFAULT_ALL_DEVICES_SPECIFIER)
        val ALL_DEVICES_SPECIFIER = ALCstringQuery(ALC11.ALC_ALL_DEVICES_SPECIFIER)
        val CAPTURE_DEVICE_SPECIFIER = ALCstringQuery(ALC11.ALC_CAPTURE_DEVICE_SPECIFIER)
        val CAPTURE_DEFAULT_DEVICE_SPECIFIER = ALCstringQuery(ALC11.ALC_CAPTURE_DEFAULT_DEVICE_SPECIFIER)
    }
}

inline class ALCerror(val i: Int) {
    companion object {
        val NO_ERROR = ALCerror(ALC10.ALC_NO_ERROR)
        val INVALID_DEVICE = ALCerror(ALC10.ALC_INVALID_DEVICE)
        val INVALID_CONTEXT = ALCerror(ALC10.ALC_INVALID_CONTEXT)
        val INVALID_ENUM = ALCerror(ALC10.ALC_INVALID_ENUM)
        val INVALID_VALUE = ALCerror(ALC10.ALC_INVALID_VALUE)
        val OUT_OF_MEMORY = ALCerror(ALC10.ALC_OUT_OF_MEMORY)
    }
}

inline class ALerror(val i: Int) {
    companion object {
        val NO_ERROR = ALerror(AL10.AL_NO_ERROR)
        val INVALID_NAME = ALerror(AL10.AL_INVALID_NAME)
        val INVALID_ENUM = ALerror(AL10.AL_INVALID_ENUM)
        val INVALID_VALUE = ALerror(AL10.AL_INVALID_VALUE)
        val INVALID_OPERATION = ALerror(AL10.AL_INVALID_OPERATION)
        val OUT_OF_MEMORY = ALerror(ALC10.ALC_OUT_OF_MEMORY)
    }
}

inline class ALstringQuery(val i: Int) {
    companion object {
        val VERSION = ALstringQuery(AL10.AL_VERSION)
        val RENDERER = ALstringQuery(AL10.AL_RENDERER)
        val VENDOR = ALstringQuery(AL10.AL_VENDOR)
        val EXTENSIONS = ALstringQuery(AL10.AL_EXTENSIONS)
    }
}

inline class DistanceModel(val i: Int) {
    companion object {
        val NONE = DistanceModel(AL10.AL_NONE)
        val INVERSE_DISTANCE = DistanceModel(AL10.AL_INVERSE_DISTANCE)
        val INVERSE_DISTANCE_CLAMPED = DistanceModel(AL10.AL_INVERSE_DISTANCE_CLAMPED)
        val LINEAR_DISTANCE = DistanceModel(AL11.AL_LINEAR_DISTANCE)
        val LINEAR_DISTANCE_CLAMPED = DistanceModel(AL11.AL_LINEAR_DISTANCE_CLAMPED)
        val EXPONENT_DISTANCE = DistanceModel(AL11.AL_EXPONENT_DISTANCE)
        val EXPONENT_DISTANCE_CLAMPED = DistanceModel(AL11.AL_EXPONENT_DISTANCE_CLAMPED)
    }
}

inline class SourceParam(val i: Int) {
    companion object {
        // Float
        val CONE_INNER_ANGLE = SourceParam(AL10.AL_CONE_INNER_ANGLE)
        val CONE_OUTER_ANGLE = SourceParam(AL10.AL_CONE_OUTER_ANGLE)
        val PITCH = SourceParam(AL10.AL_PITCH)
        val CONE_OUTER_GAIN = SourceParam(AL10.AL_CONE_OUTER_GAIN)
        val GAIN = SourceParam(AL10.AL_GAIN)
        val REFERENCE_DISTANCE = SourceParam(AL10.AL_REFERENCE_DISTANCE)
        val ROLLOFF_FACTOR = SourceParam(AL10.AL_ROLLOFF_FACTOR)
        val MAX_DISTANCE = SourceParam(AL10.AL_MAX_DISTANCE)
        // Vec3
        val DIRECTION = SourceParam(AL10.AL_DIRECTION)
        val POSITION = SourceParam(AL10.AL_POSITION)
        val VELOCITY = SourceParam(AL10.AL_VELOCITY)
        // Boolean
        val LOOPING = SourceParam(AL10.AL_LOOPING)
        // AlBuffer
        val BUFFER = SourceParam(AL10.AL_BUFFER)
        // Source State
        val SOURCE_STATE = SourceParam(AL10.AL_SOURCE_STATE)
        // Source Type
        val SOURCE_TYPE = SourceParam(AL10.AL_SOURCE_TYPE)
    }
}

inline class SourceState(val i: Int) {
    companion object {
        val initial = SourceState(AL10.AL_INITIAL)
        val playing = SourceState(AL10.AL_PLAYING)
        val paused = SourceState(AL10.AL_PAUSED)
        val stopped = SourceState(AL10.AL_STOPPED)
    }
}

inline class SourceType(val i: Int) {
    companion object {
        val undetermined = SourceType(AL11.AL_UNDETERMINED)
        val static = SourceType(AL11.AL_STATIC)
        val streaming = SourceType(AL11.AL_STREAMING)
    }
}