package org.tof.bowling;

public class LastFrame extends Frame {

	private int additionalThrowsCount=0;
	
	public LastFrame(Frame previous) {
		super(previous);
	}

	@Override
	public boolean isFinished() {
		if (throwsCount==3 && frameScore<15)
			return true;
		
		if (isStrike() && additionalThrowsCount==3)
			return true;
		
		if (isSpare() && additionalThrowsCount==2)
			return true;
		return false;
	}

	@Override
	public int getNextThrowIdxInFrame() {
		if (isStrike() || isSpare())
			return throwsCount+additionalThrowsCount;
		else
			return throwsCount;
	}

	@Override
	protected void addThrowValue(int throwValue) throws OutOfBoundsException {
		
		if (isFinished())
			throw new IllegalStateException("La frame est terminŽe");
		
		if (isStrike() || isSpare())
		{
			if (throwValue>15 || throwValue<0)
				throw new OutOfBoundsException(0, 15);
			throwManager.addThrowValue(throwValue);
			additionalThrowsCount++;
		}
		else
		{
			if (frameScore+throwValue>15 || throwValue<0)
				throw new OutOfBoundsException(0, 15-frameScore);
			
			frameScore +=throwValue;
			throwManager.addThrowValue(throwValue);
			throwsCount++;
			lastThrowIdx=throwManager.getLastIdx();
		}
	}
	
	

}
