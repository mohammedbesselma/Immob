package AsyncTask;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.view.View;

import com.example.mohammed.immob.HomeActivity;
import com.example.mohammed.immob.LoginActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

import Adapter.Constans;
import Adapter.CustomListViewAdapter;
import AppClasse.Utilisateur;

/**
 * Created by Mohammed on 25/05/2018.
 */

public class LoginRegisterTask extends AsyncTask<String,Void,String> {

    JSONObject jsonObject;
    JSONArray jsonArray;
    String method;

    private CustomListViewAdapter customListViewAdapter;

    Context ctx;


    public LoginRegisterTask(Context ctx) {

        this.ctx=ctx;

    }

    @Override
    protected String doInBackground(String... params) {

        method = params[0];

        String response="";

        if(method.equals("register")){
            String Nom=params[1];
            String Prenom=params[2];
            String Email=params[3];
            String Motpass=params[4];

            String reg_url = Constans.getIPadress()+"/register.php";


            try {

                URL url=new URL(reg_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                OutputStream OS=httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter=new BufferedWriter(new OutputStreamWriter(OS,"UTF-8"));
                String data= URLEncoder.encode("nom","UTF-8")+"="+URLEncoder.encode(Nom,"UTF-8")+"&"+
                        URLEncoder.encode("prenom","UTF-8")+"="+URLEncoder.encode(Prenom,"UTF-8")+"&"+
                        URLEncoder.encode("mail","UTF-8")+"="+URLEncoder.encode(Email,"UTF-8")+"&"+
                        URLEncoder.encode("motpass","UTF-8")+"="+URLEncoder.encode(Motpass,"UTF-8")+"&"+
                        URLEncoder.encode("photo","UTF-8")+"="+URLEncoder.encode(Motpass,"UTF-8") ;
                bufferedWriter.write(data);
                bufferedWriter.flush();
                bufferedWriter.close();
                OS.close();
                InputStream IS=httpURLConnection.getInputStream();

                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(IS,"iso-8859-1"));

                String line = "";
                while ((line=bufferedReader.readLine())!=null){

                    response+=line;

                }
                bufferedReader.close();
                IS.close();
                httpURLConnection.disconnect();


            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }else if(method.equals("login")){

            String username=params[1];
            String password=params[2];
            String reg_url = Constans.getIPadress()+"/login.php";

            try {

                URL url=new URL(reg_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                OutputStream OS=httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter=new BufferedWriter(new OutputStreamWriter(OS,"UTF-8"));
                String data= URLEncoder.encode("username","UTF-8")+"="+URLEncoder.encode(username,"UTF-8")+"&"+
                        URLEncoder.encode("password","UTF-8")+"="+URLEncoder.encode(password,"UTF-8") ;
                bufferedWriter.write(data);
                bufferedWriter.flush();
                bufferedWriter.close();
                OS.close();
                InputStream IS=httpURLConnection.getInputStream();

                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(IS,"iso-8859-1"));

                String line = "";
                while ((line=bufferedReader.readLine())!=null){

                    response+=line;

                }
                bufferedReader.close();
                IS.close();
                httpURLConnection.disconnect();


            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }





        }


        return response;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(String result) {

        if(method.equals("login")){


            try {
                jsonObject=new JSONObject(result);

                jsonArray=jsonObject.getJSONArray("server_response");
                JSONObject JO= jsonArray.getJSONObject(0);
                Utilisateur utilisateur= new Utilisateur();
                utilisateur.setID_Utilisateur(JO.getInt("ID_Utilisateur"));
                utilisateur.setNom_Utilisateur(JO.getString("Nom"));
                utilisateur.setPrenom_Utilisateur(JO.getString("Prenom"));
                utilisateur.setEmail_Utilisateur(JO.getString("Email"));
                if (!utilisateur.getEmail_Utilisateur().equals(null)){

                    Intent myintent = new Intent(ctx,HomeActivity.class);
                    myintent.putExtra("Utilisateur", utilisateur);
                    ctx.startActivity(myintent);
                }





            } catch (JSONException e) {
                e.printStackTrace();
            }

            LoginActivity.progressBar.setVisibility(View.GONE);
            LoginActivity.messagelogin.setText(result);

        }


    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }
}
