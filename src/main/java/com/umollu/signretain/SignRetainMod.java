package com.umollu.signretain;

import com.umollu.signretain.loot.condition.SignTextLootCondition;
import net.fabricmc.api.ModInitializer;
import net.minecraft.loot.condition.LootConditionType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class SignRetainMod implements ModInitializer {
    public static LootConditionType SIGN_TEXT;

    @Override
    public void onInitialize() {
        SIGN_TEXT = Registry.register(Registry.LOOT_CONDITION_TYPE, new Identifier("sign_text"), new LootConditionType(new SignTextLootCondition.Serializer()));
    }
}
