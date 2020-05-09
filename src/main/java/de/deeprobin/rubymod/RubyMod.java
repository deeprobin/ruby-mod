package de.deeprobin.rubymod;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.registry.RegistryEntryAddedCallback;
import net.minecraft.block.*;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.*;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.decorator.Decorator;
import net.minecraft.world.gen.decorator.RangeDecoratorConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.OreFeatureConfig;

/**
 * The mod initializer of the ruby mod
 * @author Robin Lindner
 * @see net.fabricmc.api.ModInitializer
 */
public class RubyMod implements ModInitializer {

	public static final ToolMaterial TOOL_MATERIAL = new RubyMaterial();
	public static final ArmorMaterial ARMOR_MATERIAL = new RubyArmorMaterial();
	public static final Item RUBY_SWORD = new SwordItem(TOOL_MATERIAL, 4, -1.8F, (new Item.Settings()).group(ItemGroup.COMBAT));
	public static final Item RUBY_PICKAXE = new RubyPickaxe(TOOL_MATERIAL, 2, -2.2F, (new Item.Settings()).group(ItemGroup.TOOLS));
	public static final Item RUBY_AXE = new RubyAxe(TOOL_MATERIAL, 6.0F, -2.8F, (new Item.Settings()).group(ItemGroup.TOOLS));
	public static final Item RUBY_SHOVEL = new ShovelItem(TOOL_MATERIAL, 2.5F, -2.8F, (new Item.Settings()).group(ItemGroup.TOOLS));
	public static final Item RUBY_HOE = new HoeItem(TOOL_MATERIAL, 0.0F, (new Item.Settings()).group(ItemGroup.TOOLS));

	public static final Item RUBY_HELMET = new ArmorItem(ARMOR_MATERIAL, EquipmentSlot.HEAD, (new Item.Settings().group(ItemGroup.COMBAT)));
	public static final Item RUBY_CHESTPLATE = new ArmorItem(ARMOR_MATERIAL, EquipmentSlot.CHEST, (new Item.Settings().group(ItemGroup.COMBAT)));
	public static final Item RUBY_LEGGINGS = new ArmorItem(ARMOR_MATERIAL, EquipmentSlot.LEGS, (new Item.Settings().group(ItemGroup.COMBAT)));
	public static final Item RUBY_BOOTS = new ArmorItem(ARMOR_MATERIAL, EquipmentSlot.FEET, (new Item.Settings().group(ItemGroup.COMBAT)));

	public static final Item RUBY_HORSE_ARMOR = new HorseArmorItem(16, "ruby", (new Item.Settings()).maxCount(1).group(ItemGroup.MISC));

	public static final Item RUBY = new Item(new Item.Settings().group(ItemGroup.MATERIALS));
	public static final Block RUBY_BLOCK = new Block(Block.Settings.of(Material.METAL, MaterialColor.RED).strength(5.0F, 6.0F));
	public static final Block RUBY_ORE = new OreBlock(Block.Settings.of(Material.STONE).strength(3.0F, 3.0F));

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

		Registry.BIOME.forEach(this::handleBiome);
		RegistryEntryAddedCallback.event(Registry.BIOME).register((i, identifier, biome) -> handleBiome(biome));
	}

	private void handleBiome(Biome biome) {
		if(biome.getCategory() != Biome.Category.NETHER && biome.getCategory() != Biome.Category.THEEND) {
			int veinCount;
			if (biome.getCategory() == Biome.Category.MUSHROOM) {
				veinCount = 8;
			} else {
				veinCount = 1;
			}

			biome.addFeature(
					GenerationStep.Feature.UNDERGROUND_ORES,
					Feature.ORE.configure(
							new OreFeatureConfig(
									OreFeatureConfig.Target.NATURAL_STONE,
									RUBY_ORE.getDefaultState(),
									1 // ore vein size
							)).createDecoratedFeature(
							Decorator.COUNT_RANGE.configure(new RangeDecoratorConfig(
									veinCount, // veins per chunk
									0,
									3, // min y level
									16 // max y level
							))));
		}
	}
}
