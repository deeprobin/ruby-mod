package de.deeprobin.ruby_mod;

import net.minecraft.item.ToolMaterial;
import net.minecraft.recipe.Ingredient;

final class RubyMaterial implements ToolMaterial {
    @Override
    public int getDurability() {
        return 2048;
    }

    @Override
    public float getMiningSpeedMultiplier() {
        return 10.0F;
    }

    @Override
    public float getAttackDamage() {
        return 4.0F;
    }

    @Override
    public int getMiningLevel() {
        return 3;
    }

    @Override
    public int getEnchantability() {
        return 9;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return Ingredient.ofItems(RubyMod.RUBY);
    }
}
