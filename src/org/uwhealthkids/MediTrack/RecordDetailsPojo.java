package org.uwhealthkids.MediTrack;

import java.util.Calendar;

public class RecordDetailsPojo {

	private Calendar time;
	private int valueOne;
	private int valueTwo;

	public Calendar getTime() {
		return time;
	}
	public void setTime(Calendar c) {
		this.time = c;
	}
	public int getValOne() {
		return valueOne;
	}
	public void setValOne(int n) {
		this.valueOne = n;
	}
	public int getValTwo() {
		return valueTwo;
	}
	public void setValTwo(int n) {
		this.valueTwo = n;
	}
}
