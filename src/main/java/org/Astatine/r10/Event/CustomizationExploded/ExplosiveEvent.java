package org.Astatine.r10.Event.CustomizationExploded;

import org.bukkit.Location;
import org.bukkit.entity.Creeper;
import org.bukkit.entity.WitherSkull;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.Astatine.r10.Event.EventRegister;

public class ExplosiveEvent implements EventRegister {
    private Location location;
    private final EntityExplodeEvent event;

    public ExplosiveEvent(EntityExplodeEvent event) {
        this.event = event;
        init();
        execute();
    }

    @Override
    public void init() {
        this.location = this.event.getLocation();
    }

    @Override
    public void execute() {
//        TNT: 4 blocks
//        Creeper:
//        Normal: 3 blocks
//        Charged: 6 blocks
//        Ghast Fireball: 1 block
//        Wither Skull:
//        Blue Skull: 1 block
//        Black Skull: Varies depending on difficulty
        switch (this.event.getEntityType()) {
            case CREEPER -> creeperBoom();
            case FIREBALL -> ghastBoom();
            case WITHER_SKULL -> witherBoom();
            case TNT -> boomBer();
            case TNT_MINECART -> cartBoom();
            case END_CRYSTAL -> endCrystal();
            default -> this.event.setCancelled(true);
        }
    }

    private void creeperBoom() {
        Creeper creeper = (Creeper) this.event.getEntity();
        int explosiveRadius = creeper.isPowered() ? 100 : 5;
        this.location.createExplosion(explosiveRadius, true);
    }

    private void ghastBoom() {
        this.location.createExplosion(3, true);
    }

    private void witherBoom() {
        WitherSkull witherSkull = (WitherSkull) this.event.getEntity();
        int explosiveRadius = witherSkull.isCharged() ? 130 : 40;
        this.location.createExplosion(explosiveRadius, true);
    }

    private void boomBer() {
        this.location.createExplosion(15, true);
    }

    private void cartBoom() {
        Runnable tntCartTask = () -> this.location.createExplosion(25, true);
        tntCartTask.run();
    }

    private void endCrystal() {
        Runnable crystalTask = () -> {
            this.location.createExplosion(15, true);
        };
        crystalTask.run();
    }
}
