package mekanism.client.gui;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import mekanism.common.tile.TileEntityElectricMachine;
import net.minecraft.entity.player.InventoryPlayer;

@SideOnly(Side.CLIENT)
public class GuiEnrichmentChamber extends GuiElectricMachine
{
	public GuiEnrichmentChamber(InventoryPlayer inventory, TileEntityElectricMachine tentity)
	{
		super(inventory, tentity);
	}
}
