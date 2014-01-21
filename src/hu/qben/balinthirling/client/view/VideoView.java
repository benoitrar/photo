package hu.qben.balinthirling.client.view;

import hu.qben.balinthirling.client.presenter.VideoPresenter;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Frame;
import com.google.gwt.user.client.ui.HTML;

/**
 * @author Kiss, Benedek
 *
 */
@SuppressWarnings("javadoc")
public class VideoView extends Composite implements VideoPresenter.Display {
	
	private final FlexTable content = new FlexTable();
	private Frame frame;
	private final HTML infos = new HTML();
	
	public VideoView() {
		initWidget(content);
		content.setWidget(0, 0, frame);
		content.setWidget(1, 0, infos);
		
		infos.addStyleName("video-infos");
	}

	@Override
	public void setUrl(String url) {
		//Window.alert(url);
		frame = new Frame(url);
		frame.setWidth("750px");
		frame.setHeight("500px");
		content.setWidget(0, 0, frame);
	}

	@Override
	public HTML getInfos() {
		return infos;
	}
}
