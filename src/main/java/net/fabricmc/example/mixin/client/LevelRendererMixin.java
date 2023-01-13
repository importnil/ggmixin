package net.fabricmc.example.mixin.client;

import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import net.fabricmc.example.render.BeamRenderer;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.render.RenderPhase;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import net.minecraft.util.math.Matrix4f;
import net.minecraft.client.render.LightmapTextureManager;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.render.Camera;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.client.gl.ShaderEffect;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.entity.EntityRenderDispatcher;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Shadow;
import net.minecraft.client.render.BufferBuilderStorage;
import net.minecraft.client.render.WorldRenderer;
import org.spongepowered.asm.mixin.Mixin;

@Mixin({WorldRenderer.class})
public class LevelRendererMixin {
    @Shadow
    @Final
    private BufferBuilderStorage bufferBuilders;
    @Shadow
    @Final
    private EntityRenderDispatcher entityRenderDispatcher;
    @Shadow
    @Final
    private MinecraftClient client;
    @Shadow
    private ShaderEffect transparencyShader;

    @Inject(
            method = {"render"},
            at = {@At(
                    value = "INVOKE",
//                    target = "Lnet/minecraft/client/render/WorldRenderer;renderEntity()V",
                    shift = At.Shift.BEFORE
//                    target = "Lnet/minecraft/client/render/LevelRenderer;renderSnowAndRain(Lnet/minecraft/client/renderer/LightTexture;FDDD)V",
            )}
    )
    public void render(MatrixStack poseStack, float partialTicks, long l, boolean bl, Camera camera, GameRenderer gameRenderer, LightmapTextureManager lightTexture, Matrix4f matrix4f, CallbackInfo info) {
//        if (((WorldRenderer)(Object)this.transparencyShader != null)) {
//            System.out.println("nicht null");
////            RenderPhase.WEATHER_TARGET.method_23518();
//        }
//
//        RenderSystem.getModelViewStack().pop();
//        RenderSystem.applyModelViewMatrix();
//        lightTexture.enable();
//        BeamRenderer.renderBeams(partialTicks);
////        IDroplightParticleEngine particleEngine = (IDroplightParticleEngine)this.field_4088.particleManager;
////        if (particleEngine != null) {
////            particleEngine.renderSparkles(poseStack, this.field_20951.method_23000(), lightTexture, camera, partialTicks);
////        }
//
//        RenderSystem.getModelViewStack().push();
//        RenderSystem.getModelViewStack().multiplyPositionMatrix(poseStack.peek().getPositionMatrix());
//        RenderSystem.applyModelViewMatrix();
//        if (((WorldRenderer)(Object)this.transparencyShader != null)) {
////            RenderPhase.WEATHER_TARGET.method_23516();
//        }

    }
}
