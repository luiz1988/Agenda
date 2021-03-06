package br.edu.ifspsaocarlos.agenda.data;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import br.edu.ifspsaocarlos.agenda.model.Contato;
import java.util.ArrayList;
import java.util.List;


public class ContatoDAO {
    private SQLiteDatabase database;
    private SQLiteHelper dbHelper;

    public ContatoDAO(Context context) {
        this.dbHelper=new SQLiteHelper(context);
    }

    public  List<Contato> buscaTodosContatos()
    {
        database=dbHelper.getReadableDatabase();
        List<Contato> contatos = new ArrayList<>();

        Cursor cursor;

        String[] cols=new String[] {SQLiteHelper.KEY_ID,SQLiteHelper.KEY_NAME, SQLiteHelper.KEY_FONE, SQLiteHelper.KEY_FONECASA,SQLiteHelper.KEY_DATA,SQLiteHelper.KEY_EMAIL,SQLiteHelper.KEY_FAVORITO};

        cursor = database.query(SQLiteHelper.DATABASE_TABLE, cols, null , null,
                null, null, SQLiteHelper.KEY_NAME);

        while (cursor.moveToNext())
        {
            Contato contato = new Contato();
            contato.setId(cursor.getInt(0));
            contato.setNome(cursor.getString(1));
            contato.setFone(cursor.getString(2));
            contato.setTelefoneCasa(cursor.getString(3));
            contato.setDataAniversario(cursor.getString(4));
            contato.setEmail(cursor.getString(5));
            contato.setRating(cursor.getFloat(6));
            contatos.add(contato);
        }
        cursor.close();


        database.close();
        return contatos;
    }

    public  List<Contato> buscaContato(String nome,boolean favoritos)
    {
        database=dbHelper.getReadableDatabase();
        List<Contato> contatos = new ArrayList<>();

        Cursor cursor;

        String[] cols=new String[] {SQLiteHelper.KEY_ID,SQLiteHelper.KEY_NAME, SQLiteHelper.KEY_FONE,SQLiteHelper.KEY_FONECASA,SQLiteHelper.KEY_DATA, SQLiteHelper.KEY_EMAIL , SQLiteHelper.KEY_FAVORITO};
        String where = "";
        String argWhere[] = new String[0];

        if (nome != null) {
            where=SQLiteHelper.KEY_NAME + " like ? OR " + SQLiteHelper.KEY_EMAIL + " like ?";
            argWhere=new String[]{"%"+nome+"%","%"+nome+"%"};
        }
        if (favoritos && nome == null) {
            where = where + SQLiteHelper.KEY_FAVORITO + " = 1";
        }else if (favoritos && nome != null) {
            where = where + " AND " + SQLiteHelper.KEY_FAVORITO + " = ?";
            argWhere = new String[]{nome + "%", Integer.toString(1)};
        }

        cursor = database.query(SQLiteHelper.DATABASE_TABLE, cols, where, argWhere,
                null, null, SQLiteHelper.KEY_NAME);


        while (cursor.moveToNext())
        {
            Contato contato = new Contato();
            contato.setId(cursor.getInt(0));
            contato.setNome(cursor.getString(1));
            contato.setFone(cursor.getString(2));
            contato.setTelefoneCasa(cursor.getString(3));
            contato.setDataAniversario(cursor.getString(4));
            contato.setEmail(cursor.getString(5));
            contato.setRating(cursor.getFloat(6));
            contatos.add(contato);
        }
        cursor.close();

        database.close();
        return contatos;
    }

    public void salvaContato(Contato c) {
        database=dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(SQLiteHelper.KEY_NAME, c.getNome());
        values.put(SQLiteHelper.KEY_FONE, c.getFone());
        values.put(SQLiteHelper.KEY_FONECASA, c.getTelefoneCasa());
        values.put(SQLiteHelper.KEY_DATA, c.getDataAniversario());
        values.put(SQLiteHelper.KEY_EMAIL, c.getEmail());
        values.put(SQLiteHelper.KEY_FAVORITO, c.getRating());

       if (c.getId()>0)
          database.update(SQLiteHelper.DATABASE_TABLE, values, SQLiteHelper.KEY_ID + "="
                + c.getId(), null);
        else
           database.insert(SQLiteHelper.DATABASE_TABLE, null, values);

        database.close();
    }

    public void apagaContato(Contato c)
    {
        database=dbHelper.getWritableDatabase();
        database.delete(SQLiteHelper.DATABASE_TABLE, SQLiteHelper.KEY_ID + "="
                + c.getId(), null);
        database.close();
    }
}
