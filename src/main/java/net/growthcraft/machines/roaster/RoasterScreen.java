package net.growthcraft.machines.roaster;

import com.mojang.blaze3d.systems.RenderSystem;
import net.growthcraft.Growthcraft;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class RoasterScreen extends HandledScreen<RoasterScreenHandler> {
    //A path to the gui texture. In this example we use the texture from the dispenser
    private static final Identifier TEXTURE = new Identifier(Growthcraft.MOD_ID, "textures/gui/roaster.png");

    public RoasterScreen(RoasterScreenHandler handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, title);
        backgroundHeight = 160;
    }
    
    @Override
    protected void drawForeground(MatrixStack matrices, int mouseX, int mouseY) {
        super.drawForeground(matrices, mouseX, mouseY);
        this.textRenderer.draw(matrices, new LiteralText(this.handler.propertyDelegate.get(1)+""), (float)this.titleX, (float)this.titleY+64, 4210752);
    }
    
    @Override
    protected void drawBackground(MatrixStack matrices, float delta, int mouseX, int mouseY) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, TEXTURE);
        int x = (width - backgroundWidth) / 2;
        int y = (height - backgroundHeight) / 2;
        drawTexture(matrices, x, y, 0, 0, backgroundWidth, backgroundHeight);
        int l;
        if (((RoasterScreenHandler)this.handler).isRoasting()) {
            l = ((RoasterScreenHandler)this.handler).getRoastingProgress();
            this.drawTexture(matrices, x + 80, y + 36 + 12 - l, 176, 42 - l, 14, l + 1);
        }
    }

    @Override
    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        renderBackground(matrices);
        super.render(matrices, mouseX, mouseY, delta);
        drawMouseoverTooltip(matrices, mouseX, mouseY);
    }

    @Override
    protected void init() {
        super.init();
        // Center the title
        titleX = (backgroundWidth - textRenderer.getWidth(title)) / 2;
    }
}