package de.deeprobin.rubymod.mixin;

import com.mojang.blaze3d.systems.RenderSystem;
import de.deeprobin.rubymod.RubyMod;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.screen.ingame.BeaconScreen;
import net.minecraft.client.gui.screen.ingame.ContainerScreen;
import net.minecraft.container.BeaconContainer;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(BeaconScreen.class)
@Environment(EnvType.CLIENT)
public abstract class MixinBeaconScreen extends ContainerScreen<BeaconContainer> {

    @Final
    @Shadow
    private static Identifier BG_TEX;

    public MixinBeaconScreen(BeaconContainer container, PlayerInventory playerInventory, Text name) {
        super(container, playerInventory, name);
    }

    @Inject(at = @At("HEAD"), cancellable = true, method = "drawBackground")
    protected void drawBackground(float delta, int mouseX, int mouseY, CallbackInfo info) {
        RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
        assert this.minecraft != null;
        this.minecraft.getTextureManager().bindTexture(BG_TEX);
        int i = (this.width - this.containerWidth) / 2;
        int j = (this.height - this.containerHeight) / 2;
        this.blit(i, j, 0, 0, this.containerWidth, this.containerHeight);
        this.itemRenderer.zOffset = 100.0F;
        this.itemRenderer.renderGuiItem(new ItemStack(RubyMod.RUBY), i + 42 - 22, j + 109);
        this.itemRenderer.renderGuiItem(new ItemStack(Items.EMERALD), i + 42, j + 109);
        this.itemRenderer.renderGuiItem(new ItemStack(Items.DIAMOND), i + 42 + 22, j + 109);
        this.itemRenderer.renderGuiItem(new ItemStack(Items.GOLD_INGOT), i + 42 + 44, j + 109);
        this.itemRenderer.renderGuiItem(new ItemStack(Items.IRON_INGOT), i + 42 + 66, j + 109);
        this.itemRenderer.zOffset = 0.0F;
        info.cancel();
    }

}
