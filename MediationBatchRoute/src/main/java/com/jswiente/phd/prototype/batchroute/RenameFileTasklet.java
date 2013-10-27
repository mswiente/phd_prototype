package com.jswiente.phd.prototype.batchroute;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.core.io.Resource;

public class RenameFileTasklet implements Tasklet {
	
	private Resource inputResource;
	private Resource outputResource;

	@Override
	public RepeatStatus execute(StepContribution contribution,
			ChunkContext chunkContext) throws Exception {
		
		inputResource.getFile().renameTo(outputResource.getFile());
		
		return RepeatStatus.FINISHED;
	}

	public void setInputResource(Resource inputResource) {
		this.inputResource = inputResource;
	}

	public void setOutputResource(Resource outputResource) {
		this.outputResource = outputResource;
	}

}
