package com.ice2670.plasmacannon.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.util.EnumFacing;

public class BlockCompositeArmor extends BlockBase {
    public BlockCompositeArmor(String name) {
        super(name, Material.IRON);
        setSoundType(SoundType.METAL);
        setHardness(900.0F);
        setResistance(6000.0F);
        setHarvestLevel("pickaxe", 3);
    }
}
