scoreboard players add @a flowerystructures_joined 0
execute as @a run execute if score @s flowerystructures_joined matches 0 run function flowery:join
