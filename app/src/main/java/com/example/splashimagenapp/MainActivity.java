package com.example.splashimagenapp;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.splashscreen.SplashScreen;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;

public class MainActivity extends AppCompatActivity {

    private final AtomicBoolean cargaTerminada =
            new AtomicBoolean(false);

    private final ExecutorService executor =
            Executors.newSingleThreadExecutor();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        SplashScreen splashScreen =
                SplashScreen.installSplashScreen(this);

        super.onCreate(savedInstanceState);

        splashScreen.setKeepOnScreenCondition(
                () -> !cargaTerminada.get());

        setContentView(R.layout.activity_main);

        prepararAplicacion();
    }

    private void prepararAplicacion() {

        executor.execute(() -> {

            try {

                // Solo para observar el SplashScreen en la práctica.
                Thread.sleep(1200);

                cargaTerminada.set(true);

            } catch (InterruptedException error) {

                Thread.currentThread().interrupt();
            }
        });
    }

    @Override
    protected void onDestroy() {

        executor.shutdownNow();

        super.onDestroy();
    }
}