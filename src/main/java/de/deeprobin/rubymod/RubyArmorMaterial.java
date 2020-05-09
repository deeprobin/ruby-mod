package de.deeprobin.rubymod;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.recipe.Ingredient;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;

class RubyArmorMaterial implements ArmorMaterial {

    private static final int[] BASE_DURABILITY = new int[]{13, 15, 16, 11};
    private static final int[] PROTECTION_AMOUNT = new int[]{2,4,3,2};


    public int getDurability(EquipmentSlot slot) {
        return BASE_DURABILITY[slot.getEntitySlotId()] * 40;
    }

    public int getProtectionAmount(EquipmentSlot slot) {
        return PROTECTION_AMOUNT[slot.getEntitySlotId()];
    }

    public int getEnchantability() {
        return 10;
    }

    public SoundEvent getEquipSound() {
        return SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND;
    }

    public Ingredient getRepairIngredient() {
        return Ingredient.ofItems(RubyMod.RUBY);
    }

    @Environment(EnvType.CLIENT)
    public String getName() {
        return "ruby";
    }

    public float getToughness() {
        return 0.0F;
    }
}