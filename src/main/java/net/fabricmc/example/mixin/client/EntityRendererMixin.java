package net.fabricmc.example.mixin.client;

import net.fabricmc.example.ExampleMod;
import net.fabricmc.example.config.DroplightConfig;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ItemEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import net.fabricmc.example.render.BeamRenderer;

import static net.fabricmc.example.render.BeamRenderer.renderBeam;

@Mixin({EntityRenderer.class})
public abstract class EntityRendererMixin<T extends Entity> {
    @Inject(
            method = {"render"},
            at = {@At("HEAD")}
    )
    public void render(T entity, float f, float g, MatrixStack poseStack, VertexConsumerProvider buffer, int packedLight, CallbackInfo info) {
        if (entity instanceof ItemEntity) {
            ItemEntity itemEntity = (ItemEntity)entity;
            MatrixStack newPoseStack = new MatrixStack();
            newPoseStack.loadIdentity();
            newPoseStack.multiplyPositionMatrix(poseStack.peek().getPositionMatrix());
            ExampleMod.VISIBLE_ITEMS.put(itemEntity, newPoseStack);

            if (itemEntity.isOnGround()) {
                if (!DroplightConfig.shouldRenderBeam(itemEntity.getStack())) {
                    return;
                }
                BeamRenderer.renderBeam(itemEntity, ExampleMod.VISIBLE_ITEMS.get(itemEntity), itemEntity.world.getTime(), 0);
            }
        }

    }
}