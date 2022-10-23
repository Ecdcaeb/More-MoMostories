package com.Hileb.moremomostories.entity.creatures.ai;

import com.Hileb.moremomostories.entity.EntityGoldenGuide;
import net.minecraft.entity.ai.EntityAIAttackMelee;

public class EntityAIGuideAttack extends EntityAIAttackMelee {

    public EntityAIGuideAttack(EntityGoldenGuide guideIn, double speedIn, boolean longMemoryIn)
    {
        super(guideIn, speedIn, longMemoryIn);
    }

    /**
     * Execute a one shot task or start executing a continuous task
     */
    public void startExecuting()
    {
        super.startExecuting();
    }

    /**
     * Reset the task's internal state. Called when this task is interrupted by another one
     */
    public void resetTask()
    {
        super.resetTask();
    }

}
