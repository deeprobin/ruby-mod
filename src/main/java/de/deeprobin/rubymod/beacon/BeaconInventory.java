package de.deeprobin.rubymod.beacon;

import de.deeprobin.rubymod.RubyMod;
import net.minecraft.inventory.BasicInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;

public class BeaconInventory extends BasicInventory {

    public BeaconInventory() {
        super(1);
    }

    public boolean isValidInvStack(int slot, ItemStack stack) {
        return stack.getItem() == RubyMod.RUBY || stack.getItem() == Items.EMERALD || stack.getItem() == Items.DIAMOND || stack.getItem() == Items.GOLD_INGOT || stack.getItem() == Items.IRON_INGOT;
    }

    public int getInvMaxStackAmount() {
            return 1;
        }

}
