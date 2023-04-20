package sv.edu.utec.diegoceron2516252019;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import sv.edu.utec.diegoceron2516252019.Helper.AdminSQLiteOpenHelper;

public class srcApellido extends AppCompatActivity {
    Button searchName;
    EditText edtCod,edtNom,edtAp,edtTel,edtCor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_src_apellido);

        searchName=findViewById(R.id.btnSearch);
        edtCod=findViewById(R.id.edtCodigosrc);
        edtNom=findViewById(R.id.edtsrcnom);
        edtAp=findViewById(R.id.edtApellidosrc);
        edtTel=findViewById(R.id.edtTelefonosrc);
        edtCor=findViewById(R.id.edtCorreosrc);

        searchName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AdminSQLiteOpenHelper admin =new AdminSQLiteOpenHelper(getApplicationContext(), "parcial",null,1);
                SQLiteDatabase bd =  admin.getWritableDatabase();
                String ap=edtAp.getText().toString();
                Cursor filas= bd.rawQuery("select cod,Nombre,Apellidos,Telefono,Correo from contactos where Apellidos='"+ap+"'",null);
                if(filas.moveToFirst()){
                    edtCod.setText(filas.getString(0));
                    edtNom.setText(filas.getString(1));
                    edtTel.setText(filas.getString(3));
                    edtCor.setText(filas.getString(4));
                }
                else {
                    Toast.makeText(getApplicationContext(),"No se encontro el usuario",Toast.LENGTH_LONG).show();
                }
                bd.close();
            }
        });
    }
}