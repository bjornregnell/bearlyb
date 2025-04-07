#version 410 core

uniform float time;
in vec3 pos;
in vec3 col;

out vec4 frag_colour;

void main() {
    // frag_colour = vec4(0.5 * pos + vec3(0.5, 0.5, 0), 1.0);
    frag_colour = vec4(col, 1.0);
}