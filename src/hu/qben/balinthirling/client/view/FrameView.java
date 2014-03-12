package hu.qben.balinthirling.client.view;

import hu.qben.balinthirling.client.presenter.FramePresenter;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author Kiss, Benedek
 * 
 * This class defines the frame's UI.
 * The structure is the following:
 * |---------------------------|
 * |-----------image-----------|
 * |---------------------------|
 * |<5/10> caption-------------|
 */
public class FrameView extends Composite implements FramePresenter.Display {
	
	private static class IntroductoryPopup extends PopupPanel {
		public IntroductoryPopup(Widget contentWidget) {
			super(true);
			setWidget(contentWidget);
		}
	}
	
	/**
	 * Stylenames of the elements.
	 */
	private static final String FRAME_TABLE_STYLENAME = "frame-table";
	private static final String IMAGE_STYLENAME = "img";
	private static final String CAPTION_LABEL_STYLENAME = "caption-label";
	private static final String RIGHT_ARROW_STYLENAME = "right-arrow";
	private static final String RIGHT_BUTTON_STYLENAME = "right-button";
	private static final String LEFT_ARROW_STYLENAME = "left-arrow";
	private static final String LEFT_BUTTON_STYLENAME = "left-button";
	private static final String TEXT_DIV_STYLENAME = "text-div";
	
	/**
	 * The image. Its <code>source</code> will be set by <code>FramePresenter</code>.
	 */
	private final Image image = new Image();
	private final HorizontalPanel infoPanel = new HorizontalPanel();
	private final Button leftButton = createButtonWithIcon("../img/left_arrow.jpg", LEFT_ARROW_STYLENAME, "<");
	private final HTML imageNumberLabel = new HTML("0/0");
	private final Button rightButton = createButtonWithIcon("../img/right_arrow.jpg", RIGHT_ARROW_STYLENAME, ">");
	private final HTML separationPanel = new HTML("&nbsp;&nbsp;");
	private final HTML captionLabel = new HTML(" ");
	private final FlexTable content = new FlexTable();
	private final HTML introduction = new HTML("Welcome aboard!");
	private final IntroductoryPopup popupWidget = new IntroductoryPopup(introduction);
	
	/**
	 * Adds an image without functionality.
	 */
	public FrameView() {
		buildUpUI();
		addStyleNamesToElements();
	}

	/**
	 * Builds up the UI. It looks like the following:
	 * |---------------------------|
	 * |-----------image-----------|
	 * |---------------------------|
	 * |<5/10> caption-------------|
	 */
	private void buildUpUI() {
		initWidget(content);
		
		infoPanel.add(leftButton);
		infoPanel.add(imageNumberLabel);
		infoPanel.add(rightButton);
		infoPanel.add(separationPanel);
		infoPanel.add(captionLabel);
		content.setWidget(0, 0, image);
		content.setWidget(1, 0, infoPanel);
	}
	
	/**
	 * Adds the proper stylenames to the elements.
	 * <br />
	 * Note that every element must have an own stylename.
	 * By this we can avoid code modifying when content change would be enough.
	 */
	private void addStyleNamesToElements() {
		image.setStyleName(IMAGE_STYLENAME);
		captionLabel.setStyleName(CAPTION_LABEL_STYLENAME);
		rightButton.addStyleName(RIGHT_BUTTON_STYLENAME);
		leftButton.addStyleName(LEFT_BUTTON_STYLENAME);
		infoPanel.addStyleName(TEXT_DIV_STYLENAME);
		content.setStyleName(FRAME_TABLE_STYLENAME);
	}

	/**
	 * @param path the icon's path
	 * @param styleName the icon's stylename
	 * @param altText the icon's alt text
	 * @return button with the specified icon
	 */
	private Button createButtonWithIcon(String path, String styleName, String altText) {
		return createButton(createImage(path, styleName, altText));
	}

	/**
	 * @see FrameView#createButtonWithIcon(String, String, String) createButtonWithIcon
	 */
	private Image createImage(String path, String styleName, String altText) {
		Image image = new Image(path);
		image.addStyleName(styleName);
		image.setAltText(altText);
		return image;
	}
	
	/**
	 * @param icon that will be placed on the button
	 * @return button with the given icon
	 */
	private Button createButton(Image icon) {
		Button button = new Button();
		button.getElement().appendChild(icon.getElement());
		return button;
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
