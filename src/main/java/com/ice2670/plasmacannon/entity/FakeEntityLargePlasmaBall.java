package com.ice2670.plasmacannon.entity;


import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.datafix.DataFixer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.ParametersAreNonnullByDefault;

public class FakeEntityLargePlasmaBall extends EntityPlasmaBall
{
    public double explosionPower = 0.5*powerFactorf;
    public int damagefactor =10* powerFactorf;

    private static final DataParameter<Boolean> INVULNERABLE = EntityDataManager.<Boolean>createKey(net.minecraft.entity.projectile.EntityWitherSkull.class, DataSerializers.BOOLEAN);

    public FakeEntityLargePlasmaBall(World worldIn)
    {
        super(worldIn);
        this.setSize(0.3125F, 0.3125F);
    }

    public FakeEntityLargePlasmaBall(World worldIn, double x, double y, double z, int powerfactor)
    {
        super(worldIn, x, y, z, powerfactor);
        this.setSize(0.3125F, 0.3125F);
    }

    public static void registerFixesLargePlasmaBall(DataFixer fixer)
    {
        EntityPlasmaBall.registerFixesPlasmaBall(fixer, "PlasmaBall");
    }

    /**
     * Return the motion factor for this projectile. The factor is multiplied by the original motion.
     */


    @SideOnly(Side.CLIENT)
    public FakeEntityLargePlasmaBall(World worldIn, EntityLivingBase throwerIn, int powerfactor2)
    {
        super(worldIn, throwerIn, powerfactor2);
        this.setSize(0.3125F, 0.3125F);
    }

    /**
     * Returns true if the entity is on fire. Used by render to add the fire effect on rendering.
     */
    public boolean isBurning()
    {
        return true;
    }

    /**
     * Explosion resistance of a block relative to this entity
     */

    /**
     * Called when this EntityFireball hits a block or entity.
     */
    protected void onImpact(RayTraceResult result)
    {
        this.setDead();
    }



    /**
     * Returns true if other Entities should be prevented from moving through this Entity.
     */
    public boolean canBeCollidedWith()
    {
        return false;
    }

    @Override
    @ParametersAreNonnullByDefault
    public void onHitBlock(IBlockState hitBlock, BlockPos pos)
    {
        this.setDead();
    }

    /**
     * Called when the entity is attacked.
     */
    public boolean attackEntityFrom(DamageSource source, float amount)
    {
        return false;
    }

    protected void entityInit()
    {
        this.dataManager.register(INVULNERABLE, Boolean.valueOf(false));
    }

    /**
     * Return whether this skull comes from an invulnerable (aura) wither boss.
     */
    public boolean isInvulnerable()
    {
        return ((Boolean)this.dataManager.get(INVULNERABLE)).booleanValue();
    }

    /**
     * Set whether this skull comes from an invulnerable (aura) wither boss.
     */
    public void setInvulnerable(boolean invulnerable)
    {
        this.dataManager.set(INVULNERABLE, Boolean.valueOf(invulnerable));
    }

    protected boolean isFireballFiery()
    {
        return false;
    }
}
