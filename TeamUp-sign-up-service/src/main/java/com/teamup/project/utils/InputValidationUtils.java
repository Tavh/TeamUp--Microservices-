package com.teamup.project.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InputValidationUtils {

	private final static String PASSWORD_REGEX = "[a-zA-Z0-9]{4,12}";
	private final static String EMAIL_REGEX = "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$";
	private final static String NICKNAME_REGEX = "[a-zA-Z ,.'-]{2,}";

	// --------------------------------Password validation-----------------------------------

	public static boolean isPasswordValid (String password) {

		if (password.matches(PASSWORD_REGEX)) {
			return true;
		}
		return false;
	}

	//----------------------------------------------------------------------------------------

	// --------------------------------Email validation---------------------------------------

	public static boolean isEmailValid (String email) {

		// This line converts the regex code into the desired pattern
		Pattern emailPattern = Pattern.compile(EMAIL_REGEX, Pattern.CASE_INSENSITIVE);

		//This line checks if the inserted email follows the previously created pattern
		Matcher matcher = emailPattern.matcher(email);

		return matcher.find();
	}

	// --------------------------------Name validation---------------------------------------

	public static boolean isNicknameValid (String name) {

		if (name.matches(NICKNAME_REGEX)) {
			return true;
		}
		return false;
	}
}