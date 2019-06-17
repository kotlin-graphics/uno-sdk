#version 330 core

#include semantic.glsl

uniform sampler2D myTexture;

in vec2 uv;

layout (location = FRAG_COLOR) out vec4 outputColor;

void main()
{
   outputColor = texture(myTexture, uv);
}