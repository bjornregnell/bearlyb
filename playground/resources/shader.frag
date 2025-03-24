#version 410 core

uniform float time;
in vec3 pos;

out vec4 frag_colour;

void main() {
    frag_colour = vec4(pos + vec3(1.0) / 2, 1.0);
}