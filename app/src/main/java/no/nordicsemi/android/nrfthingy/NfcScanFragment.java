package no.nordicsemi.android.nrfthingy;

import android.bluetooth.BluetoothDevice;
import android.nfc.NfcAdapter;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import no.nordicsemi.android.nrfthingy.common.Utils;

/**
 * This class provides the necessary backend to retrieve the NFC data
 * (Google Play Store Link, ID and nordic website link)
 * from the NFC module and presents it in a Linear Layout view.
 * A simple {@link Fragment} subclass.
 * Use the {@link NfcScanFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NfcScanFragment extends Fragment {

    //necessary TextViews
    private TextView appLinkWritten;
    private TextView appLinkBlank;
    private TextView idWritten;
    private TextView idBlank;
    private TextView websiteLinkWritten;
    private TextView websiteLinkBlank;
    private NfcAdapter usedNfcAdapter;



    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String NFC_ID = "nfcID";
    private static final String NFC_LINK = "nfcLink";
    private static final String NFC_URL = "nfcUrl";

    // TODO: Rename and change types of parameters
    private String nfcId;
    private String nfcLink;
    private String nfcUrl;


    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param device Parameter 1.
     * @return A new instance of fragment fragment_nfc_scanning.
     */
    // TODO: Rename and change types and number of parameters
    public static NfcScanFragment newInstance(BluetoothDevice device) {
        NfcScanFragment fragment = new NfcScanFragment();
        final Bundle args = new Bundle();
        args.putString(NFC_ID, MainActivity.NFCID);
        args.putString(NFC_URL, MainActivity.NFCURL);
        args.putString(NFC_LINK, MainActivity.NFCLINK);
        args.putParcelable(Utils.CURRENT_DEVICE, device);
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        usedNfcAdapter = NfcAdapter.getDefaultAdapter(this.getContext());
        if(usedNfcAdapter == null) {
            Toast.makeText(this.getContext(), "This device does not support NFC!", Toast.LENGTH_LONG).show();
            return;
        }


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_nfc_scanning, container, false);
        nfcLink = getArguments().getString(NFC_LINK);
        nfcId = getArguments().getString(NFC_ID);
        nfcUrl = getArguments().getString(NFC_URL);
        appLinkWritten = rootView.findViewById(R.id.app_link_written);
        idWritten = rootView.findViewById(R.id.mac_address_written);
        websiteLinkWritten = rootView.findViewById(R.id.website_link_written);
        appLinkBlank = rootView.findViewById(R.id.app_link_blank);
        appLinkBlank.setText(nfcLink);
        idBlank = rootView.findViewById(R.id.mac_address_blank);
        idBlank.setText(nfcId);
        websiteLinkBlank = rootView.findViewById(R.id.website_link_blank);
        websiteLinkBlank.setText(nfcUrl);
        // Inflate the layout for this fragment
        return rootView;

    }

}



