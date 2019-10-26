
package com.example.tutorial;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JSON extends AppCompatActivity {
    private Button btnGetName,btnGetCourse,btnGetAge,btnGetBooks,btnGetLibraryId,btnGetFriends;
    private TextView tvDiplay;
    private String myJasonObjectString ="{\n" +
            "  \"studentName\": \"Lee Boon Kong\",\n" +
            "  \"courseName\": \"IT\",\n" +
            "  \"age\": \"19\",\n" +
            "  \"borrowBooks\": [\n" +
            "    \"The Prince\",\n" +
            "    \"Princess\"\n" +
            "  ],\n" +
            "  \"libraryProfile\": {\n" +
            "    \"libraryId\": \"0003\",\n" +
            "    \"numberOfBorrowsBooks\": \"3\",\n" +
            "    \"allowsTheEnter\": \"true\"\n" +
            "  },\n" +
            "  \"friends\": [\n" +
            "    {\n" +
            "      \"name\": \"Jason\",\n" +
            "      \"status\": \"Best friend\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"name\": \"Jack\",\n" +
            "      \"status\": \"unfriended\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"name\": \"May\",\n" +
            "      \"status\": \"friend\"\n" +
            "    }\n" +
            "  ]\n" +
            "}";
    private JSONObject myJsonObject;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_json);
        findViews();
        setListeners();
        prepareJSON();
    }

    private void findViews(){
        btnGetName = findViewById(R.id.btnGetName);
        btnGetCourse = findViewById(R.id.btnGetCourse);
        btnGetAge = findViewById(R.id.btnGetAge);
        tvDiplay = findViewById(R.id.tvDisplay);
        btnGetBooks = findViewById(R.id.btnGetBooks);
        btnGetLibraryId = findViewById(R.id.btnGetLibraryId);
        btnGetFriends = findViewById(R.id.btnGetFriends);
    }

    private void setListeners(){
        btnGetName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    String name = myJsonObject.getString(name="studentName");
                    tvDiplay.setText(name);
                } catch (JSONException e){
                    e.printStackTrace();
                }
            }
        });

        btnGetCourse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    String name = myJsonObject.getString(name="courseName");
                    tvDiplay.setText(name);
                } catch (JSONException e){
                    e.printStackTrace();
                }
            }
        });

        btnGetAge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    String name = myJsonObject.getString(name="age");
                    tvDiplay.setText(name);
                } catch (JSONException e){
                    e.printStackTrace();
                }
            }
        });

        btnGetBooks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    JSONArray booksArray = new JSONArray(myJsonObject.getString("borrowBooks"));
                    String result = "";
                    for(int i=0; i < booksArray.length(); i++){
                        String bookName = booksArray.getString(i);
                        result += bookName + "\n";
                    }
                    tvDiplay.setText(result);
                } catch (JSONException e){
                    e.printStackTrace();
                }
            }
        });

        btnGetLibraryId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    JSONObject libraryJasonObject = myJsonObject.getJSONObject("libraryProfile");
                    String libraryId = libraryJasonObject.getString("libraryId");
                    tvDiplay.setText(libraryId);
                } catch (JSONException e){
                    e.printStackTrace();
                }
            }
        });

        btnGetFriends.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    JSONArray friendsArray = myJsonObject.getJSONArray("friends");
                    for(int i=0; i<friendsArray.length(); i++){
                        JSONObject friend = friendsArray.getJSONObject(i);
                        String name = friend.getString("name");
                        if(name.equalsIgnoreCase("Jack")){
                            tvDiplay.setText(friend.getString("status"));
                        }
                    }
                }catch (JSONException e){
                    e.printStackTrace();
                }
            }
        });
    }
    private void prepareJSON(){
        try{
            myJsonObject = new JSONObject(myJasonObjectString);
        } catch (JSONException e){
            e.printStackTrace();
        }
    }
}
