package sef.module15.activity;

import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

public class PermissionList implements Permissable {
	private String permissionID;
	private Set<Permission> permissions;

	/**
	 * Creates a permission object and provides an identifier for it
	 * 
	 * @param permissionID
	 */
	public PermissionList(String permissionID) {
		this.permissionID = permissionID;

		permissions = new TreeSet<Permission>();

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see sef.module14.activity.Permissable#getPermission()
	 */
	// @Override
	public Set<Permission> getPermission() {

		return permissions;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * sef.module14.activity.Permissable#removePermission(sef.module14.activity.
	 * Permission[])
	 */
	// @Override
	public void removePermission(Permission... permissions) {

		for (Permission i : permissions)
			this.permissions.remove(i);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * sef.module14.activity.Permissable#setPermission(sef.module14.activity.
	 * Permission[])
	 */
	// @Override
	public void setPermission(Permission... permissions) {

		for (Permission i : permissions)
			this.permissions.add(i);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * sef.module14.activity.Permissable#isPermissable(sef.module14.activity.
	 * Permission)
	 */
	// @Override
	public boolean isPermissable(Permission permission) {

		if (permissions.contains(permission))
			return true;
		return false;

	}
}
