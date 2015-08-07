package com.belladati.sdk.report;

import java.util.Date;

import com.belladati.sdk.user.UserInfo;

/**
 * A comment entry in a BellaDati report.
 * 
 * @author Chris Hennigfeld
 */
public interface Comment {

	/**
	 * Returns an info object associated with the comment's author.
	 * 
	 * @return an info object associated with the comment's author
	 */
	UserInfo getAuthorInfo();

	/**
	 * Returns the text content of this comment.
	 * 
	 * @return the text content of this comment
	 */
	String getText();

	/**
	 * Returns the date/time when this comment was posted.
	 * 
	 * @return the date/time when this comment was posted
	 */
	Date getDateTime();
}
