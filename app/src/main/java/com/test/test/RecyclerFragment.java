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

import com.test.test.databinding.FragmentRecyclerBinding;
import com.test.test.models.Usuario;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RecyclerFragment extends Fragment {
    private FragmentRecyclerBinding binding;
    List<Usuario> usuarios = new ArrayList<>();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_recycler, container, false);
        binding = DataBindingUtil.bind(v);

        usuarios.addAll(Arrays.asList(
                new Usuario("Ivan", "Noe"),
                new Usuario("Kadece", "ha muerto"),
                new Usuario("Paula", "Pchan")
        ));
        binding.recycler.setAdapter(new MyAdapter(usuarios));
        binding.setFrag(this);
        return v;
    }


    public void addMore(View v) {
        usuarios.add(new Usuario("Usuario", "" + usuarios.size()));
        binding.recycler.getAdapter().notifyItemInserted(usuarios.size() - 1);
    }

    public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
        private List<Usuario> mDataset;

        class MyViewHolder extends RecyclerView.ViewHolder {
            TextView nombre, apellido;

            MyViewHolder(View v) {
                super(v);
                nombre = v.findViewById(R.id.tvNombre);
                apellido = v.findViewById(R.id.tvApellido);
            }
        }

        MyAdapter(List<Usuario> myDataset) {
            mDataset = myDataset;
        }

        @Override
        public MyAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.recycler_item, parent, false);
            return new MyViewHolder(v);
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {
            holder.nombre.setText(mDataset.get(position).nombre);
            holder.apellido.setText(mDataset.get(position).apellido);
        }

        @Override
        public int getItemCount() {
            return mDataset.size();
        }
    }
}
