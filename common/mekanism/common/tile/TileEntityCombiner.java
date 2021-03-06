package mekanism.common.tile;

import java.util.Map;

import mekanism.api.gas.Gas;
import mekanism.api.gas.GasRegistry;
import mekanism.api.gas.GasStack;
import mekanism.common.Mekanism;
import mekanism.common.block.BlockMachine.MachineType;
import mekanism.common.recipe.RecipeHandler.Recipe;
import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class TileEntityCombiner extends TileEntityAdvancedElectricMachine
{
	public TileEntityCombiner()
	{
		super("Combiner.ogg", "Combiner", new ResourceLocation("mekanism", "gui/GuiCombiner.png"), Mekanism.combinerUsage, 1, 200, MachineType.COMBINER.baseEnergy);
	}

	@Override
	public Map getRecipes()
	{
		return Recipe.COMBINER.get();
	}

	@Override
	public GasStack getItemGas(ItemStack itemstack)
	{
		if(itemstack.getItem() instanceof ItemBlock && itemstack.itemID == Block.cobblestone.blockID)
		{
			return new GasStack(GasRegistry.getGas("liquidStone"), 200);
		}

		return null;
	}

	@Override
	public boolean isValidGas(Gas gas)
	{
		return false;
	}
}
