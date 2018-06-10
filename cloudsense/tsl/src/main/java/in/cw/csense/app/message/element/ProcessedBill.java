package in.cw.csense.app.message.element;

import java.util.List;

/**
 * @author Arya
 *
 *         An instance of processed bills.
 *
 *         A bill object which has been processed at cloud side, will be kept as
 *         part of Processed bill. A bill id which has been successfully updated
 *         in cloud database will be listed under succeeded bill id and bill id
 *         which has been failed will be added to failed bill id.
 */
public class ProcessedBill {

	/** Collection of succeeded bill ids. */
	private List<Integer> succeededBillIds;

	/** Collection of failed bill ids. */
	private List<Integer> failedBillIds;

	/**
	 * Gets the succeeded bill ids.
	 * 
	 * @return succeededBillIds collection of bill ids which has been
	 *         successfully updated in cloud database.
	 */
	public List<Integer> getSucceededBillIds() {
		return succeededBillIds;
	}

	/**
	 * Sets the successful bill ids.
	 * 
	 * @param succeededBillIds
	 *            Collection of billIds which has been updated successfully in
	 *            cloud database.
	 */
	public void setSucceededBillIds(List<Integer> succeededBillIds) {
		this.succeededBillIds = succeededBillIds;
	}

	public List<Integer> getFailedBillIds() {
		return failedBillIds;
	}

	public void setFailedBillIds(List<Integer> failedBillIds) {
		this.failedBillIds = failedBillIds;
	}

}
