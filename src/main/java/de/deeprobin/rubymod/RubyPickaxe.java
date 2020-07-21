package de.deeprobin.rubymod;

import net.minecraft.item.PickaxeItem;
import net.minecraft.item.ToolMaterial;

final class RubyPickaxe extends PickaxeItem {
    public RubyPickaxe(ToolMaterial material, int attackDamage, float attackSpeed, Settings settings) {
        super(material, attackDamage, attackSpeed, settings);
    }
}
