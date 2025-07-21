package com.pcs.tradingapp.domain;

/**
 * Enumeration of possible user roles in the application.
 * <p>
 * Defines the roles that can be assigned to users for authorization purposes:
 * <ul>
 *   <li>{@link #USER} - standard user role</li>
 *   <li>{@link #ADMIN} - administrator role with elevated privileges</li>
 * </ul>
 */
public enum RoleName {
	USER,
	ADMIN
}
