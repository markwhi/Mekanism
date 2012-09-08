package net.uberkat.obsidian.common;

import java.util.ArrayList;
import java.util.Random;

import cpw.mods.fml.common.Side;
import cpw.mods.fml.common.asm.SideOnly;
import cpw.mods.fml.common.registry.BlockProxy;
import net.minecraft.src.*;

public class BlockEnrichmentChamber extends BlockMachine
{
    public BlockEnrichmentChamber(int par1)
    {
        super(par1);
    }
    
    @SideOnly(Side.CLIENT)
    public int getBlockTexture(IBlockAccess world, int x, int y, int z, int side)
    {
    	TileEntityMachine tileEntity = (TileEntityMachine)world.getBlockTileEntity(x, y, z);
        
        if(side == tileEntity.facing)
        {
        	return isActive(world, x, y, z) ? 8 : 9;
        }
        else {
        	return 2;
        }
    }
    
    public int getBlockTextureFromSide(int side)
    {
    	if(side == 3)
    	{
    		return 9;
    	}
    	else {
    		return 2;
    	}
    }

    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer entityplayer, int i1, float f1, float f2, float f3)
    {
        if (world.isRemote)
        {
            return true;
        }
        else
        {
            TileEntityEnrichmentChamber tileEntity = (TileEntityEnrichmentChamber)world.getBlockTileEntity(x, y, z);

            if (tileEntity != null)
            {
            	if(!entityplayer.isSneaking())
            	{
            		entityplayer.openGui(ObsidianIngots.instance, 21, world, x, y, z);
            	}
            	else {
            		return false;
            	}
            }

            return true;
        }
    }

	public TileEntity createNewTileEntity(World var1) 
	{
		return new TileEntityEnrichmentChamber();
	}
}