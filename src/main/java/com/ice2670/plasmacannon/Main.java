package com.ice2670.plasmacannon;

import com.ice2670.plasmacannon.init.ItemInit;
import com.ice2670.plasmacannon.proxy.CommonProxy;
import com.ice2670.plasmacannon.util.Reference;
import com.ice2670.plasmacannon.util.handlers.RegistryHandler;
import ic2.api.recipe.IRecipeInput;
import ic2.api.recipe.Recipes;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

import java.util.Collections;


@Mod(modid = Reference.MODID, name = Reference.NAME, version = Reference.VERSION)
public class Main
{
    @Instance
    public static Main instance;

    @SidedProxy(clientSide = Reference.CLIENT, serverSide = Reference.COMMON)
    public static CommonProxy proxy;

    @EventHandler
    public static void preInit(FMLPreInitializationEvent event) {
        RegistryHandler.preInitRegistries();}
    @EventHandler
    public static void init(FMLInitializationEvent event) {
        IRecipeInput input = Recipes.inputFactory.forStack(new ItemStack(ItemInit.COMPOSITEMATERIAL));
        ItemStack output = new ItemStack(ItemInit.DENSE_COMPOSITEMATERIAL);
        Recipes.compressor.addRecipe(input, Collections.singletonList(output), null, false);

        IRecipeInput input2 = Recipes.inputFactory.forStack(new ItemStack(ItemInit.COMPOSITEARMORMATERIAL));
        ItemStack output2 = new ItemStack(ItemInit.ITEM_COMPOSITEARMOR);
        Recipes.metalformerRolling.addRecipe(input2, Collections.singletonList(output2), null, false);

        IRecipeInput input3 = Recipes.inputFactory.forStack(new ItemStack(Blocks.OBSIDIAN));
        ItemStack output3 = new ItemStack(ItemInit.SMALL_OBSIDIAN);
        Recipes.compressor.addRecipe(input3, Collections.singletonList(output3), null, false);

        RegistryHandler.initRegistries();}
    @EventHandler
    public static void postInit(FMLPostInitializationEvent event) {RegistryHandler.postInitRegistries();}

}
