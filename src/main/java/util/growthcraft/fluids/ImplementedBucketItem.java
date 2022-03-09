/**
 * Made by TeamReborn (Modified), licensed under the MIT License (MIT)
 * Source:
 * @see https://github.com/TechReborn/RebornCore/blob/1.16/src/main/java/reborncore/common/fluid/RebornFluid.java
 */
package util.growthcraft.fluids;

import net.minecraft.item.BucketItem;

public class ImplementedBucketItem extends BucketItem {
    public ImplementedBucketItem(ImplementedFluid fluid, Settings settings) {
        super(fluid, settings);
    }
}
