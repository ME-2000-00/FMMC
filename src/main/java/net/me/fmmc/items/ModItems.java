package net.me.fmmc.items;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.me.fmmc.Main;
import net.me.fmmc.component.ModDataComponents;
import net.me.fmmc.items.custom.MagicStaff;
import net.me.fmmc.items.custom.Scythe;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.AttributeModifierSlot;
import net.minecraft.component.type.AttributeModifiersComponent;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterials;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;

public class ModItems {

    public static final Item SCYTHE = registerItem("scythe",
            new Scythe(ToolMaterials.DIAMOND, new Item.Settings().maxCount(1).rarity(Rarity.EPIC)
                    .component(DataComponentTypes.ATTRIBUTE_MODIFIERS, AttributeModifiersComponent.builder().add(
                            net.minecraft.entity.attribute.EntityAttributes.GENERIC_ATTACK_DAMAGE,
                            new net.minecraft.entity.attribute.EntityAttributeModifier(
                                    Item.BASE_ATTACK_DAMAGE_MODIFIER_ID,
                                    12.0,
                                    EntityAttributeModifier.Operation.ADD_VALUE
                            ),
                                    AttributeModifierSlot.MAINHAND).build())
                    .component(ModDataComponents.IS_BLOCKING      , false)
                    .component(ModDataComponents.IS_SLASHING      , false)
                    .component(ModDataComponents.IS_ULTING        , false)
                    .component(ModDataComponents.COOLDOWN_BLOCKING, -1   )
                    .component(ModDataComponents.COOLDOWN_SLASHING, -1   )
                    .component(ModDataComponents.COOLDOWN_ULTING  , -1   )));

    public static final Item STAFF = registerItem("staff",
            new MagicStaff(new Item.Settings().maxCount(1)));


    //helper func
    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, Identifier.of(Main.MOD_ID, name), item);
    }

    public static void register() {
        Main.LOGGER.info("Registering Mod Items for " + Main.MOD_ID);

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.COMBAT).register(fabricItemGroupEntries -> {
            fabricItemGroupEntries.add(SCYTHE);
            fabricItemGroupEntries.add(STAFF);
        });
    }
}
