package com.codepunisher.cosmetics.listeners;

import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityTargetLivingEntityEvent;

public class PetListener
{
    private void onPetTarget(EntityTargetLivingEntityEvent event) {
        Entity entity = event.getEntity();

        if (entity instanceof LivingEntity && !(entity instanceof Player)) {
            if (entity.getCustomName() != null)
                event.setCancelled(true);
        }
    }

    public void handleTargetEvent(EntityTargetLivingEntityEvent event) { onPetTarget(event); }
}
