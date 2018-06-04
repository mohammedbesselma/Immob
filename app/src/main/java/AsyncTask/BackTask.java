package AsyncTask;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.view.View;
import android.widget.Toast;

import com.example.mohammed.immob.Ajouterimmob;
import com.example.mohammed.immob.Discussion;
import com.example.mohammed.immob.Favoris;
import com.example.mohammed.immob.HomeActivity;
import com.example.mohammed.immob.Homefragment;
import com.example.mohammed.immob.ImmobilierDetais;
import com.example.mohammed.immob.LoginActivity;
import com.example.mohammed.immob.MesannonceActivity;
import com.example.mohammed.immob.MessageFragment;

import Adapter.CommentlistAdapter;
import Adapter.Constans;
import Adapter.DiscussionsAdapter;
import Adapter.FavorisAdapter;
import Adapter.MesannonceAdapter;
import Adapter.MessageAdapter;
import Adapter.ReservationAdapter;
import Adapter.SliderAdapter;
import AppClasse.Photos;

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
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;

import Adapter.CustomListViewAdapter;
import AppClasse.Utilisateur;



public class BackTask extends AsyncTask<String,Void,String>  {
    JSONObject jsonObject;
    JSONArray jsonArray;
    String method;
    private CustomListViewAdapter customListViewAdapter;
    Context ctx;
    public BackTask(Context ctx) {
        this.ctx=ctx;
    }


    @Override
    protected String doInBackground(String... params) {




         method = params[0];

        String response="";
        if (method.equals("deletefavoris")){
            String idimmob=params[1];
            String idutilisateur=params[2];
            response=deletefavoris(idimmob,idutilisateur);

        }
        if (method.equals("getfavoris")){

            String idutilisateur=params[1];

            response=getfavoris(idutilisateur);
        }
        if (method.equals("deleteannonce")){

            String idimmob=params[1];

            response=deleteannonce(idimmob);

        }else if (method.equals("getmesannonce")){

            String idutilisateur=params[1];
            response=getmesannonce(idutilisateur);


        }else if (method.equals("deletereservation")){

            String idreservation=params[1];

            response=deletereservation(idreservation);

        }else if (method.equals("addreservation")){
            String idimmob=params[1];
            String idutilisateur=params[2];
            String date = params[3];

            response=addreservation(idimmob,idutilisateur,date);


        }else if (method.equals("getreservation")){
            String idimmob=params[1];
            String idutilisateur = params[2];

            response=getreservation(idutilisateur,idimmob);


        }else if (method.equals("search")){

            String type=params[1];
            String ville=params[2];
            String date=params[3];
            response=search(type,ville,date);

        }else if (method.equals("getdiscussion")){
            String myid=params[1];

            response=getdiscussion(myid);
        }else if (method.equals("sendmessage")){

            String myid=params[1];
            String yourid=params[2];
            String contene=params[3];
            response=sendmessage(myid,yourid,contene);

        }else if (method.equals("getmessage")){

            String myid=params[1];
            String yourid=params[2];
            response=getmessagelist(myid,yourid);


        }else if(method.equals("deletecomment")){

            String idcomment=params[1];
            response=deletecomment(idcomment);


        }else  if(method.equals("favoris")){

            String idutilisateur=params[1];
            String idimmob=params[2];
            response=addfavoris(idutilisateur,idimmob);

        }else if (method.equals("getcommentlist")){
            String idimmob=params[1];

            response=getcommentlist(idimmob);

        }else if (method.equals("commenter")){
            String contenue=params[1];
            String idimmob=params[2];
            String idutilisateur=params[3];

            response=addcomment(contenue,idimmob,idutilisateur);



        }else if(method.equals("get")){

            String idutilisateur=params[1];

            response=getutilisateur(idutilisateur);


        }else if(method.equals("geturl")){

            String idimmob=params[1];
            response=geturlimage(idimmob);

        }else if(method.equals("AjouterImmobilier")){
            String Type=params[1];
            String Ville=params[2];
            String Etat=params[3];
            String Prix=params[4];
            String Discription=params[5];
            String ID_Utilisateur=params[6];

            response=addnewimmob(Type,Ville,Etat,Prix,Discription,ID_Utilisateur);

        }else if(method.equals("newimmob")){

            response=getlistimmob();

        }


        return response;

    }




    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(String result) {
        if (method.equals("deletefavoris")){
            Toast.makeText(ctx,result, Toast.LENGTH_LONG).show();

        }
        if (method.equals("getfavoris")){

            Toast.makeText(ctx,result, Toast.LENGTH_LONG).show();

            try {
                ArrayList<HashMap<String, String>> Immobilierlist = new ArrayList<>();

                jsonObject=new JSONObject(result);

                jsonArray=jsonObject.getJSONArray("server_response");
                int count=0;
                while (count<jsonArray.length()){


                    JSONObject JO= jsonArray.getJSONObject(count);
                    HashMap<String, String> data = new HashMap<>();
                    data.put("idimmob", JO.getString("idimmob"));
                    data.put("idutilisateur", JO.getString("idutilisateur"));
                    data.put("type", JO.getString("type"));
                    data.put("ville", JO.getString("ville"));
                    data.put("etat", JO.getString("etat"));
                    data.put("prix", JO.getString("prix"));
                    data.put("discription", JO.getString("discription"));
                    data.put("url", JO.getString("url"));
                    data.put("favoris", JO.getString("favoris"));



                    Immobilierlist.add(data);
                    count++;


                }

                FavorisAdapter favorisAdapter = new FavorisAdapter(ctx, Immobilierlist);
                Favoris.favorislist.setAdapter(favorisAdapter);


            } catch (JSONException e) {
                e.printStackTrace();
            }



        }

        if (method.equals("deleteannonce")){




        }

        if (method.equals("getmesannonce")){

            try {


                ArrayList<HashMap<String, String>> reservationlist = new ArrayList<>();
                jsonObject=new JSONObject(result);

                jsonArray=jsonObject.getJSONArray("server_response");

                int count=0;

                while (count<jsonArray.length()){


                    JSONObject JO= jsonArray.getJSONObject(count);
                    HashMap<String, String> data = new HashMap<>();
                    data.put("idimmob", JO.getString("idimmob"));
                    data.put("type", JO.getString("type"));
                    data.put("ville", JO.getString("ville"));
                    data.put("etat", JO.getString("etat"));
                    data.put("prix", JO.getString("prix"));
                    data.put("discription", JO.getString("discription"));



                    reservationlist.add(data);
                    count++;

                }

                MesannonceAdapter mesannonceAdapter = new MesannonceAdapter(ctx,reservationlist);
                MesannonceActivity.listView.setAdapter(mesannonceAdapter);







            } catch (JSONException e) {

            }
            Toast.makeText(ctx,result, Toast.LENGTH_LONG).show();


        }

        if (method.equals("deletereservation")){
            Toast.makeText(ctx,result, Toast.LENGTH_LONG).show();



        }

        if (method.equals("addreservation")){


            Toast.makeText(ctx,result, Toast.LENGTH_LONG).show();


        }

        if (method.equals("getreservation")){


            try {


                ArrayList<HashMap<String, String>> reservationlist = new ArrayList<>();
                jsonObject=new JSONObject(result);

                jsonArray=jsonObject.getJSONArray("server_response");

                int count=0;

                while (count<jsonArray.length()){


                    JSONObject JO= jsonArray.getJSONObject(count);
                    HashMap<String, String> data = new HashMap<>();
                    data.put("idreservation", JO.getString("idresevation"));
                    data.put("date", JO.getString("date"));
                    data.put("validation", JO.getString("validation"));
                    data.put("idimmob", JO.getString("idimmob"));
                    data.put("idutilisateur", JO.getString("idutilisateur"));



                    reservationlist.add(data);
                    count++;

                }


                ReservationAdapter reservationAdapter = new ReservationAdapter(ctx,reservationlist);
                ImmobilierDetais.reservationgrid.setAdapter(reservationAdapter);
                ImmobilierDetais.reservationgrid.setExpanded(true);





            } catch (JSONException e) {

            }

        }
        if (method.equals("search")){
            try {
                ArrayList<HashMap<String, String>> Immobilierlist = new ArrayList<>();

                jsonObject=new JSONObject(result);

                jsonArray=jsonObject.getJSONArray("server_response");
                int count=0;
                while (count<jsonArray.length()){


                    JSONObject JO= jsonArray.getJSONObject(count);
                    HashMap<String, String> data = new HashMap<>();
                    data.put("idimmob", JO.getString("ID_immob"));
                    data.put("type", JO.getString("Type"));
                    data.put("ville", JO.getString("Ville"));
                    data.put("etat", JO.getString("Etat"));
                    data.put("prix", JO.getString("Prix"));
                    data.put("discription", JO.getString("Discription"));
                    data.put("idutilisateur", JO.getString("ID_Utilisateur"));
                    data.put("url", JO.getString("Url"));
                    data.put("favoris", JO.getString("Favoris"));


                    Immobilierlist.add(data);
                    count++;


                }

                customListViewAdapter = new CustomListViewAdapter(ctx, Immobilierlist,"1");
                Homefragment.listView.setAdapter(customListViewAdapter);


            } catch (JSONException e) {
                e.printStackTrace();
            }


            Toast.makeText(ctx,result, Toast.LENGTH_LONG).show();

        }

        if (method.equals("getdiscussion")){
            try {

                ArrayList<HashMap<String, String>> discussionlist = new ArrayList<>();

                jsonObject=new JSONObject(result);

                jsonArray=jsonObject.getJSONArray("server_response");
                int count=0;
                while (count<jsonArray.length()){


                    JSONObject JO= jsonArray.getJSONObject(count);
                    HashMap<String, String> data = new HashMap<>();
                    data.put("idmessage", JO.getString("idmessage"));
                    data.put("idsender", JO.getString("idsender"));
                    data.put("idresever", JO.getString("idresever"));
                    data.put("last", JO.getString("contenue"));
                    data.put("nom", JO.getString("Nom"));
                    data.put("prenom", JO.getString("Prenom"));

                    discussionlist.add(data);
                    count++;


                }


                DiscussionsAdapter discussionsAdapter=new DiscussionsAdapter(ctx,discussionlist);
                MessageFragment.disclist.setAdapter(discussionsAdapter);






            } catch (JSONException e) {
                e.printStackTrace();
            }


        }
        if (method.equals("sendmessage")){

            if (result.equals("message envoye")){

                Discussion.messagetosend.setText("");
                Toast.makeText(ctx, result, Toast.LENGTH_LONG).show();


            }else {
                Toast.makeText(ctx, result, Toast.LENGTH_LONG).show();

            }

        }

        if (method.equals("getmessage")){

            try {
                ArrayList<HashMap<String, String>> messagelist = new ArrayList<>();

                jsonObject=new JSONObject(result);

                jsonArray=jsonObject.getJSONArray("server_response");
                int count=0;
                while (count<jsonArray.length()){


                    JSONObject JO= jsonArray.getJSONObject(count);
                    HashMap<String, String> data = new HashMap<>();
                    data.put("idmessage", JO.getString("idmessage"));
                    data.put("idsender", JO.getString("idsender"));
                    data.put("idresever", JO.getString("idresever"));
                    data.put("contenue", JO.getString("contenue"));

                    messagelist.add(data);
                    count++;


                }

                MessageAdapter messageAdapter = new MessageAdapter(ctx,messagelist,Discussion.idutilisateur);
                Discussion.messageliste.setAdapter(messageAdapter);






            } catch (JSONException e) {
                e.printStackTrace();
            }


        }
        if(method.equals("deletecomment")){

            Toast.makeText(ctx, result, Toast.LENGTH_LONG).show();


        }

        if(method.equals("favoris")){


            ImmobilierDetais.ajouteraufavori.setVisibility(View.GONE);
                Toast.makeText(ctx, result, Toast.LENGTH_LONG).show();





        }


        if (method.equals("getcommentlist")){

            try {
                ArrayList<HashMap<String, String>> commentlist = new ArrayList<>();

                jsonObject=new JSONObject(result);

                jsonArray=jsonObject.getJSONArray("server_response");
                int count=0;
                while (count<jsonArray.length()){


                    JSONObject JO= jsonArray.getJSONObject(count);
                    HashMap<String, String> data = new HashMap<>();
                    data.put("idcomment", JO.getString("ID_comment"));
                    data.put("contenue", JO.getString("contenue"));
                    data.put("heur", JO.getString("heur"));
                    data.put("date", JO.getString("date"));
                    data.put("nom", JO.getString("Nom"));
                    data.put("prenom", JO.getString("Prenom"));
                    data.put("idutilisateur", JO.getString("ID_Utilisateur"));

                    commentlist.add(data);
                    count++;


                }
                CommentlistAdapter commentlistAdapter= new CommentlistAdapter(ctx,commentlist);
                ImmobilierDetais.commentlist.setAdapter(commentlistAdapter);






            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
        if (method.equals("commenter")){
            Toast.makeText(ctx, result, Toast.LENGTH_LONG).show();

        }
        if (method.equals("get")){

            try {

                jsonObject=new JSONObject(result);

                jsonArray=jsonObject.getJSONArray("server_response");
                int count=0;
                    JSONObject JO= jsonArray.getJSONObject(count);
                    HashMap<String, String> data = new HashMap<>();
                    data.put("idutilisateur", JO.getString("ID_Utilisateur"));
                    data.put("nom", JO.getString("Nom"));
                    data.put("prenom", JO.getString("Prenom"));
                    data.put("email", JO.getString("Email"));

                    ImmobilierDetais.nomprenom.setText(data.get("nom")+" "+data.get("prenom"));
                    ImmobilierDetais.email.setText(data.get("email"));







            } catch (JSONException e) {
                e.printStackTrace();
            }



        }
        if (method.equals("geturl")){

            try {
                ArrayList<String> imageurl = new ArrayList<>();

                jsonObject=new JSONObject(result);

                jsonArray=jsonObject.getJSONArray("server_response");
                int count=0;
                while (count<jsonArray.length()){


                    JSONObject JO= jsonArray.getJSONObject(count);
                    imageurl.add(JO.getString("url"));
                    count++;


                }

                SliderAdapter sliderAdapter = new SliderAdapter(ctx,imageurl);
                ImmobilierDetais.myviewpager.setAdapter(sliderAdapter);




            } catch (JSONException e) {
                e.printStackTrace();
            }



        }
        if (method.equals("newimmob")){

            try {
                ArrayList<HashMap<String, String>> Immobilierlist = new ArrayList<>();

                jsonObject=new JSONObject(result);

                jsonArray=jsonObject.getJSONArray("server_response");
                int count=0;
                while (count<jsonArray.length()){


                    JSONObject JO= jsonArray.getJSONObject(count);
                    HashMap<String, String> data = new HashMap<>();
                    data.put("idimmob", JO.getString("ID_immob"));
                    data.put("type", JO.getString("Type"));
                    data.put("ville", JO.getString("Ville"));
                    data.put("etat", JO.getString("Etat"));
                    data.put("prix", JO.getString("Prix"));
                    data.put("discription", JO.getString("Discription"));
                    data.put("idutilisateur", JO.getString("ID_Utilisateur"));
                    data.put("url", JO.getString("Url"));
                    data.put("favoris", JO.getString("Favoris"));


                    Immobilierlist.add(data);
                    count++;


                }

                customListViewAdapter = new CustomListViewAdapter(ctx, Immobilierlist,"1");
                Homefragment.listView.setAdapter(customListViewAdapter);


            } catch (JSONException e) {
                e.printStackTrace();
            }



        }

        if(method.equals("AjouterImmobilier")){



            Toast.makeText(ctx, result, Toast.LENGTH_LONG).show();
            try {
                jsonObject=new JSONObject(result);
                jsonArray=jsonObject.getJSONArray("server_response");
                JSONObject JO= jsonArray.getJSONObject(0);

                if (Ajouterimmob.selectionResult.size()>0){

                    String id= JO.getString("ID_immob");

                        new Photos().Inserer(Ajouterimmob.selectionResult,ctx,id);



                }



            } catch (JSONException e) {
                e.printStackTrace();
            }


        }


    }

    @Override
    protected void onProgressUpdate(Void... values) {



        super.onProgressUpdate(values);
    }

    public String getutilisateur(String idutilisateur){
        String response="";
        String reg_url = Constans.getIPadress()+"/getutilisateurid.php";

        try {

            URL url=new URL(reg_url);
            HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoOutput(true);
            OutputStream OS=httpURLConnection.getOutputStream();
            BufferedWriter bufferedWriter=new BufferedWriter(new OutputStreamWriter(OS,"UTF-8"));
            String data= URLEncoder.encode("idutilisateur","UTF-8")+"="+URLEncoder.encode(idutilisateur,"UTF-8");
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


        return  response;
    }

    public String geturlimage(String idimmob){
        String response="";
        String reg_url = Constans.getIPadress()+"/getimages.php";

        try {

            URL url=new URL(reg_url);
            HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoOutput(true);
            OutputStream OS=httpURLConnection.getOutputStream();
            BufferedWriter bufferedWriter=new BufferedWriter(new OutputStreamWriter(OS,"UTF-8"));
            String data= URLEncoder.encode("idimmob","UTF-8")+"="+URLEncoder.encode(idimmob,"UTF-8");
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


        return  response;


    }

    public String addnewimmob(String Type,String Ville,String Etat,String Prix,String Discription,String ID_Utilisateur){
        String response="";
        String reg_url = Constans.getIPadress()+"/ajouterimmob.php";


        try {

            URL url=new URL(reg_url);
            HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoOutput(true);
            OutputStream OS=httpURLConnection.getOutputStream();
            BufferedWriter bufferedWriter=new BufferedWriter(new OutputStreamWriter(OS,"UTF-8"));
            String data= URLEncoder.encode("type","UTF-8")+"="+URLEncoder.encode(Type,"UTF-8")+"&"+
                    URLEncoder.encode("ville","UTF-8")+"="+URLEncoder.encode(Ville,"UTF-8")+"&"+
                    URLEncoder.encode("etat","UTF-8")+"="+URLEncoder.encode(Etat,"UTF-8")+"&"+
                    URLEncoder.encode("prix","UTF-8")+"="+URLEncoder.encode(Prix,"UTF-8")+"&"+
                    URLEncoder.encode("discription","UTF-8")+"="+URLEncoder.encode(Discription,"UTF-8")+"&"+
                    URLEncoder.encode("idutilisateur","UTF-8")+"="+URLEncoder.encode(ID_Utilisateur,"UTF-8")+"&"+
                    URLEncoder.encode("validation","UTF-8")+"="+URLEncoder.encode("false","UTF-8") ;
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
        return response;
    }

    public String getlistimmob(){
        String response="";

        String reg_url = Constans.getIPadress()+"/json.php";

        try {
            URL url= new URL(reg_url);
            HttpURLConnection httpURLConnection =(HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.setDoOutput(true);
            OutputStream OS=httpURLConnection.getOutputStream();
            BufferedWriter bufferedWriter=new BufferedWriter(new OutputStreamWriter(OS,"UTF-8"));
            String data= URLEncoder.encode("idutilisateur","UTF-8")+"="+URLEncoder.encode(String.valueOf(HomeActivity.utilisateur.getID_Utilisateur()),"UTF-8");
            bufferedWriter.write(data);
            bufferedWriter.flush();
            bufferedWriter.close();
            OS.close();
            InputStream inputStream= httpURLConnection.getInputStream();
            BufferedReader bufferedReader= new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder stringBuilder= new StringBuilder();

            while ((response=bufferedReader.readLine())!=null){

                stringBuilder.append(response+"\n");

            }
            bufferedReader.close();
            inputStream.close();
            httpURLConnection.disconnect();
            return stringBuilder.toString().trim();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return response;
    }

    public String addcomment(String contenue,String idimmob,String idutilisateur){
        String response="";
        String reg_url = Constans.getIPadress()+"/insertcomment.php";


        try {

            URL url=new URL(reg_url);
            HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoOutput(true);
            OutputStream OS=httpURLConnection.getOutputStream();
            BufferedWriter bufferedWriter=new BufferedWriter(new OutputStreamWriter(OS,"UTF-8"));
            String data= URLEncoder.encode("contenue","UTF-8")+"="+URLEncoder.encode(contenue,"UTF-8")+"&"+
                    URLEncoder.encode("idimmob","UTF-8")+"="+URLEncoder.encode(idimmob,"UTF-8")+"&"+
                    URLEncoder.encode("idutilisateur","UTF-8")+"="+URLEncoder.encode(idutilisateur,"UTF-8") ;
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
        return response;
    }

    public String deletecomment(String idcomment){
        String response="";
        String reg_url = Constans.getIPadress()+"/deletecomment.php";

        try {

            URL url=new URL(reg_url);
            HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoOutput(true);
            OutputStream OS=httpURLConnection.getOutputStream();
            BufferedWriter bufferedWriter=new BufferedWriter(new OutputStreamWriter(OS,"UTF-8"));
            String data= URLEncoder.encode("idcomment","UTF-8")+"="+URLEncoder.encode(idcomment,"UTF-8");
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


        return response;
    }

    public String getcommentlist(String idimmob){
        String response="";

        String reg_url = Constans.getIPadress()+"/getcomments.php";

        try {

            URL url=new URL(reg_url);
            HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoOutput(true);
            OutputStream OS=httpURLConnection.getOutputStream();
            BufferedWriter bufferedWriter=new BufferedWriter(new OutputStreamWriter(OS,"UTF-8"));
            String data= URLEncoder.encode("idimmob","UTF-8")+"="+URLEncoder.encode(idimmob,"UTF-8");
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

        return  response;
    }

    public String addfavoris(String idutilisateur,String idimmob){
        String response="";

        String reg_url = Constans.getIPadress()+"/addfavoris.php";

        try {

            URL url=new URL(reg_url);
            HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoOutput(true);
            OutputStream OS=httpURLConnection.getOutputStream();
            BufferedWriter bufferedWriter=new BufferedWriter(new OutputStreamWriter(OS,"UTF-8"));
            String data= URLEncoder.encode("idimmob","UTF-8")+"="+URLEncoder.encode(idimmob,"UTF-8")+"&"+
                    URLEncoder.encode("idutilisateur","UTF-8")+"="+URLEncoder.encode(idutilisateur,"UTF-8");
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


        return response;

    }

    public String getmessagelist(String myid,String yourid){
    String response="";

        String reg_url = Constans.getIPadress()+"/getmessage.php";

        try {

            URL url=new URL(reg_url);
            HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoOutput(true);
            OutputStream OS=httpURLConnection.getOutputStream();
            BufferedWriter bufferedWriter=new BufferedWriter(new OutputStreamWriter(OS,"UTF-8"));
            String data= URLEncoder.encode("myid","UTF-8")+"="+URLEncoder.encode(myid,"UTF-8")+"&"+
                    URLEncoder.encode("yourid","UTF-8")+"="+URLEncoder.encode(yourid,"UTF-8");
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


    return response;
    }

    public String sendmessage(String myid,String yourid,String contenue){
        String response="";

        String reg_url = Constans.getIPadress()+"/insertmessage.php";

        try {

            URL url=new URL(reg_url);
            HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoOutput(true);
            OutputStream OS=httpURLConnection.getOutputStream();
            BufferedWriter bufferedWriter=new BufferedWriter(new OutputStreamWriter(OS,"UTF-8"));
            String data= URLEncoder.encode("myid","UTF-8")+"="+URLEncoder.encode(myid,"UTF-8")+"&"+
                    URLEncoder.encode("yourid","UTF-8")+"="+URLEncoder.encode(yourid,"UTF-8")+"&"+
                    URLEncoder.encode("contenue","UTF-8")+"="+URLEncoder.encode(contenue,"UTF-8");
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

        return response;
    }

    public String getdiscussion(String myid){
        String response="";

        String reg_url = Constans.getIPadress()+"/getdiscussion.php";

        try {

            URL url=new URL(reg_url);
            HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoOutput(true);
            OutputStream OS=httpURLConnection.getOutputStream();
            BufferedWriter bufferedWriter=new BufferedWriter(new OutputStreamWriter(OS,"UTF-8"));
            String data= URLEncoder.encode("myid","UTF-8")+"="+URLEncoder.encode(myid,"UTF-8");
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

        return response;
    }

    public String search(String type,String ville,String date){

        String response="";

        String reg_url = Constans.getIPadress()+"/search.php";

        try {
            URL url= new URL(reg_url);
            HttpURLConnection httpURLConnection =(HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoOutput(true);
            OutputStream OS=httpURLConnection.getOutputStream();
            BufferedWriter bufferedWriter=new BufferedWriter(new OutputStreamWriter(OS,"UTF-8"));
            String data= URLEncoder.encode("type","UTF-8")+"="+URLEncoder.encode(type,"UTF-8")+"&"+
                    URLEncoder.encode("ville","UTF-8")+"="+URLEncoder.encode(ville,"UTF-8")+"&"+
                    URLEncoder.encode("date","UTF-8")+"="+URLEncoder.encode(date,"UTF-8")+"&"+
                    URLEncoder.encode("idutilisateur","UTF-8")+"="+URLEncoder.encode(String.valueOf(HomeActivity.utilisateur.getID_Utilisateur()),"UTF-8");
            bufferedWriter.write(data);;
            bufferedWriter.write(data);
            bufferedWriter.flush();
            bufferedWriter.close();
            OS.close();
            InputStream inputStream= httpURLConnection.getInputStream();
            BufferedReader bufferedReader= new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder stringBuilder= new StringBuilder();

            while ((response=bufferedReader.readLine())!=null){

                stringBuilder.append(response+"\n");

            }
            bufferedReader.close();
            inputStream.close();
            httpURLConnection.disconnect();
            return stringBuilder.toString().trim();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return response;
    }

    public String getreservation(String idutilisateur,String idimmob){
        String response="";
        String reg_url = Constans.getIPadress()+"/getreservation.php";

        try {
            URL url= new URL(reg_url);
            HttpURLConnection httpURLConnection =(HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoOutput(true);
            OutputStream OS=httpURLConnection.getOutputStream();
            BufferedWriter bufferedWriter=new BufferedWriter(new OutputStreamWriter(OS,"UTF-8"));
            String data= URLEncoder.encode("idimmob","UTF-8")+"="+URLEncoder.encode(idimmob,"UTF-8")+"&"+
                    URLEncoder.encode("idutilisateur","UTF-8")+"="+URLEncoder.encode(idutilisateur,"UTF-8");
            bufferedWriter.write(data);;
            bufferedWriter.write(data);
            bufferedWriter.flush();
            bufferedWriter.close();
            OS.close();
            InputStream inputStream= httpURLConnection.getInputStream();
            BufferedReader bufferedReader= new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder stringBuilder= new StringBuilder();

            while ((response=bufferedReader.readLine())!=null){

                stringBuilder.append(response+"\n");

            }
            bufferedReader.close();
            inputStream.close();
            httpURLConnection.disconnect();
            return stringBuilder.toString().trim();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        return response;
    }

    public String addreservation(String idimmob,String idutilisateur,String date){
        String response="";
        String reg_url = Constans.getIPadress()+"/addreservation.php";

        try {
            URL url= new URL(reg_url);
            HttpURLConnection httpURLConnection =(HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoOutput(true);
            OutputStream OS=httpURLConnection.getOutputStream();
            BufferedWriter bufferedWriter=new BufferedWriter(new OutputStreamWriter(OS,"UTF-8"));
            String data= URLEncoder.encode("idimmob","UTF-8")+"="+URLEncoder.encode(idimmob,"UTF-8")+"&"+
                    URLEncoder.encode("idutilisateur","UTF-8")+"="+URLEncoder.encode(idutilisateur,"UTF-8")+"&"+
                    URLEncoder.encode("date","UTF-8")+"="+URLEncoder.encode(date,"UTF-8");
            bufferedWriter.write(data);;
            bufferedWriter.write(data);
            bufferedWriter.flush();
            bufferedWriter.close();
            OS.close();
            InputStream inputStream= httpURLConnection.getInputStream();
            BufferedReader bufferedReader= new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder stringBuilder= new StringBuilder();

            while ((response=bufferedReader.readLine())!=null){

                stringBuilder.append(response+"\n");

            }
            bufferedReader.close();
            inputStream.close();
            httpURLConnection.disconnect();
            return stringBuilder.toString().trim();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        return response;


    }

    public String deletereservation(String idreservation){

        String response="";
        String reg_url = Constans.getIPadress()+"/deletereservation.php";

        try {
            URL url= new URL(reg_url);
            HttpURLConnection httpURLConnection =(HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoOutput(true);
            OutputStream OS=httpURLConnection.getOutputStream();
            BufferedWriter bufferedWriter=new BufferedWriter(new OutputStreamWriter(OS,"UTF-8"));
            String data= URLEncoder.encode("idresevation","UTF-8")+"="+URLEncoder.encode(idreservation,"UTF-8");
            bufferedWriter.write(data);;
            bufferedWriter.write(data);
            bufferedWriter.flush();
            bufferedWriter.close();
            OS.close();
            InputStream inputStream= httpURLConnection.getInputStream();
            BufferedReader bufferedReader= new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder stringBuilder= new StringBuilder();

            while ((response=bufferedReader.readLine())!=null){

                stringBuilder.append(response+"\n");

            }
            bufferedReader.close();
            inputStream.close();
            httpURLConnection.disconnect();
            return stringBuilder.toString().trim();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        return response;


    }

    public String getmesannonce(String idutilisateur) {
        String response="";
        String reg_url = Constans.getIPadress()+"/getmesannonce.php";

        try {
            URL url= new URL(reg_url);
            HttpURLConnection httpURLConnection =(HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoOutput(true);
            OutputStream OS=httpURLConnection.getOutputStream();
            BufferedWriter bufferedWriter=new BufferedWriter(new OutputStreamWriter(OS,"UTF-8"));
            String data= URLEncoder.encode("idutilisateur","UTF-8")+"="+URLEncoder.encode(idutilisateur,"UTF-8");
            bufferedWriter.write(data);;
            bufferedWriter.write(data);
            bufferedWriter.flush();
            bufferedWriter.close();
            OS.close();
            InputStream inputStream= httpURLConnection.getInputStream();
            BufferedReader bufferedReader= new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder stringBuilder= new StringBuilder();

            while ((response=bufferedReader.readLine())!=null){

                stringBuilder.append(response+"\n");

            }
            bufferedReader.close();
            inputStream.close();
            httpURLConnection.disconnect();
            return stringBuilder.toString().trim();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        return response;

    }

    public String deleteannonce(String idimmob){

        String response="";
        String reg_url = Constans.getIPadress()+"/deleteannonce.php";

        try {
            URL url= new URL(reg_url);
            HttpURLConnection httpURLConnection =(HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoOutput(true);
            OutputStream OS=httpURLConnection.getOutputStream();
            BufferedWriter bufferedWriter=new BufferedWriter(new OutputStreamWriter(OS,"UTF-8"));
            String data= URLEncoder.encode("idimmob","UTF-8")+"="+URLEncoder.encode(idimmob,"UTF-8");
            bufferedWriter.write(data);;
            bufferedWriter.write(data);
            bufferedWriter.flush();
            bufferedWriter.close();
            OS.close();
            InputStream inputStream= httpURLConnection.getInputStream();
            BufferedReader bufferedReader= new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder stringBuilder= new StringBuilder();

            while ((response=bufferedReader.readLine())!=null){

                stringBuilder.append(response+"\n");

            }
            bufferedReader.close();
            inputStream.close();
            httpURLConnection.disconnect();
            return stringBuilder.toString().trim();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        return response;
    }

    public String getfavoris(String idutilisateur) {
        String response="";
        String reg_url = Constans.getIPadress()+"/getfavoris.php";

        try {
            URL url= new URL(reg_url);
            HttpURLConnection httpURLConnection =(HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoOutput(true);
            OutputStream OS=httpURLConnection.getOutputStream();
            BufferedWriter bufferedWriter=new BufferedWriter(new OutputStreamWriter(OS,"UTF-8"));
            String data= URLEncoder.encode("idutilisateur","UTF-8")+"="+URLEncoder.encode(idutilisateur,"UTF-8");
            bufferedWriter.write(data);;
            bufferedWriter.write(data);
            bufferedWriter.flush();
            bufferedWriter.close();
            OS.close();
            InputStream inputStream= httpURLConnection.getInputStream();
            BufferedReader bufferedReader= new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder stringBuilder= new StringBuilder();

            while ((response=bufferedReader.readLine())!=null){

                stringBuilder.append(response+"\n");

            }
            bufferedReader.close();
            inputStream.close();
            httpURLConnection.disconnect();
            return stringBuilder.toString().trim();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        return response;
    }

    public String deletefavoris(String idimmob, String idutilisateur) {
        String response="";
        String reg_url = Constans.getIPadress()+"/deletefavoris.php";

        try {
            URL url= new URL(reg_url);
            HttpURLConnection httpURLConnection =(HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoOutput(true);
            OutputStream OS=httpURLConnection.getOutputStream();
            BufferedWriter bufferedWriter=new BufferedWriter(new OutputStreamWriter(OS,"UTF-8"));
            String data=URLEncoder.encode("idimmob","UTF-8")+"="+URLEncoder.encode(idimmob,"UTF-8")+"&"+
                    URLEncoder.encode("idutilisateur","UTF-8")+"="+URLEncoder.encode(idutilisateur,"UTF-8");
            bufferedWriter.write(data);;
            bufferedWriter.write(data);
            bufferedWriter.flush();
            bufferedWriter.close();
            OS.close();
            InputStream inputStream= httpURLConnection.getInputStream();
            BufferedReader bufferedReader= new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder stringBuilder= new StringBuilder();

            while ((response=bufferedReader.readLine())!=null){

                stringBuilder.append(response+"\n");

            }
            bufferedReader.close();
            inputStream.close();
            httpURLConnection.disconnect();
            return stringBuilder.toString().trim();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        return response;
    }



}
