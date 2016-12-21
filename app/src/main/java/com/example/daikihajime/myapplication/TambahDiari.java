package com.example.daikihajime.myapplication;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * @author creatorb
 *
 */
 
public class TambahDiari extends AppCompatActivity {

    ProgressDialog pDialog;
    JSONParser jsonParser = new JSONParser();

    // url to membuat pendaftaran / bukutamu baru
    private static String url_tambah_pendaftaran = "http://192.168.1.101/mahasiswa/create_pendaftaran.php";

    private static final String TAG_SUCCESS = "success";
    public static final String TAG_NAME = "name";
    public static final String TAG_EMAIL = "email";
    public static final String TAG_DESCRIPTION = "description";

    EditText inputName;
    EditText inputEmail;
    EditText inputDesc;
    String name, email, description;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tambah_diari);

        // Edit Text
        inputName = (EditText) findViewById(R.id.inputName);
        inputEmail = (EditText) findViewById(R.id.inputEmail);
        inputDesc = (EditText) findViewById(R.id.inputDesc);

        // button untuk buat pendaftaran baru
        Button btnCreatePendaftaran = (Button) findViewById(R.id.btnCreatePendaftaran);

        // event jika di klik
        btnCreatePendaftaran.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // jalankan buat pendaftaran di background
                name = inputName.getText().toString();
                email = inputEmail.getText().toString();
                description = inputDesc.getText().toString();
                new CreateNewpendaftaran().execute();
            }
        });
    }

    /**
     * Background Async Task untuk membuat buku tamu baru
     * */
    class CreateNewpendaftaran extends AsyncTask<String, String, String> {
        ProgressDialog pdialog;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(TambahDiari.this);
            pDialog.setMessage("Sedang membuat pendaftaran...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(false);
            pDialog.show();
        }

        /**
         * buat bukutamu baru
         * */
        protected String doInBackground(String... args) {
//                String name = inputName.getText().toString();
//                String email = inputEmail.getText().toString();
//                String description = inputDesc.getText().toString();

            // parameter
            List<NameValuePair> params = new ArrayList<NameValuePair>();

            params.add(new BasicNameValuePair(TAG_NAME, name));
            params.add(new BasicNameValuePair(TAG_EMAIL, email));
            params.add(new BasicNameValuePair(TAG_DESCRIPTION, description));

            // json object
            JSONObject json = jsonParser.makeHttpRequest(url_tambah_pendaftaran, "POST", params);

            // cek respon di logcat
            Log.d("Create Response", json.toString());

            // cek tag success
            try {
                int success = json.getInt(TAG_SUCCESS);

                if (success == 1) {
                    // sukses buat pendaftaran
                    Intent i = new Intent(getApplicationContext(), SemuaDiari.class);
                    startActivity(i);

                    // tutup screen
                    finish();
                } else {
                    // jika gagal
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;
        }

        /**
         * jika proses selesai maka hentikan progress dialog
         * **/
        protected void onPostExecute(String file_url) {
            pDialog.dismiss();
        }

    }
}