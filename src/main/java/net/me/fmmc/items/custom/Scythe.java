package net.me.fmmc.items.custom;

import net.me.fmmc.Main;
import net.me.fmmc.component.ModDataComponents;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

import java.util.List;

public class Scythe extends SwordItem {

    public Scythe(ToolMaterial toolMaterial, Settings settings) {


        super(toolMaterial, settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        RightClickAction(user, world, hand);
        return super.use(world, user, hand);
    }

    public void RightClickAction(PlayerEntity player, World world, Hand hand) {
        Main.LOGGER.info("RIGHT CLICK with SCYTHE!");
    }

    public void LeftClickAction(PlayerEntity player, World world, Hand hand, Entity target) {
        Main.LOGGER.info("LEFT CLICK with SCYTHE!");
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {

        tooltip.add(Text.translatable("item.fmmc.scythe.ability1").formatted(Formatting.GOLD));
        tooltip.add(Text.translatable("item.fmmc.scythe.ability2").formatted(Formatting.GOLD));
        tooltip.add(Text.translatable("item.fmmc.scythe.ultimate").formatted(Formatting.GOLD));


        super.appendTooltip(stack, context, tooltip, type);
    }

    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        if (world.isClient) return;

        // do ability logic here




    }
}
