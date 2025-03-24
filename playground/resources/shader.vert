#version 410 core

in vec3 vp;
uniform float time;
out vec3 pos;

void main() {
    pos = vp;
    pos.y += sin(time) / 2;
    pos.x += cos(time) / 2;

    gl_Position = vec4(pos, 1.0);
}