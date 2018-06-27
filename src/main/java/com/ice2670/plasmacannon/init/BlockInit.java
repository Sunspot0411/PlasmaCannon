package com.ice2670.plasmacannon.init;

import com.ice2670.plasmacannon.blocks.BlockBase;
import com.ice2670.plasmacannon.blocks.BlockCompositeArmor;
import com.ice2670.plasmacannon.blocks.BlockPlasmaCannon;
import com.ice2670.plasmacannon.blocks.BlockTorpedoLauncher;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

import java.util.ArrayList;
import java.util.List;

public class BlockInit
{
    public static final List<Block> BLOCKS = new ArrayList<Block>();

    public static final Block BLOCK_PLASMAGENERATOR = new BlockBase("block_plasmagenerator", Material.IRON);

    public static final Block BLOCK_PLASMA_CANNON = new BlockPlasmaCannon("plasma_cannon");

    public static final Block BLOCK_COMPOSITEARMOR = new BlockCompositeArmor("block_compositearmor");

    public static final Block BLOCK_TORPEDOLAUNCHER = new BlockTorpedoLauncher("block_torpedo_launcher");


}
