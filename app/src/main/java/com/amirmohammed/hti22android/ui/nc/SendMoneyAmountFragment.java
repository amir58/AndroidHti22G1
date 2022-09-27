package com.amirmohammed.hti22android.ui.nc;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.amirmohammed.hti22android.R;
import com.amirmohammed.hti22android.databinding.FragmentSendMoneyAmountBinding;

public class SendMoneyAmountFragment extends Fragment {
    FragmentSendMoneyAmountBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentSendMoneyAmountBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        NavController navController = Navigation.findNavController(view);

        binding.btnConfirm.setOnClickListener(view1 -> navController.navigateUp());
    }

}