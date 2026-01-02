package io.github.ex_supercharger.random_shtick.item;

import java.util.function.Function;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;

import io.github.ex_supercharger.random_shtick.Random_Shtick;

public class ModItems {
    public static Item register(String name, Function<Item.Properties, Item> itemFactory, Item.Properties properties) {
        //Create the itemKey
        ResourceKey<Item> itemKey = ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(Random_Shtick.MOD_ID, name));

        //Create the item instance
        Item item = itemFactory.apply(properties.setId(itemKey));

        //Register the item
        Registry.register(BuiltInRegistries.ITEM, itemKey, item);

        return item;
    }

    //Create a custom creative tab
    public static final ResourceKey<CreativeModeTab> RANDOM_SHTICK_TAB_KEY = ResourceKey.create(BuiltInRegistries.CREATIVE_MODE_TAB.key(), Identifier.fromNamespaceAndPath(Random_Shtick.MOD_ID, "random_shtick_tab"));
    public static final CreativeModeTab RANDOM_SHTICK_TAB = FabricItemGroup.builder()
            .icon(() -> new ItemStack(ModItems.CAMERA_STICK))
            .title(Component.translatable("itemGroup.random_shtick"))
            .build();

    //Register Items
    public static final Item CAMERA_STICK = register("camera_stick", Item::new, new Item.Properties());

    public static void initialize() {
        //Register the creative tab
        Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB, RANDOM_SHTICK_TAB_KEY, RANDOM_SHTICK_TAB);

        //Register the items to the creative tab
        ItemGroupEvents.modifyEntriesEvent(RANDOM_SHTICK_TAB_KEY).register(itemGroup -> {
            itemGroup.accept(CAMERA_STICK);
        });
    }
}
