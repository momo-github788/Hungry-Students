package za.ac.cput;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.time.LocalDateTime;
import java.util.List;

import za.ac.cput.ObjectivesRecyclerAdapter;
import za.ac.cput.R;
import za.ac.cput.domain.Objective;
import za.ac.cput.domain.Transaction;


@RequiresApi(api = Build.VERSION_CODES.O)
public class PointsHistoryFragment extends Fragment {

    private RecyclerView pointsHistoryRecyclerView;
    private PointsHistoryRecyclerAdapter pointsHistoryRecyclerAdapter;

    private List<Transaction> transactionList = List.of(
            new Transaction("Food/Voucher", -350, LocalDateTime.now(),false),
            new Transaction("Deposit points",2250, LocalDateTime.now(),true),
            new Transaction("Donate points", -350, LocalDateTime.now(),false),
            new Transaction("Deposit points", +1000, LocalDateTime.now(),true),
            new Transaction("Donate points", +350, LocalDateTime.now(),true),
            new Transaction("Food/Voucher",-350, LocalDateTime.now(),false)
    );

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_points_history, null);


        buildRecyclerView(view);
        return view;
    }


    private void buildRecyclerView(View v) {
        pointsHistoryRecyclerView = v.findViewById(R.id.objectiveRecyclerView);
        pointsHistoryRecyclerView.setHasFixedSize(true);
        pointsHistoryRecyclerView.setLayoutManager(new LinearLayoutManager(PointsHistoryFragment.this.getActivity()));
        pointsHistoryRecyclerAdapter = new PointsHistoryRecyclerAdapter(PointsHistoryFragment.this.getActivity(), transactionList);
        pointsHistoryRecyclerView.setAdapter(pointsHistoryRecyclerAdapter);
    }


    private void replaceFragment(Fragment fragment) {
        FragmentManager manager = getActivity().getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.frameLayout, fragment);
        transaction.commit();
    }
}