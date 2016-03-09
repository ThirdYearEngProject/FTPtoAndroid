package team_bravo.rpi;

import android.content.pm.PackageInstaller;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.jcraft.jsch.*;

import java.util.ArrayList;

public class Main extends AppCompatActivity {

    PackageInstaller.Session session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void ConnectSSH() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    JSch jsch = new JSch();
                    Profile p = Profiles.get(CurrProfile);
                    session = jsch.getSession(p.Username, p.IpAddress, 22);
                    session.setPassword(p.Password);
                    Properties config = new Properties();
                    config.put("StrictHostKeyChecking", "no");
                    session.setConfig(config);
                    session.connect();

                    StartUpdateLoop();
                } catch (final Exception e) {
                    ThrowException(e.getMessage());
                }
            }
        }).start();
    }
}
