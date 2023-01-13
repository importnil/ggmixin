package net.fabricmc.example.shader;

import net.minecraft.client.render.VertexFormats;
import com.google.common.collect.ImmutableMap;
import org.apache.commons.lang3.exception.ExceptionUtils;
import net.minecraft.resource.ResourceFactory;
import java.util.function.Supplier;
import net.minecraft.client.render.VertexFormat;
import net.minecraft.client.render.Shader;

public class BeamShaders
{
    private static Shader twoColorFadeShader;
    private static Shader twoColorFadeFlatShader;
    private static Shader twoColorFlatShader;
    public static final VertexFormat POSITION_TEX_COLOR0_COLOR1;

    public BeamShaders() {
        super();
    }

    public static Supplier<Shader> getTwoColorFade() {
        return () -> BeamShaders.twoColorFadeShader;
    }

    public static Supplier<Shader> getTwoColorFadeFlat() {
        return () -> BeamShaders.twoColorFadeFlatShader;
    }

    public static Supplier<Shader> getTwoColorFlat() {
        return () -> BeamShaders.twoColorFlatShader;
    }

    public static void init(final ResourceFactory provider) {
        if (BeamShaders.twoColorFadeShader != null && BeamShaders.twoColorFlatShader != null) {
            if (BeamShaders.twoColorFadeFlatShader != null) {
                return;
            }
        }
        try {
            BeamShaders.twoColorFadeShader = new Shader(provider, "droplight", BeamShaders.POSITION_TEX_COLOR0_COLOR1);
            BeamShaders.twoColorFadeFlatShader = new Shader(provider, "droplight_fade_flat", BeamShaders.POSITION_TEX_COLOR0_COLOR1);
            BeamShaders.twoColorFlatShader = new Shader(provider, "droplight_flat", BeamShaders.POSITION_TEX_COLOR0_COLOR1);
        }
        catch (final Exception e) {
            System.err.println(ExceptionUtils.getStackTrace(e));
        }
    }

    public static boolean ready() {
        return BeamShaders.twoColorFadeShader != null && BeamShaders.twoColorFlatShader != null && BeamShaders.twoColorFadeFlatShader != null;
    }

    static {
        BeamShaders.twoColorFadeShader = null;
        BeamShaders.twoColorFadeFlatShader = null;
        BeamShaders.twoColorFlatShader = null;
        POSITION_TEX_COLOR0_COLOR1 = new VertexFormat((ImmutableMap)ImmutableMap.builder().put("Position", VertexFormats.POSITION_ELEMENT).put("UV0", VertexFormats.UV_ELEMENT).put("Color0", VertexFormats.COLOR_ELEMENT).put("Color1", VertexFormats.COLOR_ELEMENT).build());
    }
}