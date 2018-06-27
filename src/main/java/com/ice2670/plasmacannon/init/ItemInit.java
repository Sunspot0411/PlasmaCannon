package com.ice2670.plasmacannon.init;

import com.ice2670.plasmacannon.items.ItemBase;
import com.ice2670.plasmacannon.items.ItemCompositeArmor;
import net.minecraft.item.Item;

import java.util.ArrayList;
import java.util.List;

public class ItemInit
{
    public static final List<Item> ITEMS = new ArrayList<Item>();

    public static final Item INGOT_KEY = new ItemBase("ingot_key");

    public static final Item INGOT_ENERGYREADER = new ItemBase("ingot_energyreader");

    public static final Item ITEM_COMPOSITEARMOR = new ItemCompositeArmor("item_compositearmor");

    public static final Item COMPOSITEMATERIAL = new ItemBase("compositematerial");

    public static final Item DENSE_COMPOSITEMATERIAL = new ItemBase("dense_compositematerial");

    public static final Item COMPOSITEARMORMATERIAL = new ItemBase("composite_armor_material");

    public static final Item SMALL_OBSIDIAN = new ItemBase("small_obsidian");

    public static final Item TORPEDO = new ItemBase("torpedo");
}

