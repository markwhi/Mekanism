package mekanism.common.inventory.container;

import mekanism.api.gas.IGasItem;
import mekanism.common.inventory.slot.SlotEnergy.SlotDischarge;
import mekanism.common.inventory.slot.SlotStorageTank;
import mekanism.common.recipe.RecipeHandler;
import mekanism.common.recipe.RecipeHandler.Recipe;
import mekanism.common.tile.TileEntityChemicalOxidizer;
import mekanism.common.util.ChargeUtils;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerChemicalOxidizer extends Container
{
	private TileEntityChemicalOxidizer tileEntity;

	public ContainerChemicalOxidizer(InventoryPlayer inventory, TileEntityChemicalOxidizer tentity)
	{
		tileEntity = tentity;
		addSlotToContainer(new Slot(tentity, 0, 26, 36));
		addSlotToContainer(new SlotDischarge(tentity, 1, 155, 5));
		addSlotToContainer(new SlotStorageTank(tentity, null, true, 2, 155, 25));

		int slotX;

		for(slotX = 0; slotX < 3; ++slotX)
		{
			for(int slotY = 0; slotY < 9; ++slotY)
			{
				addSlotToContainer(new Slot(inventory, slotY + slotX * 9 + 9, 8 + slotY * 18, 84 + slotX * 18));
			}
		}

		for(slotX = 0; slotX < 9; ++slotX)
		{
			addSlotToContainer(new Slot(inventory, slotX, 8 + slotX * 18, 142));
		}

		tileEntity.open(inventory.player);
		tileEntity.openChest();
	}

	@Override
	public void onContainerClosed(EntityPlayer entityplayer)
	{
		super.onContainerClosed(entityplayer);

		tileEntity.close(entityplayer);
		tileEntity.closeChest();
	}

	@Override
	public boolean canInteractWith(EntityPlayer entityplayer)
	{
		return tileEntity.isUseableByPlayer(entityplayer);
	}

	@Override
	public ItemStack transferStackInSlot(EntityPlayer player, int slotID)
	{
		ItemStack stack = null;
		Slot currentSlot = (Slot)inventorySlots.get(slotID);

		if(currentSlot != null && currentSlot.getHasStack())
		{
			ItemStack slotStack = currentSlot.getStack();
			stack = slotStack.copy();

			if(RecipeHandler.getItemToGasOutput(slotStack, false, Recipe.CHEMICAL_OXIDIZER.get()) != null)
			{
				if(!mergeItemStack(slotStack, 0, 1, true))
				{
					return null;
				}
			}
			else if(ChargeUtils.canBeDischarged(slotStack))
			{
				if(slotID != 1)
				{
					if(!mergeItemStack(slotStack, 1, 2, false))
					{
						return null;
					}
				}
				else if(slotID == 1)
				{
					if(!mergeItemStack(slotStack, 3, inventorySlots.size(), true))
					{
						return null;
					}
				}
			}
			else if(slotStack.getItem() instanceof IGasItem)
			{
				if(slotID != 0 && slotID != 1 && slotID != 2)
				{
					if(!mergeItemStack(slotStack, 2, 3, false))
					{
						return null;
					}
				}
				else {
					if(!mergeItemStack(slotStack, 3, inventorySlots.size(), true))
					{
						return null;
					}
				}
			}
			else {
				if(slotID >= 3 && slotID <= 29)
				{
					if(!mergeItemStack(slotStack, 30, inventorySlots.size(), false))
					{
						return null;
					}
				}
				else if(slotID > 30)
				{
					if(!mergeItemStack(slotStack, 3, 29, false))
					{
						return null;
					}
				}
				else {
					if(!mergeItemStack(slotStack, 3, inventorySlots.size(), true))
					{
						return null;
					}
				}
			}

			if(slotStack.stackSize == 0)
			{
				currentSlot.putStack((ItemStack)null);
			}
			else {
				currentSlot.onSlotChanged();
			}

			if(slotStack.stackSize == stack.stackSize)
			{
				return null;
			}

			currentSlot.onPickupFromSlot(player, slotStack);
		}

		return stack;
	}
}
