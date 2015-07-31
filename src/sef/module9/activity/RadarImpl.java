package sef.module9.activity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Implementation of a Radar
 * 
 *
 */
public class RadarImpl implements Radar {
	private List<RadarContact> contact;

	/**
	 * Constructs a new Radar
	 */
	public RadarImpl() {
		contact = new ArrayList<RadarContact>();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * sef.module8.activity.Radar#addContact(sef.module8.activity.RadarContact)
	 */
	public RadarContact addContact(RadarContact contact) {
		if(contact!=null){
		for (int i = 0; i < this.contact.size(); i++)
			if (this.contact.get(i).getContactID() == contact.getContactID()) {
				this.contact.set(i, contact);
				return contact;
			}
		this.contact.add(contact);
		return contact;}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see sef.module8.activity.Radar#getContact(java.lang.String)
	 */
	public RadarContact getContact(String id) {
		for (int i = 0; i < this.contact.size(); i++)
			if (this.contact.get(i).getContactID() == id) {
				return this.contact.get(i);
			}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see sef.module8.activity.Radar#getContactCount()
	 */
	public int getContactCount() {

		return contact.size();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see sef.module8.activity.Radar#removeContact(java.lang.String)
	 */
	public RadarContact removeContact(String id) {
		for (int i = 0; i < this.contact.size(); i++)
			if (this.contact.get(i).getContactID() == id) {
				RadarContact deleted = this.contact.get(i);
				this.contact.remove(i);
				return deleted;
			}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see sef.module8.activity.Radar#returnContacts()
	 */
	public List<RadarContact> returnContacts() {
		List<RadarContact> temp= new ArrayList<RadarContact>(this.contact);
		
		return temp;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see sef.module8.activity.Radar#returnContacts(java.util.Comparator)
	 */
	public List<RadarContact> returnContacts(Comparator<RadarContact> comparator) {
		List<RadarContact> temp= returnContacts();
		temp.sort(comparator);
		return temp;
	}

}
