package util;

import configuration.DataFile;
import cosmetics.BackParticles;
import cosmetics.Pets;
import cosmetics.Suits;
import cosmetics.TrailParticles;
import models.CosmUser;

import java.util.Objects;
import java.util.UUID;

public class CosmDataManager
{
    /**
     * This method stores all of the cosmetic
     * data into 1 string line
     * This is to make the config less convoluted
     *
     * @param cosmUser cosmetic user
     * @return string
     */
    public String cosmeticDataToString(CosmUser cosmUser) {
        // UUID of cosm user
        String string = cosmUser.getUuid().toString() + ":";

        // Trail shit
        string = string + (!cosmUser.isActiveTrailParticle() ? "false:" : cosmUser.getTrailParticle().name() + ":");

        // Back shit
        string = string + (!cosmUser.isActiveBackParticle() ? "false:" : cosmUser.getBackParticle().name() + ":");

        // Pet shit (if pet is spawned)
        string = string + (!cosmUser.isActivePet() ? "false:" : cosmUser.getPet().name() + ":");

        // Pet shit (pet settings)
        string = string + cosmUser.isBaby() + ":";
        string = string + cosmUser.isCharged() + ":";
        string = string + cosmUser.isGlow() + ":";

        // Suit shit
        string = string + (!cosmUser.isWearingSuit() ? "false" : cosmUser.getSuit().name());

        // Returning string
        return string;
    }

    /**
     * Returns cosmuser object based on
     * string that was previously stored
     * as a cosmuser string
     *
     * @param string string
     * @return cosmuser
     */
    public CosmUser stringToCosmUser(String string) {
        String[] args = string.split(":");
        CosmUser cosmUser = new CosmUser(UUID.fromString(args[0]));

        if (!args[1].equalsIgnoreCase("false")) {
            cosmUser.setActiveTrailParticle(true);
            cosmUser.setTrailParticle(TrailParticles.valueOf(args[1]));
        }

        if (!args[2].equalsIgnoreCase("false")) {
            cosmUser.setActiveBackParticle(true);
            cosmUser.setBackParticle(BackParticles.valueOf(args[2]));
        }

        if (!args[3].equalsIgnoreCase("false")) {
            cosmUser.setActivePet(true);
            cosmUser.setPet(Pets.valueOf(args[3]));
        }

        cosmUser.setBaby(Boolean.parseBoolean(args[4]));
        cosmUser.setCharged(Boolean.parseBoolean(args[5]));
        cosmUser.setGlow(Boolean.parseBoolean(args[6]));

        if (!args[7].equalsIgnoreCase("false")) {
            cosmUser.setWearingSuit(true);
            cosmUser.setSuit(Suits.valueOf(args[7]));
        }

        return cosmUser;
    }

    /**
     * Returns cosmuser from file
     */
    public CosmUser getCosmUserFromFile(DataFile dataFile, UUID uuid) {
        if (dataFile.getData().isSet(uuid.toString()))
            return stringToCosmUser(Objects.requireNonNull(dataFile.getData().getString(uuid.toString())));

        return null;
    }
}
