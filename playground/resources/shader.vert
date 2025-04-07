#version 410 core

layout(location = 0) in vec3 vp;
layout(location = 1) in vec3 vc;
uniform float time;
out vec3 pos;
out vec3 col;

void main() {
    col = vc;

    pos = vp;
    pos.y += sin(time) / 2;
    pos.x += cos(time) / 2;

    gl_Position = vec4(pos, 1.0);
}