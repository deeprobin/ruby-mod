package de.deeprobin.rubymod.mixin;

import de.deeprobin.rubymod.beacon.BeaconInventory;
import de.deeprobin.rubymod.RubyMod;
import de.deeprobin.rubymod.miximpl.InventorySetterMixinInterface;
import net.minecraft.container.*;
import net.minecraft.container.BeaconContainer.SlotPayment;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(BeaconContainer.class)
public abstract class MixinBeaconContainer extends Container {

    @Mutable
    @Shadow
    @Final
    private Inventory paymentInv;

    @Mutable
    @Shadow @Final private BeaconContainer.SlotPayment paymentSlot;

    protected MixinBeaconContainer(ContainerType<?> type, int syncId) {
        super(type, syncId);
    }

    @Inject(at = @At("TAIL"), method = "<init>(ILnet/minecraft/inventory/Inventory;Lnet/minecraft/container/PropertyDelegate;Lnet/minecraft/container/BlockContext;)V")
    public void init(int syncId, Inventory inventory, PropertyDelegate propertyDelegate, BlockContext blockContext, CallbackInfo info) {
        this.paymentInv = new BeaconInventory();
        Slot paymentSlot = this.slots.get(0);
        InventorySetterMixinInterface mixin = (InventorySetterMixinInterface) paymentSlot;
        mixin.setInventory(this.paymentInv);
    }

    @Mixin(Slot.class)
    static abstract class SlotMixin implements InventorySetterMixinInterface {
        @Shadow @Mutable @Final public Inventory inventory;

        @Override
        public void setInventory(Inventory inventory) {
            this.inventory = inventory;
        }

    }

    @Mixin(SlotPayment.class)
    static abstract class SlotPaymentMixin extends Slot {

        public SlotPaymentMixin(Inventory inventory, int i, int j, int k) {
            super(inventory, i, j, k);
        }

        @Inject(at = @At("HEAD"), method = "canInsert", cancellable = true)
        public void canInsert(ItemStack stack, CallbackInfoReturnable<Boolean> info) {
            Item item = stack.getItem();
            if(item == RubyMod.RUBY) {
                info.setReturnValue(true);
            }
        }
    }
}
