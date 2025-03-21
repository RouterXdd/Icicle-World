package ice.graphics;

import arc.*;
import arc.graphics.*;
import arc.graphics.gl.*;
import arc.util.*;
import mindustry.Vars;
import mindustry.graphics.*;

import static mindustry.graphics.Shaders.*;
import static mindustry.Vars.*;

public class IceShaders {
    public static BlockSurfaceShader methane;
    public static CacheLayer.ShaderLayer methaneLayer;
    public static void init() {
        if (!headless) {
            methane = new BlockSurfaceShader("methane");
        }
            CacheLayer.add(methaneLayer = new CacheLayer.ShaderLayer(methane));
    }
    public static class BlockSurfaceShader extends ModSurfaceShader {
        public BlockSurfaceShader(String frag) {
            super(frag);
        }

        @Override
        public String texture() {
            return "noiseAlpha";
        }
    }

    public static class ModSurfaceShader extends ModShader {
        Texture noiseTex;

        public ModSurfaceShader(String frag) {
            super(frag, "screenspace");
            loadNoise();
        }

        public String texture() {
            return "noise";
        }

        public void loadNoise() {
            Core.assets.load("sprites/" + texture() + ".png", Texture.class).loaded = t -> {
                t.setFilter(Texture.TextureFilter.linear);
                t.setWrap(Texture.TextureWrap.repeat);
            };
        }

        @Override
        public void apply() {
            setUniformf("u_campos", Core.camera.position.x - Core.camera.width / 2, Core.camera.position.y - Core.camera.height / 2);
            setUniformf("u_resolution", Core.camera.width, Core.camera.height);
            setUniformf("u_time", Time.time);

            if (hasUniform("u_noise")) {
                if (noiseTex == null) {
                    noiseTex = Core.assets.get("sprites/" + texture() + ".png", Texture.class);
                }

                noiseTex.bind(1);
                renderer.effectBuffer.getTexture().bind(0);

                setUniformi("u_noise", 1);
            }
        }
    }

    public static class ModShader extends Shader {
        public ModShader(String frag, String vert) {
            super(Vars.tree.get("shaders/" + vert + ".vert"), Vars.tree.get("shaders/" + frag + ".frag"));
        }
    }
}
