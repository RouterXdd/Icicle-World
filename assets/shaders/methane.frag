#define HIGHP

#define C2 vec3(84.0, 101.0, 116.0) / 100.0
#define NSCALE 40.0

uniform sampler2D u_texture;
uniform sampler2D u_noise;

uniform vec2 u_campos;
uniform vec2 u_resolution;
uniform float u_time;

varying vec2 v_texCoords;

void main() {
    vec2 c = v_texCoords.xy;
    vec2 coords = vec2(c.x * u_resolution.x + u_campos.x, c.y * u_resolution.y + u_campos.y);

    float btime = u_time / 800.0;
    float wave = abs(sin(coords.x * 3 + coords.y) + 0.4 * sin(3 * coords.x) + 0.3 * sin(3.0 * coords.y)) / 21.0;
    float noise = wave + (texture2D(u_noise, vec2(coords.x, coords.y / 3.8) / NSCALE + vec2(btime) * vec2(0.3, -0.6)).a + texture2D(u_noise, vec2(coords.x, coords.y / 3.0) / NSCALE + vec2(btime * 1.3) * vec2(0.5, 0.1)).a) / 1.9;
    vec4 color = texture2D(u_texture, c);

    if (noise < 0.49 && noise < 0.58) {
        color.rgb = C2;
    }

    gl_FragColor = color;
}