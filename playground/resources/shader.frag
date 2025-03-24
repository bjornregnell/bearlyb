#version 410 core

uniform float time;

out vec4 frag_colour;

void main() {
    frag_colour = vec4(sin(time) * 0.5 + 0.5, cos(time) * 0.5 + 0.5, sin(time + 1) * 0.5 + 0.5, 1.0);
    // frag_colour = vec4(0.5, 0.0, 0.5, 1.0);
}