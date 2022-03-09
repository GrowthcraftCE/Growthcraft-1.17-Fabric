/**
 * Made by TeamReborn (Modified), licensed under the MIT License (MIT)
 * Source:
 * @see https://github.com/TechReborn/RebornCore/blob/1.16/src/main/java/reborncore/common/fluid/RebornFluid.java
 */
package util.growthcraft.fluids;

import net.minecraft.fluid.Fluid;
import net.minecraft.item.BucketItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.Lazy;
import net.minecraft.util.registry.Registry;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

public class ImplementedFluidManager {

    private static final HashMap<Identifier, ImplementedFluid> fluids = new HashMap<>();

    private static Lazy<Map<Fluid, BucketItem>> bucketMap;

    public static void register(ImplementedFluid rebornFluid, Identifier identifier) {
        fluids.put(identifier, rebornFluid);
        Registry.register(Registry.FLUID, identifier, rebornFluid);
    }

    public static void setupBucketMap() {
        bucketMap = new Lazy<>(() -> {
            Map<Fluid, BucketItem> map = new HashMap<>();
            Registry.ITEM.stream().filter(item -> item instanceof BucketItem).forEach(item -> {
                BucketItem bucketItem = (BucketItem) item;
                //We can be sure of this as we add this via a mixin
                ItemFluidInfo fluidInfo = (ItemFluidInfo) bucketItem;
                Fluid fluid = fluidInfo.getFluid(new ItemStack(item));
                if (!map.containsKey(fluid)) {
                    map.put(fluid, bucketItem);
                }
            });
            return map;
        });
    }

    public static Map<Fluid, BucketItem> getBucketMap() {
        return bucketMap.get();
    }

    public static HashMap<Identifier, ImplementedFluid> getFluids() {
        return fluids;
    }

    public static Stream<ImplementedFluid> getFluidStream() {
        return fluids.values().stream();
    }
}