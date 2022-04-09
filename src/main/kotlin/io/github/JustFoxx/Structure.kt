package io.github.JustFoxx

import io.github.JustFoxx.mixin.StructureFeatureAccessor
import io.github.JustFoxx.structures.WaterStructures
import net.minecraft.world.gen.GenerationStep


class Structure {
    fun registerStructureFeatures() {
        val WATER_STRUCTURE = WaterStructures()
        StructureFeatureAccessor.callRegister("flowery:water", WATER_STRUCTURE, GenerationStep.Feature.SURFACE_STRUCTURES)
    }
}