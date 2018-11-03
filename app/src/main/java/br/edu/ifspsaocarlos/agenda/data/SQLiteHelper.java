package br.edu.ifspsaocarlos.agenda.data;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

class SQLiteHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "agenda.db";
    static final String DATABASE_TABLE = "contatos";
    static final String KEY_ID = "id";
    static final String KEY_NAME = "nome";
    static final String KEY_FONE = "fone";
    static final String KEY_FONECASA = "casa";
    static final String KEY_DATA = "data";
    static final String KEY_EMAIL = "email";
    static final String KEY_FAVORITO = "favorito";
    private static final int DATABASE_VERSION = 11;
    private static final String DATABASE_CREATE = "CREATE TABLE "+ DATABASE_TABLE +" (" +
            KEY_ID  +  " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            KEY_NAME + " TEXT NOT NULL, " +
            KEY_FONE + " TEXT, "  +
            KEY_FONECASA + " TEXT, "  +
            KEY_DATA + " TEXT, "  +
            KEY_EMAIL + " TEXT," +
            KEY_FAVORITO + "REAL);";

    SQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {

      /*  if (newVersion > oldVersion) {
            database.execSQL("ALTER TABLE contatos ADD COLUMN data STRING");
        }*/
//        String sql = "DROP TABLE IF EXISTS contatos ";
//        database.execSQL(sql);
//        onCreate(database);
    }
}

