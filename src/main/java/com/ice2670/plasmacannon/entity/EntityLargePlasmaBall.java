package com.ice2670.plasmacannon.entity;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.datafix.DataFixer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;
import net.minecraftforge.event.ForgeEventFactory;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.ParametersAreNonnullByDefault;

public class EntityLargePlasmaBall extends EntityPlasmaBall
{
    public double explosionPower = 0.5*powerFactorf;
    public int damagefactor =10* powerFactorf;

    private static final DataParameter<Boolean> INVULNERABLE = EntityDataManager.<Boolean>createKey(net.minecraft.entity.projectile.EntityWitherSkull.class, DataSerializers.BOOLEAN);

    public EntityLargePlasmaBall(World worldIn)
    {
        super(worldIn);
        this.setSize(0.3125F, 0.3125F);
    }

    public EntityLargePlasmaBall(World worldIn, double x, double y, double z, int powerfactor)
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
    public EntityLargePlasmaBall(World worldIn, EntityLivingBase throwerIn, int powerfactor2)
    {
        super(worldIn, throwerIn, powerfactor2);
        this.setSize(0.3125F, 0.3125F);
    }

    /**
     * Returns true if the entity is on fire. Used by render to add the fire effect on rendering.
     */
    public boolean isBurning()
    {
        return false;
    }

    /**
     * Explosion resistance of a block relative to this entity
     */
    public float getExplosionResistance(Explosion explosionIn, World worldIn, BlockPos pos, IBlockState blockStateIn)
    {
        float f = super.getExplosionResistance(explosionIn, worldIn, pos, blockStateIn);
        Block block = blockStateIn.getBlock();

        if (this.isInvulnerable() && block.canEntityDestroy(blockStateIn, worldIn, pos, this) && net.minecraftforge.event.ForgeEventFactory.onEntityDestroyBlock(this.thrower, pos, blockStateIn))
        {
            f = Math.min(0.8F, f);
        }

        double d=(double)f;
        float f1;

        if (explosionPower > 29) {

            f1 = (float) (0.03 * d);

        } else
            {
                f1 = f;
            }

        return f1;
    }

    /**
     * Called when this EntityFireball hits a block or entity.
     */
    protected void onImpact(RayTraceResult result)
    {
        if (!this.world.isRemote)
        {
            if (result.entityHit != null)
            {
                if (this.thrower != null)
                {
                    if (result.entityHit.attackEntityFrom(DamageSource.causeMobDamage(this.thrower), (float)this.damagefactor))
                    {
                        if (result.entityHit.isEntityAlive())
                        {
                            this.applyEnchantments(this.thrower, result.entityHit);
                            if (explosionPower > 29)
                            {
                                result.entityHit.attackEntityFrom(DamageSource.DRAGON_BREATH, 50*damagefactor);
                            }
                        }
                        else
                        {

                        }
                    }
                }
                else
                {
                    if (explosionPower > 29)
                    {
                        result.entityHit.attackEntityFrom(DamageSource.DRAGON_BREATH, 50*damagefactor);
                        result.entityHit.attackEntityFrom(DamageSource.MAGIC, 50*damagefactor);
                        result.entityHit.attackEntityFrom(DamageSource.LAVA, 50*damagefactor);
                    } else {
                        result.entityHit.attackEntityFrom(DamageSource.MAGIC, damagefactor);
                        result.entityHit.attackEntityFrom(DamageSource.LAVA, damagefactor);
                    }
                }

                if (result.entityHit instanceof EntityLivingBase)
                {
                    if (explosionPower > 29)
                    {
                        result.entityHit.attackEntityFrom(DamageSource.DRAGON_BREATH, 50*damagefactor);
                    }

                    ((EntityLivingBase)result.entityHit).setFire(30);

                }

                if (result.entityHit instanceof EntityPlayer)
                {
                    if (explosionPower > 29)
                    {
                        result.entityHit.attackEntityFrom(DamageSource.DRAGON_BREATH, 50*damagefactor);
                        result.entityHit.attackEntityFrom(DamageSource.MAGIC, 50*damagefactor);
                        result.entityHit.attackEntityFrom(DamageSource.LAVA, 50*damagefactor);
                    } else {
                        result.entityHit.attackEntityFrom(DamageSource.MAGIC, damagefactor);
                        result.entityHit.attackEntityFrom(DamageSource.LAVA, damagefactor);
                    }

                    ((EntityLivingBase)result.entityHit).setFire(30);

                }
            }

            this.world.newExplosion(this, this.posX, this.posY, this.posZ, (float)this.explosionPower, false, this.world.getGameRules().getBoolean("mobGriefing"));
            this.setDead();
        }
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
        if (!hitBlock.getMaterial().isSolid()) {
            // Go through non solid block
            return;
        } else if (explosionPower > 29) {
            getEntityWorld().destroyBlock(pos, false);
        }

        this.world.newExplosion(this, this.posX, this.posY, this.posZ, (float)this.explosionPower, false, this.world.getGameRules().getBoolean("mobGriefing"));
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
