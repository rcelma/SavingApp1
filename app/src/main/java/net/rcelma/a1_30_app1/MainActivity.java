package net.rcelma.a1_30_app1;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import net.rcelma.a1_30_app1.data_access_object.ContactDAO;
import net.rcelma.a1_30_app1.data_object.Contact;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {
	private EditText et;
	private static final String SHARED_PREF_THIS = "SharedPreferencesTest";
	private static final String TAG = "DB_TEST A";
	private ContactDAO contactDAO;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		et = (EditText) findViewById(R.id.et);

		doDBStuff();
		writeReadFiles();
	}

	@Override
	protected void onStart(){
		super.onStart();
		retrievePrefs();
	}

	private void retrievePrefs(){
		SharedPreferences shared = getSharedPreferences(SHARED_PREF_THIS, MODE_PRIVATE);
		et.setText(shared.getString("testShared", "No Value"));

	}

	public void savePref(View view) {
		SharedPreferences shared = getSharedPreferences(SHARED_PREF_THIS, MODE_PRIVATE);
		SharedPreferences.Editor editor = shared.edit();
		editor.putString("testShared", et.getText().toString());
		editor.commit();
	}

	private void doDBStuff(){

		contactDAO = new ContactDAO(this);
		Long id1 = contactDAO.insert(new Contact(0L, "Kevin", "Puto"));
		Log.d(TAG, "The id is :".concat(id1.toString()));
		Contact k = contactDAO.getContact(id1);
		Log.d(TAG, "The contact is :".concat(k.getName()));
		int all = contactDAO.getContactsCount();
		Log.d(TAG, "Contact total is :".concat("" + all));
		int all2 = contactDAO.getAllContacts().size();
		Log.d(TAG, "Contact total is :".concat("" + all2));
		k.setPhoneNumber("211231231231");
		int update = contactDAO.updateContact(k);
		contactDAO.deleteContact(k);
	}

	private void writeReadFiles(){

		try {
			FileOutputStream fos = openFileOutput("dbStuff", MODE_APPEND);
			contactDAO = new ContactDAO(this);
			Contact cont = contactDAO.getContact(1L);
			cont.setPhoneNumber("092898123097812 12 3");
			contactDAO.updateContact(cont);
			fos.write(cont.toString().getBytes());
			Log.d(TAG, "FOS Written");
			fos.flush();

			int c;
			FileInputStream fis = openFileInput("dbStuff");
			StringBuilder sb = new StringBuilder();
			while((c = fis.read()) != -1){
				sb.append((char) c);
			}
			Log.d(TAG, sb.toString());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}