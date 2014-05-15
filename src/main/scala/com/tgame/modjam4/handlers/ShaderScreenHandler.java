package com.tgame.modjam4.handlers;

import com.tgame.modjam4.spells.ISpell;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.client.resources.IResourceManager;
import net.minecraft.client.shader.ShaderGroup;
import net.minecraft.client.util.JsonException;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;

/**
 * @since 15/05/14
 * @author tgame14
 */
public class ShaderScreenHandler implements ISpell
{
    protected ResourceLocation[] shaderResourceLocations = new ResourceLocation[] { new ResourceLocation("shaders/post/fxaa.json"), new ResourceLocation("shaders/post/art.json"), new ResourceLocation("shaders/post/bumpy.json"), new ResourceLocation("shaders/post/blobs2.json"), new ResourceLocation("shaders/post/pencil.json"), new ResourceLocation("shaders/post/color_convolve.json"), new ResourceLocation("shaders/post/deconverge.json"), new ResourceLocation("shaders/post/flip.json"), new ResourceLocation("shaders/post/invert.json"), new ResourceLocation("shaders/post/ntsc.json"), new ResourceLocation("shaders/post/outline.json"), new ResourceLocation("shaders/post/phosphor.json"), new ResourceLocation("shaders/post/scan_pincushion.json"), new ResourceLocation("shaders/post/sobel.json"), new ResourceLocation("shaders/post/bits.json"), new ResourceLocation("shaders/post/desaturate.json"), new ResourceLocation("shaders/post/green.json"), new ResourceLocation("shaders/post/blur.json"), new ResourceLocation("shaders/post/wobble.json"), new ResourceLocation("shaders/post/blobs.json"), new ResourceLocation("shaders/post/antialias.json") };
    protected int index = 0;

    @Override
    public void onActived (EntityPlayer player)
    {
        if (player.getEntityWorld().isRemote)
        {
            Minecraft mc = Minecraft.getMinecraft();
            IResourceManager resourceManager = mc.getResourceManager();

            if (mc.entityRenderer.isShaderActive())
            {
                mc.entityRenderer.deactivateShader();
                return;
            }

            if (mc.entityRenderer.theShaderGroup != null)
            {
                mc.entityRenderer.theShaderGroup.deleteShaderGroup();
            }

            try
            {
                mc.entityRenderer.theShaderGroup = new ShaderGroup(resourceManager, mc.getFramebuffer(), shaderResourceLocations[index]);
            }
            catch (JsonException e)
            {
                e.printStackTrace();
            }
            mc.entityRenderer.theShaderGroup.createBindFramebuffers(mc.displayWidth, mc.displayHeight);
        }
        index++;
        if (index >= shaderResourceLocations.length)
        {
            index = 0;
        }

    }
}
