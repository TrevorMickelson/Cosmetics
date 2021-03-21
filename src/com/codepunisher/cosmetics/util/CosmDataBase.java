package com.codepunisher.cosmetics.util;

import com.codepunisher.cosmetics.cosmetics.BackParticle;
import com.codepunisher.cosmetics.cosmetics.Suit;
import com.codepunisher.cosmetics.cosmetics.TrailParticle;
import com.codepunisher.cosmetics.user.CosmUser;
import com.mcaim.core.mysql.MySQL;
import com.mcaim.core.scheduler.Async;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class CosmDataBase {
    public static void createTable() {
        try {
            PreparedStatement statement = MySQL.getStatement("CREATE TABLE IF NOT EXISTS cosmetics "
                    + "(UUID VARCHAR(100), TRAIL VARCHAR(100), SUIT VARCHAR(100), BACK VARCHAR(100), PRIMARY KEY (UUID))");
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * This stores the user/updates the users
     * information if they're already stored
     */
    public static void updateCosmUser(UUID uuid, CosmUser cosmUser) {
        Async.get().run(() -> {
            // If they don't have anything equipped
            if (!CosmUtil.shouldStore(cosmUser)) {
                CosmUtil.removeCosmUser(cosmUser);
                return;
            }

            try {
                if (!MySQL.uuidExists(uuid, "cosmetics"))
                    MySQL.addUuid(uuid, "cosmetics");

                PreparedStatement statement = MySQL.getStatement("UPDATE cosmetics SET TRAIL=?, SUIT=?, BACK=? WHERE UUID=?");

                String trailSet = !cosmUser.getTrailUsage().isActive() ? "None" : cosmUser.getTrailUsage().getParticle().name();
                String suitSet = !cosmUser.getSuitUsage().isWearingSuit() ? "None" : cosmUser.getSuitUsage().getSuit().name();
                String backSet = !cosmUser.getBackParticleUsage().isActive() ? "None" : cosmUser.getBackParticleUsage().getParticle().name();

                statement.setString(1, trailSet);
                statement.setString(2, suitSet);
                statement.setString(3, backSet);
                statement.setString(4, uuid.toString());
                statement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }

            CosmUtil.removeCosmUser(cosmUser);
        });
    }

    /**
     * This pulls data from the database
     * and initializes all of the cosmetics
     * in game for the user
     */
    public static void storeCosmeticInfo(CosmUser cosmUser) {
        UUID uuid = cosmUser.getUuid();

        if (!MySQL.uuidExists(uuid, "cosmetics"))
            return;

        try {
            PreparedStatement statement = MySQL.getStatement("SELECT * FROM cosmetics WHERE UUID=?");
            statement.setString(1, uuid.toString());
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                String trail = resultSet.getString("TRAIL");
                String suit = resultSet.getString("SUIT");
                String back = resultSet.getString("BACK");

                if (!trail.equalsIgnoreCase("none")) {
                    cosmUser.getTrailUsage().setActive(true);
                    cosmUser.getTrailUsage().setParticle(TrailParticle.valueOf(trail));
                }

                if (!suit.equalsIgnoreCase("none")) {
                    cosmUser.getSuitUsage().setWearingSuit(true);
                    cosmUser.getSuitUsage().setSuit(Suit.valueOf(suit));
                }

                if (!back.equalsIgnoreCase("none")) {
                    cosmUser.getBackParticleUsage().setActive(true);
                    cosmUser.getBackParticleUsage().setParticle(BackParticle.valueOf(back));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
