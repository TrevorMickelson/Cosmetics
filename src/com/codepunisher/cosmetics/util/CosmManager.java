package com.codepunisher.cosmetics.util;

import com.codepunisher.cosmetics.user.CosmUser;

import java.util.HashMap;
import java.util.UUID;

/**
 * Handles the cosmetic data storage
 */
public class CosmManager {
    private final HashMap<UUID, CosmUser> cosmUsers = new HashMap<>();

    public CosmUser getCosmUser(UUID uuid) { return cosmUsers.get(uuid); }

    public void addCosmUser(UUID uuid) { cosmUsers.put(uuid, new CosmUser(uuid)); }
    public void addCosmUser(CosmUser cosmUser) {
        cosmUsers.put(cosmUser.getUuid(), cosmUser);
    }

    public void removeCosmUser(UUID uuid) { cosmUsers.remove(uuid); }
}
