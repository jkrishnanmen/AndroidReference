package com.example.empressum.jake.expandableListView;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.Toast;

import com.example.empressum.jake.R;

import java.util.ArrayList;
import java.util.LinkedHashMap;

public class ExpandableListViewMain extends AppCompatActivity {
    private LinkedHashMap<String,GroupInfo> subjects= new LinkedHashMap<String,GroupInfo>();
    private ArrayList<GroupInfo> deptList= new ArrayList<GroupInfo>();

    private CustomAdapter listAdapter;
    private ExpandableListView simpleExpandableListView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expandable_list_view);

        //add data for displaying in expandable list view
        loadData();

        //get reference of the ExpandableListViewMain
        simpleExpandableListView =(ExpandableListView) findViewById(R.id.simpleExpandableListView);
        //create the adapter by passing your ArrayList data
        listAdapter = new CustomAdapter(ExpandableListViewMain.this,deptList);
        //attach the adapter to the expandable list view
        simpleExpandableListView.setAdapter(listAdapter);

        //expand all the groups
        expandAll();

        //setonchildclicklistener listener for child row click
        simpleExpandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener(){
            public boolean onChildClick(ExpandableListView parent,View v, int groupPosition, int childPosition, long id){
                //get the group leader
                GroupInfo headerInfo=deptList.get(groupPosition);
                //get the child info
                ChildInfo detailInfo=headerInfo.getProductList().get(childPosition);
                //display it or do something with it
                Toast.makeText(getBaseContext(),"Clicked on ::"+ headerInfo.getName()+"/" + detailInfo.getName(),Toast.LENGTH_LONG).show();
                return false;
            }
        });

        //setonGroupclicklistener listener for group heading click
        simpleExpandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener(){
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                //get the group header
                GroupInfo headerInfo = deptList.get(groupPosition);
                //display it or do something with it
                Toast.makeText(getBaseContext(),"Header is::" + headerInfo.getName(),Toast.LENGTH_LONG).show();
                return false;
            }
        });
    }

    //method to expand all groups
    private void expandAll(){
        int count=listAdapter.getGroupCount();
        for(int i=0;i<count;i++){
            simpleExpandableListView.expandGroup(i);
        }
    }

    //load some initial data inot out list
    private void loadData(){

        addProduct("Android","ListView");
        addProduct("Android","ExpandableListView");
        addProduct("Android","GridView");
        addProduct("Android","RecyclerView");

        addProduct("Java","PolyMorphism");
        addProduct("Java","Collections");
        addProduct("Java","Abstraction");
        addProduct("Java","Encapsulation");
        addProduct("Java","Inheritance");

        addProduct("CProg","no such thing");
        addProduct("CProg","bla bla");
    }

    //here we maintain our products in various departments
    private int addProduct(String department, String product){
        int groupPosition=0;
        //check the hash map if the group already exists
        GroupInfo headerInfo= subjects.get(department);
        //add the group if doesnt exists
        if(headerInfo==null){
            headerInfo= new GroupInfo();
            headerInfo.setName(department);
            subjects.put(department,headerInfo);
            deptList.add(headerInfo);
        }

        //get the children for the group
        ArrayList<ChildInfo> productList= headerInfo.getProductList();
        //size of the children list
        int listSize=productList.size();
        //add to the counter
        listSize++;

        //create a new child and add that to the group
        ChildInfo detailInfo= new ChildInfo();
        detailInfo.setSequence(String.valueOf(listSize));
        detailInfo.setName(product);
        productList.add(detailInfo);
        headerInfo.setProductList(productList);

        //find the group position inside the list
        groupPosition = deptList.indexOf(headerInfo);
        return groupPosition;
    }
}
