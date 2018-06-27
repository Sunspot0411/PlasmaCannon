package com.ice2670.plasmacannon.util.handlers;

import com.ice2670.plasmacannon.entity.EntityLargePlasmaBall;
import com.ice2670.plasmacannon.entity.EntityPlasmaBall;
import com.ice2670.plasmacannon.entity.render.RenderLargePlasmaBall;
import com.ice2670.plasmacannon.entity.render.RenderPlasmaBall;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.client.registry.RenderingRegistry;

public class RenderHandler
{
    public static void registerEntityRenders()
    {
        RenderingRegistry.registerEntityRenderingHandler(EntityPlasmaBall.class, new IRenderFactory<EntityPlasmaBall>()
        {
            @Override
            public Render<? super EntityPlasmaBall> createRenderFor(RenderManager manager)
            {
                return new RenderPlasmaBall(manager, 1F);
            }
        });

        RenderingRegistry.registerEntityRenderingHandler(EntityLargePlasmaBall.class, new IRenderFactory<EntityLargePlasmaBall>()
        {
            @Override
            public Render<? super EntityLargePlasmaBall> createRenderFor(RenderManager manager)
            {
                return new RenderLargePlasmaBall(manager);
            }
        });
    }
}
