package com.example.aluno.projetores.fragments;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.aluno.projetores.Devolver;
import com.example.aluno.projetores.R;
import com.example.aluno.projetores.Emprestar;
import com.example.aluno.projetores.models.Emprestimo;
import com.example.aluno.projetores.models.Professor;
import com.example.aluno.projetores.models.Projetor;
import com.google.android.gms.vision.CameraSource;
import com.google.android.gms.vision.Detector;
import com.google.android.gms.vision.barcode.Barcode;
import com.google.android.gms.vision.barcode.BarcodeDetector;

import java.io.IOException;

public class HomeFragment extends Fragment {

    private final int MY_PERMISSIONS_REQUEST_CAMERA = 10;

    private SurfaceView cameraView = null;
    private TextView barcodeInfo = null;
    private BarcodeDetector barcodeDetector = null;
    private CameraSource cameraSource = null;

    public HomeFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        instantiateViews();

        qrCodeInit();

    }

    public void instantiateViews() {

        cameraView = (SurfaceView) getView().findViewById(R.id.camera_view);
        barcodeInfo = (TextView) getView().findViewById(R.id.code_info);

        barcodeDetector =
                new BarcodeDetector.Builder(getContext())
                        .setBarcodeFormats(Barcode.QR_CODE)
                        .build();

        cameraSource = new CameraSource
                .Builder(getContext(), barcodeDetector)
                .build();

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_CAMERA: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    qrCodeInit();

                } else {

                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                        getActivity().finishAffinity();
                    }
                }
                return;
            }

        }
    }

    public void qrCodeInit() {

        cameraView.getHolder().addCallback(new SurfaceHolder.Callback() {

            @Override
            public void surfaceCreated(SurfaceHolder holder) {

                try {
                    if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                        ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.CAMERA}, MY_PERMISSIONS_REQUEST_CAMERA);
                    } else {
                        cameraSource.start(cameraView.getHolder());
                    }
                } catch (IOException ie) {
                    Log.e("CAMERA SOURCE", ie.getMessage());
                }

            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
            }

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {
                cameraSource.stop();
            }
        });

        barcodeDetector.setProcessor(new Detector.Processor<Barcode>() {
            @Override
            public void release() {
            }

            @Override
            public void receiveDetections(Detector.Detections<Barcode> detections) {

                final SparseArray<Barcode> barcodes = detections.getDetectedItems();

                if (barcodes.size() != 0) {

                    Projetor projetor = Projetor.findByPatrimonio(getContext(), barcodes.valueAt(barcodes.size() - 1).displayValue);

                    if(projetor != null) {

                        if (projetor.getSituacao().equals(Projetor.PROJETOR_DISPONIVEL)) {
                            Intent intent = new Intent(getContext(), Emprestar.class);
                            intent.putExtra("PROJETOR", projetor);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(intent);
                        } else if (projetor.getSituacao().equals(Projetor.PROJETOR_EMPRESTADO)) {

                            Emprestimo emprestimo = Emprestimo.findByIdProjetor(getContext(), projetor.getId());
                            Professor professor = Professor.findById(new ProfessoresFragment().buscarProfessores(getContext()), emprestimo.getIdProfessor());
                            Intent intent = new Intent(getContext(), Devolver.class);
                            intent.putExtra("PROJETOR", projetor);
                            intent.putExtra("PROFESSOR", professor);
                            intent.putExtra("EMPRESTIMO", emprestimo);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(intent);
                        } else if (projetor.getSituacao().equals(Projetor.PROJETOR_ESTRAGADO)) {
                            Toast.makeText(getActivity().getApplicationContext(), "Projetor com defeito", Toast.LENGTH_SHORT).show();
                        }

                    } else {

                        Toast.makeText(getActivity(), "QRCode inválido", Toast.LENGTH_SHORT).show();
                    }

                }

            }
        });
    }
}
