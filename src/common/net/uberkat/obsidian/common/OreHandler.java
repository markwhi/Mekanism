package net.uberkat.obsidian.common;

import java.util.Random;

import net.minecraft.src.IChunkProvider;
import net.minecraft.src.ItemStack;
import net.minecraft.src.World;
import net.minecraft.src.WorldGenMinable;
import cpw.mods.fml.common.IWorldGenerator;

public class OreHandler implements IWorldGenerator
{
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider) 
	{
		switch(world.provider.worldType)
		{
			case 0:
				generateSurface(world, random, chunkX*16, chunkZ*16);
		}
	}
	
	/**
	 * Generate an ore in the overworld.
	 * @param world
	 * @param random
	 * @param chunkX
	 * @param chunkZ
	 */
	public void generateSurface(World world, Random random, int chunkX, int chunkZ)
	{
		if(ObsidianIngots.oreGenerationEnabled == true)
		{
			for (int i=0;i<3;i++)
			{
				int randPosX = chunkX + random.nextInt(16);
				int randPosY = random.nextInt(60);
				int randPosZ = chunkZ + random.nextInt(16);
				(new WorldGenMinable(new ItemStack(ObsidianIngots.MultiBlock, 1, 0).itemID, 16)).generate(world, random, randPosX, randPosY, randPosZ);
			}
		}
	}
}