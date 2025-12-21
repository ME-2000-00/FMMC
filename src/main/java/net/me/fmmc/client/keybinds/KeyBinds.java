package net.me.fmmc.client.keybinds;

import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;

public class KeyBinds {
    public static KeyBinding ABILITY1, ABILITY2, ULTI;

    public static void register() {
        // block key for debugging
        ABILITY1 = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "Ability 1",
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_V,
                "Abilities FMMC"
        ));

        ABILITY2 = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "Ability 2",
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_C,
                "Abilities FMMC"
        ));

        ULTI = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "Ultimate",
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_X,
                "Abilities FMMC"
        ));
    }
}
