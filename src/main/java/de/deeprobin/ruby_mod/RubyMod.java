package de.deeprobin.ruby_mod;

import net.fabricmc.api.ModInitializer;
import net.minecraft.block.*;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.*;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.decorator.Decorator;
import net.minecraft.world.gen.decorator.RangeDecoratorConfig;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.ConfiguredFeatures;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.OreFeatureConfig;

/**
 * The mod initializer of the ruby mod
 *
 * @author Robin Lindner
 * @see net.fabricmc.api.ModInitializer
 */
public final class RubyMod implements ModInitializer {

    /**
     * The tool material for all tools of the ruby mod
     */
    public static final ToolMaterial TOOL_MATERIAL = new RubyMaterial();

    /**
     * The armor material for all armor components of the ruby mod
     */
    public static final ArmorMaterial ARMOR_MATERIAL = new RubyArmorMaterial();

    /**
     * The ruby swords purpose is to attack entities
     */
    public static final Item RUBY_SWORD = new SwordItem(TOOL_MATERIAL, 4, -1.8F, (new Item.Settings()).group(ItemGroup.COMBAT));

    /**
     * The ruby pickaxes purpose is to destroy hard blocks
     */
    public static final Item RUBY_PICKAXE = new RubyPickaxe(TOOL_MATERIAL, 2, -2.2F, (new Item.Settings()).group(ItemGroup.TOOLS));

    /**
     * The ruby axes purpose is to destroy wood-like blocks
     */
    public static final Item RUBY_AXE = new RubyAxe(TOOL_MATERIAL, 6.0F, -2.8F, (new Item.Settings()).group(ItemGroup.TOOLS));

    /**
     * The ruby shovels purpose is to destroy soft blocks like {@link Items#SAND} or {@link Items#DIRT}
     */
    public static final Item RUBY_SHOVEL = new ShovelItem(TOOL_MATERIAL, 2.5F, -2.8F, (new Item.Settings()).group(ItemGroup.TOOLS));

    /**
     * The ruby hoes purpose is to edit dirt to farmland
     */
    public static final Item RUBY_HOE = new RubyHoe(TOOL_MATERIAL, 0, -2.8F, (new Item.Settings()).group(ItemGroup.TOOLS));

    /**
     * The ruby helmet is the protection piece for the head
     */
    public static final Item RUBY_HELMET = new ArmorItem(ARMOR_MATERIAL, EquipmentSlot.HEAD, (new Item.Settings().group(ItemGroup.COMBAT)));

    /**
     * The ruby chest plate is the protection piece for the body.
     */
    public static final Item RUBY_CHESTPLATE = new ArmorItem(ARMOR_MATERIAL, EquipmentSlot.CHEST, (new Item.Settings().group(ItemGroup.COMBAT)));

    /**
     * The ruby leggings is the protection piece for the legs
     */
    public static final Item RUBY_LEGGINGS = new ArmorItem(ARMOR_MATERIAL, EquipmentSlot.LEGS, (new Item.Settings().group(ItemGroup.COMBAT)));

    /**
     * The ruby boots are the protection pieces for the boots. It protects you especially of fall damage.
     */
    public static final Item RUBY_BOOTS = new ArmorItem(ARMOR_MATERIAL, EquipmentSlot.FEET, (new Item.Settings().group(ItemGroup.COMBAT)));

    /**
     * The ruby horse armors purpose is to protect a horse of damage
     */
    public static final Item RUBY_HORSE_ARMOR = new HorseArmorItem(16, "ruby", (new Item.Settings()).maxCount(1).group(ItemGroup.MISC));

    /**
     * The main item of the ruby mod is the ruby - a precious gemstone.
     */
    public static final Item RUBY = new Item(new Item.Settings().group(ItemGroup.MATERIALS));

    /**
     * The ruby block is the block made out of rubies
     */
    public static final Block RUBY_BLOCK = new Block(Block.Settings.of(Material.METAL, MaterialColor.RED).strength(5.0F, 6.0F));

    /**
     * Ruby ores are in stone encapsulated rubies
     */
    public static final Block RUBY_ORE = new OreBlock(Block.Settings.of(Material.STONE).strength(3.0F, 3.0F));

    /**
     * This is the world generation feature for rubies
     */
    public static ConfiguredFeature<?, ?> RUBY_ORE_OVERWORLD = Feature.ORE.configure(
            new OreFeatureConfig(OreFeatureConfig.Rules.BASE_STONE_OVERWORLD, RUBY_ORE.getDefaultState(), 8)
    ).decorate(Decorator.RANGE.configure(
            new RangeDecoratorConfig(
                    0,
                    0,
                    16
            )
    )).spreadHorizontally();

    @Override
    public void onInitialize() {
        Registry.register(Registry.ITEM, new Identifier("ruby_mod", "ruby"), RUBY);
        Registry.register(Registry.ITEM, new Identifier("ruby_mod", "ruby_sword"), RUBY_SWORD);
        Registry.register(Registry.ITEM, new Identifier("ruby_mod", "ruby_pickaxe"), RUBY_PICKAXE);
        Registry.register(Registry.ITEM, new Identifier("ruby_mod", "ruby_axe"), RUBY_AXE);
        Registry.register(Registry.ITEM, new Identifier("ruby_mod", "ruby_shovel"), RUBY_SHOVEL);
        Registry.register(Registry.ITEM, new Identifier("ruby_mod", "ruby_hoe"), RUBY_HOE);
        Registry.register(Registry.ITEM, new Identifier("ruby_mod", "ruby_helmet"), RUBY_HELMET);
        Registry.register(Registry.ITEM, new Identifier("ruby_mod", "ruby_chestplate"), RUBY_CHESTPLATE);
        Registry.register(Registry.ITEM, new Identifier("ruby_mod", "ruby_leggings"), RUBY_LEGGINGS);
        Registry.register(Registry.ITEM, new Identifier("ruby_mod", "ruby_boots"), RUBY_BOOTS);
        Registry.register(Registry.ITEM, new Identifier("ruby_mod", "ruby_horse_armor"), RUBY_HORSE_ARMOR);
        Registry.register(Registry.BLOCK, new Identifier("ruby_mod", "ruby_block"), RUBY_BLOCK);
        Registry.register(Registry.BLOCK, new Identifier("ruby_mod", "ruby_ore"), RUBY_ORE);
        Registry.register(Registry.ITEM, new Identifier("ruby_mod", "ruby_block"), new BlockItem(RUBY_BLOCK, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
        Registry.register(Registry.ITEM, new Identifier("ruby_mod", "ruby_ore"), new BlockItem(RUBY_ORE, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));

        Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, new Identifier("ruby_mod", "ore_ruby_overworld"), RUBY_ORE_OVERWORLD);
    }
}
