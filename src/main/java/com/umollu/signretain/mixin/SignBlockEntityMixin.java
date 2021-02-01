package com.umollu.signretain.mixin;

import com.umollu.signretain.ISignBlockEntity;
import net.minecraft.block.entity.SignBlockEntity;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(SignBlockEntity.class)
public class SignBlockEntityMixin implements ISignBlockEntity {

    @Shadow @Final private Text[] text;

    public Text getTextOnRow(int row) {
        return this.text[row];
    }
}