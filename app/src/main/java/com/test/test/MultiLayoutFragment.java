package com.test.test;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.test.test.databinding.FragmentMultiLayoutBinding;
import com.test.test.databinding.FragmentRecyclerBinding;
import com.test.test.models.Usuario;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MultiLayoutFragment extends Fragment {
    private FragmentMultiLayoutBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_multi_layout, container, false);
        binding = DataBindingUtil.bind(v);
        return v;
    }
}
