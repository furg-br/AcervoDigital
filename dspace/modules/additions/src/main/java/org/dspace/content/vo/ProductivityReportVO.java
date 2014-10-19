package org.dspace.content.vo;

import java.util.EnumSet;


/**
 * 
 * TODOMARCIO: Documentar
 * @author Márcio Ribeiro Gurgel do Amaral (marcio.rga@gmail.com)
 *
 */
public class ProductivityReportVO {

	private String name;
	private String yearAndMonth;
	private Long totalSubmitted = 0l;
	private Long totalApproved = 0l;
	private Long totalRemoved = 0l;
	private Long totalRejected = 0l;
	
	public void addTotal(ActionType findByValue, Long valueCount) {
		
		switch (findByValue) {
		case APPROVED:
			setTotalApproved(getTotalApproved() + valueCount);
			break;
		case REJECTED:
			setTotalRejected(getTotalRejected() + valueCount);
			break;
		case REMOVED:
			setTotalRemoved(getTotalRemoved() + valueCount);
			break;
		case SUBMITTED:
			setTotalSubmitted(getTotalSubmitted() + valueCount);
			break;
		default:
			break;
		}
		
	}
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getYearAndMonth() {
		return yearAndMonth;
	}
	public void setYearAndMonth(String yearAndMonth) {
		this.yearAndMonth = yearAndMonth;
	}
	public Long getTotalSubmitted() {
		return totalSubmitted;
	}
	public void setTotalSubmitted(Long totalSubmitted) {
		this.totalSubmitted = totalSubmitted;
	}
	public Long getTotalApproved() {
		return totalApproved;
	}
	public void setTotalApproved(Long totalApproved) {
		this.totalApproved = totalApproved;
	}
	public Long getTotalRemoved() {
		return totalRemoved;
	}
	public void setTotalRemoved(Long totalRemoved) {
		this.totalRemoved = totalRemoved;
	}
	public Long getTotalRejected() {
		return totalRejected;
	}
	public void setTotalRejected(Long totalRejected) {
		this.totalRejected = totalRejected;
	}
	
	
	
	/**
	 * 
	 * TODOMARCIO: Documentar
	 * @author Márcio Ribeiro Gurgel do Amaral (marcio.rga@gmail.com)
	 *
	 */
	public static enum ActionType {
		
		APPROVED("Appr"), SUBMITTED("Subm"), REMOVED("Item"), REJECTED("Reje");
		
		private String databasePrefix;

		public static ActionType findByValue(String action) {
			
			EnumSet<ActionType> allOf = EnumSet.allOf(ActionType.class);
			for(ActionType enumInstance : allOf) {
				if(enumInstance.getDatabasePrefix().equals(action)) {
					return enumInstance;
				}
			}
			
			return null;
		}
		
		private ActionType(String databasePrefix) {
			this.databasePrefix = databasePrefix;
		}

		public String getDatabasePrefix() {
			return databasePrefix;
		}
		
		
	}
	
}
