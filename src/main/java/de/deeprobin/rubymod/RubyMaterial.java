package de.deeprobin.rubymod;

import net.minecraft.item.ToolMaterial;
import net.minecraft.recipe.Ingredient;

class RubyMaterial implements ToolMaterial {
    @Override
    public int getDurability() {
        return 2048;
    }

    @Override
    public float getMiningSpeed() {
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
