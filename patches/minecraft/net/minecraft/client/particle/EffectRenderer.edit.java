
# Eagler Context Redacted Diff
# Copyright (c) 2025 lax1dude. All rights reserved.

# Version: 1.0
# Author: lax1dude

> DELETE  2  @  2 : 4

> CHANGE  2 : 6  @  2 : 4

~ import net.lax1dude.eaglercraft.v1_8.EaglercraftRandom;
~ import net.lax1dude.eaglercraft.v1_8.minecraft.AcceleratedEffectRenderer;
~ import net.lax1dude.eaglercraft.v1_8.minecraft.IAcceleratedParticleEngine;
~ 

> INSERT  1 : 10  @  1

+ 
+ import com.carrotsearch.hppc.IntObjectHashMap;
+ import com.carrotsearch.hppc.IntObjectMap;
+ import com.google.common.collect.Lists;
+ import net.lax1dude.eaglercraft.v1_8.opengl.EaglercraftGPU;
+ import net.lax1dude.eaglercraft.v1_8.opengl.GlStateManager;
+ import net.lax1dude.eaglercraft.v1_8.opengl.WorldRenderer;
+ import net.lax1dude.eaglercraft.v1_8.opengl.ext.deferred.DeferredStateManager;
+ import net.lax1dude.eaglercraft.v1_8.opengl.ext.deferred.GBufferAcceleratedEffectRenderer;

> DELETE  3  @  3 : 36

> DELETE  1  @  1 : 2

> DELETE  1  @  1 : 2

> INSERT  16 : 18  @  16

+ 	private static final ResourceLocation particleMaterialsTextures = new ResourceLocation(
+ 			"eagler:glsl/deferred/particles_s.png");

> CHANGE  4 : 6  @  4 : 6

~ 	private EaglercraftRandom rand = new EaglercraftRandom();
~ 	private IntObjectMap<IParticleFactory> particleTypes = new IntObjectHashMap<>();

> INSERT  1 : 4  @  1

+ 	public static final AcceleratedEffectRenderer vanillaAcceleratedParticleRenderer = new AcceleratedEffectRenderer();
+ 	public IAcceleratedParticleEngine acceleratedParticleRenderer = null;
+ 

> INSERT  13 : 15  @  13

+ 		this.acceleratedParticleRenderer = EaglercraftGPU.checkInstancingCapable() ? vanillaAcceleratedParticleRenderer
+ 				: null;

> CHANGE  52 : 53  @  52 : 53

~ 		this.particleTypes.put(id, particleFactory);

> CHANGE  8 : 9  @  8 : 9

~ 		IParticleFactory iparticlefactory = this.particleTypes.get(particleId);

> CHANGE  29 : 31  @  29 : 30

~ 		for (int i = 0, l = this.particleEmitters.size(); i < l; ++i) {
~ 			EntityParticleEmitter entityparticleemitter = this.particleEmitters.get(i);

> CHANGE  52 : 62  @  52 : 53

~ 	public boolean hasParticlesInAlphaLayer() {
~ 		for (int i = 0; i < 3; ++i) {
~ 			if (!this.fxLayers[i][0].isEmpty()) {
~ 				return true;
~ 			}
~ 		}
~ 		return false;
~ 	}
~ 
~ 	public void renderParticles(Entity entityIn, float partialTicks, int pass) {

> CHANGE  8 : 12  @  8 : 10

~ 		if (!DeferredStateManager.isDeferredRenderer()) {
~ 			GlStateManager.enableBlend();
~ 			GlStateManager.blendFunc(770, 771);
~ 		}

> CHANGE  2 : 7  @  2 : 4

~ 		for (int i = 0; i < 3; ++i) {
~ 			for (int j = 1; j >= 0; --j) {
~ 				if (pass != 2 && j != pass) {
~ 					continue;
~ 				}

> CHANGE  1 : 8  @  1 : 8

~ //					switch (j) {
~ //					case 0:
~ //						GlStateManager.depthMask(false);
~ //						break;
~ //					case 1:
~ //						GlStateManager.depthMask(true);
~ //					}

> INSERT  1 : 3  @  1

+ 					float texCoordWidth = 0.001f;
+ 					float texCoordHeight = 0.001f;

> INSERT  3 : 4  @  3

+ 						GBufferAcceleratedEffectRenderer.isMaterialNormalTexture = false;

> INSERT  1 : 7  @  1

+ 						if (DeferredStateManager.isDeferredRenderer()) {
+ 							GlStateManager.setActiveTexture(33986);
+ 							this.renderer.bindTexture(particleMaterialsTextures);
+ 							GlStateManager.setActiveTexture(33984);
+ 						}
+ 						texCoordWidth = texCoordHeight = 1.0f / 256.0f;

> INSERT  2 : 3  @  2

+ 						GBufferAcceleratedEffectRenderer.isMaterialNormalTexture = true;

> INSERT  1 : 4  @  1

+ 						TextureMap blockMap = (TextureMap) this.renderer.getTexture(TextureMap.locationBlocksTexture);
+ 						texCoordWidth = 1.0f / blockMap.getWidth();
+ 						texCoordHeight = 1.0f / blockMap.getHeight();

> INSERT  7 : 13  @  7

+ 					boolean legacyRenderingHasOccured = false;
+ 
+ 					if (acceleratedParticleRenderer != null) {
+ 						acceleratedParticleRenderer.begin(partialTicks);
+ 					}
+ 

> CHANGE  4 : 10  @  4 : 5

~ 							if (acceleratedParticleRenderer == null
~ 									|| !entityfx.renderAccelerated(acceleratedParticleRenderer, entityIn, partialTicks,
~ 											f, f4, f1, f2, f3)) {
~ 								entityfx.renderParticle(worldrenderer, entityIn, partialTicks, f, f4, f1, f2, f3);
~ 								legacyRenderingHasOccured = true;
~ 							}

> INSERT  9 : 10  @  9

+ 							final int l = i;

> CHANGE  2 : 5  @  2 : 5

~ 									return l == 0 ? "MISC_TEXTURE"
~ 											: (l == 1 ? "TERRAIN_TEXTURE"
~ 													: (l == 3 ? "ENTITY_PARTICLE_TEXTURE" : "Unknown - " + l));

> CHANGE  6 : 15  @  6 : 7

~ 					if (legacyRenderingHasOccured) {
~ 						tessellator.draw();
~ 					} else {
~ 						worldrenderer.finishDrawing();
~ 					}
~ 
~ 					if (acceleratedParticleRenderer != null) {
~ 						acceleratedParticleRenderer.draw(texCoordWidth, texCoordHeight);
~ 					}

> EOF
