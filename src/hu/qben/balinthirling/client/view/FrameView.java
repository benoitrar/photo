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
	private final Image image = new Image();
	private final HorizontalPanel infoPanel = new HorizontalPanel();
	private final Button leftButton = createButton("../img/left_arrow.svg", "left-arrow", "<");
	private final HTML imageNumberLabel = new HTML("0/0");
	private final Button rightButton = createButton("../img/right_arrow.svg", "right-arrow", ">");
	private final HTML separationPanel = new HTML("&nbsp;&nbsp;");
	private final HTML captionLabel = new HTML(" ");
	private final FlexTable content = new FlexTable();
	
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
		
		image.setStyleName("img");
		captionLabel.setStyleName("caption-label");
		infoPanel.addStyleName("text-div");
		content.setStyleName("frame-table");
	}

	/**
	 * @param path 
	 * @param styleName
	 * @param altText
	 * @return
	 */
	private Button createButton(String path, String styleName, String altText) {
		return createButton(createImage(path, styleName, altText));
	}

	/**
	 * @param icon
	 * @return
	 */
	private Button createButton(Image icon) {
		Button button = new Button();
		button.getElement().appendChild(icon.getElement());
		return button;
	}
	
	/**
	 * @param path 
	 * @param styleName
	 * @param altText
	 * @return
	 */
	private Image createImage(String path, String styleName, String altText) {
		Image image = new Image(path);
		image.addStyleName(styleName);
		image.setAltText(altText);
		return image;
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
