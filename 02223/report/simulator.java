while (!currentJob.timeTick(cycle)) {
    currentJob = 
	readyList.getHighestPriorityJob();
}
