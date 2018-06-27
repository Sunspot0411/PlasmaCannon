package com.ice2670.plasmacannon.tileentities;

import com.ice2670.plasmacannon.blocks.BlockTorpedoLauncher;
import com.ice2670.plasmacannon.entity.EntityLargePlasmaBall;
import com.ice2670.plasmacannon.entity.FakeEntityLargePlasmaBall;
import com.ice2670.plasmacannon.util.handlers.SoundsHandler;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import valkyrienwarfare.api.IPhysicsEntity;
import valkyrienwarfare.api.IPhysicsEntityManager;
import valkyrienwarfare.api.TransformType;

public class TileEntityTorpedoLauncher extends TileEntity
{
    int powerfactor = 25;
    public void launchtorpedo(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn)
    {
        EnumFacing enumfacing = (EnumFacing) state.getValue(BlockTorpedoLauncher.FACING);
        double d0 = pos.getX() + 0.5D + (float) enumfacing.getFrontOffsetX();
        double d1 = pos.getY() + 0.4D + (float) enumfacing.getFrontOffsetY();
        double d2 = pos.getZ() + 0.5D + (float) enumfacing.getFrontOffsetZ();



        if(worldIn.isRemote) {
            Vec3d playview = playerIn.getLookVec();
            IPhysicsEntity ship = IPhysicsEntityManager.INSTANCE.getPhysicsEntityFromShipSpace(worldIn, pos);
            if (ship != null) {
                playview = ship.rotateVector(playview, TransformType.GLOBAL_TO_SUBSPACE);
            }
            FakeEntityLargePlasmaBall faketorpedo = new FakeEntityLargePlasmaBall(worldIn, d0, d1, d2, powerfactor);
            faketorpedo.shoot(playview.x, playview.y, playview.z,2, 0.01F);
            worldIn.spawnEntity(faketorpedo);
            worldIn.playSound(playerIn, pos, SoundsHandler.TILEENTITY_TILEENTITYPLASMACANNON_FIRECANNON, SoundCategory.BLOCKS,1.0F, 1.0F);
        }else if(!worldIn.isRemote)
        {
            Vec3d playview2 = playerIn.getLookVec();
            EntityLargePlasmaBall entitytorpedo = new EntityLargePlasmaBall(worldIn, d0, d1, d2, powerfactor);
            entitytorpedo.shoot(playview2.x, playview2.y, playview2.z,2, 0.01F);
            worldIn.spawnEntity(entitytorpedo);
            worldIn.playSound(playerIn, pos, SoundsHandler.TILEENTITY_TILEENTITYPLASMACANNON_FIRECANNON, SoundCategory.BLOCKS,1.0F, 1.0F);
        }
    }
}
