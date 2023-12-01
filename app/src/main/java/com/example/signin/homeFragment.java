package com.example.signin;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.example.signin.adapters.AllproductsAdapter;
import com.example.signin.adapters.CategoryAdapters;
import com.example.signin.adapters.EditorsChoiseAdapter;
import com.example.signin.models.AllproductsModel;
import com.example.signin.models.CategoryModel;
import com.example.signin.models.EditorsChoiceModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class homeFragment extends Fragment {

    //search
    ImageButton searchview;
    //
    RecyclerView catRecyclerview,editorsChoiceRecyclerview,allproductsRecyclerview;
    //Category recycler view
    CategoryAdapters categoryAdapters;
    List<CategoryModel> categoryModelList;

    //Editors choice
    EditorsChoiseAdapter editorsChoiseAdapter;
    List<EditorsChoiceModel> editorsChoiceModelList;

    //All products
    AllproductsAdapter allproductsAdapter;
    List<AllproductsModel> allproductsModelList;

    //search



    //firestore
   // FirebaseFirestore db;
    private DatabaseReference reference;
    private DatabaseReference choice;
    private  DatabaseReference allproduct;
    //private StorageReference mStorageRef;

    ImageSlider imageSlider;



    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_home, container, false);


        searchview=root.findViewById(R.id.search);
        catRecyclerview = root.findViewById(R.id.rec_category);
        editorsChoiceRecyclerview=root.findViewById(R.id.editors_choice_rec);

        allproductsRecyclerview=root.findViewById(R.id.all_rec);
        imageSlider = root.findViewById(R.id.image_slider);


       // db = FirebaseFirestore.getInstance();


        List<SlideModel> slideModels = new ArrayList<>();

        slideModels.add(new SlideModel("https://firebasestorage.googleapis.com/v0/b/handyart-54943.appspot.com/o/image1.jpg?alt=media&token=3b493d76-39cf-4dc3-a449-2554a726c1e3", ScaleTypes.FIT));
        slideModels.add(new SlideModel("https://firebasestorage.googleapis.com/v0/b/handyart-54943.appspot.com/o/image2.jpg?alt=media&token=bd12d597-a1c5-4ee3-9bd6-d29b1bf1344b", ScaleTypes.FIT));
        slideModels.add(new SlideModel("https://firebasestorage.googleapis.com/v0/b/handyart-54943.appspot.com/o/image3.jpg?alt=media&token=349bf45b-8d8c-4541-b69b-5450fab8ad8d", ScaleTypes.FIT));
        slideModels.add(new SlideModel("https://firebasestorage.googleapis.com/v0/b/handyart-54943.appspot.com/o/image4.jpg?alt=media&token=f9c7de36-2aec-4a02-8469-fcbef7cd03c8", ScaleTypes.FIT));
        slideModels.add(new SlideModel("https://firebasestorage.googleapis.com/v0/b/handyart-54943.appspot.com/o/image5.jpg?alt=media&token=de482110-988f-4853-ba92-2add4128ca3d", ScaleTypes.FIT));

        imageSlider.setImageList(slideModels, ScaleTypes.FIT);

        //search
//        SearchView searchview =(SearchView)item.getActionView();
//        searchview.isSubmitButtonEnabled();
//        searchview.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String s) {
//                return false;
//            }
//
//            @Override
//            public boolean onQueryTextChange(String newText)
//            {
//                allproductsAdapter.getFilter().filter(newText);
//                return false;
//            }
//        });


        searchview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                Fragment fragment = new searchFrag();
//                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
//                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//                fragmentTransaction.replace(R.id.home_layout, fragment);
////                fragmentTransaction.addToBackStack(null);
//                fragmentTransaction.commit();

                Intent in = new Intent(getActivity(), search_activity.class);
                startActivity(in);
               Animatoo.animateSlideUp(getActivity());;


            }
        });

        //

        catRecyclerview.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false));
        categoryModelList = new ArrayList<>();
        categoryAdapters = new CategoryAdapters(getContext(), categoryModelList);
        catRecyclerview.setAdapter(categoryAdapters);


       /* db.collection("Categories")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {

                                CategoryModel categoryModel = document.toObject(CategoryModel.class);
                                categoryModelList.add(categoryModel);
                                categoryAdapters.notifyDataSetChanged();

                            }
                        } else {

                        }




                    }

                });*/

        reference = FirebaseDatabase.getInstance().getReference("categories");

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    CategoryModel categoryModel=dataSnapshot.getValue(CategoryModel.class);
                    categoryModelList.add(categoryModel);

                }
                categoryAdapters.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        //

        //editor's choice
        editorsChoiceRecyclerview.setLayoutManager(new LinearLayoutManager(getActivity(),RecyclerView.HORIZONTAL,false));

        editorsChoiceModelList= new ArrayList<>();
        editorsChoiseAdapter = new EditorsChoiseAdapter(getContext(),editorsChoiceModelList);
        editorsChoiceRecyclerview.setAdapter(editorsChoiseAdapter);

       choice = FirebaseDatabase.getInstance().getReference("EditorsChoice");


        choice.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){

                    EditorsChoiceModel editorsChoiceModel=dataSnapshot.getValue(EditorsChoiceModel.class);
                   editorsChoiceModelList.add(editorsChoiceModel);

                }
                editorsChoiseAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                Toast.makeText(getActivity(), "Something went wrong", Toast.LENGTH_SHORT).show();
            }
        });

       allproductsRecyclerview.setLayoutManager(new GridLayoutManager(getActivity(), 2));
//       allproductsRecyclerview.setHasFixedSize(true);
       allproductsModelList = new ArrayList<>();
       allproductsAdapter = new AllproductsAdapter(getContext(), allproductsModelList);
        allproductsRecyclerview.setAdapter(allproductsAdapter);

        allproduct = FirebaseDatabase.getInstance().getReference("products");


        allproduct.addValueEventListener(new ValueEventListener() {
                                         @Override
                                         public void onDataChange(@NonNull DataSnapshot snapshot) {
                                             for(DataSnapshot dataSnapshot : snapshot.getChildren()){

                                               AllproductsModel allproductsModel=dataSnapshot.getValue(AllproductsModel.class);
                                                 allproductsModelList.add(allproductsModel);

                                             }
                                             allproductsAdapter.notifyDataSetChanged();
                                         }

                                         @Override
                                         public void onCancelled(@NonNull DatabaseError error) {

                                             Toast.makeText(getActivity(), "Something went wrong", Toast.LENGTH_SHORT).show();
                                         }

    });


       /* mStorageRef = FirebaseStorage.getInstance().getReference();
        categoryModelList = new ArrayList<>();
        init();*/




    return root;
    }
   /* private void init(){

        clearAll();
    }

    private void clearAll(){

    }*/


//    @Override
//    public void onBackPressed() {
//        AlertDialog.Builder builder= new AlertDialog.Builder(getContext());
//        builder.setMessage("Do you want to exit?")
//                .setCancelable(false)
//                .setPositiveButton("yes", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialogInterface, int i) {
//                        //    MainActivity.super.onBackPressed();
//
//                    }
//                })
//                .setNegativeButton("No", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialogInterface, int i) {
//                        dialogInterface.cancel();
//                    }
//                });
//        AlertDialog alertDialog=builder.create();
//        alertDialog.show();
//
//    }

}
