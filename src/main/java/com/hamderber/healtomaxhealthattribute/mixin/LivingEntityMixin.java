package com.hamderber.healtomaxhealthattribute.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.hamderber.healtomaxhealthattribute.HealtoMaxHealthAttribute;

import net.minecraft.core.Holder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;

@Mixin(value = LivingEntity.class, remap = false)
public abstract class LivingEntityMixin {
	@Inject(at = @At("TAIL"), method = "onAttributeUpdated")
	private void healToMaxHealth(Holder<Attribute> attribute, CallbackInfo ci) {
		if (attribute.equals(Attributes.MAX_HEALTH)) {
			LivingEntity entity = (LivingEntity) (Object) this;
			
			if (!(entity instanceof Player) && 
					entity.getPersistentData().getBoolean(HealtoMaxHealthAttribute.HEAL_TAG) && 
					entity.getHealth() < entity.getMaxHealth()) {
				
				entity.setHealth(entity.getMaxHealth());
//				HealtoMaxHealthAttribute.LOGGER.info("Health modifier changed for {}! {}",
//						entity.getName(),
//						attribute.toString());
				entity.getPersistentData().putBoolean(HealtoMaxHealthAttribute.HEAL_TAG, false);
			}
		}
	}
}
