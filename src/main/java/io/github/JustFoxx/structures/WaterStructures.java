package io.github.JustFoxx.structures;

import net.minecraft.structure.*;
import net.minecraft.structure.pool.StructurePoolBasedGenerator;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.Heightmap;
import net.minecraft.world.gen.feature.StructureFeature;
import net.minecraft.world.gen.feature.StructurePoolFeatureConfig;
import org.slf4j.LoggerFactory;

import java.util.Optional;


public class WaterStructures extends StructureFeature<StructurePoolFeatureConfig> {

    public WaterStructures() {
        // Create the pieces layout of the structure and give it to the game
        super(StructurePoolFeatureConfig.CODEC, WaterStructures::createPiecesGenerator, PostPlacementProcessor.EMPTY);
    }
    private static boolean isFeatureChunk(StructureGeneratorFactory.Context<StructurePoolFeatureConfig> context) {
        // Grabs the chunk position we are at
        ChunkPos chunkpos = context.chunkPos();

        // Checks to make sure our structure does not spawn within 10 chunks of an Ocean Monument
        // to demonstrate how this method is good for checking extra conditions for spawning
        return !context.chunkGenerator().method_41053(StructureSetKeys.OCEAN_MONUMENTS, context.seed(), chunkpos.x, chunkpos.z, 10);
    }

    public static Optional<StructurePiecesGenerator<StructurePoolFeatureConfig>> createPiecesGenerator(StructureGeneratorFactory.Context<StructurePoolFeatureConfig> context) {

        // Check if the spot is valid for our structure. This is just as another method for cleanness.
        // Returning an empty optional tells the game to skip this spot as it will not generate the structure.
        if (!WaterStructures.isFeatureChunk(context)) {
            return Optional.empty();
        }

        // Turns the chunk coordinates into actual coordinates we can use. (Gets center of that chunk)
        BlockPos blockpos = context.chunkPos().getCenterAtY(0);

        // Find the top Y value of the land and then offset our structure to 60 blocks above that.
        // WORLD_SURFACE_WG will stop at top water so we don't accidentally put our structure into the ocean if it is a super deep ocean.
        int topLandY = context.chunkGenerator().getHeightOnGround(blockpos.getX(), blockpos.getZ(), Heightmap.Type.OCEAN_FLOOR_WG, context.world());
        blockpos = blockpos.up(topLandY);

        Optional<StructurePiecesGenerator<StructurePoolFeatureConfig>> structurePiecesGenerator =
                StructurePoolBasedGenerator.generate(
                        context, // Used for JigsawPlacement to get all the proper behaviors done.
                        PoolStructurePiece::new, // Needed in order to create a list of jigsaw pieces when making the structure's layout.
                        blockpos, // Position of the structure. Y value is ignored if last parameter is set to true.
                        false,  // Special boundary adjustments for villages. It's... hard to explain. Keep this false and make your pieces not be partially intersecting.
                        // Either not intersecting or fully contained will make children pieces spawn just fine. It's easier that way.
                        false // Place at heightmap (top land). Set this to false for structure to be place at the passed in blockpos's Y value instead.
                        // Definitely keep this false when placing structures in the nether as otherwise, heightmap placing will put the structure on the Bedrock roof.
                );

        /*
         * Note, you are always free to make your own JigsawPlacement class and implementation of how the structure
         * should generate. It is tricky but extremely powerful if you are doing something that vanilla's jigsaw system cannot do.
         * Such as for example, forcing 3 pieces to always spawn every time, limiting how often a piece spawns, or remove the intersection limitation of pieces.
         *
         * An example of a custom JigsawPlacement.addPieces in action can be found here (warning, it is using Mojmap mappings):
         * https://github.com/TelepathicGrunt/RepurposedStructures/blob/1.18.2/src/main/java/com/telepathicgrunt/repurposedstructures/world/structures/pieces/PieceLimitedJigsawManager.java
         */

        if(structurePiecesGenerator.isPresent()) {
            // I use to debug and quickly find out if the structure is spawning or not and where it is.
            // This is returning the coordinates of the center starting piece.
            LoggerFactory.getLogger("FloweryStructures").debug("Rundown House at {}", blockpos);
        }

        // Return the pieces generator that is now set up so that the game runs it when it needs to create the layout of structure pieces.
        return structurePiecesGenerator;
    }
}
