package za.ac.cput.utils;

public class DBUtils {

    // Common Database attributes
    public static final String AUTHENTICATED_STUDENT_EMAIL = "studentEmail";
    public static final String AUTHENTICATED_STUDENT_ID = "studentId";
    public static final String AUTHENTICATED_STUDENT_NAME = "studentName";
    public static final String DATABASE_NAME = "hungrystudents.db";
    public static final int DATABASE_VERSION = 8;

    // STUDENT TABLE
    public static final String STUDENT_TABLE = "student";

    public static final String COLUMN_STUDENT_ID = "id";
    public static final String COLUMN_STUDENT_FULL_NAME = "full_name";
    public static final String COLUMN_STUDENT_EMAIL_ADDRESS = "email_address";
    public static final String COLUMN_STUDENT_DATE_OF_BIRTH = "date_of_birth";

    public static final String COLUMN_STUDENT_CREATED_AT = "created_at";

    public static final String COLUMN_STUDENT_TOTAL_DONATED_POINTS= "donated_points";
    public static final String COLUMN_STUDENT_POINTS_BALANCE = "point_balance";
    public static final String COLUMN_STUDENT_PASSWORD = "password";


    public static final String CREATE_STUDENT_TABLE_QUERY = "CREATE TABLE IF NOT EXISTS " + STUDENT_TABLE + "(" + COLUMN_STUDENT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + COLUMN_STUDENT_FULL_NAME + " TEXT,"  + COLUMN_STUDENT_EMAIL_ADDRESS + " TEXT," + COLUMN_STUDENT_DATE_OF_BIRTH + " TEXT,"
            + COLUMN_STUDENT_POINTS_BALANCE + " INTEGER," + COLUMN_STUDENT_TOTAL_DONATED_POINTS + " INTEGER, " + COLUMN_STUDENT_CREATED_AT + " TEXT," + COLUMN_STUDENT_PASSWORD + " TEXT)";

    public static final String DROP_STUDENT_TABLE_QUERY = "DROP TABLE IF EXISTS " + STUDENT_TABLE;



    // OBJECTIVE TABLE
    public static final String OBJECTIVE_TABLE = "objective";
    public static final String COLUMN_OBJECTIVE_ID = "id";
    public static final String COLUMN_OBJECTIVE_TITLE = "title";
    public static final String COLUMN_OBJECTIVE_DESCRIPTION = "description";
    public static final String COLUMN_OBJECTIVE_POINTS = "points";

    public static final String CREATE_OBJECTIVE_TABLE_QUERY = "CREATE TABLE IF NOT EXISTS " + OBJECTIVE_TABLE + "(" + COLUMN_OBJECTIVE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + COLUMN_OBJECTIVE_TITLE + " TEXT,"  + COLUMN_OBJECTIVE_DESCRIPTION + " TEXT," + COLUMN_OBJECTIVE_POINTS + " INTEGER)";

    public static final String DROP_OBJECTIVE_TABLE_QUERY = "DROP TABLE IF EXISTS " + OBJECTIVE_TABLE;





    // STUDENT OBJECTIVE TABLE
    public static final String STUDENT_OBJECTIVE_TABLE = "student_objective";
    public static final String COLUMN_STUDENT_OBJECTIVE_STUDENT_ID = "student_id";
    public static final String COLUMN_STUDENT_OBJECTIVE_OBJECTIVE_ID = "objective_id";
    public static final String COLUMN_STUDENT_OBJECTIVE_TITLE = "objective_title";

    public static final String COLUMN_STUDENT_OBJECTIVE_DATE_ACHIEVED = "date_achieved";

    public static final String CREATE_STUDENT_OBJECTIVE_TABLE_QUERY = "CREATE TABLE IF NOT EXISTS " + STUDENT_OBJECTIVE_TABLE
        + "(" + COLUMN_STUDENT_OBJECTIVE_STUDENT_ID + " INTEGER REFERENCES " + STUDENT_TABLE + " (" + COLUMN_STUDENT_ID + "), "
            + COLUMN_STUDENT_OBJECTIVE_OBJECTIVE_ID + " INTEGER REFERENCES " + OBJECTIVE_TABLE + "( " + COLUMN_OBJECTIVE_ID + " ),"
            +  COLUMN_STUDENT_OBJECTIVE_DATE_ACHIEVED + " TEXT ," + COLUMN_STUDENT_OBJECTIVE_TITLE + " TEXT, PRIMARY KEY(" + COLUMN_STUDENT_OBJECTIVE_STUDENT_ID
            + ", " + COLUMN_STUDENT_OBJECTIVE_OBJECTIVE_ID + " ))";
    public static final String DROP_STUDENT_OBJECTIVE_TABLE_QUERY = "DROP TABLE IF EXISTS " + STUDENT_OBJECTIVE_TABLE;


    // SNACK TABLE
    public static final String CREATE_SNACK_TABLE_QUERY = "";
    public static final String DROP_SNACK_TABLE_QUERY = "";



    //  POINTS TRANSACTION TABLE
    public static final String POINTS_TRANSACTION_TABLE = "points_transaction";

    public static final String COLUMN_POINTS_TRANSACTION_ID = "id";

    public static final String CREATE_POINTS_TRANSACTION_TABLE_QUERY = "";
    public static final String DROP_POINTS_TRANSACTION_TABLE_QUERY = "";

    // POINTS HISTORY TABLE
    public  static final String POINTS_HISTORY_TABLE = "points_history";

    public static final String COLUMN_TITLE = "title";

    public  static final String COLUMN_TRANSACTION_ID = "transaction_id";

    public static final String COLUMN_DESCRIPTION = "description";

    public static final String COLUMN_POINTS = "points";

    public static final String CREATE_POINTS_HISTORY_TABLE_QUERY = "CREATE TABLE IF NOY EXISTS" + POINTS_HISTORY_TABLE +
            "(" + COLUMN_TITLE + "TEXT," + COLUMN_TRANSACTION_ID + "INTEGER REFRENCES"
            + COLUMN_DESCRIPTION + "TEXT," + COLUMN_POINTS + "INTEGER)";

    public static final String DROP_POINTS_HISTORY_QUERY = "DROP TABLE IF EXISTS " + POINTS_HISTORY_TABLE;


}
