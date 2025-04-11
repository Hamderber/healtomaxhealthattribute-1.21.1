package com.hamderber.healtomaxhealthattribute;

import org.slf4j.Logger;

import com.mojang.logging.LogUtils;

import net.minecraft.world.entity.player.Player;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.entity.EntityJoinLevelEvent;

@Mod(HealtoMaxHealthAttribute.MODID)
public class HealtoMaxHealthAttribute
{
    public static final String MODID = "healtomaxhealthattribute";
    public static final String HEAL_TAG = MODID + ":requires_heal_tag";
    
    public static final Logger LOGGER = LogUtils.getLogger();
    
    public HealtoMaxHealthAttribute(IEventBus modEventBus, ModContainer modContainer) {
        NeoForge.EVENT_BUS.register(this);
    }
    
  @SubscribeEvent
  public void onEntityJoin(EntityJoinLevelEvent event) {
	  if (event.getLevel().isClientSide()) return;
	  if (event.getEntity() instanceof Player) return;
	  if (event.loadedFromDisk()) return;
	  
	  event.getEntity().getPersistentData().putBoolean(HEAL_TAG, true);
    }
}
