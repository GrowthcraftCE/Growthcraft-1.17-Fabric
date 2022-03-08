package datagen.growthcraft;

import com.google.gson.JsonParser;
import me.shedaniel.cloth.api.datagen.v1.DataGeneratorHandler;
import me.shedaniel.cloth.api.datagen.v1.LootTableData;
import me.shedaniel.cloth.api.datagen.v1.ModelStateData;
import net.growthcraft.blocks.BlockDef;
import net.growthcraft.blocks.CheeseBlock;
import net.growthcraft.blocks.CheeseDef;
import net.growthcraft.blocks.GrowthcraftBlocks;
import net.growthcraft.items.GrowthcraftItems;
import net.growthcraft.items.WaxItem;
import net.minecraft.block.Block;
import net.minecraft.block.enums.SlabType;
import net.minecraft.item.Item;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.condition.BlockStatePropertyLootCondition;
import net.minecraft.loot.context.LootContextType;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.function.SetNbtLootFunction;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.predicate.StatePredicate;
import net.minecraft.util.Identifier;

import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Locale;

import static net.growthcraft.Growthcraft.MOD_ID;

public class DataGenerator {
	/**
	 * Runs the entrypoint.
	 */
	public void generate() {
		//GrowthCraftConstants.isGameRunning = false;
		
		DataGeneratorHandler handler = DataGeneratorHandler.create(Paths.get("../src/generated/resources"));
		
		generateLootTables(handler);
		generateModels(handler);
		
		handler.run();
	}
	
	private void generateLootTables(DataGeneratorHandler handler) {
		LootTableData table = handler.getLootTables();
		
		NbtCompound waxed = new NbtCompound();
		waxed.putInt("cheese_state",1);
		NbtCompound aged = new NbtCompound();
		aged.putInt("cheese_state",2);
		
		Arrays.stream(GrowthcraftBlocks.Cheeses.class.getDeclaredFields()).forEach(field -> {
			try {
				Block cheese = ((CheeseDef)field.get(null)).get();
				table.register(cheese,
						new LootTable.Builder().type(LootContextType.create().build())
								.pool(
										LootPool.builder().rolls(ConstantLootNumberProvider.create(1.0f))
												.bonusRolls(ConstantLootNumberProvider.create(0.0f))
												.with(ItemEntry.builder(cheese.asItem())
														.apply(SetNbtLootFunction.builder(waxed)
																.conditionally(BlockStatePropertyLootCondition.builder(cheese)
																		.properties(StatePredicate.Builder.create()
																				.exactMatch(CheeseBlock.CHEESE_STATE_BOTTOM, CheeseBlock.CheeseState.WAXED)
																				.exactMatch(CheeseBlock.CHEESE_STATE_TOP, CheeseBlock.CheeseState.NONE)
																		)
																)
														)
														.apply(SetNbtLootFunction.builder(waxed)
																.conditionally(BlockStatePropertyLootCondition.builder(cheese)
																		.properties(StatePredicate.Builder.create()
																				.exactMatch(CheeseBlock.CHEESE_STATE_BOTTOM, CheeseBlock.CheeseState.NONE)
																				.exactMatch(CheeseBlock.CHEESE_STATE_TOP, CheeseBlock.CheeseState.WAXED)
																		)
																)
														)
														.apply(SetNbtLootFunction.builder(waxed)
																.conditionally(BlockStatePropertyLootCondition.builder(cheese)
																		.properties(StatePredicate.Builder.create()
																				.exactMatch(CheeseBlock.CHEESE_STATE_BOTTOM, CheeseBlock.CheeseState.WAXED)
																				.exactMatch(CheeseBlock.CHEESE_STATE_TOP, CheeseBlock.CheeseState.WAXED)
																		)
																)
														)
														
														.apply(SetNbtLootFunction.builder(aged)
																.conditionally(BlockStatePropertyLootCondition.builder(cheese)
																		.properties(StatePredicate.Builder.create()
																				.exactMatch(CheeseBlock.CHEESE_STATE_BOTTOM, CheeseBlock.CheeseState.AGED)
																				.exactMatch(CheeseBlock.CHEESE_STATE_TOP, CheeseBlock.CheeseState.NONE)
																		)
																)
														)
														.apply(SetNbtLootFunction.builder(aged)
																.conditionally(BlockStatePropertyLootCondition.builder(cheese)
																		.properties(StatePredicate.Builder.create()
																				.exactMatch(CheeseBlock.CHEESE_STATE_BOTTOM, CheeseBlock.CheeseState.NONE)
																				.exactMatch(CheeseBlock.CHEESE_STATE_TOP, CheeseBlock.CheeseState.AGED)
																		)
																)
														)
														.apply(SetNbtLootFunction.builder(aged)
																.conditionally(BlockStatePropertyLootCondition.builder(cheese)
																		.properties(StatePredicate.Builder.create()
																				.exactMatch(CheeseBlock.CHEESE_STATE_BOTTOM, CheeseBlock.CheeseState.AGED)
																				.exactMatch(CheeseBlock.CHEESE_STATE_TOP, CheeseBlock.CheeseState.AGED)
																		)
																)
														)
														
														.apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(2.0f))
																.conditionally(BlockStatePropertyLootCondition.builder(cheese)
																		.properties(StatePredicate.Builder.create()
																				.exactMatch(CheeseBlock.CHEESE_STATE_BOTTOM, CheeseBlock.CheeseState.UNAGED)
																				.exactMatch(CheeseBlock.CHEESE_STATE_TOP, CheeseBlock.CheeseState.UNAGED)
																		)
																)
														)
														.apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(2.0f))
																.conditionally(BlockStatePropertyLootCondition.builder(cheese)
																		.properties(StatePredicate.Builder.create()
																				.exactMatch(CheeseBlock.CHEESE_STATE_BOTTOM, CheeseBlock.CheeseState.WAXED)
																				.exactMatch(CheeseBlock.CHEESE_STATE_TOP, CheeseBlock.CheeseState.WAXED)
																		)
																)
														)
														.apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(2.0f))
																.conditionally(BlockStatePropertyLootCondition.builder(cheese)
																		.properties(StatePredicate.Builder.create()
																				.exactMatch(CheeseBlock.CHEESE_STATE_BOTTOM, CheeseBlock.CheeseState.AGED)
																				.exactMatch(CheeseBlock.CHEESE_STATE_TOP, CheeseBlock.CheeseState.AGED)
																		)
																)
														)
												)
								)
				);
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		});
	}
	
	private void generateModels(DataGeneratorHandler handler) {
		ModelStateData modelStates = handler.getModelStates();
		JsonParser jsonParser = new JsonParser();
		
		//noinspection deprecation
		for (WaxItem wax : GrowthcraftItems.listOfWaxes().values()){
			modelStates.addGeneratedItemModel(wax);
			String blockmodel = "{\n" +
					"    \"parent\": \"block/cube_all\",\n" +
					"    \"textures\": {\n" +
					"        \"all\": \""+MOD_ID+":block/"+wax.wax+"_wax"+"_ERROR"+"\"\n" +
					"    }\n" +
					"}";
			modelStates.addState(new Identifier(MOD_ID, wax.wax+"_wax"+"_bricks"),jsonParser.parse("{\n" +
					"    \"variants\": {\n" +
					"        \"\": { \"model\": \""+MOD_ID+":block/"+ wax.wax+"_wax"+"_bricks"+"\" }\n" +
					"    }\n" +
					"}"));
			modelStates.addState(new Identifier(MOD_ID, wax.wax+"_wax"+"_cut_block"),jsonParser.parse("{\n" +
					"    \"variants\": {\n" +
					"        \"\": { \"model\": \""+MOD_ID+":block/"+ wax.wax+"_wax"+"_cut_block"+"\" }\n" +
					"    }\n" +
					"}"));
			modelStates.addState(new Identifier(MOD_ID, wax.wax+"_wax"+"_block"),jsonParser.parse("{\n" +
					"    \"variants\": {\n" +
					"        \"\": { \"model\": \""+MOD_ID+":block/"+ wax.wax+"_wax"+"_block"+"\" }\n" +
					"    }\n" +
					"}"));

			modelStates.addModel(new Identifier(MOD_ID, "block/"+wax.wax+"_wax_block"),jsonParser.parse((blockmodel.replace("ERROR","block"))));
			modelStates.addModel(new Identifier(MOD_ID, "block/"+wax.wax+"_wax_bricks"),jsonParser.parse((blockmodel.replace("ERROR","bricks"))));
			modelStates.addModel(new Identifier(MOD_ID, "block/"+wax.wax+"_wax_cut_block"),jsonParser.parse((blockmodel.replace("ERROR","cut_block"))));
			modelStates.addSimpleItemModel(new Identifier(MOD_ID, "item/"+wax.wax+"_wax"+"_block"),new Identifier(MOD_ID+":block/"+wax.wax+"_wax"+"_block"));
			modelStates.addSimpleItemModel(new Identifier(MOD_ID, "item/"+wax.wax+"_wax"+"_bricks"),new Identifier(MOD_ID+":block/"+wax.wax+"_wax"+"_bricks"));
			modelStates.addSimpleItemModel(new Identifier(MOD_ID, "item/"+wax.wax+"_wax"+"_cut_block"),new Identifier(MOD_ID+":block/"+wax.wax+"_wax"+"_cut_block"));
		}
		//noinspection deprecation
		for (Item milk : GrowthcraftItems.listOfMilks().values()){
			modelStates.addGeneratedItemModel(milk);
		}
		Arrays.stream(GrowthcraftItems.Singleton.class.getDeclaredFields()).forEach(field -> {
			try {
				modelStates.addGeneratedItemModel((Item)field.get(null));
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		});

		// Singleton
		String blockmodel = "{\n" +
				"    \"parent\": \"block/cube_all\",\n" +
				"    \"textures\": {\n" +
				"        \"all\": \""+MOD_ID+":block/NAME"+"\"\n" +
				"    }\n" +
				"}";
		Arrays.stream(GrowthcraftBlocks.Singleton.class.getDeclaredFields()).forEach(field -> {
			try {
				modelStates.addState(((BlockDef) field.get(null)).get(),jsonParser.parse("{\n" +
						"    \"variants\": {\n" +
						"        \"\": { \"model\": \""+MOD_ID+":block/"+field.getName().toLowerCase(Locale.ROOT)+"\" }\n" +
						"    }\n" +
						"}"));
				modelStates.addModel(new Identifier(MOD_ID,"block/"+field.getName().toLowerCase(Locale.ROOT)),jsonParser.parse((blockmodel.replace("NAME",field.getName().toLowerCase(Locale.ROOT)))));
				modelStates.addSimpleItemModel((Item)((BlockDef) field.get(null)).get().asItem(),new Identifier(MOD_ID,"block/"+field.getName().toLowerCase(Locale.ROOT)));
			} catch (Exception exception){

			}
		});

		// Custom
		Arrays.stream(GrowthcraftBlocks.Custom.class.getDeclaredFields()).forEach(field -> {
			try {
				modelStates.addSimpleItemModel((Item)((BlockDef) field.get(null)).get().asItem(),new Identifier(MOD_ID,"block/"+field.getName().toLowerCase(Locale.ROOT)));
			} catch (Exception exception){

			}
		});

		Arrays.stream(GrowthcraftBlocks.Cheeses.class.getDeclaredFields()).forEach(field -> {
			String cheeseName = field.getName().toLowerCase(Locale.ROOT);
			try {
				modelStates.addGeneratedItemModel(((CheeseDef)field.get(null)).slice().asItem());
				modelStates.addGeneratedItemModel(((CheeseDef)field.get(null)).curds().asItem());
				modelStates.addState(((CheeseDef) field.get(null)).get(), () -> jsonParser.parse("{\n" +
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
				modelStates.addModel(new Identifier(MOD_ID, "item/" + "unaged_"+cheeseName),()-> jsonParser.parse("{\n" +
						"  \"parent\": \"item/generated\",\n" +
						"  \"textures\": {\n" +
						"    \"layer0\": \"growthcraft:item/unaged_"+cheeseName+"\"\n" +
						"  }\n" +
						"}"));
				modelStates.addModel(new Identifier(MOD_ID, "item/" + "waxed_"+cheeseName),()-> jsonParser.parse("{\n" +
						"  \"parent\": \"item/generated\",\n" +
						"  \"textures\": {\n" +
						"    \"layer0\": \"growthcraft:item/waxed_"+cheeseName+"\"\n" +
						"  }\n" +
						"}"));
				modelStates.addModel(new Identifier(MOD_ID, "item/" + "aged_"+cheeseName),()-> jsonParser.parse("{\n" +
						"  \"parent\": \"item/generated\",\n" +
						"  \"textures\": {\n" +
						"    \"layer0\": \"growthcraft:item/aged_"+cheeseName+"\"\n" +
						"  }\n" +
						"}"));
				modelStates.addModel(new Identifier(MOD_ID, "item/" + "sliced_"+cheeseName),()-> jsonParser.parse("{\n" +
						"\t\"parent\": \"item/generated\",\n" +
						"\t\"textures\": {\n" +
						"\t\t\"layer0\": \"growthcraft:item/sliced_"+cheeseName+"\"\n" +
						"\t}\n" +
						"}"));
				modelStates.addModel(new Identifier(MOD_ID, "item/" + cheeseName),()-> jsonParser.parse("{\n" +
						"  \"parent\": \"item/generated\",\n" +
						"  \"textures\": {\n" +
						"    \"layer0\": \"growthcraft:item/unaged_"+cheeseName+"\"\n" +
						"  },\n" +
						"  \"overrides\": [\n" +
						"    { \"predicate\": { \"cheese_state\": 0 }, \"model\": \"growthcraft:item/unaged_"+cheeseName+"\" },\n" +
						"    { \"predicate\": { \"cheese_state\": 1 }, \"model\": \"growthcraft:item/waxed_"+cheeseName+"\" },\n" +
						"    { \"predicate\": { \"cheese_state\": 2 }, \"model\": \"growthcraft:item/aged_"+cheeseName+"\" },\n" +
						"    { \"predicate\": { \"cheese_state\": 3 }, \"model\": \"growthcraft:item/sliced_"+cheeseName+"\" },\n" +
						"    { \"predicate\": { \"cheese_state\": 4 }, \"model\": \"growthcraft:item/sliced_"+cheeseName+"\" },\n" +
						"    { \"predicate\": { \"cheese_state\": 5 }, \"model\": \"growthcraft:item/sliced_"+cheeseName+"\" }\n" +
						"  ]\n" +
						"}"));
				for (SlabType type : SlabType.values()) {
					String nfstate = np_state.asString().toLowerCase(Locale.ROOT);
					String state = (nfstate.contains("sliced") ? "aged" : nfstate)+"_";
					String uastate = (state.contains("waxed") ? "aged_" : state);
					modelStates.addModel(new Identifier(MOD_ID, "block/" + nfstate + "_" + cheeseName + "_" + type.toString().toLowerCase(Locale.ROOT)), () -> jsonParser.parse(
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
													? "growthcraft:block/sliced_h_top"
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
									"    \"inside\": \"growthcraft:block/" + "sliced_" + cheeseName + "_slab_side\",\n" +
									"    \"bottom\": \"growthcraft:block/" + uastate + cheeseName + "_bottom\",\n" +
									"    \"top\": \"growthcraft:block/" + uastate + cheeseName + "_top\",\n" +
									"    \"side\": \"growthcraft:block/" + uastate + cheeseName + "_slab_side\"\n" +
									"  }\n" +
									"}"
					));
				}
			}
		});
	}
}
