package com.nirvana.service.pojo.app;

public class AppAgentNrGoalResult {

	private Float thisMonth=11.1f;

	private Float thisWeek=5.5f;

	private Float thisDay=1f;

	public AppAgentNrGoalResult() {
	}

	public AppAgentNrGoalResult(Float thisMonth, Float thisWeek, Float thisDay) {
		this.thisMonth = thisMonth;
		this.thisWeek = thisWeek;
		this.thisDay = thisDay;
	}

	public Float getThisMonth() {
		return thisMonth;
	}

	public void setThisMonth(Float thisMonth) {
		this.thisMonth = thisMonth;
	}

	public Float getThisWeek() {
		return thisWeek;
	}

	public void setThisWeek(Float thisWeek) {
		this.thisWeek = thisWeek;
	}

	public Float getThisDay() {
		return thisDay;
	}

	public void setThisDay(Float thisDay) {
		this.thisDay = thisDay;
	}

}
