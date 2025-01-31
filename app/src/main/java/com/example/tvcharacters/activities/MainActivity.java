package com.example.tvcharacters.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tvcharacters.R;
import com.example.tvcharacters.adapters.CustomeAdapter;
import com.example.tvcharacters.models.Data;
import com.example.tvcharacters.models.myData;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList <Data> arr;
    private RecyclerView recyclerView;
    private LinearLayoutManager layoutManager;
    private CustomeAdapter customeAdapter;
    private SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        searchView=findViewById(R.id.searchView);
        searchView.clearFocus();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filterList(newText);
                return true;
            }
        });


        recyclerView=findViewById(R.id.rvcon);

        layoutManager= new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        // Create array
        arr=new ArrayList<Data>();
        for(int i=0; i<myData.nameArray.length;i++){
            arr.add(new Data(
                    myData.nameArray[i],
                    myData.descriptionArray[i],
                    myData.drawableArray[i],
                    myData.id_[i]
            ));
        }

        customeAdapter= new CustomeAdapter(arr);
        recyclerView.setAdapter(customeAdapter);
    }

    private void filterList(String text) {
        ArrayList<Data>filteredList= new ArrayList<>();
        for(Data data: arr){
            if(data.getName().toLowerCase().contains(text.toLowerCase())||
                    data.getDescription().toLowerCase().contains(text.toLowerCase())){
                filteredList.add(data);
            }
        }
        if(filteredList.isEmpty()){
            Toast.makeText(this,"No data found",Toast.LENGTH_SHORT).show();
        }else{
            customeAdapter.setFilteredList(filteredList);
        }
    }
}