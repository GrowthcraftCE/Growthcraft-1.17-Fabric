/**
 * Made by TeamReborn (Modified), licensed under the MIT License (MIT)
 * Source:
 * @see https://github.com/TechReborn/RebornCore/blob/1.16/src/main/java/reborncore/common/fluid/RebornFluid.java
 */
package util.growthcraft.fluids;

import net.minecraft.util.Identifier;

public class FluidSettings {
    private Identifier flowingTexture = new Identifier("growthcraft:nope");
    private Identifier stillTexture = new Identifier("growthcraft:nope");

    public FluidSettings setFlowingTexture(Identifier flowingTexture) {
        this.flowingTexture = flowingTexture;
        return this;
    }

    public FluidSettings setStillTexture(Identifier stillTexture) {
        this.stillTexture = stillTexture;
        return this;
    }

    public Identifier getFlowingTexture() {
        return flowingTexture;
    }

    public Identifier getStillTexture() {
        return stillTexture;
    }

    private FluidSettings() {
    }

    public static FluidSettings create() {
        return new FluidSettings();
    }
}
