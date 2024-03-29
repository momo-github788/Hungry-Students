package za.ac.cput;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import za.ac.cput.domain.Student;
import za.ac.cput.domain.Transaction;
import za.ac.cput.domain.TransactionType;
import za.ac.cput.repository.impl.StudentObjectiveRepositoryImpl;
import za.ac.cput.repository.impl.StudentRepositoryImpl;
import za.ac.cput.repository.impl.TransactionRepositoryImpl;
import za.ac.cput.utils.DBUtils;

public class DonatePointsFragment extends Fragment implements View.OnClickListener{

    private CardView donatePointsCardView;
    private EditText donatePointsEmailAddressEditText, donatePointsAmountEditText;
    private TextView donateUserEmailAddress,currentPointBalanceTextView;
    private Button donatePointsBtn, pointsHistoryBtn, donatePointsSearchUserBtn;

    private LinearLayout donatePointsUserNotFoundContainer;

    private StudentObjectiveRepositoryImpl studentObjectiveRepository;
    private StudentRepositoryImpl studentRepository;

    private TransactionRepositoryImpl transactionRepository;

    private String authenticatedStudentName;
    private String authenticatedStudentEmail;
    private int authenticatedStudentId;
    private Student donateToStudent = null;

    private Student authenticatedStudent;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_donate_points, null);
        donatePointsCardView = view.findViewById(R.id.donatePointsCardView);
        donatePointsAmountEditText = view.findViewById(R.id.donatePointsAmountEditText);
        currentPointBalanceTextView = view.findViewById(R.id.currentPointBalanceTextView);
        donatePointsEmailAddressEditText = view.findViewById(R.id.donatePointsEmailAddressEditText);
        donatePointsBtn = view.findViewById(R.id.donatePointsBtn);
        pointsHistoryBtn = view.findViewById(R.id.pointsHistoryBtn);
        donatePointsUserNotFoundContainer = view.findViewById(R.id.donatePointsUserNotFoundContainer);
        donatePointsSearchUserBtn = view.findViewById(R.id.donatePointsSearchUserBtn);
        donateUserEmailAddress = view.findViewById(R.id.donateUserEmailAddress);

        transactionRepository = new TransactionRepositoryImpl(getActivity());



        studentRepository = new StudentRepositoryImpl(getActivity());
        authenticatedStudentEmail = getActivity().getIntent().getStringExtra(DBUtils.AUTHENTICATED_STUDENT_EMAIL);
        authenticatedStudentName = getActivity().getIntent().getStringExtra(DBUtils.AUTHENTICATED_STUDENT_NAME);
        authenticatedStudentId = getActivity().getIntent().getIntExtra(DBUtils.AUTHENTICATED_STUDENT_ID, -999);



        authenticatedStudent = studentRepository.getStudent(authenticatedStudentId);


        currentPointBalanceTextView.setText(authenticatedStudent.getPointBalance() + " points");
        donatePointsCardView.setVisibility(View.GONE);

        donatePointsBtn.setEnabled(false);

        donatePointsSearchUserBtn.setOnClickListener(this);
        pointsHistoryBtn.setOnClickListener(this);
        donatePointsBtn.setOnClickListener(this);

        return view;
    }


    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onClick(View view) {
        if(view == donatePointsSearchUserBtn) {
            searchForStudent();
        } else if(view == pointsHistoryBtn) {
            replaceFragment(new TransactionHistoryFragment());
        } else if (view == donatePointsBtn) {
            donatePoints();
        }
    }




    private void replaceFragment(Fragment fragment) {
        FragmentManager manager = getActivity().getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.frameLayout, fragment).addToBackStack("tag").commit();
    }


    @RequiresApi(api = Build.VERSION_CODES.O)
    private void donatePoints() {
        String pointsToSend = donatePointsAmountEditText.getText().toString().trim();


        if(authenticatedStudent.getPointBalance()<=0){
            authenticatedStudent.setPointBalance(7500);
            studentRepository.updateStudentPoints(authenticatedStudent);
        }

        if(pointsToSend.equals("")) {
            donatePointsAmountEditText.setError("Please enter an amount to send");
            return;
        }
        int parsedPointsToSend = Integer.parseInt(pointsToSend);

        if(authenticatedStudent.getPointBalance() < parsedPointsToSend) {
            Toast.makeText(getActivity(), "Insufficient points balance.", Toast.LENGTH_LONG).show();
            return;
        }

        authenticatedStudent.setPointBalance((int) (authenticatedStudent.getPointBalance() - parsedPointsToSend));
        authenticatedStudent.setDonatedPointsBalance(authenticatedStudent.getDonatedPointsBalance() + parsedPointsToSend);
        donateToStudent.setPointBalance((int)donateToStudent.getPointBalance() + parsedPointsToSend);
        studentRepository.updateStudentPoints(authenticatedStudent);
        studentRepository.updateStudentPoints(donateToStudent);

        Toast.makeText(getActivity(), "You have successfully sent " + pointsToSend + " to " + donateToStudent.getEmailAddress(), Toast.LENGTH_LONG).show();
        donatePointsBtn.setEnabled(false);

        transactionRepository.createTransaction(new Transaction(
                donateToStudent.getStudentId(), "Donate Points",  authenticatedStudentId, TransactionType.DONATE_SEND, parsedPointsToSend,
                true,authenticatedStudentId
        ));

        transactionRepository.createTransaction(new Transaction(
                authenticatedStudentId, "Donate Points",  donateToStudent.getStudentId(), TransactionType.DONATE_RECEIVE, parsedPointsToSend,
                true,donateToStudent.getStudentId()
        ));
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                replaceFragment(new HomeFragment());
            }
        },1500);

    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void searchForStudent() {
        String studentEmail = donatePointsEmailAddressEditText.getText().toString().trim();
        //String studentEmail = "demo@gmail.com";

        if(studentEmail.equals("")) {
            donatePointsEmailAddressEditText.setError("Please enter a value");
        }

        if(studentRepository.existsByEmail(studentEmail)) {

            if(studentEmail.equalsIgnoreCase(authenticatedStudent.getEmailAddress())) {
                donatePointsCardView.setVisibility(View.GONE);
                donatePointsUserNotFoundContainer.setVisibility(View.GONE);
                Toast.makeText(getActivity(), "You cannot donate points to yourself.", Toast.LENGTH_LONG).show();
                return;
            }
            donatePointsCardView.setVisibility(View.VISIBLE);
            donateToStudent = studentRepository.getStudent(studentEmail);
            donatePointsBtn.setEnabled(true);

            donateUserEmailAddress.setText(studentEmail);
            donatePointsUserNotFoundContainer.setVisibility(View.GONE);
        } else {
            donatePointsBtn.setEnabled(false);
            donatePointsCardView.setVisibility(View.GONE);
            donatePointsUserNotFoundContainer.setVisibility(View.VISIBLE);
        }

    }
}