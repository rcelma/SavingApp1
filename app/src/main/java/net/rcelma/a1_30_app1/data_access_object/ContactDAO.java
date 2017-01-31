package net.rcelma.a1_30_app1.data_access_object;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import net.rcelma.a1_30_app1.constant.ContactQueries;
import net.rcelma.a1_30_app1.data_object.Contact;

import java.util.ArrayList;
import java.util.Formatter;
import java.util.List;
import java.util.Locale;

public class ContactDAO extends SQLiteOpenHelper {
	private static final String DB_NAME = "CONTACT_DB";
	private static final String TABLE_NAME = "CONTACT";
	private static final String COLUMN_ID = "ID";
	private static final String COLUMN_NAME = "NAME";
	private static final String COLUMN_PHONE_NUMBER = "PHONE_NUMBER";
	private static final int DATABASE_VERSION = 1;

	private Formatter formatter;

	public ContactDAO(Context context) {
		super(context, DB_NAME, null, DATABASE_VERSION);
		StringBuilder sb = new StringBuilder();
		formatter = new Formatter(sb, Locale.US);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		String createSQL = formatter.format(ContactQueries.CREATE_TABLE, TABLE_NAME, COLUMN_ID, COLUMN_NAME, COLUMN_PHONE_NUMBER).toString();
		db.execSQL(createSQL);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

		db.execSQL("DROP TABLE IF EXISTS ".concat(TABLE_NAME));
		onCreate(db);
	}

	public Long insert(Contact contact) {

		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues cvs = new ContentValues();
		cvs.put(COLUMN_NAME, contact.getName());
		cvs.put(COLUMN_PHONE_NUMBER, contact.getPhoneNumber());
		return db.insert(TABLE_NAME, null, cvs);
	}

	public Contact getContact(Long id) {
		SQLiteDatabase db = this.getReadableDatabase();

		Cursor cursor = db.query(TABLE_NAME, new String[]{COLUMN_ID,
						COLUMN_NAME, COLUMN_PHONE_NUMBER}, COLUMN_ID + "=?",
				new String[]{String.valueOf(id)}, null, null, null, null);
		if (cursor != null) {
			cursor.moveToFirst();
		}

		Contact contact = new Contact(cursor.getLong(0), cursor.getString(1), cursor.getString(2));
		// return contact
		return contact;
	}

	public List<Contact> getAllContacts() {
		List<Contact> contactList = new ArrayList<>();
		String selectQuery = "SELECT  * FROM " + TABLE_NAME;

		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);

		if (cursor.moveToFirst()) {
			do {
				Contact contact = new Contact();
				contact.setId(cursor.getLong(0));
				contact.setName(cursor.getString(1));
				contact.setPhoneNumber(cursor.getString(2));
				contactList.add(contact);
			} while (cursor.moveToNext());
		}
		return contactList;
	}

	public int updateContact(Contact contact) {
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(COLUMN_NAME, contact.getName());
		values.put(COLUMN_PHONE_NUMBER, contact.getPhoneNumber());

		// updating row
		return db.update(TABLE_NAME, values, COLUMN_ID + " = ?",
				new String[]{String.valueOf(contact.getId())});
	}

	// Deleting single contact
	public void deleteContact(Contact contact) {
		SQLiteDatabase db = this.getWritableDatabase();
		db.delete(TABLE_NAME, COLUMN_ID + " = ?",
				new String[]{String.valueOf(contact.getId())});
		db.close();
	}


	// Getting contacts Count
	public int getContactsCount() {
		int total = 0;
		String countQuery = "SELECT * FROM " + TABLE_NAME;
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery(countQuery, null);
		total = cursor.getCount();
		cursor.close();

		return total;
	}
}