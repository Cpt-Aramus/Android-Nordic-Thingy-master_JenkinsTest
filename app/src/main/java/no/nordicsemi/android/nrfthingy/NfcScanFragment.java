package no.nordicsemi.android.nrfthingy;

import android.annotation.SuppressLint;
import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.nfc.NfcAdapter;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
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

    //Required stuff for nfc scan
    private TextView appLinkBlank;
    private TextView idBlank;
    private TextView websiteLinkBlank;
    private NfcAdapter usedNfcAdapter;
    private ListView nfcListView;
    private ListViewAdapter nfcAdapter;
    private String[] typeOfInformation = {"Android App Link:", "Mac-address:", "Nordic website link:"};
    private String[] nfcScanInformation;
    private int[] imageIds = {R.drawable.android_icon,R.drawable.nfc_icon, R.drawable.index};



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
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_nfc_scanning, container, false);

        if (getArguments() != null) {
            nfcLink = getArguments().getString(NFC_LINK);
        }
        if (getArguments() != null) {
            nfcId = getArguments().getString(NFC_ID);
        }
        if (getArguments() != null) {
            nfcUrl = getArguments().getString(NFC_URL);
        }

        nfcScanInformation = new String[]{nfcLink, nfcId, nfcUrl};

        ListView display = rootView.findViewById(R.id.listView);
        nfcAdapter = new ListViewAdapter();
        display.setAdapter(nfcAdapter);
        // Inflate the layout for this fragment
        return rootView;
    }

    private class ListViewAdapter extends BaseAdapter {

        public ListViewAdapter() {
            super();

        }


        @Override
        public int getCount() {
            return imageIds.length;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

           View view = getLayoutInflater().inflate(R.layout.list_view_item, null);
            ImageView image = view.findViewById(R.id.imageViewItem);
            TextView written = view.findViewById(R.id.textViewWritten);
            TextView blank = view.findViewById(R.id.textViewBlank);

            image.setImageResource(imageIds[position]);
            written.setText(typeOfInformation[position]);
            blank.setText(nfcScanInformation[position]);
            return view;
        }
    }
}



