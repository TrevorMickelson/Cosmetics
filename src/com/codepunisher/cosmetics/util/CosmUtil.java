package com.codepunisher.cosmetics.util;

import com.codepunisher.cosmetics.CosmMain;
import com.codepunisher.cosmetics.cosmetics.BackParticle;
import com.codepunisher.cosmetics.cosmetics.Suit;
import com.codepunisher.cosmetics.cosmetics.TrailParticle;
import com.codepunisher.cosmetics.user.CosmUser;

public class CosmUtil {
    public static boolean isTrailNull(TrailParticle p) {
        for (TrailParticle particle : TrailParticle.values()) {
            if (p == particle)
                return false;
        }

        return true;
    }

    public static boolean isSuitNull(Suit s) {
        for (Suit suit : Suit.values()) {
            if (s == suit)
                return false;
        }

        return true;
    }

    public static boolean isBackNull(BackParticle p) {
        for (BackParticle particle : BackParticle.values()) {
            if (p == particle)
                return false;
        }

        return true;
    }

    /**
     * Determines if the cosm user should
     * be updated to the data base
     */
    public static boolean shouldStore(CosmUser cosmUser) {
        return cosmUser.getTrailUsage().isActive() ||
                cosmUser.getBackParticleUsage().isActive() ||
                cosmUser.getSuitUsage().isWearingSuit();
    }

    /**
     * Removing cosmetics and removing from map
=     */
    public static void removeCosmUser(CosmUser cosmUser) {
        cosmUser.getSuitUsage().removeSuit();
        cosmUser.getTrailUsage().setActive(false);
        cosmUser.getBackParticleUsage().setActive(false);
        CosmMain.getInstance().getCosmManager().removeCosmUser(cosmUser.getUuid());
    }
}
