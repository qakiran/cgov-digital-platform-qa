package gov.cancer.pageobject.section;

import org.openqa.selenium.WebElement;
import gov.cancer.framework.ElementHelper;
import gov.cancer.pageobject.helper.Link;
import gov.cancer.pageobject.image.Image;
import java.util.List;

/**
 * Class representing a single Managed List.
 */
public class ManagedListItem {
	static final String TITLE_SELECTOR = "a.title";
	static final String DESCRIPTION_SELECTOR = "p.body";
	final static String THUMBNAILIMAGE_SELECTOR = "a>img";
	static final String EXTLINK_SELECTOR = ":scope .icon-exit-notification";
	// locating children of the title link
	static final String titleLinkChildren = "h3>*";
	// The element representing one managed list item.
	private WebElement theManagedListItem;
	// The title text.
	private Link title;
	// The image appearing on the managed list item.
	private Image image;
	// The list description text.
	private WebElement descriptionElement;
	// The external link the title points to .
	private Link exitNotificationLink;

	/**
	 * Constructor
	 *
	 * @param element WebElement containing the managed list's markup.
	 *
	 */
	public ManagedListItem(WebElement element) {
		this.theManagedListItem = element;
		this.title = new Link(ElementHelper.findElement(theManagedListItem, TITLE_SELECTOR));
		this.descriptionElement = ElementHelper.findElement(theManagedListItem, DESCRIPTION_SELECTOR);
		this.image = new Image(ElementHelper.findElement(theManagedListItem, THUMBNAILIMAGE_SELECTOR));
		// Below list is for retrieving all children element of the title link - only if
		// there is more than one it means it has
		// external notification element - only then we initialize the object
		// exitNotificationLink
		List<WebElement> childrenOfTitle = ElementHelper.findElements(theManagedListItem, titleLinkChildren);
		if (childrenOfTitle.size() > 1) {
			exitNotificationLink = new Link(ElementHelper.findElement(theManagedListItem, EXTLINK_SELECTOR));
		}
	}

 /**
	 * Getter for title link
	 *
	 * @return String containing the title text.
	 */
	public Link getTitle() {
		return title;
	}

	/**
	 * Retrieving the text of the list description.
	 *
	 * @return String containing the list description text.
	 */
	public String getDescriptionText() {
		return descriptionElement.getText();
	}

	/**
	 * Retrieve the link the exit Notification goes to.
	 *
	 * @return the link object for exit disclaimer policy.
	 */
	public Link getExternalLinkNotification() {
		return exitNotificationLink;
	}

	/**
	 * Retrieve the thumbnail image path without autogenerated token. That will
	 * allow us to evaluate a "clean" path that will contain file path and file name
	 * only
	 *
	 * @return An Image path of the thumbnail image.
	 */
	public String getImagePath() {
		String fullPath = image.getImageURI().getPath();
		String token = image.getToken();
		return fullPath.replace(token, "");
	}

	/* Returns the image */
	public Image getImage() {
		return image;
	}
}
