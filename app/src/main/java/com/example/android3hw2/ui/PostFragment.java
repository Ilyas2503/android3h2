package com.example.android3hw2.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android3hw2.App;
import com.example.android3hw2.R;
import com.example.android3hw2.databinding.FragmentPostBinding;
import com.example.android3hw2.network.MockerModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PostFragment extends Fragment {

    private FragmentPostBinding binding;
    private NavController navController;
    private MockerModel model;
    private boolean trueUpdate;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentPostBinding.inflate(getLayoutInflater());
        navController = Navigation.findNavController(requireActivity(),R.id.host_fragment);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (getArguments()!=null){
            model = (MockerModel) requireArguments().getSerializable("key");
            trueUpdate = true;
            setPosts();
        }
        if (!trueUpdate){
            createPost();
        }else {
            updatePost();
        }
    }
    private MockerModel get(){
        return new MockerModel(binding.ed2.getText().toString()
                ,binding.ed3.getText().toString()
                ,Integer.parseInt(binding.ed1.getText().toString()),35);
    }
    private void updatePost() {
        App.mockerApi.update(get().getUser(),get()).enqueue(new Callback<MockerModel>() {
            @Override
            public void onResponse(Call<MockerModel> call, Response<MockerModel> response) {
                if (response.isSuccessful()){
                    navController.navigateUp();
                }
            }

            @Override
            public void onFailure(Call<MockerModel> call, Throwable t) {

            }
        });
    }

    private void createPost() {
        binding.button.setOnClickListener(v -> App.mockerApi.createMocker(get()).enqueue(new Callback<MockerModel>() {
            @Override
            public void onResponse(Call<MockerModel> call, Response<MockerModel> response) {
                if (response.isSuccessful()){
                    navController.navigateUp();
                }
            }

            @Override
            public void onFailure(Call<MockerModel> call, Throwable t) {

            }
        }));
    }

    private void setPosts() {
        binding.ed1.setText(String.valueOf(model.getUser()));
        binding.ed3.setText(model.getContent());
        binding.ed2.setText(model.getTitle());
    }
}