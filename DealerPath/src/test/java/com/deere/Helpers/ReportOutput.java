package com.deere.Helpers;

public class ReportOutput {
	 
	String testcaseStatus;
	String scenarioName;
	String inputTestData;
	String wcmTestCaseID;
	String actualResult;
	String expectedResult;
	
	public ReportOutput() {

	}
	
	public ReportOutput(String testcaseStatus, String scenarioName, String inputTestData, String wcmTestCaseID,
			String actualResult, String expectedResult) {
		super();
		this.testcaseStatus = testcaseStatus;
		this.scenarioName = scenarioName;
		this.inputTestData = inputTestData;
		this.wcmTestCaseID = wcmTestCaseID;
		this.actualResult = actualResult;
		this.expectedResult = expectedResult;
	}

	public String getTestcaseStatus() {
		return testcaseStatus;
	}

	public void setTestcaseStatus(String testcaseStatus) {
		this.testcaseStatus = testcaseStatus;
	}

	public String getScenarioName() {
		return scenarioName;
	}

	public void setScenarioName(String scenarioName) {
		this.scenarioName = scenarioName;
	}

	public String getInputTestData() {
		return inputTestData;
	}

	public void setInputTestData(String inputTestData) {
		this.inputTestData = inputTestData;
	}

	public String getWcmTestCaseID() {
		return wcmTestCaseID;
	}

	public void setWcmTestCaseID(String wcmTestCaseID) {
		this.wcmTestCaseID = wcmTestCaseID;
	}

	public String getActualResult() {
		return actualResult;
	}

	public void setActualResult(String actualResult) {
		this.actualResult = actualResult;
	}

	public String getExpectedResult() {
		return expectedResult;
	}

	public void setExpectedResult(String expectedResult) {
		this.expectedResult = expectedResult;
	}

	@Override
	public String toString() {
		return "ReportOutput [testcaseStatus=" + testcaseStatus + ", scenarioName=" + scenarioName + ", inputTestData="
				+ inputTestData + ", wcmTestCaseID=" + wcmTestCaseID + ", actualResult=" + actualResult
				+ ", expectedResult=" + expectedResult + "]";
	}
}
