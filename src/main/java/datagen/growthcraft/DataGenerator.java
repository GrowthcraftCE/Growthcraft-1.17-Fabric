package datagen.growthcraft;

import com.google.gson.JsonParser;
import me.shedaniel.cloth.api.datagen.v1.DataGeneratorHandler;
import me.shedaniel.cloth.api.datagen.v1.ModelData;
import me.shedaniel.cloth.api.datagen.v1.ModelStateData;
import net.fabricmc.loader.api.entrypoint.PreLaunchEntrypoint;
import net.growthcraft.GrowthCraftConstants;
import net.growthcraft.Growthcraft;
import net.growthcraft.blocks.CheeseBlock;
import net.growthcraft.blocks.GrowthcraftBlocks;
import net.growthcraft.items.GrowthcraftItems;
import net.minecraft.block.Block;
import net.minecraft.block.enums.SlabType;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Locale;

public class DataGenerator {
	/**
	 * Runs the entrypoint.
	 */
	public void generate() {
		//GrowthCraftConstants.isGameRunning = false;
		
		DataGeneratorHandler handler = DataGeneratorHandler.create(Paths.get("../src/generated/resources"));
		
		ModelStateData modelStates = handler.getModelStates();
		Arrays.stream(GrowthcraftItems.class.getDeclaredFields()).forEach(field -> {
			try {
				modelStates.addGeneratedItemModel((Item)field.get(null));
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		});
		
		JsonParser jsonParser = new JsonParser();
		
		Arrays.stream(GrowthcraftBlocks.Cheeses.class.getDeclaredFields()).forEach(field -> {
			String cheeseName = field.getName().toLowerCase(Locale.ROOT);
			try {
				modelStates.addState((Block) field.get(null), () -> jsonParser.parse("{\n" +
						"  \"multipart\": [\n" +
						"    {   \"when\": { \"cheese_state_top\": \"unaged\" },\n" +
						"      \"apply\": { \"model\": \"growthcraft:block/"+ CheeseBlock.CheeseState.UNAGED.asString().toLowerCase(Locale.ROOT) + "_" + cheeseName+"_top\" }\n" +
						"    },\n" +
						"    {   \"when\": { \"cheese_state_bottom\": \"unaged\" },\n" +
						"      \"apply\": { \"model\": \"growthcraft:block/"+CheeseBlock.CheeseState.UNAGED.asString().toLowerCase(Locale.ROOT) + "_" + cheeseName+"_bottom\" }\n" +
						"    },\n" +
						"    {   \"when\": { \"cheese_state_top\": \"waxed\" },\n" +
						"      \"apply\": { \"model\": \"growthcraft:block/"+CheeseBlock.CheeseState.WAXED.asString().toLowerCase(Locale.ROOT) + "_" + cheeseName+"_top\" }\n" +
						"    },\n" +
						"    {   \"when\": { \"cheese_state_bottom\": \"waxed\" },\n" +
						"      \"apply\": { \"model\": \"growthcraft:block/"+CheeseBlock.CheeseState.WAXED.asString().toLowerCase(Locale.ROOT) + "_" + cheeseName+"_bottom\" }\n" +
						"    },\n" +
						"    {   \"when\": { \"cheese_state_top\": \"aged\" },\n" +
						"      \"apply\": { \"model\": \"growthcraft:block/"+CheeseBlock.CheeseState.AGED.asString().toLowerCase(Locale.ROOT) + "_" + cheeseName+"_top\" }\n" +
						"    },\n" +
						"    {   \"when\": { \"cheese_state_bottom\": \"aged\" },\n" +
						"      \"apply\": { \"model\": \"growthcraft:block/"+CheeseBlock.CheeseState.AGED.asString().toLowerCase(Locale.ROOT) + "_" + cheeseName+"_bottom\" }\n" +
						"    },\n" +
						"    {   \"when\": { \"cheese_state_top\": \"sliced_hq\" },\n" +
						"      \"apply\": { \"model\": \"growthcraft:block/"+CheeseBlock.CheeseState.SLICED_HQ.asString().toLowerCase(Locale.ROOT) + "_" + cheeseName+"_top\" }\n" +
						"    },\n" +
						"    {   \"when\": { \"cheese_state_bottom\": \"sliced_hq\" },\n" +
						"      \"apply\": { \"model\": \"growthcraft:block/"+CheeseBlock.CheeseState.SLICED_HQ.asString().toLowerCase(Locale.ROOT) + "_" + cheeseName+"_bottom\" }\n" +
						"    },\n" +
						"    {   \"when\": { \"cheese_state_top\": \"sliced_h\" },\n" +
						"      \"apply\": { \"model\": \"growthcraft:block/"+CheeseBlock.CheeseState.SLICED_H.asString().toLowerCase(Locale.ROOT) + "_" + cheeseName+"_top\" }\n" +
						"    },\n" +
						"    {   \"when\": { \"cheese_state_bottom\": \"sliced_h\" },\n" +
						"      \"apply\": { \"model\": \"growthcraft:block/"+CheeseBlock.CheeseState.SLICED_H.asString().toLowerCase(Locale.ROOT) + "_" + cheeseName+"_bottom\" }\n" +
						"    },\n" +
						"    {   \"when\": { \"cheese_state_top\": \"sliced_q\" },\n" +
						"      \"apply\": { \"model\": \"growthcraft:block/"+CheeseBlock.CheeseState.SLICED_Q.asString().toLowerCase(Locale.ROOT) + "_" + cheeseName+"_top\" }\n" +
						"    },\n" +
						"    {   \"when\": { \"cheese_state_bottom\": \"sliced_q\" },\n" +
						"      \"apply\": { \"model\": \"growthcraft:block/"+CheeseBlock.CheeseState.SLICED_Q.asString().toLowerCase(Locale.ROOT) + "_" + cheeseName+"_bottom\" }\n" +
						"    }\n" +
						"  ]\n" +
						"}"));
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
			for (CheeseBlock.CheeseState np_state : CheeseBlock.CheeseState.values()) {
				modelStates.addModel(new Identifier(Growthcraft.MOD_ID, "item/" + "unaged_"+cheeseName),()-> jsonParser.parse("{\n" +
						"  \"parent\": \"item/generated\",\n" +
						"  \"textures\": {\n" +
						"    \"layer0\": \"growthcraft:item/unaged_"+cheeseName+"\"\n" +
						"  }\n" +
						"}"));
				modelStates.addModel(new Identifier(Growthcraft.MOD_ID, "item/" + "waxed_"+cheeseName),()-> jsonParser.parse("{\n" +
						"  \"parent\": \"item/generated\",\n" +
						"  \"textures\": {\n" +
						"    \"layer0\": \"growthcraft:item/waxed_"+cheeseName+"\"\n" +
						"  }\n" +
						"}"));
				modelStates.addModel(new Identifier(Growthcraft.MOD_ID, "item/" + "aged_"+cheeseName),()-> jsonParser.parse("{\n" +
						"  \"parent\": \"item/generated\",\n" +
						"  \"textures\": {\n" +
						"    \"layer0\": \"growthcraft:item/aged_"+cheeseName+"\"\n" +
						"  }\n" +
						"}"));
				modelStates.addModel(new Identifier(Growthcraft.MOD_ID, "item/" + "sliced_"+cheeseName),()-> jsonParser.parse("{\n" +
						"\t\"parent\": \"item/generated\",\n" +
						"\t\"textures\": {\n" +
						"\t\t\"layer0\": \"growthcraft:item/sliced_"+cheeseName+"\"\n" +
						"\t}\n" +
						"}"));
				modelStates.addModel(new Identifier(Growthcraft.MOD_ID, "item/" + cheeseName),()-> jsonParser.parse("{\n" +
						"  \"parent\": \"item/generated\",\n" +
						"  \"textures\": {\n" +
						"    \"layer0\": \"growthcraft:item/unaged_"+cheeseName+"\"\n" +
						"  },\n" +
						"  \"overrides\": [\n" +
						"    { \"predicate\": { \"cheese_state\": -1 }, \"model\": \"growthcraft:item/unaged_"+cheeseName+"\" },\n" +
						"    { \"predicate\": { \"cheese_state\": 0 }, \"model\": \"growthcraft:item/unaged_"+cheeseName+"\" },\n" +
						"    { \"predicate\": { \"cheese_state\": 1 }, \"model\": \"growthcraft:item/waxed_"+cheeseName+"\" },\n" +
						"    { \"predicate\": { \"cheese_state\": 2 }, \"model\": \"growthcraft:item/aged_"+cheeseName+"\" },\n" +
						"    { \"predicate\": { \"cheese_state\": 3 }, \"model\": \"growthcraft:item/sliced_"+cheeseName+"\" },\n" +
						"    { \"predicate\": { \"cheese_state\": 4 }, \"model\": \"growthcraft:item/sliced_"+cheeseName+"\" },\n" +
						"    { \"predicate\": { \"cheese_state\": 5 }, \"model\": \"growthcraft:item/sliced_"+cheeseName+"\" }\n" +
						"  ]\n" +
						"}"));
				for (SlabType type : SlabType.values()) {
					String state = np_state.asString().toLowerCase(Locale.ROOT);
					modelStates.addModel(new Identifier(Growthcraft.MOD_ID, "block/" + state + "_" + cheeseName + "_" + type.toString().toLowerCase(Locale.ROOT)), () -> jsonParser.parse(
							"\n" +
									"{\n" + "  \"parent\": \"" +
									(
											np_state == CheeseBlock.CheeseState.SLICED_HQ && type == SlabType.BOTTOM
													? "growthcraft:block/sliced_hq_bottom"
													: np_state == CheeseBlock.CheeseState.SLICED_HQ && type == SlabType.TOP
													? "growthcraft:block/sliced_hq_top"
													: np_state == CheeseBlock.CheeseState.SLICED_H && type == SlabType.BOTTOM
													? "growthcraft:block/sliced_h_bottom"
													: np_state == CheeseBlock.CheeseState.SLICED_H && type == SlabType.TOP
													? "growthcraft:block/sliced_hq_top"
													: np_state == CheeseBlock.CheeseState.SLICED_Q && type == SlabType.BOTTOM
													? "growthcraft:block/sliced_q_bottom"
													: np_state == CheeseBlock.CheeseState.SLICED_Q && type == SlabType.TOP
													? "growthcraft:block/sliced_q_top"
													: type == SlabType.BOTTOM
													? "block/slab"
													: type == SlabType.DOUBLE
													? "block/cube_bottom_top"
													: "block/slab_top"
									) + "\",\n" +
									"  \"textures\": {\n" +
									"    \"bottom\": \"growthcraft:block/" + state.replace("_hq","").replace("_h","").replace("_q","") + "_" + cheeseName + "_bottom\",\n" +
									"    \"top\": \"growthcraft:block/" + state.replace("_hq","").replace("_h","").replace("_q","") + "_" + cheeseName + "_top\",\n" +
									"    \"side\": \"growthcraft:block/" + state.replace("_hq","").replace("_h","").replace("_q","") + "_" + cheeseName + "_slab_side\"\n" +
									"  }\n" +
									"}"
					));
				}
			}
		});
		
		handler.run();
	}
}
