package net.me.fmmc.items.custom;


import net.me.fmmc.component.ModDataComponents;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

import java.util.List;


public class Scythe extends SwordItem {

    public Scythe(ToolMaterial toolMaterial, Settings settings) {
        super(toolMaterial, settings);
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {

        tooltip.add(Text.translatable("item.fmmc.scythe.ability1").formatted(Formatting.GOLD));
        tooltip.add(Text.literal("this ability blocks all damage and has no cooldown when perfectly timed").formatted(Formatting.GOLD));
        tooltip.add(Text.literal("   "));

        tooltip.add(Text.translatable("item.fmmc.scythe.ability2").formatted(Formatting.GOLD));
        tooltip.add(Text.literal("this ability creates a barrage of slashes around the player damaging every entity inside of it besides the user  does 60% of entities max hp").formatted(Formatting.GOLD));
        tooltip.add(Text.literal("   "));

        tooltip.add(Text.translatable("item.fmmc.scythe.ultimate").formatted(Formatting.GOLD));
        tooltip.add(Text.literal("100% accuracy but has only one shot   Abilities also increase the kills counter").formatted(Formatting.GOLD));
        tooltip.add(Text.literal("   "));
        tooltip.add(Text.literal("          " + String.valueOf(stack.get(ModDataComponents.ULT_KILLS)) + " / 3  Kills").formatted(stack.getOrDefault(ModDataComponents.ULT_KILLS, 0) >= 3 ? Formatting.GREEN : Formatting.RED));


        super.appendTooltip(stack, context, tooltip, type);
    }
}
