#version 410 core

in vec3 vp;
uniform float time;

void main() {
    vec3 pos = vp;
    pos.y += sin(time);
    pos.x += cos(time);

    gl_Position = vec4(pos, 1.0);
}