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
    private static final int DATABASE_VERSION = 15;
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

    /*public static final String DATABASE_DROP_TABLE = "DROP TABLE IF EXISTS " + DATABASE_TABLE;*/

    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
        if (oldVersion == 2) {
            database.execSQL("ALTER TABLE contatos ADD COLUMN favorito REAL");
        }
        if (oldVersion == 3) {
            database.execSQL("ALTER TABLE contatos ADD COLUMN casa STRING");
        }
        if (oldVersion == 4) {
            database.execSQL("ALTER TABLE contatos ADD COLUMN data STRING");
        }
    }

    public void onDowngrade(SQLiteDatabase database, int oldVersion, int newVersion) {
        if (oldVersion == 2) {
            database.execSQL("ALTER TABLE contatos ADD COLUMN favorito REAL");
        }
        if (oldVersion == 3) {
            database.execSQL("ALTER TABLE contatos ADD COLUMN casa STRING");
        }
        if (oldVersion == 4) {
            database.execSQL("ALTER TABLE contatos ADD COLUMN data STRING");
        }
    }
}

