package com.jbuelow.mc.tasercraft;

import com.jbuelow.mc.tasercraft.event.GameStartEventRegister;
import com.jbuelow.mc.tasercraft.event.TakeDamageEventRegister;
import com.jbuelow.mc.tasercraft.hardware.ArduinoTaser;
import javax.annotation.PostConstruct;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@Mod(
    modid = Tasercraft.MOD_ID,
    name = Tasercraft.MOD_NAME,
    version = Tasercraft.VERSION
)
@SideOnly(Side.CLIENT)
public class Tasercraft {

  public static final String MOD_ID = "tasercraft";
  public static final String MOD_NAME = "Tasercraft";
  public static final String VERSION = "2019.3-1.3.2";

  /**
   * This is the instance of your mod as created by Forge. It will never be null.
   */
  @Mod.Instance(MOD_ID)
  public static Tasercraft INSTANCE;

  /**
   * This is the first initialization event. Register tile entities here. The registry events below
   * will have fired prior to entry to this method.
   */
  @Mod.EventHandler
  public void preinit(FMLPreInitializationEvent event) {

  }

  /**
   * This is the second initialization event. Register custom recipes
   */
  @Mod.EventHandler
  public void init(FMLInitializationEvent event) {

  }

  /**
   * This is the final initialization event. Register actions from other mods here
   */
  @Mod.EventHandler
  public void postinit(FMLPostInitializationEvent event) {
    ArduinoTaser at = new ArduinoTaser("COM4");
    MinecraftForge.EVENT_BUS.register(new TakeDamageEventRegister(at));
    MinecraftForge.EVENT_BUS.register(new GameStartEventRegister(at));
  }

  /**
   * Forge will automatically look up and bind blocks to the fields in this class based on their
   * registry name.
   */
  @GameRegistry.ObjectHolder(MOD_ID)
  public static class Blocks {
      /*
          public static final MySpecialBlock mySpecialBlock = null; // placeholder for special block below
      */
  }

  /**
   * Forge will automatically look up and bind items to the fields in this class based on their
   * registry name.
   */
  @GameRegistry.ObjectHolder(MOD_ID)
  public static class Items {
      /*
          public static final ItemBlock mySpecialBlock = null; // itemblock for the block above
          public static final MySpecialItem mySpecialItem = null; // placeholder for special item below
      */
  }

  /**
   * This is a special class that listens to registry events, to allow creation of mod blocks and
   * items at the proper time.
   */
  @Mod.EventBusSubscriber
  public static class ObjectRegistryHandler {

    /**
     * Listen for the register event for creating custom items
     */
    @SubscribeEvent
    public static void addItems(RegistryEvent.Register<Item> event) {
           /*
             event.getRegistry().register(new ItemBlock(Blocks.myBlock).setRegistryName(MOD_ID, "myBlock"));
             event.getRegistry().register(new MySpecialItem().setRegistryName(MOD_ID, "mySpecialItem"));
            */
    }

    /**
     * Listen for the register event for creating custom blocks
     */
    @SubscribeEvent
    public static void addBlocks(RegistryEvent.Register<Block> event) {
           /*
             event.getRegistry().register(new MySpecialBlock().setRegistryName(MOD_ID, "mySpecialBlock"));
            */
    }
  }
    /* EXAMPLE ITEM AND BLOCK - you probably want these in separate files
    public static class MySpecialItem extends Item {

    }

    public static class MySpecialBlock extends Block {

    }
    */
}
