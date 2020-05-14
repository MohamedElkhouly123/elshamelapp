package com.example.elshamelapp.view.fragment.HomeCycle2.home.profileFragments;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.elshamelapp.R;
import com.example.elshamelapp.adapter.ChatAdpater;
import com.example.elshamelapp.data.model.ChatModel;
import com.example.elshamelapp.data.model.ClientData;
import com.example.elshamelapp.view.fragment.BaSeFragment;
import com.example.elshamelapp.view.fragment.HomeCycle2.home.HomeFragment;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.location.places.ui.PlacePicker;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.example.elshamelapp.data.local.SharedPreferencesManger.LoadUserData;
import static com.example.elshamelapp.utils.HelperMethod.replaceFragment;


public class ChatFragment extends BaSeFragment {
    private static final int PLACE_PICKER_REQUEST = 123;
    @BindView(R.id.chat_fragment_recycler_view)
    RecyclerView rv_chat;
    @BindView(R.id.chat_fragment_message_edit_text)
    EditText et_message_chat;

    String otherUserId;
    String ownerId;
    String type;
    private DatabaseReference mDatabase;
    private ClientData clientData;

    LinearLayoutManager layoutManager;
    ChatAdpater chatAdpater;

    List<ChatModel> data = new ArrayList<>();

    //    Views.LoadingView loadingView;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_chat, container, false);
        homeCycleActivity.setToolBar(View.VISIBLE, getString(R.string.Chat)
                , new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        onBack();
                    }
                });
        ButterKnife.bind(this, root);
        FirebaseApp.initializeApp(getActivity());
        mDatabase = FirebaseDatabase.getInstance().getReference();
//        loadingView = new Views.LoadingView(this);

        clientData = LoadUserData(getActivity());

//        loadingView.show();

//    ?
        type = "";
        type = getActivity().getIntent().getStringExtra("type");
        if (type.equals("owner")) {
            ownerId = getActivity().getIntent().getStringExtra("id");
            otherUserId = String.valueOf(clientData.getUser().getId());
        } else {
            otherUserId = getActivity().getIntent().getStringExtra("id");
            ownerId = String.valueOf(clientData.getUser().getId());
        }
//   ?

        layoutManager = new LinearLayoutManager(getContext());
        rv_chat.setLayoutManager(layoutManager);
        chatAdpater = new ChatAdpater(data, getContext(), getActivity());
        rv_chat.setAdapter(chatAdpater);
        mDatabase.child("users").child(otherUserId).child(ownerId).orderByChild("time").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                data = new ArrayList<>();
                for (DataSnapshot childDataSnapshot : dataSnapshot.getChildren()) {
                    ChatModel model = new ChatModel();
                    if (childDataSnapshot.child("message").getValue() != null)
                        model.setMessage(childDataSnapshot.child("message").getValue().toString());
                    if (childDataSnapshot.child("ownerId").getValue() != null)
                        model.setUserId(childDataSnapshot.child("ownerId").getValue().toString());
                    if (model != null)
                        data.add(model);
                }
//                loadingView.dismiss();
                if (data != null)
                    chatAdpater = new ChatAdpater(data, getContext(), getActivity());
                rv_chat.setAdapter(chatAdpater);
                rv_chat.scrollToPosition(data.size());

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        return root;

    }

    @OnClick(R.id.send)
    void sendMesaage() {
        if (!TextUtils.isEmpty(et_message_chat.getText().toString())) {
            ChatModel model = new ChatModel();
            if (type.equals("owner"))
                model.setUserId(otherUserId);
            else
                model.setUserId(ownerId);


            String currentTime = new SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(new Date());
            model.setTime(currentTime);
            model.setMessage(et_message_chat.getText().toString());
            model.setType("text");

            mDatabase.child("users").child(otherUserId).child(ownerId).push().setValue(model);
            et_message_chat.setText("");

        }
    }

//    private void showToast(String m) {
//        Toast.makeText(getActivity(), m, Toast.LENGTH_SHORT).show();
//    }


    //    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (requestCode == PLACE_PICKER_REQUEST){
//            if (resultCode == RESULT_OK) {
//                Place place = PlacePicker.getPlace(this, data);
//                if (place != null){
//                    LatLng latLng = place.getLatLng();
////                    MapModel mapModel = new MapModel(latLng.latitude + "", latLng.longitude + "");
////                    databaseReference.push().setValue(mapModel);
//                }
//            }
//        }
//    }
//
//
//    @NeedsPermission(Manifest.permission.ACCESS_FINE_LOCATION)
    private void locationPlacesIntent() {
        try {
            PlacePicker.IntentBuilder builder = new PlacePicker.IntentBuilder();
            startActivityForResult(builder.build(getActivity()), PLACE_PICKER_REQUEST);
        } catch (GooglePlayServicesRepairableException | GooglePlayServicesNotAvailableException e) {
            e.printStackTrace();
        }
    }

    //
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }


    @Override
    public void onBack() {
        replaceFragment(getActivity().getSupportFragmentManager(), R.id.home_activity_fram, new HomeFragment());
//        homeCycleActivity.setNavigationAndToolBar(View.VISIBLE,false);
        homeCycleActivity.buttonNavigation.getMenu().getItem(0).setChecked(true);
    }

//    @OnClick(R.id.send)
//    public void onViewClicked() {
//    }
}