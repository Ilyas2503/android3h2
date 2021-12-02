package com.example.android3hw2.ui;

import android.app.AlertDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.android3hw2.App;
import com.example.android3hw2.R;
import com.example.android3hw2.databinding.FragmentFormBinding;
import com.example.android3hw2.network.MockerModel;
import com.example.android3hw2.network.RetrofitClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FormFragment extends Fragment {

    private FragmentFormBinding binding;
    private FormAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentFormBinding.inflate(inflater,container,false);
        adapter = new FormAdapter();
        adapter.setOnItemClickListener(new FormAdapter.OnItemClickListener() {
            @Override
            public void clickItem(int position) {
                Bundle bundle = new Bundle();
                MockerModel model = adapter.getItem(position);
                bundle.putSerializable("key",model);
                NavController navController = Navigation.findNavController(requireActivity(), R.id.host_fragment);
                navController.navigate(R.id.postFragment,bundle);
            }

            @Override
            public void clickLongItem(int position) {
                AlertDialog.Builder alert = new AlertDialog.Builder(requireActivity());
                alert.setTitle("Внимание!").setMessage("Вы точно хотите удалить предмет?").setPositiveButton("Да",(dialog, which) -> {
                    App.mockerApi.deleteMockerMode(adapter.getItem(position).getId()).enqueue(new Callback<MockerModel>() {
                        @Override
                        public void onResponse(Call<MockerModel> call, Response<MockerModel> response) {
                            if (response.isSuccessful() && response.body() !=null){
                                adapter.remove(position);
                            }
                        }

                        @Override
                        public void onFailure(Call<MockerModel> call, Throwable t) {

                        }
                    });
                }).setNegativeButton("Нет",(dialog, which) -> dialog.dismiss()).show();
            }
        });

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);



        App.mockerApi.getPosts().enqueue(new Callback<List<MockerModel>>() {
            @Override
            public void onResponse(Call<List<MockerModel>> call, Response<List<MockerModel>> response) {
                if (response.isSuccessful()&&response.body()!= null) {
                    adapter.addItemsModelList(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<MockerModel>> call, Throwable t) {
                Toast.makeText(requireActivity(), "Error", Toast.LENGTH_SHORT).show();
            }
        });

        binding.mockerRv.setAdapter(adapter);
        binding.fabFb.setOnClickListener(v -> {
            open();
        });
    }

    private void open() {
        NavController navController = Navigation.findNavController(requireActivity(), R.id.host_fragment);
        navController.navigate(R.id.postFragment);
    }
}