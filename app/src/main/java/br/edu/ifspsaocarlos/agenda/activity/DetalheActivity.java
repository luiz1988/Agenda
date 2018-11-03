package br.edu.ifspsaocarlos.agenda.activity;

import android.content.Intent;
import android.graphics.drawable.RippleDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;

import br.edu.ifspsaocarlos.agenda.data.ContatoDAO;
import br.edu.ifspsaocarlos.agenda.model.Contato;
import br.edu.ifspsaocarlos.agenda.R;


public class DetalheActivity extends AppCompatActivity {
    private Contato c;
    private ContatoDAO cDAO;
    private RatingBar tets;
//    private  TextView mRatingScale;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhe);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (getIntent().hasExtra("contato"))
        {
            this.c = (Contato) getIntent().getSerializableExtra("contato");
            EditText nameText = (EditText)findViewById(R.id.editTextNome);
            nameText.setText(c.getNome());
            EditText foneText = (EditText)findViewById(R.id.editTextFone);
            foneText.setText(c.getFone());
            EditText foneCasaText = (EditText)findViewById(R.id.editTextCasa);
            foneCasaText.setText(c.getTelefoneCasa());
            EditText dataAniversarioText = (EditText)findViewById(R.id.editTextData);
            dataAniversarioText.setText(c.getDataAniversario());
            EditText emailText = (EditText)findViewById(R.id.editTextEmail);
            emailText.setText(c.getEmail());
            RatingBar favorito = (RatingBar) findViewById(R.id.ratingBar);
            favorito.setRating(c.getRating());

            int pos =c.getNome().indexOf(" ");
            if (pos==-1)
                pos=c.getNome().length();
            setTitle(c.getNome().substring(0,pos));
        }



        cDAO = new ContatoDAO(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_detalhe, menu);
        if (!getIntent().hasExtra("contato"))
        {
            MenuItem item = menu.findItem(R.id.delContato);
            item.setVisible(false);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.salvarContato:
                salvar();
                return true;
            case R.id.delContato:
                apagar();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void apagar()
    {
        cDAO.apagaContato(c);
        Intent resultIntent = new Intent();
        setResult(3,resultIntent);
        finish();
    }

    private void salvar()
    {
        String name = ((EditText) findViewById(R.id.editTextNome)).getText().toString();
        String fone = ((EditText) findViewById(R.id.editTextFone)).getText().toString();
        String foneCasa = ((EditText) findViewById(R.id.editTextCasa)).getText().toString();
        String data = ((EditText) findViewById(R.id.editTextData)).getText().toString();
        String email = ((EditText) findViewById(R.id.editTextEmail)).getText().toString();
        float favorito = ((RatingBar)findViewById(R.id.ratingBar)).getRating();
//        float teste3 = ((RatingBar)findViewById(R.id.ratingBar)).getRating();

        if (c==null)
            c = new Contato();


        c.setNome(name);
        c.setFone(fone);
        c.setTelefoneCasa(foneCasa);
        c.setDataAniversario(data);
        c.setEmail(email);
        c.setRating(favorito);

        cDAO.salvaContato(c);
        Intent resultIntent = new Intent();
        setResult(RESULT_OK,resultIntent);
        finish();
    }
}

