package com.example.loginandregister;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class RegisterActivity extends AppCompatActivity {
    private EditText emailedit, passedit,Phone,Name;
    private Button btnregis;
    private FirebaseAuth mAuth;

    FirebaseDatabase database;
    DatabaseReference ref;

    User user;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mAuth = FirebaseAuth.getInstance();

        emailedit = findViewById(R.id.email);
        passedit = findViewById(R.id.password);
        btnregis = findViewById(R.id.btnregis);
        Phone = findViewById(R.id.phone);
        Name = findViewById(R.id.name);

        database = FirebaseDatabase.getInstance();
        ref = database.getReference("User");

        user = new User();


        btnregis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                register();
            }
        });
    }

    private void getValues(){
        user.setPhone(Phone.getText().toString());
        user.setName(Name.getText().toString());
        // tao child cho de luu data
        ref.child(user.getName()).setValue(user);
    }


    private void register() {

        String email, pass;
        email = emailedit.getText().toString();
        pass = passedit.getText().toString();
        if (TextUtils.isEmpty(email)) {
            Toast.makeText(this, "Vui long nhap email", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(email)) {
            Toast.makeText(this, "Vui long nhap pass", Toast.LENGTH_SHORT).show();
            return;
        }
        // tao moi email va pass
        mAuth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(getApplicationContext(), "tao tai khoan thanh cong", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(getApplicationContext(), "tao tai khoan khong thanh cong", Toast.LENGTH_SHORT).show();
                }
            }
        });
        // add phone vao database
        Toast.makeText(this, "Da vao data", Toast.LENGTH_SHORT).show();
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                getValues();
                Toast.makeText(RegisterActivity.this,  "Data Insert", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }
}
