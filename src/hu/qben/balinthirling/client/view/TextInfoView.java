/**
 * 
 */
package hu.qben.balinthirling.client.view;

import hu.qben.balinthirling.client.presenter.TextInfoPresenter;
import hu.qben.balinthirling.shared.JsString;

import com.google.gwt.core.client.JsArray;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTML;

/**
 * @author Kiss, Benedek
 *
 */
@SuppressWarnings("javadoc")
public class TextInfoView extends Composite implements TextInfoPresenter.Display {
	
	private static HTML infoLabel = new HTML();
	
	public TextInfoView() {
		initWidget(infoLabel);
		infoLabel.setStyleName("data");
	}

	/* (non-Javadoc)
	 * @see hu.qben.balinthirling.client.presenter.TextInfoPresenter.Display#setData(com.google.gwt.core.client.JsArray)
	 */
	@Override
	public void setData(JsArray<JsString> jsArray) {
		infoLabel.setHTML("");
		
		for(int i=0;i<jsArray.length();i++) {
			//Window.alert(infoLabel.getHTML() + "+" + jsArray.get(i).getName());
			infoLabel.setHTML(infoLabel.getHTML() + jsArray.get(i).getName().replaceAll("___", "<br>") + "<br>");
		}
	}
	
}
