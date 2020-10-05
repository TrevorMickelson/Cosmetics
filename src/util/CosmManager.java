package util;

import models.CosmUser;

import java.util.HashMap;
import java.util.UUID;

/**
 * This class handles basic data
 * storage for cosmetic users
 */
public class CosmManager
{
    /** Stores all cosmetic users in hash map */
    private HashMap<UUID, CosmUser> cosmUsers = new HashMap<>();

    /**
     * Getting the cosmetic object for player
     * @param uuid players uuid
     * @return cosmetic user
     */
    public CosmUser getCosmUser(UUID uuid) { return this.cosmUsers.get(uuid); }

    /**
     * Creating and storing cosmetic object
     * @param uuid players uuid
     */
    public void addCosmUser(UUID uuid) { this.cosmUsers.put(uuid, new CosmUser(uuid)); }

    /**
     * Removing cosmetic storage for player
     * @param uuid players uuid
     */
    public void removeCosmUser(UUID uuid) { this.cosmUsers.remove(uuid); }
}
