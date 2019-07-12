// Attributes
#define POSITION    0
#define TEXT_COORD   1
#define COLOR       2
#define NORMAL      3
#define UV_RED      4
#define UV_GREEN    5
#define UV_BLUE     6

// Outputs
#define FRAG_COLOR  0

precision highp float;
precision highp int;
layout (std140, column_major) uniform;
//layout (std430, column_major) buffer; // no ssbo in <4.3