package br.ufjf.dcc196.dcc196sqlintro;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private SQLiteDatabase db;
    private Button btnCriaTabela;
    private Button btnExcluiTabela;
    private Button btnInsereRegistro;
    private Button btnListarRegistros;
    private Button btnExcluirRegistros;
    private Button btnAtualizarRegistros;
    private TextView txtSaida;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db = openOrCreateDatabase("biblioteca", MODE_PRIVATE, null);
        btnCriaTabela = (Button) findViewById(R.id.btnCriaTabela);
        btnExcluiTabela = (Button) findViewById(R.id.btnExcluiTabela);
        btnInsereRegistro = (Button) findViewById(R.id.btnInsereRegistro);
        btnListarRegistros = (Button) findViewById(R.id.btnListarRegistros);
        btnExcluirRegistros = (Button) findViewById(R.id.btnExcluirRegistros);
        btnAtualizarRegistros = (Button) findViewById(R.id.btnAtualizarRegistros);

        txtSaida = (TextView) findViewById(R.id.txtSaida);

        btnCriaTabela.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    db.execSQL("CREATE TABLE livro (id INTEGER PRIMARY KEY AUTOINCREMENT, titulo VARCHAR, ano INTEGER)");
                    Toast.makeText(getApplicationContext(),
                            "Tabela livro criada com sucesso!",
                            Toast.LENGTH_SHORT).show();
                    Log.i("DB", "Tabela livro criada com sucesso!");
                }catch (Exception e){
                    Log.e("DB","Erro ao criar a tabela");
                    Toast.makeText(getApplicationContext(),
                            "Erro ao criar a tabela",
                            Toast.LENGTH_LONG).show();
                }
            }
        });
        btnExcluiTabela.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    db.execSQL("DROP TABLE livro");
                    Toast.makeText(getApplicationContext(),
                            "Tabela livro excluída com sucesso!",
                            Toast.LENGTH_SHORT).show();
                    Log.i("DB", "Tabela livro excluída com sucesso!");
                }catch (Exception e){
                    Log.e("DB","Erro ao  excluir a tabela");
                    Log.e("DB",e.getLocalizedMessage());

                    Toast.makeText(getApplicationContext(),
                            "Erro ao excluir a tabela livro!",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnInsereRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {

                    db.execSQL("INSERT INTO livro(titulo, ano) VALUES('Livro X', 2017)");
                    Toast.makeText(getApplicationContext(),
                            "Registro inserido com sucesso!",
                            Toast.LENGTH_SHORT).show();
                    Log.i("DB", "Registro inserido com sucesso!");
                }catch (Exception e){
                    Log.e("DB","Erro ao inserir registro!");
                    Log.e("DB",e.getLocalizedMessage());

                    Toast.makeText(getApplicationContext(),
                            "Erro ao inserir registro!",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
        btnExcluirRegistros.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {

                    db.execSQL("DELETE FROM livro");
                    Toast.makeText(getApplicationContext(),
                            "Registros excluídos com sucesso!",
                            Toast.LENGTH_SHORT).show();
                    Log.i("DB", "Registros excluídos com sucesso!");
                }catch (Exception e){
                    Log.e("DB","Erro ao excluir registros!");
                    Log.e("DB",e.getLocalizedMessage());

                    Toast.makeText(getApplicationContext(),
                            "Erro ao excluir registros!",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
        btnAtualizarRegistros.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {

                    db.execSQL("UPDATE livro SET ano = ano + 1 ");
                    Toast.makeText(getApplicationContext(),
                            "Registros atualizados com sucesso!",
                            Toast.LENGTH_SHORT).show();
                    Log.i("DB", "Registros atualizados com sucesso!");
                }catch (Exception e){
                    Log.e("DB","Erro ao atualizar registros!");
                    Log.e("DB",e.getLocalizedMessage());

                    Toast.makeText(getApplicationContext(),
                            "Erro ao atualizar registros!",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
        btnListarRegistros.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    StringBuilder sb = new StringBuilder();
                    Cursor resultado = db.rawQuery("SELECT titulo, id,  ano FROM livro", null);
                    resultado.moveToPosition(-1);
                    while(resultado.moveToNext()){
                        Integer id = resultado.getInt(1);
                        String titulo = resultado.getString(0);
                        Integer ano = resultado.getInt(2);
                        sb.append(id);
                        sb.append(": ");
                        sb.append(titulo);
                        sb.append(" ");
                        sb.append(ano);
                        sb.append("\n");
                    }
                    txtSaida.setText(sb.toString());
                    Toast.makeText(getApplicationContext(),
                            "Registros obtidos com sucesso!",
                            Toast.LENGTH_SHORT).show();
                    Log.i("DB", "Registros obtidos com sucesso!");
                }catch (Exception e){
                    Log.e("DB","Erro ao listar registros!");
                    Log.e("DB",e.getLocalizedMessage());

                    Toast.makeText(getApplicationContext(),
                            "Erro ao listar registros!",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
