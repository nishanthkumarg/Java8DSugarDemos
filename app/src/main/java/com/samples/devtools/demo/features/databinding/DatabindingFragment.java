package com.samples.devtools.demo.features.databinding;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.databinding.DataBindingUtil;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.samples.devtools.demo.R;
import com.samples.devtools.demo.databinding.FragmentMainBinding;

/**
 * Created by nishanthkumarg on 4/4/17.
 */

public class DatabindingFragment extends Fragment {

    private PhonewordViewModel mPhonewordViewModel;
    private FragmentMainBinding mBinding;

    public DatabindingFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mPhonewordViewModel = new PhonewordViewModel();
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_main, container, false);
        mBinding.setPhonewordVM(mPhonewordViewModel);
        View v = mBinding.getRoot();

        mBinding.callButton.setOnClickListener((View view)->{
            if(ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.CALL_PHONE)
                    != PackageManager.PERMISSION_GRANTED)
            {
                ActivityCompat.requestPermissions(getActivity(),
                        new String[]{Manifest.permission.CALL_PHONE},201);
            }
            else{
                call();
            }
        });
        mBinding.translateButton.setOnClickListener((View translateView)-> {
            mPhonewordViewModel.setPhoneWord(mBinding.phonewordText.getText().toString());
            mPhonewordViewModel.translatePhoneWord();
        });

        return v;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        switch (requestCode) {
            case 201:
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    call();
                }
                break;
        }
    }

    private void call(){
        final Intent callIntent = new Intent(Intent.ACTION_CALL);
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());
        alertDialogBuilder.setMessage(mBinding.callButton.getText());
        alertDialogBuilder.setNeutralButton(R.string.call_button_text,(dialog, which) -> {
            callIntent.setData(Uri.parse("tel:" + mPhonewordViewModel.getPhoneNumber()));
            PhonewordUtils.savePhoneword(getActivity(), mPhonewordViewModel.getPhoneWord());
            startActivity(callIntent);
        });
        alertDialogBuilder.setNegativeButton(R.string.cancel_text,(dialog, which)->{
            //Do nothing
        });
        alertDialogBuilder.show();
    }
}
