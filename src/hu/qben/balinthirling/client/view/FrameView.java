package hu.qben.balinthirling.client.view;

import hu.qben.balinthirling.client.presenter.FramePresenter;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;

/**
 * @author Kiss, Benedek
 * 
 * This class defines the frame's UI.
 * Basically, it has only one element: an image.
 */
public class FrameView extends Composite implements FramePresenter.Display {
	
	/**
	 * The image. Its <code>source</code> will be set by <code>FramePresenter</code>.
	 */
	private final FlexTable content = new FlexTable();
	private static final HorizontalPanel textDiv = new HorizontalPanel();//"<div id='text'></div>");
	private final Image image = new Image();
	private final HTML stepperLabel = new HTML("0/0");
	private static final HTML separationPanel = new HTML("&nbsp;&nbsp;");
	private final HTML captionLabel = new HTML(" ");
	
	/**
	 * Adds an image without functionality.
	 */
	public FrameView() {
		initWidget(content);
		
		textDiv.add(stepperLabel);
		textDiv.add(separationPanel);
		textDiv.add(captionLabel);
		content.setWidget(0, 0, image);
		content.setWidget(1, 0, textDiv);
		
		//content.getFlexCellFormatter().getElement(1, 0).setAttribute("style", "white-space: nowrap");
		
		image.setStyleName("img");
		//stepperLabel.setStyleName("stepper-label");
		captionLabel.setStyleName("caption-label");
		textDiv.addStyleName("text-div");
		content.setStyleName("frame-table");
	}

	/* (non-Javadoc)
	 * @see hu.qben.balinthirling.client.presenter.FramePresenter.Display#getImage()
	 */
	@Override
	public Image getImage() {
		return image;
	}

	/* (non-Javadoc)
	 * @see hu.qben.balinthirling.client.presenter.FramePresenter.Display#getStepperLabel()
	 */
	@Override
	public HTML getStepperLabel() {
		return stepperLabel;
	}

	/* (non-Javadoc)
	 * @see hu.qben.balinthirling.client.presenter.FramePresenter.Display#getCaptionLabel()
	 */
	@Override
	public HTML getCaptionLabel() {
		return captionLabel;
	}
}
