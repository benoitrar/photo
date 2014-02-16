package hu.qben.balinthirling.client.view;

import hu.qben.balinthirling.client.presenter.FramePresenter;

import com.google.gwt.user.client.ui.Button;
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
	private static final HorizontalPanel infoPanel = new HorizontalPanel();//"<div id='text'></div>");
	private final Image image = new Image();
	private final Button leftButton = new Button("<");
	private final HTML imageNumberLabel = new HTML("0/0");
	private final Button rightButton = new Button(">");
	private static final HTML separationPanel = new HTML("&nbsp;&nbsp;");
	private final HTML captionLabel = new HTML(" ");
	
	/**
	 * Adds an image without functionality.
	 */
	public FrameView() {
		initWidget(content);
		
		infoPanel.add(leftButton);
		infoPanel.add(imageNumberLabel);
		infoPanel.add(rightButton);
		infoPanel.add(separationPanel);
		infoPanel.add(captionLabel);
		content.setWidget(0, 0, image);
		content.setWidget(1, 0, infoPanel);
		
		//content.getFlexCellFormatter().getElement(1, 0).setAttribute("style", "white-space: nowrap");
		
		image.setStyleName("img");
		//imageNumberLabel.setStyleName("stepper-label");
		captionLabel.setStyleName("caption-label");
		infoPanel.addStyleName("text-div");
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
	public HTML getImageNumberLabel() {
		return imageNumberLabel;
	}

	/* (non-Javadoc)
	 * @see hu.qben.balinthirling.client.presenter.FramePresenter.Display#getCaptionLabel()
	 */
	@Override
	public HTML getCaptionLabel() {
		return captionLabel;
	}

	/* (non-Javadoc)
	 * @see hu.qben.balinthirling.client.presenter.FramePresenter.Display#getLeftStepper()
	 */
	@Override
	public Button getLeftStepper() {
		return leftButton;
	}

	/* (non-Javadoc)
	 * @see hu.qben.balinthirling.client.presenter.FramePresenter.Display#getRightStepper()
	 */
	@Override
	public Button getRightStepper() {
		return rightButton;
	}
}
