package com.juliojlgon.sqlite;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.juliojlgon.gasconsumer.Repostaje;
import com.juliojlgon.gasconsumer.Vehiculo;

public class DatabaseHelper extends SQLiteOpenHelper {

	// Logcat tag
	private static final String LOG = "Sqlite Help";

	// Database Version
	private static final int DATABASE_VERSION = 1;

	// Database Name
	private static final String DATABASE_NAME = "Consumos";

	// Table Names
	private static final String TABLE_VEHICULO = "Vehiculo";
	private static final String TABLE_DATOS = "Datos";
	private static final String TABLE_UNION = "Union";

	// Common column names
	private static final String KEY_ID = "id";
	private static final String KEY_NOMBRE = "nombre";

	// Datos Table - column names
	private static final String KEY_LITROS = "litros";
	private static final String KEY_KM = "kilometros";
	private static final String KEY_FECHA = "fecha";
	private static final String KEY_PRECIO = "precio";
	private static final String KEY_CONSUMO = "consumo";

	// Datos Table - column names
	private static final String KEY_VEHICULO_ID = "vehiculo_id";
	private static final String KEY_DATO_ID = "dato_id";

	// Table Create Statements
	// Vehiculo table create statement
	private static final String CREATE_TABLE_VEHICULO = "CREATE TABLE "
			+ TABLE_VEHICULO + "(" + KEY_ID + " INTEGER PRIMARY KEY,"
			+ KEY_NOMBRE + " TEXT UNIQUE" + ")";

	// Datos table create statement
	private static final String CREATE_TABLE_DATOS = "CREATE TABLE "
			+ TABLE_DATOS + "(" + KEY_ID + " INTEGER PRIMARY KEY," + KEY_LITROS
			+ " DOUBLE," + KEY_KM + " DOUBLE," + KEY_FECHA + " DATE,"
			+ KEY_CONSUMO + " DOUBLE," + KEY_PRECIO + " DOUBLE" + ")";

	// Union table create statement
	private static final String CREATE_TABLE_UNION = "CREATE TABLE "
			+ TABLE_UNION + "(" + KEY_ID + " INTEGER PRIMARY KEY,"
			+ KEY_VEHICULO_ID + " INTEGER," + KEY_DATO_ID + " INTEGER" + ")";

	public DatabaseHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	/*
	 * ************************************************************
	 * Creamos las tablas
	 * ************************************************************
	 */

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(CREATE_TABLE_VEHICULO);
		db.execSQL(CREATE_TABLE_DATOS);
		db.execSQL(CREATE_TABLE_UNION);
	}

	/*
	 * ************************************************************
	 * En caso de Upgrade
	 * ************************************************************
	 */
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// Eliminamos la tabla anterior
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_VEHICULO);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_DATOS);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_UNION);

		// Creamos la tabla de nuevo
		onCreate(db);
	}

	/*
	 * **********************************************************************
	 * Creamos el CRUD (Create, Read, Update and Delete
	 * **********************************************************************
	 */

	/*
	 * ******************************************
	 * Todo lo necesario para la tabla VEHICULOS
	 * ******************************************
	 */

	/*
	 * Creamos un vehiculo
	 */
	public long createVehiculo(Vehiculo vehiculo) {
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(KEY_NOMBRE, vehiculo.getNombre());

		// Insertamos la fila
		long vehiculo_id = db.insert(TABLE_VEHICULO, null, values);

		return vehiculo_id;
	}

	/*
	 * Recuperamos todos los vehiculos SELECT * FROM Vehiculos
	 */

	public List<Vehiculo> getAllVehiculos() {
		List<Vehiculo> vehiculos = new ArrayList<Vehiculo>();
		String selectQuery = "SELECT + FROM " + TABLE_VEHICULO;

		Log.e(LOG, selectQuery);
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor c = db.rawQuery(selectQuery, null);

		// Bucle para añadir todos a la lista
		if (c.moveToFirst()) {
			do {
				Vehiculo v = new Vehiculo();
				v.setID(c.getInt(c.getColumnIndex(KEY_ID)));
				v.setNombre(c.getString(c.getColumnIndex(KEY_NOMBRE)));

				// Añadimos a la lista
				vehiculos.add(v);
			} while (c.moveToNext());
		}
		return vehiculos;
	}

	/*
	 * Acualizamos un vehiculo
	 */

	public int updateVehiculo(Vehiculo vehiculo) {
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(KEY_NOMBRE, vehiculo.getNombre());

		// Actualizamos
		return db.update(TABLE_VEHICULO, values, KEY_ID + "= ?",
				new String[] { String.valueOf(vehiculo.getID()) });
	}

	/*
	 * ******************************************
	 * Todo lo necesario para la tabla DATOS
	 * ******************************************
	 */

	/*
	 * Creamos un Dato
	 */
	public long createDato(Repostaje dato, long[] vehiculo_ids) {
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(KEY_LITROS, dato.getLitros());
		values.put(KEY_KM, dato.getKm());
		values.put(KEY_FECHA, dato.getFecha());
		values.put(KEY_CONSUMO, dato.getConsumo());
		values.put(KEY_PRECIO, dato.getPrecio());

		// Insertamos fila
		long dato_id = db.insert(TABLE_DATOS, null, values);

		// Asignamos Dato a vehiculo correspondiente
		for (long vehiculo_id : vehiculo_ids) {
			createUnion(dato_id, vehiculo_id);
		}

		return dato_id;
	}

	/*
	 * Recuperamos un Dato
	 */

	public Repostaje getDato(long dato_id) {
		SQLiteDatabase db = getReadableDatabase();

		String selecQuery = "SELECT  * FROM " + TABLE_DATOS + " WHERE "
				+ KEY_ID + " = " + dato_id;

		Log.e(LOG, selecQuery);

		Cursor c = db.rawQuery(selecQuery, null);

		if (c != null)
			c.moveToFirst();

		Repostaje dato = new Repostaje();
		dato.setID(c.getInt(c.getColumnIndex(KEY_ID)));
		dato.setLitros(c.getDouble(c.getColumnIndex(KEY_LITROS)));
		dato.setKm(c.getDouble(c.getColumnIndex(KEY_KM)));
		dato.setFecha(c.getString(c.getColumnIndex(KEY_FECHA)));
		dato.setConsumo(c.getDouble(c.getColumnIndex(KEY_CONSUMO)));
		dato.setPrecio(c.getDouble(c.getColumnIndex(KEY_PRECIO)));

		return dato;
	}

	/*
	 * Recuperamos todos los Datos
	 */

	public List<Repostaje> getAllDatos() {
		List<Repostaje> datos = new ArrayList<Repostaje>();
		String selectedQuery = "SELECT * FROM " + TABLE_DATOS;

		Log.e(LOG, selectedQuery);

		SQLiteDatabase db = this.getReadableDatabase();
		Cursor c = db.rawQuery(selectedQuery, null);

		// Bucle para recuperarlos todos
		if (c.moveToFirst()) {
			do {
				Repostaje dato = new Repostaje();
				dato.setID(c.getInt(c.getColumnIndex(KEY_ID)));
				dato.setLitros(c.getDouble(c.getColumnIndex(KEY_LITROS)));
				dato.setKm(c.getDouble(c.getColumnIndex(KEY_KM)));
				dato.setFecha(c.getString(c.getColumnIndex(KEY_FECHA)));
				dato.setConsumo(c.getDouble(c.getColumnIndex(KEY_CONSUMO)));
				dato.setPrecio(c.getDouble(c.getColumnIndex(KEY_PRECIO)));

				// Añadimos al List
				datos.add(dato);
			} while (c.moveToNext());
		}

		return datos;
	}

	/*
	 * Recuperar los datos con un nombre especifico
	 */

	public List<Repostaje> getAllDatosByVehiculo(String nombre) {
		List<Repostaje> datos = new ArrayList<Repostaje>();

		String selectedQuery = "SELECT * FROM " + TABLE_DATOS + " d,"
				+ TABLE_VEHICULO + " v," + TABLE_UNION + " u WHERE v."
				+ KEY_NOMBRE + " = '" + nombre + "'" + " AND v." + KEY_ID
				+ " = " + "u." + KEY_VEHICULO_ID + " AND d." + KEY_ID + " = "
				+ "u." + KEY_DATO_ID;

		Log.e(LOG, selectedQuery);

		SQLiteDatabase db = this.getReadableDatabase();
		Cursor c = db.rawQuery(selectedQuery, null);

		// Bucle para recuperarlos todos
		if (c.moveToFirst()) {
			do {
				Repostaje dato = new Repostaje();
				dato.setID(c.getInt(c.getColumnIndex(KEY_ID)));
				dato.setLitros(c.getDouble(c.getColumnIndex(KEY_LITROS)));
				dato.setKm(c.getDouble(c.getColumnIndex(KEY_KM)));
				dato.setFecha(c.getString(c.getColumnIndex(KEY_FECHA)));
				dato.setConsumo(c.getDouble(c.getColumnIndex(KEY_CONSUMO)));
				dato.setPrecio(c.getDouble(c.getColumnIndex(KEY_PRECIO)));

				// Añadimos al List
				datos.add(dato);
			} while (c.moveToNext());
		}

		return datos;
	}

	/*
	 * Actualizar un Dato
	 */

	public int updateDato(Repostaje dato) {
		SQLiteDatabase bd = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(KEY_LITROS, dato.getLitros());
		values.put(KEY_KM, dato.getKm());
		values.put(KEY_FECHA, dato.getFecha());
		values.put(KEY_CONSUMO, dato.getConsumo());
		values.put(KEY_PRECIO, dato.getPrecio());

		// Actualizamos la fila.
		return bd.update(TABLE_DATOS, values, KEY_ID + " = ?",
				new String[] { String.valueOf(dato.getID()) });
	}

	/*
	 * Eliminamos un dato
	 */
	public void deleteDato(long dato_id) {
		SQLiteDatabase db = this.getWritableDatabase();
		db.delete(TABLE_DATOS, KEY_ID + " = ?",
				new String[] { String.valueOf(dato_id) });
	}

	// -------------------------------------Para hacer las foreign Keys----------------------------------------------- //	 
	public void deleteVehiculo(Vehiculo vehiculo,
			boolean Should_delete_All_Datos) {
		SQLiteDatabase db = this.getWritableDatabase();

		// Comprobamos si queremos eliminar todos los datos del vehiculo
		if (Should_delete_All_Datos) {
			// Recogemos todas las entradas del vehiculo en cuestión
			List<Repostaje> allDatosVehiculo = getAllDatosByVehiculo(vehiculo
					.getNombre());

			// Borramos todas las entradas
			for (Repostaje dato : allDatosVehiculo) {
				deleteDato(dato.getID());
			}
		}

		// Ahora eliminamos el vehiculo
		db.delete(TABLE_VEHICULO, KEY_ID + " = ?",
				new String[] { String.valueOf(vehiculo.getID()) });
	}

	/*
	 * Asignamos un dato a un vehiculo
	 */
	public long createUnion(long vehiculo_id, long dato_id) {
		SQLiteDatabase bd = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(KEY_VEHICULO_ID, vehiculo_id);
		values.put(KEY_DATO_ID, dato_id);

		Long id = bd.insert(TABLE_UNION, null, values);

		return id;
	}
	
	/*
	 * Cambiar el vehiculo al que pertenece un dato
	 */
	public int updateVehicleFromData(long id, long vehiculo_id) {
		SQLiteDatabase db = this.getWritableDatabase();
		
		ContentValues values = new ContentValues();
		values.put(KEY_VEHICULO_ID, vehiculo_id);
		
		//Actualizamos
		return db.update(TABLE_DATOS, values, KEY_ID + " = ?",
	            new String[] { String.valueOf(id) });
	}
	
	/*
	 * Cerrar conexion con la Base de datos
	 */
	
	public void closeDb() {
		SQLiteDatabase db = this.getReadableDatabase();
		if (db != null && db.isOpen())
			db.close();
	}

}// Fin
