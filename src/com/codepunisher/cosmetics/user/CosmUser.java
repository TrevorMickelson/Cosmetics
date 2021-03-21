package com.codepunisher.cosmetics.user;

import java.util.UUID;

public class CosmUser{
    private final UUID uuid;
    private final SuitUsage suitUsage;
    private final TrailUsage trailUsage;
    private final BackParticleUsage backParticleUsage;

    public CosmUser(UUID uuid) {
        this.uuid = uuid;
        this.suitUsage = new SuitUsage(uuid);
        this.trailUsage = new TrailUsage(uuid);
        this.backParticleUsage = new BackParticleUsage(uuid);
    }

    public UUID getUuid() { return this.uuid; }
    public SuitUsage getSuitUsage() { return suitUsage; }
    public TrailUsage getTrailUsage() { return trailUsage; }
    public BackParticleUsage getBackParticleUsage() { return backParticleUsage; }
}
