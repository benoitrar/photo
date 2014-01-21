/**
 * 
 */
package hu.qben.balinthirling.client.view;

import hu.qben.balinthirling.client.presenter.CommissionedPresenter;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Image;

/**
 * @author Kiss, Benedek
 *
 */
public class CommissionedView extends Composite implements CommissionedPresenter.Display {
	
	/*/public final static String WEDDING_FOLDER = "content/commissioned_work/wedding";
	public final static String FITNESS_FOLDER = "content/commissioned_work/fitness";
	public final static String ARCHITECTURE_FOLDER = "content/commissioned_work/architecture";/*/
	
	private final static Image wedding = new Image(GWT.getHostPageBaseURL() + "img/wedding.jpg");
	private final static Image fitness = new Image(GWT.getHostPageBaseURL() + "img/fitness.jpg");
	private final static Image architecture = new Image(GWT.getHostPageBaseURL() + "img/architecture.jpg");
	
	private final static FlexTable table = new FlexTable();

	/**
	 * 
	 */
	public CommissionedView() {
		initWidget(table);
		
		table.setWidget(0, 0, wedding);
		table.setWidget(1, 0, fitness);
		table.setWidget(2, 0, architecture);
		
		table.setStyleName("commissioned");
		//table.getCellFormatter().addStyleName(0, 0, "table-first");
		//table.getCellFormatter().addStyleName(1, 0, "table-middle");
		//table.getCellFormatter().addStyleName(2, 0, "table-last");
		table.setCellPadding(0);
		table.setCellSpacing(0);
		table.setBorderWidth(0);
		table.getCellFormatter().setStyleName(1, 0, "table-middle");
	}

	/* (non-Javadoc)
	 * @see hu.qben.balinthirling.client.presenter.CommissionedPresenter.Display#getWedding()
	 */
	@Override
	public Image getWedding() {
		return wedding;
	}

	/* (non-Javadoc)
	 * @see hu.qben.balinthirling.client.presenter.CommissionedPresenter.Display#getFitness()
	 */
	@Override
	public Image getFitness() {
		return fitness;
	}

	/* (non-Javadoc)
	 * @see hu.qben.balinthirling.client.presenter.CommissionedPresenter.Display#getArchitecture()
	 */
	@Override
	public Image getArchitecture() {
		return architecture;
	}
	
}
