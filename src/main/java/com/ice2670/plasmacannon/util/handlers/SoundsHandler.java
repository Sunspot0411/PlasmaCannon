package com.ice2670.plasmacannon.util.handlers;

import com.ice2670.plasmacannon.util.Reference;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

public class SoundsHandler
{
    public static SoundEvent TILEENTITY_TILEENTITYPLASMACANNON_FIRECANNON;

    public static void registerSounds()
    {
        TILEENTITY_TILEENTITYPLASMACANNON_FIRECANNON = registerSound("block.plasmacannon.firecannon");
    }

    private static SoundEvent registerSound(String name)
    {
        ResourceLocation location = new ResourceLocation(Reference.MODID, name);
        SoundEvent event = new SoundEvent(location);
        event.setRegistryName(name);
        ForgeRegistries.SOUND_EVENTS.register(event);
        return event;
    }
}
