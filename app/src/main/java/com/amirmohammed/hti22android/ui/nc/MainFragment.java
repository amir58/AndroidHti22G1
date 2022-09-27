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
import com.amirmohammed.hti22android.databinding.FragmentMainBinding;


public class MainFragment extends Fragment {

    FragmentMainBinding binding;
    NavController navController;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentMainBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = Navigation.findNavController(view);

        clicks();
    }

    private void clicks() {
        binding.btnBalance.setOnClickListener(view ->
                navController.navigate(R.id.action_mainFragment_to_balanceFragment));

        binding.btnSendMoney.setOnClickListener(view ->
                navController.navigate(R.id.action_mainFragment_to_sendMoneyNameFragment));
    }

}