package sv.edu.utec.diegoceron2516252019;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import sv.edu.utec.diegoceron2516252019.Helper.AdminSQLiteOpenHelper;
import sv.edu.utec.diegoceron2516252019.clases.Delete;
import sv.edu.utec.diegoceron2516252019.clases.Insertar;
import sv.edu.utec.diegoceron2516252019.clases.Principal;
import sv.edu.utec.diegoceron2516252019.clases.Update;

public class MainActivity extends AppCompatActivity {
    BottomNavigationView btnNav;
    EditText edtCod,edtNombre,edtApellido,edtTelefono,edtCorreo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnNav=findViewById(R.id.btnNav);

        btnNav.setOnNavigationItemSelectedListener((BottomNavigationView.OnNavigationItemSelectedListener) navListener);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            Fragment seleccionFrag = null;
            switch(item.getItemId()){
                case R.id.nav_home:
                    seleccionFrag = new Principal();
                    break;
                case R.id.nav_insert:
                    seleccionFrag = new Insertar();
                    break;
                case R.id.nav_edit:
                    seleccionFrag = new Update();
                    break;
                case R.id.nav_delete:
                    seleccionFrag = new Delete();
                    break;
            }
            getSupportFragmentManager().beginTransaction().replace(R.id.fragmentCont, seleccionFrag).commit();
            return true;
        }
    };

    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_configuraciones,menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem items){

        switch(items.getItemId()){
            case R.id.opcion1:
                Toast.makeText(this,"Selecciono la opcion "+1,Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this, srcNombre.class);
                startActivity(intent);
                return true;
            case R.id.opcion2:
                Toast.makeText(this,"Selecciono la opcion "+2,Toast.LENGTH_SHORT).show();
                Intent intent2 = new Intent(this, srcApellido.class);
                startActivity(intent2);
                return true;
            case R.id.opcion3:
                Toast.makeText(this,"Selecciono la opcion "+3,Toast.LENGTH_SHORT).show();
                Intent intent3 = new Intent(this, srctelefono.class);
                startActivity(intent3);
                return true;
            case R.id.opcion4:
                Toast.makeText(this,"Selecciono la opcion "+4,Toast.LENGTH_SHORT).show();
                Intent intent4 = new Intent(this, srcCorreo.class);
                startActivity(intent4);
                return true;
        }

        return super.onOptionsItemSelected(items);
    }

    public void Insert(View v){
        AdminSQLiteOpenHelper admin =new AdminSQLiteOpenHelper(getApplicationContext(), "parcial",null,1);
        SQLiteDatabase bd =  admin.getWritableDatabase();
        edtNombre=findViewById(R.id.edtNombre);
        edtApellido=findViewById(R.id.edtApellido);
        edtTelefono=findViewById(R.id.edtTelefono);
        edtCorreo=findViewById(R.id.edtEmail);

        String nom= edtNombre.getText().toString();
        String ap=edtApellido.getText().toString();
        String te=edtTelefono.getText().toString();
        String co=edtCorreo.getText().toString();

        ContentValues informacion = new ContentValues();
        informacion.put("Nombre",nom);
        informacion.put("Apellidos",ap);
        informacion.put("Telefono", te);
        informacion.put("Correo", co);

        try {
            bd.insert("contactos",null,informacion);
            bd.close();
            Toast.makeText(getApplicationContext(),"Se inserto el usuario",Toast.LENGTH_LONG).show();
        }
        catch (Exception e){
            Toast.makeText(getApplicationContext(), e.getCause().toString(),Toast.LENGTH_LONG).show();
        }
    }

    public void Upd (View v){
        AdminSQLiteOpenHelper admin =new AdminSQLiteOpenHelper(getApplicationContext(), "parcial",null,1);
        SQLiteDatabase bd =  admin.getWritableDatabase();

        edtCod=findViewById(R.id.edtCod);
        edtNombre=findViewById(R.id.edtNombre);
        edtApellido=findViewById(R.id.edtApellido);
        edtTelefono=findViewById(R.id.edtTelefono);
        edtCorreo=findViewById(R.id.edtEmail);

        String cod=edtCod.getText().toString();
        String nom= edtNombre.getText().toString();
        String ap=edtApellido.getText().toString();
        String te=edtTelefono.getText().toString();
        String co=edtCorreo.getText().toString();

        ContentValues informacion = new ContentValues();

        informacion.put("Nombre",nom);
        informacion.put("Apellidos",ap);
        informacion.put("Telefono", te);
        informacion.put("Correo", co);

        int cat=bd.update("contactos",informacion,
                "cod="+cod,null);
        bd.close();
        if(cat==1){
            Toast.makeText(getApplicationContext(),"Se modifico el usuario",Toast.LENGTH_LONG).show();
        }
        else
        {
            Toast.makeText(getApplicationContext(),"No se modifico el usuario",Toast.LENGTH_LONG).show();
        }

        bd.close();

    }

    public void Delete (View v){
        AdminSQLiteOpenHelper admin =new AdminSQLiteOpenHelper(getApplicationContext(), "parcial",null,1);
        SQLiteDatabase bd =  admin.getWritableDatabase();

        edtCod=findViewById(R.id.edtCoddelete);
        String cod=edtCod.getText().toString();

        int cat=bd.delete("contactos",
                "cod="+cod,null);
        bd.close();
        edtCod.setText("");
        if(cat==1){
            Toast.makeText(getApplicationContext(),"Se borro el usuario",Toast.LENGTH_LONG).show();
        }
        else
        {
            Toast.makeText(getApplicationContext(),"No se borro el usuario",Toast.LENGTH_LONG).show();
        }

    }
}