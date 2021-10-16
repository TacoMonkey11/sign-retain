package com.umollu.signretain.mixin;

import net.minecraft.block.AbstractSignBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.BlockWithEntity;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.SignBlockEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.text.Text;
import net.minecraft.util.DyeColor;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(AbstractSignBlock.class)
public abstract class AbstractSignBlockMixin extends BlockWithEntity {
    protected AbstractSignBlockMixin(Settings settings) {
        super(settings);
    }

    @Override
    public void onPlaced(World world, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack itemStack) {
        super.onPlaced(world, pos, state, placer, itemStack);
        NbtCompound compoundTag = itemStack.getNbt();
        if (compoundTag != null && compoundTag.contains("Retained")) {
            NbtCompound compoundTag2 = compoundTag.getCompound("Retained");
            BlockEntity blockEntity = world.getBlockEntity(pos);
            if (blockEntity instanceof SignBlockEntity) {
                for(int i = 0; i < 4; ++i) {
                    String string = compoundTag2.getString("Text" + (i + 1));
                    Text text = Text.Serializer.fromJson(string.isEmpty() ? "\"\"" : string);
                    ((SignBlockEntity) blockEntity).setTextOnRow(i, text);
                }
                ((SignBlockEntity) blockEntity).setTextColor(DyeColor.byName(compoundTag2.getString("Color"), DyeColor.BLACK));
            }
        }
    }
}
