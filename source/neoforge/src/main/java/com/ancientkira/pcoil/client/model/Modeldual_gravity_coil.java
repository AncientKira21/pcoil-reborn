package com.ancientkira.pcoil.client.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.Entity;

public class Modeldual_gravity_coil {
   public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(
      Identifier.fromNamespaceAndPath("pcoil", "modeldual_gravity_coil"), "main"
   );
   public final ModelPart RightBoot;
   public final ModelPart LeftBoot;

   public Modeldual_gravity_coil(ModelPart root) {
      this.RightBoot = root.getChild("RightBoot");
      this.LeftBoot = root.getChild("LeftBoot");
   }

   public static LayerDefinition createBodyLayer() {
      MeshDefinition meshdefinition = new MeshDefinition();
      PartDefinition partdefinition = meshdefinition.getRoot();
      PartDefinition RightBoot = partdefinition.addOrReplaceChild(
         "RightBoot",
         CubeListBuilder.create()
            .texOffs(30, 33)
            .addBox(-3.0F, 10.0F, -3.0F, 4.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
            .texOffs(0, 0)
            .addBox(-3.0F, 9.0F, -3.0F, 1.0F, 1.0F, 6.0F, new CubeDeformation(0.0F))
            .texOffs(0, 8)
            .addBox(2.0F, 7.0F, -3.0F, 1.0F, 1.0F, 6.0F, new CubeDeformation(0.0F))
            .texOffs(30, 18)
            .addBox(-3.0F, 6.0F, -3.0F, 6.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
            .texOffs(15, 0)
            .addBox(-3.0F, 5.0F, -3.0F, 1.0F, 1.0F, 6.0F, new CubeDeformation(0.0F))
            .texOffs(30, 21)
            .addBox(-3.0F, 4.0F, 2.0F, 6.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
            .texOffs(15, 8)
            .addBox(2.0F, 3.0F, -3.0F, 1.0F, 1.0F, 6.0F, new CubeDeformation(0.0F))
            .texOffs(30, 24)
            .addBox(-3.0F, 2.0F, -3.0F, 6.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
            .texOffs(0, 16)
            .addBox(-3.0F, 1.0F, -3.0F, 1.0F, 1.0F, 6.0F, new CubeDeformation(0.0F))
            .texOffs(0, 35)
            .addBox(-3.0F, 0.0F, 2.0F, 4.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
            .texOffs(30, 15)
            .addBox(-3.0F, 8.0F, 2.0F, 6.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)),
         PartPose.offset(-1.9F, 12.0F, 0.0F)
      );
      PartDefinition LeftBoot = partdefinition.addOrReplaceChild(
         "LeftBoot",
         CubeListBuilder.create()
            .texOffs(11, 35)
            .addBox(-1.05F, -0.5F, -3.0F, 4.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
            .texOffs(15, 16)
            .addBox(1.95F, 0.5F, -3.0F, 1.0F, 1.0F, 6.0F, new CubeDeformation(0.0F))
            .texOffs(30, 8)
            .addBox(1.95F, 4.5F, -2.0F, 1.0F, 1.0F, 5.0F, new CubeDeformation(0.0F))
            .texOffs(0, 24)
            .addBox(1.95F, 9.5F, -3.0F, 1.0F, 1.0F, 6.0F, new CubeDeformation(0.0F))
            .texOffs(30, 27)
            .addBox(-3.05F, 1.5F, 2.0F, 6.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
            .texOffs(30, 30)
            .addBox(-3.05F, 5.5F, 2.0F, 6.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
            .texOffs(22, 36)
            .addBox(-1.05F, 10.5F, 2.0F, 4.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
            .texOffs(0, 32)
            .addBox(-3.05F, 4.5F, -3.0F, 6.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
            .texOffs(15, 32)
            .addBox(-3.05F, 8.5F, -3.0F, 6.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)),
         PartPose.offset(1.9F, 12.0F, 0.0F)
      );
      PartDefinition cube_r1 = LeftBoot.addOrReplaceChild(
         "cube_r1",
         CubeListBuilder.create().texOffs(30, 0).addBox(-3.994F, 0.0F, 0.0F, 1.0F, 1.0F, 6.0F, new CubeDeformation(0.0F)),
         PartPose.offsetAndRotation(0.945F, 8.634F, -2.6962F, 0.5236F, 0.0F, 0.0F)
      );
      PartDefinition cube_r2 = LeftBoot.addOrReplaceChild(
         "cube_r2",
         CubeListBuilder.create().texOffs(15, 24).addBox(-3.9965F, 0.0F, 0.0F, 1.0F, 1.0F, 6.0F, new CubeDeformation(0.0F)),
         PartPose.offsetAndRotation(0.9475F, 4.634F, -2.6962F, 0.5236F, 0.0F, 0.0F)
      );
      return LayerDefinition.create(meshdefinition, 64, 64);
   }

}
