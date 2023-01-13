package net.fabricmc.example.mixin;

//import net.fabricmc.example.registry.VisualityParticles;
//import net.fabricmc.example.util.ParticleUtils;
import net.fabricmc.fabric.mixin.client.rendering.EntityRenderersMixin;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.ItemEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ItemEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ItemEntityRenderer.class)
public abstract class ItemEntityMixin extends EntityRenderer<ItemEntity> {
    protected ItemEntityMixin(EntityRendererFactory.Context ctx) {
        super(ctx);
    }

    @Inject(method = "render", at = @At("HEAD"), cancellable = true)
    public void render(ItemEntity itemEntity, float f, float g, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i, CallbackInfo ci) {
            ItemRenderer renderer = MinecraftClient.getInstance().getItemRenderer();
            float scale = 3.74f;
            matrixStack.scale(scale, scale, scale);
    }

//    @Inject(method = "render", at = @At("TAIL"))
//    private void tick(CallbackInfo ci) {
//        var client = MinecraftClient.getInstance();
//        if(world.isClient && ticksDelay != 0) ticksDelay--;
//        spawnSparkles(2);
//    }
//
//    @Unique
//    private void spawnSparkles(int shinyLevel) {
//        if(shinyLevel > 0) {
//            if(this.random.nextInt(20 - shinyLevel) == 0) {
//                double x = random.nextFloat() * 2 - 1;
//                double y = random.nextFloat();
//                double z = random.nextFloat() * 2 - 1;
//                ParticleUtils.add(world, VisualityParticles.SPARKLE, this.getX() + x, this.getY() + y + 1, this.getZ() + z);
//            }
//        }
//    }
}
